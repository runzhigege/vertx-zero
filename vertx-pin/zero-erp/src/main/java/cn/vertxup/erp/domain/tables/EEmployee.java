/*
 * This file is generated by jOOQ.
 */
package cn.vertxup.erp.domain.tables;


import cn.vertxup.erp.domain.Db;
import cn.vertxup.erp.domain.Indexes;
import cn.vertxup.erp.domain.Keys;
import cn.vertxup.erp.domain.tables.records.EEmployeeRecord;
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
public class EEmployee extends TableImpl<EEmployeeRecord> {

    /**
     * The reference instance of <code>DB_ETERNAL.E_EMPLOYEE</code>
     */
    public static final EEmployee E_EMPLOYEE = new EEmployee();
    private static final long serialVersionUID = -1801268349;
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.KEY</code>. 「key」- 员工主键
     */
    public final TableField<EEmployeeRecord, String> KEY = createField("KEY", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "「key」- 员工主键");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.COMPANY_ID</code>. 「companyId」- 所属公司
     */
    public final TableField<EEmployeeRecord, String> COMPANY_ID = createField("COMPANY_ID", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「companyId」- 所属公司");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.DEPT_ID</code>. 「deptId」- 所属部门
     */
    public final TableField<EEmployeeRecord, String> DEPT_ID = createField("DEPT_ID", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「deptId」- 所属部门");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.TEAM_ID</code>. 「teamId」- 所属组
     */
    public final TableField<EEmployeeRecord, String> TEAM_ID = createField("TEAM_ID", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「teamId」- 所属组");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.IDENTITY_ID</code>. 「identityId」- 关联档案
     */
    public final TableField<EEmployeeRecord, String> IDENTITY_ID = createField("IDENTITY_ID", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「identityId」- 关联档案");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.VICE_NAME</code>. 「viceName」- 员工姓名
     */
    public final TableField<EEmployeeRecord, String> VICE_NAME = createField("VICE_NAME", org.jooq.impl.SQLDataType.VARCHAR(255), this, "「viceName」- 员工姓名");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.VICE_EMAIL</code>. 「viceEmail」- 员工邮箱
     */
    public final TableField<EEmployeeRecord, String> VICE_EMAIL = createField("VICE_EMAIL", org.jooq.impl.SQLDataType.VARCHAR(255), this, "「viceEmail」- 员工邮箱");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.VICE_MOBILE</code>. 「viceMobile」- 员工手机
     */
    public final TableField<EEmployeeRecord, String> VICE_MOBILE = createField("VICE_MOBILE", org.jooq.impl.SQLDataType.VARCHAR(255), this, "「viceMobile」- 员工手机");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.WORK_NUMBER</code>. 「workNumber」- 工号
     */
    public final TableField<EEmployeeRecord, String> WORK_NUMBER = createField("WORK_NUMBER", org.jooq.impl.SQLDataType.VARCHAR(255), this, "「workNumber」- 工号");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.WORK_TITLE</code>. 「workTitle」- 头衔
     */
    public final TableField<EEmployeeRecord, String> WORK_TITLE = createField("WORK_TITLE", org.jooq.impl.SQLDataType.VARCHAR(255), this, "「workTitle」- 头衔");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.WORK_LOCATION</code>. 「workLocation」- 办公地点
     */
    public final TableField<EEmployeeRecord, String> WORK_LOCATION = createField("WORK_LOCATION", org.jooq.impl.SQLDataType.CLOB, this, "「workLocation」- 办公地点");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.WORK_PHONE</code>. 「workPhone」- 办公电话
     */
    public final TableField<EEmployeeRecord, String> WORK_PHONE = createField("WORK_PHONE", org.jooq.impl.SQLDataType.VARCHAR(20), this, "「workPhone」- 办公电话");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.WORK_EXTENSION</code>. 「workExtension」- 分机号
     */
    public final TableField<EEmployeeRecord, String> WORK_EXTENSION = createField("WORK_EXTENSION", org.jooq.impl.SQLDataType.VARCHAR(20), this, "「workExtension」- 分机号");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.TYPE</code>. 「type」- 员工分类
     */
    public final TableField<EEmployeeRecord, String> TYPE = createField("TYPE", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「type」- 员工分类");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.METADATA</code>. 「metadata」- 附加配置
     */
    public final TableField<EEmployeeRecord, String> METADATA = createField("METADATA", org.jooq.impl.SQLDataType.CLOB, this, "「metadata」- 附加配置");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.ACTIVE</code>. 「active」- 是否启用
     */
    public final TableField<EEmployeeRecord, Boolean> ACTIVE = createField("ACTIVE", org.jooq.impl.SQLDataType.BIT, this, "「active」- 是否启用");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.SIGMA</code>. 「sigma」- 统一标识（公司所属应用）
     */
    public final TableField<EEmployeeRecord, String> SIGMA = createField("SIGMA", org.jooq.impl.SQLDataType.VARCHAR(32), this, "「sigma」- 统一标识（公司所属应用）");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.LANGUAGE</code>. 「language」- 使用的语言
     */
    public final TableField<EEmployeeRecord, String> LANGUAGE = createField("LANGUAGE", org.jooq.impl.SQLDataType.VARCHAR(8), this, "「language」- 使用的语言");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public final TableField<EEmployeeRecord, LocalDateTime> CREATED_AT = createField("CREATED_AT", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "「createdAt」- 创建时间");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public final TableField<EEmployeeRecord, String> CREATED_BY = createField("CREATED_BY", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「createdBy」- 创建人");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public final TableField<EEmployeeRecord, LocalDateTime> UPDATED_AT = createField("UPDATED_AT", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "「updatedAt」- 更新时间");
    /**
     * The column <code>DB_ETERNAL.E_EMPLOYEE.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public final TableField<EEmployeeRecord, String> UPDATED_BY = createField("UPDATED_BY", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「updatedBy」- 更新人");

    /**
     * Create a <code>DB_ETERNAL.E_EMPLOYEE</code> table reference
     */
    public EEmployee() {
        this(DSL.name("E_EMPLOYEE"), null);
    }

    /**
     * Create an aliased <code>DB_ETERNAL.E_EMPLOYEE</code> table reference
     */
    public EEmployee(String alias) {
        this(DSL.name(alias), E_EMPLOYEE);
    }

    /**
     * Create an aliased <code>DB_ETERNAL.E_EMPLOYEE</code> table reference
     */
    public EEmployee(Name alias) {
        this(alias, E_EMPLOYEE);
    }

    private EEmployee(Name alias, Table<EEmployeeRecord> aliased) {
        this(alias, aliased, null);
    }

    private EEmployee(Name alias, Table<EEmployeeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<EEmployeeRecord> getRecordType() {
        return EEmployeeRecord.class;
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
        return Arrays.<Index>asList(Indexes.E_EMPLOYEE_PRIMARY, Indexes.E_EMPLOYEE_WORK_NUMBER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<EEmployeeRecord> getPrimaryKey() {
        return Keys.KEY_E_EMPLOYEE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<EEmployeeRecord>> getKeys() {
        return Arrays.<UniqueKey<EEmployeeRecord>>asList(Keys.KEY_E_EMPLOYEE_PRIMARY, Keys.KEY_E_EMPLOYEE_WORK_NUMBER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EEmployee as(String alias) {
        return new EEmployee(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EEmployee as(Name alias) {
        return new EEmployee(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public EEmployee rename(String name) {
        return new EEmployee(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public EEmployee rename(Name name) {
        return new EEmployee(name, null);
    }
}
