package cn.vertxup.erp.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;

public interface DeptStub {
    /*
     * Get depts by : sigma = {xxx}
     */
    Future<JsonArray> fetchDepts(String sigma);
}
