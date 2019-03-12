package io.zero.quiz;

import io.vertx.core.Future;
import io.vertx.ext.unit.TestContext;
import io.vertx.up.aiki.UxJooq;

import java.util.function.Consumer;
import java.util.function.Supplier;

class Async {

    static <T> void async(final TestContext context,
                          final Future<T> future,
                          final Consumer<T> consumer) {
        final io.vertx.ext.unit.Async async = context.async();
        future.setHandler(handler -> {
            if (handler.succeeded()) {
                consumer.accept(handler.result());
            } else {
                handler.cause().printStackTrace();
                context.fail(handler.cause());
            }
            async.complete();
        });
    }

    static <T> void async(final TestContext context,
                          final Supplier<Future<T>> supplier,
                          final Consumer<T> consumer,
                          final Supplier<UxJooq> daoSupplier) {
        final UxJooq jooq = daoSupplier.get();
        if (null != jooq) {
            final Future<T> future = supplier.get();
            Async.async(context, future, consumer);
        }
    }

    static <T> Future<T> async(final TestContext context,
                               final Supplier<Future<T>> supplier,
                               final Supplier<UxJooq> daoSupplier) {
        final UxJooq jooq = daoSupplier.get();
        final Future<T> result = Future.future();
        if (null != jooq) {
            final Future<T> future = supplier.get();
            final io.vertx.ext.unit.Async async = context.async();
            future.setHandler(handler -> {
                if (!handler.succeeded()) {
                    handler.cause().printStackTrace();
                    context.fail(handler.cause());
                    result.handle(handler);
                }
                async.complete();
            });
        }
        return result;
    }
}
