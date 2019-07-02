package io.vertx.up.job.refine;

import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Info;
import io.vertx.up.job.store.JobStore;
import io.vertx.up.log.Annal;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;
import io.zero.epic.Ut;

public class JobPin {

    private static final Annal LOGGER = Annal.get(JobPin.class);
    private static final Node<JsonObject> VISITOR = Ut.singleton(ZeroUniform.class);
    private static final String JOB = "job";
    private static transient JobConfig CONFIG;
    private static JobStore STORE;

    static {
        final JsonObject config = VISITOR.read();
        if (config.containsKey(JOB)) {
            final JsonObject job = config.getJsonObject(JOB);
            if (!Ut.isNil(job)) {
                /* Extension job-store */
                CONFIG = Ut.deserialize(config, JobConfig.class);
                LOGGER.info(Info.JOB_CONFIG, CONFIG);
            }
        }
    }

    public static JobConfig getConfig() {
        return CONFIG;
    }
}
