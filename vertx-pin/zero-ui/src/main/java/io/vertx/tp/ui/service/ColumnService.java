package io.vertx.tp.ui.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.extension.ui.AbstractColumn;

public class ColumnService extends AbstractColumn {
    @Override
    public Future<JsonArray> fetchFull(final JsonObject config) {
        System.out.println(config.encodePrettily());
        return Future.succeededFuture(new JsonArray());
    }
}
