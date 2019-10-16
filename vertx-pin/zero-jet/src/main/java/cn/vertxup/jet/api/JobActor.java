package cn.vertxup.jet.api;

import cn.vertxup.jet.domain.tables.daos.IJobDao;
import cn.vertxup.jet.domain.tables.pojos.IJob;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.cv.JtAddr;
import io.vertx.tp.jet.refine.Jt;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.plugin.job.JobPool;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Queue
public class JobActor {

    private static final Annal LOGGER = Annal.get(JobActor.class);

    @Address(JtAddr.Job.START)
    public Future<Boolean> start(final String name) {
        return Ux.Job.on().start(name);
    }

    @Address(JtAddr.Job.STOP)
    public Future<Boolean> stop(final String name) {
        return Ux.Job.on().stop(name);
    }

    @Address(JtAddr.Job.RESUME)
    public Future<Boolean> resume(final String name) {
        return Ux.Job.on().resume(name);
    }

    @Address(JtAddr.Job.STATUS)
    public JsonObject status(final String name) {
        final Mission mission = JobPool.get(name);
        return Ux.toJson(mission);
    }

    @Address(JtAddr.Job.BY_SIGMA)
    public Future<JsonArray> fetch(final String sigma) {
        return Ux.Jooq.on(IJobDao.class)
                .<IJob>fetchAsync("sigma", sigma)
                .compose(jobs -> {
                    /*
                     * Result for all jobs that are belong to current sigma here.
                     */
                    final Set<String> names = jobs.stream()
                            .filter(Objects::nonNull)
                            /*
                             * Job name calculation for appending namespace
                             */
                            .map(Jt::jobName)
                            .collect(Collectors.toSet());
                    Jt.infoWeb(LOGGER, "Job fetched from database: {0}", names.size());
                    /*
                     * Read job from cache that means the jobs are valid here
                     */
                    final List<Mission> missions = JobPool.get();
                    Jt.infoWeb(LOGGER, "Valid missions: {0}", missions.size());
                    /*
                     * JsonArray process
                     */
                    final JsonArray response = Ux.toArray(missions.stream()
                            .filter(Objects::nonNull)
                            .filter(mission -> names.contains(mission.getName()))
                            .collect(Collectors.toList()));
                    Ut.itJArray(response).forEach(item -> {
                        final JsonObject metadata = item.getJsonObject(KeField.METADATA);
                        if (Ut.notNil(metadata)) {
                            final JsonObject service = metadata.getJsonObject(KeField.SERVICE);
                            if (Ut.notNil(service)) {
                                Ke.metadata(service, KeField.METADATA);
                                Ke.metadata(service, KeField.Api.CONFIG_SERVICE);
                                Ke.metadata(service, KeField.Api.CONFIG_INTEGRATION);
                                Ke.metadata(service, KeField.Api.CONFIG_DATABASE);
                                Ke.metadata(service, KeField.Api.CONFIG_CHANNEL);
                            }
                        }
                    });
                    return Ux.toFuture(response);
                });
    }
}
