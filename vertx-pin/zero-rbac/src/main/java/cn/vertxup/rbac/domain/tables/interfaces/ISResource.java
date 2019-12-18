/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.rbac.domain.tables.interfaces;


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
public interface ISResource extends Serializable {

    /**
     * Setter for <code>DB_ETERNAL.S_RESOURCE.KEY</code>. 「key」- 资源对应的ID
     */
    public ISResource setKey(String value);

    /**
     * Getter for <code>DB_ETERNAL.S_RESOURCE.KEY</code>. 「key」- 资源对应的ID
     */
    public String getKey();

    /**
     * Setter for <code>DB_ETERNAL.S_RESOURCE.CODE</code>. 「code」- 资源编号
     */
    public ISResource setCode(String value);

    /**
     * Getter for <code>DB_ETERNAL.S_RESOURCE.CODE</code>. 「code」- 资源编号
     */
    public String getCode();

    /**
     * Setter for <code>DB_ETERNAL.S_RESOURCE.NAME</code>. 「name」- 资源名称
     */
    public ISResource setName(String value);

    /**
     * Getter for <code>DB_ETERNAL.S_RESOURCE.NAME</code>. 「name」- 资源名称
     */
    public String getName();

    /**
     * Setter for <code>DB_ETERNAL.S_RESOURCE.COMMENT</code>. 「comment」- 备注信息
     */
    public ISResource setComment(String value);

    /**
     * Getter for <code>DB_ETERNAL.S_RESOURCE.COMMENT</code>. 「comment」- 备注信息
     */
    public String getComment();

    /**
     * Setter for <code>DB_ETERNAL.S_RESOURCE.LEVEL</code>. 「level」- 资源需求级别
     */
    public ISResource setLevel(Integer value);

    /**
     * Getter for <code>DB_ETERNAL.S_RESOURCE.LEVEL</code>. 「level」- 资源需求级别
     */
    public Integer getLevel();

    /**
     * Setter for <code>DB_ETERNAL.S_RESOURCE.MODE_ROLE</code>. 「modeRole」- 该资源查找角色的模式
     */
    public ISResource setModeRole(String value);

    /**
     * Getter for <code>DB_ETERNAL.S_RESOURCE.MODE_ROLE</code>. 「modeRole」- 该资源查找角色的模式
     */
    public String getModeRole();

    /**
     * Setter for <code>DB_ETERNAL.S_RESOURCE.MODE_GROUP</code>. 「modeGroup」- 该资源查找组的模式
     */
    public ISResource setModeGroup(String value);

    /**
     * Getter for <code>DB_ETERNAL.S_RESOURCE.MODE_GROUP</code>. 「modeGroup」- 该资源查找组的模式
     */
    public String getModeGroup();

    /**
     * Setter for <code>DB_ETERNAL.S_RESOURCE.MODE_TREE</code>. 「modeTree」- 该资源处理树（用户组）的模式
     */
    public ISResource setModeTree(String value);

    /**
     * Getter for <code>DB_ETERNAL.S_RESOURCE.MODE_TREE</code>. 「modeTree」- 该资源处理树（用户组）的模式
     */
    public String getModeTree();

    /**
     * Setter for <code>DB_ETERNAL.S_RESOURCE.SIGMA</code>. 「sigma」- 统一标识
     */
    public ISResource setSigma(String value);

    /**
     * Getter for <code>DB_ETERNAL.S_RESOURCE.SIGMA</code>. 「sigma」- 统一标识
     */
    public String getSigma();

    /**
     * Setter for <code>DB_ETERNAL.S_RESOURCE.CATEGORY</code>. 「category」- 资源分类
     */
    public ISResource setCategory(String value);

    /**
     * Getter for <code>DB_ETERNAL.S_RESOURCE.CATEGORY</code>. 「category」- 资源分类
     */
    public String getCategory();

    /**
     * Setter for <code>DB_ETERNAL.S_RESOURCE.LANGUAGE</code>. 「language」- 使用的语言
     */
    public ISResource setLanguage(String value);

