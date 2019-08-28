/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ui.domain.tables.interfaces;


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
public interface IUiAjax extends Serializable {

    /**
     * Setter for <code>DB_ETERNAL.UI_AJAX.KEY</code>. 「key」- 主键
     */
    public IUiAjax setKey(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_AJAX.KEY</code>. 「key」- 主键
     */
    public String getKey();

    /**
     * Setter for <code>DB_ETERNAL.UI_AJAX.NAME</code>. 「name」- Ajax的名称，生成ajaxKey专用
     */
    public IUiAjax setName(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_AJAX.NAME</code>. 「name」- Ajax的名称，生成ajaxKey专用
     */
    public String getName();

    /**
     * Setter for <code>DB_ETERNAL.UI_AJAX.URI</code>. 「uri」- 后端接口的URI地址
     */
    public IUiAjax setUri(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_AJAX.URI</code>. 「uri」- 后端接口的URI地址
     */
    public String getUri();

    /**
     * Setter for <code>DB_ETERNAL.UI_AJAX.METHOD</code>. 「method」- 当前Ajax的HTTP方法
     */
    public IUiAjax setMethod(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_AJAX.METHOD</code>. 「method」- 当前Ajax的HTTP方法
     */
    public String getMethod();

    /**
     * Setter for <code>DB_ETERNAL.UI_AJAX.QUERY</code>. 「query」- 是否query类型的接口
     */
    public IUiAjax setQuery(Boolean value);

    /**
     * Getter for <code>DB_ETERNAL.UI_AJAX.QUERY</code>. 「query」- 是否query类型的接口
     */
    public Boolean getQuery();

    /**
     * Setter for <code>DB_ETERNAL.UI_AJAX.PROJECTION</code>. 「projection」- query = true 生效
     */
    public IUiAjax setProjection(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_AJAX.PROJECTION</code>. 「projection」- query = true 生效
     */
    public String getProjection();

    /**
     * Setter for <code>DB_ETERNAL.UI_AJAX.PAGER</code>. 「pager」- query = true 生效
     */
    public IUiAjax setPager(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_AJAX.PAGER</code>. 「pager」- query = true 生效
     */
    public String getPager();

    /**
     * Setter for <code>DB_ETERNAL.UI_AJAX.SORTER</code>. 「sorter」- query = true 生效
     */
    public IUiAjax setSorter(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_AJAX.SORTER</code>. 「sorter」- query = true 生效
     */
    public String getSorter();

    /**
     * Setter for <code>DB_ETERNAL.UI_AJAX.CRITERIA</code>. 「criteria」- query = true则位于 query之下特殊节点，query = false则直接存储 query值
     */
    public IUiAjax setCriteria(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_AJAX.CRITERIA</code>. 「criteria」- query = true则位于 query之下特殊节点，query = false则直接存储 query值
     */
    public String getCriteria();

    /**
     * Setter for <code>DB_ETERNAL.UI_AJAX.ACTIVE</code>. 「active」- 是否启用
     */
    public IUiAjax setActive(Boolean value);

    /**
     * Getter for <code>DB_ETERNAL.UI_AJAX.ACTIVE</code>. 「active」- 是否启用
     */
    public Boolean getActive();

    /**
     * Setter for <code>DB_ETERNAL.UI_AJAX.SIGMA</code>. 「sigma」- 统一标识
     */
    public IUiAjax setSigma(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_AJAX.SIGMA</code>. 「sigma」- 统一标识
     */
    public String getSigma();

    /**
     * Setter for <code>DB_ETERNAL.UI_AJAX.METADATA</code>. 「metadata」- 附加配置
     */
    public IUiAjax setMetadata(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_AJAX.METADATA</code>. 「metadata」- 附加配置
     */
    public String getMetadata();

    /**
     * Setter for <code>DB_ETERNAL.UI_AJAX.LANGUAGE</code>. 「language」- 使用的语言
     */
    public IUiAjax setLanguage(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_AJAX.LANGUAGE</code>. 「language」- 使用的语言
     */
    public String getLanguage();

    /**
     * Setter for <code>DB_ETERNAL.UI_AJAX.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public IUiAjax setCreatedAt(LocalDateTime value);

    /**
     * Getter for <code>DB_ETERNAL.UI_AJAX.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public LocalDateTime getCreatedAt();

    /**
     * Setter for <code>DB_ETERNAL.UI_AJAX.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public IUiAjax setCreatedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_AJAX.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public String getCreatedBy();

    /**
     * Setter for <code>DB_ETERNAL.UI_AJAX.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public IUiAjax setUpdatedAt(LocalDateTime value);

    /**
     * Getter for <code>DB_ETERNAL.UI_AJAX.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public LocalDateTime getUpdatedAt();

    /**
     * Setter for <code>DB_ETERNAL.UI_AJAX.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public IUiAjax setUpdatedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_AJAX.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public String getUpdatedBy();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IUiAjax
     */
    public void from(cn.vertxup.ui.domain.tables.interfaces.IUiAjax from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IUiAjax
     */
    public <E extends cn.vertxup.ui.domain.tables.interfaces.IUiAjax> E into(E into);

    default IUiAjax fromJson(io.vertx.core.json.JsonObject json) {
        setKey(json.getString("KEY"));
        setName(json.getString("NAME"));
        setUri(json.getString("URI"));
        setMethod(json.getString("METHOD"));
        setQuery(json.getBoolean("QUERY"));
        setProjection(json.getString("PROJECTION"));
        setPager(json.getString("PAGER"));
        setSorter(json.getString("SORTER"));
        setCriteria(json.getString("CRITERIA"));
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
        json.put("NAME",getName());
        json.put("URI",getUri());
        json.put("METHOD",getMethod());
        json.put("QUERY",getQuery());
        json.put("PROJECTION",getProjection());
        json.put("PAGER",getPager());
        json.put("SORTER",getSorter());
        json.put("CRITERIA",getCriteria());
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
