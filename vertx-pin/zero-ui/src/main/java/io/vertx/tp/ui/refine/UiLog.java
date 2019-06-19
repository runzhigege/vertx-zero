package io.vertx.tp.ui.refine;

import io.vertx.up.log.Annal;

class UiLog {

    private static void info(final Annal logger,
                             final String flag, final String pattern, final Object... args) {
        logger.info("[ Διασύνδεση χρήστη ] ( " + flag + " ) " + pattern, args);
    }

    private static void debug(final Annal logger,
                              final String flag, final String pattern, final Object... args) {
        logger.debug("[ Διασύνδεση χρήστη ] ( " + flag + " ) " + pattern, args);
    }

    private static void error(final Annal logger,
                              final String flag, final String pattern, final Object... args) {
        logger.error("[ Διασύνδεση χρήστη ] ( " + flag + " ) " + pattern, args);
    }

    static void infoInit(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Init", pattern, args);
    }

    static void infoWarn(final Annal logger, final String pattern, final Object... args) {
        error(logger, "Warn", pattern, args);
    }
}
