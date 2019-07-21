package cn.vertxup.rbac.service.business;

import cn.vertxup.rbac.domain.tables.daos.OUserDao;
import cn.vertxup.rbac.domain.tables.daos.RUserGroupDao;
import cn.vertxup.rbac.domain.tables.daos.RUserRoleDao;
import cn.vertxup.rbac.domain.tables.daos.SUserDao;
import cn.vertxup.rbac.domain.tables.pojos.SUser;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.optic.EcUser;
import io.vertx.tp.optic.Pocket;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Uson;
import io.vertx.up.unity.Ux;

import java.util.Objects;
import java.util.function.Function;

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
                .compose(this::fetchEmployee);
    }

    private Future<JsonObject> fetchEmployee(final SUser user) {
        /* Apply user directly */
        final Function<SUser, Future<JsonObject>> applyFn
                = userInfo -> Ux.toFuture(userInfo).compose(Ux::fnJObject);

        if (Objects.nonNull(user)) {
            if (Objects.nonNull(user.getModelKey())) {
                final EcUser executor = Pocket.lookup(EcUser.class);
                if (Objects.nonNull(executor)) {
                    Sc.infoAuth(LOGGER, AuthMsg.EMPLOYEE_BY_USER, user.getModelKey());
                    return executor.fetchAsync(user.getModelKey())
                            /* Employee information */
                            .compose(employee -> Objects.isNull(employee) ?
                                    Ux.toFuture(new JsonObject()) :
                                    Ux.toFuture(employee))
                            /* Merged */
                            .compose(employee -> Uson
                                    .create(Ux.toJson(user).copy())
                                    .append(employee)
                                    .toFuture()
                            );
                } else {
                    Sc.infoAuth(LOGGER, AuthMsg.EMPLOYEE_EMPTY + " Executor");
                    return applyFn.apply(user);
                }
            } else {
                Sc.infoAuth(LOGGER, AuthMsg.EMPLOYEE_EMPTY + " Model Key");
                return applyFn.apply(user);
            }
        } else {
            Sc.infoAuth(LOGGER, AuthMsg.EMPLOYEE_EMPTY + " Null");
            return Ux.toFuture(new JsonObject());
        }
    }
}
