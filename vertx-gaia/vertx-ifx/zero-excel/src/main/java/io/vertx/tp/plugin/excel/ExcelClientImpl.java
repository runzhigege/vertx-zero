package io.vertx.tp.plugin.excel;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.plugin.excel.atom.ExTable;
import io.vertx.up.log.Annal;

import java.util.Set;

public class ExcelClientImpl implements ExcelClient {

    private static final Annal LOGGER = Annal.get(ExcelClientImpl.class);

    private transient final Vertx vertx;

    ExcelClientImpl(final Vertx vertx, final JsonObject config) {
        this.vertx = vertx;
    }

    @Override
    public ExcelClient init(final JsonObject params) {
        return this;
    }

    @Override
    public ExcelClient loading(final String filename, final Handler<AsyncResult<Set<ExTable>>> handler) {
        return this;
    }
}
