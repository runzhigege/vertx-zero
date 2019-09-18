package io.vertx.tp.ke.booter;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.tp.plugin.excel.atom.ExTable;

import java.util.List;
import java.util.Set;

/*
 * Split booter for some divide application of tool
 * 1) Loader
 * - importSyncs, read folder data and do data loading based on jooq configuration.
 * - importSync, read file data and do data loading based on jooq configuration.
 * - importExcel, read file data and left consumer for continue process.
 */
public class Bt {

    public static Vertx getVertx() {
        return BtVertx.getVertx();
    }

    /*
     * importSyncs
     */
    public static void importSyncs(final String folder) {
        BtLoader.importSyncs(folder);
    }

    public static void importSyncs(final String folder, final String prefix) {
        BtLoader.importSyncs(folder, prefix);
    }

    public static void importSyncs(final String folder, final Handler<AsyncResult<List<String>>> callback) {
        BtLoader.importSyncs(folder, callback);
    }

    public static void importSync(final String filename) {
        BtLoader.importSync(filename, handler -> BtLoader.asyncOut(handler.result()));
    }

    public static void importSync(final String filename, final Handler<AsyncResult<String>> callback) {
        BtLoader.importSync(filename, callback);
    }

    public static void ingestExcels(final String folder, final Handler<AsyncResult<Set<ExTable>>> callback) {
        BtLoader.ingestExcels(folder, callback);
    }
}
