package io.vertx.tp.rbac.authority;

import io.vertx.core.json.JsonObject;

import java.util.List;

/*
 * Group calculation
 * Ignore priority of group
 */
public class GdHorizon implements ScDetent {

    @Override
    public JsonObject proc(final List<ProfileRole> profiles) {
        /* Group Search */
        final JsonObject group = new JsonObject();
        /*
         * group = HORIZON, role = UNION
         * No priority of ( group, role )
         *
         * !!!Finished
         * */
        Assembler.union(ProfileType.HORIZON_UNION, profiles).accept(group);
        /*
         * group = HORIZON, role = EAGER
         * No priority of ( group ),  pickup the highest of each group out
         * ( Pick Up the role that group has only one )
         *
         * !!!Finished
         */
        Assembler.union(ProfileType.HORIZON_EAGER, Amalgam.eagerEach(profiles)).accept(group);
        /*
         * group = HORIZON, role = LAZY
         * No priority of ( group ), pickup the lowest of each group out
         * ( Exclude the role that group has only one )
         *
         * !!!Finished
         */
        Assembler.union(ProfileType.HORIZON_LAZY, Amalgam.lazyEach(profiles)).accept(group);
        /*
         * group = HORIZON, role = INTERSECT
         * No priority of ( group ), pickup all the role's intersect
         * All group must contain the role or it's no access.
         *
         * !!!Finished
         */
        Assembler.intersect(ProfileType.HORIZON_INTERSECT, profiles).accept(group);
        return group;
    }
}
