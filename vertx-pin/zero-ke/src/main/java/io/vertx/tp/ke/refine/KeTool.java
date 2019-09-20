package io.vertx.tp.ke.refine;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.uca.yaml.Node;
import io.vertx.up.uca.yaml.ZeroUniform;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
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

    static Future<JsonArray> combineAsync(final JsonArray data, final ConcurrentMap<String, String> headers) {
        final JsonArray combined = new JsonArray();
        /* Header sequence */
        final List<String> columns = new ArrayList<>(headers.keySet());
        /* Header */
        final JsonArray header = new JsonArray();
        columns.forEach(column -> header.add(headers.get(column)));
        combined.add(header);

        /* Data Part */
        Ut.itJArray(data, (each, index) -> {
            final JsonArray row = new JsonArray();
            /* Data Part */
            columns.stream().map(each::getValue).forEach(row::add);

            combined.add(row);
        });
        return Ux.toFuture(combined);
    }

    static <T> void consume(final Supplier<T> supplier, final Consumer<T> consumer) {
        final T input = supplier.get();
        if (Objects.nonNull(input)) {
            if (input instanceof String) {
                if (Ut.notNil((String) input)) {
                    consumer.accept(input);
                }
            } else {
                consumer.accept(input);
            }
        }
    }

    static void banner(final String module) {
        System.out.println("-------------------------------------------------------------");
        System.out.println("|                                                           |");
        System.out.println("|     Zero Extension:  " + module);
        System.out.println("|                                                           |");
        System.out.println("-------------------------------------------------------------");
    }
}
