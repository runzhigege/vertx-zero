/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.domain.tables.records;


import cn.vertxup.domain.tables.SGroup;
import cn.vertxup.domain.tables.interfaces.ISGroup;

import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record15;
import org.jooq.Row15;
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
public class SGroupRecord extends UpdatableRecordImpl<SGroupRecord> implements Record15<String, String, String, String, String, String, String, String, String, Boolean, String, LocalDateTime, String, LocalDateTime, String>, ISGroup {

    private static final long serialVersionUID = 1359770598;

    /**
     * Setter for <code>UP_RBAC.S_GROUP.KEY</code>. 「key」- 组ID
     */
    @Override
    public SGroupRecord setKey(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>UP_RBAC.S_GROUP.KEY</code>. 「key」- 组ID
     */
    @Override
    public String getKey() {
        return (String) get(0);
    }

    /**
     * Setter for <code>UP_RBAC.S_GROUP.NAME</code>. 「name」- 组名称
     */
    @Override
    public SGroupRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>UP_RBAC.S_GROUP.NAME</code>. 「name」- 组名称
     */
    @Override
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>UP_RBAC.S_GROUP.CODE</code>. 「code」- 组系统码
     */
    @Override
    public SGroupRecord setCode(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>UP_RBAC.S_GROUP.CODE</code>. 「code」- 组系统码
     */
    @Override
    public String getCode() {
        return (String) get(2);
    }

    /**
     * Setter for <code>UP_RBAC.S_GROUP.PARENT_ID</code>. 「parentId」- 父组ID（组支持树形结构，角色平行结构）
     */
    @Override
    public SGroupRecord setParentId(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>UP_RBAC.S_GROUP.PARENT_ID</code>. 「parentId」- 父组ID（组支持树形结构，角色平行结构）
     */
    @Override
    public String getParentId() {
        return (String) get(3);
    }

    /**
     * Setter for <code>UP_RBAC.S_GROUP.MODEL_ID</code>. 「modelId」- 组所关联的模型identifier，用于描述
     */
    @Override
    public SGroupRecord setModelId(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>UP_RBAC.S_GROUP.MODEL_ID</code>. 「modelId」- 组所关联的模型identifier，用于描述
     */
    @Override
    public String getModelId() {
        return (String) get(4);
    }

    /**
     * Setter for <code>UP_RBAC.S_GROUP.MODEL_KEY</code>. 「modelKey」- 组所关联的模型记录ID，用于描述哪一个Model中的记录
     */
    @Override
    public SGroupRecord setModelKey(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>UP_RBAC.S_GROUP.MODEL_KEY</code>. 「modelKey」- 组所关联的模型记录ID，用于描述哪一个Model中的记录
     */
    @Override
    public String getModelKey() {
        return (String) get(5);
    }

    /**
     * Setter for <code>UP_RBAC.S_GROUP.CATEGORY</code>. 「category」- 组类型
     */
    @Override
    public SGroupRecord setCategory(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>UP_RBAC.S_GROUP.CATEGORY</code>. 「category」- 组类型
     */
    @Override
    public String getCategory() {
        return (String) get(6);
    }

    /**
     * Setter for <code>UP_RBAC.S_GROUP.SIGMA</code>. 「sigma」- 用户组绑定的统一标识
     */
    @Override
    public SGroupRecord setSigma(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>UP_RBAC.S_GROUP.SIGMA</code>. 「sigma」- 用户组绑定的统一标识
     */
    @Override
    public String getSigma() {
        return (String) get(7);
    }

    /**
     * Setter for <code>UP_RBAC.S_GROUP.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public SGroupRecord setLanguage(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>UP_RBAC.S_GROUP.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public String getLanguage() {
        return (String) get(8);
    }

    /**
     * Setter for <code>UP_RBAC.S_GROUP.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public SGroupRecord setActive(Boolean value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>UP_RBAC.S_GROUP.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public Boolean getActive() {
        return (Boolean) get(9);
    }

    /**
     * Setter for <code>UP_RBAC.S_GROUP.METADATA</code>. 「metadata」- 附加配置数据
     */
    @Override
    public SGroupRecord setMetadata(String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>UP_RBAC.S_GROUP.METADATA</code>. 「metadata」- 附加配置数据
     */
    @Override
    public String getMetadata() {
        return (String) get(10);
    }

    /**
     * Setter for <code>UP_RBAC.S_GROUP.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public SGroupRecord setCreatedAt(LocalDateTime value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>UP_RBAC.S_GROUP.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(11);
    }

    /**
     * Setter for <code>UP_RBAC.S_GROUP.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public SGroupRecord setCreatedBy(String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>UP_RBAC.S_GROUP.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public String getCreatedBy() {
        return (String) get(12);
    }

    /**
     * Setter for <code>UP_RBAC.S_GROUP.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public SGroupRecord setUpdatedAt(LocalDateTime value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>UP_RBAC.S_GROUP.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(13);
    }

    /**
     * Setter for <code>UP_RBAC.S_GROUP.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    @Override
    public SGroupRecord setUpdatedBy(String value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>UP_RBAC.S_GROUP.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    @Override
    public String getUpdatedBy() {
        return (String) get(14);
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
    // Record15 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row15<String, String, String, String, String, String, String, String, String, Boolean, String, LocalDateTime, String, LocalDateTime, String> fieldsRow() {
        return (Row15) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row15<String, String, String, String, String, String, String, String, String, Boolean, String, LocalDateTime, String, LocalDateTime, String> valuesRow() {
        return (Row15) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return SGroup.S_GROUP.KEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return SGroup.S_GROUP.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return SGroup.S_GROUP.CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return SGroup.S_GROUP.PARENT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return SGroup.S_GROUP.MODEL_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return SGroup.S_GROUP.MODEL_KEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return SGroup.S_GROUP.CATEGORY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return SGroup.S_GROUP.SIGMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return SGroup.S_GROUP.LANGUAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field10() {
        return SGroup.S_GROUP.ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return SGroup.S_GROUP.METADATA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field12() {
        return SGroup.S_GROUP.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field13() {
        return SGroup.S_GROUP.CREATED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field14() {
        return SGroup.S_GROUP.UPDATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field15() {
        return SGroup.S_GROUP.UPDATED_BY;
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
        return getParentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getModelId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getModelKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getCategory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getSigma();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component9() {
        return getLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component10() {
        return getActive();
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
    public LocalDateTime component12() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component13() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component14() {
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component15() {
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
        return getParentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getModelId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getModelKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getCategory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getSigma();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value10() {
        return getActive();
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
    public LocalDateTime value12() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value13() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value14() {
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value15() {
        return getUpdatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SGroupRecord value1(String value) {
        setKey(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SGroupRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SGroupRecord value3(String value) {
        setCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SGroupRecord value4(String value) {
        setParentId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SGroupRecord value5(String value) {
        setModelId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SGroupRecord value6(String value) {
        setModelKey(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SGroupRecord value7(String value) {
        setCategory(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SGroupRecord value8(String value) {
        setSigma(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SGroupRecord value9(String value) {
        setLanguage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SGroupRecord value10(Boolean value) {
        setActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SGroupRecord value11(String value) {
        setMetadata(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SGroupRecord value12(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SGroupRecord value13(String value) {
        setCreatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SGroupRecord value14(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SGroupRecord value15(String value) {
        setUpdatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SGroupRecord values(String value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, String value9, Boolean value10, String value11, LocalDateTime value12, String value13, LocalDateTime value14, String value15) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(ISGroup from) {
        setKey(from.getKey());
        setName(from.getName());
        setCode(from.getCode());
        setParentId(from.getParentId());
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
    public <E extends ISGroup> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SGroupRecord
     */
    public SGroupRecord() {
        super(SGroup.S_GROUP);
    }

    /**
     * Create a detached, initialised SGroupRecord
     */
    public SGroupRecord(String key, String name, String code, String parentId, String modelId, String modelKey, String category, String sigma, String language, Boolean active, String metadata, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
        super(SGroup.S_GROUP);

        set(0, key);
        set(1, name);
        set(2, code);
        set(3, parentId);
        set(4, modelId);
        set(5, modelKey);
        set(6, category);
        set(7, sigma);
        set(8, language);
        set(9, active);
        set(10, metadata);
        set(11, createdAt);
        set(12, createdBy);
        set(13, updatedAt);
        set(14, updatedBy);
    }
}
