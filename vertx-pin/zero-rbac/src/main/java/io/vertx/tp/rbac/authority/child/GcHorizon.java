package io.vertx.tp.rbac.authority.child;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.authority.*;

import java.util.List;

/*
 * Group calculation
 * Child
 */
public class GcHorizon implements ScDetent {

    @Override
    public JsonObject proc(final List<ProfileRole> profiles) {
        /* Group Search */
        final JsonObject group = new JsonObject();
        /*
         * group = CHILD_HORIZON, role = UNION
         * No priority of ( group, role )
         *
         * !!!Finished
         */
        Assembler.union(ProfileType.CHILD_HORIZON_UNION, profiles).accept(group);
        /*
         * group = CHILD_HORIZON, role = EAGER
         * No priority of ( group ),  pickup the highest of each group out
         * ( Pick Up the role that group has only one )
         *
         * !!!Finished
         */
        Assembler.union(ProfileType.CHILD_HORIZON_EAGER, Amalgam.eagerEach(profiles)).accept(group);
        /*
         * group = CHILD_HORIZON, role = LAZY
         * No priority of ( group ), pickup the lowest of each group out
         * ( Exclude the role that group has only one )
         *
         * !!!Finished
         */
        Assembler.union(ProfileType.CHILD_HORIZON_LAZY, Amalgam.lazyEach(profiles)).accept(group);
        /*
         * group = CHILD_HORIZON, role = INTERSECT
         * No priority of ( group ), pickup all the role's intersect
         * All group must contain the role or it's no access.
         *
         * !!!Finished
         */
        Assembler.intersect(ProfileType.CHILD_HORIZON_INTERSECT, profiles).accept(group);
        return group;
    }
}
