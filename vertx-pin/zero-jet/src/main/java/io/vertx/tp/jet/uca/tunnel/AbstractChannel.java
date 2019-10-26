package io.vertx.tp.jet.uca.tunnel;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.error._501ChannelErrorException;
import io.vertx.tp.jet.monitor.JtMonitor;
import io.vertx.tp.jet.refine.Jt;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.optic.jet.JtChannel;
import io.vertx.tp.optic.jet.JtComponent;
import io.vertx.up.annotations.Contract;
import io.vertx.up.atom.Diode;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.commune.*;
import io.vertx.up.commune.config.Adminicle;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.Objects;

/**
 * Abstract channel
 * reference matrix
 * Name                 Database            Integration         Mission
 * AdaptorChannel       Yes                 No                  No
 * ConnectorChannel     Yes                 Yes                 No
 * DirectorChannel      Yes                 No                  Yes
 * ActorChannel         Yes                 Yes                 Yes
 * <p>
 * For above support list, here are some rules:
 * 1) Request - Response MODE, Client send request
 * 2) Publish - Subscribe MODE, Server send request
 * <p>
 * For common usage, it should use AdaptorChannel instead of other three types; If you want to send
 * request to third part interface ( API ), you can use ConnectorChannel instead of others.
 * <p>
 * The left two: ActorChannel & DirectorChannel are Background task in zero ( Job Support ), the
 * difference between them is that whether the channel support Integration.
 * <p>
 * The full feature of channel should be : ActorChannel
 */
public abstract class AbstractChannel implements JtChannel {

    private final transient JtMonitor monitor = JtMonitor.create(this.getClass());
    /* This field will be injected by zero directly from backend */
    @Contract
    private transient Commercial commercial;
    @Contract
    private transient Mission mission;

    @Override
    public Future<Envelop> transferAsync(final Envelop envelop) {
        /*
         * Build record and init
         */
        final Class<?> recordClass = this.commercial.recordComponent();
        /*
         * Data object, could not be singleton
         *  */
        final Record definition = Ut.instance(recordClass);
        /*
         * First step for channel
         * Initialize the `ActIn` object and reference
         */
        final ActIn request = new ActIn(envelop);
        request.connect(definition);
        /*
         * Build component and init
         */
        final Class<?> componentClass = this.commercial.businessComponent();
        if (Objects.isNull(componentClass)) {
            /*
             * null class of component
             */
            return Future.failedFuture(new _501ChannelErrorException(this.getClass(), null));
        } else {
            /* Singleton because it's not data object */
            final JtComponent component = Ut.singleton(componentClass);
            if (Objects.nonNull(component)) {
                this.monitor.componentHit(componentClass, recordClass);
                /*
                 * Initialized first and then
                 */
                Ux.debug();
                /*
                 * Options without `mapping` here
                 */
                return this.initAsync(component, request)
                        /*
                         * Debug for trace errors
                         */
                        .otherwise(error -> Ux.debug(error, () -> Boolean.FALSE))
                        /*
                         * 1) JsonObject: options ( without `mapping` )
                         * 2)
                         */
                        .compose(child -> this.contractReference(component))
                        /*
                         * Children initialized
                         */
                        .compose(initialized -> component.transferAsync(request))
                        /*
                         * Response here for future custom
                         */
                        .compose(ActOut::async)
                        /*
                         * Error throw out
                         */
                        .otherwise(error -> {
                            error.printStackTrace();
                            return Envelop.failure(error);
                        });
            } else {
                /*
                 * singleton singleton error
                 */
                return Future.failedFuture(new _501ChannelErrorException(this.getClass(), componentClass.getName()));
            }
        }
    }

    private <V> Future<Boolean> contractReference(final JtComponent component) {
        final Commercial commercial = this.commercial;
        if (Objects.nonNull(commercial)) {
            /*
             * JsonObject options inject ( without `mapping` node for Diode )
             */
            final JsonObject options = commercial.options();
            if (Ut.notNil(options)) {
                final JsonObject injectOpt = options.copy();
                injectOpt.remove(KeField.MAPPING);
                Ut.contract(component, JsonObject.class, injectOpt);
            }
            /*
             * Diode: mapping, Adminicle: reference
             */
            Ut.contract(component, Diode.class, commercial.mapping());
            Ut.contract(component, Adminicle.class, commercial.adminicle());
            return Future.succeededFuture(Boolean.TRUE);
        } else {
            /*
             *
             */
            Jt.infoWeb(this.getLogger(), "Commercial reference is null");
            return Future.succeededFuture(Boolean.TRUE);
        }
    }

    /*
     * Initialize component
     */
    public abstract Future<Boolean> initAsync(JtComponent component, ActIn request);

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }

    protected Commercial getCommercial() {
        return this.commercial;
    }

    protected Mission getMission() {
        return this.mission;
    }
}
