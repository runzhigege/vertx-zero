package io.vertx.tp.ambient.refine;

import io.vertx.up.log.Annal;

/*
 * Tool class available in current service only
 */
public class At {
    /*
     * Log
     */
    public static void infoInit(final Annal logger, final String pattern, final Object... args) {
        AtLog.infoInit(logger, pattern, args);
    }

    public static void infoEnv(final Annal logger, final String pattern, final Object... args) {
        AtLog.infoEnv(logger, pattern, args);
    }
}
