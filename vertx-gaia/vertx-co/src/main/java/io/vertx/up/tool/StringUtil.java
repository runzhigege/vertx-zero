package io.vertx.up.tool;

import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.zero.eon.Strings;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lang
 */
class StringUtil {

    static String from(final Object value) {
        return null == value ? Strings.EMPTY : value.toString();
    }

    static String from(final JsonObject value) {
        return null == value ? Strings.EMPTY : value.toString();
    }

    static Set<String> split(final String input, final String separator) {
        return Fn.get(new HashSet<>(), () -> {
            final String[] array = input.split(separator);
            final Set<String> result = new HashSet<>();
            for (final String item : array) {
                Fn.safeNull(() -> result.add(item.trim().intern()), item);
            }
            return result;
        }, input, separator);
    }

    private static String join(final Set<String> input) {
        return join(input, null);
    }

    static String join(final Set<String> input, final String separator) {
        final String connector = (null == separator) ? Strings.COMMA : separator;
        return Fn.getJvm(() -> {
            final StringBuilder builder = new StringBuilder();
            final int size = input.size();
            int start = 0;
            for (final String item : input) {
                builder.append(item);
                start++;
                if (start < size) {
                    builder.append(connector);
                }
            }
            return builder.toString();
        }, input);
    }

    static String join(final Object[] input) {
        final Set<String> data = new HashSet<>();
        for (final Object item : input) {
            if (null != item) {
                data.add(item.toString());
            }
        }
        return join(data);
    }

    static String aequilatus(final Integer seed, final Integer width, final char fill) {
        final StringBuilder builder = new StringBuilder();
        final int seedLen = seed.toString().length();
        int fillLen = width - seedLen;
        if (0 < fillLen) fillLen = 0;
        builder.append(repeat(fillLen, fill));
        builder.append(seed);
        return builder.toString();
    }

    static String repeat(final Integer times, final char fill) {
        final StringBuilder builder = new StringBuilder();
        for (int idx = 0; idx < times; idx++) {
            builder.append(fill);
        }
        return builder.toString();
    }

    static boolean isNil(final String input) {
        return null == input || 0 == input.trim().length();
    }

    static boolean notNil(final String input) {
        return !isNil(input);
    }
}
