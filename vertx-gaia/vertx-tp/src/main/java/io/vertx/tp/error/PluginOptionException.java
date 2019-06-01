package io.vertx.tp.error;

import io.vertx.zero.exception.UpException;

public class PluginOptionException extends UpException {

    public PluginOptionException(final Class<?> clazz,
                                 final String name) {
        super(clazz, name);
    }

    @Override
    public int getCode() {
        return -40015;
    }
}
