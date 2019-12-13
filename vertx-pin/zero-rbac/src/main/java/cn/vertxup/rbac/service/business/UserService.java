package cn.vertxup.rbac.service.business;

import cn.vertxup.rbac.domain.tables.daos.*;
import cn.vertxup.rbac.domain.tables.pojos.*;
import com.fasterxml.jackson.databind.JsonArrayDeserializer;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;
import io.vertx.up.unity.UxJooq;
import io.vertx.up.util.Ut;
import org.jooq.tools.json.JSONArray;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserService implements UserStub {
    private static final Annal LOGGER = Annal.get(UserService.class);

    @Override
    public Future<JsonObject> fetchOUser(final String userKey) {
        return Ux.Jooq.on(OUserDao.class)
                .fetchOneAsync(AuthKey.F_CLIENT_ID, userKey)
                .compose(Ux::fnJObject);
    }

    @Override
    public Future<JsonArray> fetchRoleIds(final String userKey) {
        Sc.infoAuth(LOGGER, AuthMsg.RELATION_USER_ROLE, userKey);
        return Sc.relation(AuthKey.F_USER_ID, userKey, RUserRoleDao.class);
    }

    @Override
    public Future<JsonArray> fetchGroupIds(final String userKey) {
        Sc.infoAuth(LOGGER, AuthMsg.RELATION_GROUP, userKey);
        return Sc.relation(AuthKey.F_USER_ID, userKey, RUserGroupDao.class);
    }

    @Override
    public Future<JsonObject> fetchEmployee(final String userId) {
        return Ux.Jooq.on(SUserDao.class)
                /* User Information */
                .<SUser>findByIdAsync(userId)
                /* Employee Information */
                .compose(UserHelper::fetchEmployee);
    }

    @Override
    public Future<JsonObject> updateUser(final String userId, final JsonObject params) {
        final JsonArray roles = params.getJsonArray("roles");
        final JsonArray groups = params.getJsonArray("groups");
        final SUser user = Ux.fromJson(params, SUser.class);
        user.setKey(userId);
        return Ux.Jooq.on(SUserDao.class)
                /* User Saving here */
                .saveAsync(userId, user)
                .compose(entity -> updateUserRelatedInformation(userId, Ux.toJson(entity), roles, groups));
    }

    @Override
    public Future<JsonObject> updateEmployee(final String userId, final JsonObject params) {
        final SUser user = Ux.fromJson(params, SUser.class);
        user.setKey(userId);
        return Ux.Jooq.on(SUserDao.class)
                .saveAsync(userId, user)
                .compose(userInfo -> UserHelper.updateEmployee(userInfo, params));
    }

    @Override
    public Future<JsonObject> fetchUser(String userKey) {
        return Ux.Jooq.on(SUserDao.class)
            .findByIdAsync(userKey)
            .compose(userInfo -> this.fulfillUserWithRolesAndGroups(userKey, Ux.toJson(userInfo)));
    }

    @Override
    public Future<JsonObject> createUser(final JsonObject params) {
        final SUser user = Ux.fromJson(params, SUser.class);
        return Ux.Jooq.on(SUserDao.class)
            .insertAsync(user)
            .compose(this::createOUser);
    }

    @Override
    public Future<Boolean> deleteUser(String userKey) {
        final UxJooq sUserDao = Ux.Jooq.on(SUserDao.class);
        final UxJooq oUserDao = Ux.Jooq.on(OUserDao.class);
        final UxJooq rUserRoleDao = Ux.Jooq.on(RUserRoleDao.class);
        final UxJooq rUserGroupDao = Ux.Jooq.on(RUserGroupDao.class);

        return oUserDao.fetchOneAsync(new JsonObject().put("CLIENT_ID", userKey))
            /* delete OUser record */
            .compose(item -> oUserDao.deleteByIdAsync(Ux.toJson(item).getString("key")))
            /* delete related role records */
            .compose(oUserFlag -> rUserRoleDao.deleteAsync(new JsonObject().put("USER_ID", userKey)))
            /* delete related group records */
            .compose(rUserRoleFlag -> rUserGroupDao.deleteAsync(new JsonObject().put("USER_ID", userKey)))
            /* delete SUser record */
            .compose(rUserGroupFlag -> sUserDao.deleteByIdAsync(userKey));
    }

    /**
     * TODO: replace the fixed value with real value
     * create OUser record
     * @param user SUser entity
     * @return SUser entity
     */
    private Future<JsonObject> createOUser(final SUser user) {
        final OUser oUser = new OUser()
            .setClientId(user.getKey())
            .setClientSecret(Ut.randomString(64))
            .setScope("vie.app.ox")
            .setGrantType("authorization_code")
            .setLanguage("cn")
            .setActive(Boolean.TRUE)
            .setKey(UUID.randomUUID().toString());

        return Ux.Jooq.on(OUserDao.class)
                .insertAsync(oUser)
                // delete attribute: password from user information
                .compose(entity -> Ux.fnJObject(user.setPassword("")));
    }

    /**
     * get related groups and roles of user
     * @param userKey user key
     * @param user user entity
     * @return user entity
     */
    private Future<JsonObject> fulfillUserWithRolesAndGroups(final String userKey, final JsonObject user) {
        /* delete attribute: password from user information */
        user.put("password", "")
            .put("roles",
		        Ux.Jooq.on(RUserRoleDao.class)
	            .fetch(AuthKey.F_USER_ID, userKey)
		        .stream()
                    .sorted(Comparator.comparing(item -> ((RUserRole) item).getPriority()))
                    .map(item -> Ux.toJson(item).getString("roleId"))
		            .collect(Collectors.toList())
	        )
	        .put("groups", Ux.Jooq.on(RUserGroupDao.class)
                .fetch(AuthKey.F_USER_ID, userKey)
		        .stream()
                    .sorted(Comparator.comparing(item -> ((RUserGroup) item).getPriority()))
                    .map(item -> Ux.toJson(item).getString("groupId"))
		            .collect(Collectors.toList())
            );
        return Ux.future(user);
    }

    private Future<JsonObject> updateUserRelatedInformation(final String userKey, final JsonObject user, JsonArray roles, JsonArray groups) {
        /* execute this branch when only update user information */
        if (roles == null || groups == null) {
            return Ux.future(user);
        } else {
            /* execute this branch when update user and related information */
            List<String> rolesJObjects = roles.getList();
            List<String> groupsJObjects = groups.getList();

            return Ux.Jooq.on(RUserRoleDao.class)
                /* delete related roles */
                .deleteAsync(new JsonObject().put("USER_ID", userKey))
                /* insert related roles */
                .compose(roleFlag -> {
                        List<RUserRole> result = rolesJObjects.stream().map((roleId -> {
                            RUserRole rUserRole = new RUserRole()
                                .setUserId(userKey)
                                .setRoleId(roleId)
                                .setPriority(rolesJObjects.indexOf(roleId));
                            return Ux.Jooq.on(RUserRoleDao.class).insert(rUserRole);
                        }))
                            .collect(Collectors.toList());

                        return Ux.future(result);
                    }
                )
                /* delete related groups */
                .compose(userRoleList -> Ux.Jooq.on(RUserGroupDao.class)
                    .deleteAsync(new JsonObject().put("USER_ID", userKey))
                )
                /* insert related roles */
                .compose(groupFlag -> {
                    List<RUserGroup> result = groupsJObjects.stream().map(groupId -> {
                        RUserGroup rUserGroup = new RUserGroup()
                            .setUserId(userKey)
                            .setGroupId(groupId)
                            .setPriority(groupsJObjects.indexOf(groupId));
                        return Ux.Jooq.on(RUserGroupDao.class).insert(rUserGroup);
                    })
                        .collect(Collectors.toList());

                    return Ux.future(result);
                })
                /* delete attribute:password and return user entity */
                .compose(userGroupList -> Ux.future(user.put("password", "").put("roles", roles).put("groups", groups)));
        }
    }
}
