/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.domain.tables.records;


import cn.vertxup.domain.tables.RRolePerm;
import cn.vertxup.domain.tables.interfaces.IRRolePerm;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
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
public class RRolePermRecord extends UpdatableRecordImpl<RRolePermRecord> implements Record2<String, String>, IRRolePerm {

    private static final long serialVersionUID = -356691450;

    /**
     * Setter for <code>UP_RBAC.R_ROLE_PERM.PERM_ID</code>. 「permId」- 关联权限ID
     */
    @Override
    public RRolePermRecord setPermId(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>UP_RBAC.R_ROLE_PERM.PERM_ID</code>. 「permId」- 关联权限ID
     */
    @Override
    public String getPermId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>UP_RBAC.R_ROLE_PERM.ROLE_ID</code>. 「roleId」- 关联角色ID
     */
    @Override
    public RRolePermRecord setRoleId(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>UP_RBAC.R_ROLE_PERM.ROLE_ID</code>. 「roleId」- 关联角色ID
     */
    @Override
    public String getRoleId() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record2<String, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return RRolePerm.R_ROLE_PERM.PERM_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return RRolePerm.R_ROLE_PERM.ROLE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getPermId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getRoleId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getPermId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getRoleId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RRolePermRecord value1(String value) {
        setPermId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RRolePermRecord value2(String value) {
        setRoleId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RRolePermRecord values(String value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IRRolePerm from) {
        setPermId(from.getPermId());
        setRoleId(from.getRoleId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IRRolePerm> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RRolePermRecord
     */
    public RRolePermRecord() {
        super(RRolePerm.R_ROLE_PERM);
    }

    /**
     * Create a detached, initialised RRolePermRecord
     */
    public RRolePermRecord(String permId, String roleId) {
        super(RRolePerm.R_ROLE_PERM);

        set(0, permId);
        set(1, roleId);
    }
}
