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
    public Future<Boolean> start(final String name) {
        return Ux.Job.on().start(name);
    }

    @Address(JtAddr.Job.STOP)
    public Future<Boolean> stop(final String name) {
        return Ux.Job.on().stop(name);
    }

    @Address(JtAddr.Job.RESUME)
    public Future<Boolean> resume(final String name) {
        return Ux.Job.on().resume(name);
    }

    @Address(JtAddr.Job.STATUS)
    public JsonObject status(final String name) {
        final Mission mission = JobPool.get(name);
        return Ux.toJson(mission);
    }

    @Address(JtAddr.Job.BY_SIGMA)
    public Future<JsonArray> fetch(final String sigma) {
        return this.stub.fetchAll(sigma);
    }
}
