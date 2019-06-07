package io.vertx.tp.rbac.authority;

import io.vertx.core.json.JsonObject;

import java.util.List;

/*
 * Horizon Group
 */
public class ScDetentGroup implements ScDetent {

    private transient final JsonObject input;

    public ScDetentGroup(final JsonObject input) {
        this.input = input;
    }

    @Override
    public JsonObject proc(final List<ProfileRole> profiles) {
        profiles.forEach(System.out::println);
        return null;
    }
}
