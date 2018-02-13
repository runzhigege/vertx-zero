package io.vertx.up.aiki;


import io.vertx.core.Future;
import io.vertx.up.aiki.fun.Case;
import io.vertx.up.func.Actuator;

import java.util.function.Consumer;
import java.util.function.Supplier;

class Wait {

    static <T> Future<T> then(final Consumer<Future<T>> consumer) {
        final Future<T> future = Future.future();
        consumer.accept(future);
        return future;
    }

    static <T> Case<T> branch(
            final Supplier<Future<T>> caseLine) {
        return Case.item(caseLine);
    }

    static <T> Case<T> branch(
            final Actuator executor,
            final Supplier<Future<T>> caseLine
    ) {
        if (null != executor) executor.execute();
        return Case.item(caseLine);
    }

    static <T> Case<T> branch(
            final boolean condition,
            final Supplier<Future<T>> caseLine
    ) {
        return Case.item(() -> condition, caseLine);
    }

    static <T> Case<T> branch(
            final boolean condition,
            final Actuator executor,
            final Supplier<Future<T>> caseLine) {
        if (null != executor) executor.execute();
        return branch(condition, caseLine);
    }

    static <T> Case<T> match(
            final Case.DefaultCase<T> defaultCase,
            final Case<T>... matchers
    ) {
        for (final Case<T> each : matchers) {
            if (each.first.get()) return each;
        }
        return defaultCase;
    }
}
