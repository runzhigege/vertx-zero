package io.vertx.tp.crud.refine;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.up.aiki.Ux;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Values;
import io.zero.epic.Ut;

import java.time.Instant;
import java.util.Objects;

class IxData {

    private static final Annal LOGGER = Annal.get(IxData.class);

    static Future<JsonObject> unique(final JsonObject result) {
        final JsonArray list = result.getJsonArray("list");
        final JsonObject resultObj = list.getJsonObject(Values.IDX);
        return Ux.toFuture(resultObj);
    }

    static boolean isExist(final JsonObject result) {
        final Long counter = result.getLong("count", 0L);
        return 0 < counter;
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

    @SuppressWarnings("all")
    static <T> T entity(final JsonObject data, final IxConfig config) {
        LOGGER.info("[ Εκδήλωση ] ( Json ) Normalized: \n{0}", data.encodePrettily());
        final String pojo = config.getPojo();
        final T reference = Ut.isNil(pojo) ?
                Ux.fromJson(data, (Class<T>) config.getPojoCls()) :
                Ux.fromJson(data, (Class<T>) config.getPojoCls(), config.getPojo());
        LOGGER.info("[ Εκδήλωση ] Deserialized: {0}", reference);
        return reference;
    }
}
