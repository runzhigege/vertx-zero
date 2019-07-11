package io.vertx.tp.ambient.init;

import io.vertx.tp.ambient.atom.AtConfig;
import io.vertx.tp.ambient.extension.Init;
import io.vertx.tp.ambient.extension.Prerequisite;
import io.vertx.tp.ambient.refine.At;
import io.vertx.tp.error._500InitSpecificationException;
import io.vertx.tp.error._500PrerequisiteSpecException;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.up.log.Annal;
import io.vertx.zero.epic.Ut;
import io.vertx.zero.epic.fn.Fn;

import java.util.Objects;

/*
 * Init Plugin for 'initAsync' static life
 */
public class AtPin {

    private static final Annal LOGGER = Annal.get(AtPin.class);

    public static void init() {
        Ke.banner("「περιβάλλων」- Ambient ( At )");
        At.infoInit(LOGGER, "AtConfiguration...");
        AtConfiguration.init();
    }

    public static AtConfig getConfig() {
        return AtConfiguration.getConfig();
    }

    public static Init getInit() {
        return getInit(getConfig().getInitializer());
    }

    public static Init getLoader() {
        return getInit(getConfig().getLoader());
    }

    private static Init getInit(final Class<?> initializer) {
        if (Objects.isNull(initializer)) {
            return null;
        } else {
            Fn.outWeb(!Ut.isImplement(initializer, Init.class), _500InitSpecificationException.class,
                    AtPin.class, initializer.getName());
            return Init.generate(initializer);
        }
    }

    public static Prerequisite getPrerequisite() {
        final Class<?> prerequisite = getConfig().getPrerequisite();
        if (Objects.isNull(prerequisite)) {
            return null;
        } else {
            Fn.outWeb(!Ut.isImplement(prerequisite, Prerequisite.class), _500PrerequisiteSpecException.class,
                    AtPin.class, prerequisite.getName());
            return Prerequisite.generate(prerequisite);
        }
    }
}
