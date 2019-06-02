package io.vertx.tp.crud.init;

import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.up.aiki.UxJooq;
import io.vertx.up.atom.Rule;
import io.vertx.up.log.Annal;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

/*
 * Init Plugin for `init` static life
 */
public class IxPin {

    private static final Annal LOGGER = Annal.get(IxPin.class);

    public static void init() {
        LOGGER.info("[ Εκδήλωση ] ( Init ) IxDao...");
        /* Dao Init */
        IxDao.init();
        LOGGER.info("[ Εκδήλωση ] ( Init ) IxValidator...");
        /* Validator Init */
        IxValidator.init();
    }

    public static IxConfig getActor(final String actor) {
        return IxDao.get(actor);
    }

    public static UxJooq getDao(final IxConfig config) {
        return IxDao.get(config);
    }

    public static ConcurrentMap<String, List<Rule>> getRules(final String actor) {
        return IxValidator.getRules(actor);
    }
}
