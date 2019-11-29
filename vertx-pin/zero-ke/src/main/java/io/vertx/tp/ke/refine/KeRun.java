package io.vertx.tp.ke.refine;

import io.vertx.core.Future;
import io.vertx.tp.optic.Pocket;
import io.vertx.up.unity.Ux;

import java.util.Objects;
import java.util.function.Function;

class KeRun {

    static <T, O> Future<O> channel(final Class<T> clazz, final O defaultValue,
                                    final Function<T, Future<O>> executor) {
        final T channel = Pocket.lookup(clazz);
        if (Objects.isNull(channel)) {
            return Ux.future(defaultValue);
        } else {
            return executor.apply(channel);
        }
    }

    static <T, O> O channelSync(final Class<T> clazz, final O defaultValue,
                                final Function<T, O> executor) {
        final T channel = Pocket.lookup(clazz);
        if (Objects.isNull(channel)) {
            return defaultValue;
        } else {
            return executor.apply(channel);
        }
    }

    static <T, O> Future<O> channelAsync(final Class<T> clazz, final Future<O> future,
                                         final Function<T, Future<O>> executor) {
        final T channel = Pocket.lookup(clazz);
        if (Objects.isNull(channel)) {
            return future;
        } else {
            return executor.apply(channel);
        }
    }
}
