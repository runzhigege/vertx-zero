package io.vertx.tp.ke.booter;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.plugin.excel.atom.ExTable;

import java.util.List;
import java.util.Set;

/*
 * Split booter for some divide application of tool
 * 1) Loader
 */
public class Bt {
    /*
     * doImport
     * doImport: with prefix to do filter
     */
    public static void doImport(final String folder) {
        BtLoader.doImport(folder);
    }

    public static void doImport(final String folder, final String prefix) {
        BtLoader.doImport(folder, prefix);
    }

    public static void doImport(final String folder, final Handler<AsyncResult<List<String>>> callback) {
        BtLoader.doImport(folder, null, callback);
    }

    public static Future<JsonObject> asyncImport(final String filename) {
        return BtLoader.asyncImport(filename);
    }

    public static void ingestExcels(final String folder, final Handler<AsyncResult<Set<ExTable>>> callback) {

    }
}
