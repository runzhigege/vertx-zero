package io.vertx.tp.rbac.init;

import io.vertx.tp.ke.extension.Orbit;
import io.vertx.tp.ke.tool.Ke;
import io.vertx.tp.rbac.atom.ScConfig;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.log.Annal;

import java.util.Objects;

/*
 * Init Plugin for `initAsync` static life
 */
public class ScPin {

    private static final Annal LOGGER = Annal.get(ScPin.class);

    public static void init() {
        Ke.banner("「Ακριβώς」- Rbac ( Sc )");
        Sc.infoInit(LOGGER, "ScConfiguration...");
        ScConfiguration.init();
    }


    public static ScConfig getConfig() {
        return ScConfiguration.getConfig();
    }

    public static Orbit getOrbit() {
        final Class<?> clazz = getConfig().getOrbit();
        if (Objects.isNull(clazz)) {
            return null;
        } else {
            return Orbit.generate(clazz);
        }
    }
}
