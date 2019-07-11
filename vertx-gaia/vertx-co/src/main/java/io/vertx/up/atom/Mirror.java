package io.vertx.up.atom;

import io.reactivex.Observable;
import io.vertx.core.json.JsonObject;
import io.vertx.up.log.Annal;
import io.vertx.up.util.Ut;
import io.vertx.up.fn.Fn;

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
        logger = Annal.get(clazz);
    }

    public static Mirror create(final Class<?> clazz) {
        return new Mirror(clazz);
    }

    public Mirror mount(final String filename) {
        // Build meta
        logger.info("[ ZERO ] Mount pojo configuration file {0}", filename);
        mojo = Fn.pool(Pool.MOJOS, filename, () -> {
            final JsonObject data = Ut.ioYaml(MessageFormat.format(POJO, filename));
            return Fn.getNull(() -> Ut.deserialize(data, Mojo.class), data);
        });
        return this;
    }

    public Mirror type(final Class<?> entityCls) {
        mojo.setType(entityCls);
        return this;
    }

    public Mirror connect(final JsonObject data) {
        // Copy new data
        this.data = Fn.getNull(new JsonObject(), data::copy, data);
        return this;
    }

    public Mirror to() {
        convert(mojo.getMapper());
        return this;
    }

    public Mojo mojo() {
        return mojo;
    }

    private void convert(final ConcurrentMap<String, String> mapper) {
        Observable.fromIterable(data.fieldNames())
                .groupBy(mapper::containsKey)
                .map(contain -> contain.getKey() ?
                        contain.subscribe(from -> {
                            // Existing in mapper
                            final String to = mapper.get(from);
                            converted.put(to, data.getValue(from));
                        }) :
                        contain.subscribe(item ->
                                // Not found in mapper
                                converted.put(item, data.getValue(item)))
                ).subscribe().dispose();
    }

    public Mirror from() {
        convert(mojo.getRevert());
        return this;
    }

    public Mirror apply(final Function<String, String> function) {
        final JsonObject result = data.copy();
        result.forEach((entry) ->
                converted.put(function.apply(entry.getKey()),
                        entry.getValue()));
        return this;
    }

    public JsonObject json(final Object entity, final boolean overwrite) {
        final JsonObject data = Ut.serializeJson(entity);
        final JsonObject merged = converted.copy();
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
        final Object reference = Ut.deserialize(converted, mojo.getType());
        return Fn.getNull(null, () -> (T) reference, reference);
    }

    public JsonObject result() {
        return converted;
    }
}
