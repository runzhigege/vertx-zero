package io.vertx.tp.plugin.excel;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.plugin.excel.atom.ExTable;
import io.vertx.up.log.Annal;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Set;

public class ExcelClientImpl implements ExcelClient {

    private static final Annal LOGGER = Annal.get(ExcelClientImpl.class);

    private transient final Vertx vertx;
    private transient final ExcelHelper helper = ExcelHelper.helper(this.getClass());

    ExcelClientImpl(final Vertx vertx, final JsonObject config) {
        this.vertx = vertx;
        System.err.println(config);
    }

    @Override
    public ExcelClient init(final JsonObject params) {
        return this;
    }

    @Override
    public ExcelClient loading(final String filename, final Handler<AsyncResult<Set<ExTable>>> handler) {
        /* 1. Get Workbook reference */
        final Workbook workbook = this.helper.getWorkbook(filename);
        /* 2. Iterator for Sheet */
        final Set<ExTable> tables = this.helper.getExTables(workbook);
        /* 3. Loading data into the system */

        return this;
    }
}
