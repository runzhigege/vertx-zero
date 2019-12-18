package io.vertx.up.util;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Values;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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

    static JsonArray climb(final JsonArray children, final JsonArray tree, final JsonObject options) {
        final JsonArray result = new JsonArray();
        /*
         * Array to Map
         */
        final JsonObject opts = treeOption(options);
        final ConcurrentMap<String, JsonObject> maps = new ConcurrentHashMap<>();
        children.stream().filter(Objects::nonNull).map(each -> (JsonObject) each).forEach(child -> {
            /*
             * 当前 child
             */
            final JsonArray parents = climb(child, tree, options);
            final String idField = opts.getString("key");
            parents.stream().filter(Objects::nonNull).map(found -> (JsonObject) found)
                    .filter(found -> Objects.nonNull(found.getString(idField)))
                    .forEach(found -> maps.put(found.getString(idField), found));
        });
        maps.values().forEach(result::add);
        return result;
    }

    static JsonArray climb(final JsonObject child, final JsonArray tree, final JsonObject options) {
        final JsonArray result = new JsonArray();
        /*
         * Wether it contains current node
         */
        final Boolean include = Objects.nonNull(options) ? options.getBoolean("include") : Boolean.TRUE;
        if (include) {
            result.add(child);
        }
        /*
         * Parent Find
         */
        final JsonObject parent = elementParent(child, tree, options);
        if (Objects.nonNull(parent)) {
            result.addAll(climb(parent, tree, options));
        }
        return result;
    }

    private static JsonObject elementParent(final JsonObject item, final JsonArray tree, final JsonObject options) {
        final JsonObject opts = treeOption(options);
        final String parentField = opts.getString("parent");
        final String idField = opts.getString("key");
        return tree.stream().filter(Objects::nonNull).map(each -> (JsonObject) each)
                .filter(each -> Objects.nonNull(each.getString(idField)))
                .filter(each -> each.getString(idField).equals(item.getString(parentField)))
                .findFirst().orElse(null);
    }


    private static JsonObject treeOption(final JsonObject options) {
        final JsonObject target = new JsonObject()
                .put("parent", "parentId")
                .put("key", "key")
                .put("include", Boolean.TRUE);
        if (Objects.nonNull(options)) {
            target.mergeIn(options);
        }
        return target;
    }


}
