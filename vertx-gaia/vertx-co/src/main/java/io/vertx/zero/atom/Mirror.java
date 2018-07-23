package io.vertx.zero.atom;

import io.reactivex.Observable;
import io.vertx.core.json.JsonObject;
import io.vertx.up.epic.Ut;
import io.vertx.up.epic.fn.Fn;
import io.vertx.up.epic.io.IO;
import io.vertx.up.log.Annal;

import java.text.MessageFormat;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * Define mapping for custom serialization/deserialization
 */
public class Mirror {

    private static final String POJO = "pojo/{0}.yml";
    private final transient Annal logger;
    private final transient JsonObject converted = new JsonObject();
    private transient Mojo mojo;
    private transient JsonObject data = new JsonObject();

    private Mirror(final Class<?> clazz) {
        this.logger = Annal.get(clazz);
    }

    public static Mirror create(final Class<?> clazz) {
        return new Mirror(clazz);
    }

    public Mirror mount(final String filename) {
        // Build meta
        this.mojo = Fn.pool(Pool.MOJOS, filename, () -> {
            final JsonObject data = IO.getYaml(MessageFormat.format(POJO, filename));
            return Fn.getNull(() -> Ut.deserialize(data, Mojo.class), data);
        });
        return this;
    }

    public Mirror connect(final JsonObject data) {
        // Copy new data
        this.data = Fn.getNull(new JsonObject(), data::copy, data);
        return this;
    }

    public Mirror to() {
        this.convert(this.mojo.getMapper());
        return this;
    }

    public Mojo mojo() {
        return this.mojo;
    }

    private void convert(final ConcurrentMap<String, String> mapper) {
        Observable.fromIterable(this.data.fieldNames())
                .groupBy(mapper::containsKey)
                .map(contain -> contain.getKey() ?
                        contain.subscribe(from -> {
                            // Existing in mapper
                            final String to = mapper.get(from);
                            this.converted.put(to, this.data.getValue(from));
                        }) :
                        contain.subscribe(item ->
                                // Not found in mapper
                                this.converted.put(item, this.data.getValue(item)))
                ).subscribe();
    }

    public Mirror from() {
        this.convert(this.mojo.getRevert());
        return this;
    }

    public Mirror apply(final Function<String, String> function) {
        final JsonObject result = this.data.copy();
        result.forEach((entry) ->
                this.converted.put(function.apply(entry.getKey()),
                        entry.getValue()));
        return this;
    }

    public JsonObject json(final Object entity, final boolean overwrite) {
        final JsonObject data = Ut.serializeJson(entity);
        final JsonObject merged = this.converted.copy();
        for (final String field : data.fieldNames()) {
            if (overwrite) {
                // If overwrite
                merged.put(field, data.getValue(field));
            } else {
                if (!merged.containsKey(field)) {
                    merged.put(field, data.getValue(field));
                }
            }
        }
        return merged;
    }

    @SuppressWarnings("unchecked")
    public <T> T get() {
        final Object reference = Ut.deserialize(this.converted, this.mojo.getType());
        return Fn.getNull(null, () -> (T) reference, reference);
    }

    public JsonObject result() {
        return this.converted;
    }
}
