package io.vertx.tp.error;

import io.vertx.core.http.HttpStatusCode;
import io.vertx.up.exception.WebException;

public class _501ExtensionAbstractException extends WebException {
    public _501ExtensionAbstractException(final Class<?> clazz,
                                          final String className,
                                          final String abstractName) {
        super(clazz, className, abstractName);
    }

    @Override
    public int getCode() {
        return -60040;
    }

    @Override
    public HttpStatusCode getStatus() {
        return HttpStatusCode.NOT_IMPLEMENTED;
    }
}
