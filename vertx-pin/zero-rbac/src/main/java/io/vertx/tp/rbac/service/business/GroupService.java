package io.vertx.tp.rbac.service.business;

import cn.vertxup.domain.tables.daos.RGroupRoleDao;
import cn.vertxup.domain.tables.daos.SGroupDao;
import cn.vertxup.domain.tables.pojos.RGroupRole;
import cn.vertxup.domain.tables.pojos.SGroup;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.aiki.Uarr;
import io.vertx.up.aiki.Ux;
import io.vertx.up.aiki.UxJooq;
import io.vertx.up.log.Annal;

import java.util.ArrayList;
import java.util.List;

public class GroupService implements GroupStub {

    private static final Annal LOGGER = Annal.get(GroupService.class);

    @Override
    public Future<JsonArray> fetchRoleIdsAsync(final String groupKey) {
        Sc.infoAuth(LOGGER, AuthMsg.RELATION_GROUP_ROLE, groupKey, "Async");
        return Sc.relation(AuthKey.F_GROUP_ID, groupKey, RGroupRoleDao.class);
    }

    @Override
    public JsonArray fetchRoleIds(final String groupKey) {
        Sc.infoAuth(LOGGER, AuthMsg.RELATION_GROUP_ROLE, groupKey, "Sync");
        final List<RGroupRole> relations = Ux.Jooq.on(RGroupRoleDao.class)
                .fetch(AuthKey.F_GROUP_ID, groupKey);
        return Uarr.create(Ux.toArray(relations))
                .remove(AuthKey.F_GROUP_ID).to();
    }

    @Override
    public SGroup fetchParent(final String groupKey) {
        final UxJooq dao = Ux.Jooq.on(SGroupDao.class);
        if (null == dao) {
            return null;
        }
        final SGroup current = dao.findById(groupKey);
        return null == current ? null :
                dao.findById(current.getParentId());
    }

    @Override
    public List<SGroup> fetchChildren(final String groupKey) {
        final UxJooq dao = Ux.Jooq.on(SGroupDao.class);
        if (null == dao) {
            return new ArrayList<>();
        }
        return dao.fetch(AuthKey.F_PARENT_ID, groupKey);
    }
}
