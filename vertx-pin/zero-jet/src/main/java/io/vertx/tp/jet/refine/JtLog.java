package io.vertx.tp.jet.refine;

import io.vertx.up.log.Annal;

class JtLog {

    private static void info(final Annal logger,
                             final String flag, final String pattern, final Object... args) {
        logger.info("[ Πίδακας δρομολογητή ] ( " + flag + " ) " + pattern, args);
    }

    private static void debug(final Annal logger,
                              final String flag, final String pattern, final Object... args) {
        logger.debug("[ Πίδακας δρομολογητή ] ( " + flag + " ) " + pattern, args);
    }

    private static void error(final Annal logger,
                              final String flag, final String pattern, final Object... args) {
        logger.error("[ Πίδακας δρομολογητή ] ( " + flag + " ) " + pattern, args);
    }

    static void infoInit(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Init", pattern, args);
    }


    static void infoRoute(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Route", pattern, args);
    }

    static void infoWeb(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Web", pattern, args);
    }
}
