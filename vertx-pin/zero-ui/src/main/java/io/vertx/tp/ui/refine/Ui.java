package io.vertx.tp.ui.refine;

import io.vertx.up.log.Annal;

public class Ui {
    /*
     * Log
     */
    public static void infoInit(final Annal logger, final String pattern, final Object... args) {
        UiLog.infoInit(logger, pattern, args);
    }

    public static void infoWarn(final Annal logger, final String pattern, final Object... args) {
        UiLog.infoWarn(logger, pattern, args);
    }
}
