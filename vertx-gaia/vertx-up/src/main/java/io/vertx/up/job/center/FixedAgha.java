package io.vertx.up.job.center;

import io.vertx.core.Future;
import io.vertx.up.atom.worker.Mission;

class FixedAgha extends AbstractAgha {

    @Override
    public Future<Long> begin(final Mission mission) {
        return Future.succeededFuture(-1L);
    }
}