    /**
     * Getter for <code>DB_ETERNAL.S_RESOURCE.LANGUAGE</code>. 「language」- 使用的语言
     */
    public String getLanguage();

    /**
     * Setter for <code>DB_ETERNAL.S_RESOURCE.ACTIVE</code>. 「active」- 是否启用
     */
    public ISResource setActive(Boolean value);

    /**
     * Getter for <code>DB_ETERNAL.S_RESOURCE.ACTIVE</code>. 「active」- 是否启用
     */
    public Boolean getActive();

    /**
     * Setter for <code>DB_ETERNAL.S_RESOURCE.METADATA</code>. 「metadata」- 附加配置数据
     */
    public ISResource setMetadata(String value);

    /**
     * Getter for <code>DB_ETERNAL.S_RESOURCE.METADATA</code>. 「metadata」- 附加配置数据
     */
    public String getMetadata();

    /**
     * Setter for <code>DB_ETERNAL.S_RESOURCE.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public ISResource setCreatedAt(LocalDateTime value);

    /**
     * Getter for <code>DB_ETERNAL.S_RESOURCE.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public LocalDateTime getCreatedAt();

    /**
     * Setter for <code>DB_ETERNAL.S_RESOURCE.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public ISResource setCreatedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.S_RESOURCE.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public String getCreatedBy();

    /**
     * Setter for <code>DB_ETERNAL.S_RESOURCE.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public ISResource setUpdatedAt(LocalDateTime value);

    /**
     * Getter for <code>DB_ETERNAL.S_RESOURCE.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public LocalDateTime getUpdatedAt();

    /**
     * Setter for <code>DB_ETERNAL.S_RESOURCE.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public ISResource setUpdatedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.S_RESOURCE.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public String getUpdatedBy();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface ISResource
     */
    public void from(cn.vertxup.rbac.domain.tables.interfaces.ISResource from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface ISResource
     */
    public <E extends cn.vertxup.rbac.domain.tables.interfaces.ISResource> E into(E into);

    default ISResource fromJson(io.vertx.core.json.JsonObject json) {
        setKey(json.getString("KEY"));
        setCode(json.getString("CODE"));
        setName(json.getString("NAME"));
        setComment(json.getString("COMMENT"));
        setLevel(json.getInteger("LEVEL"));
        setModeRole(json.getString("MODE_ROLE"));
        setModeGroup(json.getString("MODE_GROUP"));
        setModeTree(json.getString("MODE_TREE"));
        setSigma(json.getString("SIGMA"));
        setCategory(json.getString("CATEGORY"));
        setLanguage(json.getString("LANGUAGE"));
        setActive(json.getBoolean("ACTIVE"));
        setMetadata(json.getString("METADATA"));
        // Omitting unrecognized type java.time.LocalDateTime for column CREATED_AT!
        setCreatedBy(json.getString("CREATED_BY"));
        // Omitting unrecognized type java.time.LocalDateTime for column UPDATED_AT!
        setUpdatedBy(json.getString("UPDATED_BY"));
        return this;
    }


    default io.vertx.core.json.JsonObject toJson() {
        io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("KEY",getKey());
        json.put("CODE",getCode());
        json.put("NAME",getName());
        json.put("COMMENT",getComment());
        json.put("LEVEL",getLevel());
        json.put("MODE_ROLE",getModeRole());
        json.put("MODE_GROUP",getModeGroup());
        json.put("MODE_TREE",getModeTree());
        json.put("SIGMA",getSigma());
        json.put("CATEGORY",getCategory());
        json.put("LANGUAGE",getLanguage());
        json.put("ACTIVE",getActive());
        json.put("METADATA",getMetadata());
        // Omitting unrecognized type java.time.LocalDateTime for column CREATED_AT!
        json.put("CREATED_BY",getCreatedBy());
        // Omitting unrecognized type java.time.LocalDateTime for column UPDATED_AT!
        json.put("UPDATED_BY",getUpdatedBy());
        return json;
    }

}
