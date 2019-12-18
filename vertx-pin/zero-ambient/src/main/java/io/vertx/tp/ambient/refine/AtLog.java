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

    static void infoApp(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Application", pattern, args);
    }

    static void infoEnv(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Env", pattern, args);
    }

    static void infoInit(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Init", pattern, args);
    }

    static void infoFile(final Annal logger, final String pattern, final Object... args) {
        info(logger, "File", pattern, args);
    }

    static void infoExec(final Class<?> clazz, final String pattern, final Object... args) {
        final Annal logger = Annal.get(clazz);
        info(logger, "Execution", pattern, args);
    }
}
