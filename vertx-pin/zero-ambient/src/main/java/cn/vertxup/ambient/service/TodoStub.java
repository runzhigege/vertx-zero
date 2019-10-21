package cn.vertxup.ambient.service;

import io.vertx.core.Future;
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
    Future<JsonObject> createTodo(final String type, final JsonObject data);
}
