package io.vertx.up.uca.job.phase;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.commune.Envelop;
import io.vertx.up.fn.Fn;

/*
 * Major phase for code logical here
 */
public class Phase {

    private transient Vertx vertx;
    private transient Mission mission;
    /* Phase */
    private transient Input input;
    private transient RunOn runOn;
    private transient OutPut output;

    private Phase() {
    }

    public static Phase start(final String name) {
        return Fn.pool(Pool.PHASES, name, Phase::new);
    }

    public Phase bind(final Vertx vertx) {
        this.vertx = vertx;
        input = new Input(this.vertx);
        runOn = new RunOn(this.vertx);
        output = new OutPut(this.vertx);
        return this;
    }

    public Phase bind(final Mission mission) {
        this.mission = mission;
        return this;
    }

    /*
     * 1. Event Bus with Input
     */
    public Future<Envelop> inputAsync(final Mission mission) {
        return input.inputAsync(mission);
    }

    /*
     * 2. JobIncome here
     */
    public Future<Envelop> incomeAsync(final Envelop envelop) {
        return input.incomeAsync(envelop, mission);
    }

    /*
     * 3. Major code logical
     */
    public Future<Envelop> invokeAsync(final Envelop envelop) {
        return runOn.invoke(envelop, mission);
    }

    /*
     * 4. JobOutcome here
     */
    public Future<Envelop> outcomeAsync(final Envelop envelop) {
        return output.outcomeAsync(envelop, mission);
    }

    /*
     * 5. Output here ( Send message )
     * This method existing because you want to set some call back because
     * Output Address will be defined !
     */
    public Future<Envelop> outputAsync(final Envelop envelop) {
        return output.outputAsync(envelop, mission);
    }

    /*
     * 6. Output callback ( Consume message )
     * 1) - Write log
     * 2) - Set some status
     * 3) - Do some checking or job status changing.
     */
    public Future<Envelop> callbackAsync(final Envelop envelop) {
        return runOn.callback(envelop, mission);
    }
}
