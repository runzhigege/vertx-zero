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
import io.vertx.up.eon.em.JobStatus;
import io.vertx.up.eon.em.JobType;
import io.zero.epic.Ut;

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
        return this.status;
    }

    public void setStatus(final JobStatus status) {
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public JobType getType() {
        return this.type;
    }

    public void setType(final JobType type) {
        this.type = type;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public JsonObject getMetadata() {
        return this.metadata;
    }

    public void setMetadata(final JsonObject metadata) {
        this.metadata = metadata;
    }

    public JsonObject getAdditional() {
        return this.additional;
    }

    public void setAdditional(final JsonObject additional) {
        this.additional = additional;
    }

    public Instant getInstant() {
        return this.instant;
    }

    public void setInstant(final Instant instant) {
        this.instant = instant;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(final long duration) {
        this.duration = duration;
    }

    public Object getProxy() {
        return this.proxy;
    }

    public void setProxy(final Object proxy) {
        this.proxy = proxy;
    }

    public Method getOn() {
        return this.on;
    }

    public void setOn(final Method on) {
        this.on = on;
    }

    public Method getOff() {
        return this.off;
    }

    public void setOff(final Method off) {
        this.off = off;
    }

    public Class<?> getIncome() {
        return this.income;
    }

    public void setIncome(final Class<?> income) {
        this.income = income;
    }

    public String getIncomeAddress() {
        return this.incomeAddress;
    }

    public void setIncomeAddress(final String incomeAddress) {
        this.incomeAddress = incomeAddress;
    }

    public Class<?> getOutcome() {
        return this.outcome;
    }

    public void setOutcome(final Class<?> outcome) {
        this.outcome = outcome;
    }

    public String getOutcomeAddress() {
        return this.outcomeAddress;
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
             * 2. @On / @Off
             */
            /* Method */
            this.on = Arrays.stream(clazz.getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(On.class))
                    .findFirst().orElse(null);
            this.off = Arrays.stream(clazz.getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(Off.class))
                    .findFirst().orElse(null);

            /*
             * Income / IncomeAddress
             */
            final Annotation on = this.on.getAnnotation(On.class);
            this.incomeAddress = this.invoke(on, "address", this::getIncomeAddress);
            this.income = this.invoke(on, "income", this::getIncome);
            if (Ut.isNil(this.incomeAddress)) {
                this.incomeAddress = null;
            }
            /*
             * Outcome / OutcomeAddress
             */
            final Annotation out = this.off.getAnnotation(Off.class);
            this.outcomeAddress = this.invoke(out, "address", this::getOutcomeAddress);
            this.outcome = this.invoke(out, "outcome", this::getOutcome);
            if (Ut.isNil(this.outcomeAddress)) {
                this.outcomeAddress = null;
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
                "status=" + this.status +
                ", name='" + this.name + '\'' +
                ", type=" + this.type +
                ", code='" + this.code + '\'' +
                ", comment='" + this.comment + '\'' +
                ", metadata=" + this.metadata +
                ", additional=" + this.additional +
                ", instant=" + this.instant +
                ", duration=" + this.duration +
                ", income=" + this.income +
                ", incomeAddress='" + this.incomeAddress + '\'' +
                ", outcome=" + this.outcome +
                ", outcomeAddress='" + this.outcomeAddress + '\'' +
                ", proxy=" + this.proxy +
                ", on=" + this.on +
                ", off=" + this.off +
                '}';
    }
}
