/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.rbac.domain.tables.records;


import cn.vertxup.rbac.domain.tables.SView;
import cn.vertxup.rbac.domain.tables.interfaces.ISView;

import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record17;
import org.jooq.Row17;
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
public class SViewRecord extends UpdatableRecordImpl<SViewRecord> implements Record17<String, String, String, String, String, String, String, String, String, String, String, Boolean, String, LocalDateTime, String, LocalDateTime, String>, ISView {

    private static final long serialVersionUID = 140599857;

    /**
     * Setter for <code>DB_ETERNAL.S_VIEW.KEY</code>. 「key」- 限定记录ID
     */
    @Override
    public SViewRecord setKey(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_VIEW.KEY</code>. 「key」- 限定记录ID
     */
    @Override
    public String getKey() {
        return (String) get(0);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_VIEW.NAME</code>. 「name」- 视图名称，每个 MATRIX 对应一个视图
     */
    @Override
    public SViewRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_VIEW.NAME</code>. 「name」- 视图名称，每个 MATRIX 对应一个视图
     */
    @Override
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_VIEW.OWNER</code>. 「owner」- 用户 / 角色ID
     */
    @Override
    public SViewRecord setOwner(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_VIEW.OWNER</code>. 「owner」- 用户 / 角色ID
     */
    @Override
    public String getOwner() {
        return (String) get(2);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_VIEW.OWNER_TYPE</code>. 「ownerType」- ROLE 角色，USER 用户
     */
    @Override
    public SViewRecord setOwnerType(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_VIEW.OWNER_TYPE</code>. 「ownerType」- ROLE 角色，USER 用户
     */
    @Override
    public String getOwnerType() {
        return (String) get(3);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_VIEW.RESOURCE_ID</code>. 「resourceId」- 关联资源ID
     */
    @Override
    public SViewRecord setResourceId(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_VIEW.RESOURCE_ID</code>. 「resourceId」- 关联资源ID
     */
    @Override
    public String getResourceId() {
        return (String) get(4);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_VIEW.PROJECTION</code>. 「projection」- 该资源的列定义（单用户处理）
     */
    @Override
    public SViewRecord setProjection(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_VIEW.PROJECTION</code>. 「projection」- 该资源的列定义（单用户处理）
     */
    @Override
    public String getProjection() {
        return (String) get(5);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_VIEW.CRITERIA</code>. 「criteria」- 该资源的行查询（单用户处理）
     */
    @Override
    public SViewRecord setCriteria(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_VIEW.CRITERIA</code>. 「criteria」- 该资源的行查询（单用户处理）
     */
    @Override
    public String getCriteria() {
        return (String) get(6);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_VIEW.ROWS</code>. 「rows」- 该资源针对保存的行进行过滤
     */
    @Override
    public SViewRecord setRows(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_VIEW.ROWS</code>. 「rows」- 该资源针对保存的行进行过滤
     */
    @Override
    public String getRows() {
        return (String) get(7);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_VIEW.POSITION</code>. 「position」- 当前列的顺序信息
     */
    @Override
    public SViewRecord setPosition(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_VIEW.POSITION</code>. 「position」- 当前列的顺序信息
     */
    @Override
    public String getPosition() {
        return (String) get(8);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_VIEW.SIGMA</code>. 「sigma」- 用户组绑定的统一标识
     */
    @Override
    public SViewRecord setSigma(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_VIEW.SIGMA</code>. 「sigma」- 用户组绑定的统一标识
     */
    @Override
    public String getSigma() {
        return (String) get(9);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_VIEW.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public SViewRecord setLanguage(String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_VIEW.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public String getLanguage() {
        return (String) get(10);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_VIEW.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public SViewRecord setActive(Boolean value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_VIEW.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public Boolean getActive() {
        return (Boolean) get(11);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_VIEW.METADATA</code>. 「metadata」- 附加配置数据
     */
    @Override
    public SViewRecord setMetadata(String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_VIEW.METADATA</code>. 「metadata」- 附加配置数据
     */
    @Override
    public String getMetadata() {
        return (String) get(12);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_VIEW.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public SViewRecord setCreatedAt(LocalDateTime value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_VIEW.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(13);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_VIEW.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public SViewRecord setCreatedBy(String value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_VIEW.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public String getCreatedBy() {
        return (String) get(14);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_VIEW.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public SViewRecord setUpdatedAt(LocalDateTime value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_VIEW.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(15);
    }

    /**
     * Setter for <code>DB_ETERNAL.S_VIEW.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    @Override
    public SViewRecord setUpdatedBy(String value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.S_VIEW.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    @Override
    public String getUpdatedBy() {
        return (String) get(16);
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
    // Record17 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row17<String, String, String, String, String, String, String, String, String, String, String, Boolean, String, LocalDateTime, String, LocalDateTime, String> fieldsRow() {
        return (Row17) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row17<String, String, String, String, String, String, String, String, String, String, String, Boolean, String, LocalDateTime, String, LocalDateTime, String> valuesRow() {
        return (Row17) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return SView.S_VIEW.KEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return SView.S_VIEW.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return SView.S_VIEW.OWNER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return SView.S_VIEW.OWNER_TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return SView.S_VIEW.RESOURCE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return SView.S_VIEW.PROJECTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return SView.S_VIEW.CRITERIA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return SView.S_VIEW.ROWS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return SView.S_VIEW.POSITION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return SView.S_VIEW.SIGMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return SView.S_VIEW.LANGUAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field12() {
        return SView.S_VIEW.ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field13() {
        return SView.S_VIEW.METADATA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field14() {
        return SView.S_VIEW.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field15() {
        return SView.S_VIEW.CREATED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field16() {
        return SView.S_VIEW.UPDATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field17() {
        return SView.S_VIEW.UPDATED_BY;
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
        return getOwner();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getOwnerType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getResourceId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getProjection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getCriteria();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getRows();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component9() {
        return getPosition();
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
    public LocalDateTime component14() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component15() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component16() {
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component17() {
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
        return getOwner();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getOwnerType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getResourceId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getProjection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getCriteria();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getRows();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getPosition();
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
    public LocalDateTime value14() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value15() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value16() {
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value17() {
        return getUpdatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SViewRecord value1(String value) {
        setKey(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SViewRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SViewRecord value3(String value) {
        setOwner(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SViewRecord value4(String value) {
        setOwnerType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SViewRecord value5(String value) {
        setResourceId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SViewRecord value6(String value) {
        setProjection(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SViewRecord value7(String value) {
        setCriteria(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SViewRecord value8(String value) {
        setRows(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SViewRecord value9(String value) {
        setPosition(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SViewRecord value10(String value) {
        setSigma(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SViewRecord value11(String value) {
        setLanguage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SViewRecord value12(Boolean value) {
        setActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SViewRecord value13(String value) {
        setMetadata(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SViewRecord value14(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SViewRecord value15(String value) {
        setCreatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SViewRecord value16(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SViewRecord value17(String value) {
        setUpdatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SViewRecord values(String value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11, Boolean value12, String value13, LocalDateTime value14, String value15, LocalDateTime value16, String value17) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(ISView from) {
        setKey(from.getKey());
        setName(from.getName());
        setOwner(from.getOwner());
        setOwnerType(from.getOwnerType());
        setResourceId(from.getResourceId());
        setProjection(from.getProjection());
        setCriteria(from.getCriteria());
        setRows(from.getRows());
        setPosition(from.getPosition());
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
    public <E extends ISView> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SViewRecord
     */
    public SViewRecord() {
        super(SView.S_VIEW);
    }

    /**
     * Create a detached, initialised SViewRecord
     */
    public SViewRecord(String key, String name, String owner, String ownerType, String resourceId, String projection, String criteria, String rows, String position, String sigma, String language, Boolean active, String metadata, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
        super(SView.S_VIEW);

        set(0, key);
        set(1, name);
        set(2, owner);
        set(3, ownerType);
        set(4, resourceId);
        set(5, projection);
        set(6, criteria);
        set(7, rows);
        set(8, position);
        set(9, sigma);
        set(10, language);
        set(11, active);
        set(12, metadata);
        set(13, createdAt);
        set(14, createdBy);
        set(15, updatedAt);
        set(16, updatedBy);
    }
}
