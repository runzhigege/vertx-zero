package io.vertx.tp.rbac.authority;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.AuthKey;

import java.util.List;

class UserDetent implements ScDetent {

    private transient final JsonObject input;

    UserDetent(final JsonObject input) {
        this.input = input;
    }

    @Override
    public JsonObject proc(final List<ScProfile> profile) {
        final JsonObject data = new JsonObject();
        /* UNION */
        ScCollect.union(ProfileType.UNION, profile).accept(data);
        /* INTERSECT */
        ScCollect.intersect(ProfileType.INTERSECT, profile).accept(data);
        /* Eager */
        ScCollect.eager(ProfileType.EAGER, profile).accept(data);
        /* Lazy */
        ScCollect.lazy(ProfileType.LAZY, profile).accept(data);
        this.input.put(AuthKey.USER_AUTHORITIES, data);
        return this.input;
    }
}
