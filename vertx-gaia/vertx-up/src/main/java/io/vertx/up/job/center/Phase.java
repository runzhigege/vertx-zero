package io.vertx.up.job.center;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.job.in.JobIncome;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.util.Objects;

/*
 * Major phase for code logical here
 */
class Phase {

    private transient Vertx vertx;
    private transient Mission mission;

    private Phase() {
    }

    static Phase start(final String name) {
        return Fn.pool(Pool.PHASES, name, Phase::new);
    }

    Phase bind(final Vertx vertx) {
        this.vertx = vertx;
        return this;
    }

    Phase bind(final Mission mission) {
        this.mission = mission;
        return this;
    }

    Future<Envelop> inputAsync(final Mission mission) {
        /*
         * Get income address
         * */
        final String address = Akka.address(mission, true);
        if (Ut.isNil(address)) {
            /*
             * Event bus did not provide any input here
             */
            return Future.succeededFuture(Envelop.ok());
        } else {
            /*
             * Event bus provide input and then it will pass to @On
             */
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

    Future<Envelop> incomeAsync(final Envelop envelop) {
        /*
         * Get JobIncome
         */
        final JobIncome income = Akka.income(this.mission);
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
            Ut.contract(income, Vertx.class, this.vertx);
            Ut.contract(income, Mission.class, this.mission);
            return income.beforeAsync(envelop);
        }
    }
}
