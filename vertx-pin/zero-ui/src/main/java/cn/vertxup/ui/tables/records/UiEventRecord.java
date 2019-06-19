/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ui.tables.records;


import cn.vertxup.ui.tables.UiEvent;
import cn.vertxup.ui.tables.interfaces.IUiEvent;

import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record22;
import org.jooq.Row22;
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
public class UiEventRecord extends UpdatableRecordImpl<UiEventRecord> implements Record22<String, String, String, String, String, String, String, String, String, String, String, String, String, String, Boolean, String, String, String, LocalDateTime, String, LocalDateTime, String>, IUiEvent {

    private static final long serialVersionUID = 1033898968;

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.KEY</code>. 「key」-- 主键
     */
    @Override
    public UiEventRecord setKey(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.KEY</code>. 「key」-- 主键
     */
    @Override
    public String getKey() {
        return (String) get(0);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.ACTION_ID</code>. 「actionId」-- 事件关联的ActionId（安全专用）
     */
    @Override
    public UiEventRecord setActionId(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.ACTION_ID</code>. 「actionId」-- 事件关联的ActionId（安全专用）
     */
    @Override
    public String getActionId() {
        return (String) get(1);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.TARGET</code>. 「target」-- 必须，事件对应的目标ID
     */
    @Override
    public UiEventRecord setTarget(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.TARGET</code>. 「target」-- 必须，事件对应的目标ID
     */
    @Override
    public String getTarget() {
        return (String) get(2);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.RELATED_TYPE</code>. 「relatedType」-- 事件类型：FORM - 表单事件，COMPONENT - 组件事件
     */
    @Override
    public UiEventRecord setRelatedType(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.RELATED_TYPE</code>. 「relatedType」-- 事件类型：FORM - 表单事件，COMPONENT - 组件事件
     */
    @Override
    public String getRelatedType() {
        return (String) get(3);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.RELATED_ID</code>. 「relatedId」-- 关联ID
     */
    @Override
    public UiEventRecord setRelatedId(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.RELATED_ID</code>. 「relatedId」-- 关联ID
     */
    @Override
    public String getRelatedId() {
        return (String) get(4);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.AJAX</code>. 「ajax」- 对应 ajax 节点
     */
    @Override
    public UiEventRecord setAjax(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.AJAX</code>. 「ajax」- 对应 ajax 节点
     */
    @Override
    public String getAjax() {
        return (String) get(5);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.MODE</code>. 「mode」- 对应 mode 节点，$env中的合并、删除
     */
    @Override
    public UiEventRecord setMode(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.MODE</code>. 「mode」- 对应 mode 节点，$env中的合并、删除
     */
    @Override
    public String getMode() {
        return (String) get(6);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.MODAL</code>. 「modal」- 窗口配置
     */
    @Override
    public UiEventRecord setModal(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.MODAL</code>. 「modal」- 窗口配置
     */
    @Override
    public String getModal() {
        return (String) get(7);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.QUERY</code>. 「query」- 查询参数专用配置
     */
    @Override
    public UiEventRecord setQuery(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.QUERY</code>. 「query」- 查询参数专用配置
     */
    @Override
    public String getQuery() {
        return (String) get(8);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.CONNECTOR</code>. 「connector」- 查询专用：AND或OR
     */
    @Override
    public UiEventRecord setConnector(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.CONNECTOR</code>. 「connector」- 查询专用：AND或OR
     */
    @Override
    public String getConnector() {
        return (String) get(9);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.FILE</code>. 「file」- 上传专用
     */
    @Override
    public UiEventRecord setFile(String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.FILE</code>. 「file」- 上传专用
     */
    @Override
    public String getFile() {
        return (String) get(10);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.RULES</code>. 「rules」- 事件专用的配置，规则
     */
    @Override
    public UiEventRecord setRules(String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.RULES</code>. 「rules」- 事件专用的配置，规则
     */
    @Override
    public String getRules() {
        return (String) get(11);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.DATA_MAGIC</code>. 「dataMagic」- 数据加载：config -&gt; magic
     */
    @Override
    public UiEventRecord setDataMagic(String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.DATA_MAGIC</code>. 「dataMagic」- 数据加载：config -&gt; magic
     */
    @Override
    public String getDataMagic() {
        return (String) get(12);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.DATA_ARGUMENTS</code>. 「dataArguments」- 函数配置：config -&gt; arguments
     */
    @Override
    public UiEventRecord setDataArguments(String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.DATA_ARGUMENTS</code>. 「dataArguments」- 函数配置：config -&gt; arguments
     */
    @Override
    public String getDataArguments() {
        return (String) get(13);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public UiEventRecord setActive(Boolean value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public Boolean getActive() {
        return (Boolean) get(14);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.SIGMA</code>. 「sigma」- 统一标识
     */
    @Override
    public UiEventRecord setSigma(String value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.SIGMA</code>. 「sigma」- 统一标识
     */
    @Override
    public String getSigma() {
        return (String) get(15);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.METADATA</code>. 「metadata」- 附加配置
     */
    @Override
    public UiEventRecord setMetadata(String value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.METADATA</code>. 「metadata」- 附加配置
     */
    @Override
    public String getMetadata() {
        return (String) get(16);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public UiEventRecord setLanguage(String value) {
        set(17, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public String getLanguage() {
        return (String) get(17);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public UiEventRecord setCreatedAt(LocalDateTime value) {
        set(18, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(18);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public UiEventRecord setCreatedBy(String value) {
        set(19, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public String getCreatedBy() {
        return (String) get(19);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public UiEventRecord setUpdatedAt(LocalDateTime value) {
        set(20, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(20);
    }

    /**
     * Setter for <code>DB_ETERNAL.UI_EVENT.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    @Override
    public UiEventRecord setUpdatedBy(String value) {
        set(21, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.UI_EVENT.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    @Override
    public String getUpdatedBy() {
        return (String) get(21);
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
    // Record22 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row22<String, String, String, String, String, String, String, String, String, String, String, String, String, String, Boolean, String, String, String, LocalDateTime, String, LocalDateTime, String> fieldsRow() {
        return (Row22) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row22<String, String, String, String, String, String, String, String, String, String, String, String, String, String, Boolean, String, String, String, LocalDateTime, String, LocalDateTime, String> valuesRow() {
        return (Row22) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return UiEvent.UI_EVENT.KEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return UiEvent.UI_EVENT.ACTION_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return UiEvent.UI_EVENT.TARGET;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return UiEvent.UI_EVENT.RELATED_TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return UiEvent.UI_EVENT.RELATED_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return UiEvent.UI_EVENT.AJAX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return UiEvent.UI_EVENT.MODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return UiEvent.UI_EVENT.MODAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return UiEvent.UI_EVENT.QUERY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return UiEvent.UI_EVENT.CONNECTOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return UiEvent.UI_EVENT.FILE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field12() {
        return UiEvent.UI_EVENT.RULES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field13() {
        return UiEvent.UI_EVENT.DATA_MAGIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field14() {
        return UiEvent.UI_EVENT.DATA_ARGUMENTS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field15() {
        return UiEvent.UI_EVENT.ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field16() {
        return UiEvent.UI_EVENT.SIGMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field17() {
        return UiEvent.UI_EVENT.METADATA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field18() {
        return UiEvent.UI_EVENT.LANGUAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field19() {
        return UiEvent.UI_EVENT.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field20() {
        return UiEvent.UI_EVENT.CREATED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field21() {
        return UiEvent.UI_EVENT.UPDATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field22() {
        return UiEvent.UI_EVENT.UPDATED_BY;
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
        return getActionId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getTarget();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getRelatedType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getRelatedId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getAjax();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getMode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getModal();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component9() {
        return getQuery();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component10() {
        return getConnector();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component11() {
        return getFile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component12() {
        return getRules();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component13() {
        return getDataMagic();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component14() {
        return getDataArguments();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component15() {
        return getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component16() {
        return getSigma();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component17() {
        return getMetadata();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component18() {
        return getLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component19() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component20() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component21() {
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component22() {
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
        return getActionId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getTarget();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getRelatedType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getRelatedId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getAjax();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getMode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getModal();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getQuery();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getConnector();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value11() {
        return getFile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value12() {
        return getRules();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value13() {
        return getDataMagic();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value14() {
        return getDataArguments();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value15() {
        return getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value16() {
        return getSigma();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value17() {
        return getMetadata();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value18() {
        return getLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value19() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value20() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value21() {
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value22() {
        return getUpdatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value1(String value) {
        setKey(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value2(String value) {
        setActionId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value3(String value) {
        setTarget(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value4(String value) {
        setRelatedType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value5(String value) {
        setRelatedId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value6(String value) {
        setAjax(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value7(String value) {
        setMode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value8(String value) {
        setModal(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value9(String value) {
        setQuery(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value10(String value) {
        setConnector(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value11(String value) {
        setFile(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value12(String value) {
        setRules(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value13(String value) {
        setDataMagic(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value14(String value) {
        setDataArguments(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value15(Boolean value) {
        setActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value16(String value) {
        setSigma(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value17(String value) {
        setMetadata(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value18(String value) {
        setLanguage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value19(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value20(String value) {
        setCreatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value21(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord value22(String value) {
        setUpdatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiEventRecord values(String value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11, String value12, String value13, String value14, Boolean value15, String value16, String value17, String value18, LocalDateTime value19, String value20, LocalDateTime value21, String value22) {
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
        value21(value21);
        value22(value22);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IUiEvent from) {
        setKey(from.getKey());
        setActionId(from.getActionId());
        setTarget(from.getTarget());
        setRelatedType(from.getRelatedType());
        setRelatedId(from.getRelatedId());
        setAjax(from.getAjax());
        setMode(from.getMode());
        setModal(from.getModal());
        setQuery(from.getQuery());
        setConnector(from.getConnector());
        setFile(from.getFile());
        setRules(from.getRules());
        setDataMagic(from.getDataMagic());
        setDataArguments(from.getDataArguments());
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
    public <E extends IUiEvent> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UiEventRecord
     */
    public UiEventRecord() {
        super(UiEvent.UI_EVENT);
    }

    /**
     * Create a detached, initialised UiEventRecord
     */
    public UiEventRecord(String key, String actionId, String target, String relatedType, String relatedId, String ajax, String mode, String modal, String query, String connector, String file, String rules, String dataMagic, String dataArguments, Boolean active, String sigma, String metadata, String language, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
        super(UiEvent.UI_EVENT);

        set(0, key);
        set(1, actionId);
        set(2, target);
        set(3, relatedType);
        set(4, relatedId);
        set(5, ajax);
        set(6, mode);
        set(7, modal);
        set(8, query);
        set(9, connector);
        set(10, file);
        set(11, rules);
        set(12, dataMagic);
        set(13, dataArguments);
        set(14, active);
        set(15, sigma);
        set(16, metadata);
        set(17, language);
        set(18, createdAt);
        set(19, createdBy);
        set(20, updatedAt);
        set(21, updatedBy);
    }
}
