package io.vertx.tp.kern;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.UxJooq;

/*
 * Column implementation by some specific definition
 * 1. Read full columns
 * 2. Save my columns
 * 3. Read my columns
 */
public interface ColStub {

    ColStub on(UxJooq jooq);

    /*
     * Read my columns
     */
    Future<JsonArray> fetchMyColumns(JsonObject filters);

    /*
     * Save my columns
     */
    Future<JsonArray> saveMyColumns(JsonObject filters);

    /*
     * Read full columns
     */
    Future<JsonArray> fetchFullColumns(JsonObject filters);
}
