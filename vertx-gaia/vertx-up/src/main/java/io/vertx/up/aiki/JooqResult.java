package io.vertx.up.aiki;

import io.vertx.core.json.JsonArray;
import io.vertx.zero.atom.Mojo;

import java.util.List;

/*
 * Jooq Result for
 * 1) `projection` to do filter and remove un-used columns
 */
class JooqResult {

    static <T> List<T> toResult(final List<T> list, final JsonArray projection, final Mojo mojo) {

        return list;
    }
}
