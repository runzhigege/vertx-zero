package io.vertx.up.atom.record;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.em.ChangeFlag;
import io.vertx.up.util.Ut;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class AtomyBatch implements AtomyOp<JsonArray> {
    private transient final JsonArray original;
    private transient final JsonArray current;
    private transient final JsonArray data = new JsonArray();
    private transient final ConcurrentMap<ChangeFlag, JsonArray> combine;

    AtomyBatch(final JsonArray original, final JsonArray current) {
        this.original = Ut.sureJArray(original);
        this.current = Ut.sureJArray(current);
        this.combine = new ConcurrentHashMap<>();
        this.data.addAll(this.current.copy()); /* Synced with current data */
    }

    @Override
    public JsonArray original() {
        return this.original;
    }

    @Override
    public JsonArray current() {
        return this.current;
    }

    @Override
    public JsonArray data() {
        return this.data;
    }

    @Override
    public AtomyOp<JsonArray> update(final JsonObject input) {
        final JsonObject inputData = Ut.sureJObject(input);
        if (Ut.notNil(inputData)) {
            /*
             * Update current JsonArray by
             */
            final JsonArray normalized = new JsonArray();
            Ut.itJArray(this.data).forEach(json -> {
                final JsonObject reference = json.copy();
                reference.mergeIn(inputData.copy(), true);
                normalized.add(reference);
            });

            this.data.clear();
            this.data.addAll(normalized);
        }
        return this;
    }

    @Override
    public ConcurrentMap<ChangeFlag, JsonArray> compared() {
        return this.combine;
    }
}
