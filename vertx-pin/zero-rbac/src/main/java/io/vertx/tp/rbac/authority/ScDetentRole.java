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
        /*
         * role = UNION
         *
         * !!!Finished
         * */
        Assembler.union(ProfileType.UNION, profile).accept(data);
        /*
         * role = INTERSECT
         *
         * !!!Finished
         * */
        Assembler.intersect(ProfileType.INTERSECT, profile).accept(data);
        /*
         * role = EAGER
         *
         * !!!Finished
         * */
        Assembler.eager(ProfileType.EAGER, profile).accept(data);
        /*
         * role = LAZY
         *
         * !!!Finished
         * */
        Assembler.lazy(ProfileType.LAZY, profile).accept(data);

        return this.input.put(AuthKey.USER_AUTHORITIES, data);
    }
}
