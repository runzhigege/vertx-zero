package io.vertx.tp.jet.uca.tunnel;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.error._501ChannelErrorException;
import io.vertx.tp.jet.monitor.JtMonitor;
import io.vertx.tp.optic.jet.JtChannel;
import io.vertx.tp.optic.jet.JtComponent;
import io.vertx.up.annotations.Contract;
import io.vertx.up.atom.Envelop;
import io.vertx.up.commune.ActIn;
import io.vertx.up.commune.ActOut;
import io.vertx.up.commune.Api;
import io.vertx.up.commune.Record;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;

import java.util.Objects;

/*
 * Abstract channel
 */
public abstract class AbstractChannel implements JtChannel {

    private final transient JtMonitor monitor = JtMonitor.create(this.getClass());
    /* This field will be injected by zero directly from backend */
    @Contract
    private transient Api apiRef;

    @Override
    public Future<Envelop> transferAsync(final Envelop envelop) {
        /*
         * Build record and init
         */
        final Class<?> recordClass = this.apiRef.recordComponent();
        this.monitor.recordHit(recordClass);
        /*
         * Data object, could not be singleton
         *  */
        final Record record = Ut.instance(recordClass);

        /*
         * First step for channel
         * Initialize the `ActIn` object and reference
         */
        final ActIn request = new ActIn(envelop);
        request.connect(record);
        /*
         * Build component and init
         */
        final Class<?> componentClass = this.apiRef.businessComponent();
        if (Objects.isNull(componentClass)) {
            /*
             * null class of component
             */
            return Future.failedFuture(new _501ChannelErrorException(this.getClass(), null));
        } else {
            this.monitor.componentHit(componentClass);

            /* Singleton because it's not data object */
            final JtComponent component = Ut.singleton(componentClass);
            if (Objects.nonNull(component)) {
                /*
                 * Initialized first and then
                 */
                return this.initAsync(component, request)
                        /*
                         * options injection
                         */
                        .compose(child -> Ut.contractAsync(component, JsonObject.class, this.apiRef.options()))
                        /*
                         * Children initialized
                         */
                        .compose(initialized -> component.transferAsync(request))
                        /*
                         * Response here for future custom
                         */
                        .compose(ActOut::async);
            } else {
                /*
                 * singleton singleton error
                 */
                return Future.failedFuture(new _501ChannelErrorException(this.getClass(), componentClass.getName()));
            }
        }
    }

    /*
     * Initialize component
     */
    public abstract Future<Boolean> initAsync(JtComponent component, ActIn request);

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }

    protected Api getApi() {
        return this.apiRef;
    }
}
