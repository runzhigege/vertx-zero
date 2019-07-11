package io.vertx.up.job.phase;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.eon.Info;
import io.vertx.up.job.plugin.JobOutcome;
import io.vertx.up.log.Annal;
import io.vertx.zero.epic.Ut;

import java.util.Objects;

class OutPut {
    private static final Annal LOGGER = Annal.get(OutPut.class);
    private transient final Vertx vertx;

    OutPut(final Vertx vertx) {
        this.vertx = vertx;
    }

    Future<Envelop> outcomeAsync(final Envelop envelop, final Mission mission) {
        if (envelop.valid()) {
            /*
             * Get JobOutcome
             */
            final JobOutcome outcome = Element.outcome(mission);
            if (Objects.isNull(outcome)) {
                /*
                 * Directly
                 */
                Element.onceLog(mission,
                        () -> LOGGER.info(Info.PHASE_4TH_JOB, mission.getName()));

                return Future.succeededFuture(envelop);
            } else {
                /*
                 * JobOutcome processing here
                 * Contract for vertx/mission
                 */
                LOGGER.info(Info.JOB_COMPONENT_SELECTED, "JobOutcome", outcome.getClass().getName());
                Ut.contract(outcome, Vertx.class, vertx);
                Ut.contract(outcome, Mission.class, mission);

                Element.onceLog(mission,
                        () -> LOGGER.info(Info.PHASE_4TH_JOB_ASYNC, mission.getName(), outcome.getClass().getName()));
                return outcome.afterAsync(envelop);
            }
        } else {
            Element.onceLog(mission,
                    () -> LOGGER.info(Info.PHASE_ERROR, mission.getName(),
                            envelop.error().getClass().getName()));

            return envelop.toFuture();
        }
    }

    Future<Envelop> outputAsync(final Envelop envelop, final Mission mission) {
        if (envelop.valid()) {
            /*
             * Get outcome address
             */
            final String address = mission.getOutcomeAddress();
            if (Ut.isNil(address)) {
                /*
                 * Directly
                 */
                Element.onceLog(mission,
                        () -> LOGGER.info(Info.PHASE_5TH_JOB, mission.getName()));
                return Future.succeededFuture(envelop);
            } else {
                /*
                 * Event bus provide output and then it will execute
                 */
                LOGGER.info(Info.JOB_ADDRESS_EVENT_BUS, "Outcome", address);
                final Future<Envelop> output = Future.future();
                final EventBus eventBus = vertx.eventBus();
                Element.onceLog(mission,
                        () -> LOGGER.info(Info.PHASE_5TH_JOB_ASYNC, mission.getName(), address));
                eventBus.<Envelop>send(address, envelop, handler -> {
                    if (handler.succeeded()) {
                        output.complete(handler.result().body());
                    } else {
                        output.complete(Envelop.failure(handler.cause()));
                    }
                });
                return output;
            }
        } else {
            Element.onceLog(mission,
                    () -> LOGGER.info(Info.PHASE_ERROR, mission.getName(),
                            envelop.error().getClass().getName()));

            return envelop.toFuture();
        }
    }
}
