package io.vertx.tp.ke.tool;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.Ux;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;
import io.zero.epic.Ut;

import java.util.function.Supplier;

class KeTool {

    private static final Node<JsonObject> VISITOR = Ut.singleton(ZeroUniform.class);

    static String getCatalog() {
        final JsonObject config = VISITOR.read();
        return Ut.visitString(config, "jooq", "provider", "catalog");
    }

    static <T> Future<T> poolAsync(final String name,
                                   final String key,
                                   final Supplier<Future<T>> value) {
        return Ux.Pool.on(name).<String, T>get(key).compose(item -> {
            if (null == item) {
                return value.get().compose(updated ->
                        Ux.Pool.on(name).put(key, updated)
                                .compose(kv -> Ux.toFuture(kv.getValue())));
            } else {
                return Ux.toFuture(item);
            }
        });
    }
}
