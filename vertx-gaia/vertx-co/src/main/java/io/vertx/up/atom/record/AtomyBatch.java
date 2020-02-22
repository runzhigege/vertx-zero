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
    private transient final ChangeFlag flag;

    private transient final JsonArray data = new JsonArray();
    private transient final ConcurrentMap<ChangeFlag, JsonArray> combine;

    AtomyBatch(final JsonArray original, final JsonArray current) {
        this.original = Ut.sureJArray(original);
        this.current = Ut.sureJArray(current);
        this.combine = new ConcurrentHashMap<>();
        if (Ut.isNil(original)) {
            /*
             * ADD
             */
            this.flag = ChangeFlag.ADD;
            this.data.addAll(this.current.copy());
        } else if (Ut.isNil(current)) {
            /*
             * DELETE
             */
            this.flag = ChangeFlag.DELETE;
            this.data.addAll(this.original.copy());
        } else {
            /*
             * UPDATE
             * the `data` won't be initialized
             */
            this.flag = ChangeFlag.UPDATE;
            this.data.addAll(this.original.copy());
        }
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
    public JsonArray current(final JsonArray current) {
        this.current.clear();
        this.current.addAll(current);
        return this.current;
    }

    @Override
    public JsonArray data() {
        return this.data;
    }

    @Override
    public ChangeFlag type() {
        return this.flag;
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
