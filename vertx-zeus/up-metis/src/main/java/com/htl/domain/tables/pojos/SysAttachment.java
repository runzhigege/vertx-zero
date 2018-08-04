/*
 * This file is generated by jOOQ.
*/
package com.htl.domain.tables.pojos;


import com.htl.domain.tables.interfaces.ISysAttachment;

import java.time.LocalDateTime;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SysAttachment implements ISysAttachment {

    private static final long serialVersionUID = -42974198;

    private String        pkId;
    private byte[]        bContent;
    private String        sName;
    private String        sCode;
    private String        sType;
    private String        jConfig;
    private Boolean       isActive;
    private String        zSigma;
    private String        zLanguage;
    private String        zCreateBy;
    private LocalDateTime zCreateTime;
    private String        zUpdateBy;
    private LocalDateTime zUpdateTime;

    public SysAttachment() {}

    public SysAttachment(SysAttachment value) {
        this.pkId = value.pkId;
        this.bContent = value.bContent;
        this.sName = value.sName;
        this.sCode = value.sCode;
        this.sType = value.sType;
        this.jConfig = value.jConfig;
        this.isActive = value.isActive;
        this.zSigma = value.zSigma;
        this.zLanguage = value.zLanguage;
        this.zCreateBy = value.zCreateBy;
        this.zCreateTime = value.zCreateTime;
        this.zUpdateBy = value.zUpdateBy;
        this.zUpdateTime = value.zUpdateTime;
    }

    public SysAttachment(
        String        pkId,
        byte[]        bContent,
        String        sName,
        String        sCode,
        String        sType,
        String        jConfig,
        Boolean       isActive,
        String        zSigma,
        String        zLanguage,
        String        zCreateBy,
        LocalDateTime zCreateTime,
        String        zUpdateBy,
        LocalDateTime zUpdateTime
    ) {
        this.pkId = pkId;
        this.bContent = bContent;
        this.sName = sName;
        this.sCode = sCode;
        this.sType = sType;
        this.jConfig = jConfig;
        this.isActive = isActive;
        this.zSigma = zSigma;
        this.zLanguage = zLanguage;
        this.zCreateBy = zCreateBy;
        this.zCreateTime = zCreateTime;
        this.zUpdateBy = zUpdateBy;
        this.zUpdateTime = zUpdateTime;
    }

    @Override
    public String getPkId() {
        return this.pkId;
    }

    @Override
    public SysAttachment setPkId(String pkId) {
        this.pkId = pkId;
        return this;
    }

    @Override
    public byte[] getBContent() {
        return this.bContent;
    }

    @Override
    public SysAttachment setBContent(byte... bContent) {
        this.bContent = bContent;
        return this;
    }

    @Override
    public String getSName() {
        return this.sName;
    }

    @Override
    public SysAttachment setSName(String sName) {
        this.sName = sName;
        return this;
    }

    @Override
    public String getSCode() {
        return this.sCode;
    }

    @Override
    public SysAttachment setSCode(String sCode) {
        this.sCode = sCode;
        return this;
    }

    @Override
    public String getSType() {
        return this.sType;
    }

    @Override
    public SysAttachment setSType(String sType) {
        this.sType = sType;
        return this;
    }

    @Override
    public String getJConfig() {
        return this.jConfig;
    }

    @Override
    public SysAttachment setJConfig(String jConfig) {
        this.jConfig = jConfig;
        return this;
    }

    @Override
    public Boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public SysAttachment setIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    @Override
    public String getZSigma() {
        return this.zSigma;
    }

    @Override
    public SysAttachment setZSigma(String zSigma) {
        this.zSigma = zSigma;
        return this;
    }

    @Override
    public String getZLanguage() {
        return this.zLanguage;
    }

    @Override
    public SysAttachment setZLanguage(String zLanguage) {
        this.zLanguage = zLanguage;
        return this;
    }

    @Override
    public String getZCreateBy() {
        return this.zCreateBy;
    }

    @Override
    public SysAttachment setZCreateBy(String zCreateBy) {
        this.zCreateBy = zCreateBy;
        return this;
    }

    @Override
    public LocalDateTime getZCreateTime() {
        return this.zCreateTime;
    }

    @Override
    public SysAttachment setZCreateTime(LocalDateTime zCreateTime) {
        this.zCreateTime = zCreateTime;
        return this;
    }

    @Override
    public String getZUpdateBy() {
        return this.zUpdateBy;
    }

    @Override
    public SysAttachment setZUpdateBy(String zUpdateBy) {
        this.zUpdateBy = zUpdateBy;
        return this;
    }

    @Override
    public LocalDateTime getZUpdateTime() {
        return this.zUpdateTime;
    }

    @Override
    public SysAttachment setZUpdateTime(LocalDateTime zUpdateTime) {
        this.zUpdateTime = zUpdateTime;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SysAttachment (");

        sb.append(pkId);
        sb.append(", ").append("[binary...]");
        sb.append(", ").append(sName);
        sb.append(", ").append(sCode);
        sb.append(", ").append(sType);
        sb.append(", ").append(jConfig);
        sb.append(", ").append(isActive);
        sb.append(", ").append(zSigma);
        sb.append(", ").append(zLanguage);
        sb.append(", ").append(zCreateBy);
        sb.append(", ").append(zCreateTime);
        sb.append(", ").append(zUpdateBy);
        sb.append(", ").append(zUpdateTime);

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
    public void from(ISysAttachment from) {
        setPkId(from.getPkId());
        setBContent(from.getBContent());
        setSName(from.getSName());
        setSCode(from.getSCode());
        setSType(from.getSType());
        setJConfig(from.getJConfig());
        setIsActive(from.getIsActive());
        setZSigma(from.getZSigma());
        setZLanguage(from.getZLanguage());
        setZCreateBy(from.getZCreateBy());
        setZCreateTime(from.getZCreateTime());
        setZUpdateBy(from.getZUpdateBy());
        setZUpdateTime(from.getZUpdateTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends ISysAttachment> E into(E into) {
        into.from(this);
        return into;
    }

    public SysAttachment(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
