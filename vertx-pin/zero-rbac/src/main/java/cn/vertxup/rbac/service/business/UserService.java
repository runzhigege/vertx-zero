package cn.vertxup.rbac.service.business;

import cn.vertxup.rbac.domain.tables.daos.OUserDao;
import cn.vertxup.rbac.domain.tables.daos.RUserGroupDao;
import cn.vertxup.rbac.domain.tables.daos.RUserRoleDao;
import cn.vertxup.rbac.domain.tables.daos.SUserDao;
import cn.vertxup.rbac.domain.tables.pojos.SUser;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;

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
        final SUser user = Ux.fromJson(params, SUser.class);
        user.setKey(userId);
        return Ux.Jooq.on(SUserDao.class)
                /* User Saving here */
                .saveAsync(userId, user)
                .compose(Ux::fnJObject);
    }

    @Override
    public Future<JsonObject> updateEmployee(final String userId, final JsonObject params) {
        final SUser user = Ux.fromJson(params, SUser.class);
        user.setKey(userId);
        return Ux.Jooq.on(SUserDao.class)
                .saveAsync(userId, user)
                .compose(userInfo -> UserHelper.updateEmployee(userInfo, params));
    }
}
