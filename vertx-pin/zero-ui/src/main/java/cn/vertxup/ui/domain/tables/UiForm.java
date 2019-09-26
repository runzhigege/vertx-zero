/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ui.domain.tables;


import cn.vertxup.ui.domain.Db;
import cn.vertxup.ui.domain.Indexes;
import cn.vertxup.ui.domain.Keys;
import cn.vertxup.ui.domain.tables.records.UiFormRecord;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class UiForm extends TableImpl<UiFormRecord> {

    private static final long serialVersionUID = 545170438;

    /**
     * The reference instance of <code>DB_ETERNAL.UI_FORM</code>
     */
    public static final UiForm UI_FORM = new UiForm();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UiFormRecord> getRecordType() {
        return UiFormRecord.class;
    }

    /**
     * The column <code>DB_ETERNAL.UI_FORM.KEY</code>. 「key」- 主键
     */
    public final TableField<UiFormRecord, String> KEY = createField("KEY", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "「key」- 主键");

    /**
     * The column <code>DB_ETERNAL.UI_FORM.NAME</code>. 「name」- 表单名称
     */
    public final TableField<UiFormRecord, String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR(255), this, "「name」- 表单名称");

    /**
     * The column <code>DB_ETERNAL.UI_FORM.CODE</code>. 「code」- 表单系统编码
     */
    public final TableField<UiFormRecord, String> CODE = createField("CODE", org.jooq.impl.SQLDataType.VARCHAR(255), this, "「code」- 表单系统编码");

    /**
     * The column <code>DB_ETERNAL.UI_FORM.WINDOW</code>. 「window」- window, Form对应的窗口配置
     */
    public final TableField<UiFormRecord, BigDecimal> WINDOW = createField("WINDOW", org.jooq.impl.SQLDataType.DECIMAL(10, 2), this, "「window」- window, Form对应的窗口配置");

    /**
     * The column <code>DB_ETERNAL.UI_FORM.COLUMNS</code>. 「columns」- columns, Form对应的配置
     */
    public final TableField<UiFormRecord, Integer> COLUMNS = createField("COLUMNS", org.jooq.impl.SQLDataType.INTEGER, this, "「columns」- columns, Form对应的配置");

    /**
     * The column <code>DB_ETERNAL.UI_FORM.HIDDEN</code>. 「hidden」- 隐藏字段专用配置
     */
    public final TableField<UiFormRecord, String> HIDDEN = createField("HIDDEN", org.jooq.impl.SQLDataType.CLOB, this, "「hidden」- 隐藏字段专用配置");

    /**
     * The column <code>DB_ETERNAL.UI_FORM.ROW</code>. 「rowConfig/rowClass」- 行专用配置
     */
    public final TableField<UiFormRecord, String> ROW = createField("ROW", org.jooq.impl.SQLDataType.CLOB, this, "「rowConfig/rowClass」- 行专用配置");

    /**
     * The column <code>DB_ETERNAL.UI_FORM.ACTIVE</code>. 「active」- 是否启用
     */
    public final TableField<UiFormRecord, Boolean> ACTIVE = createField("ACTIVE", org.jooq.impl.SQLDataType.BIT, this, "「active」- 是否启用");

    /**
     * The column <code>DB_ETERNAL.UI_FORM.SIGMA</code>. 「sigma」- 统一标识
     */
    public final TableField<UiFormRecord, String> SIGMA = createField("SIGMA", org.jooq.impl.SQLDataType.VARCHAR(32), this, "「sigma」- 统一标识");

    /**
     * The column <code>DB_ETERNAL.UI_FORM.METADATA</code>. 「metadata」- 附加配置
     */
    public final TableField<UiFormRecord, String> METADATA = createField("METADATA", org.jooq.impl.SQLDataType.CLOB, this, "「metadata」- 附加配置");

    /**
     * The column <code>DB_ETERNAL.UI_FORM.LANGUAGE</code>. 「language」- 使用的语言
     */
    public final TableField<UiFormRecord, String> LANGUAGE = createField("LANGUAGE", org.jooq.impl.SQLDataType.VARCHAR(8), this, "「language」- 使用的语言");

    /**
     * The column <code>DB_ETERNAL.UI_FORM.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public final TableField<UiFormRecord, LocalDateTime> CREATED_AT = createField("CREATED_AT", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "「createdAt」- 创建时间");

    /**
     * The column <code>DB_ETERNAL.UI_FORM.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public final TableField<UiFormRecord, String> CREATED_BY = createField("CREATED_BY", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「createdBy」- 创建人");

    /**
     * The column <code>DB_ETERNAL.UI_FORM.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public final TableField<UiFormRecord, LocalDateTime> UPDATED_AT = createField("UPDATED_AT", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "「updatedAt」- 更新时间");

    /**
     * The column <code>DB_ETERNAL.UI_FORM.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public final TableField<UiFormRecord, String> UPDATED_BY = createField("UPDATED_BY", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「updatedBy」- 更新人");

    /**
     * Create a <code>DB_ETERNAL.UI_FORM</code> table reference
     */
    public UiForm() {
        this(DSL.name("UI_FORM"), null);
    }

    /**
     * Create an aliased <code>DB_ETERNAL.UI_FORM</code> table reference
     */
    public UiForm(String alias) {
        this(DSL.name(alias), UI_FORM);
    }

    /**
     * Create an aliased <code>DB_ETERNAL.UI_FORM</code> table reference
     */
    public UiForm(Name alias) {
        this(alias, UI_FORM);
    }

    private UiForm(Name alias, Table<UiFormRecord> aliased) {
        this(alias, aliased, null);
    }

    private UiForm(Name alias, Table<UiFormRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Db.DB_ETERNAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.UI_FORM_CODE, Indexes.UI_FORM_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<UiFormRecord> getPrimaryKey() {
        return Keys.KEY_UI_FORM_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<UiFormRecord>> getKeys() {
        return Arrays.<UniqueKey<UiFormRecord>>asList(Keys.KEY_UI_FORM_PRIMARY, Keys.KEY_UI_FORM_CODE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiForm as(String alias) {
        return new UiForm(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiForm as(Name alias) {
        return new UiForm(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public UiForm rename(String name) {
        return new UiForm(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UiForm rename(Name name) {
        return new UiForm(name, null);
    }
}
