package io.vertx.up.web;

import io.reactivex.Observable;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Envelop;
import io.vertx.up.log.Annal;
import io.vertx.up.web.serialization.*;
import io.vertx.zero.atom.Mirror;
import io.vertx.zero.eon.Values;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * ZeroSerializer the request by different type.
 * 1. String -> T
 * 2. T -> JsonObject ( Envelop request )
 * 3. T -> String ( Generate resonse )
 * 4. Checking the request type to see where support serialization
 */
@SuppressWarnings("unchecked")
public class ZeroSerializer {

    private static final Annal LOGGER = Annal.get(ZeroSerializer.class);

    private static final ConcurrentMap<Class<?>, Saber> SABERS =
            new ConcurrentHashMap<Class<?>, Saber>() {
                {
                    this.put(int.class, Ut.singleton(IntegerSaber.class));
                    this.put(Integer.class, Ut.singleton(IntegerSaber.class));
                    this.put(short.class, Ut.singleton(ShortSaber.class));
                    this.put(Short.class, Ut.singleton(ShortSaber.class));
                    this.put(long.class, Ut.singleton(LongSaber.class));
                    this.put(Long.class, Ut.singleton(LongSaber.class));

                    this.put(double.class, Ut.singleton(DoubleSaber.class));
                    this.put(Double.class, Ut.singleton(DoubleSaber.class));

                    this.put(LocalDate.class, Ut.singleton(Java8DataTimeSaber.class));
                    this.put(LocalDateTime.class, Ut.singleton(Java8DataTimeSaber.class));
                    this.put(LocalTime.class, Ut.singleton(Java8DataTimeSaber.class));

                    this.put(float.class, Ut.singleton(FloatSaber.class));
                    this.put(Float.class, Ut.singleton(FloatSaber.class));
                    this.put(BigDecimal.class, Ut.singleton(BigDecimalSaber.class));

                    this.put(Enum.class, Ut.singleton(EnumSaber.class));

                    this.put(boolean.class, Ut.singleton(BooleanSaber.class));
                    this.put(Boolean.class, Ut.singleton(BooleanSaber.class));

                    this.put(Date.class, Ut.singleton(DateSaber.class));
                    this.put(Calendar.class, Ut.singleton(DateSaber.class));

                    this.put(JsonObject.class, Ut.singleton(JsonObjectSaber.class));
                    this.put(JsonArray.class, Ut.singleton(JsonArraySaber.class));

                    this.put(String.class, Ut.singleton(StringSaber.class));
                    this.put(StringBuffer.class, Ut.singleton(StringBufferSaber.class));
                    this.put(StringBuilder.class, Ut.singleton(StringBufferSaber.class));

                    this.put(Buffer.class, Ut.singleton(BufferSaber.class));
                    this.put(Set.class, Ut.singleton(CollectionSaber.class));
                    this.put(List.class, Ut.singleton(CollectionSaber.class));
                    this.put(Collection.class, Ut.singleton(CollectionSaber.class));

                    this.put(byte[].class, Ut.singleton(ByteArraySaber.class));
                    this.put(Byte[].class, Ut.singleton(ByteArraySaber.class));

                    this.put(File.class, Ut.singleton(FileSaber.class));
                }
            };

    /**
     * String -> T
     *
     * @param paramType argument types
     * @param literal   literal values
     * @return deserialized object.
     */
    public static Object getValue(final Class<?> paramType,
                                  final String literal) {
        Object reference = null;
        if (null != literal) {
            Saber saber;
            if (paramType.isEnum()) {
                saber = SABERS.get(Enum.class);
            } else if (Collection.class.isAssignableFrom(paramType)) {
                saber = SABERS.get(Collection.class);
            } else {
                saber = SABERS.get(paramType);
            }
            if (null == saber) {
                saber = Ut.singleton(CommonSaber.class);
            }
            reference = saber.from(paramType, literal);
        }
        return reference;
    }

    public static <T> boolean isDirect(final T input) {
        boolean result = false;
        if (null != input) {
            final Class<?> cls = input.getClass();
            if (JsonObject.class == cls) {
                result = false;
            } else if (JsonArray.class == cls) {
                result = false;
            } else {
                result = SABERS.keySet().contains(cls);
            }
        }
        return result;
    }

    /**
     * T -> JsonObject
     *
     * @param input Checked object
     * @param <T>   Generic Types
     * @return returned values.
     */
    public static <T> Object toSupport(final T input) {
        Object reference = null;
        if (null != input) {
            Saber saber;
            final Class<?> cls = input.getClass();
            if (cls.isEnum()) {
                saber = SABERS.get(Enum.class);
            } else if (Calendar.class.isAssignableFrom(cls)) {
                saber = SABERS.get(Date.class);
            } else if (Collection.class.isAssignableFrom(cls)) {
                saber = SABERS.get(Collection.class);
            } else if (cls.isArray()) {
                final Class<?> type = cls.getComponentType();
                if (byte.class == type || Byte.class == type) {
                    saber = SABERS.get(byte[].class);
                } else {
                    saber = SABERS.get(Collection.class);
                }
            } else {
                saber = SABERS.get(cls);
            }
            if (null == saber) {
                saber = Ut.singleton(CommonSaber.class);
            }
            reference = saber.from(input);
        }
        return reference;
    }

    @Deprecated
    public static <T> JsonArray toArray(final List<T> list, final Function<JsonObject, JsonObject> converted) {
        final JsonArray array = Ut.serializeJson(list);
        final JsonArray result = new JsonArray();
        Observable.fromIterable(array)
                .filter(Objects::nonNull)
                .map(item -> (JsonObject) item)
                .map(converted::apply)
                .subscribe(result::add);
        return result;
    }

    @Deprecated
    public static <T> JsonArray toArray(final List<T> list) {
        return toArray(list, item -> item);
    }

    @Deprecated
    public static <T> JsonArray toArray(final List<T> list, final String pojo) {
        return Fn.getNull(new JsonArray(), () -> {
            final Function<JsonObject, JsonObject> converted =
                    (from) -> Mirror.create(ZeroSerializer.class)
                            .mount(pojo).connect(from).to().result();
            return toArray(list, converted);
        }, pojo, list);
    }

    @Deprecated
    public static <T> JsonObject toObject(final T entity, final String pojo) {
        return Fn.getNull(new JsonObject(), () -> {
            final JsonObject from = Ut.serializeJson(entity);
            return Mirror.create(ZeroSerializer.class)
                    .mount(pojo).connect(from).to().result();
        }, entity, pojo);
    }

    @Deprecated
    public static <T> Envelop collect(final List<T> list) {
        return Envelop.success(toArray(list, item -> item));
    }

    @Deprecated
    public static <T> Envelop unique(final List<T> list) {
        return Fn.getSemi(null == list || 0 == list.size(), LOGGER,
                () -> Envelop.success(new JsonObject()),
                () -> unique(list.get(Values.IDX)));
    }

    @Deprecated
    public static <T> Envelop unique(final JsonArray data) {
        return Fn.getSemi(null == data || 0 == data.size(), LOGGER,
                () -> Envelop.success(new JsonObject()),
                () -> unique(data.getJsonObject(Values.IDX)));
    }

    @Deprecated
    public static <T> Envelop unique(final T entity) {
        return Fn.getJvm(Envelop.success(new JsonObject()), () -> Envelop.success(entity), entity);
    }
}
