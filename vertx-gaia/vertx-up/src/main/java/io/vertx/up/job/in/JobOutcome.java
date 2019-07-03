package io.vertx.up.job.in;

import io.vertx.core.Future;
import io.vertx.up.atom.Envelop;

/*
 * Job outcome, this outcome interface should provide Future<JobOut> to execute
 */
public interface JobOutcome {
    /*
     * Async process outcome here
     */
    Future<Envelop> afterAsync(final Envelop envelop);
}
