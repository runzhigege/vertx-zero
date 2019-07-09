package io.vertx.up.job.store;

import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.eon.Info;
import io.vertx.up.log.Annal;
import io.vertx.tp.plugin.job.JobPool;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;
import io.zero.epic.Ut;

public class JobPin {

    private static final Annal LOGGER = Annal.get(JobPin.class);
    private static final Node<JsonObject> VISITOR = Ut.singleton(ZeroUniform.class);
    private static final String JOB = "job";
    private static transient JobConfig CONFIG;
    private static transient JobStore STORE;

    static {
        final JsonObject config = VISITOR.read();
        if (config.containsKey(JOB)) {
            final JsonObject job = config.getJsonObject(JOB);
            if (!Ut.isNil(job)) {
                /* Extension job-store */
                CONFIG = Ut.deserialize(job, JobConfig.class);
            } else {
                CONFIG = new JobConfig();
            }
            LOGGER.info(Info.JOB_CONFIG, CONFIG);
        }
    }

    public static JobConfig getConfig() {
        return CONFIG;
    }

    public static JobStore getStore() {
        /*
         * Singleton for UnityStore ( package scope )
         */
        synchronized (JobStore.class) {
            if (null == STORE) {
                STORE = new UnityStore();
            }
            return STORE;
        }
    }

    /*
     * Whether the mission is in JobPool
     * Only job pool could be accessed by JobClient in future here.
     */
    public static boolean isIn(final Mission mission) {
        return JobPool.valid(mission);
    }
}
