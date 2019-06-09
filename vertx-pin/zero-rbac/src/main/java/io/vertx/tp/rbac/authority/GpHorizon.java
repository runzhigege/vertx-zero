package io.vertx.tp.rbac.authority;

import io.vertx.core.json.JsonObject;

import java.util.List;

/*
 * Group calculation
 * Ignore current group, only parent ok
 *
 */
public class GpHorizon implements ScDetent {

    @Override
    public JsonObject proc(final List<ProfileRole> profiles) {
        /* Group Search */
        final JsonObject group = new JsonObject();

        /* Parent List<ProfileRole> profiles */
        final List<ProfileRole> roles = Align.parent(profiles);
        return new JsonObject();
    }
}
