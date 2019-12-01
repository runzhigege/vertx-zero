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
            KeLog.warnChannel(KeRun.class, "Channel {0} null", clazz.getName());
            return Ux.future(defaultValue);
        } else {
            KeLog.infoChannel(KeRun.class, "Channel Async selected {0}", channel.getClass().getName());
            return executor.apply(channel);
        }
    }

    static <T, O> O channelSync(final Class<T> clazz, final O defaultValue,
                                final Function<T, O> executor) {
        final T channel = Pocket.lookup(clazz);
        if (Objects.isNull(channel)) {
            KeLog.warnChannel(KeRun.class, "Channel Sync {0} null", clazz.getName());
            return defaultValue;
        } else {
            KeLog.infoChannel(KeRun.class, "Channel Sync selected {0}", channel.getClass().getName());
            return executor.apply(channel);
        }
    }

    static <T, O> Future<O> channelAsync(final Class<T> clazz, final Future<O> future,
                                         final Function<T, Future<O>> executor) {
        final T channel = Pocket.lookup(clazz);
        if (Objects.isNull(channel)) {
            KeLog.warnChannel(KeRun.class, "Channel Async {0} null", clazz.getName());
            return future;
        } else {
            KeLog.infoChannel(KeRun.class, "Channel Async selected {0}", channel.getClass().getName());
            return executor.apply(channel);
        }
    }
}
