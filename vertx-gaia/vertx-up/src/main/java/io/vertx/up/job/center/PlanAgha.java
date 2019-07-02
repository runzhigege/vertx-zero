package io.vertx.up.job.center;

import io.vertx.up.atom.config.ComponentOpts;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.job.refine.JobConfig;
import io.vertx.up.job.refine.JobPin;
import io.vertx.up.job.timer.Interval;
import io.zero.epic.Ut;

import java.util.Optional;

class PlanAgha extends AbstractAgha {

    @Override
    public boolean start(final Mission mission) {
        System.out.println("P");
        return false;
    }

    private Interval interval() {
        final JobConfig config = JobPin.getConfig();
        final ComponentOpts interval = config.getInterval();
        final Class<?> clazz = interval.getComponent();
        return Optional.ofNullable(clazz)
                .<Interval>map(each -> Ut.singleton(each))
                .orElse(null);
    }
}
