package io.vertx.tp.crud.init;

import io.vertx.core.http.HttpMethod;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.tp.crud.atom.IxSeeker;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.tp.ke.tool.Ke;
import io.vertx.up.aiki.UxJooq;
import io.vertx.up.atom.Rule;
import io.vertx.up.log.Annal;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/*
 * Init Plugin for `init` static life
 */
public class IxPin {

    private static final Annal LOGGER = Annal.get(IxPin.class);

    public static void init() {
        Ke.banner("「Εκδήλωση」- Crud ( Ix )");
        Ix.infoInit(LOGGER, "IxConfiguration...");
        /* Configuration Init */
        IxConfiguration.init();

        Ix.infoInit(LOGGER, "IxDao...");
        /* Dao Init */
        IxDao.init();

        Ix.infoInit(LOGGER, "IxValidator...");
        /* Validator Init */
        IxValidator.init();
    }

    public static IxModule getActor(final String actor) {
        return IxDao.get(actor);
    }

    public static UxJooq getDao(final IxModule config) {
        return IxDao.get(config);
    }

    public static Set<String> getUris() {
        return IxDao.getUris();
    }

    public static IxSeeker getSeeker(final String requestUri, final HttpMethod method) {
        return IxConfiguration.getSeeker(requestUri, method);
    }

    public static ConcurrentMap<String, List<Rule>> getRules(final String actor) {
        return IxValidator.getRules(actor);
    }
}
