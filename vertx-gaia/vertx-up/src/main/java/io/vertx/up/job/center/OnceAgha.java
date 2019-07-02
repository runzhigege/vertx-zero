package io.vertx.up.job.center;

import io.vertx.up.atom.worker.Mission;

/**
 * Start one time
 */
class OnceAgha extends AbstractAgha {

    @Override
    public boolean start(final Mission mission) {
        System.out.println("O");
        return false;
    }
}
