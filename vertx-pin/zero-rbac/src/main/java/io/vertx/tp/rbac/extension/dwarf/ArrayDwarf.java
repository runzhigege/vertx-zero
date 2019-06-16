package io.vertx.tp.rbac.extension.dwarf;

import io.vertx.core.json.JsonObject;

class ArrayDwarf implements DataDwarf {
    @Override
    public void minimize(final JsonObject dataReference, final JsonObject matrix) {
        System.out.println(matrix.encodePrettily());
    }
}
