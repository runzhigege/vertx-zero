package cn.vertxup.rbac.api;

import cn.vertxup.rbac.service.business.UserStub;
import cn.vertxup.rbac.service.login.LoginStub;
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

    @Inject
    private transient LoginStub loginStub;

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

    @Address(Addr.User.PROFILE)
    public Future<JsonObject> profile(final Envelop envelop) {
        final String userId = envelop.jwt("user");
        final JsonObject params = Ux.getJson(envelop);
        return stub.updateEmployee(userId, params);
    }

    @Address(Addr.Auth.LOGOUT)
    public Future<Boolean> logout(final Envelop envelop) {
        final String token = envelop.jwt();
        final String habitus = envelop.jwt("habitus");
        return loginStub.logout(token, habitus);
    }

    @Address(Addr.User.GET)
    public Future<JsonObject> getById(final String key) {
        return stub.fetchUser(key);
    }

    @Address(Addr.User.ADD)
    public Future<JsonObject> create(final JsonObject data) {
        return stub.createUser(data);
    }

    @Address(Addr.User.UPDATE)
    public Future<JsonObject> update(final String key, final JsonObject data) {
        return stub.updateUser(key, data);
    }

    @Address(Addr.User.DELETE)
    public Future<Boolean> delete(final String key) {
        return stub.deleteUser(key);
    }
}
