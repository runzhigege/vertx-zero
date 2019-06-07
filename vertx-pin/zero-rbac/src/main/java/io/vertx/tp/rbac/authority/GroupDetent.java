package io.vertx.tp.rbac.authority;

import io.vertx.core.json.JsonObject;

import java.util.List;

public class GroupDetent implements ScDetent {
    @Override
    public JsonObject proc(final List<ScProfile> profiles) {
        return null;
    }
}
