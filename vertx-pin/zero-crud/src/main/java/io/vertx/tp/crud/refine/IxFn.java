package io.vertx.tp.crud.refine;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.up.unity.Ux;
import io.vertx.up.unity.UxJooq;
import io.vertx.up.log.Annal;
import io.vertx.up.util.Ut;

import java.time.Instant;
import java.util.Objects;
import java.util.function.Function;

class IxFn {

    private static final Annal LOGGER = Annal.get(IxFn.class);

    static Function<UxJooq, Future<JsonObject>> search(
            final JsonObject filters, final IxModule config) {
        final String pojo = config.getPojo();
        return dao -> {
            IxLog.infoDao(LOGGER, "( Search ) Dao -> {0}, pojo = {1}", dao.getClass(), pojo);

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

    static Function<UxJooq, Future<JsonObject>> query(
            final JsonObject criteria, final IxModule config) {
        final String pojo = config.getPojo();
        return dao -> {
            IxLog.infoDao(LOGGER, "( Query ) Dao -> {0}, pojo = {1}", dao.getClass(), pojo);
            // Here must put condition here.
            if (Ut.notNil(pojo)) {
                return dao.searchAsync(criteria, pojo);
            } else {
                return dao.searchAsync(criteria);
            }
        };
    }

    static Function<UxJooq, Future<Boolean>> existing(
            final JsonObject criteria, final IxModule config
    ) {
        final String pojo = config.getPojo();
        final JsonObject parameters = new JsonObject();
        if (Ut.notNil(pojo)) {
            parameters.mergeIn(Ux.fromJson(criteria, pojo));
        } else {
            parameters.mergeIn(criteria);
        }
        return dao -> {
            IxLog.infoDao(LOGGER, "( Query ) Dao -> {0}, pojo = {1}", dao.getClass(), pojo);
            // Here must put condition here.
            return dao.existsOneAsync(parameters);
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