package io.vertx.tp.plugin.excel;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.plugin.excel.atom.ExTable;
import io.vertx.up.eon.TpClient;

import java.util.Set;

/**
 * ExcelClient for office excel data loading
 * Apache Poi
 */
public interface ExcelClient extends TpClient<ExcelClient> {

    String MAPPING = "mapping";

    static ExcelClient createShared(final Vertx vertx, final JsonObject config) {
        return new ExcelClientImpl(vertx, config);
    }

    @Fluent
    @Override
    ExcelClient init(JsonObject params);

    /**
     * Excel file data loading
     *
     * @param filename data input file
     * @param handler  callback handler to process Set<ExTable>
     * @return self reference
     */
    @Fluent
    <T> ExcelClient loading(String filename, Handler<AsyncResult<Set<T>>> handler);

    @Fluent
    ExcelClient ingestList(String filename, Handler<AsyncResult<Set<ExTable>>> handler);

    @Fluent
    ExcelClient ingest(String filename, Handler<AsyncResult<ExTable>> handler);
}
