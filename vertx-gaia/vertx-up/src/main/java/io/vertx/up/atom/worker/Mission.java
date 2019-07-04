package io.vertx.up.atom.worker;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonObjectDeserializer;
import com.fasterxml.jackson.databind.JsonObjectSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.em.JobStatus;
import io.vertx.up.eon.em.JobType;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.Instant;

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
    private String description;
    /* Job configuration */
    @JsonSerialize(using = JsonObjectSerializer.class)
    @JsonDeserialize(using = JsonObjectDeserializer.class)
    private JsonObject config = new JsonObject();
    /* Job additional */
    @JsonSerialize(using = JsonObjectSerializer.class)
    @JsonDeserialize(using = JsonObjectDeserializer.class)
    private JsonObject additional = new JsonObject();
    /* Time: started time */
    @JsonIgnore
    private Instant instant = Instant.now();
    /* Time: duration, default is 5min */
    private long duration = 5_000L;

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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public JsonObject getConfig() {
        return this.config;
    }

    public void setConfig(final JsonObject config) {
        this.config = config;
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

    @Override
    public String toString() {
        return "Mission{" +
                "status=" + this.status +
                ", name='" + this.name + '\'' +
                ", type=" + this.type +
                ", code='" + this.code + '\'' +
                ", description='" + this.description + '\'' +
                ", config=" + this.config +
                ", additional=" + this.additional +
                ", instant=" + this.instant +
                ", duration=" + this.duration +
                ", proxy=" + this.proxy +
                ", on=" + this.on +
                ", off=" + this.off +
                '}';
    }
}
