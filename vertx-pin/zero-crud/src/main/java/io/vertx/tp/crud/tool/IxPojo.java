package io.vertx.tp.crud.tool;

import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.tp.error._409UniqueAmbiguityException;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.Envelop;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.util.HashSet;
import java.util.Set;

class IxPojo {

    private static final Annal LOGGER = Annal.get(IxPojo.class);

    @SuppressWarnings("all")
    static <T> T inJson(final String module, final Envelop envelop, final boolean isUpdate) {
        final IxConfig config = IxDao.get(module);
        final String pojo = config.getPojo();
        /* Add/Update splitting workflow */
        final JsonObject normalize = new JsonObject();
        if (!isUpdate) {
            /* Add, Append Key */
            normalize.mergeIn(IxAdd.inAdd(envelop, config));
        }
        LOGGER.info("[ Εκδήλωση ] Json Data: \n{0}", normalize.encodePrettily());
        final T reference = Ut.isNil(pojo) ?
                Ux.fromJson(normalize, (Class<T>) config.getPojoCls()) :
                Ux.fromJson(normalize, (Class<T>) config.getPojoCls(), config.getPojo());
        return reference;
    }

    static JsonObject inFilters(final String module, final Envelop envelop) {
        final IxConfig config = IxDao.get(module);
        /* Unique Operation */
        final String field = Ux.getString1(envelop);
        final String value = Ux.getString2(envelop);

        /* Build Filters */
        verifyUnique(module, envelop);

        return null;
    }

    private static void verifyUnique(final String module, final Envelop envelop) {
        final MultiMap params = envelop.headers();
        /* Two collection removing */
        final Set<String> keys = new HashSet<>(CvHeader.PARAM_MAP.keySet());
        keys.removeAll(params.names());
        /* There  */
        Fn.outWeb(2 > keys.size(), _409UniqueAmbiguityException.class, IxPojo.class,
                module, Ut.fromJoin(params.names()));
    }
}
