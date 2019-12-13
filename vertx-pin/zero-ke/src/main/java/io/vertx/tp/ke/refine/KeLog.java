package io.vertx.tp.ke.refine;

import io.vertx.up.log.Annal;

class KeLog {
    private static void info(final Annal logger,
                             final String flag, final String pattern, final Object... args) {
        logger.info("[ Εισόδημα ] ( " + flag + " ) " + pattern, args);
    }

    private static void warn(final Annal logger,
                             final String flag, final String pattern, final Object... args) {
        logger.warn("[ Εισόδημα ] ( " + flag + " ) " + pattern, args);
    }

    private static void debug(final Annal logger,
                              final String flag, final String pattern, final Object... args) {
        logger.debug("[ Εισόδημα ] ( " + flag + " ) " + pattern, args);
    }

    static void infoKe(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Ke", pattern, args);
    }


    static void debugKe(final Annal logger, final String pattern, final Object... args) {
        debug(logger, "Ke", pattern, args);
    }

    static void infoChannel(final Class<?> clazz, final String pattern, final Object... args) {
        final Annal logger = Annal.get(clazz);
        info(logger, "Channel", pattern, args);
    }

    static void warnChannel(final Class<?> clazz, final String pattern, final Object... args) {
        final Annal logger = Annal.get(clazz);
        warn(logger, "Channel", pattern, args);
    }
}
