/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.rbac.domain.tables.pojos;


import cn.vertxup.rbac.domain.tables.interfaces.IRRolePerm;

import javax.annotation.Generated;


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
public class RRolePerm implements IRRolePerm {

    private static final long serialVersionUID = 490432879;

    private String permId;
    private String roleId;

    public RRolePerm() {}

    public RRolePerm(RRolePerm value) {
        this.permId = value.permId;
        this.roleId = value.roleId;
    }

    public RRolePerm(
        String permId,
        String roleId
    ) {
        this.permId = permId;
        this.roleId = roleId;
    }

    @Override
    public String getPermId() {
        return this.permId;
    }

    @Override
    public RRolePerm setPermId(String permId) {
        this.permId = permId;
        return this;
    }

    @Override
    public String getRoleId() {
        return this.roleId;
    }

    @Override
    public RRolePerm setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("RRolePerm (");

        sb.append(permId);
        sb.append(", ").append(roleId);

        sb.append(")");
        return sb.toString();
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

    public RRolePerm(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
