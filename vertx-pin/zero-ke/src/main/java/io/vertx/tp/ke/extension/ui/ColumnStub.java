package io.vertx.tp.ke.extension.ui;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.UxJooq;

/*
 * Column implementation by some specific definition
 * Only full column contains render part for different usage
 */
public interface ColumnStub {

    ColumnStub on(UxJooq jooq);

    /*
     * Read full columns
     */
    Future<JsonArray> fetchFull(JsonObject filters);
}
