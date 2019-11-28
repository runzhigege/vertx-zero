package io.vertx.tp.ke.booter;

import io.vertx.core.*;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.plugin.excel.ExcelClient;
import io.vertx.tp.plugin.excel.ExcelInfix;
import io.vertx.tp.plugin.jooq.JooqInfix;
import io.vertx.up.eon.Strings;
import io.vertx.up.fn.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class BtLoader {

    private static final Annal LOGGER = Annal.get(BtLoader.class);

    /*
     * Environment Init for Split Booter
     */
    static {
        /* Excel Init */
        ExcelInfix.init(BtHelper.getVertx());
        /* Jooq Init */
        JooqInfix.init(BtHelper.getVertx());
    }

    static void doImport(final String folder) {
        doImport(folder, Strings.EMPTY);
    }

    static void doImport(final String folder, final String prefix) {
        doImport(folder, prefix, handler -> {
            if (handler.succeeded()) {
                System.exit(0);
            } else {
                if (Objects.nonNull(handler.cause())) {
                    handler.cause().printStackTrace();
                }
            }
        });
    }

    static void doImport(final String folder, final String prefix, final Handler<AsyncResult<List<String>>> callback) {
        final long start = System.currentTimeMillis();
        final Set<String> files = BtHelper.ioFiles(folder, prefix);
        /*
         * Create worker executor for each files
         */
        final CountDownLatch counter = new CountDownLatch(files.size());
        files.forEach(file -> {
            final WorkerExecutor executor = BtHelper.getWorker(file);
            executor.<String>executeBlocking(
                    pre -> pre.handle(doLoading(file).future()),
                    post -> counter.countDown());
            executor.close();
        });
        Fn.safeJvm(() -> {
            counter.await();
            final long end = System.currentTimeMillis();
            LOGGER.info("[ BT ] Imported successfully! files = {0}, time = {1}s",
                    String.valueOf(files.size()), TimeUnit.MILLISECONDS.toSeconds(end - start));
            callback.handle(Ux.future(new ArrayList<>(files)));
        });
    }

    private static Promise<String> doLoading(final String filename) {
        final Promise<String> promise = Promise.promise();
        /* ExcelClient of New */
        final ExcelClient client = ExcelInfix.createClient(BtHelper.getVertx());
        client.loading(filename, handler -> promise.complete(filename));
        return promise;
    }

    static Future<JsonObject> asyncImport(final String filename) {
        final Promise<JsonObject> promise = Promise.promise();
        final WorkerExecutor executor = BtHelper.getWorker(filename);
        executor.<String>executeBlocking(
                pre -> pre.handle(doLoading(filename).future()),
                post -> {
                    if (post.succeeded()) {
                        promise.complete(Ke.Result.bool(filename, Boolean.TRUE));
                    } else {
                        promise.fail(post.cause());
                    }
                });
        executor.close();
        return promise.future();
    }
}
