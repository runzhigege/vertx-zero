package cn.vertxup.rbac.api;

import cn.vertxup.rbac.service.business.UserStub;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.Addr;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.commune.Envelop;
import io.vertx.up.unity.Ux;

import javax.inject.Inject;

@Queue
public class UserActor {

    @Inject
    private transient UserStub stub;

    /*
     * User information get from the system to extract data here.
     */
    @Address(Addr.User.INFORMATION)
    public Future<JsonObject> information(final Envelop envelop) {
        final String userId = envelop.jwt("user");
        /*
         * Async for user information
         */
        return stub.fetchEmployee(userId);
    }

    @Address(Addr.User.PASSWORD)
    public Future<JsonObject> password(final Envelop envelop) {
        /*
         * Async for user password / modification
         */
        final String userId = envelop.jwt("user");
        final JsonObject params = Ux.getJson(envelop);
        return stub.updateUser(userId, params);
    }
}
