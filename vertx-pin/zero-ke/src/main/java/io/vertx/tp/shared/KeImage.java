package io.vertx.tp.shared;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.Ux;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.util.function.Function;

class KeImage {
    static Function<JsonObject, Future<JsonObject>> image(final String field) {
        return response -> Fn.getJvm(Future.succeededFuture(new JsonObject()), () -> {
            final String image = response.getString(field);
            if (!Ut.isNil(image)) {
                response.put(field, new JsonArray(image));
            }
            return Ux.toFuture(response);
        }, response);
    }
}
