package io.vertx.up.job.phase;

import io.vertx.up.atom.worker.Mission;
import io.vertx.up.eon.em.JobType;
import io.vertx.up.job.plugin.JobIncome;
import io.vertx.up.job.plugin.JobOutcome;
import io.zero.epic.Ut;
import io.zero.epic.fn.Actuator;
import io.zero.epic.fn.Fn;
import io.zero.runtime.Runner;

/*
 * Assist class to help Agha object to process income / outcome extraction
 */
class Element {

    static JobIncome income(final Mission mission) {
        final Class<?> incomeCls = mission.getIncome();
        JobIncome income = null;
        if (Ut.isImplement(incomeCls, JobIncome.class)) {
            income = Fn.pool(Pool.INCOMES, mission.getName(), () -> Ut.instance(incomeCls));
        }
        return income;
    }

    static JobOutcome outcome(final Mission mission) {
        final Class<?> incomeCls = mission.getOutcome();
        JobOutcome outcome = null;
        if (Ut.isImplement(incomeCls, JobOutcome.class)) {
            outcome = Fn.pool(Pool.OUTCOMES, mission.getName(), () -> Ut.instance(incomeCls));
        }
        return outcome;
    }

    static void onceLog(final Mission mission, final Actuator actuator) {
        if (JobType.ONCE == mission.getType()) {
            Runner.run(actuator::execute, "once-logger-debug");
        }
    }
}
