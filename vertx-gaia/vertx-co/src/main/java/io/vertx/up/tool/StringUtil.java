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

    static boolean isNil(final String input) {
        return null == input || 0 == input.trim().length();
    }

    static boolean notNil(final String input) {
        return !isNil(input);
    }
}
