package io.vertx.tp.erp.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public interface CompanyStub {
    /*
     * Get company information by `employeeId`
     */
    Future<JsonObject> fetchByEmployee(String employeeId);

    /*
     * Get company information by `companyId`
     */
    Future<JsonObject> fetch(String companyId);

    /*
     * Get companys by : sigma = {xxx}
     */
    Future<JsonArray> fetchCompanys(String sigma);
}
