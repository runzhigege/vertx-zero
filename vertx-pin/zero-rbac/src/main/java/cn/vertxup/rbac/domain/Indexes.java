/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.rbac.domain;


import cn.vertxup.rbac.domain.tables.OAccessToken;
import cn.vertxup.rbac.domain.tables.OUser;
import cn.vertxup.rbac.domain.tables.RGroupRole;
import cn.vertxup.rbac.domain.tables.RRolePerm;
import cn.vertxup.rbac.domain.tables.RUserGroup;
import cn.vertxup.rbac.domain.tables.RUserRole;
import cn.vertxup.rbac.domain.tables.SAction;
import cn.vertxup.rbac.domain.tables.SGroup;
import cn.vertxup.rbac.domain.tables.SPermission;
import cn.vertxup.rbac.domain.tables.SResource;
import cn.vertxup.rbac.domain.tables.SRole;
import cn.vertxup.rbac.domain.tables.SUser;
import cn.vertxup.rbac.domain.tables.SView;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>DB_ETERNAL</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index O_ACCESS_TOKEN_PRIMARY = Indexes0.O_ACCESS_TOKEN_PRIMARY;
    public static final Index O_USER_CLIENT_ID = Indexes0.O_USER_CLIENT_ID;
    public static final Index O_USER_CLIENT_SECRET = Indexes0.O_USER_CLIENT_SECRET;
    public static final Index O_USER_PRIMARY = Indexes0.O_USER_PRIMARY;
    public static final Index R_GROUP_ROLE_PRIMARY = Indexes0.R_GROUP_ROLE_PRIMARY;
    public static final Index R_ROLE_PERM_PRIMARY = Indexes0.R_ROLE_PERM_PRIMARY;
    public static final Index R_USER_GROUP_PRIMARY = Indexes0.R_USER_GROUP_PRIMARY;
    public static final Index R_USER_ROLE_PRIMARY = Indexes0.R_USER_ROLE_PRIMARY;
    public static final Index S_ACTION_CODE = Indexes0.S_ACTION_CODE;
    public static final Index S_ACTION_PRIMARY = Indexes0.S_ACTION_PRIMARY;
    public static final Index S_ACTION_RESOURCE_ID = Indexes0.S_ACTION_RESOURCE_ID;
    public static final Index S_ACTION_URI = Indexes0.S_ACTION_URI;
    public static final Index S_GROUP_CODE = Indexes0.S_GROUP_CODE;
    public static final Index S_GROUP_MODEL_ID = Indexes0.S_GROUP_MODEL_ID;
    public static final Index S_GROUP_PRIMARY = Indexes0.S_GROUP_PRIMARY;
    public static final Index S_PERMISSION_CODE = Indexes0.S_PERMISSION_CODE;
    public static final Index S_PERMISSION_PRIMARY = Indexes0.S_PERMISSION_PRIMARY;
    public static final Index S_RESOURCE_CODE = Indexes0.S_RESOURCE_CODE;
    public static final Index S_RESOURCE_PRIMARY = Indexes0.S_RESOURCE_PRIMARY;
    public static final Index S_ROLE_CODE = Indexes0.S_ROLE_CODE;
    public static final Index S_ROLE_PRIMARY = Indexes0.S_ROLE_PRIMARY;
    public static final Index S_USER_EMAIL = Indexes0.S_USER_EMAIL;
    public static final Index S_USER_MOBILE = Indexes0.S_USER_MOBILE;
    public static final Index S_USER_PRIMARY = Indexes0.S_USER_PRIMARY;
    public static final Index S_USER_USERNAME = Indexes0.S_USER_USERNAME;
    public static final Index S_VIEW_OWNER = Indexes0.S_VIEW_OWNER;
    public static final Index S_VIEW_PRIMARY = Indexes0.S_VIEW_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index O_ACCESS_TOKEN_PRIMARY = Internal.createIndex("PRIMARY", OAccessToken.O_ACCESS_TOKEN, new OrderField[] { OAccessToken.O_ACCESS_TOKEN.KEY }, true);
        public static Index O_USER_CLIENT_ID = Internal.createIndex("CLIENT_ID", OUser.O_USER, new OrderField[] { OUser.O_USER.CLIENT_ID }, true);
        public static Index O_USER_CLIENT_SECRET = Internal.createIndex("CLIENT_SECRET", OUser.O_USER, new OrderField[] { OUser.O_USER.CLIENT_SECRET }, true);
        public static Index O_USER_PRIMARY = Internal.createIndex("PRIMARY", OUser.O_USER, new OrderField[] { OUser.O_USER.KEY }, true);
        public static Index R_GROUP_ROLE_PRIMARY = Internal.createIndex("PRIMARY", RGroupRole.R_GROUP_ROLE, new OrderField[] { RGroupRole.R_GROUP_ROLE.GROUP_ID, RGroupRole.R_GROUP_ROLE.ROLE_ID }, true);
        public static Index R_ROLE_PERM_PRIMARY = Internal.createIndex("PRIMARY", RRolePerm.R_ROLE_PERM, new OrderField[] { RRolePerm.R_ROLE_PERM.PERM_ID, RRolePerm.R_ROLE_PERM.ROLE_ID }, true);
        public static Index R_USER_GROUP_PRIMARY = Internal.createIndex("PRIMARY", RUserGroup.R_USER_GROUP, new OrderField[] { RUserGroup.R_USER_GROUP.GROUP_ID, RUserGroup.R_USER_GROUP.USER_ID }, true);
        public static Index R_USER_ROLE_PRIMARY = Internal.createIndex("PRIMARY", RUserRole.R_USER_ROLE, new OrderField[] { RUserRole.R_USER_ROLE.USER_ID, RUserRole.R_USER_ROLE.ROLE_ID }, true);
        public static Index S_ACTION_CODE = Internal.createIndex("CODE", SAction.S_ACTION, new OrderField[] { SAction.S_ACTION.CODE, SAction.S_ACTION.SIGMA }, true);
        public static Index S_ACTION_PRIMARY = Internal.createIndex("PRIMARY", SAction.S_ACTION, new OrderField[] { SAction.S_ACTION.KEY }, true);
        public static Index S_ACTION_RESOURCE_ID = Internal.createIndex("RESOURCE_ID", SAction.S_ACTION, new OrderField[] { SAction.S_ACTION.RESOURCE_ID }, true);
        public static Index S_ACTION_URI = Internal.createIndex("URI", SAction.S_ACTION, new OrderField[] { SAction.S_ACTION.URI, SAction.S_ACTION.METHOD, SAction.S_ACTION.SIGMA }, true);
        public static Index S_GROUP_CODE = Internal.createIndex("CODE", SGroup.S_GROUP, new OrderField[] { SGroup.S_GROUP.CODE, SGroup.S_GROUP.SIGMA }, true);
        public static Index S_GROUP_MODEL_ID = Internal.createIndex("MODEL_ID", SGroup.S_GROUP, new OrderField[] { SGroup.S_GROUP.MODEL_ID, SGroup.S_GROUP.MODEL_KEY }, true);
        public static Index S_GROUP_PRIMARY = Internal.createIndex("PRIMARY", SGroup.S_GROUP, new OrderField[] { SGroup.S_GROUP.KEY }, true);
        public static Index S_PERMISSION_CODE = Internal.createIndex("CODE", SPermission.S_PERMISSION, new OrderField[] { SPermission.S_PERMISSION.CODE, SPermission.S_PERMISSION.SIGMA }, true);
        public static Index S_PERMISSION_PRIMARY = Internal.createIndex("PRIMARY", SPermission.S_PERMISSION, new OrderField[] { SPermission.S_PERMISSION.KEY }, true);
        public static Index S_RESOURCE_CODE = Internal.createIndex("CODE", SResource.S_RESOURCE, new OrderField[] { SResource.S_RESOURCE.CODE, SResource.S_RESOURCE.SIGMA }, true);
        public static Index S_RESOURCE_PRIMARY = Internal.createIndex("PRIMARY", SResource.S_RESOURCE, new OrderField[] { SResource.S_RESOURCE.KEY }, true);
        public static Index S_ROLE_CODE = Internal.createIndex("CODE", SRole.S_ROLE, new OrderField[] { SRole.S_ROLE.CODE, SRole.S_ROLE.SIGMA }, true);
        public static Index S_ROLE_PRIMARY = Internal.createIndex("PRIMARY", SRole.S_ROLE, new OrderField[] { SRole.S_ROLE.KEY }, true);
        public static Index S_USER_EMAIL = Internal.createIndex("EMAIL", SUser.S_USER, new OrderField[] { SUser.S_USER.EMAIL, SUser.S_USER.SIGMA }, true);
        public static Index S_USER_MOBILE = Internal.createIndex("MOBILE", SUser.S_USER, new OrderField[] { SUser.S_USER.MOBILE, SUser.S_USER.SIGMA }, true);
        public static Index S_USER_PRIMARY = Internal.createIndex("PRIMARY", SUser.S_USER, new OrderField[] { SUser.S_USER.KEY }, true);
        public static Index S_USER_USERNAME = Internal.createIndex("USERNAME", SUser.S_USER, new OrderField[] { SUser.S_USER.USERNAME, SUser.S_USER.SIGMA }, true);
        public static Index S_VIEW_OWNER = Internal.createIndex("OWNER", SView.S_VIEW, new OrderField[] { SView.S_VIEW.OWNER, SView.S_VIEW.OWNER_TYPE, SView.S_VIEW.RESOURCE_ID, SView.S_VIEW.NAME }, true);
        public static Index S_VIEW_PRIMARY = Internal.createIndex("PRIMARY", SView.S_VIEW, new OrderField[] { SView.S_VIEW.KEY }, true);
    }
}