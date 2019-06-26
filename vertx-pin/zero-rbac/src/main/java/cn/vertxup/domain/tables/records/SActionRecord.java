/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.domain.tables.records;


import cn.vertxup.domain.tables.SAction;
import cn.vertxup.domain.tables.interfaces.ISAction;

import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record18;
import org.jooq.Row18;
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
public class SActionRecord extends UpdatableRecordImpl<SActionRecord> implements Record18<String, String, String, String, String, Integer, String, String, String, String, String, Boolean, String, String, LocalDateTime, String, LocalDateTime, String>, ISAction {

    private static final long serialVersionUID = -815171691;

    /**
     * Setter for <code>DB_ETERNAL.S_ACTION.KEY</code>. 「key」- 操作ID
     */
    @Override
    public SActionRecord setKey(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ACTION.KEY</code>. 「key」- 操作ID
     */
    @Override
    public String getKey() {
        return (String) get(0);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ACTION.NAME</code>. 「name」- 操作名称
     */
    @Override
    public SActionRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ACTION.NAME</code>. 「name」- 操作名称
     */
    @Override
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ACTION.CODE</code>. 「code」- 操作码
     */
    @Override
    public SActionRecord setCode(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ACTION.CODE</code>. 「code」- 操作码
     */
    @Override
    public String getCode() {
        return (String) get(2);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ACTION.RESOURCE_ID</code>. 「resourceId」- 操作关联资源ID
     */
    @Override
    public SActionRecord setResourceId(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ACTION.RESOURCE_ID</code>. 「resourceId」- 操作关联资源ID
     */
    @Override
    public String getResourceId() {
        return (String) get(3);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ACTION.PERMISSION_ID</code>. 「permissionId」- 操作所属权限
     */
    @Override
    public SActionRecord setPermissionId(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ACTION.PERMISSION_ID</code>. 「permissionId」- 操作所属权限
     */
    @Override
    public String getPermissionId() {
        return (String) get(4);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ACTION.LEVEL</code>. 「level」- 操作级别, ACL控制
     */
    @Override
    public SActionRecord setLevel(Integer value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ACTION.LEVEL</code>. 「level」- 操作级别, ACL控制
     */
    @Override
    public Integer getLevel() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ACTION.URI</code>. 「uri」- 资源地址
     */
    @Override
    public SActionRecord setUri(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ACTION.URI</code>. 「uri」- 资源地址
     */
    @Override
    public String getUri() {
        return (String) get(6);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ACTION.METHOD</code>. 「method」- 资源方法
     */
    @Override
    public SActionRecord setMethod(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ACTION.METHOD</code>. 「method」- 资源方法
     */
    @Override
    public String getMethod() {
        return (String) get(7);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ACTION.SIGMA</code>. 「sigma」- 统一标识
     */
    @Override
    public SActionRecord setSigma(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ACTION.SIGMA</code>. 「sigma」- 统一标识
     */
    @Override
    public String getSigma() {
        return (String) get(8);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ACTION.RENEWAL_CREDIT</code>. 「renewalCredit」- 被刷新的凭证
     */
    @Override
    public SActionRecord setRenewalCredit(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ACTION.RENEWAL_CREDIT</code>. 「renewalCredit」- 被刷新的凭证
     */
    @Override
    public String getRenewalCredit() {
        return (String) get(9);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ACTION.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public SActionRecord setLanguage(String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ACTION.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public String getLanguage() {
        return (String) get(10);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ACTION.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public SActionRecord setActive(Boolean value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ACTION.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public Boolean getActive() {
        return (Boolean) get(11);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ACTION.METADATA</code>. 「metadata」- 附加配置数据
     */
    @Override
    public SActionRecord setMetadata(String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ACTION.METADATA</code>. 「metadata」- 附加配置数据
     */
    @Override
    public String getMetadata() {
        return (String) get(12);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ACTION.COMMENT</code>. 「action」- 操作说明
     */
    @Override
    public SActionRecord setComment(String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ACTION.COMMENT</code>. 「action」- 操作说明
     */
    @Override
    public String getComment() {
        return (String) get(13);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ACTION.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public SActionRecord setCreatedAt(LocalDateTime value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ACTION.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(14);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ACTION.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public SActionRecord setCreatedBy(String value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ACTION.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public String getCreatedBy() {
        return (String) get(15);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ACTION.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public SActionRecord setUpdatedAt(LocalDateTime value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ACTION.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(16);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ACTION.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    @Override
    public SActionRecord setUpdatedBy(String value) {
        set(17, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ACTION.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    @Override
    public String getUpdatedBy() {
        return (String) get(17);
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
    // Record18 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row18<String, String, String, String, String, Integer, String, String, String, String, String, Boolean, String, String, LocalDateTime, String, LocalDateTime, String> fieldsRow() {
        return (Row18) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row18<String, String, String, String, String, Integer, String, String, String, String, String, Boolean, String, String, LocalDateTime, String, LocalDateTime, String> valuesRow() {
        return (Row18) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return SAction.S_ACTION.KEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return SAction.S_ACTION.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return SAction.S_ACTION.CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return SAction.S_ACTION.RESOURCE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return SAction.S_ACTION.PERMISSION_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return SAction.S_ACTION.LEVEL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return SAction.S_ACTION.URI;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return SAction.S_ACTION.METHOD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return SAction.S_ACTION.SIGMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return SAction.S_ACTION.RENEWAL_CREDIT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return SAction.S_ACTION.LANGUAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field12() {
        return SAction.S_ACTION.ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field13() {
        return SAction.S_ACTION.METADATA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field14() {
        return SAction.S_ACTION.COMMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field15() {
        return SAction.S_ACTION.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field16() {
        return SAction.S_ACTION.CREATED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field17() {
        return SAction.S_ACTION.UPDATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field18() {
        return SAction.S_ACTION.UPDATED_BY;
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
        return getResourceId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getPermissionId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component6() {
        return getLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getUri();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getMethod();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component9() {
        return getSigma();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component10() {
        return getRenewalCredit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component11() {
        return getLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component12() {
        return getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component13() {
        return getMetadata();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component14() {
        return getComment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component15() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component16() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component17() {
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component18() {
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
        return getResourceId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getPermissionId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getUri();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getMethod();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getSigma();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getRenewalCredit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value11() {
        return getLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value12() {
        return getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value13() {
        return getMetadata();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value14() {
        return getComment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value15() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value16() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value17() {
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value18() {
        return getUpdatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord value1(String value) {
        setKey(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord value3(String value) {
        setCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord value4(String value) {
        setResourceId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord value5(String value) {
        setPermissionId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord value6(Integer value) {
        setLevel(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord value7(String value) {
        setUri(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord value8(String value) {
        setMethod(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord value9(String value) {
        setSigma(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord value10(String value) {
        setRenewalCredit(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord value11(String value) {
        setLanguage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord value12(Boolean value) {
        setActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord value13(String value) {
        setMetadata(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord value14(String value) {
        setComment(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord value15(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord value16(String value) {
        setCreatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord value17(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord value18(String value) {
        setUpdatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SActionRecord values(String value1, String value2, String value3, String value4, String value5, Integer value6, String value7, String value8, String value9, String value10, String value11, Boolean value12, String value13, String value14, LocalDateTime value15, String value16, LocalDateTime value17, String value18) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(ISAction from) {
        setKey(from.getKey());
        setName(from.getName());
        setCode(from.getCode());
        setResourceId(from.getResourceId());
        setPermissionId(from.getPermissionId());
        setLevel(from.getLevel());
        setUri(from.getUri());
        setMethod(from.getMethod());
        setSigma(from.getSigma());
        setRenewalCredit(from.getRenewalCredit());
        setLanguage(from.getLanguage());
        setActive(from.getActive());
        setMetadata(from.getMetadata());
        setComment(from.getComment());
        setCreatedAt(from.getCreatedAt());
        setCreatedBy(from.getCreatedBy());
        setUpdatedAt(from.getUpdatedAt());
        setUpdatedBy(from.getUpdatedBy());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends ISAction> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SActionRecord
     */
    public SActionRecord() {
        super(SAction.S_ACTION);
    }

    /**
     * Create a detached, initialised SActionRecord
     */
    public SActionRecord(String key, String name, String code, String resourceId, String permissionId, Integer level, String uri, String method, String sigma, String renewalCredit, String language, Boolean active, String metadata, String comment, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
        super(SAction.S_ACTION);

        set(0, key);
        set(1, name);
        set(2, code);
        set(3, resourceId);
        set(4, permissionId);
        set(5, level);
        set(6, uri);
        set(7, method);
        set(8, sigma);
        set(9, renewalCredit);
        set(10, language);
        set(11, active);
        set(12, metadata);
        set(13, comment);
        set(14, createdAt);
        set(15, createdBy);
        set(16, updatedAt);
        set(17, updatedBy);
    }
}
