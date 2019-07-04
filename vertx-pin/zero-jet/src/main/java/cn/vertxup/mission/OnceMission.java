package cn.vertxup.mission;

import io.vertx.core.Future;
import io.vertx.up.annotations.Job;
import io.vertx.up.annotations.Off;
import io.vertx.up.annotations.On;
import io.vertx.up.atom.Envelop;
import io.vertx.up.eon.em.JobType;
import io.vertx.up.job.AbstractMission;

@Job(value = JobType.ONCE, name = "demo-once")
public class OnceMission extends AbstractMission {

    @On
    public Future<Envelop> startAsync(final Envelop envelop) {
        /*
         *
         */
        System.out.println("ONCE: execute");
        return Future.succeededFuture(envelop);
    }

    @Off
    public Future<Envelop> endAsync(final Envelop envelop) {
        /*
         *
         */
        System.out.println("ONCE: callback");
        return Future.succeededFuture(envelop);
    }
}
