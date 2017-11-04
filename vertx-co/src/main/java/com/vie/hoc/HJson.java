package com.vie.hoc;

import io.vertx.core.json.JsonObject;

import java.util.function.BiConsumer;

public class HJson {

    public static void execIt(final JsonObject data,
                              final BiConsumer<Object, String> fnIt) {
        HNull.exec(() -> {
            for (final String name : data.fieldNames()) {
                final Object item = data.getValue(name);
                if (null != item) {
                    /**
                     * Swap the value and key.
                     */
                    fnIt.accept(item, name);
                }
            }
        }, data);
    }
}
