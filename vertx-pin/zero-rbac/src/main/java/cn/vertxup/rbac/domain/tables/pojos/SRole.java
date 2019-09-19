/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.rbac.domain.tables.pojos;


import cn.vertxup.rbac.domain.tables.interfaces.ISRole;

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
public class SRole implements ISRole {

    private static final long serialVersionUID = -849628649;

    private String        key;
    private String        name;
    private String        code;
    private Boolean       power;
    private String        comment;
    private String        modelId;
    private String        modelKey;
    private String        category;
    private String        sigma;
    private String        language;
    private Boolean       active;
    private String        metadata;
    private LocalDateTime createdAt;
    private String        createdBy;
    private LocalDateTime updatedAt;
    private String        updatedBy;

    public SRole() {}

    public SRole(SRole value) {
        this.key = value.key;
        this.name = value.name;
        this.code = value.code;
        this.power = value.power;
        this.comment = value.comment;
        this.modelId = value.modelId;
        this.modelKey = value.modelKey;
        this.category = value.category;
        this.sigma = value.sigma;
        this.language = value.language;
        this.active = value.active;
        this.metadata = value.metadata;
        this.createdAt = value.createdAt;
        this.createdBy = value.createdBy;
        this.updatedAt = value.updatedAt;
        this.updatedBy = value.updatedBy;
    }

    public SRole(
        String        key,
        String        name,
        String        code,
        Boolean       power,
        String        comment,
        String        modelId,
        String        modelKey,
        String        category,
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
        this.name = name;
        this.code = code;
        this.power = power;
        this.comment = comment;
        this.modelId = modelId;
        this.modelKey = modelKey;
        this.category = category;
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
    public SRole setKey(String key) {
        this.key = key;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public SRole setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public SRole setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public Boolean getPower() {
        return this.power;
    }

    @Override
    public SRole setPower(Boolean power) {
        this.power = power;
        return this;
    }

    @Override
    public String getComment() {
        return this.comment;
    }

    @Override
    public SRole setComment(String comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public String getModelId() {
        return this.modelId;
    }

    @Override
    public SRole setModelId(String modelId) {
        this.modelId = modelId;
        return this;
    }

    @Override
    public String getModelKey() {
        return this.modelKey;
    }

    @Override
    public SRole setModelKey(String modelKey) {
        this.modelKey = modelKey;
        return this;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public SRole setCategory(String category) {
        this.category = category;
        return this;
    }

    @Override
    public String getSigma() {
        return this.sigma;
    }

    @Override
    public SRole setSigma(String sigma) {
        this.sigma = sigma;
        return this;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    @Override
    public SRole setLanguage(String language) {
        this.language = language;
        return this;
    }

    @Override
    public Boolean getActive() {
        return this.active;
    }

    @Override
    public SRole setActive(Boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public String getMetadata() {
        return this.metadata;
    }

    @Override
    public SRole setMetadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public SRole setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    @Override
    public SRole setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public SRole setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    @Override
    public SRole setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SRole (");

        sb.append(key);
        sb.append(", ").append(name);
        sb.append(", ").append(code);
        sb.append(", ").append(power);
        sb.append(", ").append(comment);
        sb.append(", ").append(modelId);
        sb.append(", ").append(modelKey);
        sb.append(", ").append(category);
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
    public void from(ISRole from) {
        setKey(from.getKey());
        setName(from.getName());
        setCode(from.getCode());
        setPower(from.getPower());
        setComment(from.getComment());
        setModelId(from.getModelId());
        setModelKey(from.getModelKey());
        setCategory(from.getCategory());
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
    public <E extends ISRole> E into(E into) {
        into.from(this);
        return into;
    }

    public SRole(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
