package io.vertx.up.unity;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Values;
import io.vertx.up.fn.Fn;
import io.vertx.up.log.Annal;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

class Async {

    private static final Annal LOGGER = Annal.get(Async.class);

    @SuppressWarnings("all")
    static Future<JsonObject> future(final JsonObject input, final Function<JsonObject, Future<JsonObject>>... queues) {
        if (0 == queues.length) {
            /*
             * None queue here
             */
            return To.future(input);
        } else {
            Future<JsonObject> first = queues[Values.IDX].apply(input);
            if (1 == queues.length) {
                /*
                 * Get first future
                 */
                return first;
            } else {
                /*
                 * future[0]
                 *    .compose(future[1])
                 *    .compose(future[2])
                 *    .compose(...)
                 */
                for (int idx = 1; idx < queues.length; idx++) {
                    first = first.compose(queues[idx]);
                }
                return first;
            }
        }
    }

    static <T> Future<T> future(
            final CompletableFuture<T> completableFuture
    ) {
        final Promise<T> future = Promise.promise();
        completableFuture.thenAcceptAsync(future::complete)
                .exceptionally((ex) -> {
                    LOGGER.jvm(ex);
                    future.fail(ex);
                    return null;
                });
        return future.future();
    }

    @SuppressWarnings("all")
    static <T> Future<JsonObject> toJsonFuture(
            final String pojo,
            final CompletableFuture<T> completableFuture
    ) {
        final Promise<JsonObject> future = Promise.promise();
        Fn.safeSemi(null == completableFuture, null,
                () -> future.complete(new JsonObject()),
                () -> completableFuture.thenAcceptAsync((item) -> Fn.safeSemi(
                        null == item, null,
                        () -> future.complete(new JsonObject()),
                        () -> future.complete(To.toJson(item, pojo))
                )).exceptionally((ex) -> {
                    LOGGER.jvm(ex);
                    future.fail(ex);
                    return null;
                }));
        return future.future();
    }

    @SuppressWarnings("all")
    static <T> Future<JsonArray> toArrayFuture(
            final String pojo,
            final CompletableFuture<List<T>> completableFuture
    ) {
        final Promise<JsonArray> future = Promise.promise();
        Fn.safeSemi(null == completableFuture, null,
                () -> future.complete(new JsonArray()),
                () -> completableFuture.thenAcceptAsync((item) -> Fn.safeSemi(
                        null == item, null,
                        () -> future.complete(new JsonArray()),
                        () -> future.complete(To.toArray(item, pojo))
                )).exceptionally((ex) -> {
                    LOGGER.jvm(ex);
                    future.fail(ex);
                    return null;
                }));
        return future.future();
    }

    @SuppressWarnings("all")
    static <T> Future<JsonObject> toUpsertFuture(final T entity, final String pojo,
                                                 final Supplier<Future<JsonObject>> supplier,
                                                 final Function<JsonObject, JsonObject> updateFun) {
        return Fn.match(
                Fn.fork(() -> Future.succeededFuture(To.toJson(entity, pojo))
                        .compose(item -> null == updateFun ?
                                Future.succeededFuture(item) :
                                Future.succeededFuture(updateFun.apply(item))
                        )
                ),
                Fn.branch(null == entity, supplier));
    }
}
