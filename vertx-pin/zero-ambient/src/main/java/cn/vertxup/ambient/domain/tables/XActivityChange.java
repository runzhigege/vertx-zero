/*
 * This file is generated by jOOQ.
 */
package cn.vertxup.ambient.domain.tables;


import cn.vertxup.ambient.domain.Db;
import cn.vertxup.ambient.domain.Indexes;
import cn.vertxup.ambient.domain.Keys;
import cn.vertxup.ambient.domain.tables.records.XActivityChangeRecord;
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
public class XActivityChange extends TableImpl<XActivityChangeRecord> {

    /**
     * The reference instance of <code>DB_ETERNAL.X_ACTIVITY_CHANGE</code>
     */
    public static final XActivityChange X_ACTIVITY_CHANGE = new XActivityChange();
    private static final long serialVersionUID = -2109093327;
    /**
     * The column <code>DB_ETERNAL.X_ACTIVITY_CHANGE.KEY</code>. 「key」- 操作行为主键
     */
    public final TableField<XActivityChangeRecord, String> KEY = createField("KEY", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "「key」- 操作行为主键");
    /**
     * The column <code>DB_ETERNAL.X_ACTIVITY_CHANGE.ACTIVITY_ID</code>. 「activityId」- 关联的操作ID
     */
    public final TableField<XActivityChangeRecord, String> ACTIVITY_ID = createField("ACTIVITY_ID", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「activityId」- 关联的操作ID");
    /**
     * The column <code>DB_ETERNAL.X_ACTIVITY_CHANGE.TYPE</code>. 「type」- 字段变更类型：ADD | DELETE | UPDATE 三种
     */
    public final TableField<XActivityChangeRecord, String> TYPE = createField("TYPE", org.jooq.impl.SQLDataType.VARCHAR(64), this, "「type」- 字段变更类型：ADD | DELETE | UPDATE 三种");
    /**
     * The column <code>DB_ETERNAL.X_ACTIVITY_CHANGE.FIELD_NAME</code>. 「fieldName」- 如果是变更记录则需要生成变更日志
     */
    public final TableField<XActivityChangeRecord, String> FIELD_NAME = createField("FIELD_NAME", org.jooq.impl.SQLDataType.VARCHAR(255), this, "「fieldName」- 如果是变更记录则需要生成变更日志");
    /**
     * The column <code>DB_ETERNAL.X_ACTIVITY_CHANGE.FIELD_TYPE</code>. 「fieldType」- 变更字段的数据类型，直接从模型定义中读取
     */
    public final TableField<XActivityChangeRecord, String> FIELD_TYPE = createField("FIELD_TYPE", org.jooq.impl.SQLDataType.VARCHAR(255), this, "「fieldType」- 变更字段的数据类型，直接从模型定义中读取");
    /**
     * The column <code>DB_ETERNAL.X_ACTIVITY_CHANGE.VALUE_OLD</code>. 「valueOld」- 旧值
     */
    public final TableField<XActivityChangeRecord, String> VALUE_OLD = createField("VALUE_OLD", org.jooq.impl.SQLDataType.CLOB, this, "「valueOld」- 旧值");
    /**
     * The column <code>DB_ETERNAL.X_ACTIVITY_CHANGE.VALUE_NEW</code>. 「valueNew」- 新值
     */
    public final TableField<XActivityChangeRecord, String> VALUE_NEW = createField("VALUE_NEW", org.jooq.impl.SQLDataType.CLOB, this, "「valueNew」- 新值");
    /**
     * The column <code>DB_ETERNAL.X_ACTIVITY_CHANGE.SIGMA</code>. 「sigma」- 用户组绑定的统一标识
     */
    public final TableField<XActivityChangeRecord, String> SIGMA = createField("SIGMA", org.jooq.impl.SQLDataType.VARCHAR(128), this, "「sigma」- 用户组绑定的统一标识");
    /**
     * The column <code>DB_ETERNAL.X_ACTIVITY_CHANGE.LANGUAGE</code>. 「language」- 使用的语言
     */
    public final TableField<XActivityChangeRecord, String> LANGUAGE = createField("LANGUAGE", org.jooq.impl.SQLDataType.VARCHAR(10), this, "「language」- 使用的语言");
    /**
     * The column <code>DB_ETERNAL.X_ACTIVITY_CHANGE.ACTIVE</code>. 「active」- 是否启用
     */
    public final TableField<XActivityChangeRecord, Boolean> ACTIVE = createField("ACTIVE", org.jooq.impl.SQLDataType.BIT, this, "「active」- 是否启用");
    /**
     * The column <code>DB_ETERNAL.X_ACTIVITY_CHANGE.METADATA</code>. 「metadata」- 附加配置数据
     */
    public final TableField<XActivityChangeRecord, String> METADATA = createField("METADATA", org.jooq.impl.SQLDataType.CLOB, this, "「metadata」- 附加配置数据");
    /**
     * The column <code>DB_ETERNAL.X_ACTIVITY_CHANGE.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public final TableField<XActivityChangeRecord, LocalDateTime> CREATED_AT = createField("CREATED_AT", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "「createdAt」- 创建时间");
    /**
     * The column <code>DB_ETERNAL.X_ACTIVITY_CHANGE.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public final TableField<XActivityChangeRecord, String> CREATED_BY = createField("CREATED_BY", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「createdBy」- 创建人");
    /**
     * The column <code>DB_ETERNAL.X_ACTIVITY_CHANGE.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public final TableField<XActivityChangeRecord, LocalDateTime> UPDATED_AT = createField("UPDATED_AT", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "「updatedAt」- 更新时间");
    /**
     * The column <code>DB_ETERNAL.X_ACTIVITY_CHANGE.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public final TableField<XActivityChangeRecord, String> UPDATED_BY = createField("UPDATED_BY", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「updatedBy」- 更新人");

    /**
     * Create a <code>DB_ETERNAL.X_ACTIVITY_CHANGE</code> table reference
     */
    public XActivityChange() {
        this(DSL.name("X_ACTIVITY_CHANGE"), null);
    }

    /**
     * Create an aliased <code>DB_ETERNAL.X_ACTIVITY_CHANGE</code> table reference
     */
    public XActivityChange(String alias) {
        this(DSL.name(alias), X_ACTIVITY_CHANGE);
    }

    /**
     * Create an aliased <code>DB_ETERNAL.X_ACTIVITY_CHANGE</code> table reference
     */
    public XActivityChange(Name alias) {
        this(alias, X_ACTIVITY_CHANGE);
    }

    private XActivityChange(Name alias, Table<XActivityChangeRecord> aliased) {
        this(alias, aliased, null);
    }

    private XActivityChange(Name alias, Table<XActivityChangeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<XActivityChangeRecord> getRecordType() {
        return XActivityChangeRecord.class;
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
        return Arrays.<Index>asList(Indexes.X_ACTIVITY_CHANGE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<XActivityChangeRecord> getPrimaryKey() {
        return Keys.KEY_X_ACTIVITY_CHANGE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<XActivityChangeRecord>> getKeys() {
        return Arrays.<UniqueKey<XActivityChangeRecord>>asList(Keys.KEY_X_ACTIVITY_CHANGE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XActivityChange as(String alias) {
        return new XActivityChange(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XActivityChange as(Name alias) {
        return new XActivityChange(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public XActivityChange rename(String name) {
        return new XActivityChange(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public XActivityChange rename(Name name) {
        return new XActivityChange(name, null);
    }
}
