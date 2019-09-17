package io.vertx.tp.ke.booter;

import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.plugin.excel.ExcelClient;
import io.vertx.tp.plugin.excel.ExcelInfix;
import io.vertx.tp.plugin.jooq.JooqInfix;
import io.vertx.up.log.Annal;
import io.vertx.up.util.Ut;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class BtLoader {
    /*
     * Environment Init for Split Booter
     */
    static {
        /* Excel Init */
        ExcelInfix.init(BtVertx.getVertx());
        /* Jooq Init */
        JooqInfix.init(BtVertx.getVertx());
    }

    static void importSyncs(final String folder) {
        streamFile(folder).forEach(BtLoader::importSync);
    }

    static void importSyncs(final String folder, final String prefix) {
        streamFile(folder)
                .filter(filename -> filename.startsWith(folder + prefix))
                .forEach(BtLoader::importSync);
    }

    static void importSyncs(final String folder, final Handler<AsyncResult<List<String>>> callback) {
        final List<Future> futures = streamFile(folder)
                .map(BtLoader::importFuture)
                .collect(Collectors.toList());
        CompositeFuture.all(futures).compose(result -> {
            final List<String> async = result.list();
            callback.handle(Future.succeededFuture(async));
            return Future.succeededFuture(Boolean.TRUE);
        });
    }

    private static void importSync(final String filename) {
        importSync(filename, handler -> asyncOut(handler.result()));
    }

    private static Stream<String> streamFile(final String folder) {
        return Ut.ioFiles(folder).stream()
                .filter(file -> !file.startsWith("~"))
                .map(file -> folder + file);
    }

    static void importSync(final String filename, final Handler<AsyncResult<String>> callback) {
        /* ExcelClient */
        final ExcelClient client = ExcelInfix.getClient();
        /* Single file */
        client.loading(filename, handler -> callback.handle(Future.succeededFuture(filename)));
    }

    static void asyncOut(final String filename) {
        final Annal logger = Annal.get(BtLoader.class);
        Ke.infoKe(logger, "Successfully to finish loading ! data file = {0}", filename);
    }

    private static Future<String> importFuture(final String filename) {
        final Future<String> future = Future.future();
        importSync(filename, handler -> future.complete(handler.result()));
        return future;
    }
}
