package io.vertx.up.epic;

import io.vertx.up.log.Errors;
import io.vertx.up.exception.heart.ArgumentException;

final class Ensurer {
    private Ensurer() {
    }

    static void eqLength(final Class<?> clazz, final int expected, final Object... args) {
        if (expected != args.length) {
            final String method = Errors.method(Ensurer.class, "eqLength");
            throw new ArgumentException(clazz, method, expected, "=");
        }
    }

    static void gtLength(final Class<?> clazz, final int min, final Object... args) {
        if (min >= args.length) {
            final String method = Errors.method(Ensurer.class, "gtLength");
            throw new ArgumentException(clazz, method, min, ">");
        }
    }
}
