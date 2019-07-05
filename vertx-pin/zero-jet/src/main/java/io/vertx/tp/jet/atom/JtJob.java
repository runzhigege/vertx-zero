package io.vertx.tp.jet.atom;

import cn.vertxup.jet.tables.pojos.IJob;
import cn.vertxup.jet.tables.pojos.IService;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.eon.em.JobType;
import io.vertx.zero.eon.Strings;
import io.zero.epic.Ut;

import java.util.Objects;

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
         * IJob -> Mission：name
         * The job unique identifier: namespace + name
         * The splitted character is `$$`
         */
        mission.setName(this.job.getNamespace() +
                Strings.DOLLER + Strings.DOLLER + this.job.getName());
        mission.setType(Ut.toEnum(this.job::getType, JobType.class, JobType.ONCE));
        mission.setCode(this.job.getNamespace() +
                Strings.DOLLER + Strings.DOLLER + this.job.getCode());
        /*
         * Basic information
         */
        mission.setComment(this.job.getComment());
        mission.setAdditional(Ut.toJObject(this.job.getAdditional()));
        /*
         * Set job configuration of current environment. bind to `service`
         */
        final JsonObject metadata = Ut.toJObject(this.job.getMetadata());
        final JsonObject service = Ut.serializeJson(this.service);
        mission.setMetadata(new JsonObject()
                /* Bind configuration */
                .put(KeField.METADATA, metadata)
                .mergeIn(service.copy())
        );
        /*
         * Instant / duration
         */
        if (Objects.nonNull(this.job.getRunAt())) {
            mission.setInstant(Ut.parse(this.job.getRunAt()).toInstant());
        }
        /*
         * Proxy & @On @Off without @Job method
         */
        mission.setDuration(this.job.getDuration());
        /*
         * Income / incomeAddress
         */
        if (Objects.nonNull(this.job.getIncomeComponent())) {
            mission.setIncome(Ut.clazz(this.job.getIncomeComponent()));
        }
        mission.setIncomeAddress(this.job.getIncomeAddress());
        /*
         * Outcome / outcomeAddress
         */
        if (Objects.nonNull(this.job.getOutcomeComponent())) {
            mission.setOutcome(Ut.clazz(this.job.getOutcomeComponent()));
        }
        mission.setOutcomeAddress(this.job.getOutcomeAddress());
        return this.mount(mission);
    }

    private Mission mount(final Mission mission) {
        final String proxyStr = this.job.getProxy();
        final Class clazz = Ut.clazz(proxyStr);
        if (Objects.nonNull(clazz)) {
            /*
             * Object initialized
             */
            return mission.connect(clazz);
        } else {
            return mission;
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JtJob)) {
            return false;
        }
        final JtJob jtJob = (JtJob) o;
        return this.key.equals(jtJob.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.key);
    }
}
