package io.vertx.tp.jet.atom;

import cn.vertxup.jet.tables.pojos.IJob;
import cn.vertxup.jet.tables.pojos.IService;
import io.vertx.up.atom.worker.Mission;

public class JtJob {

    private final transient IJob job;
    private final transient IService service;
    private final transient String key;

    public JtJob(final IJob job, final IService service) {
        this.job = job;
        this.service = service;
        /* */
        this.key = job.getKey();
    }

    // ----------- job & service
    public String key() {
        return this.key;
    }

    public Mission toJob() {
        final Mission mission = new Mission();
        /*
         * IJob -> Mission
         */

        return mission;
    }
}
