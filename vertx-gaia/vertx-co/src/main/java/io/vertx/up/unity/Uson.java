package io.vertx.up.unity;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.fn.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.util.Ut;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Stream for JsonObject
 */
public class Uson {

    private static final Annal LOGGER = Annal.get(Uson.class);

    private final transient JsonObject objectReference;

    private Uson(final JsonObject json) {
        objectReference = Fn.getNull(new JsonObject(), () -> json, json);
        LOGGER.debug(StreamInfo.STREAM_START, String.valueOf(hashCode()), json);
    }

    public static Uson create(final String field, final Object value) {
        return new Uson(new JsonObject().put(field, value));
    }

    public static Uson create() {
        return new Uson(new JsonObject());
    }

    public static Uson create(final JsonObject item) {
        return new Uson(item);
    }

    public Uson append(final JsonObject object) {
        Dual.append(objectReference, object, false);
        return this;
    }

    public Uson append(final JsonArray array) {
        Dual.append(objectReference, array);
        return this;
    }

    public Uson append(final String field, final Object value) {
        objectReference.put(field, value);
        return this;
    }

    public Uson convert(final String from, final String to) {
        Self.convert(objectReference, new ConcurrentHashMap<String, String>() {{
            put(from, to);
        }}, false);
        return this;
    }

    public Uson dft(final String field, final Object value) {
        Self.defaultValue(objectReference, field, value, false);
        return this;
    }

    public Uson dft(final JsonObject values) {
        Self.defaultValue(objectReference, values, false);
        return this;
    }

    public Uson plus(final String from, final Integer seed) {
        final Object value = objectReference.getValue(from);
        if (null != value && Ut.isInteger(value)) {
            final Integer old = objectReference.getInteger(from);
            objectReference.put(from, old + seed);
        }
        return this;
    }

    public <I, O> Uson convert(final String field, final Function<I, O> function) {
        Self.convert(objectReference, field, function, false);
        return this;
    }

    public Uson copy(final String from, final String to) {
        Self.copy(objectReference, from, to, false);
        return this;
    }

    public Uson remove(final String... keys) {
        Self.remove(objectReference, false, keys);
        return this;
    }

    public Uson pickup(final String... keys) {
        Self.pickup(objectReference, keys);
        return this;
    }

    public Uson denull() {
        Self.deNull(objectReference, false);
        return this;
    }

    public JsonObject to() {
        LOGGER.debug(StreamInfo.STREAM_END, String.valueOf(hashCode()), objectReference);
        return objectReference;
    }

    public Future<JsonObject> toFuture() {
        return Future.succeededFuture(to());
    }

    public Object get(final String field) {
        return objectReference.getValue(field);
    }

    @Override
    public String toString() {
        return objectReference.encode();
    }
}
