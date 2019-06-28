package io.vertx.tp.jet.uca.micro;

import io.vertx.core.Future;
import io.vertx.tp.jet.monitor.JtMonitor;
import io.vertx.tp.optic.jet.JtChannel;
import io.vertx.tp.optic.jet.JtConsumer;
import io.vertx.up.atom.Envelop;
import io.vertx.up.commune.ZApi;
import io.zero.epic.Ut;

/*
 * 「Consumer」
 * Default consumer to consume request, complex code logical
 */
public class JtAiakos implements JtConsumer {

    private transient final JtMonitor monitor = JtMonitor.create(this.getClass());

    @Override
    public Future<Envelop> async(final Envelop envelop, final ZApi uri) {
        /* Channel class for current consumer thread */
        final Class<?> channelClass = uri.channelComponent();
        this.monitor.channelHit(channelClass);

        /* Initialization for channel */
        final JtChannel channel = Ut.instance(channelClass);

        /* Bind reference to ZApi */
        channel.bind(uri);

        return channel.transferAsync(envelop);
    }
}
