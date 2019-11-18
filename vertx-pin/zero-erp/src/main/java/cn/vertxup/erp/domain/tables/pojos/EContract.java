/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.erp.domain.tables.pojos;


import cn.vertxup.erp.domain.tables.interfaces.IEContract;

import java.math.BigDecimal;
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
public class EContract implements IEContract {

    private static final long serialVersionUID = 2111786849;

    private String        key;
    private String        name;
    private String        code;
    private String        title;
    private String        fileKey;
    private BigDecimal    amount;
    private String        companyId;
    private String        customerId;
    private LocalDateTime expiredAt;
    private LocalDateTime signedAt;
    private LocalDateTime runAt;
    private LocalDateTime runUpAt;
    private String        aName;
    private String        aPhone;
    private String        aAddress;
    private String        bName;
    private String        bPhone;
    private String        bAddress;
    private String        type;
    private String        metadata;
    private Boolean       active;
    private String        sigma;
    private String        language;
    private LocalDateTime createdAt;
    private String        createdBy;
    private LocalDateTime updatedAt;
    private String        updatedBy;

    public EContract() {}

    public EContract(EContract value) {
        this.key = value.key;
        this.name = value.name;
        this.code = value.code;
        this.title = value.title;
        this.fileKey = value.fileKey;
        this.amount = value.amount;
        this.companyId = value.companyId;
        this.customerId = value.customerId;
        this.expiredAt = value.expiredAt;
        this.signedAt = value.signedAt;
        this.runAt = value.runAt;
        this.runUpAt = value.runUpAt;
        this.aName = value.aName;
        this.aPhone = value.aPhone;
        this.aAddress = value.aAddress;
        this.bName = value.bName;
        this.bPhone = value.bPhone;
        this.bAddress = value.bAddress;
        this.type = value.type;
        this.metadata = value.metadata;
        this.active = value.active;
        this.sigma = value.sigma;
        this.language = value.language;
        this.createdAt = value.createdAt;
        this.createdBy = value.createdBy;
        this.updatedAt = value.updatedAt;
        this.updatedBy = value.updatedBy;
    }

