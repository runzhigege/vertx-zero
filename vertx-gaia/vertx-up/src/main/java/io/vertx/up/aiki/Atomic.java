package io.vertx.up.aiki;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.zero.epic.fn.Fn;

import java.util.function.Function;

class Atomic {

    static Function<JsonObject, Future<JsonObject>> joinTo(final JsonObject to, final String field) {
        return Fn.getNull(Future::succeededFuture, () -> source -> {
            if (source.containsKey(field)) {
                to.put(field, source.getValue(field));
            }
            return Future.succeededFuture(source);
        }, to);
    }

    static Function<JsonObject, Future<JsonObject>> joinFrom(final JsonObject from, final String field) {
        return Fn.getNull(Future::succeededFuture, () -> source -> {
            if (from.containsKey(field)) {
                source.put(field, from.getValue(field));
            }
            return Future.succeededFuture(source);
        }, from);
    }
}
