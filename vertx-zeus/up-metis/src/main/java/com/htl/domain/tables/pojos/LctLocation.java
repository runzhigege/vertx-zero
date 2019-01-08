/*
 * This up.god.file is generated by jOOQ.
 */
package com.htl.domain.tables.pojos;


import com.htl.domain.tables.interfaces.ILctLocation;

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
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class LctLocation implements ILctLocation {

    private static final long serialVersionUID = 779451477;

    private String pkId;
    private String sName;
    private String sCode;
    private String sAddress;
    private String sCity;
    private String sCountry;
    private String sDistinct;
    private String sFullname;
    private String sState;
    private String sStreet1;
    private String sStreet2;
    private String sStreet3;
    private String sZip;
    private String jConfig;
    private String rDistinctId;
    private Boolean isActive;
    private String zSigma;
    private String zLanguage;
    private String zCreateBy;
    private LocalDateTime zCreateTime;
    private String zUpdateBy;
    private LocalDateTime zUpdateTime;

    public LctLocation() {
    }

    public LctLocation(LctLocation value) {
        this.pkId = value.pkId;
        this.sName = value.sName;
        this.sCode = value.sCode;
        this.sAddress = value.sAddress;
        this.sCity = value.sCity;
        this.sCountry = value.sCountry;
        this.sDistinct = value.sDistinct;
        this.sFullname = value.sFullname;
        this.sState = value.sState;
        this.sStreet1 = value.sStreet1;
        this.sStreet2 = value.sStreet2;
        this.sStreet3 = value.sStreet3;
        this.sZip = value.sZip;
        this.jConfig = value.jConfig;
        this.rDistinctId = value.rDistinctId;
        this.isActive = value.isActive;
        this.zSigma = value.zSigma;
        this.zLanguage = value.zLanguage;
        this.zCreateBy = value.zCreateBy;
        this.zCreateTime = value.zCreateTime;
        this.zUpdateBy = value.zUpdateBy;
        this.zUpdateTime = value.zUpdateTime;
    }

    public LctLocation(
            String pkId,
            String sName,
            String sCode,
            String sAddress,
            String sCity,
            String sCountry,
            String sDistinct,
            String sFullname,
            String sState,
            String sStreet1,
            String sStreet2,
            String sStreet3,
            String sZip,
            String jConfig,
            String rDistinctId,
            Boolean isActive,
            String zSigma,
            String zLanguage,
            String zCreateBy,
            LocalDateTime zCreateTime,
            String zUpdateBy,
            LocalDateTime zUpdateTime
    ) {
        this.pkId = pkId;
        this.sName = sName;
        this.sCode = sCode;
        this.sAddress = sAddress;
        this.sCity = sCity;
        this.sCountry = sCountry;
        this.sDistinct = sDistinct;
        this.sFullname = sFullname;
        this.sState = sState;
        this.sStreet1 = sStreet1;
        this.sStreet2 = sStreet2;
        this.sStreet3 = sStreet3;
        this.sZip = sZip;
        this.jConfig = jConfig;
        this.rDistinctId = rDistinctId;
        this.isActive = isActive;
        this.zSigma = zSigma;
        this.zLanguage = zLanguage;
        this.zCreateBy = zCreateBy;
        this.zCreateTime = zCreateTime;
        this.zUpdateBy = zUpdateBy;
        this.zUpdateTime = zUpdateTime;
    }

    public LctLocation(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }

    @Override
    public String getPkId() {
        return this.pkId;
    }

    @Override
    public LctLocation setPkId(String pkId) {
        this.pkId = pkId;
        return this;
    }

    @Override
    public String getSName() {
        return this.sName;
    }

    @Override
    public LctLocation setSName(String sName) {
        this.sName = sName;
        return this;
    }

    @Override
    public String getSCode() {
        return this.sCode;
    }

    @Override
    public LctLocation setSCode(String sCode) {
        this.sCode = sCode;
        return this;
    }

    @Override
    public String getSAddress() {
        return this.sAddress;
    }

    @Override
    public LctLocation setSAddress(String sAddress) {
        this.sAddress = sAddress;
        return this;
    }

    @Override
    public String getSCity() {
        return this.sCity;
    }

    @Override
    public LctLocation setSCity(String sCity) {
        this.sCity = sCity;
        return this;
    }

    @Override
    public String getSCountry() {
        return this.sCountry;
    }

    @Override
    public LctLocation setSCountry(String sCountry) {
        this.sCountry = sCountry;
        return this;
    }

    @Override
    public String getSDistinct() {
        return this.sDistinct;
    }

    @Override
    public LctLocation setSDistinct(String sDistinct) {
        this.sDistinct = sDistinct;
        return this;
    }

    @Override
    public String getSFullname() {
        return this.sFullname;
    }

    @Override
    public LctLocation setSFullname(String sFullname) {
        this.sFullname = sFullname;
        return this;
    }

    @Override
    public String getSState() {
        return this.sState;
    }

    @Override
    public LctLocation setSState(String sState) {
        this.sState = sState;
        return this;
    }

    @Override
    public String getSStreet1() {
        return this.sStreet1;
    }

    @Override
    public LctLocation setSStreet1(String sStreet1) {
        this.sStreet1 = sStreet1;
        return this;
    }

    @Override
    public String getSStreet2() {
        return this.sStreet2;
    }

    @Override
    public LctLocation setSStreet2(String sStreet2) {
        this.sStreet2 = sStreet2;
        return this;
    }

    @Override
    public String getSStreet3() {
        return this.sStreet3;
    }

    @Override
    public LctLocation setSStreet3(String sStreet3) {
        this.sStreet3 = sStreet3;
        return this;
    }

    @Override
    public String getSZip() {
        return this.sZip;
    }

    @Override
    public LctLocation setSZip(String sZip) {
        this.sZip = sZip;
        return this;
    }

    @Override
    public String getJConfig() {
        return this.jConfig;
    }

    @Override
    public LctLocation setJConfig(String jConfig) {
        this.jConfig = jConfig;
        return this;
    }

    @Override
    public String getRDistinctId() {
        return this.rDistinctId;
    }

    @Override
    public LctLocation setRDistinctId(String rDistinctId) {
        this.rDistinctId = rDistinctId;
        return this;
    }

    @Override
    public Boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public LctLocation setIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    @Override
    public String getZSigma() {
        return this.zSigma;
    }

    @Override
    public LctLocation setZSigma(String zSigma) {
        this.zSigma = zSigma;
        return this;
    }

    @Override
    public String getZLanguage() {
        return this.zLanguage;
    }

    @Override
    public LctLocation setZLanguage(String zLanguage) {
        this.zLanguage = zLanguage;
        return this;
    }

    @Override
    public String getZCreateBy() {
        return this.zCreateBy;
    }

    @Override
    public LctLocation setZCreateBy(String zCreateBy) {
        this.zCreateBy = zCreateBy;
        return this;
    }

    @Override
    public LocalDateTime getZCreateTime() {
        return this.zCreateTime;
    }

    @Override
    public LctLocation setZCreateTime(LocalDateTime zCreateTime) {
        this.zCreateTime = zCreateTime;
        return this;
    }

    @Override
    public String getZUpdateBy() {
        return this.zUpdateBy;
    }

    @Override
    public LctLocation setZUpdateBy(String zUpdateBy) {
        this.zUpdateBy = zUpdateBy;
        return this;
    }

    @Override
    public LocalDateTime getZUpdateTime() {
        return this.zUpdateTime;
    }

    @Override
    public LctLocation setZUpdateTime(LocalDateTime zUpdateTime) {
        this.zUpdateTime = zUpdateTime;
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LctLocation (");

        sb.append(pkId);
        sb.append(", ").append(sName);
        sb.append(", ").append(sCode);
        sb.append(", ").append(sAddress);
        sb.append(", ").append(sCity);
        sb.append(", ").append(sCountry);
        sb.append(", ").append(sDistinct);
        sb.append(", ").append(sFullname);
        sb.append(", ").append(sState);
        sb.append(", ").append(sStreet1);
        sb.append(", ").append(sStreet2);
        sb.append(", ").append(sStreet3);
        sb.append(", ").append(sZip);
        sb.append(", ").append(jConfig);
        sb.append(", ").append(rDistinctId);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(ILctLocation from) {
        setPkId(from.getPkId());
        setSName(from.getSName());
        setSCode(from.getSCode());
        setSAddress(from.getSAddress());
        setSCity(from.getSCity());
        setSCountry(from.getSCountry());
        setSDistinct(from.getSDistinct());
        setSFullname(from.getSFullname());
        setSState(from.getSState());
        setSStreet1(from.getSStreet1());
        setSStreet2(from.getSStreet2());
        setSStreet3(from.getSStreet3());
        setSZip(from.getSZip());
        setJConfig(from.getJConfig());
        setRDistinctId(from.getRDistinctId());
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
    public <E extends ILctLocation> E into(E into) {
        into.from(this);
        return into;
    }
}
