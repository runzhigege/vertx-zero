package io.vertx.up.job.center;

import io.vertx.up.atom.worker.Mission;

class PlanAgha extends AbstractAgha {

    @Override
    public long begin(final Mission mission) {

        return -1L;
    }

    @Override
    public boolean end(final Mission mission) {

        return false;
    }
}
