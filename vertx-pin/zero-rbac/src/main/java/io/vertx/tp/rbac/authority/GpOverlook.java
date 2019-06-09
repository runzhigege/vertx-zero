package io.vertx.tp.rbac.authority;

import io.vertx.core.json.JsonObject;

import java.util.List;

/*
 * Group calculation
 * Only one group of low priority
 */
public class GpOverlook implements ScDetent {

    @Override
    public JsonObject proc(final List<ProfileRole> profiles) {
        /* Group Search */
        final JsonObject group = new JsonObject();
        final List<ProfileRole> source = Amalgam.lazy(profiles);
        /*
         * group = OVERLOOK, role = UNION
         * Low Priority of Group, then role union
         *
         * !!!Finished
         * */
        Assembler.union(ProfileType.OVERLOOK_UNION, source).accept(group);
        /*
         * group = OVERLOOK, role = EAGER
         * Low Priority of Group, then role eager
         *
         * !!!Finished
         */
        Assembler.eager(ProfileType.OVERLOOK_EAGER, source).accept(group);
        /*
         * group = OVERLOOK, role = LAZY
         * Low Priority of Group, then role lazy
         *
         * !!!Finished
         */
        Assembler.lazy(ProfileType.OVERLOOK_LAZY, source).accept(group);
        /*
         * group = OVERLOOK, role = INTERSECT
         * Low Priority of Group, then role intersect
         *
         * !!!Finished
         */
        Assembler.intersect(ProfileType.OVERLOOK_INTERSECT, source).accept(group);
        return group;
    }
}
