package io.vertx.tp.crud.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.kern.AbstractVertical;

/*
 * Default Implementation
 */
public class ColService extends AbstractVertical {
    @Override
    public Future<JsonArray> fetchMyColumns(final JsonObject filters) {
        return null;
    }

    @Override
    public Future<JsonArray> saveMyColumns(final JsonObject filters) {
        return null;
    }

    @Override
    public Future<JsonArray> fetchFullColumns(final JsonObject filters) {
        return null;
    }
}
