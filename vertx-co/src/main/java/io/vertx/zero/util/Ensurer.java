package io.vertx.zero.util;

import com.vie.hors.ZeroException;
import com.vie.hors.system.ArgumentException;

public final class Ensurer {

    public static void argLength(
            final Class<?> clazz,
            final int expected,
            final Object... args
    ) throws ZeroException {
        if (expected != args.length) {
            final String method = Errors.method(Ensurer.class, "argLength");
            throw new ArgumentException(clazz, method, expected);
        }
    }
}
