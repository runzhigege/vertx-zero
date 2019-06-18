/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ambient.tables.records;


import cn.vertxup.ambient.tables.XMenu;
import cn.vertxup.ambient.tables.interfaces.IXMenu;

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
public class XMenuRecord extends UpdatableRecordImpl<XMenuRecord> implements Record18<String, String, String, String, String, String, Long, Long, String, String, Boolean, String, String, String, LocalDateTime, String, LocalDateTime, String>, IXMenu {

    private static final long serialVersionUID = -1747579682;

    /**
     * Setter for <code>DB_ETERNAL.X_MENU.KEY</code>. 「key」- 菜单主键
     */
    @Override
    public XMenuRecord setKey(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_MENU.KEY</code>. 「key」- 菜单主键
     */
    @Override
    public String getKey() {
        return (String) get(0);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_MENU.NAME</code>. 「name」- 菜单名称
     */
    @Override
    public XMenuRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_MENU.NAME</code>. 「name」- 菜单名称
     */
    @Override
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_MENU.ICON</code>. 「icon」- 菜单使用的icon
     */
    @Override
    public XMenuRecord setIcon(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_MENU.ICON</code>. 「icon」- 菜单使用的icon
     */
    @Override
    public String getIcon() {
        return (String) get(2);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_MENU.TEXT</code>. 「text」- 菜单显示文字
     */
    @Override
    public XMenuRecord setText(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_MENU.TEXT</code>. 「text」- 菜单显示文字
     */
    @Override
    public String getText() {
        return (String) get(3);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_MENU.URI</code>. 「uri」- 菜单地址（不包含应用的path）
     */
    @Override
    public XMenuRecord setUri(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_MENU.URI</code>. 「uri」- 菜单地址（不包含应用的path）
     */
    @Override
    public String getUri() {
        return (String) get(4);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_MENU.TYPE</code>. 「type」- 菜单类型
     */
    @Override
    public XMenuRecord setType(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_MENU.TYPE</code>. 「type」- 菜单类型
     */
    @Override
    public String getType() {
        return (String) get(5);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_MENU.ORDER</code>. 「order」- 菜单排序
     */
    @Override
    public XMenuRecord setOrder(Long value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_MENU.ORDER</code>. 「order」- 菜单排序
     */
    @Override
    public Long getOrder() {
        return (Long) get(6);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_MENU.LEVEL</code>. 「level」- 菜单层级
     */
    @Override
    public XMenuRecord setLevel(Long value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_MENU.LEVEL</code>. 「level」- 菜单层级
     */
    @Override
    public Long getLevel() {
        return (Long) get(7);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_MENU.PARENT_ID</code>. 「parentId」- 菜单父ID
     */
    @Override
    public XMenuRecord setParentId(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_MENU.PARENT_ID</code>. 「parentId」- 菜单父ID
     */
    @Override
    public String getParentId() {
        return (String) get(8);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_MENU.APP_ID</code>. 「appId」- 应用程序ID
     */
    @Override
    public XMenuRecord setAppId(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_MENU.APP_ID</code>. 「appId」- 应用程序ID
     */
    @Override
    public String getAppId() {
        return (String) get(9);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_MENU.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public XMenuRecord setActive(Boolean value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_MENU.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public Boolean getActive() {
        return (Boolean) get(10);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_MENU.SIGMA</code>. 「sigma」- 统一标识
     */
    @Override
    public XMenuRecord setSigma(String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_MENU.SIGMA</code>. 「sigma」- 统一标识
     */
    @Override
    public String getSigma() {
        return (String) get(11);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_MENU.METADATA</code>. 「metadata」- 附加配置
     */
    @Override
    public XMenuRecord setMetadata(String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_MENU.METADATA</code>. 「metadata」- 附加配置
     */
    @Override
    public String getMetadata() {
        return (String) get(12);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_MENU.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public XMenuRecord setLanguage(String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_MENU.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public String getLanguage() {
        return (String) get(13);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_MENU.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public XMenuRecord setCreatedAt(LocalDateTime value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_MENU.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(14);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_MENU.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public XMenuRecord setCreatedBy(String value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_MENU.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public String getCreatedBy() {
        return (String) get(15);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_MENU.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public XMenuRecord setUpdatedAt(LocalDateTime value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_MENU.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(16);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_MENU.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    @Override
    public XMenuRecord setUpdatedBy(String value) {
        set(17, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_MENU.UPDATED_BY</code>. 「updatedBy」- 更新人
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
    public Row18<String, String, String, String, String, String, Long, Long, String, String, Boolean, String, String, String, LocalDateTime, String, LocalDateTime, String> fieldsRow() {
        return (Row18) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row18<String, String, String, String, String, String, Long, Long, String, String, Boolean, String, String, String, LocalDateTime, String, LocalDateTime, String> valuesRow() {
        return (Row18) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return XMenu.X_MENU.KEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return XMenu.X_MENU.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return XMenu.X_MENU.ICON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return XMenu.X_MENU.TEXT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return XMenu.X_MENU.URI;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return XMenu.X_MENU.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field7() {
        return XMenu.X_MENU.ORDER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field8() {
        return XMenu.X_MENU.LEVEL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return XMenu.X_MENU.PARENT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return XMenu.X_MENU.APP_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field11() {
        return XMenu.X_MENU.ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field12() {
        return XMenu.X_MENU.SIGMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field13() {
        return XMenu.X_MENU.METADATA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field14() {
        return XMenu.X_MENU.LANGUAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field15() {
        return XMenu.X_MENU.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field16() {
        return XMenu.X_MENU.CREATED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field17() {
        return XMenu.X_MENU.UPDATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field18() {
        return XMenu.X_MENU.UPDATED_BY;
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
        return getIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getUri();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component7() {
        return getOrder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component8() {
        return getLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component9() {
        return getParentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component10() {
        return getAppId();
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
        return getSigma();
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
        return getLanguage();
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
        return getIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getUri();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value7() {
        return getOrder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value8() {
        return getLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getParentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getAppId();
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
        return getSigma();
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
        return getLanguage();
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
    public XMenuRecord value1(String value) {
        setKey(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMenuRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMenuRecord value3(String value) {
        setIcon(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMenuRecord value4(String value) {
        setText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMenuRecord value5(String value) {
        setUri(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMenuRecord value6(String value) {
        setType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMenuRecord value7(Long value) {
        setOrder(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMenuRecord value8(Long value) {
        setLevel(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMenuRecord value9(String value) {
        setParentId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMenuRecord value10(String value) {
        setAppId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMenuRecord value11(Boolean value) {
        setActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMenuRecord value12(String value) {
        setSigma(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMenuRecord value13(String value) {
        setMetadata(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMenuRecord value14(String value) {
        setLanguage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMenuRecord value15(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMenuRecord value16(String value) {
        setCreatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMenuRecord value17(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMenuRecord value18(String value) {
        setUpdatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMenuRecord values(String value1, String value2, String value3, String value4, String value5, String value6, Long value7, Long value8, String value9, String value10, Boolean value11, String value12, String value13, String value14, LocalDateTime value15, String value16, LocalDateTime value17, String value18) {
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
    public void from(IXMenu from) {
        setKey(from.getKey());
        setName(from.getName());
        setIcon(from.getIcon());
        setText(from.getText());
        setUri(from.getUri());
        setType(from.getType());
        setOrder(from.getOrder());
        setLevel(from.getLevel());
        setParentId(from.getParentId());
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
    public <E extends IXMenu> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached XMenuRecord
     */
    public XMenuRecord() {
        super(XMenu.X_MENU);
    }

    /**
     * Create a detached, initialised XMenuRecord
     */
    public XMenuRecord(String key, String name, String icon, String text, String uri, String type, Long order, Long level, String parentId, String appId, Boolean active, String sigma, String metadata, String language, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
        super(XMenu.X_MENU);

        set(0, key);
        set(1, name);
        set(2, icon);
        set(3, text);
        set(4, uri);
        set(5, type);
        set(6, order);
        set(7, level);
        set(8, parentId);
        set(9, appId);
        set(10, active);
        set(11, sigma);
        set(12, metadata);
        set(13, language);
        set(14, createdAt);
        set(15, createdBy);
        set(16, updatedAt);
        set(17, updatedBy);
    }
}
