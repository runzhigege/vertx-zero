package io.vertx.up.job.phase;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.job.cv.JobMsg;
import io.vertx.up.job.plugin.JobIncome;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;

import java.util.Objects;

class Input {

    private static final Annal LOGGER = Annal.get(Input.class);

    private transient final Vertx vertx;

    Input(final Vertx vertx) {
        this.vertx = vertx;
    }

    Future<Envelop> inputAsync(final Mission mission) {
        /*
         * Get income address
         * */
        final String address = Element.address(mission, true);
        if (Ut.isNil(address)) {
            /*
             * Event bus did not provide any input here
             */
            return Future.succeededFuture(Envelop.ok());
        } else {
            /*
             * Event bus provide input and then it will pass to @On
             */
            LOGGER.info(JobMsg.ADDRESS_EVENT_BUS, "Income", address);
            final Future<Envelop> input = Future.future();
            final EventBus eventBus = this.vertx.eventBus();
            eventBus.<Envelop>consumer(address, handler -> {
                final Envelop envelop = handler.body();
                if (Objects.isNull(envelop)) {
                    input.complete(Envelop.ok());
                } else {
                    input.complete(envelop);
                }
            });
            return input;
        }
    }

    Future<Envelop> incomeAsync(final Envelop envelop, final Mission mission) {
        /*
         * Get JobIncome
         */
        final JobIncome income = Element.income(mission);
        if (Objects.isNull(income)) {
            /*
             * Directly
             */
            return Future.succeededFuture(envelop);
        } else {
            /*
             * JobIncome processing here
             * Contract for vertx/mission
             */
            LOGGER.info(JobMsg.COMPONENT_SELECTED, "JobIncome", income.getClass().getName());
            Ut.contract(income, Vertx.class, this.vertx);
            Ut.contract(income, Mission.class, mission);
            return income.beforeAsync(envelop);
        }
    }
}
