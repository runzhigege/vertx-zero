package cn.vertxup.rbac.service.business;

import cn.vertxup.rbac.domain.tables.pojos.SUser;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.optic.EcUser;
import io.vertx.tp.optic.Pocket;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Uson;
import io.vertx.up.unity.Ux;

import java.util.Objects;
import java.util.function.Function;

class UserHelper {

    private static final Annal LOGGER = Annal.get(UserHelper.class);

    static Future<JsonObject> fetchEmployee(final SUser user) {
        /* User model key */
        return applyTunnel(user, executor ->
                /* Read employee information */
                executor.fetchAsync(user.getModelKey()));
    }

    static Future<JsonObject> updateEmployee(final SUser user, final JsonObject params) {
        /* User model key */
        return applyTunnel(user, executor ->
                /* Update employee information */
                executor.updateAsync(params.getString("employeeId"), params));
    }

    private static Future<JsonObject> applyUser(final SUser user) {
        return Ux.toFuture(user).compose(Ux::fnJObject);
    }

    private static Future<JsonObject> applyTunnel(final SUser user, final Function<EcUser, Future<JsonObject>> fnTunnel) {
        if (Objects.nonNull(user)) {
            if (Objects.nonNull(user.getModelKey())) {
                final EcUser executor = Pocket.lookup(EcUser.class);
                if (Objects.nonNull(executor)) {
                    Sc.infoAuth(LOGGER, AuthMsg.EMPLOYEE_BY_USER, user.getModelKey());
                    return fnTunnel.apply(executor);
                } else {
                    Sc.infoAuth(LOGGER, AuthMsg.EMPLOYEE_EMPTY + " Executor");
                    return applyUser(user)
                            /* Employee information */
                            .compose(employee -> Objects.isNull(employee) ?
                                    Ux.toFuture(new JsonObject()) :
                                    Ux.toFuture(employee))
                            /* Merged */
                            .compose(employee -> Uson
                                    .create(Ux.toJson(user).copy())
                                    .append(employee)
                                    /* Model Key -> Employee Id */
                                    .convert("modelKey", "employeeId")
                                    .toFuture()
                            );
                }
            } else {
                Sc.infoAuth(LOGGER, AuthMsg.EMPLOYEE_EMPTY + " Model Key");
                return applyUser(user);
            }
        } else {
            Sc.infoAuth(LOGGER, AuthMsg.EMPLOYEE_EMPTY + " Null");
            return Ux.toFuture(new JsonObject());
        }
    }
}
