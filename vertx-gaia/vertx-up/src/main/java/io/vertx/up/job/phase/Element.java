package io.vertx.up.job.phase;

import io.vertx.up.annotations.Off;
import io.vertx.up.annotations.On;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.job.plugin.JobIncome;
import io.vertx.up.job.plugin.JobOutcome;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/*
 * Assist class to help Agha object to process income / outcome extraction
 */
class Element {

    private static Annotation on(final Mission mission) {
        final Method on = mission.getOn();
        return on.getAnnotation(On.class);
    }

    private static Annotation off(final Mission mission) {
        final Method off = mission.getOff();
        return off.getAnnotation(Off.class);
    }

    static JobIncome income(final Mission mission) {
        final Class<?> incomeCls = Ut.invoke(on(mission), "income");
        JobIncome income = null;
        if (Ut.isImplement(incomeCls, JobIncome.class)) {
            income = Fn.pool(Pool.INCOMES, mission.getName(), () -> Ut.instance(incomeCls));
        }
        return income;
    }

    static JobOutcome outcome(final Mission mission) {
        final Class<?> incomeCls = Ut.invoke(off(mission), "outcome");
        JobOutcome outcome = null;
        if (Ut.isImplement(incomeCls, JobOutcome.class)) {
            outcome = Fn.pool(Pool.OUTCOMES, mission.getName(), () -> Ut.instance(incomeCls));
        }
        return outcome;
    }

    static String address(final Mission mission, final boolean isOn) {
        final Annotation annotation = isOn ? on(mission) : off(mission);
        return Ut.invoke(annotation, "address");
    }
}
