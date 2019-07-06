package io.vertx.tp.jet.uca.micro;

import io.vertx.core.Future;
import io.vertx.tp.jet.monitor.JtMonitor;
import io.vertx.tp.optic.jet.JtConsumer;
import io.vertx.up.atom.Envelop;
import io.vertx.up.commune.Commercial;

/**
 * 「Consumer」
 * Default consumer to consume request, complex code logical
 */
public class JtAiakos implements JtConsumer {

    private transient final JtMonitor monitor = JtMonitor.create(this.getClass());

    /*
     * Data example
     */
    @Override
    public Future<Envelop> async(final Envelop envelop, final Commercial commercial) {
        System.out.println(commercial.toJson().encodePrettily());
        return JtPandora.async(envelop, commercial, this.monitor);
    }
}
