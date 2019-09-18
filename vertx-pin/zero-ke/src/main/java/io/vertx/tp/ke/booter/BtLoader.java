package io.vertx.tp.ke.booter;

import io.vertx.core.*;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.plugin.excel.ExcelClient;
import io.vertx.tp.plugin.excel.ExcelInfix;
import io.vertx.tp.plugin.excel.atom.ExTable;
import io.vertx.tp.plugin.jooq.JooqInfix;
import io.vertx.up.log.Annal;
import io.vertx.up.util.Ut;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    static void ingestExcels(final String folder, final Handler<AsyncResult<Set<ExTable>>> callback) {
        final List<Future> futures = streamFile(folder)
                .map(BtLoader::ingestFuture)
                .collect(Collectors.toList());
        CompositeFuture.all(futures).compose(result -> {
            final List<Set<ExTable>> async = result.list();
            final Set<ExTable> tables = new HashSet<>();
            async.forEach(tables::addAll);
            callback.handle(Future.succeededFuture(tables));
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

    static void ingestExcel(final String filename, final Handler<AsyncResult<Set<ExTable>>> callback) {
        final ExcelClient client = ExcelInfix.getClient();
        client.ingest(filename, handler -> callback.handle(Future.succeededFuture(handler.result())));
    }

    private static Future<Set<ExTable>> ingestFuture(final String filename) {
        final Promise<Set<ExTable>> promise = Promise.promise();
        ingestExcel(filename, handler -> promise.complete(handler.result()));
        return promise.future();
    }

    private static Future<String> importFuture(final String filename) {
        final Promise<String> promise = Promise.promise();
        importSync(filename, handler -> promise.complete(handler.result()));
        return promise.future();
    }
}
