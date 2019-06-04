package io.vertx.tp.plugin.excel.cell;

import java.util.UUID;

/**
 * {UUID} Processing
 */
class UuidValue implements ExValue {

    @Override
    @SuppressWarnings("all")
    public String to(final Object value) {
        return UUID.randomUUID().toString();
    }
}
