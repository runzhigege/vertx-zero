package cn.vertxup.erp.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

/*
 * This api provide service interface to do following things
 * 1) When created employee, the interface should update related user information
 *    call `EcUser` service for updating
 */
public interface EmployeeStub {

    Future<JsonObject> createAsync(JsonObject data);

    Future<JsonObject> fetchAsync(String key);

    Future<JsonObject> updateAsync(String key, JsonObject data);

    Future<Boolean> deleteAsync(String key);
}
