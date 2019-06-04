package io.vertx.tp.crud.refine;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.up.aiki.UxJooq;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;

import java.time.Instant;
import java.util.Objects;
import java.util.function.Function;

class IxFn {

    private static final Annal LOGGER = Annal.get(IxFn.class);

    static Function<UxJooq, Future<JsonObject>> search(
            final JsonObject filters, final IxConfig config) {
        final String pojo = config.getPojo();
        return dao -> {
            IxLog.infoDao(LOGGER, "Dao -> {0}, pojo = {1}", dao.getClass(), pojo);

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

    static void audit(final JsonObject auditor, final JsonObject config, final String userId) {
        if (Objects.nonNull(config) && Ut.notNil(userId)) {
            /* User By */
            final String by = config.getString("by");
            if (Ut.notNil(by)) {
                auditor.put(by, userId);
            }
            final String at = config.getString("at");
            if (Ut.notNil(at)) {
                auditor.put(at, Instant.now());
            }
        }
    }
}
