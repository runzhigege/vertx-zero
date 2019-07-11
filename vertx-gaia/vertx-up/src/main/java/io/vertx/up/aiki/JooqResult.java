package io.vertx.up.aiki;

import io.vertx.core.json.JsonArray;
import io.vertx.zero.atom.Mojo;
import io.vertx.zero.epic.Ut;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/*
 * Jooq Result for ( DataRegion Needed )
 * 1) `projection` to do filter and remove un-used columns
 */
@SuppressWarnings("all")
class JooqResult {

    static <T> List<T> toResult(final List<T> list, final JsonArray projection, final Mojo mojo) {
        /*
         * convert projection to field
         */
        if (Objects.nonNull(projection) && !projection.isEmpty()) {
            final Set<String> filters = new HashSet<>();
            if (Objects.nonNull(mojo)) {
                /* Pojo file bind */
                projection.stream()
                        .filter(Objects::nonNull)
                        .map(field -> (String) field)
                        .map(field -> mojo.getRevert().get(field))
                        .forEach(filters::add);
            } else {
                /* No Pojo file */
                filters.addAll(projection.getList());
            }
            /* Clear field */
            filters.forEach(filtered -> list.forEach(entity -> Ut.field(entity, filtered, null)));
        }
        return list;
    }
}
