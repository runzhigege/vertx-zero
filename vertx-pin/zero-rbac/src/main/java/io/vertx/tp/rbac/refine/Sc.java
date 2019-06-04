package io.vertx.tp.rbac.refine;

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
}
