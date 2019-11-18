/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.erp.domain.tables.pojos;


import cn.vertxup.erp.domain.tables.interfaces.IECustomer;

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
public class ECustomer implements IECustomer {

    private static final long serialVersionUID = 1703338377;

    private String        key;
    private String        comment;
    private String        name;
    private String        title;
    private String        code;
    private String        taxCode;
    private String        taxTitle;
    private String        contactName;
    private String        contactPhone;
    private String        contactEmail;
    private String        contactOnline;
    private String        email;
    private String        fax;
    private String        homepage;
    private String        logo;
    private String        phone;
    private String        address;
    private Boolean       runUp;
    private String        type;
    private String        metadata;
    private Boolean       active;
    private String        sigma;
    private String        language;
    private LocalDateTime createdAt;
    private String        createdBy;
    private LocalDateTime updatedAt;
    private String        updatedBy;

    public ECustomer() {}

    public ECustomer(ECustomer value) {
        this.key = value.key;
        this.comment = value.comment;
        this.name = value.name;
        this.title = value.title;
        this.code = value.code;
        this.taxCode = value.taxCode;
        this.taxTitle = value.taxTitle;
        this.contactName = value.contactName;
        this.contactPhone = value.contactPhone;
        this.contactEmail = value.contactEmail;
        this.contactOnline = value.contactOnline;
        this.email = value.email;
        this.fax = value.fax;
        this.homepage = value.homepage;
        this.logo = value.logo;
        this.phone = value.phone;
        this.address = value.address;
        this.runUp = value.runUp;
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

    public ECustomer(
        String        key,
        String        comment,
        String        name,
        String        title,
        String        code,
        String        taxCode,
        String        taxTitle,
        String        contactName,
        String        contactPhone,
        String        contactEmail,
        String        contactOnline,
        String        email,
        String        fax,
        String        homepage,
        String        logo,
        String        phone,
        String        address,
        Boolean       runUp,
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
        this.comment = comment;
        this.name = name;
        this.title = title;
        this.code = code;
        this.taxCode = taxCode;
        this.taxTitle = taxTitle;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.contactOnline = contactOnline;
        this.email = email;
        this.fax = fax;
        this.homepage = homepage;
        this.logo = logo;
        this.phone = phone;
        this.address = address;
        this.runUp = runUp;
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
    public ECustomer setKey(String key) {
        this.key = key;
        return this;
    }

    @Override
    public String getComment() {
        return this.comment;
    }

    @Override
    public ECustomer setComment(String comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ECustomer setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public ECustomer setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public ECustomer setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String getTaxCode() {
        return this.taxCode;
    }

    @Override
    public ECustomer setTaxCode(String taxCode) {
        this.taxCode = taxCode;
        return this;
    }

    @Override
    public String getTaxTitle() {
        return this.taxTitle;
    }

    @Override
    public ECustomer setTaxTitle(String taxTitle) {
        this.taxTitle = taxTitle;
        return this;
    }

    @Override
    public String getContactName() {
        return this.contactName;
    }

    @Override
    public ECustomer setContactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    @Override
    public String getContactPhone() {
        return this.contactPhone;
    }

    @Override
    public ECustomer setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }

    @Override
    public String getContactEmail() {
        return this.contactEmail;
    }

    @Override
    public ECustomer setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    @Override
    public String getContactOnline() {
        return this.contactOnline;
    }

    @Override
    public ECustomer setContactOnline(String contactOnline) {
        this.contactOnline = contactOnline;
        return this;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public ECustomer setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String getFax() {
        return this.fax;
    }

    @Override
    public ECustomer setFax(String fax) {
        this.fax = fax;
        return this;
    }

    @Override
    public String getHomepage() {
        return this.homepage;
    }

    @Override
    public ECustomer setHomepage(String homepage) {
        this.homepage = homepage;
        return this;
    }

    @Override
    public String getLogo() {
        return this.logo;
    }

    @Override
    public ECustomer setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    @Override
    public String getPhone() {
        return this.phone;
    }

    @Override
    public ECustomer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public ECustomer setAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public Boolean getRunUp() {
        return this.runUp;
    }

    @Override
    public ECustomer setRunUp(Boolean runUp) {
        this.runUp = runUp;
        return this;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public ECustomer setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String getMetadata() {
        return this.metadata;
    }

    @Override
    public ECustomer setMetadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    @Override
    public Boolean getActive() {
        return this.active;
    }

    @Override
    public ECustomer setActive(Boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public String getSigma() {
        return this.sigma;
    }

    @Override
    public ECustomer setSigma(String sigma) {
        this.sigma = sigma;
        return this;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    @Override
    public ECustomer setLanguage(String language) {
        this.language = language;
        return this;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public ECustomer setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    @Override
    public ECustomer setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public ECustomer setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    @Override
    public ECustomer setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ECustomer (");

        sb.append(key);
        sb.append(", ").append(comment);
        sb.append(", ").append(name);
        sb.append(", ").append(title);
        sb.append(", ").append(code);
        sb.append(", ").append(taxCode);
        sb.append(", ").append(taxTitle);
        sb.append(", ").append(contactName);
        sb.append(", ").append(contactPhone);
        sb.append(", ").append(contactEmail);
        sb.append(", ").append(contactOnline);
        sb.append(", ").append(email);
        sb.append(", ").append(fax);
        sb.append(", ").append(homepage);
        sb.append(", ").append(logo);
        sb.append(", ").append(phone);
        sb.append(", ").append(address);
        sb.append(", ").append(runUp);
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
    public void from(IECustomer from) {
        setKey(from.getKey());
        setComment(from.getComment());
        setName(from.getName());
        setTitle(from.getTitle());
        setCode(from.getCode());
        setTaxCode(from.getTaxCode());
        setTaxTitle(from.getTaxTitle());
        setContactName(from.getContactName());
        setContactPhone(from.getContactPhone());
        setContactEmail(from.getContactEmail());
        setContactOnline(from.getContactOnline());
        setEmail(from.getEmail());
        setFax(from.getFax());
        setHomepage(from.getHomepage());
        setLogo(from.getLogo());
        setPhone(from.getPhone());
        setAddress(from.getAddress());
        setRunUp(from.getRunUp());
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
    public <E extends IECustomer> E into(E into) {
        into.from(this);
        return into;
    }

    public ECustomer(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
