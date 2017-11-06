package com.vie.hoc;

import com.vie.cv.Values;
import com.vie.fun.error.JdBiConsumer;
import com.vie.hors.ZeroException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.LinkedHashMap;
import java.util.function.BiConsumer;

@SuppressWarnings("unchecked")
public class HJson {

    public static void execIt(final JsonObject data,
                              final BiConsumer<Object, String> fnIt) {
        HNull.exec(() -> {
            for (final String name : data.fieldNames()) {
                final Object item = data.getValue(name);
                if (null != item) {
                    /**
                     * Swap the value and key.
                     */
                    fnIt.accept(item, name);
                }
            }
        }, data, fnIt);
    }

    public static void execIt(final JsonArray dataArray,
                              final BiConsumer<JsonObject, Integer> fnIt) {
        HNull.exec(() -> {
            final int size = dataArray.size();
            for (int idx = Values.IDX; idx < size; idx++) {
                final Object value = dataArray.getValue(idx);
                if (null != value) {
                    if (JsonObject.class == value.getClass()
                            || LinkedHashMap.class == value.getClass()) {
                        final JsonObject item = (JsonObject) value;
                        fnIt.accept(item, idx);
                    }
                }
            }
        }, dataArray, fnIt);
    }

    public static <T> void execZero(final JsonArray dataArray,
                                    final Class<T> clazz,
                                    final JdBiConsumer<T, Integer> fnIt)
            throws ZeroException {
        HNull.execZero(() -> {
            final int size = dataArray.size();
            for (int idx = Values.IDX; idx < size; idx++) {
                final Object value = dataArray.getValue(idx);
                if (null != value) {
                    if (clazz == value.getClass()) {
                        final T item = (T) value;
                        fnIt.accept(item, idx);
                    }
                }
            }
        }, dataArray, clazz, fnIt);
    }
}
