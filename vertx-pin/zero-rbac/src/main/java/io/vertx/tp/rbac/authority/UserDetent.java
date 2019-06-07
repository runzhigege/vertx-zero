package io.vertx.tp.rbac.authority;

import io.vertx.core.json.JsonArray;

import java.util.List;

public class UserDetent implements ScDetent {
    @Override
    public JsonArray proc(final List<ScProfile> profile) {
        profile.forEach(each -> {
            System.out.println(each.getPriority());
            System.out.println(each.getAuthorities());
        });
        return new JsonArray();
    }
}
