package io.vertx.up.uca.job.plugin;

import io.vertx.core.Future;
import io.vertx.up.commune.Envelop;

/*
 * Job income before, this income interface should provide Future<JobIn> to Job to consume
 */
public interface JobIncome {
    /*
     * Async process income here
     */
    Future<Envelop> beforeAsync(final Envelop envelop);
}
