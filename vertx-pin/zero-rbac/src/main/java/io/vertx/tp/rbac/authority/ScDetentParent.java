package io.vertx.tp.rbac.authority;

import io.vertx.core.json.JsonObject;

import java.util.List;

public class ScDetentParent implements ScDetent {

    private transient final JsonObject input;

    ScDetentParent(final JsonObject input) {
        this.input = input;
    }

    @Override
    public JsonObject proc(final List<ProfileRole> profiles) {
        final JsonObject parent = new JsonObject();
        /* Bind group profiles first */
        return this.input.mergeIn(parent);
    }
}
