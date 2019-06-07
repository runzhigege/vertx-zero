package io.vertx.tp.rbac.refine;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.up.aiki.Uarr;
import io.vertx.up.aiki.Ux;

class ScFn {

    static <T> Future<JsonArray> relation(final String field, final String key, final Class<?> daoCls) {
        return Ux.Jooq.on(daoCls).<T>fetchAsync(field, key)
                .compose(Ux::fnJArray)
                .compose(relation -> Uarr.create(relation)
                        .remove(field).toFuture());
    }
}
