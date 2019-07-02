package io.vertx.up.job.center;

import io.vertx.up.atom.worker.Mission;

class PlanAgha extends AbstractAgha {

    @Override
    public boolean start(final Mission mission) {
        System.out.println("P");
        return false;
    }
}
