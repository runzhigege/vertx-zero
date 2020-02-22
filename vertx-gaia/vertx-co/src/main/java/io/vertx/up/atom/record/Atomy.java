package io.vertx.up.atom.record;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.em.ChangeFlag;
import io.vertx.up.exception.heart.AtomyParameterException;
import io.vertx.up.log.Annal;
import io.vertx.up.util.Ut;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * This data structure is for different compare
 */
@SuppressWarnings("unchecked")
public class Atomy {
    private final static Annal LOGGER = Annal.get(Atomy.class);

    private final static String MSG_ATOMY_BATCH = "[ ZERO ] Current api does not support `isBatch = false`.";

    private final transient boolean isBatch;
    private transient AtomyOp<JsonObject> single;
    private transient AtomyOp<JsonArray> batch;

    private Atomy(final JsonObject original, final JsonObject current) {
        this.single = new AtomySingle(original, current);
        this.isBatch = false;
    }

    private Atomy(final JsonArray original, final JsonArray current) {
        this.batch = new AtomyBatch(original, current);
        this.isBatch = true;
    }

    public static Atomy create(final JsonObject original, final JsonObject current) {
        if (Objects.isNull(original) && Objects.isNull(current)) {
            throw new AtomyParameterException();
        }
        return new Atomy(original, current);
    }

    public static Atomy create(final JsonArray original, final JsonArray current) {
        if (Ut.isNil(original) && Ut.isNil(current)) {
            throw new AtomyParameterException();
        }
        return new Atomy(original, current);
    }

    /*
     * Read original data
     */
    @SuppressWarnings("unchecked")
    public <T> T original() {
        final T reference;
        if (this.isBatch) {
            reference = (T) this.batch.original();
        } else {
            final JsonObject dataRef = this.single.original();
            if (Objects.isNull(dataRef)) {
                reference = null;
            } else {
                reference = (T) dataRef;
            }
        }
        return reference;
    }

    /*
     * ChangeFlag here
     */
    public ChangeFlag type() {
        if (this.isBatch) {
            return this.batch.type();
        } else {
            return this.single.type();
        }
    }

    /*
     * Read current data
     */
    public <T> T current() {
        final T reference;
        if (this.isBatch) {
            reference = (T) this.batch.current();
        } else {
            final JsonObject dataRef = this.single.current();
            if (Objects.isNull(dataRef)) {
                reference = null;
            } else {
                reference = (T) dataRef;
            }
        }
        return reference;
    }

    /*
     * Single operation here, select value
     */
    public <T> T data() {
        if (this.isBatch) {
            return (T) this.batch.data();
        } else {
            return (T) this.single.data();
        }
    }

    @Fluent
    public Atomy io(final JsonObject updated) {
        if (this.isBatch) {
            this.batch.update(updated);
        } else {
            this.single.update(updated);
        }
        return this;
    }

    public Future<Atomy> ioAsync(final JsonObject updated) {
        return Future.succeededFuture(this.io(updated));
    }

    public <T> Future<T> ioAsync(final T updated) {
        final T reference;
        if (this.isBatch) {
            reference = (T) this.batch.current((JsonArray) updated);
        } else {
            reference = (T) this.single.current((JsonObject) updated);
        }
        return Future.succeededFuture(reference);
    }

    /*
     * Batch operation api here
     * - compared                   - Read Io
     * - comparedAsync              - Read Io
     * - compared(compared)         - Write Io
     * - add(JsonArray)             - Replace flag = ADD queue
     * - update(JsonArray)          - Replace flag = UPDATE queue
     * - update(JsonObject)         - Update data based on `JsonObject`
     */
    public ConcurrentMap<ChangeFlag, JsonArray> compared() {
        if (this.isBatch) {
            return this.batch.compared();
        } else {
            LOGGER.warn(MSG_ATOMY_BATCH);
            return new ConcurrentHashMap<>();
        }
    }

    public Future<ConcurrentMap<ChangeFlag, JsonArray>> comparedAsync() {
        return Future.succeededFuture(this.compared());
    }

    @Fluent
    public Atomy compared(final ConcurrentMap<ChangeFlag, JsonArray> compared) {
        if (this.isBatch) {
            this.batch.compared(compared);
        } else {
            LOGGER.warn(MSG_ATOMY_BATCH);
        }
        return this;
    }

    @Fluent
    public Atomy add(final JsonArray inserted) {
        if (this.isBatch) {
            final JsonArray normalized = Ut.sureJArray(inserted);
            this.batch.compared().put(ChangeFlag.ADD, normalized);
        }
        return this;
    }

    @Fluent
    public Atomy update(final JsonArray updated) {
        if (this.isBatch) {
            final JsonArray normalized = Ut.sureJArray(updated);
            this.batch.compared().put(ChangeFlag.UPDATE, normalized);
        }
        return this;
    }

    public JsonArray add() {
        return this.batch.compared(ChangeFlag.ADD);
    }

    public JsonArray update() {
        return this.batch.compared(ChangeFlag.UPDATE);
    }
}
