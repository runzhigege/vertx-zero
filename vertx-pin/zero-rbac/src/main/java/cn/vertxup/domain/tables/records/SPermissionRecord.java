/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.domain.tables.records;


import cn.vertxup.domain.tables.SPermission;
import cn.vertxup.domain.tables.interfaces.ISPermission;

import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record12;
import org.jooq.Row12;
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
public class SPermissionRecord extends UpdatableRecordImpl<SPermissionRecord> implements Record12<String, String, String, String, String, Boolean, String, String, LocalDateTime, String, LocalDateTime, String>, ISPermission {

    private static final long serialVersionUID = -694510254;

    /**
     * Setter for <code>DB_ORIGIN_X.S_PERMISSION.KEY</code>. 「key」- 权限ID
     */
    @Override
    public SPermissionRecord setKey(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>DB_ORIGIN_X.S_PERMISSION.KEY</code>. 「key」- 权限ID
     */
    @Override
    public String getKey() {
        return (String) get(0);
    }

    /**
     * Setter for <code>DB_ORIGIN_X.S_PERMISSION.NAME</code>. 「name」- 权限名称
     */
    @Override
    public SPermissionRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>DB_ORIGIN_X.S_PERMISSION.NAME</code>. 「name」- 权限名称
     */
    @Override
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>DB_ORIGIN_X.S_PERMISSION.CODE</code>. 「code」- 权限系统码
     */
    @Override
    public SPermissionRecord setCode(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>DB_ORIGIN_X.S_PERMISSION.CODE</code>. 「code」- 权限系统码
     */
    @Override
    public String getCode() {
        return (String) get(2);
    }

    /**
     * Setter for <code>DB_ORIGIN_X.S_PERMISSION.SIGMA</code>. 「sigma」- 绑定的统一标识
     */
    @Override
    public SPermissionRecord setSigma(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>DB_ORIGIN_X.S_PERMISSION.SIGMA</code>. 「sigma」- 绑定的统一标识
     */
    @Override
    public String getSigma() {
        return (String) get(3);
    }

    /**
     * Setter for <code>DB_ORIGIN_X.S_PERMISSION.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public SPermissionRecord setLanguage(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>DB_ORIGIN_X.S_PERMISSION.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public String getLanguage() {
        return (String) get(4);
    }

    /**
     * Setter for <code>DB_ORIGIN_X.S_PERMISSION.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public SPermissionRecord setActive(Boolean value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>DB_ORIGIN_X.S_PERMISSION.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public Boolean getActive() {
        return (Boolean) get(5);
    }

    /**
     * Setter for <code>DB_ORIGIN_X.S_PERMISSION.COMMENT</code>. 「comment」- 权限说明
     */
    @Override
    public SPermissionRecord setComment(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>DB_ORIGIN_X.S_PERMISSION.COMMENT</code>. 「comment」- 权限说明
     */
    @Override
    public String getComment() {
        return (String) get(6);
    }

    /**
     * Setter for <code>DB_ORIGIN_X.S_PERMISSION.METADATA</code>. 「metadata」- 附加配置数据
     */
    @Override
    public SPermissionRecord setMetadata(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>DB_ORIGIN_X.S_PERMISSION.METADATA</code>. 「metadata」- 附加配置数据
     */
    @Override
    public String getMetadata() {
        return (String) get(7);
    }

    /**
     * Setter for <code>DB_ORIGIN_X.S_PERMISSION.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public SPermissionRecord setCreatedAt(LocalDateTime value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>DB_ORIGIN_X.S_PERMISSION.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(8);
    }

    /**
     * Setter for <code>DB_ORIGIN_X.S_PERMISSION.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public SPermissionRecord setCreatedBy(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>DB_ORIGIN_X.S_PERMISSION.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public String getCreatedBy() {
        return (String) get(9);
    }

    /**
     * Setter for <code>DB_ORIGIN_X.S_PERMISSION.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public SPermissionRecord setUpdatedAt(LocalDateTime value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>DB_ORIGIN_X.S_PERMISSION.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(10);
    }

    /**
     * Setter for <code>DB_ORIGIN_X.S_PERMISSION.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    @Override
    public SPermissionRecord setUpdatedBy(String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>DB_ORIGIN_X.S_PERMISSION.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    @Override
    public String getUpdatedBy() {
        return (String) get(11);
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
    // Record12 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row12<String, String, String, String, String, Boolean, String, String, LocalDateTime, String, LocalDateTime, String> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row12<String, String, String, String, String, Boolean, String, String, LocalDateTime, String, LocalDateTime, String> valuesRow() {
        return (Row12) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return SPermission.S_PERMISSION.KEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return SPermission.S_PERMISSION.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return SPermission.S_PERMISSION.CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return SPermission.S_PERMISSION.SIGMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return SPermission.S_PERMISSION.LANGUAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field6() {
        return SPermission.S_PERMISSION.ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return SPermission.S_PERMISSION.COMMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return SPermission.S_PERMISSION.METADATA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field9() {
        return SPermission.S_PERMISSION.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return SPermission.S_PERMISSION.CREATED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field11() {
        return SPermission.S_PERMISSION.UPDATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field12() {
        return SPermission.S_PERMISSION.UPDATED_BY;
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
        return getSigma();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component6() {
        return getActive();
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
        return getMetadata();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component9() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component10() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component11() {
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component12() {
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
        return getSigma();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value6() {
        return getActive();
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
        return getMetadata();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value9() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value11() {
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value12() {
        return getUpdatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SPermissionRecord value1(String value) {
        setKey(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SPermissionRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SPermissionRecord value3(String value) {
        setCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SPermissionRecord value4(String value) {
        setSigma(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SPermissionRecord value5(String value) {
        setLanguage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SPermissionRecord value6(Boolean value) {
        setActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SPermissionRecord value7(String value) {
        setComment(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SPermissionRecord value8(String value) {
        setMetadata(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SPermissionRecord value9(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SPermissionRecord value10(String value) {
        setCreatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SPermissionRecord value11(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SPermissionRecord value12(String value) {
        setUpdatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SPermissionRecord values(String value1, String value2, String value3, String value4, String value5, Boolean value6, String value7, String value8, LocalDateTime value9, String value10, LocalDateTime value11, String value12) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(ISPermission from) {
        setKey(from.getKey());
        setName(from.getName());
        setCode(from.getCode());
        setSigma(from.getSigma());
        setLanguage(from.getLanguage());
        setActive(from.getActive());
        setComment(from.getComment());
        setMetadata(from.getMetadata());
        setCreatedAt(from.getCreatedAt());
        setCreatedBy(from.getCreatedBy());
        setUpdatedAt(from.getUpdatedAt());
        setUpdatedBy(from.getUpdatedBy());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends ISPermission> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SPermissionRecord
     */
    public SPermissionRecord() {
        super(SPermission.S_PERMISSION);
    }

    /**
     * Create a detached, initialised SPermissionRecord
     */
    public SPermissionRecord(String key, String name, String code, String sigma, String language, Boolean active, String comment, String metadata, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
        super(SPermission.S_PERMISSION);

        set(0, key);
        set(1, name);
        set(2, code);
        set(3, sigma);
        set(4, language);
        set(5, active);
        set(6, comment);
        set(7, metadata);
        set(8, createdAt);
        set(9, createdBy);
        set(10, updatedAt);
        set(11, updatedBy);
    }
}
