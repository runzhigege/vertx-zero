package io.vertx.tp.jet.monitor;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.cv.JtMsg;
import io.vertx.tp.jet.cv.em.ParamMode;
import io.vertx.tp.jet.refine.Jt;
import io.vertx.tp.jet.uca.param.JtIngest;
import io.vertx.up.log.Annal;
import io.zero.epic.fn.Fn;

/*
 * The monitor of workflow here.
 */
public class JtMonitor {

    private transient final Annal logger;
    private transient final String name;
    private transient final JtRouter agentMonitor = new JtRouter();

    private JtMonitor(final Class<?> clazz) {
        this.name = clazz.getName();
        this.logger = Annal.get(clazz);
    }

    public static JtMonitor create(final Class<?> clazz) {
        return Fn.pool(Pool.MONITORS, clazz, () -> new JtMonitor(clazz));
    }

    // ---------------- Agent
    public void agentConfig(final JsonObject config) {
        this.agentMonitor.start(this.logger, config);
    }

    // ---------------- Ingest
    public void ingestParam(final ParamMode mode, final JtIngest ingest) {
        Jt.infoWeb(this.logger, JtMsg.PARAM_INGEST, mode, ingest.getClass().getCanonicalName(), String.valueOf(ingest.hashCode()));
    }

    public void ingestFinal(final JsonObject data) {
        Jt.infoWeb(this.logger, JtMsg.PARAM_FINAL, data.encode());
    }
}
