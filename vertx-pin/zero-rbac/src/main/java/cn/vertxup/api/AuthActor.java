package cn.vertxup.api;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.Addr;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;

/*
 * Auth Actor
 */
@Queue
public class AuthActor {

    @Address(Addr.Auth.LOGIN)
    public Future<JsonObject> login(final JsonObject user) {

        return Future.succeededFuture();
    }

    @Address(Addr.Auth.AUTHORIZE)
    public Future<JsonObject> authorize(final JsonObject request) {

        return Future.succeededFuture();
    }

    @Address(Addr.Auth.TOKEN)
    public Future<JsonObject> token(final JsonObject auth) {

        return Future.succeededFuture();
    }
}
