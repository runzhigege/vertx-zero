package io.vertx.tp.ambient.refine;

import io.vertx.up.log.Annal;

class AtLog {

    private static void info(final Annal logger,
                             final String flag, final String pattern, final Object... args) {
        logger.info("[ περιβάλλων ] ( " + flag + " ) " + pattern, args);
    }

    private static void debug(final Annal logger,
                              final String flag, final String pattern, final Object... args) {
        logger.debug("[ περιβάλλων ] ( " + flag + " ) " + pattern, args);
    }

    static void infoEnv(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Env", pattern, args);
    }

    static void infoInit(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Init", pattern, args);
    }
}
