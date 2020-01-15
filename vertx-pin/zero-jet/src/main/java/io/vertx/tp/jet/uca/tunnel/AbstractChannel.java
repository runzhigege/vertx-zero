package io.vertx.tp.jet.uca.tunnel;

import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonArray;
import io.vertx.tp.error._501ChannelErrorException;
import io.vertx.tp.jet.atom.JtApp;
import io.vertx.tp.jet.monitor.JtMonitor;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.optic.environment.Ambient;
import io.vertx.tp.optic.jet.JtChannel;
import io.vertx.tp.optic.jet.JtComponent;
import io.vertx.up.annotations.Contract;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.commune.*;
import io.vertx.up.commune.config.Dict;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

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

    @Contract
    private transient ConcurrentMap<String, JsonArray> dictionary;

    @Override
    public Future<Envelop> transferAsync(final Envelop envelop) {
        /*
         * Build record and init
         */
        final Class<?> recordClass = this.commercial.recordComponent();
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
            return this.createRequest(envelop, recordClass).compose(request -> {
                /*
                 * Create new component here
                 * It means that Channel/Component must contains new object
                 * Container will create new Channel - Component to process request
                 * Instead of singleton here.
                 *  */
                final JtComponent component = Ut.instance(componentClass);
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
                             * Contract here
                             * 1) Definition in current channel
                             * 2) Data came from request ( XHeader )
                             */
                            .compose(nil -> Anagogic.componentAsync(component, this.commercial))
                            .compose(initialized -> Anagogic.componentAsync(component, envelop))
                            /*
                             * Children initialized
                             */
                            .compose(initialized -> component.transferAsync(request))
                            /*
                             * Response here for future custom
                             */
                            .compose(actOut -> this.createResponse(actOut, envelop))
                            /*
                             * Otherwise;
                             */
                            .otherwise(Ux.otherwise());
                } else {
                    /*
                     * singleton singleton error
                     */
                    return Future.failedFuture(new _501ChannelErrorException(this.getClass(), componentClass.getName()));
                }
            });
        }
    }

    private Future<Envelop> createResponse(final ActOut actOut, final Envelop envelop) {
        return Ux.future(actOut.envelop(this.commercial.mapping()).from(envelop));
    }

    /*
     * Switcher `dictionary` here for usage
     * 1) When `Job`, assist data may be initialized before.
     * 2) When `Api`, here will initialize assist data.
     * 3) Finally the data will bind to request
     */
    private Future<ActIn> createRequest(final Envelop envelop, final Class<?> recordClass) {
        return this.createDict().compose(dict -> {
            /*
             * Data object, could not be singleton
             *  */
            final Record definition = Ut.instance(recordClass);
            /*
             * First step for channel
             * Initialize the `ActIn` object and reference
             */
            final ActIn request = new ActIn(envelop);
            request.bind(this.commercial.mapping());
            request.bind(dict).connect(definition);
            return Ux.future(request);
        });
    }

    private Future<ConcurrentMap<String, JsonArray>> createDict() {
        if (Objects.isNull(this.dictionary)) {
            /*
             * Params here for different situations
             */
            final MultiMap paramMap = MultiMap.caseInsensitiveMultiMap();
            paramMap.add(KeField.IDENTIFIER, this.commercial.identifier());
            final JtApp app = Ambient.getApp(this.commercial.app());
            if (Objects.nonNull(app)) {
                paramMap.add(KeField.SIGMA, app.getSigma());
                paramMap.add(KeField.APP_ID, app.getAppId());
            }
            /*
             * Dict configuration
             */
            final Dict dict = this.commercial.dict();
            return Ux.dictCalc(dict, paramMap).compose(dictionary -> {
                /*
                 * Bind dictionary to current dictionary reference
                 */
                this.dictionary = dictionary;
                return Ux.future(this.dictionary);
            });
        } else {
            return Ux.future(this.dictionary);
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
