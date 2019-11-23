package cn.vertxup.jet.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;

/*
 * Job Stub for job processing here
 * Job Management in front end
 */
public interface JobStub {

    Future<JsonArray> fetchAll(String sigma);
}
