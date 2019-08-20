package io.vertx.tp.ke.refine;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.fn.Fn;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.function.Function;

class KeElement {

    static Function<JsonObject, Future<JsonObject>> json(final String field) {
        return response -> Fn.getJvm(Future.succeededFuture(new JsonObject()), () -> {
            final String data = response.getString(field);
            if (!Ut.isJObject(data)) {
                response.put(field, new JsonObject(data));
            }
            return Ux.toFuture(response);
        }, response);
    }
}
