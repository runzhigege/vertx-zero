/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.domain.tables.interfaces;


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
public interface IOUser extends Serializable {

    /**
     * Setter for <code>DB_ORIGIN_X.O_USER.KEY</code>. 「key」- OAuth用户ID
     */
    public IOUser setKey(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.O_USER.KEY</code>. 「key」- OAuth用户ID
     */
    public String getKey();

    /**
     * Setter for <code>DB_ORIGIN_X.O_USER.REDIRECT_URI</code>. 「redirectUri」- 回调重定向地址
     */
    public IOUser setRedirectUri(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.O_USER.REDIRECT_URI</code>. 「redirectUri」- 回调重定向地址
     */
    public String getRedirectUri();

    /**
     * Setter for <code>DB_ORIGIN_X.O_USER.CODE</code>. 「code」- 系统编号
     */
    public IOUser setCode(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.O_USER.CODE</code>. 「code」- 系统编号
     */
    public String getCode();

    /**
     * Setter for <code>DB_ORIGIN_X.O_USER.CLIENT_SECRET</code>. 「clientSecret」- 客户端密钥
     */
    public IOUser setClientSecret(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.O_USER.CLIENT_SECRET</code>. 「clientSecret」- 客户端密钥
     */
    public String getClientSecret();

    /**
     * Setter for <code>DB_ORIGIN_X.O_USER.CLIENT_ID</code>. 「clientId」- 客户端ID
     */
    public IOUser setClientId(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.O_USER.CLIENT_ID</code>. 「clientId」- 客户端ID
     */
    public String getClientId();

    /**
     * Setter for <code>DB_ORIGIN_X.O_USER.GRANT_TYPE</code>. 「grantType」- 认证方式
     */
    public IOUser setGrantType(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.O_USER.GRANT_TYPE</code>. 「grantType」- 认证方式
     */
    public String getGrantType();

    /**
     * Setter for <code>DB_ORIGIN_X.O_USER.SCOPE</code>. 「scope」- 对应名空间，以应用为中心
     */
    public IOUser setScope(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.O_USER.SCOPE</code>. 「scope」- 对应名空间，以应用为中心
     */
    public String getScope();

    /**
     * Setter for <code>DB_ORIGIN_X.O_USER.STATE</code>. 「state」- 客户端状态
     */
    public IOUser setState(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.O_USER.STATE</code>. 「state」- 客户端状态
     */
    public String getState();

    /**
     * Setter for <code>DB_ORIGIN_X.O_USER.LANGUAGE</code>. 「language」- 使用的语言
     */
    public IOUser setLanguage(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.O_USER.LANGUAGE</code>. 「language」- 使用的语言
     */
    public String getLanguage();

    /**
     * Setter for <code>DB_ORIGIN_X.O_USER.ACTIVE</code>. 「active」- 是否启用
     */
    public IOUser setActive(Boolean value);

    /**
     * Getter for <code>DB_ORIGIN_X.O_USER.ACTIVE</code>. 「active」- 是否启用
     */
    public Boolean getActive();

    /**
     * Setter for <code>DB_ORIGIN_X.O_USER.METADATA</code>. 「metadata」- 附加配置数据
     */
    public IOUser setMetadata(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.O_USER.METADATA</code>. 「metadata」- 附加配置数据
     */
    public String getMetadata();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IOUser
     */
    public void from(cn.vertxup.domain.tables.interfaces.IOUser from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IOUser
     */
    public <E extends cn.vertxup.domain.tables.interfaces.IOUser> E into(E into);

    default IOUser fromJson(io.vertx.core.json.JsonObject json) {
        setKey(json.getString("KEY"));
        setRedirectUri(json.getString("REDIRECT_URI"));
        setCode(json.getString("CODE"));
        setClientSecret(json.getString("CLIENT_SECRET"));
        setClientId(json.getString("CLIENT_ID"));
        setGrantType(json.getString("GRANT_TYPE"));
        setScope(json.getString("SCOPE"));
        setState(json.getString("STATE"));
        setLanguage(json.getString("LANGUAGE"));
        setActive(json.getBoolean("ACTIVE"));
        setMetadata(json.getString("METADATA"));
        return this;
    }


    default io.vertx.core.json.JsonObject toJson() {
        io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("KEY",getKey());
        json.put("REDIRECT_URI",getRedirectUri());
        json.put("CODE",getCode());
        json.put("CLIENT_SECRET",getClientSecret());
        json.put("CLIENT_ID",getClientId());
        json.put("GRANT_TYPE",getGrantType());
        json.put("SCOPE",getScope());
        json.put("STATE",getState());
        json.put("LANGUAGE",getLanguage());
        json.put("ACTIVE",getActive());
        json.put("METADATA",getMetadata());
        return json;
    }

}
