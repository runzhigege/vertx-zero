/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.domain;


import cn.vertxup.domain.tables.OAccessToken;
import cn.vertxup.domain.tables.OUser;
import cn.vertxup.domain.tables.RGroupRole;
import cn.vertxup.domain.tables.RResourceMatrix;
import cn.vertxup.domain.tables.RRolePerm;
import cn.vertxup.domain.tables.RUserGroup;
import cn.vertxup.domain.tables.RUserRole;
import cn.vertxup.domain.tables.SAction;
import cn.vertxup.domain.tables.SGroup;
import cn.vertxup.domain.tables.SPermission;
import cn.vertxup.domain.tables.SResource;
import cn.vertxup.domain.tables.SRole;
import cn.vertxup.domain.tables.SUser;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in DB_ORIGIN_X
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>DB_ORIGIN_X.O_ACCESS_TOKEN</code>.
     */
    public static final OAccessToken O_ACCESS_TOKEN = cn.vertxup.domain.tables.OAccessToken.O_ACCESS_TOKEN;

    /**
     * The table <code>DB_ORIGIN_X.O_USER</code>.
     */
    public static final OUser O_USER = cn.vertxup.domain.tables.OUser.O_USER;

    /**
     * The table <code>DB_ORIGIN_X.R_GROUP_ROLE</code>.
     */
    public static final RGroupRole R_GROUP_ROLE = cn.vertxup.domain.tables.RGroupRole.R_GROUP_ROLE;

    /**
     * The table <code>DB_ORIGIN_X.R_RESOURCE_MATRIX</code>.
     */
    public static final RResourceMatrix R_RESOURCE_MATRIX = cn.vertxup.domain.tables.RResourceMatrix.R_RESOURCE_MATRIX;

    /**
     * The table <code>DB_ORIGIN_X.R_ROLE_PERM</code>.
     */
    public static final RRolePerm R_ROLE_PERM = cn.vertxup.domain.tables.RRolePerm.R_ROLE_PERM;

    /**
     * The table <code>DB_ORIGIN_X.R_USER_GROUP</code>.
     */
    public static final RUserGroup R_USER_GROUP = cn.vertxup.domain.tables.RUserGroup.R_USER_GROUP;

    /**
     * The table <code>DB_ORIGIN_X.R_USER_ROLE</code>.
     */
    public static final RUserRole R_USER_ROLE = cn.vertxup.domain.tables.RUserRole.R_USER_ROLE;

    /**
     * The table <code>DB_ORIGIN_X.S_ACTION</code>.
     */
    public static final SAction S_ACTION = cn.vertxup.domain.tables.SAction.S_ACTION;

    /**
     * The table <code>DB_ORIGIN_X.S_GROUP</code>.
     */
    public static final SGroup S_GROUP = cn.vertxup.domain.tables.SGroup.S_GROUP;

    /**
     * The table <code>DB_ORIGIN_X.S_PERMISSION</code>.
     */
    public static final SPermission S_PERMISSION = cn.vertxup.domain.tables.SPermission.S_PERMISSION;

    /**
     * The table <code>DB_ORIGIN_X.S_RESOURCE</code>.
     */
    public static final SResource S_RESOURCE = cn.vertxup.domain.tables.SResource.S_RESOURCE;

    /**
     * The table <code>DB_ORIGIN_X.S_ROLE</code>.
     */
    public static final SRole S_ROLE = cn.vertxup.domain.tables.SRole.S_ROLE;

    /**
     * The table <code>DB_ORIGIN_X.S_USER</code>.
     */
    public static final SUser S_USER = cn.vertxup.domain.tables.SUser.S_USER;
}
