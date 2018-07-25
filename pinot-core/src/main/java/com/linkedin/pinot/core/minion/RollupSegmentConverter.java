/**
 * Copyright (C) 2014-2018 LinkedIn Corp. (pinot-core@linkedin.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.linkedin.pinot.core.minion;

import com.linkedin.pinot.common.config.IndexingConfig;
import com.linkedin.pinot.common.config.MultiLevelRollupSetting;
import com.linkedin.pinot.common.data.DimensionFieldSpec;
import com.linkedin.pinot.common.data.Schema;
import com.linkedin.pinot.common.exception.InvalidConfigException;
import com.linkedin.pinot.common.segment.SegmentMetadata;
import com.linkedin.pinot.core.minion.rollup.RollupRecordAggregator;
import com.linkedin.pinot.core.minion.rollup.RollupRecordTransformer;
import com.linkedin.pinot.core.minion.segment.RecordAggregator;
import com.linkedin.pinot.core.minion.segment.RecordTransformer;
import com.linkedin.pinot.core.segment.index.SegmentMetadataImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class RollupSegmentConverter {
  private static final String CONCATENATE = "CONCATENATE";
  private static final String ROLLUP = "ROLLUP";

  private List<File> _inputIndexDirs;
  private File _workingDir;
  private IndexingConfig _indexingConfig;
  private String _mergeType;
  private MultiLevelRollupSetting _rollupSetting;
  private Map<String, String> _rolllupPreAggregateType;

  public RollupSegmentConverter(@Nonnull List<File> inputIndexDirs, @Nonnull File workingDir, @Nonnull String mergeType,
      @Nullable Map<String, String> rollupPreAggregateType, @Nullable MultiLevelRollupSetting rollupSetting,
      @Nullable IndexingConfig indexingConfig) {
    _inputIndexDirs = inputIndexDirs;
    _workingDir = workingDir;
    _mergeType = mergeType;
    _rollupSetting = rollupSetting;
    _rolllupPreAggregateType = rollupPreAggregateType;
    _indexingConfig = indexingConfig;
  }

  public List<File> convert() throws Exception {
    // Fetch table name and schema from segment metadata
    List<SegmentMetadata> segmentMetadataList = new ArrayList<>();

    String tableName = null;
    Schema schema = null;
    for (File inputIndexDir : _inputIndexDirs) {
      SegmentMetadata segmentMetadata = new SegmentMetadataImpl(inputIndexDir);
      segmentMetadataList.add(segmentMetadata);
      if (tableName == null) {
        tableName = segmentMetadata.getTableName();
      } else if (!tableName.equals(segmentMetadata.getTableName())) {
        throw new InvalidConfigException("Table name has to be the same for all segments");
      }

      if (schema == null) {
        schema = segmentMetadata.getSchema();
      } else if (!schema.equals(segmentMetadata.getSchema())) {
        throw new InvalidConfigException("Schema has to be the same for all segments");
      }
    }

    // Compute segment name for merged/rolled up segment
    String segmentName = "merged";

    // Compute number of partition
    int numPartition = 1;

    List<File> convertedSegments;
    switch (_mergeType) {
      case CONCATENATE:
        SegmentConverter concatenateSegmentConverter = new SegmentConverter.Builder().setTableName(tableName)
            .setSegmentName(segmentName)
            .setInputIndexDirs(_inputIndexDirs)
            .setWorkingDir(_workingDir)
            .setRecordTransformer((row) -> row)
            .setIndexingConfig(_indexingConfig)
            .build();

        convertedSegments = concatenateSegmentConverter.convertSegment();
        break;
      case ROLLUP:
        // Get the list of dimensions from schema
        List<String> groupByColumns = new ArrayList<>();
        for (DimensionFieldSpec dimensionFieldSpec : schema.getDimensionFieldSpecs()) {
          groupByColumns.add(dimensionFieldSpec.getName());
        }
        String timeColumn = schema.getTimeColumnName();
        groupByColumns.add(timeColumn);

        // Initialize roll-up record transformer
        RecordTransformer rollupRecordTransformer;

        if (_rollupSetting == null) {
          rollupRecordTransformer = (row) -> row;
        } else {
          rollupRecordTransformer = new RollupRecordTransformer(schema.getTimeFieldSpec(), _rollupSetting);
        }

        // Initialize roll-up record aggregator
        RecordAggregator rollupRecordAggregator = new RollupRecordAggregator(schema, _rolllupPreAggregateType);

        // Create segment converter
        SegmentConverter rollupSegmentConverter = new SegmentConverter.Builder().setTableName(tableName)
            .setSegmentName(segmentName)
            .setInputIndexDirs(_inputIndexDirs)
            .setWorkingDir(_workingDir)
            .setRecordTransformer(rollupRecordTransformer)
            .setRecordAggregator(rollupRecordAggregator)
            .setGroupByColumns(groupByColumns)
            .setIndexingConfig(_indexingConfig)
            .build();

        convertedSegments = rollupSegmentConverter.convertSegment();
        break;
      default:
        throw new RuntimeException("Invalid merge type : " + _mergeType);
    }

    return convertedSegments;
  }

  public static class Builder {
    // Required
    private List<File> _inputIndexDirs;
    private File _workingDir;
    private String _mergeType;

    // Optional
    private Map<String, String> _rollupPreAggregateType;
    private MultiLevelRollupSetting _rollupSetting;
    private IndexingConfig _indexingConfig;

    public Builder setInputIndexDirs(List<File> inputIndexDirs) {
      _inputIndexDirs = inputIndexDirs;
      return this;
    }

    public Builder setWorkingDir(File workingDir) {
      _workingDir = workingDir;
      return this;
    }

    public Builder setMergeType(String mergeType) {
      _mergeType = mergeType;
      return this;
    }

    public Builder setRollupPreAggregateType(Map<String, String> rollupPreAggregateType) {
      _rollupPreAggregateType = rollupPreAggregateType;
      return this;
    }

    public Builder setRollupSetting(MultiLevelRollupSetting rollupSetting) {
      _rollupSetting = rollupSetting;
      return this;
    }

    public Builder setIndexingConfig(IndexingConfig indexingConfig) {
      _indexingConfig = indexingConfig;
      return this;
    }

    public RollupSegmentConverter build() {
      return new RollupSegmentConverter(_inputIndexDirs, _workingDir, _mergeType, _rollupPreAggregateType,
          _rollupSetting, _indexingConfig);
    }
  }
}
