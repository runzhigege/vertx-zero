package io.vertx.tp.optic;

import cn.vertxup.erp.domain.tables.daos.ECompanyDao;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.unity.Ux;

/*
 * Get user information from database
 * Company Information
 */
public class ExCompanyEpic implements EcCompany {
    @Override
    public Future<JsonObject> fetchAsync(final String id) {
        return Ux.Jooq.on(ECompanyDao.class)
                .findByIdAsync(id)
                .compose(Ux::fnJObject);
    }
}
