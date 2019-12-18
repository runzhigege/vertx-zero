/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.rbac.domain.tables.pojos;


import cn.vertxup.rbac.domain.tables.interfaces.ISResource;

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
public class SResource implements ISResource {

    private static final long serialVersionUID = 485153002;

    private String        key;
    private String        code;
    private String        name;
    private String        comment;
    private Integer       level;
    private String        modeRole;
    private String        modeGroup;
    private String        modeTree;
    private String        sigma;
    private String        category;
    private String        language;
    private Boolean       active;
    private String        metadata;
    private LocalDateTime createdAt;
    private String        createdBy;
    private LocalDateTime updatedAt;
    private String        updatedBy;

    public SResource() {}

    public SResource(SResource value) {
        this.key = value.key;
        this.code = value.code;
        this.name = value.name;
        this.comment = value.comment;
        this.level = value.level;
        this.modeRole = value.modeRole;
        this.modeGroup = value.modeGroup;
        this.modeTree = value.modeTree;
        this.sigma = value.sigma;
        this.category = value.category;
        this.language = value.language;
        this.active = value.active;
        this.metadata = value.metadata;
        this.createdAt = value.createdAt;
        this.createdBy = value.createdBy;
        this.updatedAt = value.updatedAt;
        this.updatedBy = value.updatedBy;
    }

    public SResource(
        String        key,
        String        code,
        String        name,
        String        comment,
        Integer       level,
        String        modeRole,
        String        modeGroup,
        String        modeTree,
        String        sigma,
        String        category,
        String        language,
        Boolean       active,
        String        metadata,
        LocalDateTime createdAt,
        String        createdBy,
        LocalDateTime updatedAt,
        String        updatedBy
    ) {
        this.key = key;
        this.code = code;
        this.name = name;
        this.comment = comment;
        this.level = level;
        this.modeRole = modeRole;
        this.modeGroup = modeGroup;
        this.modeTree = modeTree;
        this.sigma = sigma;
        this.category = category;
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
    public SResource setKey(String key) {
        this.key = key;
        return this;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public SResource setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public SResource setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getComment() {
        return this.comment;
    }

    @Override
    public SResource setComment(String comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public Integer getLevel() {
        return this.level;
    }

    @Override
    public SResource setLevel(Integer level) {
        this.level = level;
        return this;
    }

    @Override
    public String getModeRole() {
        return this.modeRole;
    }

    @Override
    public SResource setModeRole(String modeRole) {
        this.modeRole = modeRole;
        return this;
    }

    @Override
    public String getModeGroup() {
        return this.modeGroup;
    }

    @Override
    public SResource setModeGroup(String modeGroup) {
        this.modeGroup = modeGroup;
        return this;
    }

    @Override
    public String getModeTree() {
        return this.modeTree;
    }

    @Override
    public SResource setModeTree(String modeTree) {
        this.modeTree = modeTree;
        return this;
    }

    @Override
    public String getSigma() {
        return this.sigma;
    }

    @Override
    public SResource setSigma(String sigma) {
        this.sigma = sigma;
        return this;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public SResource setCategory(String category) {
        this.category = category;
        return this;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    @Override
    public SResource setLanguage(String language) {
        this.language = language;
        return this;
    }

    @Override
    public Boolean getActive() {
        return this.active;
    }

    @Override
    public SResource setActive(Boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public String getMetadata() {
        return this.metadata;
    }

    @Override
    public SResource setMetadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public SResource setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    @Override
    public SResource setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public SResource setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    @Override
    public SResource setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SResource (");

        sb.append(key);
        sb.append(", ").append(code);
        sb.append(", ").append(name);
        sb.append(", ").append(comment);
        sb.append(", ").append(level);
        sb.append(", ").append(modeRole);
        sb.append(", ").append(modeGroup);
        sb.append(", ").append(modeTree);
        sb.append(", ").append(sigma);
        sb.append(", ").append(category);
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
    public void from(ISResource from) {
        setKey(from.getKey());
        setCode(from.getCode());
        setName(from.getName());
        setComment(from.getComment());
        setLevel(from.getLevel());
        setModeRole(from.getModeRole());
        setModeGroup(from.getModeGroup());
        setModeTree(from.getModeTree());
        setSigma(from.getSigma());
        setCategory(from.getCategory());
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
    public <E extends ISResource> E into(E into) {
        into.from(this);
        return into;
    }

    public SResource(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
