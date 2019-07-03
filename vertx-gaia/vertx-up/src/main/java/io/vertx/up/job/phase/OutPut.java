package io.vertx.up.job.phase;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.job.cv.JobMsg;
import io.vertx.up.job.plugin.JobOutcome;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;

import java.util.Objects;

class OutPut {
    private static final Annal LOGGER = Annal.get(OutPut.class);
    private transient final Vertx vertx;

    OutPut(final Vertx vertx) {
        this.vertx = vertx;
    }

    Future<Envelop> outcomeAsync(final Envelop envelop, final Mission mission) {
        /*
         * Get JobOutcome
         */
        final JobOutcome outcome = Element.outcome(mission);
        if (Objects.isNull(outcome)) {
            /*
             * Directly
             */
            return Future.succeededFuture(envelop);
        } else {
            /*
             * JobOutcome processing here
             * Contract for vertx/mission
             */
            LOGGER.info(JobMsg.COMPONENT_SELECTED, "JobOutcome", outcome.getClass().getName());
            Ut.contract(outcome, Vertx.class, this.vertx);
            Ut.contract(outcome, Mission.class, mission);
            return outcome.afterAsync(envelop);
        }
    }

    Future<Envelop> outputAsync(final Envelop envelop, final Mission mission) {
        /*
         * Get outcome address
         */
        final String address = Element.address(mission, false);
        if (Ut.isNil(address)) {
            /*
             * Directly
             */
            return Future.succeededFuture(envelop);
        } else {
            /*
             * Event bus provide output and then it will execute
             */
            LOGGER.info(JobMsg.ADDRESS_EVENT_BUS, "Outcome", address);
            final Future<Envelop> output = Future.future();
            final EventBus eventBus = this.vertx.eventBus();
            eventBus.<Envelop>send(address, envelop, handler -> {
                if (handler.succeeded()) {
                    output.complete(handler.result().body());
                } else {
                    output.complete(Envelop.failure(handler.cause()));
                }
            });
            return output;
        }
    }
}
