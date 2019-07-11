package io.vertx.up.job.phase;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.up.commune.Envelop;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.eon.Info;
import io.vertx.up.job.plugin.JobIncome;
import io.vertx.up.log.Annal;
import io.vertx.up.util.Ut;

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
        final String address = mission.getIncomeAddress();
        if (Ut.isNil(address)) {
            /*
             * Event bus did not provide any input here
             */
            Element.onceLog(mission,
                    () -> LOGGER.info(Info.PHASE_1ST_JOB, mission.getName()));

            return Future.succeededFuture(Envelop.okJson());
        } else {
            /*
             * Event bus provide input and then it will pass to @On
             */
            LOGGER.info(Info.JOB_ADDRESS_EVENT_BUS, "Income", address);
            final Future<Envelop> input = Future.future();
            final EventBus eventBus = vertx.eventBus();
            eventBus.<Envelop>consumer(address, handler -> {

                Element.onceLog(mission, () -> LOGGER.info(Info.PHASE_1ST_JOB_ASYNC, mission.getName(), address));

                final Envelop envelop = handler.body();
                if (Objects.isNull(envelop)) {
                    /*
                     * Success
                     */
                    input.complete(Envelop.ok());
                } else {
                    /*
                     * Failure
                     */
                    input.complete(envelop);
                }
            });
            return input;
        }
    }

    Future<Envelop> incomeAsync(final Envelop envelop, final Mission mission) {
        if (envelop.valid()) {
            /*
             * Get JobIncome
             */
            final JobIncome income = Element.income(mission);
            if (Objects.isNull(income)) {
                /*
                 * Directly
                 */
                Element.onceLog(mission,
                        () -> LOGGER.info(Info.PHASE_2ND_JOB, mission.getName()));

                return Future.succeededFuture(envelop);
            } else {
                /*
                 * JobIncome processing here
                 * Contract for vertx/mission
                 */
                LOGGER.info(Info.JOB_COMPONENT_SELECTED, "JobIncome", income.getClass().getName());
                /*
                 * JobIncome must define
                 * - Vertx reference
                 * - Mission reference
                 */
                Ut.contract(income, Vertx.class, vertx);
                Ut.contract(income, Mission.class, mission);
                Element.onceLog(mission,
                        () -> LOGGER.info(Info.PHASE_2ND_JOB_ASYNC, mission.getName(), income.getClass().getName()));

                return income.beforeAsync(envelop);
            }
        } else {
            Element.onceLog(mission,
                    () -> LOGGER.info(Info.PHASE_ERROR, mission.getName(),
                            envelop.error().getClass().getName()));

            return envelop.toFuture();
        }
    }
}
