/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.linkedin.pinot.common.request;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
/**
 * AUTO GENERATED: DO NOT EDIT
 *  Query type
 * 
 */
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2017-8-24")
public class QueryType implements org.apache.thrift.TBase<QueryType, QueryType._Fields>, java.io.Serializable, Cloneable, Comparable<QueryType> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("QueryType");

  private static final org.apache.thrift.protocol.TField HAS_SELECTION_FIELD_DESC = new org.apache.thrift.protocol.TField("hasSelection", org.apache.thrift.protocol.TType.BOOL, (short)1);
  private static final org.apache.thrift.protocol.TField HAS_FILTER_FIELD_DESC = new org.apache.thrift.protocol.TField("hasFilter", org.apache.thrift.protocol.TType.BOOL, (short)2);
  private static final org.apache.thrift.protocol.TField HAS_AGGREGATION_FIELD_DESC = new org.apache.thrift.protocol.TField("hasAggregation", org.apache.thrift.protocol.TType.BOOL, (short)3);
  private static final org.apache.thrift.protocol.TField HAS_GROUP_BY_FIELD_DESC = new org.apache.thrift.protocol.TField("hasGroup_by", org.apache.thrift.protocol.TType.BOOL, (short)4);
  private static final org.apache.thrift.protocol.TField HAS_HAVING_FIELD_DESC = new org.apache.thrift.protocol.TField("hasHaving", org.apache.thrift.protocol.TType.BOOL, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new QueryTypeStandardSchemeFactory());
    schemes.put(TupleScheme.class, new QueryTypeTupleSchemeFactory());
  }

  private boolean hasSelection; // optional
  private boolean hasFilter; // optional
  private boolean hasAggregation; // optional
  private boolean hasGroup_by; // optional
  private boolean hasHaving; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    HAS_SELECTION((short)1, "hasSelection"),
    HAS_FILTER((short)2, "hasFilter"),
    HAS_AGGREGATION((short)3, "hasAggregation"),
    HAS_GROUP_BY((short)4, "hasGroup_by"),
    HAS_HAVING((short)5, "hasHaving");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // HAS_SELECTION
          return HAS_SELECTION;
        case 2: // HAS_FILTER
          return HAS_FILTER;
        case 3: // HAS_AGGREGATION
          return HAS_AGGREGATION;
        case 4: // HAS_GROUP_BY
          return HAS_GROUP_BY;
        case 5: // HAS_HAVING
          return HAS_HAVING;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __HASSELECTION_ISSET_ID = 0;
  private static final int __HASFILTER_ISSET_ID = 1;
  private static final int __HASAGGREGATION_ISSET_ID = 2;
  private static final int __HASGROUP_BY_ISSET_ID = 3;
  private static final int __HASHAVING_ISSET_ID = 4;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.HAS_SELECTION,_Fields.HAS_FILTER,_Fields.HAS_AGGREGATION,_Fields.HAS_GROUP_BY,_Fields.HAS_HAVING};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.HAS_SELECTION, new org.apache.thrift.meta_data.FieldMetaData("hasSelection", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.HAS_FILTER, new org.apache.thrift.meta_data.FieldMetaData("hasFilter", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.HAS_AGGREGATION, new org.apache.thrift.meta_data.FieldMetaData("hasAggregation", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.HAS_GROUP_BY, new org.apache.thrift.meta_data.FieldMetaData("hasGroup_by", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.HAS_HAVING, new org.apache.thrift.meta_data.FieldMetaData("hasHaving", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(QueryType.class, metaDataMap);
  }

  public QueryType() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public QueryType(QueryType other) {
    __isset_bitfield = other.__isset_bitfield;
    this.hasSelection = other.hasSelection;
    this.hasFilter = other.hasFilter;
    this.hasAggregation = other.hasAggregation;
    this.hasGroup_by = other.hasGroup_by;
    this.hasHaving = other.hasHaving;
  }

  public QueryType deepCopy() {
    return new QueryType(this);
  }

  @Override
  public void clear() {
    setHasSelectionIsSet(false);
    this.hasSelection = false;
    setHasFilterIsSet(false);
    this.hasFilter = false;
    setHasAggregationIsSet(false);
    this.hasAggregation = false;
    setHasGroup_byIsSet(false);
    this.hasGroup_by = false;
    setHasHavingIsSet(false);
    this.hasHaving = false;
  }

  public boolean isHasSelection() {
    return this.hasSelection;
  }

  public void setHasSelection(boolean hasSelection) {
    this.hasSelection = hasSelection;
    setHasSelectionIsSet(true);
  }

  public void unsetHasSelection() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __HASSELECTION_ISSET_ID);
  }

  /** Returns true if field hasSelection is set (has been assigned a value) and false otherwise */
  public boolean isSetHasSelection() {
    return EncodingUtils.testBit(__isset_bitfield, __HASSELECTION_ISSET_ID);
  }

  public void setHasSelectionIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __HASSELECTION_ISSET_ID, value);
  }

  public boolean isHasFilter() {
    return this.hasFilter;
  }

  public void setHasFilter(boolean hasFilter) {
    this.hasFilter = hasFilter;
    setHasFilterIsSet(true);
  }

  public void unsetHasFilter() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __HASFILTER_ISSET_ID);
  }

  /** Returns true if field hasFilter is set (has been assigned a value) and false otherwise */
  public boolean isSetHasFilter() {
    return EncodingUtils.testBit(__isset_bitfield, __HASFILTER_ISSET_ID);
  }

  public void setHasFilterIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __HASFILTER_ISSET_ID, value);
  }

  public boolean isHasAggregation() {
    return this.hasAggregation;
  }

  public void setHasAggregation(boolean hasAggregation) {
    this.hasAggregation = hasAggregation;
    setHasAggregationIsSet(true);
  }

  public void unsetHasAggregation() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __HASAGGREGATION_ISSET_ID);
  }

  /** Returns true if field hasAggregation is set (has been assigned a value) and false otherwise */
  public boolean isSetHasAggregation() {
    return EncodingUtils.testBit(__isset_bitfield, __HASAGGREGATION_ISSET_ID);
  }

  public void setHasAggregationIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __HASAGGREGATION_ISSET_ID, value);
  }

  public boolean isHasGroup_by() {
    return this.hasGroup_by;
  }

  public void setHasGroup_by(boolean hasGroup_by) {
    this.hasGroup_by = hasGroup_by;
    setHasGroup_byIsSet(true);
  }

  public void unsetHasGroup_by() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __HASGROUP_BY_ISSET_ID);
  }

  /** Returns true if field hasGroup_by is set (has been assigned a value) and false otherwise */
  public boolean isSetHasGroup_by() {
    return EncodingUtils.testBit(__isset_bitfield, __HASGROUP_BY_ISSET_ID);
  }

  public void setHasGroup_byIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __HASGROUP_BY_ISSET_ID, value);
  }

  public boolean isHasHaving() {
    return this.hasHaving;
  }

  public void setHasHaving(boolean hasHaving) {
    this.hasHaving = hasHaving;
    setHasHavingIsSet(true);
  }

  public void unsetHasHaving() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __HASHAVING_ISSET_ID);
  }

  /** Returns true if field hasHaving is set (has been assigned a value) and false otherwise */
  public boolean isSetHasHaving() {
    return EncodingUtils.testBit(__isset_bitfield, __HASHAVING_ISSET_ID);
  }

  public void setHasHavingIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __HASHAVING_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case HAS_SELECTION:
      if (value == null) {
        unsetHasSelection();
      } else {
        setHasSelection((Boolean)value);
      }
      break;

    case HAS_FILTER:
      if (value == null) {
        unsetHasFilter();
      } else {
        setHasFilter((Boolean)value);
      }
      break;

    case HAS_AGGREGATION:
      if (value == null) {
        unsetHasAggregation();
      } else {
        setHasAggregation((Boolean)value);
      }
      break;

    case HAS_GROUP_BY:
      if (value == null) {
        unsetHasGroup_by();
      } else {
        setHasGroup_by((Boolean)value);
      }
      break;

    case HAS_HAVING:
      if (value == null) {
        unsetHasHaving();
      } else {
        setHasHaving((Boolean)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case HAS_SELECTION:
      return Boolean.valueOf(isHasSelection());

    case HAS_FILTER:
      return Boolean.valueOf(isHasFilter());

    case HAS_AGGREGATION:
      return Boolean.valueOf(isHasAggregation());

    case HAS_GROUP_BY:
      return Boolean.valueOf(isHasGroup_by());

    case HAS_HAVING:
      return Boolean.valueOf(isHasHaving());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case HAS_SELECTION:
      return isSetHasSelection();
    case HAS_FILTER:
      return isSetHasFilter();
    case HAS_AGGREGATION:
      return isSetHasAggregation();
    case HAS_GROUP_BY:
      return isSetHasGroup_by();
    case HAS_HAVING:
      return isSetHasHaving();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof QueryType)
      return this.equals((QueryType)that);
    return false;
  }

  public boolean equals(QueryType that) {
    if (that == null)
      return false;

    boolean this_present_hasSelection = true && this.isSetHasSelection();
    boolean that_present_hasSelection = true && that.isSetHasSelection();
    if (this_present_hasSelection || that_present_hasSelection) {
      if (!(this_present_hasSelection && that_present_hasSelection))
        return false;
      if (this.hasSelection != that.hasSelection)
        return false;
    }

    boolean this_present_hasFilter = true && this.isSetHasFilter();
    boolean that_present_hasFilter = true && that.isSetHasFilter();
    if (this_present_hasFilter || that_present_hasFilter) {
      if (!(this_present_hasFilter && that_present_hasFilter))
        return false;
      if (this.hasFilter != that.hasFilter)
        return false;
    }

    boolean this_present_hasAggregation = true && this.isSetHasAggregation();
    boolean that_present_hasAggregation = true && that.isSetHasAggregation();
    if (this_present_hasAggregation || that_present_hasAggregation) {
      if (!(this_present_hasAggregation && that_present_hasAggregation))
        return false;
      if (this.hasAggregation != that.hasAggregation)
        return false;
    }

    boolean this_present_hasGroup_by = true && this.isSetHasGroup_by();
    boolean that_present_hasGroup_by = true && that.isSetHasGroup_by();
    if (this_present_hasGroup_by || that_present_hasGroup_by) {
      if (!(this_present_hasGroup_by && that_present_hasGroup_by))
        return false;
      if (this.hasGroup_by != that.hasGroup_by)
        return false;
    }

    boolean this_present_hasHaving = true && this.isSetHasHaving();
    boolean that_present_hasHaving = true && that.isSetHasHaving();
    if (this_present_hasHaving || that_present_hasHaving) {
      if (!(this_present_hasHaving && that_present_hasHaving))
        return false;
      if (this.hasHaving != that.hasHaving)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_hasSelection = true && (isSetHasSelection());
    list.add(present_hasSelection);
    if (present_hasSelection)
      list.add(hasSelection);

    boolean present_hasFilter = true && (isSetHasFilter());
    list.add(present_hasFilter);
    if (present_hasFilter)
      list.add(hasFilter);

    boolean present_hasAggregation = true && (isSetHasAggregation());
    list.add(present_hasAggregation);
    if (present_hasAggregation)
      list.add(hasAggregation);

    boolean present_hasGroup_by = true && (isSetHasGroup_by());
    list.add(present_hasGroup_by);
    if (present_hasGroup_by)
      list.add(hasGroup_by);

    boolean present_hasHaving = true && (isSetHasHaving());
    list.add(present_hasHaving);
    if (present_hasHaving)
      list.add(hasHaving);

    return list.hashCode();
  }

  @Override
  public int compareTo(QueryType other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetHasSelection()).compareTo(other.isSetHasSelection());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHasSelection()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.hasSelection, other.hasSelection);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetHasFilter()).compareTo(other.isSetHasFilter());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHasFilter()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.hasFilter, other.hasFilter);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetHasAggregation()).compareTo(other.isSetHasAggregation());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHasAggregation()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.hasAggregation, other.hasAggregation);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetHasGroup_by()).compareTo(other.isSetHasGroup_by());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHasGroup_by()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.hasGroup_by, other.hasGroup_by);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetHasHaving()).compareTo(other.isSetHasHaving());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHasHaving()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.hasHaving, other.hasHaving);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("QueryType(");
    boolean first = true;

    if (isSetHasSelection()) {
      sb.append("hasSelection:");
      sb.append(this.hasSelection);
      first = false;
    }
    if (isSetHasFilter()) {
      if (!first) sb.append(", ");
      sb.append("hasFilter:");
      sb.append(this.hasFilter);
      first = false;
    }
    if (isSetHasAggregation()) {
      if (!first) sb.append(", ");
      sb.append("hasAggregation:");
      sb.append(this.hasAggregation);
      first = false;
    }
    if (isSetHasGroup_by()) {
      if (!first) sb.append(", ");
      sb.append("hasGroup_by:");
      sb.append(this.hasGroup_by);
      first = false;
    }
    if (isSetHasHaving()) {
      if (!first) sb.append(", ");
      sb.append("hasHaving:");
      sb.append(this.hasHaving);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class QueryTypeStandardSchemeFactory implements SchemeFactory {
    public QueryTypeStandardScheme getScheme() {
      return new QueryTypeStandardScheme();
    }
  }

  private static class QueryTypeStandardScheme extends StandardScheme<QueryType> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, QueryType struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // HAS_SELECTION
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.hasSelection = iprot.readBool();
              struct.setHasSelectionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // HAS_FILTER
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.hasFilter = iprot.readBool();
              struct.setHasFilterIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // HAS_AGGREGATION
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.hasAggregation = iprot.readBool();
              struct.setHasAggregationIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // HAS_GROUP_BY
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.hasGroup_by = iprot.readBool();
              struct.setHasGroup_byIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // HAS_HAVING
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.hasHaving = iprot.readBool();
              struct.setHasHavingIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, QueryType struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetHasSelection()) {
        oprot.writeFieldBegin(HAS_SELECTION_FIELD_DESC);
        oprot.writeBool(struct.hasSelection);
        oprot.writeFieldEnd();
      }
      if (struct.isSetHasFilter()) {
        oprot.writeFieldBegin(HAS_FILTER_FIELD_DESC);
        oprot.writeBool(struct.hasFilter);
        oprot.writeFieldEnd();
      }
      if (struct.isSetHasAggregation()) {
        oprot.writeFieldBegin(HAS_AGGREGATION_FIELD_DESC);
        oprot.writeBool(struct.hasAggregation);
        oprot.writeFieldEnd();
      }
      if (struct.isSetHasGroup_by()) {
        oprot.writeFieldBegin(HAS_GROUP_BY_FIELD_DESC);
        oprot.writeBool(struct.hasGroup_by);
        oprot.writeFieldEnd();
      }
      if (struct.isSetHasHaving()) {
        oprot.writeFieldBegin(HAS_HAVING_FIELD_DESC);
        oprot.writeBool(struct.hasHaving);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class QueryTypeTupleSchemeFactory implements SchemeFactory {
    public QueryTypeTupleScheme getScheme() {
      return new QueryTypeTupleScheme();
    }
  }

  private static class QueryTypeTupleScheme extends TupleScheme<QueryType> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, QueryType struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetHasSelection()) {
        optionals.set(0);
      }
      if (struct.isSetHasFilter()) {
        optionals.set(1);
      }
      if (struct.isSetHasAggregation()) {
        optionals.set(2);
      }
      if (struct.isSetHasGroup_by()) {
        optionals.set(3);
      }
      if (struct.isSetHasHaving()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetHasSelection()) {
        oprot.writeBool(struct.hasSelection);
      }
      if (struct.isSetHasFilter()) {
        oprot.writeBool(struct.hasFilter);
      }
      if (struct.isSetHasAggregation()) {
        oprot.writeBool(struct.hasAggregation);
      }
      if (struct.isSetHasGroup_by()) {
        oprot.writeBool(struct.hasGroup_by);
      }
      if (struct.isSetHasHaving()) {
        oprot.writeBool(struct.hasHaving);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, QueryType struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.hasSelection = iprot.readBool();
        struct.setHasSelectionIsSet(true);
      }
      if (incoming.get(1)) {
        struct.hasFilter = iprot.readBool();
        struct.setHasFilterIsSet(true);
      }
      if (incoming.get(2)) {
        struct.hasAggregation = iprot.readBool();
        struct.setHasAggregationIsSet(true);
      }
      if (incoming.get(3)) {
        struct.hasGroup_by = iprot.readBool();
        struct.setHasGroup_byIsSet(true);
      }
      if (incoming.get(4)) {
        struct.hasHaving = iprot.readBool();
        struct.setHasHavingIsSet(true);
      }
    }
  }

}

