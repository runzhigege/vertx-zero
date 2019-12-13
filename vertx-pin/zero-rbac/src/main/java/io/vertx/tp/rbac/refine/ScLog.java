package io.vertx.tp.rbac.refine;

import io.vertx.up.log.Annal;

class ScLog {

    private static void info(final Annal logger,
                             final String flag, final String pattern, final Object... args) {
        logger.info("[ Ακριβώς ] ( " + flag + " ) " + pattern, args);
    }

    private static void debug(final Annal logger,
                              final String flag, final String pattern, final Object... args) {
        logger.debug("[ Ακριβώς ] ( " + flag + " ) " + pattern, args);
    }

    static void infoAuth(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Auth", pattern, args);
    }

    static void debugAuth(final Annal logger, final String pattern, final Object... args) {
        debug(logger, "Auth", pattern, args);
    }

    static void infoAudit(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Auditor", pattern, args);
    }

    static void infoInit(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Init", pattern, args);
    }


    static void infoResource(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Resource", pattern, args);
    }

    static void debugCredit(final Annal logger, final String pattern, final Object... args) {
        debug(logger, "Credit", pattern, args);
    }
}
