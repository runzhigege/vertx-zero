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
                                    /* Model Key -> Employee Id */
                                    .convert("modelKey", "employeeId")
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
