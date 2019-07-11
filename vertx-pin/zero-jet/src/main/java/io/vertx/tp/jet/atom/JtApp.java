package io.vertx.tp.jet.atom;

import com.fasterxml.jackson.databind.JsonObjectDeserializer;
import com.fasterxml.jackson.databind.JsonObjectSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.atom.Database;

import java.io.Serializable;

/*
 * Environment for current jet here.
 */
public class JtApp implements Serializable {
    /* appId, appKey, sigma */
    private transient String appId;
    private transient String appKey;
    private transient String sigma;

    /* name, code, language, active */
    private transient String name;
    private transient String code;
    private transient String language;
    private transient Boolean active;

    /* logo, title */
    private transient String logo;
    private transient String title;

    /* business -> icp, email, copyRight */
    @JsonSerialize(using = JsonObjectSerializer.class)
    @JsonDeserialize(using = JsonObjectDeserializer.class)
    private transient JsonObject business;

    /* backend -> domain, appPort, route */
    @JsonSerialize(using = JsonObjectSerializer.class)
    @JsonDeserialize(using = JsonObjectDeserializer.class)
    private transient JsonObject backend;

    /* frontend -> path, urlEntry, urlMain */
    @JsonSerialize(using = JsonObjectSerializer.class)
    @JsonDeserialize(using = JsonObjectDeserializer.class)
    private transient JsonObject frontend;

    /* auditor -> createdAt, createdBy, updatedAt, updatedBy */
    @JsonSerialize(using = JsonObjectSerializer.class)
    @JsonDeserialize(using = JsonObjectDeserializer.class)
    private transient JsonObject auditor;

    /*
     * database
     * - hostname, instance, port, category
     * - jdbcUrl, username, password
     * */
    private transient Database source;

    @Override
    public String toString() {
        return "JtEnv{" +
                "appId='" + appId + '\'' +
                ", appKey='" + appKey + '\'' +
                ", sigma='" + sigma + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", language='" + language + '\'' +
                ", active=" + active +
                ", logo='" + logo + '\'' +
                ", title='" + title + '\'' +
                ", business=" + business +
                ", backend=" + backend +
                ", frontend=" + frontend +
                ", auditor=" + auditor +
                ", source=" + source +
                '}';
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(final String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(final String appKey) {
        this.appKey = appKey;
    }

    public String getSigma() {
        return sigma;
    }

    public void setSigma(final String sigma) {
        this.sigma = sigma;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(final Boolean active) {
        this.active = active;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(final String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setBusiness(final JsonObject business) {
        this.business = business;
    }

    public void setBackend(final JsonObject backend) {
        this.backend = backend;
    }

    public void setFrontend(final JsonObject frontend) {
        this.frontend = frontend;
    }

    public void setAuditor(final JsonObject auditor) {
        this.auditor = auditor;
    }

    /*
     * Defined Method
     */
    public String getRoute() {
        return backend.getString(KeField.App.ROUTE);
    }

    public Database getSource() {
        return source;
    }

    public void setSource(final Database source) {
        this.source = source;
    }
}
