/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.domain.tables.interfaces;


import java.io.Serializable;

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
public interface IRUserRole extends Serializable {

    /**
     * Setter for <code>DB_RBAC.R_USER_ROLE.USER_ID</code>. 「userId」- 关联用户ID
     */
    public IRUserRole setUserId(String value);

    /**
     * Getter for <code>DB_RBAC.R_USER_ROLE.USER_ID</code>. 「userId」- 关联用户ID
     */
    public String getUserId();

    /**
     * Setter for <code>DB_RBAC.R_USER_ROLE.ROLE_ID</code>. 「roleId」- 关联角色ID
     */
    public IRUserRole setRoleId(String value);

    /**
     * Getter for <code>DB_RBAC.R_USER_ROLE.ROLE_ID</code>. 「roleId」- 关联角色ID
     */
    public String getRoleId();

    /**
     * Setter for <code>DB_RBAC.R_USER_ROLE.PRIORITY</code>. 「priority」- 角色优先级
     */
    public IRUserRole setPriority(Integer value);

    /**
     * Getter for <code>DB_RBAC.R_USER_ROLE.PRIORITY</code>. 「priority」- 角色优先级
     */
    public Integer getPriority();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IRUserRole
     */
    public void from(cn.vertxup.domain.tables.interfaces.IRUserRole from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IRUserRole
     */
    public <E extends cn.vertxup.domain.tables.interfaces.IRUserRole> E into(E into);

    default IRUserRole fromJson(io.vertx.core.json.JsonObject json) {
        setUserId(json.getString("USER_ID"));
        setRoleId(json.getString("ROLE_ID"));
        setPriority(json.getInteger("PRIORITY"));
        return this;
    }


    default io.vertx.core.json.JsonObject toJson() {
        io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("USER_ID",getUserId());
        json.put("ROLE_ID",getRoleId());
        json.put("PRIORITY",getPriority());
        return json;
    }

}
