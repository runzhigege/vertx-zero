package io.vertx.tp.error;

import io.vertx.zero.exception.UpException;

public class PluginUpException extends UpException {

    public PluginUpException(final Class<?> clazz,
                             final String name,
                             final String message) {
        super(clazz, name, message);
    }

    @Override
    public int getCode() {
        return -40019;
    }
}
