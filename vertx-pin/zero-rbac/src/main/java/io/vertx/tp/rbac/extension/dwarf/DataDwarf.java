package io.vertx.tp.rbac.extension.dwarf;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.em.RegionType;
import io.zero.epic.fn.Fn;

/*
 * Dwarf
 */
public interface DataDwarf {

    static DataDwarf create(final RegionType type) {
        if (RegionType.RECORD == type) {
            return Fn.pool(Pool.DWARF_POOL, type, RecordDwarf::new);
        } else if (RegionType.PAGINATION == type) {
            return Fn.pool(Pool.DWARF_POOL, type, PaginationDwarf::new);
        } else if (RegionType.ARRAY == type) {
            return Fn.pool(Pool.DWARF_POOL, type, ArrayDwarf::new);
        } else {
            return null;
        }
    }

    void minimize(JsonObject dataReference, JsonObject matrix);
}
