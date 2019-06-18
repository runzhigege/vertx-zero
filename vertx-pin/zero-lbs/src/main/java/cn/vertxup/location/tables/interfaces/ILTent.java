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
public interface ILTent extends Serializable {

    /**
     * Setter for <code>DB_ETERNAL.L_TENT.KEY</code>. 「key」- 主键
     */
    public ILTent setKey(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_TENT.KEY</code>. 「key」- 主键
     */
    public String getKey();

    /**
     * Setter for <code>DB_ETERNAL.L_TENT.NAME</code>. 「name」- 名称
     */
    public ILTent setName(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_TENT.NAME</code>. 「name」- 名称
     */
    public String getName();

    /**
     * Setter for <code>DB_ETERNAL.L_TENT.CODE</code>. 「code」- 编码
     */
    public ILTent setCode(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_TENT.CODE</code>. 「code」- 编码
     */
    public String getCode();

    /**
     * Setter for <code>DB_ETERNAL.L_TENT.CONTACT_PHONE</code>. 「contactPhone」- 联系电话
     */
    public ILTent setContactPhone(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_TENT.CONTACT_PHONE</code>. 「contactPhone」- 联系电话
     */
    public String getContactPhone();

    /**
     * Setter for <code>DB_ETERNAL.L_TENT.CONTACT_NAME</code>. 「contactName」- 联系人姓名
     */
    public ILTent setContactName(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_TENT.CONTACT_NAME</code>. 「contactName」- 联系人姓名
     */
    public String getContactName();

    /**
     * Setter for <code>DB_ETERNAL.L_TENT.METADATA</code>. 「metadata」- 附加配置
     */
    public ILTent setMetadata(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_TENT.METADATA</code>. 「metadata」- 附加配置
     */
    public String getMetadata();

    /**
     * Setter for <code>DB_ETERNAL.L_TENT.ORDER</code>. 「order」- 排序
     */
    public ILTent setOrder(Integer value);

    /**
     * Getter for <code>DB_ETERNAL.L_TENT.ORDER</code>. 「order」- 排序
     */
    public Integer getOrder();

    /**
     * Setter for <code>DB_ETERNAL.L_TENT.LOCATION_ID</code>. 「locationId」- 关联地址ID
     */
    public ILTent setLocationId(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_TENT.LOCATION_ID</code>. 「locationId」- 关联地址ID
     */
    public String getLocationId();

    /**
     * Setter for <code>DB_ETERNAL.L_TENT.YARD_ID</code>. 「yardId」- 关联小区ID
     */
    public ILTent setYardId(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_TENT.YARD_ID</code>. 「yardId」- 关联小区ID
     */
    public String getYardId();

    /**
     * Setter for <code>DB_ETERNAL.L_TENT.ACTIVE</code>. 「active」- 是否启用
     */
    public ILTent setActive(Boolean value);

    /**
     * Getter for <code>DB_ETERNAL.L_TENT.ACTIVE</code>. 「active」- 是否启用
     */
    public Boolean getActive();

    /**
     * Setter for <code>DB_ETERNAL.L_TENT.SIGMA</code>. 「sigma」- 统一标识
     */
    public ILTent setSigma(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_TENT.SIGMA</code>. 「sigma」- 统一标识
     */
    public String getSigma();

    /**
     * Setter for <code>DB_ETERNAL.L_TENT.LANGUAGE</code>. 「language」- 使用的语言
     */
    public ILTent setLanguage(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_TENT.LANGUAGE</code>. 「language」- 使用的语言
     */
    public String getLanguage();

    /**
     * Setter for <code>DB_ETERNAL.L_TENT.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public ILTent setCreatedAt(LocalDateTime value);

    /**
     * Getter for <code>DB_ETERNAL.L_TENT.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public LocalDateTime getCreatedAt();

    /**
     * Setter for <code>DB_ETERNAL.L_TENT.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public ILTent setCreatedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_TENT.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public String getCreatedBy();

    /**
     * Setter for <code>DB_ETERNAL.L_TENT.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public ILTent setUpdatedAt(LocalDateTime value);

    /**
     * Getter for <code>DB_ETERNAL.L_TENT.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public LocalDateTime getUpdatedAt();

    /**
     * Setter for <code>DB_ETERNAL.L_TENT.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public ILTent setUpdatedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.L_TENT.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public String getUpdatedBy();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface ILTent
     */
    public void from(cn.vertxup.location.tables.interfaces.ILTent from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface ILTent
     */
    public <E extends cn.vertxup.location.tables.interfaces.ILTent> E into(E into);

    default ILTent fromJson(io.vertx.core.json.JsonObject json) {
        setKey(json.getString("KEY"));
        setName(json.getString("NAME"));
        setCode(json.getString("CODE"));
        setContactPhone(json.getString("CONTACT_PHONE"));
        setContactName(json.getString("CONTACT_NAME"));
        setMetadata(json.getString("METADATA"));
        setOrder(json.getInteger("ORDER"));
        setLocationId(json.getString("LOCATION_ID"));
        setYardId(json.getString("YARD_ID"));
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
        json.put("CONTACT_PHONE",getContactPhone());
        json.put("CONTACT_NAME",getContactName());
        json.put("METADATA",getMetadata());
        json.put("ORDER",getOrder());
        json.put("LOCATION_ID",getLocationId());
        json.put("YARD_ID",getYardId());
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
