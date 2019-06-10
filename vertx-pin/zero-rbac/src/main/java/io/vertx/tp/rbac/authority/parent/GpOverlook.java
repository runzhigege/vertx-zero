package io.vertx.tp.rbac.authority.parent;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.authority.*;

import java.util.List;

public class GpOverlook implements ScDetent {

    @Override
    public JsonObject proc(final List<ProfileRole> profiles) {
        /* Group Search */
        final JsonObject group = new JsonObject();
        final List<ProfileRole> source = Amalgam.lazy(profiles);
        /*
         * group = PARENT_OVERLOOK, role = UNION
         * No priority of ( group, role )
         *
         * !!!Finished
         */
        Assembler.union(ProfileType.PARENT_OVERLOOK_UNION, source).accept(group);
        /*
         * group = PARENT_OVERLOOK, role = EAGER
         * No priority of ( group ),  pickup the highest of each group out
         * ( Pick Up the role that group has only one )
         *
         * !!!Finished
         */
        Assembler.eager(ProfileType.PARENT_OVERLOOK_EAGER, source).accept(group);
        /*
         * group = PARENT_OVERLOOK, role = LAZY
         * No priority of ( group ), pickup the lowest of each group out
         * ( Exclude the role that group has only one )
         *
         * !!!Finished
         */
        Assembler.lazy(ProfileType.PARENT_OVERLOOK_LAZY, source).accept(group);
        /*
         * group = PARENT_OVERLOOK, role = INTERSECT
         * No priority of ( group ), pickup all the role's intersect
         * All group must contain the role or it's no access.
         *
         * !!!Finished
         */
        Assembler.intersect(ProfileType.PARENT_OVERLOOK_INTERSECT, source).accept(group);
        return group;
    }
}
