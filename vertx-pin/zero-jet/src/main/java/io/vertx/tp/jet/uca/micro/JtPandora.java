package io.vertx.tp.jet.uca.micro;

import io.vertx.core.Future;
import io.vertx.tp.jet.monitor.JtMonitor;
import io.vertx.tp.optic.jet.JtChannel;
import io.vertx.up.atom.Envelop;
import io.vertx.up.commune.Commercial;
import io.zero.epic.Ut;

/*
 * Channel uniform interface call
 */
class JtPandora {

    static Future<Envelop> async(final Envelop envelop, final Commercial commercial,
                                 final JtMonitor monitor) {
        /* Channel class for current consumer thread */
        final Class<?> channelClass = commercial.channelComponent();

        /* Initialization for channel */
        final JtChannel channel = Ut.instance(channelClass);

        /* Find the target Field */
        Ut.contract(channel, Commercial.class, commercial);
        monitor.channelHit(channelClass);
        /* Transfer the `Envelop` request data into channel and let channel do next works */
        return channel.transferAsync(envelop);
    }
}
