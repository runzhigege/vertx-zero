/*
 * This file is generated by jOOQ.
 */
package cn.vertxup.hr.domain.tables;


import cn.vertxup.hr.domain.Db;
import cn.vertxup.hr.domain.Indexes;
import cn.vertxup.hr.domain.Keys;
import cn.vertxup.hr.domain.tables.records.EDeptRecord;
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
public class EDept extends TableImpl<EDeptRecord> {

    /**
     * The reference instance of <code>DB_ETERNAL.E_DEPT</code>
     */
    public static final EDept E_DEPT = new EDept();
    private static final long serialVersionUID = 1508851834;
    /**
     * The column <code>DB_ETERNAL.E_DEPT.KEY</code>. 「key」- 部门主键
     */
    public final TableField<EDeptRecord, String> KEY = createField("KEY", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "「key」- 部门主键");
    /**
     * The column <code>DB_ETERNAL.E_DEPT.NAME</code>. 「name」- 部门名称
     */
    public final TableField<EDeptRecord, String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR(255), this, "「name」- 部门名称");
    /**
     * The column <code>DB_ETERNAL.E_DEPT.CODE</code>. 「code」- 部门编号
     */
    public final TableField<EDeptRecord, String> CODE = createField("CODE", org.jooq.impl.SQLDataType.VARCHAR(255), this, "「code」- 部门编号");
    /**
     * The column <code>DB_ETERNAL.E_DEPT.MANAGER_ID</code>. 「managerId」- 部门经理
     */
    public final TableField<EDeptRecord, String> MANAGER_ID = createField("MANAGER_ID", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「managerId」- 部门经理");
    /**
     * The column <code>DB_ETERNAL.E_DEPT.MANAGER_NAME</code>. 「managerName」- 部门名称
     */
    public final TableField<EDeptRecord, String> MANAGER_NAME = createField("MANAGER_NAME", org.jooq.impl.SQLDataType.VARCHAR(255), this, "「managerName」- 部门名称");
    /**
     * The column <code>DB_ETERNAL.E_DEPT.COMPANY_ID</code>. 「companyId」- 所属公司
     */
    public final TableField<EDeptRecord, String> COMPANY_ID = createField("COMPANY_ID", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「companyId」- 所属公司");
    /**
     * The column <code>DB_ETERNAL.E_DEPT.DEPT_ID</code>. 「deptId」- 父部门
     */
    public final TableField<EDeptRecord, String> DEPT_ID = createField("DEPT_ID", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「deptId」- 父部门");
    /**
     * The column <code>DB_ETERNAL.E_DEPT.COMMENT</code>. 「comment」- 部门备注
     */
    public final TableField<EDeptRecord, String> COMMENT = createField("COMMENT", org.jooq.impl.SQLDataType.CLOB, this, "「comment」- 部门备注");
    /**
     * The column <code>DB_ETERNAL.E_DEPT.METADATA</code>. 「metadata」- 附加配置
     */
    public final TableField<EDeptRecord, String> METADATA = createField("METADATA", org.jooq.impl.SQLDataType.CLOB, this, "「metadata」- 附加配置");
    /**
     * The column <code>DB_ETERNAL.E_DEPT.ACTIVE</code>. 「active」- 是否启用
     */
    public final TableField<EDeptRecord, Boolean> ACTIVE = createField("ACTIVE", org.jooq.impl.SQLDataType.BIT, this, "「active」- 是否启用");
    /**
     * The column <code>DB_ETERNAL.E_DEPT.SIGMA</code>. 「sigma」- 统一标识
     */
    public final TableField<EDeptRecord, String> SIGMA = createField("SIGMA", org.jooq.impl.SQLDataType.VARCHAR(32), this, "「sigma」- 统一标识");
    /**
     * The column <code>DB_ETERNAL.E_DEPT.LANGUAGE</code>. 「language」- 使用的语言
     */
    public final TableField<EDeptRecord, String> LANGUAGE = createField("LANGUAGE", org.jooq.impl.SQLDataType.VARCHAR(8), this, "「language」- 使用的语言");
    /**
     * The column <code>DB_ETERNAL.E_DEPT.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public final TableField<EDeptRecord, LocalDateTime> CREATED_AT = createField("CREATED_AT", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "「createdAt」- 创建时间");
    /**
     * The column <code>DB_ETERNAL.E_DEPT.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public final TableField<EDeptRecord, String> CREATED_BY = createField("CREATED_BY", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「createdBy」- 创建人");
    /**
     * The column <code>DB_ETERNAL.E_DEPT.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public final TableField<EDeptRecord, LocalDateTime> UPDATED_AT = createField("UPDATED_AT", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "「updatedAt」- 更新时间");
    /**
     * The column <code>DB_ETERNAL.E_DEPT.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public final TableField<EDeptRecord, String> UPDATED_BY = createField("UPDATED_BY", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「updatedBy」- 更新人");

    /**
     * Create a <code>DB_ETERNAL.E_DEPT</code> table reference
     */
    public EDept() {
        this(DSL.name("E_DEPT"), null);
    }

    /**
     * Create an aliased <code>DB_ETERNAL.E_DEPT</code> table reference
     */
    public EDept(String alias) {
        this(DSL.name(alias), E_DEPT);
    }

    /**
     * Create an aliased <code>DB_ETERNAL.E_DEPT</code> table reference
     */
    public EDept(Name alias) {
        this(alias, E_DEPT);
    }

    private EDept(Name alias, Table<EDeptRecord> aliased) {
        this(alias, aliased, null);
    }

    private EDept(Name alias, Table<EDeptRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<EDeptRecord> getRecordType() {
        return EDeptRecord.class;
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
        return Arrays.<Index>asList(Indexes.E_DEPT_CODE, Indexes.E_DEPT_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<EDeptRecord> getPrimaryKey() {
        return Keys.KEY_E_DEPT_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<EDeptRecord>> getKeys() {
        return Arrays.<UniqueKey<EDeptRecord>>asList(Keys.KEY_E_DEPT_PRIMARY, Keys.KEY_E_DEPT_CODE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EDept as(String alias) {
        return new EDept(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EDept as(Name alias) {
        return new EDept(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public EDept rename(String name) {
        return new EDept(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public EDept rename(Name name) {
        return new EDept(name, null);
    }
}
