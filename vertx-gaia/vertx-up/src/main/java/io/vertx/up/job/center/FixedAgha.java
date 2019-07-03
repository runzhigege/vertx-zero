package io.vertx.up.job.center;

import io.vertx.up.atom.worker.Mission;

class FixedAgha extends AbstractAgha {

    @Override
    public boolean begin(final Mission mission) {
        System.out.println("F");
        return false;
    }

    @Override
    public boolean end(final Mission mission) {

        return false;
    }
}
