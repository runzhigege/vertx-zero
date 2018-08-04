/*
 * This file is generated by jOOQ.
*/
package com.htl.domain.tables.pojos;


import com.htl.domain.tables.interfaces.ISysTabular;

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
public class SysTabular implements ISysTabular {

    private static final long serialVersionUID = -41834784;

    private String        pkId;
    private String        tComment;
    private String        sName;
    private String        sCode;
    private String        sSerial;
    private String        sType;
    private String        jConfig;
    private Integer       iOrder;
    private Boolean       isActive;
    private String        zSigma;
    private String        zLanguage;
    private String        zCreateBy;
    private LocalDateTime zCreateTime;
    private String        zUpdateBy;
    private LocalDateTime zUpdateTime;

    public SysTabular() {}

    public SysTabular(SysTabular value) {
        this.pkId = value.pkId;
        this.tComment = value.tComment;
        this.sName = value.sName;
        this.sCode = value.sCode;
        this.sSerial = value.sSerial;
        this.sType = value.sType;
        this.jConfig = value.jConfig;
        this.iOrder = value.iOrder;
        this.isActive = value.isActive;
        this.zSigma = value.zSigma;
        this.zLanguage = value.zLanguage;
        this.zCreateBy = value.zCreateBy;
        this.zCreateTime = value.zCreateTime;
        this.zUpdateBy = value.zUpdateBy;
        this.zUpdateTime = value.zUpdateTime;
    }

    public SysTabular(
        String        pkId,
        String        tComment,
        String        sName,
        String        sCode,
        String        sSerial,
        String        sType,
        String        jConfig,
        Integer       iOrder,
        Boolean       isActive,
        String        zSigma,
        String        zLanguage,
        String        zCreateBy,
        LocalDateTime zCreateTime,
        String        zUpdateBy,
        LocalDateTime zUpdateTime
    ) {
        this.pkId = pkId;
        this.tComment = tComment;
        this.sName = sName;
        this.sCode = sCode;
        this.sSerial = sSerial;
        this.sType = sType;
        this.jConfig = jConfig;
        this.iOrder = iOrder;
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
    public SysTabular setPkId(String pkId) {
        this.pkId = pkId;
        return this;
    }

    @Override
    public String getTComment() {
        return this.tComment;
    }

    @Override
    public SysTabular setTComment(String tComment) {
        this.tComment = tComment;
        return this;
    }

    @Override
    public String getSName() {
        return this.sName;
    }

    @Override
    public SysTabular setSName(String sName) {
        this.sName = sName;
        return this;
    }

    @Override
    public String getSCode() {
        return this.sCode;
    }

    @Override
    public SysTabular setSCode(String sCode) {
        this.sCode = sCode;
        return this;
    }

    @Override
    public String getSSerial() {
        return this.sSerial;
    }

    @Override
    public SysTabular setSSerial(String sSerial) {
        this.sSerial = sSerial;
        return this;
    }

    @Override
    public String getSType() {
        return this.sType;
    }

    @Override
    public SysTabular setSType(String sType) {
        this.sType = sType;
        return this;
    }

    @Override
    public String getJConfig() {
        return this.jConfig;
    }

    @Override
    public SysTabular setJConfig(String jConfig) {
        this.jConfig = jConfig;
        return this;
    }

    @Override
    public Integer getIOrder() {
        return this.iOrder;
    }

    @Override
    public SysTabular setIOrder(Integer iOrder) {
        this.iOrder = iOrder;
        return this;
    }

    @Override
    public Boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public SysTabular setIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    @Override
    public String getZSigma() {
        return this.zSigma;
    }

    @Override
    public SysTabular setZSigma(String zSigma) {
        this.zSigma = zSigma;
        return this;
    }

    @Override
    public String getZLanguage() {
        return this.zLanguage;
    }

    @Override
    public SysTabular setZLanguage(String zLanguage) {
        this.zLanguage = zLanguage;
        return this;
    }

    @Override
    public String getZCreateBy() {
        return this.zCreateBy;
    }

    @Override
    public SysTabular setZCreateBy(String zCreateBy) {
        this.zCreateBy = zCreateBy;
        return this;
    }

    @Override
    public LocalDateTime getZCreateTime() {
        return this.zCreateTime;
    }

    @Override
    public SysTabular setZCreateTime(LocalDateTime zCreateTime) {
        this.zCreateTime = zCreateTime;
        return this;
    }

    @Override
    public String getZUpdateBy() {
        return this.zUpdateBy;
    }

    @Override
    public SysTabular setZUpdateBy(String zUpdateBy) {
        this.zUpdateBy = zUpdateBy;
        return this;
    }

    @Override
    public LocalDateTime getZUpdateTime() {
        return this.zUpdateTime;
    }

    @Override
    public SysTabular setZUpdateTime(LocalDateTime zUpdateTime) {
        this.zUpdateTime = zUpdateTime;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SysTabular (");

        sb.append(pkId);
        sb.append(", ").append(tComment);
        sb.append(", ").append(sName);
        sb.append(", ").append(sCode);
        sb.append(", ").append(sSerial);
        sb.append(", ").append(sType);
        sb.append(", ").append(jConfig);
        sb.append(", ").append(iOrder);
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
    public void from(ISysTabular from) {
        setPkId(from.getPkId());
        setTComment(from.getTComment());
        setSName(from.getSName());
        setSCode(from.getSCode());
        setSSerial(from.getSSerial());
        setSType(from.getSType());
        setJConfig(from.getJConfig());
        setIOrder(from.getIOrder());
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
    public <E extends ISysTabular> E into(E into) {
        into.from(this);
        return into;
    }

    public SysTabular(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
