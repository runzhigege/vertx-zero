package cn.vertxup.api;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.Addr;
import io.vertx.tp.rbac.service.AuthStub;
import io.vertx.up.aiki.Uson;
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
        final String username = user.getString("username");
        final String password = user.getString("password");
        return this.stub.login(username, password);
    }

    @Address(Addr.Auth.AUTHORIZE)
    public Future<JsonObject> authorize(final JsonObject data) {
        return this.stub.authorize(Uson.create(data).denull()
                        .remove("response_type")
                        .convert("client_id", "clientId")
                        .convert("client_secret", "clientSecret")
                        .to(),
                data.getString("state"));
    }

    @Address(Addr.Auth.TOKEN)
    public Future<JsonObject> token(final JsonObject auth) {

        return Future.succeededFuture();
    }
}
