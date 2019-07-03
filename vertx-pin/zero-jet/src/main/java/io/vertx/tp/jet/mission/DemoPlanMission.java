package io.vertx.tp.jet.mission;

import io.vertx.up.annotations.Job;
import io.vertx.up.annotations.Off;
import io.vertx.up.annotations.On;
import io.vertx.up.eon.em.JobType;

@Job(JobType.PLAN)
public class DemoPlanMission {

    @On
    public void start() {

    }

    @Off
    public void end() {

    }
}
