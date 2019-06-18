/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.location.tables.interfaces;


import java.io.Serializable;
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
public interface ILLocation extends Serializable {

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.KEY</code>. 「key」- 主键
     */
    public ILLocation setKey(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.KEY</code>. 「key」- 主键
     */
    public String getKey();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.NAME</code>. 「name」- 名称
     */
    public ILLocation setName(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.NAME</code>. 「name」- 名称
     */
    public String getName();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.CODE</code>. 「code」- 编码
     */
    public ILLocation setCode(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.CODE</code>. 「code」- 编码
     */
    public String getCode();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.ADDRESS</code>. 「address」- 详细地址
     */
    public ILLocation setAddress(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.ADDRESS</code>. 「address」- 详细地址
     */
    public String getAddress();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.CITY</code>. 「city」- 3.城市
     */
    public ILLocation setCity(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.CITY</code>. 「city」- 3.城市
     */
    public String getCity();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.COUNTRY</code>. 「country」- 1.国家
     */
    public ILLocation setCountry(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.COUNTRY</code>. 「country」- 1.国家
     */
    public String getCountry();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.REGION</code>. 「region」- 4.区域
     */
    public ILLocation setRegion(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.REGION</code>. 「region」- 4.区域
     */
    public String getRegion();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.FULL_NAME</code>. 「fullName」- 地址全称
     */
    public ILLocation setFullName(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.FULL_NAME</code>. 「fullName」- 地址全称
     */
    public String getFullName();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.STATE</code>. 「state」- 2.省会
     */
    public ILLocation setState(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.STATE</code>. 「state」- 2.省会
     */
    public String getState();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.STREET1</code>. 「street1」- 街道1
     */
    public ILLocation setStreet1(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.STREET1</code>. 「street1」- 街道1
     */
    public String getStreet1();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.STREET2</code>. 「street2」- 街道2
     */
    public ILLocation setStreet2(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.STREET2</code>. 「street2」- 街道2
     */
    public String getStreet2();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.STREET3</code>. 「street3」- 街道3
     */
    public ILLocation setStreet3(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.STREET3</code>. 「street3」- 街道3
     */
    public String getStreet3();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.POSTAL</code>. 「postal」- 邮政编码
     */
    public ILLocation setPostal(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.POSTAL</code>. 「postal」- 邮政编码
     */
    public String getPostal();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.METADATA</code>. 「metadata」- 附加配置
     */
    public ILLocation setMetadata(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.METADATA</code>. 「metadata」- 附加配置
     */
    public String getMetadata();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.REGION_ID</code>. 「regionId」- 区域ID
     */
    public ILLocation setRegionId(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.REGION_ID</code>. 「regionId」- 区域ID
     */
    public String getRegionId();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.ACTIVE</code>. 「active」- 是否启用
     */
    public ILLocation setActive(Boolean value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.ACTIVE</code>. 「active」- 是否启用
     */
    public Boolean getActive();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.SIGMA</code>. 「sigma」- 统一标识
     */
    public ILLocation setSigma(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.SIGMA</code>. 「sigma」- 统一标识
     */
    public String getSigma();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.LANGUAGE</code>. 「language」- 使用的语言
     */
    public ILLocation setLanguage(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.LANGUAGE</code>. 「language」- 使用的语言
     */
    public String getLanguage();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public ILLocation setCreatedAt(LocalDateTime value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public LocalDateTime getCreatedAt();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public ILLocation setCreatedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public String getCreatedBy();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public ILLocation setUpdatedAt(LocalDateTime value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public LocalDateTime getUpdatedAt();

    /**
     * Setter for <code>DB_ETERNAL.L_LOCATION.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public ILLocation setUpdatedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_LOCATION.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public String getUpdatedBy();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface ILLocation
     */
    public void from(cn.vertxup.location.tables.interfaces.ILLocation from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface ILLocation
     */
    public <E extends cn.vertxup.location.tables.interfaces.ILLocation> E into(E into);

    default ILLocation fromJson(io.vertx.core.json.JsonObject json) {
        setKey(json.getString("KEY"));
        setName(json.getString("NAME"));
        setCode(json.getString("CODE"));
        setAddress(json.getString("ADDRESS"));
        setCity(json.getString("CITY"));
        setCountry(json.getString("COUNTRY"));
        setRegion(json.getString("REGION"));
        setFullName(json.getString("FULL_NAME"));
        setState(json.getString("STATE"));
        setStreet1(json.getString("STREET1"));
        setStreet2(json.getString("STREET2"));
        setStreet3(json.getString("STREET3"));
        setPostal(json.getString("POSTAL"));
        setMetadata(json.getString("METADATA"));
        setRegionId(json.getString("REGION_ID"));
        setActive(json.getBoolean("ACTIVE"));
        setSigma(json.getString("SIGMA"));
        setLanguage(json.getString("LANGUAGE"));
        // Omitting unrecognized type java.time.LocalDateTime for column CREATED_AT!
        setCreatedBy(json.getString("CREATED_BY"));
        // Omitting unrecognized type java.time.LocalDateTime for column UPDATED_AT!
        setUpdatedBy(json.getString("UPDATED_BY"));
        return this;
    }


    default io.vertx.core.json.JsonObject toJson() {
        io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("KEY",getKey());
        json.put("NAME",getName());
        json.put("CODE",getCode());
        json.put("ADDRESS",getAddress());
        json.put("CITY",getCity());
        json.put("COUNTRY",getCountry());
        json.put("REGION",getRegion());
        json.put("FULL_NAME",getFullName());
        json.put("STATE",getState());
        json.put("STREET1",getStreet1());
        json.put("STREET2",getStreet2());
        json.put("STREET3",getStreet3());
        json.put("POSTAL",getPostal());
        json.put("METADATA",getMetadata());
        json.put("REGION_ID",getRegionId());
        json.put("ACTIVE",getActive());
        json.put("SIGMA",getSigma());
        json.put("LANGUAGE",getLanguage());
        // Omitting unrecognized type java.time.LocalDateTime for column CREATED_AT!
        json.put("CREATED_BY",getCreatedBy());
        // Omitting unrecognized type java.time.LocalDateTime for column UPDATED_AT!
        json.put("UPDATED_BY",getUpdatedBy());
        return json;
    }

}
