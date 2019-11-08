package io.vertx.tp.jet.uca.tunnel;

import io.vertx.core.Future;
import io.vertx.tp.error._501ChannelErrorException;
import io.vertx.tp.jet.monitor.JtMonitor;
import io.vertx.tp.optic.jet.JtChannel;
import io.vertx.tp.optic.jet.JtComponent;
import io.vertx.up.annotations.Contract;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.commune.ActIn;
import io.vertx.up.commune.Commercial;
import io.vertx.up.commune.Envelop;
import io.vertx.up.commune.Record;
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
        if (Objects.nonNull(this.commercial.mapping())) {
            request.bind(this.commercial.mapping());
        }
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
                         * 1. `Dict` calculation for current channel
                         * Children initialized here, for `dict` calculation
                         * 1) Fetch dict that configured in current channel
                         * 2) Put dict to `ActIn` object for future usage
                         */
                        .compose(nil -> Anagogic.dictAsync(this.commercial))
                        .compose(dict -> Ux.future(request.bind(dict)))
                        /*
                         * 1) JsonObject: options ( without `mapping` )
                         */
                        .compose(nil -> Anagogic.componentAsync(component, this.commercial))
                        /*
                         * Children initialized
                         */
                        .compose(initialized -> component.transferAsync(request))
                        /*
                         * Response here for future custom
                         */
                        .compose(actOut -> Anagogic.complete(actOut, this.commercial.mapping(), envelop));
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

    protected Commercial getCommercial() {
        return this.commercial;
    }

    protected Mission getMission() {
        return this.mission;
    }
}
