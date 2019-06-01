package io.vertx.tp.error;

import io.vertx.zero.exception.UpException;

public class EventCodexMultiException extends UpException {

    public EventCodexMultiException(final Class<?> clazz,
                                    final Class<?> target) {
        super(clazz, target);
    }

    @Override
    public int getCode() {
        return -40036;
    }
}
