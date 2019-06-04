/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.domain;


import cn.vertxup.domain.tables.OAccessToken;
import cn.vertxup.domain.tables.OUser;
import cn.vertxup.domain.tables.RGroupRole;
import cn.vertxup.domain.tables.RPermAction;
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
import cn.vertxup.domain.tables.records.OAccessTokenRecord;
import cn.vertxup.domain.tables.records.OUserRecord;
import cn.vertxup.domain.tables.records.RGroupRoleRecord;
import cn.vertxup.domain.tables.records.RPermActionRecord;
import cn.vertxup.domain.tables.records.RResourceMatrixRecord;
import cn.vertxup.domain.tables.records.RRolePermRecord;
import cn.vertxup.domain.tables.records.RUserGroupRecord;
import cn.vertxup.domain.tables.records.RUserRoleRecord;
import cn.vertxup.domain.tables.records.SActionRecord;
import cn.vertxup.domain.tables.records.SGroupRecord;
import cn.vertxup.domain.tables.records.SPermissionRecord;
import cn.vertxup.domain.tables.records.SResourceRecord;
import cn.vertxup.domain.tables.records.SRoleRecord;
import cn.vertxup.domain.tables.records.SUserRecord;

import javax.annotation.Generated;

import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>DB_RBAC</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<OAccessTokenRecord> KEY_O_ACCESS_TOKEN_PRIMARY = UniqueKeys0.KEY_O_ACCESS_TOKEN_PRIMARY;
    public static final UniqueKey<OUserRecord> KEY_O_USER_PRIMARY = UniqueKeys0.KEY_O_USER_PRIMARY;
    public static final UniqueKey<OUserRecord> KEY_O_USER_CLIENT_SECRET = UniqueKeys0.KEY_O_USER_CLIENT_SECRET;
    public static final UniqueKey<OUserRecord> KEY_O_USER_CLIENT_ID = UniqueKeys0.KEY_O_USER_CLIENT_ID;
    public static final UniqueKey<RGroupRoleRecord> KEY_R_GROUP_ROLE_PRIMARY = UniqueKeys0.KEY_R_GROUP_ROLE_PRIMARY;
    public static final UniqueKey<RPermActionRecord> KEY_R_PERM_ACTION_PRIMARY = UniqueKeys0.KEY_R_PERM_ACTION_PRIMARY;
    public static final UniqueKey<RResourceMatrixRecord> KEY_R_RESOURCE_MATRIX_PRIMARY = UniqueKeys0.KEY_R_RESOURCE_MATRIX_PRIMARY;
    public static final UniqueKey<RResourceMatrixRecord> KEY_R_RESOURCE_MATRIX_SIGMA_2 = UniqueKeys0.KEY_R_RESOURCE_MATRIX_SIGMA_2;
    public static final UniqueKey<RResourceMatrixRecord> KEY_R_RESOURCE_MATRIX_SIGMA = UniqueKeys0.KEY_R_RESOURCE_MATRIX_SIGMA;
    public static final UniqueKey<RRolePermRecord> KEY_R_ROLE_PERM_PRIMARY = UniqueKeys0.KEY_R_ROLE_PERM_PRIMARY;
    public static final UniqueKey<RUserGroupRecord> KEY_R_USER_GROUP_PRIMARY = UniqueKeys0.KEY_R_USER_GROUP_PRIMARY;
    public static final UniqueKey<RUserRoleRecord> KEY_R_USER_ROLE_PRIMARY = UniqueKeys0.KEY_R_USER_ROLE_PRIMARY;
    public static final UniqueKey<SActionRecord> KEY_S_ACTION_PRIMARY = UniqueKeys0.KEY_S_ACTION_PRIMARY;
    public static final UniqueKey<SActionRecord> KEY_S_ACTION_CODE = UniqueKeys0.KEY_S_ACTION_CODE;
    public static final UniqueKey<SActionRecord> KEY_S_ACTION_RESOURCE_ID = UniqueKeys0.KEY_S_ACTION_RESOURCE_ID;
    public static final UniqueKey<SGroupRecord> KEY_S_GROUP_PRIMARY = UniqueKeys0.KEY_S_GROUP_PRIMARY;
    public static final UniqueKey<SGroupRecord> KEY_S_GROUP_CODE = UniqueKeys0.KEY_S_GROUP_CODE;
    public static final UniqueKey<SGroupRecord> KEY_S_GROUP_MODEL_ID = UniqueKeys0.KEY_S_GROUP_MODEL_ID;
    public static final UniqueKey<SPermissionRecord> KEY_S_PERMISSION_PRIMARY = UniqueKeys0.KEY_S_PERMISSION_PRIMARY;
    public static final UniqueKey<SPermissionRecord> KEY_S_PERMISSION_CODE = UniqueKeys0.KEY_S_PERMISSION_CODE;
    public static final UniqueKey<SResourceRecord> KEY_S_RESOURCE_PRIMARY = UniqueKeys0.KEY_S_RESOURCE_PRIMARY;
    public static final UniqueKey<SResourceRecord> KEY_S_RESOURCE_SIGMA = UniqueKeys0.KEY_S_RESOURCE_SIGMA;
    public static final UniqueKey<SRoleRecord> KEY_S_ROLE_PRIMARY = UniqueKeys0.KEY_S_ROLE_PRIMARY;
    public static final UniqueKey<SRoleRecord> KEY_S_ROLE_CODE = UniqueKeys0.KEY_S_ROLE_CODE;
    public static final UniqueKey<SUserRecord> KEY_S_USER_PRIMARY = UniqueKeys0.KEY_S_USER_PRIMARY;
    public static final UniqueKey<SUserRecord> KEY_S_USER_USERNAME = UniqueKeys0.KEY_S_USER_USERNAME;
    public static final UniqueKey<SUserRecord> KEY_S_USER_MOBILE = UniqueKeys0.KEY_S_USER_MOBILE;
    public static final UniqueKey<SUserRecord> KEY_S_USER_EMAIL = UniqueKeys0.KEY_S_USER_EMAIL;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<OAccessTokenRecord> KEY_O_ACCESS_TOKEN_PRIMARY = Internal.createUniqueKey(OAccessToken.O_ACCESS_TOKEN, "KEY_O_ACCESS_TOKEN_PRIMARY", OAccessToken.O_ACCESS_TOKEN.KEY);
        public static final UniqueKey<OUserRecord> KEY_O_USER_PRIMARY = Internal.createUniqueKey(OUser.O_USER, "KEY_O_USER_PRIMARY", OUser.O_USER.KEY);
        public static final UniqueKey<OUserRecord> KEY_O_USER_CLIENT_SECRET = Internal.createUniqueKey(OUser.O_USER, "KEY_O_USER_CLIENT_SECRET", OUser.O_USER.CLIENT_SECRET);
        public static final UniqueKey<OUserRecord> KEY_O_USER_CLIENT_ID = Internal.createUniqueKey(OUser.O_USER, "KEY_O_USER_CLIENT_ID", OUser.O_USER.CLIENT_ID);
        public static final UniqueKey<RGroupRoleRecord> KEY_R_GROUP_ROLE_PRIMARY = Internal.createUniqueKey(RGroupRole.R_GROUP_ROLE, "KEY_R_GROUP_ROLE_PRIMARY", RGroupRole.R_GROUP_ROLE.GROUP_ID, RGroupRole.R_GROUP_ROLE.ROLE_ID);
        public static final UniqueKey<RPermActionRecord> KEY_R_PERM_ACTION_PRIMARY = Internal.createUniqueKey(RPermAction.R_PERM_ACTION, "KEY_R_PERM_ACTION_PRIMARY", RPermAction.R_PERM_ACTION.PERM_ID, RPermAction.R_PERM_ACTION.ACTION_ID);
        public static final UniqueKey<RResourceMatrixRecord> KEY_R_RESOURCE_MATRIX_PRIMARY = Internal.createUniqueKey(RResourceMatrix.R_RESOURCE_MATRIX, "KEY_R_RESOURCE_MATRIX_PRIMARY", RResourceMatrix.R_RESOURCE_MATRIX.KEY);
        public static final UniqueKey<RResourceMatrixRecord> KEY_R_RESOURCE_MATRIX_SIGMA_2 = Internal.createUniqueKey(RResourceMatrix.R_RESOURCE_MATRIX, "KEY_R_RESOURCE_MATRIX_SIGMA_2", RResourceMatrix.R_RESOURCE_MATRIX.SIGMA, RResourceMatrix.R_RESOURCE_MATRIX.RESOURCE_ID, RResourceMatrix.R_RESOURCE_MATRIX.ROLE_ID);
        public static final UniqueKey<RResourceMatrixRecord> KEY_R_RESOURCE_MATRIX_SIGMA = Internal.createUniqueKey(RResourceMatrix.R_RESOURCE_MATRIX, "KEY_R_RESOURCE_MATRIX_SIGMA", RResourceMatrix.R_RESOURCE_MATRIX.SIGMA, RResourceMatrix.R_RESOURCE_MATRIX.RESOURCE_ID, RResourceMatrix.R_RESOURCE_MATRIX.USER_ID);
        public static final UniqueKey<RRolePermRecord> KEY_R_ROLE_PERM_PRIMARY = Internal.createUniqueKey(RRolePerm.R_ROLE_PERM, "KEY_R_ROLE_PERM_PRIMARY", RRolePerm.R_ROLE_PERM.PERM_ID, RRolePerm.R_ROLE_PERM.ROLE_ID);
        public static final UniqueKey<RUserGroupRecord> KEY_R_USER_GROUP_PRIMARY = Internal.createUniqueKey(RUserGroup.R_USER_GROUP, "KEY_R_USER_GROUP_PRIMARY", RUserGroup.R_USER_GROUP.GROUP_ID, RUserGroup.R_USER_GROUP.ROLE_ID);
        public static final UniqueKey<RUserRoleRecord> KEY_R_USER_ROLE_PRIMARY = Internal.createUniqueKey(RUserRole.R_USER_ROLE, "KEY_R_USER_ROLE_PRIMARY", RUserRole.R_USER_ROLE.USER_ID, RUserRole.R_USER_ROLE.ROLE_ID);
        public static final UniqueKey<SActionRecord> KEY_S_ACTION_PRIMARY = Internal.createUniqueKey(SAction.S_ACTION, "KEY_S_ACTION_PRIMARY", SAction.S_ACTION.KEY);
        public static final UniqueKey<SActionRecord> KEY_S_ACTION_CODE = Internal.createUniqueKey(SAction.S_ACTION, "KEY_S_ACTION_CODE", SAction.S_ACTION.CODE, SAction.S_ACTION.SIGMA);
        public static final UniqueKey<SActionRecord> KEY_S_ACTION_RESOURCE_ID = Internal.createUniqueKey(SAction.S_ACTION, "KEY_S_ACTION_RESOURCE_ID", SAction.S_ACTION.RESOURCE_ID);
        public static final UniqueKey<SGroupRecord> KEY_S_GROUP_PRIMARY = Internal.createUniqueKey(SGroup.S_GROUP, "KEY_S_GROUP_PRIMARY", SGroup.S_GROUP.KEY);
        public static final UniqueKey<SGroupRecord> KEY_S_GROUP_CODE = Internal.createUniqueKey(SGroup.S_GROUP, "KEY_S_GROUP_CODE", SGroup.S_GROUP.CODE, SGroup.S_GROUP.SIGMA);
        public static final UniqueKey<SGroupRecord> KEY_S_GROUP_MODEL_ID = Internal.createUniqueKey(SGroup.S_GROUP, "KEY_S_GROUP_MODEL_ID", SGroup.S_GROUP.MODEL_ID, SGroup.S_GROUP.MODEL_KEY);
        public static final UniqueKey<SPermissionRecord> KEY_S_PERMISSION_PRIMARY = Internal.createUniqueKey(SPermission.S_PERMISSION, "KEY_S_PERMISSION_PRIMARY", SPermission.S_PERMISSION.KEY);
        public static final UniqueKey<SPermissionRecord> KEY_S_PERMISSION_CODE = Internal.createUniqueKey(SPermission.S_PERMISSION, "KEY_S_PERMISSION_CODE", SPermission.S_PERMISSION.CODE, SPermission.S_PERMISSION.SIGMA);
        public static final UniqueKey<SResourceRecord> KEY_S_RESOURCE_PRIMARY = Internal.createUniqueKey(SResource.S_RESOURCE, "KEY_S_RESOURCE_PRIMARY", SResource.S_RESOURCE.KEY);
        public static final UniqueKey<SResourceRecord> KEY_S_RESOURCE_SIGMA = Internal.createUniqueKey(SResource.S_RESOURCE, "KEY_S_RESOURCE_SIGMA", SResource.S_RESOURCE.SIGMA, SResource.S_RESOURCE.URI, SResource.S_RESOURCE.METHOD);
        public static final UniqueKey<SRoleRecord> KEY_S_ROLE_PRIMARY = Internal.createUniqueKey(SRole.S_ROLE, "KEY_S_ROLE_PRIMARY", SRole.S_ROLE.KEY);
        public static final UniqueKey<SRoleRecord> KEY_S_ROLE_CODE = Internal.createUniqueKey(SRole.S_ROLE, "KEY_S_ROLE_CODE", SRole.S_ROLE.CODE, SRole.S_ROLE.SIGMA);
        public static final UniqueKey<SUserRecord> KEY_S_USER_PRIMARY = Internal.createUniqueKey(SUser.S_USER, "KEY_S_USER_PRIMARY", SUser.S_USER.KEY);
        public static final UniqueKey<SUserRecord> KEY_S_USER_USERNAME = Internal.createUniqueKey(SUser.S_USER, "KEY_S_USER_USERNAME", SUser.S_USER.USERNAME, SUser.S_USER.SIGMA);
        public static final UniqueKey<SUserRecord> KEY_S_USER_MOBILE = Internal.createUniqueKey(SUser.S_USER, "KEY_S_USER_MOBILE", SUser.S_USER.MOBILE, SUser.S_USER.SIGMA);
        public static final UniqueKey<SUserRecord> KEY_S_USER_EMAIL = Internal.createUniqueKey(SUser.S_USER, "KEY_S_USER_EMAIL", SUser.S_USER.EMAIL, SUser.S_USER.SIGMA);
    }
}
