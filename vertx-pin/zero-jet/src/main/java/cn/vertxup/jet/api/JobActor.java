package cn.vertxup.jet.api;

import cn.vertxup.jet.service.JobStub;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.cv.JtAddr;
import io.vertx.tp.plugin.job.JobPool;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.unity.Ux;

import javax.inject.Inject;

@Queue
public class JobActor {

    @Inject
    private transient JobStub stub;

    @Address(JtAddr.Job.START)
    public Future<Boolean> start(final String code) {
        return Ux.Job.on().start(code);
    }

    @Address(JtAddr.Job.STOP)
    public Future<Boolean> stop(final String code) {
        return Ux.Job.on().stop(code);
    }

    @Address(JtAddr.Job.RESUME)
    public Future<Boolean> resume(final String code) {
        return Ux.Job.on().resume(code);
    }

    @Address(JtAddr.Job.STATUS)
    public JsonObject status(final String code) {
        final Mission mission = JobPool.get(code);
        return Ux.toJson(mission);
    }

    /*
     * Basic Job api here
     */
    @Address(JtAddr.Job.BY_SIGMA)
    public Future<JsonArray> fetch(final String sigma) {
        return this.stub.fetchAll(sigma);
    }

    @Address(JtAddr.Job.GET_BY_KEY)
    public Future<JsonObject> fetchByKey(final String key) {
        return this.stub.fetchByKey(key);
    }

    @Address(JtAddr.Job.UPDATE_BY_KEY)
    public Future<JsonObject> updateByKey(final String key, final JsonObject data) {
        return this.stub.update(key, data);
    }
}
