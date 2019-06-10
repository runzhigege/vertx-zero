package io.vertx.tp.rbac.authority;

import cn.vertxup.domain.tables.pojos.SGroup;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.service.business.GroupService;
import io.vertx.tp.rbac.service.business.GroupStub;
import io.vertx.up.aiki.Ux;
import io.zero.epic.Ut;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
 * Group dynamic searching
 */
public class Align {

    private static final GroupStub STUB = Ut.singleton(GroupService.class);

    /*
     * Search parent ProfileRole based on roles.
     * 1 Level Only ( Find Parent Only )
     */
    public static Future<List<ProfileRole>> flat(final List<ProfileGroup> profiles) {
        return Ux.toFuture(profiles.stream()
                .flatMap(group -> group.getRoles().stream())
                .collect(Collectors.toList()));
    }

    public static Future<List<ProfileRole>> parent(final List<ProfileGroup> profiles) {
        return flat(profiles.stream().map(Align::findParent)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }

    private static ProfileGroup findParent(final ProfileGroup current) {
        /* Group Id */
        final String groupId = current.getKey();
        /* Group Object */
        final SGroup group = STUB.fetchParent(groupId);
        if (null == group) {
            return null;
        } else {
            final JsonObject groupData = new JsonObject();
            groupData.put(AuthKey.F_GROUP_ID, group.getKey());
            groupData.put(AuthKey.PRIORITY, current.getPriority());
            /* GroupId */
            final JsonArray roles = STUB.fetchRoleIds(group.getKey());
            groupData.put("role", roles);
            /* Don't forget to call init() method to set role related permissions. */
            return new ProfileGroup(groupData).init();
        }
    }
}
