package io.vertx.tp.optic.business;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/*
 * ExBack callback for integration post
 */
public interface ExBack {
    /*
     * 分组处理，需要重新绑定
     */
    ExBack instance();

    Future<JsonObject> writeAsync(JsonObject backData);

    Future<JsonArray> writeAsync(JsonArray backData);
}
