/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package irc;

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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-9-20")
public class messageSend implements org.apache.thrift.TBase<messageSend, messageSend._Fields>, java.io.Serializable, Cloneable, Comparable<messageSend> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("messageSend");

  private static final org.apache.thrift.protocol.TField USRID_FIELD_DESC = new org.apache.thrift.protocol.TField("usrid", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField MESSAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("message", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField CHNAME_FIELD_DESC = new org.apache.thrift.protocol.TField("chname", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("time", org.apache.thrift.protocol.TType.I64, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new messageSendStandardSchemeFactory());
    schemes.put(TupleScheme.class, new messageSendTupleSchemeFactory());
  }

  public int usrid; // required
  public String message; // required
  public String chname; // required
  public long time; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    USRID((short)1, "usrid"),
    MESSAGE((short)2, "message"),
    CHNAME((short)3, "chname"),
    TIME((short)4, "time");

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
        case 1: // USRID
          return USRID;
        case 2: // MESSAGE
          return MESSAGE;
        case 3: // CHNAME
          return CHNAME;
        case 4: // TIME
          return TIME;
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
  private static final int __USRID_ISSET_ID = 0;
  private static final int __TIME_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.USRID, new org.apache.thrift.meta_data.FieldMetaData("usrid", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32        , "int")));
    tmpMap.put(_Fields.MESSAGE, new org.apache.thrift.meta_data.FieldMetaData("message", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , "String")));
    tmpMap.put(_Fields.CHNAME, new org.apache.thrift.meta_data.FieldMetaData("chname", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , "String")));
    tmpMap.put(_Fields.TIME, new org.apache.thrift.meta_data.FieldMetaData("time", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64        , "long")));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(messageSend.class, metaDataMap);
  }

  public messageSend() {
  }

  public messageSend(
    int usrid,
    String message,
    String chname,
    long time)
  {
    this();
    this.usrid = usrid;
    setUsridIsSet(true);
    this.message = message;
    this.chname = chname;
    this.time = time;
    setTimeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public messageSend(messageSend other) {
    __isset_bitfield = other.__isset_bitfield;
    this.usrid = other.usrid;
    if (other.isSetMessage()) {
      this.message = other.message;
    }
    if (other.isSetChname()) {
      this.chname = other.chname;
    }
    this.time = other.time;
  }

  public messageSend deepCopy() {
    return new messageSend(this);
  }

  @Override
  public void clear() {
    setUsridIsSet(false);
    this.usrid = 0;
    this.message = null;
    this.chname = null;
    setTimeIsSet(false);
    this.time = 0;
  }

  public int getUsrid() {
    return this.usrid;
  }

  public messageSend setUsrid(int usrid) {
    this.usrid = usrid;
    setUsridIsSet(true);
    return this;
  }

  public void unsetUsrid() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __USRID_ISSET_ID);
  }

  /** Returns true if field usrid is set (has been assigned a value) and false otherwise */
  public boolean isSetUsrid() {
    return EncodingUtils.testBit(__isset_bitfield, __USRID_ISSET_ID);
  }

  public void setUsridIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __USRID_ISSET_ID, value);
  }

  public String getMessage() {
    return this.message;
  }

  public messageSend setMessage(String message) {
    this.message = message;
    return this;
  }

  public void unsetMessage() {
    this.message = null;
  }

  /** Returns true if field message is set (has been assigned a value) and false otherwise */
  public boolean isSetMessage() {
    return this.message != null;
  }

  public void setMessageIsSet(boolean value) {
    if (!value) {
      this.message = null;
    }
  }

  public String getChname() {
    return this.chname;
  }

  public messageSend setChname(String chname) {
    this.chname = chname;
    return this;
  }

  public void unsetChname() {
    this.chname = null;
  }

  /** Returns true if field chname is set (has been assigned a value) and false otherwise */
  public boolean isSetChname() {
    return this.chname != null;
  }

  public void setChnameIsSet(boolean value) {
    if (!value) {
      this.chname = null;
    }
  }

  public long getTime() {
    return this.time;
  }

  public messageSend setTime(long time) {
    this.time = time;
    setTimeIsSet(true);
    return this;
  }

  public void unsetTime() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TIME_ISSET_ID);
  }

  /** Returns true if field time is set (has been assigned a value) and false otherwise */
  public boolean isSetTime() {
    return EncodingUtils.testBit(__isset_bitfield, __TIME_ISSET_ID);
  }

  public void setTimeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TIME_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case USRID:
      if (value == null) {
        unsetUsrid();
      } else {
        setUsrid((Integer)value);
      }
      break;

    case MESSAGE:
      if (value == null) {
        unsetMessage();
      } else {
        setMessage((String)value);
      }
      break;

    case CHNAME:
      if (value == null) {
        unsetChname();
      } else {
        setChname((String)value);
      }
      break;

    case TIME:
      if (value == null) {
        unsetTime();
      } else {
        setTime((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case USRID:
      return Integer.valueOf(getUsrid());

    case MESSAGE:
      return getMessage();

    case CHNAME:
      return getChname();

    case TIME:
      return Long.valueOf(getTime());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case USRID:
      return isSetUsrid();
    case MESSAGE:
      return isSetMessage();
    case CHNAME:
      return isSetChname();
    case TIME:
      return isSetTime();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof messageSend)
      return this.equals((messageSend)that);
    return false;
  }

  public boolean equals(messageSend that) {
    if (that == null)
      return false;

    boolean this_present_usrid = true;
    boolean that_present_usrid = true;
    if (this_present_usrid || that_present_usrid) {
      if (!(this_present_usrid && that_present_usrid))
        return false;
      if (this.usrid != that.usrid)
        return false;
    }

    boolean this_present_message = true && this.isSetMessage();
    boolean that_present_message = true && that.isSetMessage();
    if (this_present_message || that_present_message) {
      if (!(this_present_message && that_present_message))
        return false;
      if (!this.message.equals(that.message))
        return false;
    }

    boolean this_present_chname = true && this.isSetChname();
    boolean that_present_chname = true && that.isSetChname();
    if (this_present_chname || that_present_chname) {
      if (!(this_present_chname && that_present_chname))
        return false;
      if (!this.chname.equals(that.chname))
        return false;
    }

    boolean this_present_time = true;
    boolean that_present_time = true;
    if (this_present_time || that_present_time) {
      if (!(this_present_time && that_present_time))
        return false;
      if (this.time != that.time)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_usrid = true;
    list.add(present_usrid);
    if (present_usrid)
      list.add(usrid);

    boolean present_message = true && (isSetMessage());
    list.add(present_message);
    if (present_message)
      list.add(message);

    boolean present_chname = true && (isSetChname());
    list.add(present_chname);
    if (present_chname)
      list.add(chname);

    boolean present_time = true;
    list.add(present_time);
    if (present_time)
      list.add(time);

    return list.hashCode();
  }

  @Override
  public int compareTo(messageSend other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetUsrid()).compareTo(other.isSetUsrid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUsrid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.usrid, other.usrid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMessage()).compareTo(other.isSetMessage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMessage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.message, other.message);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetChname()).compareTo(other.isSetChname());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetChname()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.chname, other.chname);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTime()).compareTo(other.isSetTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.time, other.time);
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
    StringBuilder sb = new StringBuilder("messageSend(");
    boolean first = true;

    sb.append("usrid:");
    sb.append(this.usrid);
    first = false;
    if (!first) sb.append(", ");
    sb.append("message:");
    if (this.message == null) {
      sb.append("null");
    } else {
      sb.append(this.message);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("chname:");
    if (this.chname == null) {
      sb.append("null");
    } else {
      sb.append(this.chname);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("time:");
    sb.append(this.time);
    first = false;
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

  private static class messageSendStandardSchemeFactory implements SchemeFactory {
    public messageSendStandardScheme getScheme() {
      return new messageSendStandardScheme();
    }
  }

  private static class messageSendStandardScheme extends StandardScheme<messageSend> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, messageSend struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // USRID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.usrid = iprot.readI32();
              struct.setUsridIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // MESSAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.message = iprot.readString();
              struct.setMessageIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CHNAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.chname = iprot.readString();
              struct.setChnameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.time = iprot.readI64();
              struct.setTimeIsSet(true);
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

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, messageSend struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(USRID_FIELD_DESC);
      oprot.writeI32(struct.usrid);
      oprot.writeFieldEnd();
      if (struct.message != null) {
        oprot.writeFieldBegin(MESSAGE_FIELD_DESC);
        oprot.writeString(struct.message);
        oprot.writeFieldEnd();
      }
      if (struct.chname != null) {
        oprot.writeFieldBegin(CHNAME_FIELD_DESC);
        oprot.writeString(struct.chname);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TIME_FIELD_DESC);
      oprot.writeI64(struct.time);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class messageSendTupleSchemeFactory implements SchemeFactory {
    public messageSendTupleScheme getScheme() {
      return new messageSendTupleScheme();
    }
  }

  private static class messageSendTupleScheme extends TupleScheme<messageSend> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, messageSend struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetUsrid()) {
        optionals.set(0);
      }
      if (struct.isSetMessage()) {
        optionals.set(1);
      }
      if (struct.isSetChname()) {
        optionals.set(2);
      }
      if (struct.isSetTime()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetUsrid()) {
        oprot.writeI32(struct.usrid);
      }
      if (struct.isSetMessage()) {
        oprot.writeString(struct.message);
      }
      if (struct.isSetChname()) {
        oprot.writeString(struct.chname);
      }
      if (struct.isSetTime()) {
        oprot.writeI64(struct.time);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, messageSend struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.usrid = iprot.readI32();
        struct.setUsridIsSet(true);
      }
      if (incoming.get(1)) {
        struct.message = iprot.readString();
        struct.setMessageIsSet(true);
      }
      if (incoming.get(2)) {
        struct.chname = iprot.readString();
        struct.setChnameIsSet(true);
      }
      if (incoming.get(3)) {
        struct.time = iprot.readI64();
        struct.setTimeIsSet(true);
      }
    }
  }

}

