package io.vertx.up.unity;

import io.vertx.core.Future;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

class Complex {

    static <T> Future<T> complex(final T input, final Predicate<T> predicate, final Supplier<Future<T>> executor) {
        if (Objects.isNull(input)) {
            return To.future(null);
        } else {
            if (Objects.isNull(executor)) {
                return To.future(input);
            } else {
                if (Objects.isNull(predicate)) {
                    return executor.get();
                } else {
                    if (predicate.test(input)) {
                        return executor.get();
                    } else {
                        return To.future(input);
                    }
                }
            }
        }
    }
}
