package io.vertx.tp.rbac.service.login;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

/*
 * Token exchange interface
 * 1. exchange token by authorization code
 */
public interface TokenStub {

    Future<JsonObject> execute(String clientId, String code, String state);
}
