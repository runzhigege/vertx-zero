/*
 * This file is generated by jOOQ.
*/
package up.zero.domain.tables.interfaces;


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
public interface ISRole extends Serializable {

    /**
     * Setter for <code>UP_ARES.S_ROLE.KEY</code>. 「key」- 角色ID
     */
    public ISRole setKey(String value);

    /**
     * Getter for <code>UP_ARES.S_ROLE.KEY</code>. 「key」- 角色ID
     */
    public String getKey();

    /**
     * Setter for <code>UP_ARES.S_ROLE.NAME</code>. 「name」- 角色名称
     */
    public ISRole setName(String value);

    /**
     * Getter for <code>UP_ARES.S_ROLE.NAME</code>. 「name」- 角色名称
     */
    public String getName();

    /**
     * Setter for <code>UP_ARES.S_ROLE.CODE</code>. 「code」- 角色系统名
     */
    public ISRole setCode(String value);

    /**
     * Getter for <code>UP_ARES.S_ROLE.CODE</code>. 「code」- 角色系统名
     */
    public String getCode();

    /**
     * Setter for <code>UP_ARES.S_ROLE.SUPER</code>. 「super」- 是否具有定制权限？
     */
    public ISRole setSuper(Boolean value);

    /**
     * Getter for <code>UP_ARES.S_ROLE.SUPER</code>. 「super」- 是否具有定制权限？
     */
    public Boolean getSuper();

    /**
     * Setter for <code>UP_ARES.S_ROLE.COMMENT</code>. 「comment」- 角色备注信息
     */
    public ISRole setComment(String value);

    /**
     * Getter for <code>UP_ARES.S_ROLE.COMMENT</code>. 「comment」- 角色备注信息
     */
    public String getComment();

    /**
     * Setter for <code>UP_ARES.S_ROLE.SIGMA</code>. 「sigma」- 角色绑定的统一标识
     */
    public ISRole setSigma(String value);

    /**
     * Getter for <code>UP_ARES.S_ROLE.SIGMA</code>. 「sigma」- 角色绑定的统一标识
     */
    public String getSigma();

    /**
     * Setter for <code>UP_ARES.S_ROLE.LANGUAGE</code>. 「language」- 使用的语言
     */
    public ISRole setLanguage(String value);

    /**
     * Getter for <code>UP_ARES.S_ROLE.LANGUAGE</code>. 「language」- 使用的语言
     */
    public String getLanguage();

    /**
     * Setter for <code>UP_ARES.S_ROLE.ACTIVE</code>. 「active」- 是否启用
     */
    public ISRole setActive(Boolean value);

    /**
     * Getter for <code>UP_ARES.S_ROLE.ACTIVE</code>. 「active」- 是否启用
     */
    public Boolean getActive();

    /**
     * Setter for <code>UP_ARES.S_ROLE.METADATA</code>. 「metadata」- 附加配置数据
     */
    public ISRole setMetadata(String value);

    /**
     * Getter for <code>UP_ARES.S_ROLE.METADATA</code>. 「metadata」- 附加配置数据
     */
    public String getMetadata();

    /**
     * Setter for <code>UP_ARES.S_ROLE.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public ISRole setCreatedAt(LocalDateTime value);

    /**
     * Getter for <code>UP_ARES.S_ROLE.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public LocalDateTime getCreatedAt();

    /**
     * Setter for <code>UP_ARES.S_ROLE.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public ISRole setCreatedBy(String value);

    /**
     * Getter for <code>UP_ARES.S_ROLE.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public String getCreatedBy();

    /**
     * Setter for <code>UP_ARES.S_ROLE.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public ISRole setUpdatedAt(LocalDateTime value);

    /**
     * Getter for <code>UP_ARES.S_ROLE.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public LocalDateTime getUpdatedAt();

    /**
     * Setter for <code>UP_ARES.S_ROLE.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public ISRole setUpdatedBy(String value);

    /**
     * Getter for <code>UP_ARES.S_ROLE.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public String getUpdatedBy();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface ISRole
     */
    public void from(up.zero.domain.tables.interfaces.ISRole from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface ISRole
     */
    public <E extends up.zero.domain.tables.interfaces.ISRole> E into(E into);

    default ISRole fromJson(io.vertx.core.json.JsonObject json) {
        setKey(json.getString("KEY"));
        setName(json.getString("NAME"));
        setCode(json.getString("CODE"));
        setSuper(json.getBoolean("SUPER"));
        setComment(json.getString("COMMENT"));
        setSigma(json.getString("SIGMA"));
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
        json.put("NAME",getName());
        json.put("CODE",getCode());
        json.put("SUPER",getSuper());
        json.put("COMMENT",getComment());
        json.put("SIGMA",getSigma());
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
