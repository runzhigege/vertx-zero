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
public interface IXMenu extends Serializable {

    /**
     * Setter for <code>DB_ORIGIN_X.X_MENU.KEY</code>. 「key」- 菜单主键
     */
    public IXMenu setKey(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.X_MENU.KEY</code>. 「key」- 菜单主键
     */
    public String getKey();

    /**
     * Setter for <code>DB_ORIGIN_X.X_MENU.NAME</code>. 「name」- 菜单名称
     */
    public IXMenu setName(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.X_MENU.NAME</code>. 「name」- 菜单名称
     */
    public String getName();

    /**
     * Setter for <code>DB_ORIGIN_X.X_MENU.ICON</code>. 「icon」- 菜单使用的icon
     */
    public IXMenu setIcon(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.X_MENU.ICON</code>. 「icon」- 菜单使用的icon
     */
    public String getIcon();

    /**
     * Setter for <code>DB_ORIGIN_X.X_MENU.TEXT</code>. 「text」- 菜单显示文字
     */
    public IXMenu setText(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.X_MENU.TEXT</code>. 「text」- 菜单显示文字
     */
    public String getText();

    /**
     * Setter for <code>DB_ORIGIN_X.X_MENU.URI</code>. 「uri」- 菜单地址（不包含应用的path）
     */
    public IXMenu setUri(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.X_MENU.URI</code>. 「uri」- 菜单地址（不包含应用的path）
     */
    public String getUri();

    /**
     * Setter for <code>DB_ORIGIN_X.X_MENU.TYPE</code>. 「type」- 菜单类型
     */
    public IXMenu setType(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.X_MENU.TYPE</code>. 「type」- 菜单类型
     */
    public String getType();

    /**
     * Setter for <code>DB_ORIGIN_X.X_MENU.ORDER</code>. 「order」- 菜单排序
     */
    public IXMenu setOrder(Long value);

    /**
     * Getter for <code>DB_ORIGIN_X.X_MENU.ORDER</code>. 「order」- 菜单排序
     */
    public Long getOrder();

    /**
     * Setter for <code>DB_ORIGIN_X.X_MENU.LEVEL</code>. 「level」- 菜单层级
     */
    public IXMenu setLevel(Long value);

    /**
     * Getter for <code>DB_ORIGIN_X.X_MENU.LEVEL</code>. 「level」- 菜单层级
     */
    public Long getLevel();

    /**
     * Setter for <code>DB_ORIGIN_X.X_MENU.PARENT_ID</code>. 「parentId」- 菜单父ID
     */
    public IXMenu setParentId(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.X_MENU.PARENT_ID</code>. 「parentId」- 菜单父ID
     */
    public String getParentId();

    /**
     * Setter for <code>DB_ORIGIN_X.X_MENU.APP_ID</code>. 「appId」- 应用程序ID
     */
    public IXMenu setAppId(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.X_MENU.APP_ID</code>. 「appId」- 应用程序ID
     */
    public String getAppId();

    /**
     * Setter for <code>DB_ORIGIN_X.X_MENU.ACTIVE</code>. 「active」- 是否启用
     */
    public IXMenu setActive(Boolean value);

    /**
     * Getter for <code>DB_ORIGIN_X.X_MENU.ACTIVE</code>. 「active」- 是否启用
     */
    public Boolean getActive();

    /**
     * Setter for <code>DB_ORIGIN_X.X_MENU.LANGUAGE</code>. 「language」- 使用的语言
     */
    public IXMenu setLanguage(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.X_MENU.LANGUAGE</code>. 「language」- 使用的语言
     */
    public String getLanguage();

    /**
     * Setter for <code>DB_ORIGIN_X.X_MENU.METADATA</code>. 「metadata」- 附加配置数据
     */
    public IXMenu setMetadata(String value);

    /**
     * Getter for <code>DB_ORIGIN_X.X_MENU.METADATA</code>. 「metadata」- 附加配置数据
     */
    public String getMetadata();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IXMenu
     */
    public void from(cn.vertxup.ambient.tables.interfaces.IXMenu from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IXMenu
     */
    public <E extends cn.vertxup.ambient.tables.interfaces.IXMenu> E into(E into);

    default IXMenu fromJson(io.vertx.core.json.JsonObject json) {
        setKey(json.getString("KEY"));
        setName(json.getString("NAME"));
        setIcon(json.getString("ICON"));
        setText(json.getString("TEXT"));
        setUri(json.getString("URI"));
        setType(json.getString("TYPE"));
        setOrder(json.getLong("ORDER"));
        setLevel(json.getLong("LEVEL"));
        setParentId(json.getString("PARENT_ID"));
        setAppId(json.getString("APP_ID"));
        setActive(json.getBoolean("ACTIVE"));
        setLanguage(json.getString("LANGUAGE"));
        setMetadata(json.getString("METADATA"));
        return this;
    }


    default io.vertx.core.json.JsonObject toJson() {
        io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("KEY",getKey());
        json.put("NAME",getName());
        json.put("ICON",getIcon());
        json.put("TEXT",getText());
        json.put("URI",getUri());
        json.put("TYPE",getType());
        json.put("ORDER",getOrder());
        json.put("LEVEL",getLevel());
        json.put("PARENT_ID",getParentId());
        json.put("APP_ID",getAppId());
        json.put("ACTIVE",getActive());
        json.put("LANGUAGE",getLanguage());
        json.put("METADATA",getMetadata());
        return json;
    }

}
