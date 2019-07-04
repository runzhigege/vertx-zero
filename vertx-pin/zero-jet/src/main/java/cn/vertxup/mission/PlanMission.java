package cn.vertxup.mission;

import io.vertx.core.Future;
import io.vertx.up.annotations.Job;
import io.vertx.up.annotations.Off;
import io.vertx.up.annotations.On;
import io.vertx.up.atom.Envelop;
import io.vertx.up.eon.em.JobType;
import io.vertx.up.job.AbstractMission;

@Job(value = JobType.PLAN, name = "demo-plan")
public class PlanMission extends AbstractMission {
    @On
    public Future<Envelop> startAsync(final Envelop envelop) {
        /*
         *
         */
        System.out.println("PLAN: execute");
        return Future.succeededFuture(envelop);
    }

    @Off
    public Future<Envelop> endAsync(final Envelop envelop) {
        /*
         *
         */
        System.out.println("PLAN: callback");
        return Future.succeededFuture(envelop);
    }
}
