/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.jet.domain.tables.pojos;


import cn.vertxup.jet.domain.tables.interfaces.IIJob;

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
public class IJob implements IIJob {

    private static final long serialVersionUID = 1381959657;

    private String        key;
    private String        namespace;
    private String        name;
    private String        code;
    private String        type;
    private String        comment;
    private String        additional;
    private LocalDateTime runAt;
    private Long          duration;
    private String        proxy;
    private Integer       threshold;
    private String        incomeComponent;
    private String        incomeAddress;
    private String        outcomeComponent;
    private String        outcomeAddress;
    private String        serviceId;
    private String        sigma;
    private String        language;
    private Boolean       active;
    private String        metadata;
    private LocalDateTime createdAt;
    private String        createdBy;
    private LocalDateTime updatedAt;
    private String        updatedBy;

    public IJob() {}

    public IJob(IJob value) {
        this.key = value.key;
        this.namespace = value.namespace;
        this.name = value.name;
        this.code = value.code;
        this.type = value.type;
        this.comment = value.comment;
        this.additional = value.additional;
        this.runAt = value.runAt;
        this.duration = value.duration;
        this.proxy = value.proxy;
        this.threshold = value.threshold;
        this.incomeComponent = value.incomeComponent;
        this.incomeAddress = value.incomeAddress;
        this.outcomeComponent = value.outcomeComponent;
        this.outcomeAddress = value.outcomeAddress;
        this.serviceId = value.serviceId;
        this.sigma = value.sigma;
        this.language = value.language;
        this.active = value.active;
        this.metadata = value.metadata;
        this.createdAt = value.createdAt;
        this.createdBy = value.createdBy;
        this.updatedAt = value.updatedAt;
        this.updatedBy = value.updatedBy;
    }

    public IJob(
        String        key,
        String        namespace,
        String        name,
        String        code,
        String        type,
        String        comment,
        String        additional,
        LocalDateTime runAt,
        Long          duration,
        String        proxy,
        Integer       threshold,
        String        incomeComponent,
        String        incomeAddress,
        String        outcomeComponent,
        String        outcomeAddress,
        String        serviceId,
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
        this.namespace = namespace;
        this.name = name;
        this.code = code;
        this.type = type;
        this.comment = comment;
        this.additional = additional;
        this.runAt = runAt;
        this.duration = duration;
        this.proxy = proxy;
        this.threshold = threshold;
        this.incomeComponent = incomeComponent;
        this.incomeAddress = incomeAddress;
        this.outcomeComponent = outcomeComponent;
        this.outcomeAddress = outcomeAddress;
        this.serviceId = serviceId;
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
    public IJob setKey(String key) {
        this.key = key;
        return this;
    }

    @Override
    public String getNamespace() {
        return this.namespace;
    }

    @Override
    public IJob setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public IJob setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public IJob setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public IJob setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String getComment() {
        return this.comment;
    }

    @Override
    public IJob setComment(String comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public String getAdditional() {
        return this.additional;
    }

    @Override
    public IJob setAdditional(String additional) {
        this.additional = additional;
        return this;
    }

    @Override
    public LocalDateTime getRunAt() {
        return this.runAt;
    }

    @Override
    public IJob setRunAt(LocalDateTime runAt) {
        this.runAt = runAt;
        return this;
    }

    @Override
    public Long getDuration() {
        return this.duration;
    }

    @Override
    public IJob setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public String getProxy() {
        return this.proxy;
    }

    @Override
    public IJob setProxy(String proxy) {
        this.proxy = proxy;
        return this;
    }

    @Override
    public Integer getThreshold() {
        return this.threshold;
    }

    @Override
    public IJob setThreshold(Integer threshold) {
        this.threshold = threshold;
        return this;
    }

    @Override
    public String getIncomeComponent() {
        return this.incomeComponent;
    }

    @Override
    public IJob setIncomeComponent(String incomeComponent) {
        this.incomeComponent = incomeComponent;
        return this;
    }

    @Override
    public String getIncomeAddress() {
        return this.incomeAddress;
    }

    @Override
    public IJob setIncomeAddress(String incomeAddress) {
        this.incomeAddress = incomeAddress;
        return this;
    }

    @Override
    public String getOutcomeComponent() {
        return this.outcomeComponent;
    }

    @Override
    public IJob setOutcomeComponent(String outcomeComponent) {
        this.outcomeComponent = outcomeComponent;
        return this;
    }

    @Override
    public String getOutcomeAddress() {
        return this.outcomeAddress;
    }

    @Override
    public IJob setOutcomeAddress(String outcomeAddress) {
        this.outcomeAddress = outcomeAddress;
        return this;
    }

    @Override
    public String getServiceId() {
        return this.serviceId;
    }

    @Override
    public IJob setServiceId(String serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    @Override
    public String getSigma() {
        return this.sigma;
    }

    @Override
    public IJob setSigma(String sigma) {
        this.sigma = sigma;
        return this;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    @Override
    public IJob setLanguage(String language) {
        this.language = language;
        return this;
    }

    @Override
    public Boolean getActive() {
        return this.active;
    }

    @Override
    public IJob setActive(Boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public String getMetadata() {
        return this.metadata;
    }

    @Override
    public IJob setMetadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public IJob setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    @Override
    public IJob setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public IJob setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    @Override
    public IJob setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("IJob (");

        sb.append(key);
        sb.append(", ").append(namespace);
        sb.append(", ").append(name);
        sb.append(", ").append(code);
        sb.append(", ").append(type);
        sb.append(", ").append(comment);
        sb.append(", ").append(additional);
        sb.append(", ").append(runAt);
        sb.append(", ").append(duration);
        sb.append(", ").append(proxy);
        sb.append(", ").append(threshold);
        sb.append(", ").append(incomeComponent);
        sb.append(", ").append(incomeAddress);
        sb.append(", ").append(outcomeComponent);
        sb.append(", ").append(outcomeAddress);
        sb.append(", ").append(serviceId);
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
    public void from(IIJob from) {
        setKey(from.getKey());
        setNamespace(from.getNamespace());
        setName(from.getName());
        setCode(from.getCode());
        setType(from.getType());
        setComment(from.getComment());
        setAdditional(from.getAdditional());
        setRunAt(from.getRunAt());
        setDuration(from.getDuration());
        setProxy(from.getProxy());
        setThreshold(from.getThreshold());
        setIncomeComponent(from.getIncomeComponent());
        setIncomeAddress(from.getIncomeAddress());
        setOutcomeComponent(from.getOutcomeComponent());
        setOutcomeAddress(from.getOutcomeAddress());
        setServiceId(from.getServiceId());
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
    public <E extends IIJob> E into(E into) {
        into.from(this);
        return into;
    }

    public IJob(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
