package io.vertx.up.unity;

import io.reactivex.Observable;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Values;
import io.vertx.up.exception.WebException;
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
class Combine {

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
            return CompositeFuture.join(secondFutures).compose(finished -> {
                final List<JsonObject> secondary = finished.list();
                // Zipper Operation, the base list is first
                final List<JsonObject> completed = Ut.elementZip(first.getList(), secondary, operatorFun);
                return Ux.future(new JsonArray(completed));
            });
        });
    }

    static Future<JsonObject> thenCombine(
            final Future<JsonObject>... futures
    ) {
        return CompositeFuture.join(Arrays.asList(futures)).compose(finished -> {
            final JsonObject resultMap = new JsonObject();
            if (null != finished) {
                Ut.itList(finished.list(), (item, index) -> resultMap.put(index.toString(), item));
            }
            return Future.succeededFuture(resultMap);
        });
    }

    static <T> Future<ConcurrentMap<String, T>> thenCombine(
            final ConcurrentMap<String, Future<T>> futureMap
    ) {
        final List<String> keys = new ArrayList<>();
        final List<Future> futures = new ArrayList<>();
        futureMap.forEach((key, future) -> {
            keys.add(key);
            futures.add(future);
        });
        return CompositeFuture.join(futures).compose(finished -> {
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
            return Future.succeededFuture(resultMap);
        });
    }

    static Future<JsonArray> thenCombineArray(
            final List<Future<JsonArray>> futures
    ) {
        final List<Future> futureList = new ArrayList<>(futures);
        return CompositeFuture.join(futureList).compose(finished -> {
            final JsonArray resultMap = new JsonArray();
            if (null != finished) {
                Ut.itList(finished.list(), (item, index) -> {
                    if (item instanceof JsonArray) {
                        resultMap.addAll((JsonArray) item);
                    }
                });
            }
            return Future.succeededFuture(resultMap);
        });
    }

    static Future<JsonObject> thenCombine(
            final Future<JsonObject> source,
            final Function<JsonObject, List<Future>> generateFun,
            final BiConsumer<JsonObject, JsonObject>... operatorFun
    ) {
        return source.compose(first -> CompositeFuture.join(generateFun.apply(first)).compose(finished -> {
            if (null != finished) {
                final List<JsonObject> secondary = finished.list();
                // Zipper Operation, the base list is first
                Ut.itList(secondary, (item, index) -> operatorFun[index].accept(first, item));
            }
            return Future.succeededFuture(first);
        }));
    }

    static Future<JsonArray> thenCombine(
            final List<Future<JsonObject>> futures
    ) {
        return CompositeFuture.join(new ArrayList<>(futures)).compose(finished -> {
            final JsonArray result = null == finished ? new JsonArray() : new JsonArray(finished.list());
            return Future.succeededFuture(result);
        });
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
        return CompositeFuture.join(new ArrayList<>(futures)).compose(finished -> {
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
            }
            return Future.succeededFuture(resultMap);
        });
    }
    /*
    Old code for future scheduled
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
    } */
}
