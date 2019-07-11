package io.vertx.tp.jet.atom;

import cn.vertxup.jet.domain.tables.pojos.IJob;
import cn.vertxup.jet.domain.tables.pojos.IService;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.cv.JtKey;
import io.vertx.tp.jet.refine.Jt;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.eon.em.JobType;
import io.vertx.zero.eon.Strings;
import io.zero.epic.Ut;

import java.util.Objects;

/*
 * Job ( JOB + SERVICE )
 */
public class JtJob extends JtCommercial {

    private transient IJob job;
    private transient String key;

    /*
     * For deserialization
     */
    public JtJob() {
    }

    public JtJob(final IJob job, final IService service) {
        super(service);
        this.job = job;
        /* */
        this.key = job.getKey();
    }
    // ----------- override

    @Override
    public JsonObject options() {
        return Jt.toOptions(this.getApp(), this.job, this.service());
    }

    @Override
    public String key() {
        return this.key;
    }

    @Override
    public JsonObject toJson() {
        final JsonObject data = super.toJson();
        /* key data */
        data.put(JtKey.Delivery.JOB, (JsonObject) Ut.serializeJson(this.job));
        return data;
    }

    @Override
    public void fromJson(final JsonObject data) {
        super.fromJson(data);
        /*
         * Basic attributes
         */
        this.key = data.getString(JtKey.Delivery.KEY);
        /*
         * job
         */
        this.job = Ut.deserialize(data.getJsonObject(JtKey.Delivery.JOB), IJob.class);
    }

    // ----------- job & service

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
        mission.setMetadata(this.toJson().copy());
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
}
