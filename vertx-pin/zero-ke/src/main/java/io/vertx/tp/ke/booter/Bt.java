package io.vertx.tp.ke.booter;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

import java.util.List;

/*
 * Split booter for some divide application of tool
 * 1) Loader
 */
public class Bt {

    public static Vertx getVertx() {
        return BtVertx.getVertx();
    }

    public static void importSyncs(final String folder) {
        BtLoader.importSyncs(folder);
    }

    public static void importSyncs(final String folder, final String prefix) {
        BtLoader.importSyncs(folder, prefix);
    }

    public static void importSync(final String filename) {
        BtLoader.importSync(filename, handler -> BtLoader.asyncOut(handler.result()));
    }

    public static void importSync(final String filename, final Handler<AsyncResult<String>> callback) {
        BtLoader.importSync(filename, callback);
    }

    public static void importSyncs(final String folder, final Handler<AsyncResult<List<String>>> callback) {
        BtLoader.importSyncs(folder, callback);
    }
}
