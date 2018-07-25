package com.linkedin.pinot.core.minion;

import com.linkedin.pinot.common.config.IndexingConfig;
import com.linkedin.pinot.common.config.MultiLevelRollupSetting;
import com.linkedin.pinot.common.data.DimensionFieldSpec;
import com.linkedin.pinot.common.data.FieldSpec;
import com.linkedin.pinot.common.data.MetricFieldSpec;
import com.linkedin.pinot.common.data.Schema;
import com.linkedin.pinot.common.data.TimeFieldSpec;
import com.linkedin.pinot.core.data.GenericRow;
import com.linkedin.pinot.core.data.readers.GenericRowRecordReader;
import com.linkedin.pinot.core.data.readers.PinotSegmentRecordReader;
import com.linkedin.pinot.core.data.readers.RecordReader;
import com.linkedin.pinot.core.indexsegment.generator.SegmentGeneratorConfig;
import com.linkedin.pinot.core.segment.creator.impl.SegmentIndexCreationDriverImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class RollupSegmentConverterTest {

  private static final File WORKING_DIR = new File(FileUtils.getTempDirectory(), "SegmentConverterTest");
  private static final File ORIGINAL_SEGMENT_DIR = new File(WORKING_DIR, "originalSegment");

  private static final int NUM_ROWS = 10000;
  private static final int REPEAT_ROWS = 5;
  private static final int NUM_SEGMENTS = 10;
  private static final String TABLE_NAME = "testTable";
  private static final String INPUT_SEGMENT_NAME_PREFIX = "testSegment_";
  private static final String D1 = "d1";
  private static final String D2 = "d2";
  private static final String M1 = "m1";
  private static final String M2 = "m2";
  private static final String T = "t";

  private List<File> _segmentIndexDirList;
  private final long _referenceTimestamp = System.currentTimeMillis();

  @BeforeClass
  public void setUp() throws Exception {
    FileUtils.deleteDirectory(WORKING_DIR);
    _segmentIndexDirList = new ArrayList<>(NUM_SEGMENTS);

    Schema schema = new Schema();
    schema.addField(new DimensionFieldSpec(D1, FieldSpec.DataType.INT, true));
    schema.addField(new DimensionFieldSpec(D2, FieldSpec.DataType.STRING, true));
    schema.addField(new MetricFieldSpec(M1, FieldSpec.DataType.LONG));
    schema.addField(new MetricFieldSpec(M2, FieldSpec.DataType.DOUBLE));
    schema.addField(new TimeFieldSpec(T, FieldSpec.DataType.LONG, TimeUnit.MILLISECONDS));

    List<GenericRow> rows = new ArrayList<>(NUM_ROWS);
    long timestamp = _referenceTimestamp;
    for (int i = 0; i < NUM_ROWS; i++) {
      int dimensionValue = i % (NUM_ROWS / REPEAT_ROWS);
      GenericRow row = new GenericRow();
      row.putField(D1, dimensionValue);
      row.putField(D2, Integer.toString(dimensionValue));
      row.putField(M1, (long) dimensionValue);
      row.putField(M2, (double) dimensionValue);
      row.putField(T, timestamp++);
      rows.add(row);
    }

    for (int i = 0; i < NUM_SEGMENTS; i++) {
      String segmentName = INPUT_SEGMENT_NAME_PREFIX + i;
      RecordReader recordReader = new GenericRowRecordReader(rows, schema);
      SegmentGeneratorConfig config = new SegmentGeneratorConfig(schema);
      config.setOutDir(ORIGINAL_SEGMENT_DIR.getPath());
      config.setTableName(TABLE_NAME);
      config.setSegmentName(segmentName);
      SegmentIndexCreationDriverImpl driver = new SegmentIndexCreationDriverImpl();
      driver.init(config, recordReader);
      driver.build();
      _segmentIndexDirList.add(new File(ORIGINAL_SEGMENT_DIR, segmentName));
    }
  }

  @Test
  public void testSegmentConcatenate() throws Exception {
    MultiLevelRollupSetting rollupSetting = new MultiLevelRollupSetting();
    rollupSetting.setTimeInputFormat("1:MILLIS:EPOCH");
    rollupSetting.setTimeOutputFormat("1:DAYS:EPOCH");
    rollupSetting.setTimeOutputGranularity("1:DAYS");

    RollupSegmentConverter rollupSegmentConverter = new RollupSegmentConverter.Builder()
        .setInputIndexDirs(_segmentIndexDirList)
        .setWorkingDir(WORKING_DIR)
        .setMergeType("CONCATENATE")
        .build();

    List<File> result = rollupSegmentConverter.convert();

    Assert.assertEquals(result.size(), 1);
    List<GenericRow> outputRows = new ArrayList<>();
    try (PinotSegmentRecordReader pinotSegmentRecordReader = new PinotSegmentRecordReader(result.get(0))) {
      while (pinotSegmentRecordReader.hasNext()) {
        outputRows.add(pinotSegmentRecordReader.next());
      }
    }
    // Check that the segment is correctly rolled up on the time column
    Assert.assertEquals(outputRows.size(), NUM_ROWS * NUM_SEGMENTS,
        "Number of rows returned by segment converter is incorrect");

    // Check the value
    for (int i = 0; i < NUM_SEGMENTS; i++) {
      int rowCount = 0;
      for (int j = 0; j < NUM_ROWS; j++) {
        int expectedValue = rowCount % (NUM_ROWS / REPEAT_ROWS);
        Assert.assertEquals(outputRows.get(rowCount).getValue(D1), expectedValue);
        Assert.assertEquals(outputRows.get(rowCount).getValue(D2), Integer.toString(expectedValue));
        Assert.assertEquals(outputRows.get(rowCount).getValue(M1), (long) expectedValue);
        Assert.assertEquals(outputRows.get(rowCount).getValue(M2), (double) expectedValue);
        rowCount++;
      }
    }
  }

  @Test
  public void testSegmentSimpleRollup() throws Exception {
    Map<String, String> preAggregateType = new HashMap<>();
    preAggregateType.put(M1, "SUM");

    RollupSegmentConverter rollupSegmentConverter = new RollupSegmentConverter.Builder()
        .setInputIndexDirs(_segmentIndexDirList)
        .setWorkingDir(WORKING_DIR)
        .setMergeType("ROLLUP")
        .setRollupPreAggregateType(preAggregateType)
        .build();

    List<File> result = rollupSegmentConverter.convert();
    Assert.assertEquals(result.size(), 1);
    List<GenericRow> outputRows = new ArrayList<>();
    try (PinotSegmentRecordReader pinotSegmentRecordReader = new PinotSegmentRecordReader(result.get(0))) {
      while (pinotSegmentRecordReader.hasNext()) {
        outputRows.add(pinotSegmentRecordReader.next());
      }
    }

    // Check that the segment is correctly rolled up on the time column
    Assert.assertEquals(outputRows.size(), NUM_ROWS, "Number of rows returned by segment converter is incorrect");

    // Check the value
    for (int i = 0; i < outputRows.size(); i++) {
      int expectedValue = i / REPEAT_ROWS;
      GenericRow row = outputRows.get(i);
      Assert.assertEquals(row.getValue(D1), expectedValue);
      Assert.assertEquals(row.getValue(D2), Long.toString(expectedValue));
      Assert.assertEquals(row.getValue(M1), (long) expectedValue * NUM_SEGMENTS);
      Assert.assertEquals(row.getValue(M2), (double) expectedValue * NUM_SEGMENTS);
    }
  }

  @Test
  public void testSegmentRollupWithTimeGranularityChange() throws Exception {
    Map<String, String> preAggregateType = new HashMap<>();
    preAggregateType.put(M1, "SUM");

    MultiLevelRollupSetting rollupSetting = new MultiLevelRollupSetting();
    rollupSetting.setTimeInputFormat("1:MILLISECONDS:EPOCH");
    rollupSetting.setTimeOutputFormat("1:DAYS:EPOCH");
    rollupSetting.setTimeOutputGranularity("1:DAYS");

    RollupSegmentConverter rollupSegmentConverter = new RollupSegmentConverter.Builder()
        .setInputIndexDirs(_segmentIndexDirList)
        .setWorkingDir(WORKING_DIR)
        .setMergeType("ROLLUP")
        .setRollupPreAggregateType(preAggregateType)
        .setRollupSetting(rollupSetting)
        .build();

    List<File> result = rollupSegmentConverter.convert();
    Assert.assertEquals(result.size(), 1);
    List<GenericRow> outputRows = new ArrayList<>();
    try (PinotSegmentRecordReader pinotSegmentRecordReader = new PinotSegmentRecordReader(result.get(0))) {
      while (pinotSegmentRecordReader.hasNext()) {
        outputRows.add(pinotSegmentRecordReader.next());
      }
    }

    // Check that the segment is correctly rolled up on the time column
    Assert.assertEquals(outputRows.size(), NUM_ROWS / REPEAT_ROWS,
        "Number of rows returned by segment converter is incorrect");

    // Check the value
    int expectedValue = 0;
    for (GenericRow row : outputRows) {
      Assert.assertEquals(row.getValue(D1), expectedValue);
      Assert.assertEquals(row.getValue(D2), Long.toString(expectedValue));
      Assert.assertEquals(row.getValue(M1), (long) expectedValue * NUM_SEGMENTS * REPEAT_ROWS);
      Assert.assertEquals(row.getValue(M2), (double) expectedValue * NUM_SEGMENTS * REPEAT_ROWS);
      expectedValue++;
    }
  }

  @AfterClass
  public void tearDown() throws Exception {
    FileUtils.deleteDirectory(WORKING_DIR);
  }
}
