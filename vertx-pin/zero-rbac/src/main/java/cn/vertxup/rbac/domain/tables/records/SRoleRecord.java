/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.rbac.domain.tables.records;


import cn.vertxup.rbac.domain.tables.SRole;
import cn.vertxup.rbac.domain.tables.interfaces.ISRole;

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
public class SRoleRecord extends UpdatableRecordImpl<SRoleRecord> implements Record16<String, String, String, Boolean, String, String, String, String, String, String, Boolean, String, LocalDateTime, String, LocalDateTime, String>, ISRole {

    private static final long serialVersionUID = 669949674;

    /**
     * Setter for <code>DB_ETERNAL.S_ROLE.KEY</code>. 「key」- 角色ID
     */
    @Override
    public SRoleRecord setKey(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ROLE.KEY</code>. 「key」- 角色ID
     */
    @Override
    public String getKey() {
        return (String) get(0);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ROLE.NAME</code>. 「name」- 角色名称
     */
    @Override
    public SRoleRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ROLE.NAME</code>. 「name」- 角色名称
     */
    @Override
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ROLE.CODE</code>. 「code」- 角色系统名
     */
    @Override
    public SRoleRecord setCode(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ROLE.CODE</code>. 「code」- 角色系统名
     */
    @Override
    public String getCode() {
        return (String) get(2);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ROLE.Power</code>. 「Power」- 是否具有定制权限？
     */
    @Override
    public SRoleRecord setPower(Boolean value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ROLE.Power</code>. 「Power」- 是否具有定制权限？
     */
    @Override
    public Boolean getPower() {
        return (Boolean) get(3);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ROLE.COMMENT</code>. 「comment」- 角色备注信息
     */
    @Override
    public SRoleRecord setComment(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ROLE.COMMENT</code>. 「comment」- 角色备注信息
     */
    @Override
    public String getComment() {
        return (String) get(4);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ROLE.MODEL_ID</code>. 「modelId」- 组所关联的模型identifier，用于描述
     */
    @Override
    public SRoleRecord setModelId(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ROLE.MODEL_ID</code>. 「modelId」- 组所关联的模型identifier，用于描述
     */
    @Override
    public String getModelId() {
        return (String) get(5);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ROLE.MODEL_KEY</code>. 「modelKey」- 组所关联的模型记录ID，用于描述哪一个Model中的记录
     */
    @Override
    public SRoleRecord setModelKey(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ROLE.MODEL_KEY</code>. 「modelKey」- 组所关联的模型记录ID，用于描述哪一个Model中的记录
     */
    @Override
    public String getModelKey() {
        return (String) get(6);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ROLE.CATEGORY</code>. 「category」- 组类型
     */
    @Override
    public SRoleRecord setCategory(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ROLE.CATEGORY</code>. 「category」- 组类型
     */
    @Override
    public String getCategory() {
        return (String) get(7);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ROLE.SIGMA</code>. 「sigma」- 统一标识
     */
    @Override
    public SRoleRecord setSigma(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ROLE.SIGMA</code>. 「sigma」- 统一标识
     */
    @Override
    public String getSigma() {
        return (String) get(8);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ROLE.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public SRoleRecord setLanguage(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ROLE.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public String getLanguage() {
        return (String) get(9);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ROLE.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public SRoleRecord setActive(Boolean value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ROLE.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public Boolean getActive() {
        return (Boolean) get(10);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ROLE.METADATA</code>. 「metadata」- 附加配置数据
     */
    @Override
    public SRoleRecord setMetadata(String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ROLE.METADATA</code>. 「metadata」- 附加配置数据
     */
    @Override
    public String getMetadata() {
        return (String) get(11);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ROLE.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public SRoleRecord setCreatedAt(LocalDateTime value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ROLE.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(12);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ROLE.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public SRoleRecord setCreatedBy(String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ROLE.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public String getCreatedBy() {
        return (String) get(13);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ROLE.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public SRoleRecord setUpdatedAt(LocalDateTime value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ROLE.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(14);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_ROLE.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    @Override
    public SRoleRecord setUpdatedBy(String value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_ROLE.UPDATED_BY</code>. 「updatedBy」- 更新人
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
    public Row16<String, String, String, Boolean, String, String, String, String, String, String, Boolean, String, LocalDateTime, String, LocalDateTime, String> fieldsRow() {
        return (Row16) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row16<String, String, String, Boolean, String, String, String, String, String, String, Boolean, String, LocalDateTime, String, LocalDateTime, String> valuesRow() {
        return (Row16) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return SRole.S_ROLE.KEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return SRole.S_ROLE.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return SRole.S_ROLE.CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field4() {
        return SRole.S_ROLE.POWER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return SRole.S_ROLE.COMMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return SRole.S_ROLE.MODEL_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return SRole.S_ROLE.MODEL_KEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return SRole.S_ROLE.CATEGORY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return SRole.S_ROLE.SIGMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return SRole.S_ROLE.LANGUAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field11() {
        return SRole.S_ROLE.ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field12() {
        return SRole.S_ROLE.METADATA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field13() {
        return SRole.S_ROLE.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field14() {
        return SRole.S_ROLE.CREATED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field15() {
        return SRole.S_ROLE.UPDATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field16() {
        return SRole.S_ROLE.UPDATED_BY;
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
    public Boolean component4() {
        return getPower();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getComment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getModelId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getModelKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getCategory();
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
        return getLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component11() {
        return getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component12() {
        return getMetadata();
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
    public Boolean value4() {
        return getPower();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getComment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getModelId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getModelKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getCategory();
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
        return getLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value11() {
        return getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value12() {
        return getMetadata();
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
    public SRoleRecord value1(String value) {
        setKey(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SRoleRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SRoleRecord value3(String value) {
        setCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SRoleRecord value4(Boolean value) {
        setPower(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SRoleRecord value5(String value) {
        setComment(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SRoleRecord value6(String value) {
        setModelId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SRoleRecord value7(String value) {
        setModelKey(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SRoleRecord value8(String value) {
        setCategory(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SRoleRecord value9(String value) {
        setSigma(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SRoleRecord value10(String value) {
        setLanguage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SRoleRecord value11(Boolean value) {
        setActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SRoleRecord value12(String value) {
        setMetadata(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SRoleRecord value13(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SRoleRecord value14(String value) {
        setCreatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SRoleRecord value15(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SRoleRecord value16(String value) {
        setUpdatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SRoleRecord values(String value1, String value2, String value3, Boolean value4, String value5, String value6, String value7, String value8, String value9, String value10, Boolean value11, String value12, LocalDateTime value13, String value14, LocalDateTime value15, String value16) {
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
    public void from(ISRole from) {
        setKey(from.getKey());
        setName(from.getName());
        setCode(from.getCode());
        setPower(from.getPower());
        setComment(from.getComment());
        setModelId(from.getModelId());
        setModelKey(from.getModelKey());
        setCategory(from.getCategory());
        setSigma(from.getSigma());
        setLanguage(from.getLanguage());
        setActive(from.getActive());
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
    public <E extends ISRole> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SRoleRecord
     */
    public SRoleRecord() {
        super(SRole.S_ROLE);
    }

    /**
     * Create a detached, initialised SRoleRecord
     */
    public SRoleRecord(String key, String name, String code, Boolean power, String comment, String modelId, String modelKey, String category, String sigma, String language, Boolean active, String metadata, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
        super(SRole.S_ROLE);

        set(0, key);
        set(1, name);
        set(2, code);
        set(3, power);
        set(4, comment);
        set(5, modelId);
        set(6, modelKey);
        set(7, category);
        set(8, sigma);
        set(9, language);
        set(10, active);
        set(11, metadata);
        set(12, createdAt);
        set(13, createdBy);
        set(14, updatedAt);
        set(15, updatedBy);
    }
}
