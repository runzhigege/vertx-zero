/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ambient.tables.interfaces;


import java.io.Serializable;

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
public interface IXSource extends Serializable {

    /**
     * Setter for <code>DB_ETERNAL.X_SOURCE.KEY</code>. 「key」- 数据源主键
     */
    public IXSource setKey(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_SOURCE.KEY</code>. 「key」- 数据源主键
     */
    public String getKey();

    /**
     * Setter for <code>DB_ETERNAL.X_SOURCE.IP_V4</code>. 「ipV4」- IP v4地址
     */
    public IXSource setIpV4(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_SOURCE.IP_V4</code>. 「ipV4」- IP v4地址
     */
    public String getIpV4();

    /**
     * Setter for <code>DB_ETERNAL.X_SOURCE.IP_V6</code>. 「ipV6」- IP v6地址
     */
    public IXSource setIpV6(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_SOURCE.IP_V6</code>. 「ipV6」- IP v6地址
     */
    public String getIpV6();

    /**
     * Setter for <code>DB_ETERNAL.X_SOURCE.HOSTNAME</code>. 「hostname」- 主机地址
     */
    public IXSource setHostname(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_SOURCE.HOSTNAME</code>. 「hostname」- 主机地址
     */
    public String getHostname();

    /**
     * Setter for <code>DB_ETERNAL.X_SOURCE.PORT</code>. 「port」- 端口号
     */
    public IXSource setPort(Integer value);

    /**
     * Getter for <code>DB_ETERNAL.X_SOURCE.PORT</code>. 「port」- 端口号
     */
    public Integer getPort();

    /**
     * Setter for <code>DB_ETERNAL.X_SOURCE.CATEGORY</code>. 「category」- 数据库类型
     */
    public IXSource setCategory(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_SOURCE.CATEGORY</code>. 「category」- 数据库类型
     */
    public String getCategory();

    /**
     * Setter for <code>DB_ETERNAL.X_SOURCE.JDBC_URL</code>. 「jdbcUrl」- JDBC连接字符串
     */
    public IXSource setJdbcUrl(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_SOURCE.JDBC_URL</code>. 「jdbcUrl」- JDBC连接字符串
     */
    public String getJdbcUrl();

    /**
     * Setter for <code>DB_ETERNAL.X_SOURCE.JDBC_CONFIG</code>. 「jdbcConfig」- 连接字符串中的配置key=value
     */
    public IXSource setJdbcConfig(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_SOURCE.JDBC_CONFIG</code>. 「jdbcConfig」- 连接字符串中的配置key=value
     */
    public String getJdbcConfig();

    /**
     * Setter for <code>DB_ETERNAL.X_SOURCE.INSTANCE</code>. 「instance」- 实例名称
     */
    public IXSource setInstance(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_SOURCE.INSTANCE</code>. 「instance」- 实例名称
     */
    public String getInstance();

    /**
     * Setter for <code>DB_ETERNAL.X_SOURCE.USERNAME</code>. 「username」- 账号
     */
    public IXSource setUsername(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_SOURCE.USERNAME</code>. 「username」- 账号
     */
    public String getUsername();

    /**
     * Setter for <code>DB_ETERNAL.X_SOURCE.PASSWORD</code>. 「password」- 密码
     */
    public IXSource setPassword(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_SOURCE.PASSWORD</code>. 「password」- 密码
     */
    public String getPassword();

    /**
     * Setter for <code>DB_ETERNAL.X_SOURCE.ACTIVE</code>. 「active」- 是否启用
     */
    public IXSource setActive(Boolean value);

    /**
     * Getter for <code>DB_ETERNAL.X_SOURCE.ACTIVE</code>. 「active」- 是否启用
     */
    public Boolean getActive();

    /**
     * Setter for <code>DB_ETERNAL.X_SOURCE.METADATA</code>. 「metadata」- 通过MetadataConnection分析读取的数据
     */
    public IXSource setMetadata(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_SOURCE.METADATA</code>. 「metadata」- 通过MetadataConnection分析读取的数据
     */
    public String getMetadata();

    /**
     * Setter for <code>DB_ETERNAL.X_SOURCE.LANGUAGE</code>. 「language」- 使用的语言
     */
    public IXSource setLanguage(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_SOURCE.LANGUAGE</code>. 「language」- 使用的语言
     */
    public String getLanguage();

    /**
     * Setter for <code>DB_ETERNAL.X_SOURCE.APP_ID</code>. 「appId」- 关联的应用程序ID
     */
    public IXSource setAppId(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_SOURCE.APP_ID</code>. 「appId」- 关联的应用程序ID
     */
    public String getAppId();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IXSource
     */
    public void from(cn.vertxup.ambient.tables.interfaces.IXSource from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IXSource
     */
    public <E extends cn.vertxup.ambient.tables.interfaces.IXSource> E into(E into);

    default IXSource fromJson(io.vertx.core.json.JsonObject json) {
        setKey(json.getString("KEY"));
        setIpV4(json.getString("IP_V4"));
        setIpV6(json.getString("IP_V6"));
        setHostname(json.getString("HOSTNAME"));
        setPort(json.getInteger("PORT"));
        setCategory(json.getString("CATEGORY"));
        setJdbcUrl(json.getString("JDBC_URL"));
        setJdbcConfig(json.getString("JDBC_CONFIG"));
        setInstance(json.getString("INSTANCE"));
        setUsername(json.getString("USERNAME"));
        setPassword(json.getString("PASSWORD"));
        setActive(json.getBoolean("ACTIVE"));
        setMetadata(json.getString("METADATA"));
        setLanguage(json.getString("LANGUAGE"));
        setAppId(json.getString("APP_ID"));
        return this;
    }


    default io.vertx.core.json.JsonObject toJson() {
        io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("KEY",getKey());
        json.put("IP_V4",getIpV4());
        json.put("IP_V6",getIpV6());
        json.put("HOSTNAME",getHostname());
        json.put("PORT",getPort());
        json.put("CATEGORY",getCategory());
        json.put("JDBC_URL",getJdbcUrl());
        json.put("JDBC_CONFIG",getJdbcConfig());
        json.put("INSTANCE",getInstance());
        json.put("USERNAME",getUsername());
        json.put("PASSWORD",getPassword());
        json.put("ACTIVE",getActive());
        json.put("METADATA",getMetadata());
        json.put("LANGUAGE",getLanguage());
        json.put("APP_ID",getAppId());
        return json;
    }

}
