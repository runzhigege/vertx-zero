/*
 * This file is generated by jOOQ.
 */
package cn.vertxup.domain.tables;


import cn.vertxup.domain.Db;
import cn.vertxup.domain.Indexes;
import cn.vertxup.domain.Keys;
import cn.vertxup.domain.tables.records.RUserGroupRecord;
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
public class RUserGroup extends TableImpl<RUserGroupRecord> {

    /**
     * The reference instance of <code>DB_ETERNAL.R_USER_GROUP</code>
     */
    public static final RUserGroup R_USER_GROUP = new RUserGroup();
    private static final long serialVersionUID = 1170270290;
    /**
     * The column <code>DB_ETERNAL.R_USER_GROUP.GROUP_ID</code>. 「groupId」- 关联组ID
     */
    public final TableField<RUserGroupRecord, String> GROUP_ID = createField("GROUP_ID", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "「groupId」- 关联组ID");
    /**
     * The column <code>DB_ETERNAL.R_USER_GROUP.USER_ID</code>. 「userId」- 关联用户ID
     */
    public final TableField<RUserGroupRecord, String> USER_ID = createField("USER_ID", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "「userId」- 关联用户ID");
    /**
     * The column <code>DB_ETERNAL.R_USER_GROUP.PRIORITY</code>. 「priority」- 组优先级
     */
    public final TableField<RUserGroupRecord, Integer> PRIORITY = createField("PRIORITY", org.jooq.impl.SQLDataType.INTEGER, this, "「priority」- 组优先级");

    /**
     * Create a <code>DB_ETERNAL.R_USER_GROUP</code> table reference
     */
    public RUserGroup() {
        this(DSL.name("R_USER_GROUP"), null);
    }

    /**
     * Create an aliased <code>DB_ETERNAL.R_USER_GROUP</code> table reference
     */
    public RUserGroup(String alias) {
        this(DSL.name(alias), R_USER_GROUP);
    }

    /**
     * Create an aliased <code>DB_ETERNAL.R_USER_GROUP</code> table reference
     */
    public RUserGroup(Name alias) {
        this(alias, R_USER_GROUP);
    }

    private RUserGroup(Name alias, Table<RUserGroupRecord> aliased) {
        this(alias, aliased, null);
    }

    private RUserGroup(Name alias, Table<RUserGroupRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RUserGroupRecord> getRecordType() {
        return RUserGroupRecord.class;
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
        return Arrays.<Index>asList(Indexes.R_USER_GROUP_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RUserGroupRecord> getPrimaryKey() {
        return Keys.KEY_R_USER_GROUP_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RUserGroupRecord>> getKeys() {
        return Arrays.<UniqueKey<RUserGroupRecord>>asList(Keys.KEY_R_USER_GROUP_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RUserGroup as(String alias) {
        return new RUserGroup(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RUserGroup as(Name alias) {
        return new RUserGroup(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public RUserGroup rename(String name) {
        return new RUserGroup(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RUserGroup rename(Name name) {
        return new RUserGroup(name, null);
    }
}
