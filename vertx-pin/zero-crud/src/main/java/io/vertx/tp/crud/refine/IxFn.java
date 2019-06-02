package io.vertx.tp.crud.refine;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.up.aiki.UxJooq;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;

import java.util.function.Function;

class IxFn {

    private static final Annal LOGGER = Annal.get(IxFn.class);

    static Function<UxJooq, Future<JsonObject>> search(
            final JsonObject filters, final IxConfig config) {
        final String pojo = config.getPojo();
        return dao -> {
            LOGGER.info("[ Εκδήλωση ] Dao -> {0}, pojo = {1}",
                    dao.getClass(), pojo);
            final JsonObject criteria = new JsonObject();
            criteria.put("criteria", filters);
            // Here must put condition here.
            if (Ut.notNil(pojo)) {
                return dao.searchAsync(criteria, pojo);
            } else {
                return dao.searchAsync(criteria);
            }
        };
    }
}
