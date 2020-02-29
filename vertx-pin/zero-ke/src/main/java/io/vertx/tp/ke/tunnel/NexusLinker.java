package io.vertx.tp.ke.tunnel;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.error._501JooqReferenceException;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.unity.Ux;
import io.vertx.up.unity.jq.UxJooq;
import io.vertx.up.util.Ut;

import java.util.Objects;
import java.util.function.Function;

/*
 * Spec Linker for `modelKey` and `modelId`
 * Usage
 */
class NexusLinker implements Nexus {

    private final transient Class<?> entityT;
    private transient UxJooq jooq;

    public NexusLinker(final Class<?> entityT) {
        this.entityT = entityT;
    }

    @Override
    public Nexus on(final UxJooq jooq) {
        this.jooq = jooq;
        return this;
    }

    /*
     *
     */
    @Override
    public Future<JsonObject> fetchNexus(final JsonObject filters) {
        return this.execute(filters, (condition) -> {
            condition.put("", Boolean.TRUE);
            condition.put(KeField.SIGMA, filters.getString(KeField.SIGMA));
            return this.jooq.fetchOneAsync(condition)
                    .compose(Ux::fnJObject);
        });
    }

    @Override
    public Future<JsonObject> updateNexus(final String key, final JsonObject params) {
        return this.execute(params, (updatedData) -> this.jooq.findByIdAsync(key)
                .compose(Ux::fnJObject)
                .compose(original -> {
                    original.mergeIn(updatedData);
                    final Object entity = Ut.deserialize(original, this.entityT);
                    return this.jooq.updateAsync(entity)
                            .compose(Ux::fnJObject);
                })
        );
    }

    private Future<JsonObject> execute(final JsonObject params, final Function<JsonObject, Future<JsonObject>> future) {
        if (Objects.isNull(this.jooq)) {
            return Future.failedFuture(new _501JooqReferenceException(this.getClass()));
        } else {
            final JsonObject data = this.getData(params);
            if (Objects.isNull(data)) {
                return Future.failedFuture(new _501JooqReferenceException(this.getClass()));
            } else {
                return future.apply(data);
            }
        }
    }

    private JsonObject getData(final JsonObject json) {
        if (Ut.isNil(json)) {
            return null;
        } else {
            final JsonObject nexusData = new JsonObject();
            nexusData.put(KeField.MODEL_ID, json.getString(KeField.IDENTIFIER));
            nexusData.put(KeField.MODEL_KEY, json.getString(KeField.KEY));
            return nexusData;
        }
    }
}
