package io.vertx.tp.ui.init;

import io.vertx.tp.ke.tool.Ke;
import io.vertx.tp.ui.atom.UiConfig;
import io.vertx.tp.ui.refine.Ui;
import io.vertx.up.log.Annal;

public class UiPin {

    private static final Annal LOGGER = Annal.get(UiPin.class);

    public static void init() {
        Ke.banner("「Διασύνδεση χρήστη」- ( Ui )");
        Ui.infoInit(LOGGER, "UiConfiguration...");
        UiConfiguration.init();
    }

    public static UiConfig getConfig() {
        return UiConfiguration.getConfig();
    }
}
