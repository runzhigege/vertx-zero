package io.vertx.tp.rbac.service.business;

import cn.vertxup.domain.tables.daos.RUserGroupDao;
import cn.vertxup.domain.tables.daos.RUserRoleDao;
import cn.vertxup.domain.tables.pojos.RUserGroup;
import cn.vertxup.domain.tables.pojos.RUserRole;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.aiki.Ux;
import io.vertx.up.log.Annal;

import java.util.List;

public class LinkService implements LinkStub {
    private static final Annal LOGGER = Annal.get(LinkService.class);

    @Override
    public Future<List<String>> fetchRoleIds(final String userKey) {
        Sc.infoAuth(LOGGER, AuthMsg.RELATION_ROLE, userKey);
        return Ux.Jooq.on(RUserRoleDao.class)
                .<RUserRole>fetchAsync(AuthKey.F_USER_ID, userKey)
                .compose(relation -> Ux.toFuture(Sc.reduce(relation, RUserRole::getRoleId)));
    }

    @Override
    public Future<List<String>> fetchGroupIds(final String userKey) {
        Sc.infoAuth(LOGGER, AuthMsg.RELATION_GROUP, userKey);
        return Ux.Jooq.on(RUserGroupDao.class)
                .<RUserGroup>fetchAsync(AuthKey.F_USER_ID, userKey)
                .compose(relation -> Ux.toFuture(Sc.reduce(relation, RUserGroup::getGroupId)));
    }

    @Override
    public Future<JsonArray> fetchRoles(final String userKey) {
        return null;
    }


    @Override
    public Future<JsonArray> fetchGroups(final String userKey) {
        return null;
    }
}
