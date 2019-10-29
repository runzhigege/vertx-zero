package io.vertx.tp.ke.refine;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.optic.fantom.Fabric;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.function.Function;

class KeFabric {

    static Function<JsonObject, Future<JsonObject>> combineAsync(final String field) {
        return json -> {
            if (Ut.isNil(json) || !json.containsKey(field)) {
                return Ux.toFuture(json);
            } else {
                final Class<?> clazz = Ut.clazz(json.getString(field));
                final Fabric<JsonObject> fabric = Ut.instance(clazz);
                return fabric.combine(json);
            }
        };
    }
}
