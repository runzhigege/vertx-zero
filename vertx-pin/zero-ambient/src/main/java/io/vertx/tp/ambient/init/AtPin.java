package io.vertx.tp.ambient.init;

import io.vertx.tp.ambient.atom.AtConfig;
import io.vertx.tp.ambient.refine.At;
import io.vertx.up.log.Annal;

/*
 * Init Plugin for 'initAsync' static life
 */
public class AtPin {

    private static final Annal LOGGER = Annal.get(AtPin.class);

    public static void init() {
        At.infoInit(LOGGER, "AtConfiguration...");
        AtConfiguration.init();
    }

    public static AtConfig getConfig() {
        return AtConfiguration.getConfig();
    }
}
