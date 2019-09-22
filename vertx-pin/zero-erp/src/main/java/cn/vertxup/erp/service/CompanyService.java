package cn.vertxup.erp.service;

import cn.vertxup.erp.domain.tables.daos.ECompanyDao;
import cn.vertxup.erp.domain.tables.daos.EEmployeeDao;
import cn.vertxup.erp.domain.tables.pojos.ECompany;
import cn.vertxup.erp.domain.tables.pojos.EEmployee;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.unity.Ux;

import java.util.Objects;

public class CompanyService implements CompanyStub {
    @Override
    public Future<JsonObject> fetchByEmployee(final String employeeId) {
        return Ux.Jooq.on(EEmployeeDao.class)
                .<EEmployee>findByIdAsync(employeeId)
                .compose(employee -> this.fetchById(Objects.isNull(employee) ?
                        null : employee.getCompanyId()))
                .compose(Ux::fnJObject);
    }

    @Override
    public Future<JsonObject> fetch(final String companyId) {
        return this.fetchById(companyId)
                .compose(Ux::fnJObject);
    }

    private Future<ECompany> fetchById(final String companyId) {
        return Ux.Jooq.on(ECompanyDao.class)
                .findByIdAsync(companyId);
    }

    @Override
    public Future<JsonArray> fetchCompanys(final String sigma) {
        return Ux.Jooq.on(ECompanyDao.class)
                /* Fetch by sigma */
                .<ECompany>fetchAsync(KeField.SIGMA, sigma)
                /* Get Result */
                .compose(Ux::fnJArray);
    }
}
