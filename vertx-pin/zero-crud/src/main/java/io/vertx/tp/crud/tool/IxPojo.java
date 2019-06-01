package io.vertx.tp.crud.tool;

import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.tp.error._409UniqueAmbiguityException;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.Envelop;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.util.HashSet;
import java.util.Set;

class IxPojo {

    @SuppressWarnings("all")
    static <T> T inJson(final String module, final JsonObject data) {
        final IxConfig config = IxDao.get(module);
        final String pojo = config.getPojo();
        final T reference = Ux.fromJson(data, (Class<T>) config.getPojoCls(), pojo);
        // TODO:
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
