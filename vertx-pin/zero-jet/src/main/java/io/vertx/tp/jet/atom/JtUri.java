package io.vertx.tp.jet.atom;

import cn.vertxup.jet.tables.pojos.IApi;
import cn.vertxup.jet.tables.pojos.IService;
import io.vertx.core.http.HttpMethod;
import io.vertx.tp.jet.cv.JtComponent;
import io.vertx.tp.jet.cv.em.WorkerType;
import io.vertx.tp.jet.refine.Jt;
import io.vertx.tp.optic.environment.Ambient;
import io.vertx.up.eon.Orders;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.util.Objects;
import java.util.Set;

/*
 * Uri ( API + SERVICE )
 */
public class JtUri {

    private final transient IApi api;
    private final transient IService service;
    private final transient String key;

    private transient JtApp app;
    private transient Integer order;
    private transient JtConfig config;

    public JtUri(final IApi api, final IService service) {
        this.api = api;
        this.service = service;
        /* Api Key */
        this.key = api.getKey();

        /* Default Component Value */
        this.initWorker(api);
    }

    public String getKey() {
        return this.key;
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

    public Set<String> consumes() {
        return Jt.toMimeString(this.api::getConsumes);
    }

    private void initWorker(final IApi api) {
        /*
         * Set default value in I_API related to worker
         * workerType
         * workerAddress
         * workerConsumer
         * workerClass
         * workerJs
         */
        Fn.safeSemi(Ut.isNil(api.getWorkerClass()),
                () -> api.setWorkerClass(JtComponent.COMPONENT_DEFAULT_WORKER));
        Fn.safeSemi(Ut.isNil(api.getWorkerAddress()),
                () -> api.setWorkerAddress(JtComponent.EVENT_ADDRESS));
        Fn.safeSemi(Ut.isNil(api.getWorkerConsumer()),
                () -> api.setWorkerConsumer(JtComponent.COMPONENT_DEFAULT_CONSUMER));
        Fn.safeSemi(Ut.isNil(api.getWorkerType()),
                () -> api.setWorkerType(WorkerType.STD.name()));
    }
}
