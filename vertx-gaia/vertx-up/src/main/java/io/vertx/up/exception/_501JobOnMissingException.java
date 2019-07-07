package io.vertx.up.exception;

import io.vertx.core.http.HttpStatusCode;

public class _501JobOnMissingException extends WebException {

    public _501JobOnMissingException(final Class<?> clazz,
                                     final String clazzName) {
        super(clazz, clazzName);
    }

    @Override
    public int getCode() {
        return -60042;
    }

    @Override
    public HttpStatusCode getStatus() {
        return HttpStatusCode.NOT_IMPLEMENTED;
    }
}
