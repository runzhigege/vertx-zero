package io.vertx.tp.rbac.authority;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.AuthKey;

import java.util.List;

class ScDetentRole implements ScDetent {

    private transient final JsonObject input;

    ScDetentRole(final JsonObject input) {
        this.input = input;
    }

    @Override
    public JsonObject proc(final List<ProfileRole> profile) {
        final JsonObject data = new JsonObject();
        /* UNION */
        Collection.union(ProfileType.UNION, profile).accept(data);
        /* INTERSECT */
        Collection.intersect(ProfileType.INTERSECT, profile).accept(data);
        /* EAGER */
        Collection.eager(ProfileType.EAGER, profile).accept(data);
        /* LAZY */
        Collection.lazy(ProfileType.LAZY, profile).accept(data);
        this.input.put(AuthKey.USER_AUTHORITIES, data);
        return this.input;
    }
}
