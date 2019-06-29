package io.vertx.tp.jet.uca.tunnel;

import io.vertx.core.Future;
import io.vertx.tp.jet.monitor.JtMonitor;
import io.vertx.tp.optic.jet.JtChannel;
import io.vertx.tp.optic.jet.JtComponent;
import io.vertx.up.atom.Envelop;
import io.vertx.up.commune.ActRequest;
import io.vertx.up.commune.ActResponse;
import io.vertx.up.commune.Api;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

/*
 * Abstract channel
 */
public abstract class AbstractChannel implements JtChannel {

    private final transient JtMonitor monitor = JtMonitor.create(this.getClass());
    private transient Api apiRef;

    @Override
    public JtChannel bind(final Api apiRef) {
        this.apiRef = apiRef;
        return this;
    }

    @Override
    public Future<Envelop> transferAsync(final Envelop envelop) {
        /*
         * First step for channel
         * Initialize the `ActRequest` object and reference
         */
        final ActRequest request = new ActRequest(envelop);
        /*
         * Build component
         */
        final Class<?> componentClass = this.apiRef.businessComponent();
        this.monitor.componentHit(componentClass);
        /*
         * Init JtComponent
         */
        final JtComponent component = Ut.singleton(componentClass);
        return Fn.getJvm(() -> component.transferAsync(request)
                .compose(ActResponse::async), component);
    }

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }
}
