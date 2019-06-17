package io.vertx.tp.rbac.extension.dwarf;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/*
 * rows only
 */
class ArrayDwarf implements DataDwarf {
    /*
     * {
     *     "data": []
     * }
     *
     */
    @Override
    public void minimize(final JsonObject dataReference, final JsonObject matrix) {
        /* inputArray */
        final JsonArray inputArray = dataReference.getJsonArray("data");
        /* rows */
        final JsonArray updated = Dwarf.onRows(inputArray, matrix.getJsonObject("rows"));
        /* Updated */
        dataReference.put("data", updated);
    }
}
