/*
 * This file is generated by jOOQ.
 */
package cn.vertxup.domain.tables;


import cn.vertxup.domain.Db;
import cn.vertxup.domain.Indexes;
import cn.vertxup.domain.Keys;
import cn.vertxup.domain.tables.records.RResourceMatrixRecord;
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
public class RResourceMatrix extends TableImpl<RResourceMatrixRecord> {

    /**
     * The reference instance of <code>DB_ORIGIN_X.R_RESOURCE_MATRIX</code>
     */
    public static final RResourceMatrix R_RESOURCE_MATRIX = new RResourceMatrix();
    private static final long serialVersionUID = 1216530983;
    /**
     * The column <code>DB_ORIGIN_X.R_RESOURCE_MATRIX.KEY</code>. 「key」- 限定记录ID
     */
    public final TableField<RResourceMatrixRecord, String> KEY = createField("KEY", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "「key」- 限定记录ID");
    /**
     * The column <code>DB_ORIGIN_X.R_RESOURCE_MATRIX.USER_ID</code>. 「userId」- 限定用户ID
     */
    public final TableField<RResourceMatrixRecord, String> USER_ID = createField("USER_ID", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「userId」- 限定用户ID");
    /**
     * The column <code>DB_ORIGIN_X.R_RESOURCE_MATRIX.RESOURCE_ID</code>. 「resourceId」- 关联资源ID
     */
    public final TableField<RResourceMatrixRecord, String> RESOURCE_ID = createField("RESOURCE_ID", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「resourceId」- 关联资源ID");
    /**
     * The column <code>DB_ORIGIN_X.R_RESOURCE_MATRIX.PROJECTION</code>. 「projection」- 该资源的列定义（单用户处理）
     */
    public final TableField<RResourceMatrixRecord, String> PROJECTION = createField("PROJECTION", org.jooq.impl.SQLDataType.CLOB, this, "「projection」- 该资源的列定义（单用户处理）");
    /**
     * The column <code>DB_ORIGIN_X.R_RESOURCE_MATRIX.QUERY</code>. 「query」- 该资源的行查询（单用户处理）
     */
    public final TableField<RResourceMatrixRecord, String> QUERY = createField("QUERY", org.jooq.impl.SQLDataType.CLOB, this, "「query」- 该资源的行查询（单用户处理）");
    /**
     * The column <code>DB_ORIGIN_X.R_RESOURCE_MATRIX.IDS</code>. 「ids」- 该资源针对用户或角色的过滤处理
     */
    public final TableField<RResourceMatrixRecord, String> IDS = createField("IDS", org.jooq.impl.SQLDataType.CLOB, this, "「ids」- 该资源针对用户或角色的过滤处理");
    /**
     * The column <code>DB_ORIGIN_X.R_RESOURCE_MATRIX.EXPIRED</code>. 「expired」- 资源过期时间（动态授权）
     */
    public final TableField<RResourceMatrixRecord, LocalDateTime> EXPIRED = createField("EXPIRED", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "「expired」- 资源过期时间（动态授权）");

    /**
     * Create a <code>DB_ORIGIN_X.R_RESOURCE_MATRIX</code> table reference
     */
    public RResourceMatrix() {
        this(DSL.name("R_RESOURCE_MATRIX"), null);
    }

    /**
     * Create an aliased <code>DB_ORIGIN_X.R_RESOURCE_MATRIX</code> table reference
     */
    public RResourceMatrix(String alias) {
        this(DSL.name(alias), R_RESOURCE_MATRIX);
    }

    /**
     * Create an aliased <code>DB_ORIGIN_X.R_RESOURCE_MATRIX</code> table reference
     */
    public RResourceMatrix(Name alias) {
        this(alias, R_RESOURCE_MATRIX);
    }

    private RResourceMatrix(Name alias, Table<RResourceMatrixRecord> aliased) {
        this(alias, aliased, null);
    }

    private RResourceMatrix(Name alias, Table<RResourceMatrixRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RResourceMatrixRecord> getRecordType() {
        return RResourceMatrixRecord.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Db.DB_ORIGIN_X;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.R_RESOURCE_MATRIX_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RResourceMatrixRecord> getPrimaryKey() {
        return Keys.KEY_R_RESOURCE_MATRIX_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RResourceMatrixRecord>> getKeys() {
        return Arrays.<UniqueKey<RResourceMatrixRecord>>asList(Keys.KEY_R_RESOURCE_MATRIX_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RResourceMatrix as(String alias) {
        return new RResourceMatrix(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RResourceMatrix as(Name alias) {
        return new RResourceMatrix(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public RResourceMatrix rename(String name) {
        return new RResourceMatrix(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RResourceMatrix rename(Name name) {
        return new RResourceMatrix(name, null);
    }
}
