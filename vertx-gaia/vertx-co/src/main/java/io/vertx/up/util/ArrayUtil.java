package io.vertx.up.util;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Values;

import java.lang.reflect.Array;
import java.util.Objects;

final class ArrayUtil {

    private ArrayUtil() {
    }

    @SuppressWarnings("all")
    private static Object copyArrayGrow1(final Object array, final Class<?> newArrayComponentType) {
        if (array != null) {
            final int arrayLength = Array.getLength(array);
            final Object newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
            System.arraycopy(array, 0, newArray, 0, arrayLength);
            return newArray;
        }
        return Array.newInstance(newArrayComponentType, 1);
    }

    static <T> T[] add(final T[] array, final T element) {
        final Class<?> type;
        if (array != null) {
            type = array.getClass().getComponentType();
        } else if (element != null) {
            type = element.getClass();
        } else {
            throw new IllegalArgumentException("Arguments cannot both be null");
        }
        @SuppressWarnings("unchecked") // type must be T
        final T[] newArray = (T[]) copyArrayGrow1(array, type);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * Replaced duplicated by key
     * This method will modify input array.
     *
     * @param array      source
     * @param jsonObject element that will be added.
     * @return the new json array
     */
    static JsonArray add(final JsonArray array,
                         final JsonObject jsonObject,
                         final String field) {
        // counter
        int targetIndex = Values.UNSET;
        for (int idx = 0; idx < array.size(); idx++) {
            final JsonObject element = array.getJsonObject(idx);
            if (null != element) {
                final Object elementValue = element.getValue(field);
                final Object value = jsonObject.getValue(field);
                if (Objects.nonNull(elementValue) && Objects.nonNull(value)
                        && elementValue.equals(value)) {
                    targetIndex = idx;
                    break;
                }
            }
        }
        if (Values.ZERO < targetIndex) {
            array.getJsonObject(targetIndex).clear().mergeIn(jsonObject);
        } else {
            array.add(jsonObject);
        }
        return array;
    }
}
