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
public interface IRUserGroup extends Serializable {

    /**
     * Setter for <code>DB_RBAC.R_USER_GROUP.GROUP_ID</code>. 「groupId」- 关联组ID
     */
    public IRUserGroup setGroupId(String value);

    /**
     * Getter for <code>DB_RBAC.R_USER_GROUP.GROUP_ID</code>. 「groupId」- 关联组ID
     */
    public String getGroupId();

    /**
     * Setter for <code>DB_RBAC.R_USER_GROUP.USER_ID</code>. 「userId」- 关联用户ID
     */
    public IRUserGroup setUserId(String value);

    /**
     * Getter for <code>DB_RBAC.R_USER_GROUP.USER_ID</code>. 「userId」- 关联用户ID
     */
    public String getUserId();

    /**
     * Setter for <code>DB_RBAC.R_USER_GROUP.PRIORITY</code>. 「priority」- 组优先级
     */
    public IRUserGroup setPriority(Integer value);

    /**
     * Getter for <code>DB_RBAC.R_USER_GROUP.PRIORITY</code>. 「priority」- 组优先级
     */
    public Integer getPriority();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IRUserGroup
     */
    public void from(cn.vertxup.domain.tables.interfaces.IRUserGroup from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IRUserGroup
     */
    public <E extends cn.vertxup.domain.tables.interfaces.IRUserGroup> E into(E into);

    default IRUserGroup fromJson(io.vertx.core.json.JsonObject json) {
        setGroupId(json.getString("GROUP_ID"));
        setUserId(json.getString("USER_ID"));
        setPriority(json.getInteger("PRIORITY"));
        return this;
    }


    default io.vertx.core.json.JsonObject toJson() {
        io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("GROUP_ID",getGroupId());
        json.put("USER_ID",getUserId());
        json.put("PRIORITY",getPriority());
        return json;
    }

}
