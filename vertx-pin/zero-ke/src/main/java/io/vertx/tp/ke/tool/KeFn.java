package io.vertx.tp.ke.tool;

import java.util.Objects;
import java.util.function.Supplier;

class KeFn {
    /* VerticalStub */
    static <T> T generate(final Class<?> clazz, final Supplier<T> supplier) {
        if (Objects.isNull(clazz)) {
            return null;
        } else {
            return supplier.get();
        }
    }
}
