package io.vertx.tp.rbac.service.login;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface AuthStub {

    /**
     * Exchange authorization code
     * by filters ( JsonObject )
     */
    Future<JsonObject> authorize(JsonObject filters, String state);

    /**
     * Exchange token with authorization code
     */
    Future<JsonObject> token(JsonObject filters, String state);

    /**
     * Login with "username/password"
     */
    Future<JsonObject> login(JsonObject params);
}
