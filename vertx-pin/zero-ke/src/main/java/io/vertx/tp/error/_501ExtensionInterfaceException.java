package io.vertx.tp.error;

import io.vertx.core.http.HttpStatusCode;
import io.vertx.up.exception.WebException;

public class _501ExtensionInterfaceException extends WebException {

    public _501ExtensionInterfaceException(final Class<?> clazz,
                                           final String className,
                                           final String interfaceName) {
        super(clazz, className, interfaceName);
    }

    @Override
    public int getCode() {
        return -60039;
    }

    @Override
    public HttpStatusCode getStatus() {
        return HttpStatusCode.NOT_IMPLEMENTED;
    }
}
