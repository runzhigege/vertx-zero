package io.vertx.tp.ke.refine;

import io.vertx.up.log.Annal;

class KeLog {
    private static void info(final Annal logger,
                             final String flag, final String pattern, final Object... args) {
        logger.info("[ Εισόδημα ] ( " + flag + " ) " + pattern, args);
    }

    static void infoKe(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Ke", pattern, args);
    }
}
