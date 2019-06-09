package io.vertx.tp.rbac.authority;

import io.vertx.core.json.JsonObject;

import java.util.List;

public class SgCritical implements ScDetent {

    @Override
    public JsonObject proc(final List<ProfileRole> profiles) {
        /* Group Search */
        final JsonObject group = new JsonObject();
        /* Filter Group first */

        return new JsonObject();
    }
}
