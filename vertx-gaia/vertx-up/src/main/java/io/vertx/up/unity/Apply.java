package io.vertx.up.unity;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

class Apply {

    static <T> Function<T, Future<JsonObject>> applyField(final JsonObject input, final String field) {
        return data -> {
            input.put(field, data);
            return To.toFuture(input);
        };
    }

    static <I, T> Function<I, Future<T>> applyNil(final Supplier<T> supplier, final Supplier<Future<T>> executor) {
        return input -> {
            if (Objects.isNull(input)) {
                return To.toFuture(supplier.get());
            } else {
                return executor.get();
            }
        };
    }

    static <I, T> Function<I, Future<T>> applyNil(final Supplier<T> supplier, final Function<I, Future<T>> executor) {
        return input -> {
            if (Objects.isNull(input)) {
                return To.toFuture(supplier.get());
            } else {
                return executor.apply(input);
            }
        };
    }

    static <T> Function<T, Future<T>> applyNil(final Function<T, Future<T>> executor) {
        return input -> {
            if (Objects.isNull(input)) {
                return To.toFuture(input);
            } else {
                return executor.apply(input);
            }
        };
    }
}
