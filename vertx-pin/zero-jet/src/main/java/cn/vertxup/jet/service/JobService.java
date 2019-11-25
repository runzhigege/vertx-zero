package cn.vertxup.jet.service;

import cn.vertxup.jet.domain.tables.daos.IJobDao;
import cn.vertxup.jet.domain.tables.pojos.IJob;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.refine.Jt;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.plugin.job.JobPool;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class JobService implements JobStub {
    private static final Annal LOGGER = Annal.get(JobService.class);

    @Override
    public Future<JsonArray> fetchAll(final String sigma) {
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
                            .map(Jt::jobCode)
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
                            .filter(mission -> names.contains(mission.getCode()))
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
                    return Ux.future(response);
                });
    }

    @Override
    public Future<JsonObject> fetchByKey(final String key) {
        return Future.succeededFuture(new JsonObject());
    }

    @Override
    public Future<JsonObject> update(final String key, final JsonObject data) {
        return Future.succeededFuture(new JsonObject());
    }
}
