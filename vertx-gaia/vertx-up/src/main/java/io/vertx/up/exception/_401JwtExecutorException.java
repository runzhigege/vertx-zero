package io.vertx.up.exception;

import io.vertx.core.http.HttpStatusCode;
import io.vertx.core.json.JsonObject;

public class _401JwtExecutorException extends WebException {

    public _401JwtExecutorException(final Class<?> clazz,
                                    final JsonObject auth) {
        super(clazz, auth);
    }

    @Override
    public int getCode() {
        return -60033;
    }

    @Override
    public HttpStatusCode getStatus() {
        return HttpStatusCode.UNAUTHORIZED;
    }
}

