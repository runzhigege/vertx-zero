package io.vertx.tp.rbac.refine;

import io.vertx.core.Future;
import io.vertx.up.log.Annal;

import java.util.List;
import java.util.function.Function;

public class Sc {
    /*
     * Log information
     */
    public static void infoAuth(final Annal logger, final String pattern, final Object... args) {
        ScLog.infoAuth(logger, pattern, args);
    }

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

    /*
     * Business logical
     */
    public static String generateCode() {
        return ScTool.generateCode();
    }

    public static String getDatabase() {
        return ScTool.getDatabase();
    }

    public static <T, R> List<R> reduce(final List<T> list, final Function<T, R> function) {
        return ScFn.reduce(list, function);
    }
}
