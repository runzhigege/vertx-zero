/*
 * This file is generated by jOOQ.
*/
package com.htl.domain.tables.pojos;


import com.htl.domain.tables.interfaces.ISysNumber;

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
public class SysNumber implements ISysNumber {

    private static final long serialVersionUID = -780367877;

    private String        pkId;
    private Long          lCurrent;
    private String        sCode;
    private String        sComment;
    private String        sFormat;
    private String        sIdentifier;
    private String        sPrefix;
    private String        sSuffix;
    private String        sTime;
    private String        jConfig;
    private Integer       iLength;
    private Integer       iStep;
    private Boolean       isDecrement;
    private Boolean       isActive;
    private String        zSigma;
    private String        zLanguage;
    private String        zCreateBy;
    private LocalDateTime zCreateTime;
    private String        zUpdateBy;
    private LocalDateTime zUpdateTime;

    public SysNumber() {}

    public SysNumber(SysNumber value) {
        this.pkId = value.pkId;
        this.lCurrent = value.lCurrent;
        this.sCode = value.sCode;
        this.sComment = value.sComment;
        this.sFormat = value.sFormat;
        this.sIdentifier = value.sIdentifier;
        this.sPrefix = value.sPrefix;
        this.sSuffix = value.sSuffix;
        this.sTime = value.sTime;
        this.jConfig = value.jConfig;
        this.iLength = value.iLength;
        this.iStep = value.iStep;
        this.isDecrement = value.isDecrement;
        this.isActive = value.isActive;
        this.zSigma = value.zSigma;
        this.zLanguage = value.zLanguage;
        this.zCreateBy = value.zCreateBy;
        this.zCreateTime = value.zCreateTime;
        this.zUpdateBy = value.zUpdateBy;
        this.zUpdateTime = value.zUpdateTime;
    }

