package io.vertx.tp.optic;

import cn.vertxup.erp.domain.tables.daos.EEmployeeDao;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.unity.Ux;

/*
 * Get user information from database
 * Account + Employee
 */
public class ExUserEpic implements EcUser {
    @Override
    public Future<JsonObject> fetchAsync(final String id) {
        return Ux.Jooq.on(EEmployeeDao.class)
                .findByIdAsync(id)
                .compose(Ux::fnJObject);
    }
}
