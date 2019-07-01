package io.zero.epic;

import io.reactivex.Observable;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.eon.Values;
import io.zero.epic.fn.Fn;

import java.util.*;
import java.util.function.Supplier;

final class To {

    private To() {
    }

    static <T extends Enum<T>> T toEnum(final Class<T> clazz, final String input) {
        return Fn.getJvm(null, () -> Enum.valueOf(clazz, input), clazz, input);
    }

    static <T extends Enum<T>> T toEnum(final Supplier<String> supplier, final Class<T> type, final T defaultEnum) {
        final String method = supplier.get();
        T result = Ut.toEnum(type, method);
        if (null == result) {
            result = defaultEnum;
        }
        return result;
    }

    static Integer toInteger(final Object value) {
        return Fn.getNull(null, () -> Integer.parseInt(value.toString()), value);
    }

    static String toString(final Object reference) {
        return Fn.getNull("null", () -> {
            final String literal;
            if (Types.isJObject(reference)) {
                // Fix issue for serialization
                literal = ((JsonObject) reference).encode();
            } else if (Types.isJArray(reference)) {
                // Fix issue for serialization
                literal = ((JsonArray) reference).encode();
            } else {
                literal = reference.toString();
            }
            return literal;
        }, reference);
    }

    static <T> JsonArray toJArray(final Set<T> set) {
        final JsonArray array = new JsonArray();
        Observable.fromIterable(set)
                .subscribe(array::add).dispose();
        return array;
    }

    static <T> JsonArray toJArray(final List<T> list) {
        final JsonArray array = new JsonArray();
        Observable.fromIterable(list)
                .subscribe(array::add).dispose();
        return array;
    }

    static JsonObject toJObject(final String literal) {
        if (Ut.isNil(literal)) {
            return new JsonObject();
        } else {
            try {
                return new JsonObject(literal);
            } catch (final DecodeException ex) {
                return new JsonObject();
            }
        }
    }

    static JsonArray toJArray(final String literal) {
        if (Ut.isNil(literal)) {
            return new JsonArray();
        } else {
            try {
                return new JsonArray(literal);
            } catch (final DecodeException ex) {
                return new JsonArray();
            }
        }
    }

    static <T> JsonArray toJArray(final T entity, final int repeat) {
        final JsonArray array = new JsonArray();
        for (int idx = Values.IDX; idx < repeat; idx++) {
            array.add(entity);
        }
        return array;
    }

    static Collection toCollection(final Object value) {
        return Fn.getNull(() -> {
            // Collection
            if (value instanceof Collection) {
                return ((Collection) value);
            }
            // JsonArray
            if (Types.isJArray(value)) {
                return ((JsonArray) value).getList();
            }
            // Object[]
            if (Types.isArray(value)) {
                // Array
                final Object[] values = (Object[]) value;
                return Arrays.asList(values);
            }
            return null;
        }, value);
    }

    static JsonObject toJObject(final Map<String, Object> map) {
        final JsonObject params = new JsonObject();
        Fn.safeNull(() -> map.forEach(params::put), map);
        return params;
    }

    static Class<?> toPrimary(final Class<?> source) {
        return Types.UNBOXES.getOrDefault(source, source);
    }
}
