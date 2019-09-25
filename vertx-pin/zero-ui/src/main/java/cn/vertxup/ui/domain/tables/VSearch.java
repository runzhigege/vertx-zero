/*
 * This file is generated by jOOQ.
 */
package cn.vertxup.ui.domain.tables;


import cn.vertxup.ui.domain.Db;
import cn.vertxup.ui.domain.Indexes;
import cn.vertxup.ui.domain.Keys;
import cn.vertxup.ui.domain.tables.records.VSearchRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import javax.annotation.Generated;
import java.util.Arrays;
import java.util.List;


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
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class VSearch extends TableImpl<VSearchRecord> {

    /**
     * The reference instance of <code>DB_ETERNAL.V_SEARCH</code>
     */
    public static final VSearch V_SEARCH = new VSearch();
    private static final long serialVersionUID = 1640653563;
    /**
     * The column <code>DB_ETERNAL.V_SEARCH.KEY</code>. 「key」- 选项主键
     */
    public final TableField<VSearchRecord, String> KEY = createField("KEY", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "「key」- 选项主键");
    /**
     * The column <code>DB_ETERNAL.V_SEARCH.ENABLED</code>. 「enabled」- search.enabled: 是否启用搜索
     */
    public final TableField<VSearchRecord, Boolean> ENABLED = createField("ENABLED", org.jooq.impl.SQLDataType.BIT, this, "「enabled」- search.enabled: 是否启用搜索");
    /**
     * The column <code>DB_ETERNAL.V_SEARCH.ADVANCED</code>. 「advanced」- search.advanced: 是否启用高级搜索
     */
    public final TableField<VSearchRecord, Boolean> ADVANCED = createField("ADVANCED", org.jooq.impl.SQLDataType.BIT, this, "「advanced」- search.advanced: 是否启用高级搜索");
    /**
     * The column <code>DB_ETERNAL.V_SEARCH.OP_REDO</code>. 「opRedo」- search.op.redo: 清除条件按钮提示文字
     */
    public final TableField<VSearchRecord, String> OP_REDO = createField("OP_REDO", org.jooq.impl.SQLDataType.VARCHAR(64), this, "「opRedo」- search.op.redo: 清除条件按钮提示文字");
    /**
     * The column <code>DB_ETERNAL.V_SEARCH.OP_ADVANCED</code>. 「opAdvanced」- search.op.advanced: 高级搜索按钮提示文字
     */
    public final TableField<VSearchRecord, String> OP_ADVANCED = createField("OP_ADVANCED", org.jooq.impl.SQLDataType.VARCHAR(64), this, "「opAdvanced」- search.op.advanced: 高级搜索按钮提示文字");
    /**
     * The column <code>DB_ETERNAL.V_SEARCH.CONFIRM_CLEAR</code>. 「confirmClear」- search.confirm.clear: 清除条件提示
     */
    public final TableField<VSearchRecord, String> CONFIRM_CLEAR = createField("CONFIRM_CLEAR", org.jooq.impl.SQLDataType.VARCHAR(255), this, "「confirmClear」- search.confirm.clear: 清除条件提示");
    /**
     * The column <code>DB_ETERNAL.V_SEARCH.PLACEHOLDER</code>. 「placeholder」- search.placeholder: 搜索框水印文字
     */
    public final TableField<VSearchRecord, String> PLACEHOLDER = createField("PLACEHOLDER", org.jooq.impl.SQLDataType.VARCHAR(255), this, "「placeholder」- search.placeholder: 搜索框水印文字");
    /**
     * The column <code>DB_ETERNAL.V_SEARCH.COND</code>. 「cond」- search.cond: 搜索条件
     */
    public final TableField<VSearchRecord, String> COND = createField("COND", org.jooq.impl.SQLDataType.CLOB, this, "「cond」- search.cond: 搜索条件");
    /**
     * The column <code>DB_ETERNAL.V_SEARCH.ADVANCED_WIDTH</code>. 「advancedWidth」- search.advanced.width: 高级搜索窗口宽度
     */
    public final TableField<VSearchRecord, String> ADVANCED_WIDTH = createField("ADVANCED_WIDTH", org.jooq.impl.SQLDataType.VARCHAR(100), this, "「advancedWidth」- search.advanced.width: 高级搜索窗口宽度");
    /**
     * The column <code>DB_ETERNAL.V_SEARCH.ADVANCED_TITLE</code>. 「advancedTitle」- search.advanced.title: 高级搜索窗口标题
     */
    public final TableField<VSearchRecord, String> ADVANCED_TITLE = createField("ADVANCED_TITLE", org.jooq.impl.SQLDataType.VARCHAR(128), this, "「advancedTitle」- search.advanced.title: 高级搜索窗口标题");
    /**
     * The column <code>DB_ETERNAL.V_SEARCH.ADVANCED_NOTICE</code>. 「advancedNotice」- search.advanced.notice: 提示信息结构（Alert）
     */
    public final TableField<VSearchRecord, String> ADVANCED_NOTICE = createField("ADVANCED_NOTICE", org.jooq.impl.SQLDataType.CLOB, this, "「advancedNotice」- search.advanced.notice: 提示信息结构（Alert）");

    /**
     * Create a <code>DB_ETERNAL.V_SEARCH</code> table reference
     */
    public VSearch() {
        this(DSL.name("V_SEARCH"), null);
    }

    /**
     * Create an aliased <code>DB_ETERNAL.V_SEARCH</code> table reference
     */
    public VSearch(String alias) {
        this(DSL.name(alias), V_SEARCH);
    }

    /**
     * Create an aliased <code>DB_ETERNAL.V_SEARCH</code> table reference
     */
    public VSearch(Name alias) {
        this(alias, V_SEARCH);
    }

    private VSearch(Name alias, Table<VSearchRecord> aliased) {
        this(alias, aliased, null);
    }

    private VSearch(Name alias, Table<VSearchRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VSearchRecord> getRecordType() {
        return VSearchRecord.class;
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
        return Arrays.<Index>asList(Indexes.V_SEARCH_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<VSearchRecord> getPrimaryKey() {
        return Keys.KEY_V_SEARCH_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<VSearchRecord>> getKeys() {
        return Arrays.<UniqueKey<VSearchRecord>>asList(Keys.KEY_V_SEARCH_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VSearch as(String alias) {
        return new VSearch(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VSearch as(Name alias) {
        return new VSearch(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public VSearch rename(String name) {
        return new VSearch(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public VSearch rename(Name name) {
        return new VSearch(name, null);
    }
}
