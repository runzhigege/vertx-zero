package io.vertx.tp.error;

import io.vertx.core.http.HttpStatusCode;
import io.vertx.up.exception.WebException;

public class _409UniqueAmbiguityException extends WebException {

    public _409UniqueAmbiguityException(final Class<?> clazz,
                                        final String module,
                                        final String headers) {
        super(clazz, module, headers);
    }

    @Override
    public int getCode() {
        return -60038;
    }

    @Override
    public HttpStatusCode getStatus() {
        return HttpStatusCode.BAD_REQUEST;
    }
}