    public SysNumber(
        String        pkId,
        Long          lCurrent,
        String        sCode,
        String        sComment,
        String        sFormat,
        String        sIdentifier,
        String        sPrefix,
        String        sSuffix,
        String        sTime,
        String        jConfig,
        Integer       iLength,
        Integer       iStep,
        Boolean       isDecrement,
        Boolean       isActive,
        String        zSigma,
        String        zLanguage,
        String        zCreateBy,
        LocalDateTime zCreateTime,
        String        zUpdateBy,
        LocalDateTime zUpdateTime
    ) {
        this.pkId = pkId;
        this.lCurrent = lCurrent;
        this.sCode = sCode;
        this.sComment = sComment;
        this.sFormat = sFormat;
        this.sIdentifier = sIdentifier;
        this.sPrefix = sPrefix;
        this.sSuffix = sSuffix;
        this.sTime = sTime;
        this.jConfig = jConfig;
        this.iLength = iLength;
        this.iStep = iStep;
        this.isDecrement = isDecrement;
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
    public SysNumber setPkId(String pkId) {
        this.pkId = pkId;
        return this;
    }

    @Override
    public Long getLCurrent() {
        return this.lCurrent;
    }

    @Override
    public SysNumber setLCurrent(Long lCurrent) {
        this.lCurrent = lCurrent;
        return this;
    }

    @Override
    public String getSCode() {
        return this.sCode;
    }

    @Override
    public SysNumber setSCode(String sCode) {
        this.sCode = sCode;
        return this;
    }

    @Override
    public String getSComment() {
        return this.sComment;
    }

    @Override
    public SysNumber setSComment(String sComment) {
        this.sComment = sComment;
        return this;
    }

    @Override
    public String getSFormat() {
        return this.sFormat;
    }

    @Override
    public SysNumber setSFormat(String sFormat) {
        this.sFormat = sFormat;
        return this;
    }

    @Override
    public String getSIdentifier() {
        return this.sIdentifier;
    }

    @Override
    public SysNumber setSIdentifier(String sIdentifier) {
        this.sIdentifier = sIdentifier;
        return this;
    }

    @Override
    public String getSPrefix() {
        return this.sPrefix;
    }

    @Override
    public SysNumber setSPrefix(String sPrefix) {
        this.sPrefix = sPrefix;
        return this;
    }

    @Override
    public String getSSuffix() {
        return this.sSuffix;
    }

    @Override
    public SysNumber setSSuffix(String sSuffix) {
        this.sSuffix = sSuffix;
        return this;
    }

    @Override
    public String getSTime() {
        return this.sTime;
    }

    @Override
    public SysNumber setSTime(String sTime) {
        this.sTime = sTime;
        return this;
    }

    @Override
    public String getJConfig() {
        return this.jConfig;
    }

    @Override
    public SysNumber setJConfig(String jConfig) {
        this.jConfig = jConfig;
        return this;
    }

    @Override
    public Integer getILength() {
        return this.iLength;
    }

    @Override
    public SysNumber setILength(Integer iLength) {
        this.iLength = iLength;
        return this;
    }

    @Override
    public Integer getIStep() {
        return this.iStep;
    }

    @Override
    public SysNumber setIStep(Integer iStep) {
        this.iStep = iStep;
        return this;
    }

    @Override
    public Boolean getIsDecrement() {
        return this.isDecrement;
    }

    @Override
    public SysNumber setIsDecrement(Boolean isDecrement) {
        this.isDecrement = isDecrement;
        return this;
    }

    @Override
    public Boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public SysNumber setIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    @Override
    public String getZSigma() {
        return this.zSigma;
    }

    @Override
    public SysNumber setZSigma(String zSigma) {
        this.zSigma = zSigma;
        return this;
    }

    @Override
    public String getZLanguage() {
        return this.zLanguage;
    }

    @Override
    public SysNumber setZLanguage(String zLanguage) {
        this.zLanguage = zLanguage;
        return this;
    }

    @Override
    public String getZCreateBy() {
        return this.zCreateBy;
    }

    @Override
    public SysNumber setZCreateBy(String zCreateBy) {
        this.zCreateBy = zCreateBy;
        return this;
    }

    @Override
    public LocalDateTime getZCreateTime() {
        return this.zCreateTime;
    }

    @Override
    public SysNumber setZCreateTime(LocalDateTime zCreateTime) {
        this.zCreateTime = zCreateTime;
        return this;
    }

    @Override
    public String getZUpdateBy() {
        return this.zUpdateBy;
    }

    @Override
    public SysNumber setZUpdateBy(String zUpdateBy) {
        this.zUpdateBy = zUpdateBy;
        return this;
    }

    @Override
    public LocalDateTime getZUpdateTime() {
        return this.zUpdateTime;
    }

    @Override
    public SysNumber setZUpdateTime(LocalDateTime zUpdateTime) {
        this.zUpdateTime = zUpdateTime;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SysNumber (");

        sb.append(pkId);
        sb.append(", ").append(lCurrent);
        sb.append(", ").append(sCode);
        sb.append(", ").append(sComment);
        sb.append(", ").append(sFormat);
        sb.append(", ").append(sIdentifier);
        sb.append(", ").append(sPrefix);
        sb.append(", ").append(sSuffix);
        sb.append(", ").append(sTime);
        sb.append(", ").append(jConfig);
        sb.append(", ").append(iLength);
        sb.append(", ").append(iStep);
        sb.append(", ").append(isDecrement);
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
    public void from(ISysNumber from) {
        setPkId(from.getPkId());
        setLCurrent(from.getLCurrent());
        setSCode(from.getSCode());
        setSComment(from.getSComment());
        setSFormat(from.getSFormat());
        setSIdentifier(from.getSIdentifier());
        setSPrefix(from.getSPrefix());
        setSSuffix(from.getSSuffix());
        setSTime(from.getSTime());
        setJConfig(from.getJConfig());
        setILength(from.getILength());
        setIStep(from.getIStep());
        setIsDecrement(from.getIsDecrement());
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
    public <E extends ISysNumber> E into(E into) {
        into.from(this);
        return into;
    }

    public SysNumber(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
