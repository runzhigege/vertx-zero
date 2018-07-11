package io.vertx.up.aiki;

import io.vertx.core.json.JsonArray;

import java.util.function.BiConsumer;

class Functions {

    @SuppressWarnings("all")
    static BiConsumer<JsonArray, Object> fnCollectJArray() {
        return (collection, item) -> collection.add(item);
    }
}
