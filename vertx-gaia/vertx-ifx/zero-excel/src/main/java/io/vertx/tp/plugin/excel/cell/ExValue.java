package io.vertx.tp.plugin.excel.cell;

import java.util.Objects;

/*
 * Cell Processing for value
 */
@FunctionalInterface
public interface ExValue {
    /*
     * Get value reference to process value
     */
    @SuppressWarnings("all")
    static ExValue get(final Object value) {
        if (Objects.isNull(value)) {
            return PureValue.create();
        }
        final ExValue reference = Pool.VALUE_MAP.get(value);
        if (Objects.isNull(reference)) {
            return PureValue.create();
        } else {
            return reference;
        }
    }

    <T> T to(Object value);
}
