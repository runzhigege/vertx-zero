package io.vertx.up.atom.worker;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ClassDeserializer;
import com.fasterxml.jackson.databind.ClassSerializer;
import com.fasterxml.jackson.databind.JsonObjectDeserializer;
import com.fasterxml.jackson.databind.JsonObjectSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Off;
import io.vertx.up.annotations.On;
import io.vertx.up.eon.Info;
import io.vertx.up.eon.em.JobStatus;
import io.vertx.up.eon.em.JobType;
import io.vertx.up.exception._501JobOnMissingException;
import io.vertx.up.log.Annal;
import io.vertx.up.epic.Ut;
import io.vertx.up.fn.Fn;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Data Object to describe job detail, it stored job definition
 * 1) The definition came from scanned @Job annotation class ( each class should has one Mission )
 * 2) The definition came from JobStore interface ( the job definition may be stored into database or other
 */
public class Mission implements Serializable {
    private static final Annal LOGGER = Annal.get(Mission.class);
    /* Job status, default job is 'ready' */
    private JobStatus status = JobStatus.READY;
    /* Job name */
    private String name;
    /* Job type */
    private JobType type;
    /* Job code */
    private String code;
    /* Job description */
    private String comment;
    /* Job configuration */
    @JsonSerialize(using = JsonObjectSerializer.class)
    @JsonDeserialize(using = JsonObjectDeserializer.class)
    private JsonObject metadata = new JsonObject();
    /* Job additional */
    @JsonSerialize(using = JsonObjectSerializer.class)
    @JsonDeserialize(using = JsonObjectDeserializer.class)
    private JsonObject additional = new JsonObject();
    /* Time: started time */
    @JsonIgnore
    private Instant instant = Instant.now();
    /* Time: duration, default is 5 seconds */
    private long duration = 5_000L;

    @JsonSerialize(using = ClassSerializer.class)
    @JsonDeserialize(using = ClassDeserializer.class)
    private Class<?> income;

    private String incomeAddress;
    @JsonSerialize(using = ClassSerializer.class)
    @JsonDeserialize(using = ClassDeserializer.class)
    private Class<?> outcome;

    private String outcomeAddress;

    /* Job reference */
    @JsonIgnore
    private Object proxy;
    /* Job begin method */
    @JsonIgnore
    private Method on;
    /* Job end method */
    @JsonIgnore
    private Method off;

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(final JobStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public JobType getType() {
        return type;
    }

    public void setType(final JobType type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public JsonObject getMetadata() {
        return metadata;
    }

    public void setMetadata(final JsonObject metadata) {
        this.metadata = metadata;
    }

    public JsonObject getAdditional() {
        return additional;
    }

    public void setAdditional(final JsonObject additional) {
        this.additional = additional;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(final Instant instant) {
        this.instant = instant;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(final long duration) {
        this.duration = duration;
    }

    public Object getProxy() {
        return proxy;
    }

    public void setProxy(final Object proxy) {
        this.proxy = proxy;
    }

    public Method getOn() {
        return on;
    }

    public void setOn(final Method on) {
        this.on = on;
    }

    public Method getOff() {
        return off;
    }

    public void setOff(final Method off) {
        this.off = off;
    }

    public Class<?> getIncome() {
        return income;
    }

    public void setIncome(final Class<?> income) {
        this.income = income;
    }

    public String getIncomeAddress() {
        return incomeAddress;
    }

    public void setIncomeAddress(final String incomeAddress) {
        this.incomeAddress = incomeAddress;
    }

    public Class<?> getOutcome() {
        return outcome;
    }

    public void setOutcome(final Class<?> outcome) {
        this.outcome = outcome;
    }

    public String getOutcomeAddress() {
        return outcomeAddress;
    }

    public void setOutcomeAddress(final String outcomeAddress) {
        this.outcomeAddress = outcomeAddress;
    }

    public Mission connect(final Class<?> clazz) {
        /*
         * Here the system should connect clazz to set:
         * 1. proxy
         *    - on
         *    - off
         * 2. in/out
         *    - income
         *    - incomeAddress
         *    - outcome
         *    - outcomeAddress
         */
        final Object proxy = Ut.singleton(clazz);
        if (Objects.nonNull(proxy)) {
            /*
             * 1. proxy of class has bee initialized successfully
             * Care: The field of other instances will be bind in future after mission
             */
            this.proxy = proxy;
            /*
             * 2. @On
             *  It's required in clazz definition or here should throw exception or errors
             */
            on = Arrays.stream(clazz.getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(On.class))
                    .findFirst().orElse(null);
            Fn.out(null == on, _501JobOnMissingException.class,
                    getClass(), clazz.getName());
            /*
             * Income / IncomeAddress
             */
            final Annotation on = this.on.getAnnotation(On.class);
            incomeAddress = invoke(on, "address", this::getIncomeAddress);
            income = invoke(on, "income", this::getIncome);
            if (Ut.isNil(incomeAddress)) {
                incomeAddress = null;
            }

            /*
             * 3. @Off
             * It's optional in clazz definition
             */
            off = Arrays.stream(clazz.getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(Off.class))
                    .findFirst().orElse(null);
            if (Objects.nonNull(off)) {
                /*
                 * Outcome / OutcomeAddress
                 */
                final Annotation out = off.getAnnotation(Off.class);
                outcomeAddress = invoke(out, "address", this::getOutcomeAddress);
                outcome = invoke(out, "outcome", this::getOutcome);
                if (Ut.isNil(outcomeAddress)) {
                    outcomeAddress = null;
                }
            } else {
                LOGGER.info(Info.JOB_NO_OFF, getName());
            }
        }
        return this;
    }

    private <T> T invoke(final Annotation annotation, final String annotationMethod,
                         final Supplier<T> supplier) {
        /*
         * Stored in database / or @Job -> config file
         */
        T reference = supplier.get();
        if (Objects.isNull(reference)) {
            /*
             * Annotation extraction
             */
            reference = Ut.invoke(annotation, annotationMethod);
        }
        return reference;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "status=" + status +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", code='" + code + '\'' +
                ", comment='" + comment + '\'' +
                ", metadata=" + metadata +
                ", additional=" + additional +
                ", instant=" + instant +
                ", duration=" + duration +
                ", income=" + income +
                ", incomeAddress='" + incomeAddress + '\'' +
                ", outcome=" + outcome +
                ", outcomeAddress='" + outcomeAddress + '\'' +
                ", proxy=" + proxy +
                ", on=" + on +
                ", off=" + off +
                '}';
    }
}
