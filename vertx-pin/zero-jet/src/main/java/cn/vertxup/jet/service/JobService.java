package cn.vertxup.jet.service;

import cn.vertxup.jet.domain.tables.daos.IJobDao;
import cn.vertxup.jet.domain.tables.pojos.IJob;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.refine.Jt;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

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
                    final Set<String> codes = jobs.stream()
                            .filter(Objects::nonNull)
                            /*
                             * Job name calculation for appending namespace
                             */
                            .map(Jt::jobCode)
                            .collect(Collectors.toSet());
                    Jt.infoWeb(LOGGER, "Job fetched from database: {0}", codes.size());
                    return JobKit.fetchMission(codes);
                });
    }

    @Override
    public Future<JsonObject> fetchByKey(final String key) {
        return Ux.Jooq.on(IJobDao.class)
                .<IJob>findByIdAsync(key)
                /*
                 * 1) Supplier here for `JsonObject` generated
                 * 2) Mission conversation here to JsonObject directly
                 */
                .compose(Ut.applyNil(JsonObject::new,
                        job -> JobKit.fetchMission(Jt.jobCode(job))));
    }

    @Override
    public Future<JsonObject> update(final String key, final JsonObject data) {
        return Future.succeededFuture(new JsonObject());
    }
}
