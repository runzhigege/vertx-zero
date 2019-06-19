/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ambient.tables.records;


import cn.vertxup.ambient.tables.XNumber;
import cn.vertxup.ambient.tables.interfaces.IXNumber;

import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record20;
import org.jooq.Row20;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class XNumberRecord extends UpdatableRecordImpl<XNumberRecord> implements Record20<String, String, String, Long, String, String, String, String, String, Integer, Integer, Boolean, Boolean, String, String, String, LocalDateTime, String, LocalDateTime, String>, IXNumber {

    private static final long serialVersionUID = 254868041;

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.KEY</code>. 「key」- 主键
     */
    @Override
    public XNumberRecord setKey(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.KEY</code>. 「key」- 主键
     */
    @Override
    public String getKey() {
        return (String) get(0);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.CODE</code>. 「code」- 编码
     */
    @Override
    public XNumberRecord setCode(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.CODE</code>. 「code」- 编码
     */
    @Override
    public String getCode() {
        return (String) get(1);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.COMMENT</code>. 「comment」- 编号备注信息，用于描述编号数据,comment,S_COMMENT
     */
    @Override
    public XNumberRecord setComment(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.COMMENT</code>. 「comment」- 编号备注信息，用于描述编号数据,comment,S_COMMENT
     */
    @Override
    public String getComment() {
        return (String) get(2);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.CURRENT</code>. 「current」编号当前值，对应${seed}，每次变化时current提取后更新为：current + step或current - step,current,L_CURRENT
     */
    @Override
    public XNumberRecord setCurrent(Long value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.CURRENT</code>. 「current」编号当前值，对应${seed}，每次变化时current提取后更新为：current + step或current - step,current,L_CURRENT
     */
    @Override
    public Long getCurrent() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.FORMAT</code>. 「format」格式信息，用于处理最终格式：,format,S_FORMAT
     */
    @Override
    public XNumberRecord setFormat(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.FORMAT</code>. 「format」格式信息，用于处理最终格式：,format,S_FORMAT
     */
    @Override
    public String getFormat() {
        return (String) get(4);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.IDENTIFIER</code>. 「identifier」编号对应的identifier，用于查询当前identifier使用的序号信息,identifier,S_IDENTIFIER
     */
    @Override
    public XNumberRecord setIdentifier(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.IDENTIFIER</code>. 「identifier」编号对应的identifier，用于查询当前identifier使用的序号信息,identifier,S_IDENTIFIER
     */
    @Override
    public String getIdentifier() {
        return (String) get(5);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.PREFIX</code>. 「prefix」编号前缀,prefix,S_PREFIX
     */
    @Override
    public XNumberRecord setPrefix(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.PREFIX</code>. 「prefix」编号前缀,prefix,S_PREFIX
     */
    @Override
    public String getPrefix() {
        return (String) get(6);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.SUFFIX</code>. 「suffix」编号后缀,suffix,S_SUFFIX
     */
    @Override
    public XNumberRecord setSuffix(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.SUFFIX</code>. 「suffix」编号后缀,suffix,S_SUFFIX
     */
    @Override
    public String getSuffix() {
        return (String) get(7);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.TIME</code>. 「time」时间对应Pattern，对应${time}：YYYY-MM-DD HH:mm:ss用于描述时间格式生成序号时间部分,time,S_TIME
     */
    @Override
    public XNumberRecord setTime(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.TIME</code>. 「time」时间对应Pattern，对应${time}：YYYY-MM-DD HH:mm:ss用于描述时间格式生成序号时间部分,time,S_TIME
     */
    @Override
    public String getTime() {
        return (String) get(8);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.LENGTH</code>. 「length」编号长度，编号长度不包含prefix和suffix部分,length,I_LENGTH
     */
    @Override
    public XNumberRecord setLength(Integer value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.LENGTH</code>. 「length」编号长度，编号长度不包含prefix和suffix部分,length,I_LENGTH
     */
    @Override
    public Integer getLength() {
        return (Integer) get(9);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.STEP</code>. 「step」编号的步进系数，每次按照step进行变化,step,I_STEP
     */
    @Override
    public XNumberRecord setStep(Integer value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.STEP</code>. 「step」编号的步进系数，每次按照step进行变化,step,I_STEP
     */
    @Override
    public Integer getStep() {
        return (Integer) get(10);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.DECREMENT</code>. 「decrement」递增/递减？如果为true则递减，为false则是递增,decrement,IS_DECREMENT
     */
    @Override
    public XNumberRecord setDecrement(Boolean value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.DECREMENT</code>. 「decrement」递增/递减？如果为true则递减，为false则是递增,decrement,IS_DECREMENT
     */
    @Override
    public Boolean getDecrement() {
        return (Boolean) get(11);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public XNumberRecord setActive(Boolean value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public Boolean getActive() {
        return (Boolean) get(12);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.SIGMA</code>. 「sigma」- 统一标识
     */
    @Override
    public XNumberRecord setSigma(String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.SIGMA</code>. 「sigma」- 统一标识
     */
    @Override
    public String getSigma() {
        return (String) get(13);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.METADATA</code>. 「metadata」- 附加配置
     */
    @Override
    public XNumberRecord setMetadata(String value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.METADATA</code>. 「metadata」- 附加配置
     */
    @Override
    public String getMetadata() {
        return (String) get(14);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public XNumberRecord setLanguage(String value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public String getLanguage() {
        return (String) get(15);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public XNumberRecord setCreatedAt(LocalDateTime value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(16);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public XNumberRecord setCreatedBy(String value) {
        set(17, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public String getCreatedBy() {
        return (String) get(17);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public XNumberRecord setUpdatedAt(LocalDateTime value) {
        set(18, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(18);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_NUMBER.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    @Override
    public XNumberRecord setUpdatedBy(String value) {
        set(19, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_NUMBER.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    @Override
    public String getUpdatedBy() {
        return (String) get(19);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record20 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row20<String, String, String, Long, String, String, String, String, String, Integer, Integer, Boolean, Boolean, String, String, String, LocalDateTime, String, LocalDateTime, String> fieldsRow() {
        return (Row20) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row20<String, String, String, Long, String, String, String, String, String, Integer, Integer, Boolean, Boolean, String, String, String, LocalDateTime, String, LocalDateTime, String> valuesRow() {
        return (Row20) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return XNumber.X_NUMBER.KEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return XNumber.X_NUMBER.CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return XNumber.X_NUMBER.COMMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field4() {
        return XNumber.X_NUMBER.CURRENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return XNumber.X_NUMBER.FORMAT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return XNumber.X_NUMBER.IDENTIFIER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return XNumber.X_NUMBER.PREFIX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return XNumber.X_NUMBER.SUFFIX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return XNumber.X_NUMBER.TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field10() {
        return XNumber.X_NUMBER.LENGTH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field11() {
        return XNumber.X_NUMBER.STEP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field12() {
        return XNumber.X_NUMBER.DECREMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field13() {
        return XNumber.X_NUMBER.ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field14() {
        return XNumber.X_NUMBER.SIGMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field15() {
        return XNumber.X_NUMBER.METADATA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field16() {
        return XNumber.X_NUMBER.LANGUAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field17() {
        return XNumber.X_NUMBER.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field18() {
        return XNumber.X_NUMBER.CREATED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field19() {
        return XNumber.X_NUMBER.UPDATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field20() {
        return XNumber.X_NUMBER.UPDATED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getComment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component4() {
        return getCurrent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getFormat();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getIdentifier();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getPrefix();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getSuffix();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component9() {
        return getTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component10() {
        return getLength();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component11() {
        return getStep();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component12() {
        return getDecrement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component13() {
        return getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component14() {
        return getSigma();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component15() {
        return getMetadata();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component16() {
        return getLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component17() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component18() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component19() {
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component20() {
        return getUpdatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getComment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value4() {
        return getCurrent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getFormat();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getIdentifier();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getPrefix();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getSuffix();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value10() {
        return getLength();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value11() {
        return getStep();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value12() {
        return getDecrement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value13() {
        return getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value14() {
        return getSigma();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value15() {
        return getMetadata();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value16() {
        return getLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value17() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value18() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value19() {
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value20() {
        return getUpdatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value1(String value) {
        setKey(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value2(String value) {
        setCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value3(String value) {
        setComment(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value4(Long value) {
        setCurrent(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value5(String value) {
        setFormat(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value6(String value) {
        setIdentifier(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value7(String value) {
        setPrefix(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value8(String value) {
        setSuffix(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value9(String value) {
        setTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value10(Integer value) {
        setLength(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value11(Integer value) {
        setStep(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value12(Boolean value) {
        setDecrement(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value13(Boolean value) {
        setActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value14(String value) {
        setSigma(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value15(String value) {
        setMetadata(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value16(String value) {
        setLanguage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value17(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value18(String value) {
        setCreatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value19(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord value20(String value) {
        setUpdatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XNumberRecord values(String value1, String value2, String value3, Long value4, String value5, String value6, String value7, String value8, String value9, Integer value10, Integer value11, Boolean value12, Boolean value13, String value14, String value15, String value16, LocalDateTime value17, String value18, LocalDateTime value19, String value20) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        value18(value18);
        value19(value19);
        value20(value20);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IXNumber from) {
        setKey(from.getKey());
        setCode(from.getCode());
        setComment(from.getComment());
        setCurrent(from.getCurrent());
        setFormat(from.getFormat());
        setIdentifier(from.getIdentifier());
        setPrefix(from.getPrefix());
        setSuffix(from.getSuffix());
        setTime(from.getTime());
        setLength(from.getLength());
        setStep(from.getStep());
        setDecrement(from.getDecrement());
        setActive(from.getActive());
        setSigma(from.getSigma());
        setMetadata(from.getMetadata());
        setLanguage(from.getLanguage());
        setCreatedAt(from.getCreatedAt());
        setCreatedBy(from.getCreatedBy());
        setUpdatedAt(from.getUpdatedAt());
        setUpdatedBy(from.getUpdatedBy());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IXNumber> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached XNumberRecord
     */
    public XNumberRecord() {
        super(XNumber.X_NUMBER);
    }

    /**
     * Create a detached, initialised XNumberRecord
     */
    public XNumberRecord(String key, String code, String comment, Long current, String format, String identifier, String prefix, String suffix, String time, Integer length, Integer step, Boolean decrement, Boolean active, String sigma, String metadata, String language, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
        super(XNumber.X_NUMBER);

        set(0, key);
        set(1, code);
        set(2, comment);
        set(3, current);
        set(4, format);
        set(5, identifier);
        set(6, prefix);
        set(7, suffix);
        set(8, time);
        set(9, length);
        set(10, step);
        set(11, decrement);
        set(12, active);
        set(13, sigma);
        set(14, metadata);
        set(15, language);
        set(16, createdAt);
        set(17, createdBy);
        set(18, updatedAt);
        set(19, updatedBy);
    }
}
