package io.vertx.tp.jet.monitor;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.cv.JtMsg;
import io.vertx.tp.jet.cv.em.ParamMode;
import io.vertx.tp.jet.refine.Jt;
import io.vertx.tp.jet.uca.param.JtIngest;
import io.vertx.up.log.Annal;
import io.vertx.up.web.Runner;
import io.zero.epic.fn.Fn;

/*
 * The monitor of workflow here.
 */
public class JtMonitor {

    private transient final Annal logger;
    private transient final String name;
    private transient final JtAtomic atomic = new JtAtomic();

    private JtMonitor(final Class<?> clazz) {
        this.name = clazz.getName();
        this.logger = Annal.get(clazz);
    }

    public static JtMonitor create(final Class<?> clazz) {
        return Fn.pool(Pool.MONITORS, clazz, () -> new JtMonitor(clazz));
    }

    // ---------------- Agent
    public void agentConfig(final JsonObject config) {
        this.atomic.start(this.logger, config);
    }

    // ---------------- Worker
    public void workerStart() {
        this.atomic.worker(this.logger);
    }

    public void workerFailure() {
        this.atomic.workerFailure(this.logger);
    }

    // ---------------- Ingest
    public void ingestParam(final ParamMode mode, final JtIngest ingest) {
        Runner.run(() -> Jt.infoWeb(this.logger, JtMsg.PARAM_INGEST,
                mode, ingest.getClass().getCanonicalName(), String.valueOf(ingest.hashCode())), "jet-ingest-param");
    }

    public void ingestFinal(final JsonObject data) {
        Runner.run(() -> Jt.infoWeb(this.logger, JtMsg.PARAM_FINAL,
                data.encode()), "jet-ingest-final");
    }

    // ---------------- Aim
    public void aimEngine(final HttpMethod method, final String path, final JsonObject data) {
        Runner.run(() -> Jt.infoWeb(this.logger, JtMsg.WEB_ENGINE,
                method, path, data.encode()), "jet-aim-engine");
    }

    public void aimSend(final JsonObject data, final String address) {
        Runner.run(() -> Jt.infoWeb(this.logger, JtMsg.WEB_SEND,
                data.encode(), address), "jet-aim-send");
    }

}
