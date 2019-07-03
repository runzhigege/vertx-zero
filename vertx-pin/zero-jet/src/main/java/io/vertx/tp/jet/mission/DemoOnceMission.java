package io.vertx.tp.jet.mission;

import io.vertx.up.annotations.Job;
import io.vertx.up.annotations.Off;
import io.vertx.up.annotations.On;
import io.vertx.up.atom.Envelop;
import io.vertx.up.eon.em.JobType;
import io.vertx.up.job.AbstractMission;

@Job(JobType.ONCE)
public class DemoOnceMission extends AbstractMission {

    @On
    public void start(final Envelop envelop) {

    }

    @Off
    public void end(final Envelop envelop) {

    }
}
