package io.vertx.tp.erp.service;

import cn.vertxup.erp.domain.tables.daos.EDeptDao;
import cn.vertxup.erp.domain.tables.pojos.EDept;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.unity.Ux;

public class DeptService implements DeptStub {

    @Override
    public Future<JsonArray> fetchDepts(final String sigma) {
        return Ux.Jooq.on(EDeptDao.class)
                /* Fetch by sigma */
                .<EDept>fetchAsync(KeField.SIGMA, sigma)
                /* Get Result */
                .compose(Ux::fnJArray);
    }

}
