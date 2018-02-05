package io.vertx.up.tool.mirror;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Numeric;
import io.vertx.up.tool.Period;
import io.vertx.zero.eon.Values;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Types {

    private static final Annal LOGGER = Annal.get(Types.class);

    public static <T extends Enum<T>> T fromStr(
            final Class<T> clazz,
            final String input) {
        return Fn.get(null, () -> Enum.valueOf(clazz, input), clazz, input);
    }

    public static boolean isJArray(final Object value) {
        return Fn.getSemi(null == value, LOGGER,
                () -> false,
                () -> JsonArray.class == value.getClass());
    }

    public static boolean isVoid(final Class<?> clazz) {
        return null == clazz || Void.class == clazz || void.class == clazz;
    }

    public static boolean isClass(final Object value) {
        return Fn.getSemi(null == value, LOGGER,
                () -> false,
                () -> null != Instance.clazz(value.toString()));
    }

    public static boolean isJObject(final Object value) {
        return Fn.getSemi(null == value, LOGGER,
                () -> false,
                () -> isJObject(value.getClass()));
    }

    public static boolean isJObject(final Class<?> clazz) {
        return JsonObject.class == clazz || LinkedHashMap.class == clazz;
    }

    public static boolean isInteger(final Object value) {
        return Fn.getSemi(null == value, LOGGER,
                () -> false,
                () -> Numeric.isInteger(value.toString()));
    }

    public static boolean isInteger(final Class<?> clazz) {
        return int.class == clazz || Integer.class == clazz
                || long.class == clazz || Long.class == clazz
                || short.class == clazz || Short.class == clazz;
    }

    public static boolean isDecimal(final Object value) {
        return Fn.getSemi(null == value, LOGGER,
                () -> false,
                () -> Numeric.isDecimal(value.toString()));
    }

    public static boolean isDeciaml(final Class<?> clazz) {
        return double.class == clazz || Double.class == clazz
                || float.class == clazz || Float.class == clazz
                || BigDecimal.class == clazz;
    }

    public static boolean isBoolean(final Class<?> clazz) {
        return boolean.class == clazz || Boolean.class == clazz;
    }

    public static boolean isBoolean(final Object value) {
        return Fn.getSemi(null == value, LOGGER,
                () -> false,
                () -> {
                    boolean logical = false;
                    final String literal = value.toString();
                    // Multi true literal such as "true", "TRUE" or 1
                    if (Values.TRUE.equalsIgnoreCase(literal)
                            || Integer.valueOf(1).toString().equalsIgnoreCase(literal)
                            || Values.FALSE.equalsIgnoreCase(literal)
                            || Integer.valueOf(0).toString().equalsIgnoreCase(literal)) {
                        logical = true;
                    }
                    return logical;
                });
    }

    private static final ObjectMapper YAML = new YAMLMapper();

    public static boolean isDate(final Object value) {
        return Fn.getSemi(null == value, LOGGER,
                () -> false,
                () -> Period.isValid(value.toString()));
    }

    public static boolean isArray(final Object value) {
        if (null == value) {
            return false;
        }
        return (value instanceof Collection ||
                value.getClass().isArray());
    }

    public static String toString(final Object reference) {
        return Fn.get("null", () -> {
            final String literal;
            if (isJObject(reference)) {
                // Fix issue for serialization
                literal = ((JsonObject) reference).encode();
            } else if (isJArray(reference)) {
                // Fix issue for serialization
                literal = ((JsonArray) reference).encode();
            } else {
                literal = reference.toString();
            }
            return literal;
        }, reference);
    }

    public static Collection toCollection(final Object value) {
        return Fn.get(() -> {
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

    /**
     * Check Primary
     *
     * @param source
     * @return
     */
    public static boolean isPrimary(final Class<?> source) {
        return UNBOXES.values().contains(source);
    }

    public static Class<?> toPrimary(final Class<?> source) {
        return UNBOXES.getOrDefault(source, source);
    }

    public static ConcurrentMap<Class<?>, Class<?>> UNBOXES =
            new ConcurrentHashMap<Class<?>, Class<?>>() {
                {
                    this.put(Integer.class, int.class);
                    this.put(Long.class, long.class);
                    this.put(Short.class, short.class);
                    this.put(Boolean.class, boolean.class);
                    this.put(Character.class, char.class);
                    this.put(Double.class, double.class);
                    this.put(Float.class, float.class);
                    this.put(Byte.class, byte.class);
                }
            };
}
