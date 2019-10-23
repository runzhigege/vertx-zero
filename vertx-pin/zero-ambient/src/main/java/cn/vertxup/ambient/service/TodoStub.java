package cn.vertxup.ambient.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/*
 * XTodo created by type / params
 */
public interface TodoStub {
    /*
     * data: the data that will be input
     * type + params: the default value came from type.json under
     * plugin/ambient/to do/folder
     */
    Future<JsonObject> createTodo(String type, JsonObject data);

    /*
     * fetch X_TODO by `sigma` & `type` & statues
     */
    Future<JsonArray> fetchTodos(String sigma, String type, JsonArray statues);
}
