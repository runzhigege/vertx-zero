package io.vertx.tp.rbac.authorization.inherit;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.authorization.*;

import java.util.List;

public class GiOverlook implements ScDetent {

    private transient final List<ProfileGroup> original;

    public GiOverlook(final List<ProfileGroup> original) {
        this.original = original;
    }

    private List<ProfileRole> before(final List<ProfileRole> profiles) {
        /* Find eager group in Critical */
        final ProfileGroup lazy = Align.lazy(this.original);
        /* Filter by group key */
        final List<ProfileRole> source = Amalgam.parent(profiles, lazy);
        /* Then filter by priority */
        final List<ProfileRole> processed = Amalgam.lazy(source);
        /* Final Append */
        return Assembler.connect(processed, lazy);
    }

    @Override
    public JsonObject proc(final List<ProfileRole> profiles) {
        /* Group Search */
        final JsonObject group = new JsonObject();
        final List<ProfileRole> source = this.before(profiles);
        Amalgam.logGroup(this.getClass(), source);
        /*
         * group = INHERIT_OVERLOOK, role = UNION
         * No priority of ( group, role )
         *
         * !!!Finished
         */
        Assembler.union(ProfileType.INHERIT_OVERLOOK_UNION, source).accept(group);
        /*
         * group = INHERIT_OVERLOOK, role = EAGER
         * No priority of ( group ),  pickup the highest of each group out
         * ( Pick Up the role that group has only one )
         *
         * !!!Finished
         */
        Assembler.eager(ProfileType.INHERIT_OVERLOOK_EAGER, source).accept(group);
        /*
         * group = INHERIT_OVERLOOK, role = LAZY
         * No priority of ( group ), pickup the lowest of each group out
         * ( Exclude the role that group has only one )
         *
         * !!!Finished
         */
        Assembler.lazy(ProfileType.INHERIT_OVERLOOK_LAZY, source).accept(group);
        /*
         * group = INHERIT_OVERLOOK, role = INTERSECT
         * No priority of ( group ), pickup all the role's intersect
         * All group must contain the role or it's no access.
         *
         * !!!Finished
         */
        Assembler.intersect(ProfileType.INHERIT_OVERLOOK_INTERSECT, source).accept(group);
        return group;
    }
}
