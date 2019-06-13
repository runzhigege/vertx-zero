package io.vertx.tp.rbac.service.authorization;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

/*
 * Uniform interface for authorization workflow on restful api.
 * Json Data:
 * {
 *      "jwt" : "<Token Value>"
 * }
 */
public interface AccreditStub {

    Future<Boolean> authorize(JsonObject data);
}
