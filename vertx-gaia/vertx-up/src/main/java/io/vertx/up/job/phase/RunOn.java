package io.vertx.up.job.phase;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.worker.Mission;

class RunOn {
    private transient final Vertx vertx;

    RunOn(final Vertx vertx) {
        this.vertx = vertx;
    }

    Future<Envelop> invoke(final Envelop envelop, final Mission mission) {
        System.out.println("Invoke");
        return Future.succeededFuture(envelop);
    }

    Future<Envelop> callback(final Envelop envelop, final Mission mission) {
        System.out.println("Callback");
        return Future.succeededFuture(envelop);
    }
}
