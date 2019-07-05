package io.vertx.zero.exception;

import java.lang.reflect.Method;

public class InvokingSpecException extends UpException {

    public InvokingSpecException(final Class<?> clazz,
                                 final Method method) {
        super(clazz, method.getName());
    }

    @Override
    public int getCode() {
        return -40063;
    }
}
