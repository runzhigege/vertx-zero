package io.vertx.tp.rbac.service.business;

import cn.vertxup.domain.tables.daos.OUserDao;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.up.aiki.Ux;
import io.vertx.up.log.Annal;

public class UserService implements UserStub {

    private static final Annal LOGGER = Annal.get(UserService.class);

    @Override
    public Future<JsonObject> fetchOUser(final String userKey) {
        return Ux.Jooq.on(OUserDao.class)
                .fetchOneAsync(AuthKey.F_CLIENT_ID, userKey)
                .compose(Ux::fnJObject);
    }
}
