package io.vertx.tp.ke.refine;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.atom.KeMetadata;
import io.vertx.up.fn.Fn;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.Objects;
import java.util.function.Function;

class KeElement {

    static Function<JsonObject, Future<JsonObject>> metadata(final String field) {
        return response -> Fn.getJvm(Future.succeededFuture(new JsonObject()), () -> {
            final String data = response.getString(field);
            if (Objects.nonNull(data) && Ut.isJObject(data)) {
                response.put(field, parseMetadata(new JsonObject(data)));
            }
            return Ux.toFuture(response);
        }, response);
    }

    /*
     * Spec metadata data structure of Json normalized.
     */
    private static JsonObject parseMetadata(final JsonObject metadata) {
        assert Objects.nonNull(metadata) : "Here input metadata should not be null";
        /*
         * Structure that will be parsed here.
         */
        return new KeMetadata(metadata).toJson();
    }
}
