package io.vertx.tp.jet.extension;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Off;
import io.vertx.up.annotations.On;
import io.vertx.up.atom.Envelop;
import io.vertx.up.job.AbstractMission;

/*
 * Configured in database, it's not need @Job
 */
// @Job(value = JobType.CONTAINER)
public class JetMission extends AbstractMission {
    /*
     * Data example
     * refer mission-config.json
     */
    @On
    public Future<Envelop> start(final JsonObject config) {
        System.out.println("Hello" + config.encodePrettily());
        return Future.succeededFuture();
    }

    @Off
    public Future<Envelop> end(final JsonObject config) {
        System.out.println("Callback" + config.encodePrettily());
        return Future.succeededFuture();
    }
}