    public EContract(
        String        key,
        String        name,
        String        code,
        String        title,
        String        fileKey,
        BigDecimal    amount,
        String        companyId,
        String        customerId,
        LocalDateTime expiredAt,
        LocalDateTime signedAt,
        LocalDateTime runAt,
        LocalDateTime runUpAt,
        String        aName,
        String        aPhone,
        String        aAddress,
        String        bName,
        String        bPhone,
        String        bAddress,
        String        type,
        String        metadata,
        Boolean       active,
        String        sigma,
        String        language,
        LocalDateTime createdAt,
        String        createdBy,
        LocalDateTime updatedAt,
        String        updatedBy
    ) {
        this.key = key;
        this.name = name;
        this.code = code;
        this.title = title;
        this.fileKey = fileKey;
        this.amount = amount;
        this.companyId = companyId;
        this.customerId = customerId;
        this.expiredAt = expiredAt;
        this.signedAt = signedAt;
        this.runAt = runAt;
        this.runUpAt = runUpAt;
        this.aName = aName;
        this.aPhone = aPhone;
        this.aAddress = aAddress;
        this.bName = bName;
        this.bPhone = bPhone;
        this.bAddress = bAddress;
        this.type = type;
        this.metadata = metadata;
        this.active = active;
        this.sigma = sigma;
        this.language = language;
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
    public EContract setKey(String key) {
        this.key = key;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public EContract setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public EContract setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public EContract setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public String getFileKey() {
        return this.fileKey;
    }

    @Override
    public EContract setFileKey(String fileKey) {
        this.fileKey = fileKey;
        return this;
    }

    @Override
    public BigDecimal getAmount() {
        return this.amount;
    }

    @Override
    public EContract setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public String getCompanyId() {
        return this.companyId;
    }

    @Override
    public EContract setCompanyId(String companyId) {
        this.companyId = companyId;
        return this;
    }

    @Override
    public String getCustomerId() {
        return this.customerId;
    }

    @Override
    public EContract setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    @Override
    public LocalDateTime getExpiredAt() {
        return this.expiredAt;
    }

    @Override
    public EContract setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
        return this;
    }

    @Override
    public LocalDateTime getSignedAt() {
        return this.signedAt;
    }

    @Override
    public EContract setSignedAt(LocalDateTime signedAt) {
        this.signedAt = signedAt;
        return this;
    }

    @Override
    public LocalDateTime getRunAt() {
        return this.runAt;
    }

    @Override
    public EContract setRunAt(LocalDateTime runAt) {
        this.runAt = runAt;
        return this;
    }

    @Override
    public LocalDateTime getRunUpAt() {
        return this.runUpAt;
    }

    @Override
    public EContract setRunUpAt(LocalDateTime runUpAt) {
        this.runUpAt = runUpAt;
        return this;
    }

    @Override
    public String getAName() {
        return this.aName;
    }

    @Override
    public EContract setAName(String aName) {
        this.aName = aName;
        return this;
    }

    @Override
    public String getAPhone() {
        return this.aPhone;
    }

    @Override
    public EContract setAPhone(String aPhone) {
        this.aPhone = aPhone;
        return this;
    }

    @Override
    public String getAAddress() {
        return this.aAddress;
    }

    @Override
    public EContract setAAddress(String aAddress) {
        this.aAddress = aAddress;
        return this;
    }

    @Override
    public String getBName() {
        return this.bName;
    }

    @Override
    public EContract setBName(String bName) {
        this.bName = bName;
        return this;
    }

    @Override
    public String getBPhone() {
        return this.bPhone;
    }

    @Override
    public EContract setBPhone(String bPhone) {
        this.bPhone = bPhone;
        return this;
    }

    @Override
    public String getBAddress() {
        return this.bAddress;
    }

    @Override
    public EContract setBAddress(String bAddress) {
        this.bAddress = bAddress;
        return this;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public EContract setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String getMetadata() {
        return this.metadata;
    }

    @Override
    public EContract setMetadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    @Override
    public Boolean getActive() {
        return this.active;
    }

    @Override
    public EContract setActive(Boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public String getSigma() {
        return this.sigma;
    }

    @Override
    public EContract setSigma(String sigma) {
        this.sigma = sigma;
        return this;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    @Override
    public EContract setLanguage(String language) {
        this.language = language;
        return this;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public EContract setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    @Override
    public EContract setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public EContract setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    @Override
    public EContract setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("EContract (");

        sb.append(key);
        sb.append(", ").append(name);
        sb.append(", ").append(code);
        sb.append(", ").append(title);
        sb.append(", ").append(fileKey);
        sb.append(", ").append(amount);
        sb.append(", ").append(companyId);
        sb.append(", ").append(customerId);
        sb.append(", ").append(expiredAt);
        sb.append(", ").append(signedAt);
        sb.append(", ").append(runAt);
        sb.append(", ").append(runUpAt);
        sb.append(", ").append(aName);
        sb.append(", ").append(aPhone);
        sb.append(", ").append(aAddress);
        sb.append(", ").append(bName);
        sb.append(", ").append(bPhone);
        sb.append(", ").append(bAddress);
        sb.append(", ").append(type);
        sb.append(", ").append(metadata);
        sb.append(", ").append(active);
        sb.append(", ").append(sigma);
        sb.append(", ").append(language);
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
    public void from(IEContract from) {
        setKey(from.getKey());
        setName(from.getName());
        setCode(from.getCode());
        setTitle(from.getTitle());
        setFileKey(from.getFileKey());
        setAmount(from.getAmount());
        setCompanyId(from.getCompanyId());
        setCustomerId(from.getCustomerId());
        setExpiredAt(from.getExpiredAt());
        setSignedAt(from.getSignedAt());
        setRunAt(from.getRunAt());
        setRunUpAt(from.getRunUpAt());
        setAName(from.getAName());
        setAPhone(from.getAPhone());
        setAAddress(from.getAAddress());
        setBName(from.getBName());
        setBPhone(from.getBPhone());
        setBAddress(from.getBAddress());
        setType(from.getType());
        setMetadata(from.getMetadata());
        setActive(from.getActive());
        setSigma(from.getSigma());
        setLanguage(from.getLanguage());
        setCreatedAt(from.getCreatedAt());
        setCreatedBy(from.getCreatedBy());
        setUpdatedAt(from.getUpdatedAt());
        setUpdatedBy(from.getUpdatedBy());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IEContract> E into(E into) {
        into.from(this);
        return into;
    }

    public EContract(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
