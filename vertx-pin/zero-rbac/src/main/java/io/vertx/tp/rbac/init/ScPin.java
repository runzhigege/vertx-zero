package io.vertx.tp.rbac.init;

import io.vertx.tp.rbac.atom.ScConfig;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.log.Annal;

/*
 * Init Plugin for `init` static life
 */
public class ScPin {

    private static final Annal LOGGER = Annal.get(ScPin.class);
    private static ScConfig INSTANCE = null;

    public static void init() {
        Sc.infoInit(LOGGER, "ScConfiguration...");
        ScConfiguration.init();
    }

    /**
     * Get configuration
     */
    public static ScConfig getConfig() {
        if (null == INSTANCE) {
            INSTANCE = ScConfiguration.getConfig();
        }
        return INSTANCE;
    }
}
