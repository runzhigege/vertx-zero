package io.vertx.tp.rbac.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public class AuthService implements AuthStub {

    private transient final ScUser scUser = ScUser.create();

    @Override
    public Future<JsonObject> authorize(final JsonObject filters, final String state) {
        return null;
    }

    @Override
    public Future<JsonObject> token(final JsonObject filters, final String state) {
        return null;
    }

    @Override
    public Future<JsonObject> login(final String username, final String password) {
        return this.scUser.login(username, password);
    }
}
