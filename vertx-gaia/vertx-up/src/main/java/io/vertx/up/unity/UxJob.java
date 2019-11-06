package io.vertx.up.unity;

import io.vertx.core.Future;
import io.vertx.tp.plugin.job.JobClient;
import io.vertx.tp.plugin.job.JobInfix;
import io.vertx.tp.plugin.job.JobPool;
import io.vertx.up.fn.Fn;
import io.vertx.up.log.Annal;

public class UxJob {
    private static final Annal LOGGER = Annal.get(UxJob.class);
    private transient final JobClient client;

    UxJob() {
        this.client = JobInfix.getClient();
    }

    // Start job
    public Future<Boolean> start(final String name) {
        return Fn.thenGeneric(future -> this.client.start(name,
                res -> {
                    LOGGER.info(Info.JOB_START, name, res.result());
                    future.complete(Boolean.TRUE);
                }));
    }

    // Stop job
    public Future<Boolean> stop(final String name) {
        final long timeId = JobPool.timeId(name);
        return Fn.thenGeneric(future -> this.client.stop(timeId,
                res -> {
                    LOGGER.info(Info.JOB_STOP, name, res.result());
                    future.complete(Boolean.TRUE);
                }));
    }

    // Resume job
    public Future<Boolean> resume(final String name) {
        final long timeId = JobPool.timeId(name);
        return Fn.thenGeneric(future -> this.client.resume(timeId,
                res -> {
                    LOGGER.info(Info.JOB_RESUME, name, res.result());
                    future.complete(Boolean.TRUE);
                }));
    }
}
