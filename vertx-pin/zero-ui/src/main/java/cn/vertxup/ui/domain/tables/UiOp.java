/*
 * This file is generated by jOOQ.
 */
package cn.vertxup.ui.domain.tables;


import cn.vertxup.ui.domain.Db;
import cn.vertxup.ui.domain.Indexes;
import cn.vertxup.ui.domain.Keys;
import cn.vertxup.ui.domain.tables.records.UiOpRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import javax.annotation.Generated;
import java.time.LocalDateTime;
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
public class UiOp extends TableImpl<UiOpRecord> {

    /**
     * The reference instance of <code>DB_ETERNAL.UI_OP</code>
     */
    public static final UiOp UI_OP = new UiOp();
    private static final long serialVersionUID = 312345051;
    /**
     * The column <code>DB_ETERNAL.UI_OP.KEY</code>. 「key」- 操作主键
     */
    public final TableField<UiOpRecord, String> KEY = createField("KEY", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "「key」- 操作主键");
    /**
     * The column <code>DB_ETERNAL.UI_OP.ACTION</code>. 「action」- S_ACTION中的code（权限检查专用）
     */
    public final TableField<UiOpRecord, String> ACTION = createField("ACTION", org.jooq.impl.SQLDataType.VARCHAR(255), this, "「action」- S_ACTION中的code（权限检查专用）");
    /**
     * The column <code>DB_ETERNAL.UI_OP.TEXT</code>. 「text」- 该操作上的文字信息
     */
    public final TableField<UiOpRecord, String> TEXT = createField("TEXT", org.jooq.impl.SQLDataType.VARCHAR(255), this, "「text」- 该操作上的文字信息");
    /**
     * The column <code>DB_ETERNAL.UI_OP.EVENT</code>. 「event」- 操作中的 event 事件名称
     */
    public final TableField<UiOpRecord, String> EVENT = createField("EVENT", org.jooq.impl.SQLDataType.VARCHAR(32), this, "「event」- 操作中的 event 事件名称");
    /**
     * The column <code>DB_ETERNAL.UI_OP.CLIENT_KEY</code>. 「clientKey」- 一般是Html中对应的key信息，如 $opSave
     */
    public final TableField<UiOpRecord, String> CLIENT_KEY = createField("CLIENT_KEY", org.jooq.impl.SQLDataType.VARCHAR(32), this, "「clientKey」- 一般是Html中对应的key信息，如 $opSave");
    /**
     * The column <code>DB_ETERNAL.UI_OP.CLIENT_ID</code>. 「clientId」- 没有特殊情况，clientId = clientKey
     */
    public final TableField<UiOpRecord, String> CLIENT_ID = createField("CLIENT_ID", org.jooq.impl.SQLDataType.VARCHAR(32), this, "「clientId」- 没有特殊情况，clientId = clientKey");
    /**
     * The column <code>DB_ETERNAL.UI_OP.CONFIG</code>. 「config」- 该按钮操作对应的配置数据信息, icon, type
     */
    public final TableField<UiOpRecord, String> CONFIG = createField("CONFIG", org.jooq.impl.SQLDataType.CLOB, this, "「config」- 该按钮操作对应的配置数据信息, icon, type");
    /**
     * The column <code>DB_ETERNAL.UI_OP.PLUGIN</code>. 「plugin」- 该按钮中的插件，如 tooltip，component等
     */
    public final TableField<UiOpRecord, String> PLUGIN = createField("PLUGIN", org.jooq.impl.SQLDataType.CLOB, this, "「plugin」- 该按钮中的插件，如 tooltip，component等");
    /**
     * The column <code>DB_ETERNAL.UI_OP.CONTROL_ID</code>. 「controlId」- 挂载专用的ID
     */
    public final TableField<UiOpRecord, String> CONTROL_ID = createField("CONTROL_ID", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「controlId」- 挂载专用的ID");
    /**
     * The column <code>DB_ETERNAL.UI_OP.ACTIVE</code>. 「active」- 是否启用
     */
    public final TableField<UiOpRecord, Boolean> ACTIVE = createField("ACTIVE", org.jooq.impl.SQLDataType.BIT, this, "「active」- 是否启用");
    /**
     * The column <code>DB_ETERNAL.UI_OP.SIGMA</code>. 「sigma」- 统一标识
     */
    public final TableField<UiOpRecord, String> SIGMA = createField("SIGMA", org.jooq.impl.SQLDataType.VARCHAR(32), this, "「sigma」- 统一标识");
    /**
     * The column <code>DB_ETERNAL.UI_OP.METADATA</code>. 「metadata」- 附加配置
     */
    public final TableField<UiOpRecord, String> METADATA = createField("METADATA", org.jooq.impl.SQLDataType.CLOB, this, "「metadata」- 附加配置");
    /**
     * The column <code>DB_ETERNAL.UI_OP.LANGUAGE</code>. 「language」- 使用的语言
     */
    public final TableField<UiOpRecord, String> LANGUAGE = createField("LANGUAGE", org.jooq.impl.SQLDataType.VARCHAR(8), this, "「language」- 使用的语言");
    /**
     * The column <code>DB_ETERNAL.UI_OP.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public final TableField<UiOpRecord, LocalDateTime> CREATED_AT = createField("CREATED_AT", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "「createdAt」- 创建时间");
    /**
     * The column <code>DB_ETERNAL.UI_OP.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public final TableField<UiOpRecord, String> CREATED_BY = createField("CREATED_BY", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「createdBy」- 创建人");
    /**
     * The column <code>DB_ETERNAL.UI_OP.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public final TableField<UiOpRecord, LocalDateTime> UPDATED_AT = createField("UPDATED_AT", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "「updatedAt」- 更新时间");
    /**
     * The column <code>DB_ETERNAL.UI_OP.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public final TableField<UiOpRecord, String> UPDATED_BY = createField("UPDATED_BY", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「updatedBy」- 更新人");

    /**
     * Create a <code>DB_ETERNAL.UI_OP</code> table reference
     */
    public UiOp() {
        this(DSL.name("UI_OP"), null);
    }

    /**
     * Create an aliased <code>DB_ETERNAL.UI_OP</code> table reference
     */
    public UiOp(String alias) {
        this(DSL.name(alias), UI_OP);
    }

    /**
     * Create an aliased <code>DB_ETERNAL.UI_OP</code> table reference
     */
    public UiOp(Name alias) {
        this(alias, UI_OP);
    }

    private UiOp(Name alias, Table<UiOpRecord> aliased) {
        this(alias, aliased, null);
    }

    private UiOp(Name alias, Table<UiOpRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UiOpRecord> getRecordType() {
        return UiOpRecord.class;
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
        return Arrays.<Index>asList(Indexes.UI_OP_CONTROL_ID, Indexes.UI_OP_CONTROL_ID_2, Indexes.UI_OP_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<UiOpRecord> getPrimaryKey() {
        return Keys.KEY_UI_OP_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<UiOpRecord>> getKeys() {
        return Arrays.<UniqueKey<UiOpRecord>>asList(Keys.KEY_UI_OP_PRIMARY, Keys.KEY_UI_OP_CONTROL_ID_2, Keys.KEY_UI_OP_CONTROL_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiOp as(String alias) {
        return new UiOp(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiOp as(Name alias) {
        return new UiOp(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public UiOp rename(String name) {
        return new UiOp(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UiOp rename(Name name) {
        return new UiOp(name, null);
    }
}
