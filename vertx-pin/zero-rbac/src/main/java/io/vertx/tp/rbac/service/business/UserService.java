package io.vertx.tp.rbac.service.business;

import cn.vertxup.domain.tables.daos.OUserDao;
import cn.vertxup.domain.tables.daos.RUserGroupDao;
import cn.vertxup.domain.tables.daos.RUserRoleDao;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.aiki.Ux;
import io.vertx.up.log.Annal;

public class UserService implements UserStub {

    private static final Annal LOGGER = Annal.get(UserService.class);

    @Override
    public Future<JsonObject> fetchOUser(final String userKey) {
        return Ux.Jooq.on(OUserDao.class)
                .fetchOneAsync(AuthKey.F_CLIENT_ID, userKey)
                .compose(Ux::fnJObject);
    }

    @Override
    public Future<JsonArray> fetchGroups(final String userKey) {
        Sc.infoAuth(LOGGER, AuthMsg.RELATION_GROUP, userKey);
        return Ux.Jooq.on(RUserGroupDao.class)
                .fetchAsync(AuthKey.F_USER_ID, userKey)
                .compose(Ux::fnJArray);
    }

    @Override
    public Future<JsonArray> fetchRoles(final String userKey) {
        Sc.infoAuth(LOGGER, AuthMsg.RELATION_ROLE, userKey);
        return Ux.Jooq.on(RUserRoleDao.class)
                .fetchAsync(AuthKey.F_USER_ID, userKey)
                .compose(Ux::fnJArray);
    }
}
