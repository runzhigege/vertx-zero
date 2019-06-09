package io.vertx.tp.rbac.init;

import io.vertx.tp.rbac.atom.ScConfig;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.log.Annal;

/*
 * Init Plugin for `init` static life
 */
public class ScPin {

    private static final Annal LOGGER = Annal.get(ScPin.class);

    public static void init() {
        Sc.infoInit(LOGGER, "ScConfiguration...");
        ScConfiguration.init();
    }


    public static ScConfig getConfig() {
        return ScConfiguration.getConfig();
    }
}
