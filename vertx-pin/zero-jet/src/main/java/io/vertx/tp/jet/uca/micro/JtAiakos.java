package io.vertx.tp.jet.uca.micro;

import io.vertx.core.Future;
import io.vertx.tp.jet.monitor.JtMonitor;
import io.vertx.tp.optic.jet.JtChannel;
import io.vertx.tp.optic.jet.JtConsumer;
import io.vertx.up.atom.Envelop;
import io.vertx.up.commune.Commercial;
import io.zero.epic.Ut;

/*
 * 「Consumer」
 * Default consumer to consume request, complex code logical
 */
public class JtAiakos implements JtConsumer {

    private transient final JtMonitor monitor = JtMonitor.create(this.getClass());

    @Override
    public Future<Envelop> async(final Envelop envelop, final Commercial commercial) {
        /* Channel class for current consumer thread */
        final Class<?> channelClass = commercial.channelComponent();

        /* Initialization for channel */
        final JtChannel channel = Ut.instance(channelClass);

        /* Find the target Field */
        Ut.contract(channel, Commercial.class, commercial);
        this.monitor.channelHit(channelClass);

        /* Transfer the `Envelop` request data into channel and let channel do next works */
        return channel.transferAsync(envelop);
    }
}
