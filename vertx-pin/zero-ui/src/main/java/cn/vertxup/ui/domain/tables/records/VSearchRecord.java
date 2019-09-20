/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ui.domain.tables.records;


import cn.vertxup.ui.domain.tables.VSearch;
import cn.vertxup.ui.domain.tables.interfaces.IVSearch;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row11;
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
public class VSearchRecord extends UpdatableRecordImpl<VSearchRecord> implements Record11<String, Boolean, Boolean, String, String, String, String, String, String, String, String>, IVSearch {

    private static final long serialVersionUID = -172053930;

    /**
     * Setter for <code>DB_ETERNAL.V_SEARCH.KEY</code>. 「key」- 选项主键
     */
    @Override
    public VSearchRecord setKey(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.V_SEARCH.KEY</code>. 「key」- 选项主键
     */
    @Override
    public String getKey() {
        return (String) get(0);
    }

    /**
     * Setter for <code>DB_ETERNAL.V_SEARCH.ENABLED</code>. 「enabled」- search.enabled: 是否启用搜索
     */
    @Override
    public VSearchRecord setEnabled(Boolean value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.V_SEARCH.ENABLED</code>. 「enabled」- search.enabled: 是否启用搜索
     */
    @Override
    public Boolean getEnabled() {
        return (Boolean) get(1);
    }

    /**
     * Setter for <code>DB_ETERNAL.V_SEARCH.ADVANCED</code>. 「advanced」- search.advanced: 是否启用高级搜索
     */
    @Override
    public VSearchRecord setAdvanced(Boolean value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.V_SEARCH.ADVANCED</code>. 「advanced」- search.advanced: 是否启用高级搜索
     */
    @Override
    public Boolean getAdvanced() {
        return (Boolean) get(2);
    }

    /**
     * Setter for <code>DB_ETERNAL.V_SEARCH.OP_REDO</code>. 「opRedo」- search.op.redo: 清除条件按钮提示文字
     */
    @Override
    public VSearchRecord setOpRedo(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.V_SEARCH.OP_REDO</code>. 「opRedo」- search.op.redo: 清除条件按钮提示文字
     */
    @Override
    public String getOpRedo() {
        return (String) get(3);
    }

    /**
     * Setter for <code>DB_ETERNAL.V_SEARCH.OP_ADVANCED</code>. 「opAdvanced」- search.op.advanced: 高级搜索按钮提示文字
     */
    @Override
    public VSearchRecord setOpAdvanced(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.V_SEARCH.OP_ADVANCED</code>. 「opAdvanced」- search.op.advanced: 高级搜索按钮提示文字
     */
    @Override
    public String getOpAdvanced() {
        return (String) get(4);
    }

    /**
     * Setter for <code>DB_ETERNAL.V_SEARCH.CONFIRM_CLEAR</code>. 「confirmClear」- search.confirm.clear: 清除条件提示
     */
    @Override
    public VSearchRecord setConfirmClear(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.V_SEARCH.CONFIRM_CLEAR</code>. 「confirmClear」- search.confirm.clear: 清除条件提示
     */
    @Override
    public String getConfirmClear() {
        return (String) get(5);
    }

    /**
     * Setter for <code>DB_ETERNAL.V_SEARCH.PLACEHOLDER</code>. 「placeholder」- search.placeholder: 搜索框水印文字
     */
    @Override
    public VSearchRecord setPlaceholder(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.V_SEARCH.PLACEHOLDER</code>. 「placeholder」- search.placeholder: 搜索框水印文字
     */
    @Override
    public String getPlaceholder() {
        return (String) get(6);
    }

    /**
     * Setter for <code>DB_ETERNAL.V_SEARCH.COND</code>. 「cond」- search.cond: 搜索条件
     */
    @Override
    public VSearchRecord setCond(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.V_SEARCH.COND</code>. 「cond」- search.cond: 搜索条件
     */
    @Override
    public String getCond() {
        return (String) get(7);
    }

    /**
     * Setter for <code>DB_ETERNAL.V_SEARCH.ADVANCED_WIDTH</code>. 「advancedWidth」- search.advanced.width: 高级搜索窗口宽度
     */
    @Override
    public VSearchRecord setAdvancedWidth(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.V_SEARCH.ADVANCED_WIDTH</code>. 「advancedWidth」- search.advanced.width: 高级搜索窗口宽度
     */
    @Override
    public String getAdvancedWidth() {
        return (String) get(8);
    }

    /**
     * Setter for <code>DB_ETERNAL.V_SEARCH.ADVANCED_TITLE</code>. 「advancedTitle」- search.advanced.title: 高级搜索窗口标题
     */
    @Override
    public VSearchRecord setAdvancedTitle(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.V_SEARCH.ADVANCED_TITLE</code>. 「advancedTitle」- search.advanced.title: 高级搜索窗口标题
     */
    @Override
    public String getAdvancedTitle() {
        return (String) get(9);
    }

    /**
     * Setter for <code>DB_ETERNAL.V_SEARCH.ADVANCED_NOTICE</code>. 「advancedNotice」- search.advanced.notice: 提示信息结构（Alert）
     */
    @Override
    public VSearchRecord setAdvancedNotice(String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.V_SEARCH.ADVANCED_NOTICE</code>. 「advancedNotice」- search.advanced.notice: 提示信息结构（Alert）
     */
    @Override
    public String getAdvancedNotice() {
        return (String) get(10);
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
    // Record11 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row11<String, Boolean, Boolean, String, String, String, String, String, String, String, String> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row11<String, Boolean, Boolean, String, String, String, String, String, String, String, String> valuesRow() {
        return (Row11) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return VSearch.V_SEARCH.KEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field2() {
        return VSearch.V_SEARCH.ENABLED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field3() {
        return VSearch.V_SEARCH.ADVANCED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return VSearch.V_SEARCH.OP_REDO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return VSearch.V_SEARCH.OP_ADVANCED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return VSearch.V_SEARCH.CONFIRM_CLEAR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return VSearch.V_SEARCH.PLACEHOLDER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return VSearch.V_SEARCH.COND;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return VSearch.V_SEARCH.ADVANCED_WIDTH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return VSearch.V_SEARCH.ADVANCED_TITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return VSearch.V_SEARCH.ADVANCED_NOTICE;
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
    public Boolean component2() {
        return getEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component3() {
        return getAdvanced();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getOpRedo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getOpAdvanced();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getConfirmClear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getPlaceholder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getCond();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component9() {
        return getAdvancedWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component10() {
        return getAdvancedTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component11() {
        return getAdvancedNotice();
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
    public Boolean value2() {
        return getEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value3() {
        return getAdvanced();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getOpRedo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getOpAdvanced();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getConfirmClear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getPlaceholder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getCond();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getAdvancedWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getAdvancedTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value11() {
        return getAdvancedNotice();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VSearchRecord value1(String value) {
        setKey(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VSearchRecord value2(Boolean value) {
        setEnabled(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VSearchRecord value3(Boolean value) {
        setAdvanced(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VSearchRecord value4(String value) {
        setOpRedo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VSearchRecord value5(String value) {
        setOpAdvanced(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VSearchRecord value6(String value) {
        setConfirmClear(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VSearchRecord value7(String value) {
        setPlaceholder(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VSearchRecord value8(String value) {
        setCond(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VSearchRecord value9(String value) {
        setAdvancedWidth(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VSearchRecord value10(String value) {
        setAdvancedTitle(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VSearchRecord value11(String value) {
        setAdvancedNotice(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VSearchRecord values(String value1, Boolean value2, Boolean value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IVSearch from) {
        setKey(from.getKey());
        setEnabled(from.getEnabled());
        setAdvanced(from.getAdvanced());
        setOpRedo(from.getOpRedo());
        setOpAdvanced(from.getOpAdvanced());
        setConfirmClear(from.getConfirmClear());
        setPlaceholder(from.getPlaceholder());
        setCond(from.getCond());
        setAdvancedWidth(from.getAdvancedWidth());
        setAdvancedTitle(from.getAdvancedTitle());
        setAdvancedNotice(from.getAdvancedNotice());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IVSearch> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached VSearchRecord
     */
    public VSearchRecord() {
        super(VSearch.V_SEARCH);
    }

    /**
     * Create a detached, initialised VSearchRecord
     */
    public VSearchRecord(String key, Boolean enabled, Boolean advanced, String opRedo, String opAdvanced, String confirmClear, String placeholder, String cond, String advancedWidth, String advancedTitle, String advancedNotice) {
        super(VSearch.V_SEARCH);

        set(0, key);
        set(1, enabled);
        set(2, advanced);
        set(3, opRedo);
        set(4, opAdvanced);
        set(5, confirmClear);
        set(6, placeholder);
        set(7, cond);
        set(8, advancedWidth);
        set(9, advancedTitle);
        set(10, advancedNotice);
    }
}
