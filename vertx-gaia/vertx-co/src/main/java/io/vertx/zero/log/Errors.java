package io.vertx.zero.log;

import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Plugins;
import io.vertx.up.epic.fn.Fn;
import io.vertx.zero.eon.Tpl;
import io.vertx.zero.exception.heart.ErrorMissingException;
import io.vertx.zero.marshal.node.Node;

import java.text.MessageFormat;

/**
 *
 */
public final class Errors {

    public static String normalize(final Class<?> clazz,
                                   final int code,
                                   final Object... args) {
        return normalize(clazz, code, Tpl.ZERO_ERROR, args);
    }

    public static String normalizeWeb(final Class<?> clazz,
                                      final int code,
                                      final Object... args) {
        return normalize(clazz, code, Tpl.WEB_ERROR, args);
    }

    private static String normalize(final Class<?> clazz,
                                    final int code,
                                    final String tpl,
                                    final Object... args) {
        return Fn.getJvm(() -> {
            final String key = ("E" + Math.abs(code)).intern();
            final Node<JsonObject> node = Node.infix(Plugins.ERROR);
            final JsonObject data = node.read();
            if (null != data && data.containsKey(key)) {
                // 1. Read pattern
                final String pattern = data.getString(key);
                // 2. Build message
                final String error = MessageFormat.format(pattern, args);
                // 3. Format
                return MessageFormat.format(
                        tpl, String.valueOf(code),
                        clazz.getSimpleName(),
                        error
                );
            } else {
                throw new ErrorMissingException(code, clazz.getName());
            }
        }, clazz);
    }

    public static String method(final Class<?> clazzPos,
                                final String methodPos) {
        final StackTraceElement[] methods = Thread.currentThread().getStackTrace();
        String methodName = null;
        int position = 0;
        for (int idx = 0; idx < methods.length; idx++) {
            final String clazz = methods[idx].getClassName();
            final String method = methods[idx].getMethodName();
            if (clazz.equals(clazzPos.getName())
                    && method.equals(methodPos)) {
                position = idx + 1;
            }
        }
        if (position < methods.length - 1) {
            methodName = methods[position].getMethodName();
        }
        return methodName;
    }
}
