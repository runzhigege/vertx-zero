package io.vertx.tp.jet.atom;

import cn.vertxup.jet.tables.pojos.IApi;
import cn.vertxup.jet.tables.pojos.IService;
import io.vertx.tp.jet.cv.JtComponent;
import io.vertx.tp.jet.cv.em.WorkerType;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

/*
 * Uri ( API + SERVICE )
 */
public class JtUri {

    private final transient IApi api;
    private final transient IService service;
    private final transient String key;

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
