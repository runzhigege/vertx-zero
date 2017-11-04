package io.vertx.zero.exception;

import io.vertx.core.VertxException;

/**
 * Extend from vert.x exception
 * @see io.vertx.core.VertxException
 */
public abstract class ZeroException extends VertxException {
    public ZeroException(final String message) {
        super(message);
    }

    public ZeroException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ZeroException(final Throwable cause) {
        super(cause);
    }
}
