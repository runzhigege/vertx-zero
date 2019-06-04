package io.vertx.tp.rbac.service.login;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface TokenStub {

    Future<JsonObject> execute(String clientId, String code, String state);
}
