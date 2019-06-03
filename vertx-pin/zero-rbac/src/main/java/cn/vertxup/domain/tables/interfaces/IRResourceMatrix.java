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
public interface IRResourceMatrix extends Serializable {

    /**
     * Setter for <code>UP_RBAC.R_RESOURCE_MATRIX.KEY</code>. 「key」- 限定记录ID
     */
    public IRResourceMatrix setKey(String value);

    /**
     * Getter for <code>UP_RBAC.R_RESOURCE_MATRIX.KEY</code>. 「key」- 限定记录ID
     */
    public String getKey();

    /**
     * Setter for <code>UP_RBAC.R_RESOURCE_MATRIX.ROLE_ID</code>. 「roleId」- 限定角色ID
     */
    public IRResourceMatrix setRoleId(String value);

    /**
     * Getter for <code>UP_RBAC.R_RESOURCE_MATRIX.ROLE_ID</code>. 「roleId」- 限定角色ID
     */
    public String getRoleId();

    /**
     * Setter for <code>UP_RBAC.R_RESOURCE_MATRIX.USER_ID</code>. 「userId」- 限定用户ID
     */
    public IRResourceMatrix setUserId(String value);

    /**
     * Getter for <code>UP_RBAC.R_RESOURCE_MATRIX.USER_ID</code>. 「userId」- 限定用户ID
     */
    public String getUserId();

    /**
     * Setter for <code>UP_RBAC.R_RESOURCE_MATRIX.RESOURCE_ID</code>. 「resourceId」- 关联资源ID
     */
    public IRResourceMatrix setResourceId(String value);

    /**
     * Getter for <code>UP_RBAC.R_RESOURCE_MATRIX.RESOURCE_ID</code>. 「resourceId」- 关联资源ID
     */
    public String getResourceId();

    /**
     * Setter for <code>UP_RBAC.R_RESOURCE_MATRIX.SIGMA</code>. 「sigma」- 所属APP的APPKEY
     */
    public IRResourceMatrix setSigma(String value);

    /**
     * Getter for <code>UP_RBAC.R_RESOURCE_MATRIX.SIGMA</code>. 「sigma」- 所属APP的APPKEY
     */
    public String getSigma();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IRResourceMatrix
     */
    public void from(cn.vertxup.domain.tables.interfaces.IRResourceMatrix from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IRResourceMatrix
     */
    public <E extends cn.vertxup.domain.tables.interfaces.IRResourceMatrix> E into(E into);

    default IRResourceMatrix fromJson(io.vertx.core.json.JsonObject json) {
        setKey(json.getString("KEY"));
        setRoleId(json.getString("ROLE_ID"));
        setUserId(json.getString("USER_ID"));
        setResourceId(json.getString("RESOURCE_ID"));
        setSigma(json.getString("SIGMA"));
        return this;
    }


    default io.vertx.core.json.JsonObject toJson() {
        io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("KEY",getKey());
        json.put("ROLE_ID",getRoleId());
        json.put("USER_ID",getUserId());
        json.put("RESOURCE_ID",getResourceId());
        json.put("SIGMA",getSigma());
        return json;
    }

}
