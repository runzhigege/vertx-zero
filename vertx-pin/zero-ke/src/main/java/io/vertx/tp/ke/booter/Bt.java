package io.vertx.tp.ke.booter;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.tp.plugin.excel.ExcelInfix;
import io.vertx.tp.plugin.jooq.JooqInfix;

/*
 * Split booter for some divide application of tool
 * 1) Loader
 */
public class Bt {
    /*
     * Environment Init for Split Booter
     */
    static {
        /* Prepare another vert.x instance */
        final VertxOptions options = new VertxOptions();
        options.setMaxEventLoopExecuteTime(30000000000L);
        final Vertx vertx = Vertx.vertx(options);

        /* Excel Init */
        ExcelInfix.init(vertx);
        /* Jooq Init */
        JooqInfix.init(vertx);
    }

    public static void importExcels(final String folder) {
        BtLoader.importExcels(folder);
    }

    public static void importExcel(final String filename) {
        BtLoader.importExcel(filename);
    }
}
