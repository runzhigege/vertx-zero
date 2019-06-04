package io.vertx.tp.rbac.refine;

import io.vertx.core.Future;
import io.vertx.up.log.Annal;

public class Sc {
    /*
     * Log
     */
    public static void infoAuth(final Annal logger, final String pattern, final Object... args) {
        ScLog.infoAuth(logger, pattern, args);
    }

    /*
     * Log
     */
    public static void infoInit(final Annal logger, final String pattern, final Object... args) {
        ScLog.infoInit(logger, pattern, args);
    }

    /*
     * cache information
     * 1. Code: Authorization Code Cache Pool
     */
    public static <V> Future<V> cacheCode(final String key) {
        return ScSession.code(key);
    }

    public static <V> Future<V> cacheCode(final String key, final V value) {
        return ScSession.code(key, value);
    }

    public static String generateCode() {
        return ScTool.generateCode();
    }
}
