/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ui.domain.tables.interfaces;


import java.io.Serializable;
import java.math.BigDecimal;
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
public interface IUiForm extends Serializable {

    /**
     * Setter for <code>DB_ETERNAL.UI_FORM.KEY</code>. 「key」- 主键
     */
    public IUiForm setKey(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_FORM.KEY</code>. 「key」- 主键
     */
    public String getKey();

    /**
     * Setter for <code>DB_ETERNAL.UI_FORM.NAME</code>. 「name」- 表单名称
     */
    public IUiForm setName(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_FORM.NAME</code>. 「name」- 表单名称
     */
    public String getName();

    /**
     * Setter for <code>DB_ETERNAL.UI_FORM.CODE</code>. 「code」- 表单系统编码
     */
    public IUiForm setCode(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_FORM.CODE</code>. 「code」- 表单系统编码
     */
    public String getCode();

    /**
     * Setter for <code>DB_ETERNAL.UI_FORM.WINDOW</code>. 「window」- window, Form对应的窗口配置
     */
    public IUiForm setWindow(BigDecimal value);

    /**
     * Getter for <code>DB_ETERNAL.UI_FORM.WINDOW</code>. 「window」- window, Form对应的窗口配置
     */
    public BigDecimal getWindow();

    /**
     * Setter for <code>DB_ETERNAL.UI_FORM.COLUMNS</code>. 「columns」- columns, Form对应的配置
     */
    public IUiForm setColumns(Integer value);

    /**
     * Getter for <code>DB_ETERNAL.UI_FORM.COLUMNS</code>. 「columns」- columns, Form对应的配置
     */
    public Integer getColumns();

    /**
     * Setter for <code>DB_ETERNAL.UI_FORM.HIDDEN</code>. 「hidden」- 隐藏字段专用配置
     */
    public IUiForm setHidden(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_FORM.HIDDEN</code>. 「hidden」- 隐藏字段专用配置
     */
    public String getHidden();

    /**
     * Setter for <code>DB_ETERNAL.UI_FORM.ROW</code>. 「rowConfig/rowClass」- 行专用配置
     */
    public IUiForm setRow(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_FORM.ROW</code>. 「rowConfig/rowClass」- 行专用配置
     */
    public String getRow();

    /**
     * Setter for <code>DB_ETERNAL.UI_FORM.ACTIVE</code>. 「active」- 是否启用
     */
    public IUiForm setActive(Boolean value);

    /**
     * Getter for <code>DB_ETERNAL.UI_FORM.ACTIVE</code>. 「active」- 是否启用
     */
    public Boolean getActive();

    /**
     * Setter for <code>DB_ETERNAL.UI_FORM.SIGMA</code>. 「sigma」- 统一标识
     */
    public IUiForm setSigma(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_FORM.SIGMA</code>. 「sigma」- 统一标识
     */
    public String getSigma();

    /**
     * Setter for <code>DB_ETERNAL.UI_FORM.METADATA</code>. 「metadata」- 附加配置
     */
    public IUiForm setMetadata(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_FORM.METADATA</code>. 「metadata」- 附加配置
     */
    public String getMetadata();

    /**
     * Setter for <code>DB_ETERNAL.UI_FORM.LANGUAGE</code>. 「language」- 使用的语言
     */
    public IUiForm setLanguage(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_FORM.LANGUAGE</code>. 「language」- 使用的语言
     */
    public String getLanguage();

    /**
     * Setter for <code>DB_ETERNAL.UI_FORM.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public IUiForm setCreatedAt(LocalDateTime value);

    /**
     * Getter for <code>DB_ETERNAL.UI_FORM.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public LocalDateTime getCreatedAt();

    /**
     * Setter for <code>DB_ETERNAL.UI_FORM.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public IUiForm setCreatedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_FORM.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public String getCreatedBy();

    /**
     * Setter for <code>DB_ETERNAL.UI_FORM.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public IUiForm setUpdatedAt(LocalDateTime value);

    /**
     * Getter for <code>DB_ETERNAL.UI_FORM.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public LocalDateTime getUpdatedAt();

    /**
     * Setter for <code>DB_ETERNAL.UI_FORM.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public IUiForm setUpdatedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.UI_FORM.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public String getUpdatedBy();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IUiForm
     */
    public void from(cn.vertxup.ui.domain.tables.interfaces.IUiForm from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IUiForm
     */
    public <E extends cn.vertxup.ui.domain.tables.interfaces.IUiForm> E into(E into);

    default IUiForm fromJson(io.vertx.core.json.JsonObject json) {
        setKey(json.getString("KEY"));
        setName(json.getString("NAME"));
        setCode(json.getString("CODE"));
        // Omitting unrecognized type java.math.BigDecimal for column WINDOW!
        setColumns(json.getInteger("COLUMNS"));
        setHidden(json.getString("HIDDEN"));
        setRow(json.getString("ROW"));
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
        json.put("CODE",getCode());
        // Omitting unrecognized type java.math.BigDecimal for column WINDOW!
        json.put("COLUMNS",getColumns());
        json.put("HIDDEN",getHidden());
        json.put("ROW",getRow());
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
