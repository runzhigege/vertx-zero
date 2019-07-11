package io.vertx.up.unity;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.up.util.container.Refer;
import io.vertx.up.fn.Fn;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

class Functions {

    @SuppressWarnings("all")
    static BiConsumer<JsonArray, Object> fnCollectJArray() {
        return (collection, item) -> collection.add(item);
    }

    static <E, T> Future<E> fnSupplier(final Refer container, final E entity, final Supplier<T> supplier) {
        return Fn.getNull(Future.succeededFuture(), () -> {
            if (null == supplier) {
                container.add(entity);
            } else {
                container.add(supplier.get());
            }
            return Future.succeededFuture(entity);
        }, container, entity);
    }

    static <E, T> Future<E> fnConsumer(final Refer container, final E entity, final Consumer<T> consumer) {
        return Fn.getNull(Future.succeededFuture(), () -> {
            consumer.accept(container.get());
            return Future.succeededFuture(entity);
        }, container, entity, consumer);
    }
}
