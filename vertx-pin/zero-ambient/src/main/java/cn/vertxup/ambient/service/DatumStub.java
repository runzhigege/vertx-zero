package cn.vertxup.ambient.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public interface DatumStub {

    Future<JsonArray> tabulars(String appId, JsonArray types);

    Future<JsonArray> tabulars(String appId, String type);

    Future<JsonObject> tabular(String appId, String type, String code);

    Future<JsonArray> categories(String appId, JsonArray types);

    Future<JsonArray> categories(String appId, String type);

    Future<JsonObject> category(String appId, String type, String code);

    Future<JsonArray> generate(String appId, String code, Integer count);
}
