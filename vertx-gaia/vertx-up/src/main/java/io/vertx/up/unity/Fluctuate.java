package io.vertx.up.unity;

import io.reactivex.Observable;
import io.vertx.core.*;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Values;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception.web._500InternalServerException;
import io.vertx.up.util.Ut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;

@SuppressWarnings("unchecked")
class Fluctuate {

    static Future<JsonArray> thenCombine(
            final Future<JsonArray> source,
            final Function<JsonObject, Future<JsonObject>> generateFun,
            final BinaryOperator<JsonObject> operatorFun
    ) {
        return source.compose(first -> {
            final List secondFutures = new ArrayList<>();
            Observable.fromIterable(first)
                    .map(item -> (JsonObject) item)
                    .map(generateFun::apply)
                    .subscribe(secondFutures::add)
                    .dispose();
            final Promise<JsonArray> result = Promise.promise();
            CompositeFuture.all(secondFutures).setHandler(res -> {
                final List<JsonObject> secondary = res.result().list();
                // Zipper Operation, the base list is first
                final List<JsonObject> completed = Ut.elementZip(first.getList(), secondary, operatorFun);
                result.complete(new JsonArray(completed));
            });
            return result.future();
        });
    }

    static Future<JsonObject> thenCombine(
            final Future<JsonObject>... futures
    ) {
        final Promise<JsonObject> promise = Promise.promise();
        // thenResponse
        CompositeFuture.all(Arrays.asList(futures)).setHandler(thenResponse(promise, (finished) -> {
            final JsonObject resultMap = new JsonObject();
            if (null != finished) {
                Ut.itList(finished.list(), (item, index) -> resultMap.put(index.toString(), item));
            }
            return resultMap;
        }));
        return promise.future();
    }

    static <T> Future<ConcurrentMap<String, T>> thenCombine(
            final ConcurrentMap<String, Future<T>> futureMap
    ) {
        final Promise<ConcurrentMap<String, T>> promise = Promise.promise();
        final List<String> keys = new ArrayList<>();
        final List<Future> futures = new ArrayList<>();
        futureMap.forEach((key, future) -> {
            keys.add(key);
            futures.add(future);
        });
        CompositeFuture.all(futures).setHandler(thenResponse(promise, (finished) -> {
            final List<T> list = finished.list();
            /*
             * Index mapping
             */
            final int size = list.size();
            final ConcurrentMap<String, T> resultMap = new ConcurrentHashMap<>();
            for (int idx = Values.IDX; idx < size; idx++) {
                final String key = keys.get(idx);
                final T result = list.get(idx);
                if (Ut.notNil(key)) {
                    resultMap.put(key, result);
                }
            }
            return resultMap;
        }));
        return promise.future();
    }

    static Future<JsonArray> thenCombineArray(
            final List<Future<JsonArray>> futures
    ) {
        final Promise<JsonArray> promise = Promise.promise();
        // thenResponse
        final List<Future> futureList = new ArrayList<>(futures);
        CompositeFuture.all(futureList).setHandler(thenResponse(promise, (finished) -> {
            final JsonArray resultMap = new JsonArray();
            if (null != finished) {
                Ut.itList(finished.list(), (item, index) -> resultMap.add(item));
            }
            return resultMap;
        }));
        return promise.future();
    }

    static Future<JsonObject> thenCombine(
            final Future<JsonObject> source,
            final Function<JsonObject, List<Future>> generateFun,
            final BiConsumer<JsonObject, JsonObject>... operatorFun
    ) {
        return source.compose(first -> {
            final List<Future> secondFutures = generateFun.apply(first);
            final Promise<JsonObject> promise = Promise.promise();
            // thenResponse
            CompositeFuture.all(secondFutures).setHandler(thenResponse(promise, (finished) -> {
                if (null != finished) {
                    final List<JsonObject> secondary = finished.list();
                    // Zipper Operation, the base list is first
                    Ut.itList(secondary, (item, index) -> operatorFun[index].accept(first, item));
                }
                return first;
            }));
            return promise.future();
        });
    }

    static Future<JsonArray> thenCombine(
            final List<Future<JsonObject>> futures
    ) {
        final Promise<JsonArray> promise = Promise.promise();
        final List<Future> converted = new ArrayList<>(futures);
        // thenResponse
        CompositeFuture.all(converted).setHandler(thenResponse(promise, (finished) ->
                null == finished ? new JsonArray() : new JsonArray(finished.list())
        ));
        return promise.future();
    }

    static <T> Future<T> thenError(
            final Class<? extends WebException> clazz,
            final Object... args
    ) {
        final WebException error = To.toError(clazz, args);
        return Future.failedFuture(error);
    }

    /*
     * List<Future<Map<String,T>>> futures ->
     *      Future<Map<String,T>>
     * Exchange data by key here.
     *      The binary operator should ( T, T ) -> T
     */
    static <T> Future<ConcurrentMap<String, T>> thenCompress(
            final List<Future<ConcurrentMap<String, T>>> futures,
            final BinaryOperator<T> binaryOperator
    ) {
        /* thenResponse */
        final Promise<ConcurrentMap<String, T>> promise = Promise.promise();
        final List<Future> converted = new ArrayList<>(futures);
        CompositeFuture.all(converted).setHandler(thenResponse(promise, (finished) -> {
            final ConcurrentMap<String, T> resultMap = new ConcurrentHashMap<>();
            if (Objects.nonNull(finished)) {
                final List<ConcurrentMap<String, T>> result = finished.list();

                final BinaryOperator<T> mergeOperator = Objects.isNull(binaryOperator) ?
                        /*
                         * Default set merged function to
                         * latest replace original T in result map
                         * For other situation, the system should call binaryOperator
                         * to merge (T, T) -> T
                         * 1) JsonArray
                         * 2) List<T>
                         * 3) Others
                         *
                         * */
                        (original, latest) -> latest : binaryOperator;
                /*
                 * List<ConcurrentMap<String,T>> result ->
                 *      ConcurrentMap<String,T>
                 */
                result.stream().filter(Objects::nonNull).forEach(each -> each.keySet()
                        .stream().filter(key -> Objects.nonNull(each.get(key))).forEach(key -> {
                            final T combined;
                            if (resultMap.containsKey(key)) {
                                /*
                                 * Merged key -> value to result
                                 */
                                final T original = resultMap.get(key);
                                final T latest = each.get(key);
                                combined = mergeOperator.apply(original, latest);
                            } else {
                                /*
                                 * Extract combined
                                 */
                                combined = each.get(key);
                            }
                            resultMap.put(key, combined);
                        }));
            } else {
                promise.complete(resultMap);
            }
            return resultMap;
        }));
        return promise.future();
    }


    private static <T> Handler<AsyncResult<CompositeFuture>> thenResponse(
            final Promise<T> promise,
            final Function<CompositeFuture, T> fun) {
        return res -> {
            if (res.succeeded()) {
                final T callback = fun.apply(res.result());
                promise.complete(callback);
            } else {
                if (null != res.cause()) {
                    res.cause().printStackTrace();
                    promise.fail(res.cause());
                } else {
                    promise.fail(new _500InternalServerException(Fluctuate.class, null));
                }
            }
        };
    }
}
