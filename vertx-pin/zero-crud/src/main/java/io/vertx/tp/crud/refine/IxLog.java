package io.vertx.tp.crud.refine;

import io.vertx.up.log.Annal;

class IxLog {

    static void info(final Annal logger,
                     final String flag, final String pattern, final Object... args) {
        logger.info("[ Εκδήλωση ] ( " + flag + " ) " + pattern, args);
    }

    static void debug(final Annal logger,
                      final String flag, final String pattern, final Object... args) {
        logger.debug("[ Εκδήλωση ] ( " + flag + " ) " + pattern, args);
    }

    static void infoInit(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Init", pattern, args);
    }

    static void infoRest(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Rest", pattern, args);
    }

    static void infoFilters(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Filters", pattern, args);
    }

    static void infoVerify(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Verify", pattern, args);
    }

    static void infoDao(final Annal logger, final String pattern, final Object... args) {
        info(logger, "Dao", pattern, args);
    }
}
