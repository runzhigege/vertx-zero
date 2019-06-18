/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ambient.tables.records;


import cn.vertxup.ambient.tables.XTabular;
import cn.vertxup.ambient.tables.interfaces.IXTabular;

import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record16;
import org.jooq.Row16;
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
public class XTabularRecord extends UpdatableRecordImpl<XTabularRecord> implements Record16<String, String, String, String, String, Integer, String, String, Boolean, String, String, String, LocalDateTime, String, LocalDateTime, String>, IXTabular {

    private static final long serialVersionUID = 1567834867;

    /**
     * Setter for <code>DB_ETERNAL.X_TABULAR.KEY</code>. 「key」- 列表主键
     */
    @Override
    public XTabularRecord setKey(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_TABULAR.KEY</code>. 「key」- 列表主键
     */
    @Override
    public String getKey() {
        return (String) get(0);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_TABULAR.NAME</code>. 「name」- 列表名称
     */
    @Override
    public XTabularRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_TABULAR.NAME</code>. 「name」- 列表名称
     */
    @Override
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_TABULAR.CODE</code>. 「code」- 列表编号
     */
    @Override
    public XTabularRecord setCode(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_TABULAR.CODE</code>. 「code」- 列表编号
     */
    @Override
    public String getCode() {
        return (String) get(2);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_TABULAR.TYPE</code>. 「type」- 列表类型
     */
    @Override
    public XTabularRecord setType(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_TABULAR.TYPE</code>. 「type」- 列表类型
     */
    @Override
    public String getType() {
        return (String) get(3);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_TABULAR.ICON</code>. 「icon」- 列表图标
     */
    @Override
    public XTabularRecord setIcon(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_TABULAR.ICON</code>. 「icon」- 列表图标
     */
    @Override
    public String getIcon() {
        return (String) get(4);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_TABULAR.SORT</code>. 「sort」- 排序信息
     */
    @Override
    public XTabularRecord setSort(Integer value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_TABULAR.SORT</code>. 「sort」- 排序信息
     */
    @Override
    public Integer getSort() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_TABULAR.COMMENT</code>. 「comment」- 备注信息
     */
    @Override
    public XTabularRecord setComment(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_TABULAR.COMMENT</code>. 「comment」- 备注信息
     */
    @Override
    public String getComment() {
        return (String) get(6);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_TABULAR.APP_ID</code>. 「appId」- 关联的应用程序ID
     */
    @Override
    public XTabularRecord setAppId(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_TABULAR.APP_ID</code>. 「appId」- 关联的应用程序ID
     */
    @Override
    public String getAppId() {
        return (String) get(7);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_TABULAR.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public XTabularRecord setActive(Boolean value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_TABULAR.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public Boolean getActive() {
        return (Boolean) get(8);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_TABULAR.SIGMA</code>. 「sigma」- 统一标识
     */
    @Override
    public XTabularRecord setSigma(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_TABULAR.SIGMA</code>. 「sigma」- 统一标识
     */
    @Override
    public String getSigma() {
        return (String) get(9);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_TABULAR.METADATA</code>. 「metadata」- 附加配置
     */
    @Override
    public XTabularRecord setMetadata(String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_TABULAR.METADATA</code>. 「metadata」- 附加配置
     */
    @Override
    public String getMetadata() {
        return (String) get(10);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_TABULAR.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public XTabularRecord setLanguage(String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_TABULAR.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public String getLanguage() {
        return (String) get(11);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_TABULAR.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public XTabularRecord setCreatedAt(LocalDateTime value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_TABULAR.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(12);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_TABULAR.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public XTabularRecord setCreatedBy(String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_TABULAR.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public String getCreatedBy() {
        return (String) get(13);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_TABULAR.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public XTabularRecord setUpdatedAt(LocalDateTime value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_TABULAR.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(14);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_TABULAR.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    @Override
    public XTabularRecord setUpdatedBy(String value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_TABULAR.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    @Override
    public String getUpdatedBy() {
        return (String) get(15);
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
    // Record16 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row16<String, String, String, String, String, Integer, String, String, Boolean, String, String, String, LocalDateTime, String, LocalDateTime, String> fieldsRow() {
        return (Row16) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row16<String, String, String, String, String, Integer, String, String, Boolean, String, String, String, LocalDateTime, String, LocalDateTime, String> valuesRow() {
        return (Row16) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return XTabular.X_TABULAR.KEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return XTabular.X_TABULAR.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return XTabular.X_TABULAR.CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return XTabular.X_TABULAR.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return XTabular.X_TABULAR.ICON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return XTabular.X_TABULAR.SORT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return XTabular.X_TABULAR.COMMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return XTabular.X_TABULAR.APP_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field9() {
        return XTabular.X_TABULAR.ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return XTabular.X_TABULAR.SIGMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return XTabular.X_TABULAR.METADATA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field12() {
        return XTabular.X_TABULAR.LANGUAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field13() {
        return XTabular.X_TABULAR.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field14() {
        return XTabular.X_TABULAR.CREATED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field15() {
        return XTabular.X_TABULAR.UPDATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field16() {
        return XTabular.X_TABULAR.UPDATED_BY;
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
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component6() {
        return getSort();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getComment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getAppId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component9() {
        return getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component10() {
        return getSigma();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component11() {
        return getMetadata();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component12() {
        return getLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component13() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component14() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component15() {
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component16() {
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
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getSort();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getComment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getAppId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value9() {
        return getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getSigma();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value11() {
        return getMetadata();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value12() {
        return getLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value13() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value14() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value15() {
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value16() {
        return getUpdatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XTabularRecord value1(String value) {
        setKey(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XTabularRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XTabularRecord value3(String value) {
        setCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XTabularRecord value4(String value) {
        setType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XTabularRecord value5(String value) {
        setIcon(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XTabularRecord value6(Integer value) {
        setSort(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XTabularRecord value7(String value) {
        setComment(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XTabularRecord value8(String value) {
        setAppId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XTabularRecord value9(Boolean value) {
        setActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XTabularRecord value10(String value) {
        setSigma(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XTabularRecord value11(String value) {
        setMetadata(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XTabularRecord value12(String value) {
        setLanguage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XTabularRecord value13(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XTabularRecord value14(String value) {
        setCreatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XTabularRecord value15(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XTabularRecord value16(String value) {
        setUpdatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XTabularRecord values(String value1, String value2, String value3, String value4, String value5, Integer value6, String value7, String value8, Boolean value9, String value10, String value11, String value12, LocalDateTime value13, String value14, LocalDateTime value15, String value16) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IXTabular from) {
        setKey(from.getKey());
        setName(from.getName());
        setCode(from.getCode());
        setType(from.getType());
        setIcon(from.getIcon());
        setSort(from.getSort());
        setComment(from.getComment());
        setAppId(from.getAppId());
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
    public <E extends IXTabular> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached XTabularRecord
     */
    public XTabularRecord() {
        super(XTabular.X_TABULAR);
    }

    /**
     * Create a detached, initialised XTabularRecord
     */
    public XTabularRecord(String key, String name, String code, String type, String icon, Integer sort, String comment, String appId, Boolean active, String sigma, String metadata, String language, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
        super(XTabular.X_TABULAR);

        set(0, key);
        set(1, name);
        set(2, code);
        set(3, type);
        set(4, icon);
        set(5, sort);
        set(6, comment);
        set(7, appId);
        set(8, active);
        set(9, sigma);
        set(10, metadata);
        set(11, language);
        set(12, createdAt);
        set(13, createdBy);
        set(14, updatedAt);
        set(15, updatedBy);
    }
}
