package io.vertx.tp.rbac.authority;

import cn.vertxup.domain.tables.daos.SGroupDao;
import cn.vertxup.domain.tables.pojos.SGroup;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.up.aiki.Ux;
import io.vertx.up.aiki.UxJooq;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
 * Group dynamic searching
 */
class Align {
    /*
     * Search parent ProfileRole based on roles.
     * 1 Level Only ( Find Parent Only )
     */

    static Future<List<ProfileRole>> flat(final List<ProfileGroup> profiles) {
        return Ux.toFuture(profiles.stream()
                .flatMap(group -> group.getRoles().stream())
                .collect(Collectors.toList()));
    }

    static Future<List<ProfileRole>> parent(final List<ProfileGroup> profiles) {
        return flat(profiles.stream().map(Align::findParent)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }

    private static ProfileGroup findParent(final ProfileGroup current) {
        /* Group Id */
        final String groupId = current.getKey();
        /* Group Object */
        final SGroup group = findParent(groupId);
        if (null == group) {
            return null;
        } else {
            final JsonObject groupData = new JsonObject();
            groupData.put(AuthKey.F_GROUP_ID, group.getKey());
            groupData.put(AuthKey.PRIORITY, current.getPriority());
            /* Profile Group */
            return null;
        }
    }

    private static SGroup findParent(final String groupId) {
        final UxJooq dao = Ux.Jooq.on(SGroupDao.class);
        if (null == dao) {
            return null;
        }
        final SGroup current = dao.fetchOne("key", groupId);
        return null == current ? null :
                dao.fetchOne("key", current.getParentId());
    }
}
