package com.vie.log;

import io.vertx.core.VertxException;

/**
 * Unite Logging system connect to vert.x, tool kit of Vertx-Zero
 */
public interface Annal {

    void vertx(VertxException ex);

    void jvm(Throwable ex);

    void info(String key, Object... args);

    void warn(String key, Object... args);

    void error(String key, Object... args);

    void debug(String key, Object... args);

    static Annal get(final Class<?> clazz) {
        return null;
    }
}
