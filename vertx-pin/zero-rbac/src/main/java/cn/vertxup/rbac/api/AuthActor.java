package cn.vertxup.rbac.api;

import cn.vertxup.rbac.service.login.AuthStub;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Session;
import io.vertx.tp.rbac.cv.Addr;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.up.unity.Uson;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;

import javax.inject.Inject;

/*
 * Auth Actor
 */
@Queue
public class AuthActor {

    @Inject
    private transient AuthStub stub;

    @Address(Addr.Auth.LOGIN)
    public Future<JsonObject> login(final JsonObject user) {
        return stub.login(user);
    }

    @Address(Addr.Auth.AUTHORIZE)
    public Future<JsonObject> authorize(final JsonObject data) {
        return stub.authorize(Uson.create(data).denull()
                .remove(AuthKey.RESPONSE_TYPE)
                .convert(AuthKey.CLIENT_ID, AuthKey.F_CLIENT_ID)
                .convert(AuthKey.CLIENT_SECRET, AuthKey.F_CLIENT_SECRET)
                .to());
    }

    @Address(Addr.Auth.TOKEN)
    public Future<JsonObject> token(final JsonObject data, final Session session) {
        return stub.token(data.copy(), session);
    }
}
