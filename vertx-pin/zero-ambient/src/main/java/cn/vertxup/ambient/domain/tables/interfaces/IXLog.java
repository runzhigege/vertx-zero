/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ambient.domain.tables.interfaces;


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
public interface IXLog extends Serializable {

    /**
     * Setter for <code>DB_ETERNAL.X_LOG.KEY</code>. 「key」- 日志的主键
     */
    public IXLog setKey(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_LOG.KEY</code>. 「key」- 日志的主键
     */
    public String getKey();

    /**
     * Setter for <code>DB_ETERNAL.X_LOG.TYPE</code>. 「type」- 日志的分类
     */
    public IXLog setType(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_LOG.TYPE</code>. 「type」- 日志的分类
     */
    public String getType();

    /**
     * Setter for <code>DB_ETERNAL.X_LOG.LEVEL</code>. 「level」- 日志级别：ERROR / WARN / INFO
     */
    public IXLog setLevel(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_LOG.LEVEL</code>. 「level」- 日志级别：ERROR / WARN / INFO
     */
    public String getLevel();

    /**
     * Setter for <code>DB_ETERNAL.X_LOG.INFO_STACK</code>. 「infoStack」- 堆栈信息
     */
    public IXLog setInfoStack(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_LOG.INFO_STACK</code>. 「infoStack」- 堆栈信息
     */
    public String getInfoStack();

    /**
     * Setter for <code>DB_ETERNAL.X_LOG.INFO_SYSTEM</code>. 「infoSystem」- 日志内容
     */
    public IXLog setInfoSystem(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_LOG.INFO_SYSTEM</code>. 「infoSystem」- 日志内容
     */
    public String getInfoSystem();

    /**
     * Setter for <code>DB_ETERNAL.X_LOG.INFO_READABLE</code>. 「infoReadable」- 日志的可读信息
     */
    public IXLog setInfoReadable(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_LOG.INFO_READABLE</code>. 「infoReadable」- 日志的可读信息
     */
    public String getInfoReadable();

    /**
     * Setter for <code>DB_ETERNAL.X_LOG.INFO_AT</code>. 「infoAt」- 日志记录时间
     */
    public IXLog setInfoAt(LocalDateTime value);

    /**
     * Getter for <code>DB_ETERNAL.X_LOG.INFO_AT</code>. 「infoAt」- 日志记录时间
     */
    public LocalDateTime getInfoAt();

    /**
     * Setter for <code>DB_ETERNAL.X_LOG.LOG_AGENT</code>. 「logAgent」- 记录日志的 agent 信息
     */
    public IXLog setLogAgent(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_LOG.LOG_AGENT</code>. 「logAgent」- 记录日志的 agent 信息
     */
    public String getLogAgent();

    /**
     * Setter for <code>DB_ETERNAL.X_LOG.LOG_IP</code>. 「logIp」- 日志扩展组件
     */
    public IXLog setLogIp(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_LOG.LOG_IP</code>. 「logIp」- 日志扩展组件
     */
    public String getLogIp();

    /**
     * Setter for <code>DB_ETERNAL.X_LOG.LOG_USER</code>. 「logUser」- 日志记录人
     */
    public IXLog setLogUser(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_LOG.LOG_USER</code>. 「logUser」- 日志记录人
     */
    public String getLogUser();

    /**
     * Setter for <code>DB_ETERNAL.X_LOG.ACTIVE</code>. 「active」- 是否启用
     */
    public IXLog setActive(Boolean value);

    /**
     * Getter for <code>DB_ETERNAL.X_LOG.ACTIVE</code>. 「active」- 是否启用
     */
    public Boolean getActive();

    /**
     * Setter for <code>DB_ETERNAL.X_LOG.SIGMA</code>. 「sigma」- 统一标识
     */
    public IXLog setSigma(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_LOG.SIGMA</code>. 「sigma」- 统一标识
     */
    public String getSigma();

    /**
     * Setter for <code>DB_ETERNAL.X_LOG.METADATA</code>. 「metadata」- 附加配置
     */
    public IXLog setMetadata(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_LOG.METADATA</code>. 「metadata」- 附加配置
     */
    public String getMetadata();

    /**
     * Setter for <code>DB_ETERNAL.X_LOG.LANGUAGE</code>. 「language」- 使用的语言
     */
    public IXLog setLanguage(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_LOG.LANGUAGE</code>. 「language」- 使用的语言
     */
    public String getLanguage();

    /**
     * Setter for <code>DB_ETERNAL.X_LOG.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public IXLog setCreatedAt(LocalDateTime value);

    /**
     * Getter for <code>DB_ETERNAL.X_LOG.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public LocalDateTime getCreatedAt();

    /**
     * Setter for <code>DB_ETERNAL.X_LOG.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public IXLog setCreatedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_LOG.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public String getCreatedBy();

    /**
     * Setter for <code>DB_ETERNAL.X_LOG.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public IXLog setUpdatedAt(LocalDateTime value);

    /**
     * Getter for <code>DB_ETERNAL.X_LOG.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public LocalDateTime getUpdatedAt();

    /**
     * Setter for <code>DB_ETERNAL.X_LOG.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public IXLog setUpdatedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_LOG.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public String getUpdatedBy();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IXLog
     */
    public void from(cn.vertxup.ambient.domain.tables.interfaces.IXLog from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IXLog
     */
    public <E extends cn.vertxup.ambient.domain.tables.interfaces.IXLog> E into(E into);

    default IXLog fromJson(io.vertx.core.json.JsonObject json) {
        setKey(json.getString("KEY"));
        setType(json.getString("TYPE"));
        setLevel(json.getString("LEVEL"));
        setInfoStack(json.getString("INFO_STACK"));
        setInfoSystem(json.getString("INFO_SYSTEM"));
        setInfoReadable(json.getString("INFO_READABLE"));
        // Omitting unrecognized type java.time.LocalDateTime for column INFO_AT!
        setLogAgent(json.getString("LOG_AGENT"));
        setLogIp(json.getString("LOG_IP"));
        setLogUser(json.getString("LOG_USER"));
        setActive(json.getBoolean("ACTIVE"));
        setSigma(json.getString("SIGMA"));
        setMetadata(json.getString("METADATA"));
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
        json.put("TYPE",getType());
        json.put("LEVEL",getLevel());
        json.put("INFO_STACK",getInfoStack());
        json.put("INFO_SYSTEM",getInfoSystem());
        json.put("INFO_READABLE",getInfoReadable());
        // Omitting unrecognized type java.time.LocalDateTime for column INFO_AT!
        json.put("LOG_AGENT",getLogAgent());
        json.put("LOG_IP",getLogIp());
        json.put("LOG_USER",getLogUser());
        json.put("ACTIVE",getActive());
        json.put("SIGMA",getSigma());
        json.put("METADATA",getMetadata());
        json.put("LANGUAGE",getLanguage());
        // Omitting unrecognized type java.time.LocalDateTime for column CREATED_AT!
        json.put("CREATED_BY",getCreatedBy());
        // Omitting unrecognized type java.time.LocalDateTime for column UPDATED_AT!
        json.put("UPDATED_BY",getUpdatedBy());
        return json;
    }

}
