/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.domain.tables.pojos;


import cn.vertxup.domain.tables.interfaces.ISAction;

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
public class SAction implements ISAction {

    private static final long serialVersionUID = 2079319091;

    private String        key;
    private String        name;
    private String        code;
    private String        resourceId;
    private String        permissionId;
    private Integer       level;
    private String        mode;
    private String        uri;
    private String        method;
    private String        sigma;
    private String        effectComponent;
    private String        effectConfig;
    private String        language;
    private Boolean       active;
    private String        metadata;
    private String        comment;
    private LocalDateTime createdAt;
    private String        createdBy;
    private LocalDateTime updatedAt;
    private String        updatedBy;

    public SAction() {}

    public SAction(SAction value) {
        this.key = value.key;
        this.name = value.name;
        this.code = value.code;
        this.resourceId = value.resourceId;
        this.permissionId = value.permissionId;
        this.level = value.level;
        this.mode = value.mode;
        this.uri = value.uri;
        this.method = value.method;
        this.sigma = value.sigma;
        this.effectComponent = value.effectComponent;
        this.effectConfig = value.effectConfig;
        this.language = value.language;
        this.active = value.active;
        this.metadata = value.metadata;
        this.comment = value.comment;
        this.createdAt = value.createdAt;
        this.createdBy = value.createdBy;
        this.updatedAt = value.updatedAt;
        this.updatedBy = value.updatedBy;
    }

    public SAction(
        String        key,
        String        name,
        String        code,
        String        resourceId,
        String        permissionId,
        Integer       level,
        String        mode,
        String        uri,
        String        method,
        String        sigma,
        String        effectComponent,
        String        effectConfig,
        String        language,
        Boolean       active,
        String        metadata,
        String        comment,
        LocalDateTime createdAt,
        String        createdBy,
        LocalDateTime updatedAt,
        String        updatedBy
    ) {
        this.key = key;
        this.name = name;
        this.code = code;
        this.resourceId = resourceId;
        this.permissionId = permissionId;
        this.level = level;
        this.mode = mode;
        this.uri = uri;
        this.method = method;
        this.sigma = sigma;
        this.effectComponent = effectComponent;
        this.effectConfig = effectConfig;
        this.language = language;
        this.active = active;
        this.metadata = metadata;
        this.comment = comment;
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
    public SAction setKey(String key) {
        this.key = key;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public SAction setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public SAction setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String getResourceId() {
        return this.resourceId;
    }

    @Override
    public SAction setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    @Override
    public String getPermissionId() {
        return this.permissionId;
    }

    @Override
    public SAction setPermissionId(String permissionId) {
        this.permissionId = permissionId;
        return this;
    }

    @Override
    public Integer getLevel() {
        return this.level;
    }

    @Override
    public SAction setLevel(Integer level) {
        this.level = level;
        return this;
    }

    @Override
    public String getMode() {
        return this.mode;
    }

    @Override
    public SAction setMode(String mode) {
        this.mode = mode;
        return this;
    }

    @Override
    public String getUri() {
        return this.uri;
    }

    @Override
    public SAction setUri(String uri) {
        this.uri = uri;
        return this;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public SAction setMethod(String method) {
        this.method = method;
        return this;
    }

    @Override
    public String getSigma() {
        return this.sigma;
    }

    @Override
    public SAction setSigma(String sigma) {
        this.sigma = sigma;
        return this;
    }

    @Override
    public String getEffectComponent() {
        return this.effectComponent;
    }

    @Override
    public SAction setEffectComponent(String effectComponent) {
        this.effectComponent = effectComponent;
        return this;
    }

    @Override
    public String getEffectConfig() {
        return this.effectConfig;
    }

    @Override
    public SAction setEffectConfig(String effectConfig) {
        this.effectConfig = effectConfig;
        return this;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    @Override
    public SAction setLanguage(String language) {
        this.language = language;
        return this;
    }

    @Override
    public Boolean getActive() {
        return this.active;
    }

    @Override
    public SAction setActive(Boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public String getMetadata() {
        return this.metadata;
    }

    @Override
    public SAction setMetadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    @Override
    public String getComment() {
        return this.comment;
    }

    @Override
    public SAction setComment(String comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public SAction setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    @Override
    public SAction setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public SAction setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    @Override
    public SAction setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SAction (");

        sb.append(key);
        sb.append(", ").append(name);
        sb.append(", ").append(code);
        sb.append(", ").append(resourceId);
        sb.append(", ").append(permissionId);
        sb.append(", ").append(level);
        sb.append(", ").append(mode);
        sb.append(", ").append(uri);
        sb.append(", ").append(method);
        sb.append(", ").append(sigma);
        sb.append(", ").append(effectComponent);
        sb.append(", ").append(effectConfig);
        sb.append(", ").append(language);
        sb.append(", ").append(active);
        sb.append(", ").append(metadata);
        sb.append(", ").append(comment);
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
    public void from(ISAction from) {
        setKey(from.getKey());
        setName(from.getName());
        setCode(from.getCode());
        setResourceId(from.getResourceId());
        setPermissionId(from.getPermissionId());
        setLevel(from.getLevel());
        setMode(from.getMode());
        setUri(from.getUri());
        setMethod(from.getMethod());
        setSigma(from.getSigma());
        setEffectComponent(from.getEffectComponent());
        setEffectConfig(from.getEffectConfig());
        setLanguage(from.getLanguage());
        setActive(from.getActive());
        setMetadata(from.getMetadata());
        setComment(from.getComment());
        setCreatedAt(from.getCreatedAt());
        setCreatedBy(from.getCreatedBy());
        setUpdatedAt(from.getUpdatedAt());
        setUpdatedBy(from.getUpdatedBy());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends ISAction> E into(E into) {
        into.from(this);
        return into;
    }

    public SAction(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
