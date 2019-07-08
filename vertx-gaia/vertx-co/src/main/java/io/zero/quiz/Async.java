package io.zero.quiz;

import io.vertx.core.Future;
import io.vertx.ext.unit.TestContext;

import java.util.function.Consumer;

public class Async {

    public static <T> void async(final TestContext context,
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
}
