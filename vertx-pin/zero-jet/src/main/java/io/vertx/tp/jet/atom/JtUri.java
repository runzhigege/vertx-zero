package io.vertx.tp.jet.atom;

import cn.vertxup.jet.tables.pojos.IApi;
import cn.vertxup.jet.tables.pojos.IService;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.cv.JtComponent;
import io.vertx.tp.jet.cv.JtKey;
import io.vertx.tp.jet.cv.em.ParamMode;
import io.vertx.tp.jet.cv.em.WorkerType;
import io.vertx.tp.jet.refine.Jt;
import io.vertx.tp.optic.environment.Ambient;
import io.vertx.up.eon.Orders;
import io.vertx.up.eon.ZJson;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import javax.ws.rs.core.MediaType;
import java.util.Objects;
import java.util.Set;

/*
 * Uri ( API + SERVICE )
 */
public class JtUri implements ZJson {

    /*
     * Worker
     */
    private transient JtWorker worker;
    private transient IApi api;
    private transient IService service;
    private transient String key;
    /*
     * App / Config
     */
    private transient JtApp app;
    private transient Integer order;
    private transient JtConfig config;

    public JtUri(final IApi api, final IService service) {
        this.api = api;
        this.service = service;
        /* Api Key */
        this.key = api.getKey();

        /* Default Component Value */
        this.initialize(api);
        /*
         * JtWorker instance here for future use
         */
        this.worker = Jt.toWorker(api);
    }

    public JtUri bind(final Integer order) {
        this.order = order;
        return this;
    }

    public JtUri bind(final JtConfig config) {
        this.config = config;
        return this;
    }

    public JtUri bind(final String appId) {
        this.app = Ambient.getApp(appId);
        return this;
    }

    // ----------- Uri Hub
    /*
     * Method in JtUri
     * Uri, Order, Path
     */
    public Integer order() {
        return Objects.nonNull(this.order) ? this.order : Orders.DYNAMIC;
    }

    public String path() {
        return Jt.toPath(this.app::getRoute, this.api::getUri, this.api.getSecure(), this.config);
    }

    public HttpMethod method() {
        return Ut.toEnum(this.api::getMethod, HttpMethod.class, HttpMethod.GET);
    }

    /*
     * Mime
     * Consumes, Produces
     */
    public Set<String> produces() {
        return Jt.toMimeString(this.api::getProduces);
    }

    public Set<MediaType> producesMime() {
        return Jt.toMime(this.api::getProduces);
    }

    public Set<String> consumes() {
        return Jt.toMimeString(this.api::getConsumes);
    }

    // ------------ param
    /*
     * Param mode, default BODY
     */
    public ParamMode paramMode() {
        return Ut.toEnum(this.api::getParamMode, ParamMode.class, ParamMode.BODY);
    }

    public Set<String> paramRequired() {
        return Jt.toSet(this.api::getParamRequired);
    }

    public Set<String> paramContained() {
        return Jt.toSet(this.api::getParamContained);
    }

    // ------------- api & service
    public String key() {
        return this.key;
    }

    public String identifier() {
        return this.service.getIdentifier();
    }

    // ------------- worker
    public JtWorker worker() {
        return this.worker;
    }

    private void initialize(final IApi api) {
        /*
         * Set default value in I_API related to worker
         * workerType
         * workerAddress
         * workerConsumer
         * workerClass
         * workerJs
         */
        Fn.safeSemi(Ut.isNil(api.getWorkerClass()),
                () -> api.setWorkerClass(JtComponent.COMPONENT_DEFAULT_WORKER.getName()));
        Fn.safeSemi(Ut.isNil(api.getWorkerAddress()),
                () -> api.setWorkerAddress(JtComponent.EVENT_ADDRESS));
        Fn.safeSemi(Ut.isNil(api.getWorkerConsumer()),
                () -> api.setWorkerConsumer(JtComponent.COMPONENT_DEFAULT_CONSUMER.getName()));
        Fn.safeSemi(Ut.isNil(api.getWorkerType()),
                () -> api.setWorkerType(WorkerType.STD.name()));
    }

    /*
     * Api Key as id of current Uri
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JtUri)) {
            return false;
        }
        final JtUri jtUri = (JtUri) o;
        return this.key.equals(jtUri.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.key);
    }

    @Override
    public JsonObject toJson() {
        final JsonObject data = new JsonObject();
        data.put(JtKey.Delivery.KEY, this.key);
        data.put(JtKey.Delivery.ORDER, this.order);

        data.put(JtKey.Delivery.API, (JsonObject) Ut.serializeJson(this.api));
        data.put(JtKey.Delivery.SERVICE, (JsonObject) Ut.serializeJson(this.service));
        data.put(JtKey.Delivery.CONFIG, (JsonObject) Ut.serializeJson(this.config));
        /* Connect App Id */
        data.put(JtKey.Delivery.APP_ID, this.app.getAppId());
        return data;
    }

    @Override
    public void fromJson(final JsonObject data) {
        /*
         * Basic attributes
         */
        this.key = data.getString(JtKey.Delivery.KEY);
        this.order = data.getInteger(JtKey.Delivery.ORDER);

        /*
         * api, service, config
         */
        this.api = Ut.deserialize(data.getJsonObject(JtKey.Delivery.API), IApi.class);
        this.service = Ut.deserialize(data.getJsonObject(JtKey.Delivery.SERVICE), IService.class);
        this.config = Ut.deserialize(data.getJsonObject(JtKey.Delivery.CONFIG), JtConfig.class);

        /*
         * app, worker
         */
        this.worker = Jt.toWorker(this.api);
        final String appId = data.getString(JtKey.Delivery.APP_ID);
        this.app = Ambient.getApp(appId);
    }
}
