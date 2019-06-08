package io.vertx.tp.rbac.authority;

import io.vertx.core.json.JsonObject;

import java.util.List;

public class ScAimHorizon implements ScDetent {

    private transient final JsonObject input;

    ScAimHorizon(final JsonObject input) {
        this.input = input;
    }

    @Override
    public JsonObject proc(final List<ProfileRole> profiles) {
        /* 1. Group Search */
        final JsonObject group = new JsonObject();
        /* group = HORIZON, role = HORIZON */
        Assembler.union(ProfileType.HORIZON_U, profiles).accept(group);
        /* group = CRITICAL, role = HORIZON */
        Assembler.eager(ProfileType.HORIZON_E, Amalgam.eager(profiles)).accept(group);
        /* group = OVERLOOK, role = HORIZON */
        Assembler.lazy(ProfileType.HORIZON_L, Amalgam.lazy(profiles)).accept(group);
        return group;
    }
}
