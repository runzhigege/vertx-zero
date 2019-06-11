package io.vertx.tp.rbac.authority.detent;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.authority.Assembler;
import io.vertx.tp.rbac.authority.ProfileRole;
import io.vertx.tp.rbac.authority.ProfileType;
import io.vertx.tp.rbac.authority.ScDetent;

import java.util.List;

public class ScDetentRole implements ScDetent {

    private transient final JsonObject input;

    public ScDetentRole(final JsonObject input) {
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

        return this.input.mergeIn(data);
    }
}
