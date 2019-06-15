/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.domain.tables.pojos;


import cn.vertxup.domain.tables.interfaces.IRResourceMatrix;

import java.time.LocalDateTime;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RResourceMatrix implements IRResourceMatrix {

    private static final long serialVersionUID = -998976170;

    private String        key;
    private String        owner;
    private Boolean       ownerType;
    private String        resourceId;
    private String        projection;
    private String        query;
    private String        rows;
    private LocalDateTime expired;
    private String        sigma;
    private String        language;
    private Boolean       active;
    private String        metadata;
    private LocalDateTime createdAt;
    private String        createdBy;
    private LocalDateTime updatedAt;
    private String        updatedBy;

    public RResourceMatrix() {}

    public RResourceMatrix(RResourceMatrix value) {
        this.key = value.key;
        this.owner = value.owner;
        this.ownerType = value.ownerType;
        this.resourceId = value.resourceId;
        this.projection = value.projection;
        this.query = value.query;
        this.rows = value.rows;
        this.expired = value.expired;
        this.sigma = value.sigma;
        this.language = value.language;
        this.active = value.active;
        this.metadata = value.metadata;
        this.createdAt = value.createdAt;
        this.createdBy = value.createdBy;
        this.updatedAt = value.updatedAt;
        this.updatedBy = value.updatedBy;
    }

    public RResourceMatrix(
        String        key,
        String        owner,
        Boolean       ownerType,
        String        resourceId,
        String        projection,
        String        query,
        String        rows,
        LocalDateTime expired,
        String        sigma,
        String        language,
        Boolean       active,
        String        metadata,
        LocalDateTime createdAt,
        String        createdBy,
        LocalDateTime updatedAt,
        String        updatedBy
    ) {
        this.key = key;
        this.owner = owner;
        this.ownerType = ownerType;
        this.resourceId = resourceId;
        this.projection = projection;
        this.query = query;
        this.rows = rows;
        this.expired = expired;
        this.sigma = sigma;
        this.language = language;
        this.active = active;
        this.metadata = metadata;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public RResourceMatrix setKey(String key) {
        this.key = key;
        return this;
    }

    @Override
    public String getOwner() {
        return this.owner;
    }

    @Override
    public RResourceMatrix setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public Boolean getOwnerType() {
        return this.ownerType;
    }

    @Override
    public RResourceMatrix setOwnerType(Boolean ownerType) {
        this.ownerType = ownerType;
        return this;
    }

    @Override
    public String getResourceId() {
        return this.resourceId;
    }

    @Override
    public RResourceMatrix setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    @Override
    public String getProjection() {
        return this.projection;
    }

    @Override
    public RResourceMatrix setProjection(String projection) {
        this.projection = projection;
        return this;
    }

    @Override
    public String getQuery() {
        return this.query;
    }

    @Override
    public RResourceMatrix setQuery(String query) {
        this.query = query;
        return this;
    }

    @Override
    public String getRows() {
        return this.rows;
    }

    @Override
    public RResourceMatrix setRows(String rows) {
        this.rows = rows;
        return this;
    }

    @Override
    public LocalDateTime getExpired() {
        return this.expired;
    }

    @Override
    public RResourceMatrix setExpired(LocalDateTime expired) {
        this.expired = expired;
        return this;
    }

    @Override
    public String getSigma() {
        return this.sigma;
    }

    @Override
    public RResourceMatrix setSigma(String sigma) {
        this.sigma = sigma;
        return this;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    @Override
    public RResourceMatrix setLanguage(String language) {
        this.language = language;
        return this;
    }

    @Override
    public Boolean getActive() {
        return this.active;
    }

    @Override
    public RResourceMatrix setActive(Boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public String getMetadata() {
        return this.metadata;
    }

    @Override
    public RResourceMatrix setMetadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public RResourceMatrix setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    @Override
    public RResourceMatrix setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public RResourceMatrix setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    @Override
    public RResourceMatrix setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("RResourceMatrix (");

        sb.append(key);
        sb.append(", ").append(owner);
        sb.append(", ").append(ownerType);
        sb.append(", ").append(resourceId);
        sb.append(", ").append(projection);
        sb.append(", ").append(query);
        sb.append(", ").append(rows);
        sb.append(", ").append(expired);
        sb.append(", ").append(sigma);
        sb.append(", ").append(language);
        sb.append(", ").append(active);
        sb.append(", ").append(metadata);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedAt);
        sb.append(", ").append(updatedBy);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IRResourceMatrix from) {
        setKey(from.getKey());
        setOwner(from.getOwner());
        setOwnerType(from.getOwnerType());
        setResourceId(from.getResourceId());
        setProjection(from.getProjection());
        setQuery(from.getQuery());
        setRows(from.getRows());
        setExpired(from.getExpired());
        setSigma(from.getSigma());
        setLanguage(from.getLanguage());
        setActive(from.getActive());
        setMetadata(from.getMetadata());
        setCreatedAt(from.getCreatedAt());
        setCreatedBy(from.getCreatedBy());
        setUpdatedAt(from.getUpdatedAt());
        setUpdatedBy(from.getUpdatedBy());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IRResourceMatrix> E into(E into) {
        into.from(this);
        return into;
    }

    public RResourceMatrix(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
