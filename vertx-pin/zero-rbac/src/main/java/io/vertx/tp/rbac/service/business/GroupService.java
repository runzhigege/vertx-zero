package io.vertx.tp.rbac.service.business;

import cn.vertxup.domain.tables.daos.RGroupRoleDao;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.log.Annal;

public class GroupService implements GroupStub {

    private static final Annal LOGGER = Annal.get(GroupService.class);

    @Override
    public Future<JsonArray> fetchRoleIds(final String groupKey) {
        Sc.infoAuth(LOGGER, AuthMsg.RELATION_GROUP_ROLE, groupKey);
        return Sc.relation(AuthKey.F_GROUP_ID, groupKey, RGroupRoleDao.class);
    }
}
