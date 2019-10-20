package io.vertx.up.unity;

import io.reactivex.Observable;
import io.vertx.core.*;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception.web._500InternalServerException;
import io.vertx.up.util.Ut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
