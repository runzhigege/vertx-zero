/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ambient.tables.records;


import cn.vertxup.ambient.tables.XApp;
import cn.vertxup.ambient.tables.interfaces.IXApp;

import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record20;
import org.jooq.Row20;
import org.jooq.impl.UpdatableRecordImpl;


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
public class XAppRecord extends UpdatableRecordImpl<XAppRecord> implements Record20<String, String, String, String, String, String, Integer, String, String, String, String, String, Boolean, String, String, String, LocalDateTime, String, LocalDateTime, String>, IXApp {

    private static final long serialVersionUID = 2087035617;

    /**
     * Setter for <code>DB_ETERNAL.X_APP.KEY</code>. 「key」- 应用程序主键
     */
    @Override
    public XAppRecord setKey(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.KEY</code>. 「key」- 应用程序主键
     */
    @Override
    public String getKey() {
        return (String) get(0);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.NAME</code>. 「name」- 应用程序名称
     */
    @Override
    public XAppRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.NAME</code>. 「name」- 应用程序名称
     */
    @Override
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.CODE</code>. 「code」- 应用程序编码
     */
    @Override
    public XAppRecord setCode(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.CODE</code>. 「code」- 应用程序编码
     */
    @Override
    public String getCode() {
        return (String) get(2);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.TITLE</code>. 「title」- 应用程序标题
     */
    @Override
    public XAppRecord setTitle(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.TITLE</code>. 「title」- 应用程序标题
     */
    @Override
    public String getTitle() {
        return (String) get(3);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.LOGO</code>. 「logo」- 应用程序图标
     */
    @Override
    public XAppRecord setLogo(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.LOGO</code>. 「logo」- 应用程序图标
     */
    @Override
    public String getLogo() {
        return (String) get(4);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.DOMAIN</code>. 「domain」- 应用程序所在域
     */
    @Override
    public XAppRecord setDomain(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.DOMAIN</code>. 「domain」- 应用程序所在域
     */
    @Override
    public String getDomain() {
        return (String) get(5);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.APP_PORT</code>. 「appPort」- 应用程序端口号，和SOURCE的端口号区别开
     */
    @Override
    public XAppRecord setAppPort(Integer value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.APP_PORT</code>. 「appPort」- 应用程序端口号，和SOURCE的端口号区别开
     */
    @Override
    public Integer getAppPort() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.URL_ENTRY</code>. 「urlEntry」— 应用程序入口页面（登录页）
     */
    @Override
    public XAppRecord setUrlEntry(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.URL_ENTRY</code>. 「urlEntry」— 应用程序入口页面（登录页）
     */
    @Override
    public String getUrlEntry() {
        return (String) get(7);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.URL_MAIN</code>. 「urlMain」- 应用程序内置主页（带安全）
     */
    @Override
    public XAppRecord setUrlMain(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.URL_MAIN</code>. 「urlMain」- 应用程序内置主页（带安全）
     */
    @Override
    public String getUrlMain() {
        return (String) get(8);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.PATH</code>. 「path」- 应用程序路径
     */
    @Override
    public XAppRecord setPath(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.PATH</code>. 「path」- 应用程序路径
     */
    @Override
    public String getPath() {
        return (String) get(9);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.ROUTE</code>. 「route」- 后端API的根路径，启动时需要
     */
    @Override
    public XAppRecord setRoute(String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.ROUTE</code>. 「route」- 后端API的根路径，启动时需要
     */
    @Override
    public String getRoute() {
        return (String) get(10);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.APP_KEY</code>. 「appKey」- 应用程序专用唯一hashKey
     */
    @Override
    public XAppRecord setAppKey(String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.APP_KEY</code>. 「appKey」- 应用程序专用唯一hashKey
     */
    @Override
    public String getAppKey() {
        return (String) get(11);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public XAppRecord setActive(Boolean value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.ACTIVE</code>. 「active」- 是否启用
     */
    @Override
    public Boolean getActive() {
        return (Boolean) get(12);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.SIGMA</code>. 「sigma」- 统一标识
     */
    @Override
    public XAppRecord setSigma(String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.SIGMA</code>. 「sigma」- 统一标识
     */
    @Override
    public String getSigma() {
        return (String) get(13);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.METADATA</code>. 「metadata」- 附加配置
     */
    @Override
    public XAppRecord setMetadata(String value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.METADATA</code>. 「metadata」- 附加配置
     */
    @Override
    public String getMetadata() {
        return (String) get(14);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public XAppRecord setLanguage(String value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.LANGUAGE</code>. 「language」- 使用的语言
     */
    @Override
    public String getLanguage() {
        return (String) get(15);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public XAppRecord setCreatedAt(LocalDateTime value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(16);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public XAppRecord setCreatedBy(String value) {
        set(17, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.CREATED_BY</code>. 「createdBy」- 创建人
     */
    @Override
    public String getCreatedBy() {
        return (String) get(17);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public XAppRecord setUpdatedAt(LocalDateTime value) {
        set(18, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    @Override
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(18);
    }

    /**
     * Setter for <code>DB_ETERNAL.X_APP.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    @Override
    public XAppRecord setUpdatedBy(String value) {
        set(19, value);
        return this;
    }

    /**
     * Getter for <code>DB_ETERNAL.X_APP.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    @Override
    public String getUpdatedBy() {
        return (String) get(19);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record20 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row20<String, String, String, String, String, String, Integer, String, String, String, String, String, Boolean, String, String, String, LocalDateTime, String, LocalDateTime, String> fieldsRow() {
        return (Row20) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row20<String, String, String, String, String, String, Integer, String, String, String, String, String, Boolean, String, String, String, LocalDateTime, String, LocalDateTime, String> valuesRow() {
        return (Row20) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return XApp.X_APP.KEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return XApp.X_APP.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return XApp.X_APP.CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return XApp.X_APP.TITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return XApp.X_APP.LOGO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return XApp.X_APP.DOMAIN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field7() {
        return XApp.X_APP.APP_PORT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return XApp.X_APP.URL_ENTRY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return XApp.X_APP.URL_MAIN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return XApp.X_APP.PATH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return XApp.X_APP.ROUTE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field12() {
        return XApp.X_APP.APP_KEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field13() {
        return XApp.X_APP.ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field14() {
        return XApp.X_APP.SIGMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field15() {
        return XApp.X_APP.METADATA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field16() {
        return XApp.X_APP.LANGUAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field17() {
        return XApp.X_APP.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field18() {
        return XApp.X_APP.CREATED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field19() {
        return XApp.X_APP.UPDATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field20() {
        return XApp.X_APP.UPDATED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getLogo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getDomain();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component7() {
        return getAppPort();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getUrlEntry();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component9() {
        return getUrlMain();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component10() {
        return getPath();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component11() {
        return getRoute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component12() {
        return getAppKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component13() {
        return getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component14() {
        return getSigma();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component15() {
        return getMetadata();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component16() {
        return getLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component17() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component18() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component19() {
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component20() {
        return getUpdatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getLogo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getDomain();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value7() {
        return getAppPort();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getUrlEntry();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getUrlMain();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getPath();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value11() {
        return getRoute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value12() {
        return getAppKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value13() {
        return getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value14() {
        return getSigma();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value15() {
        return getMetadata();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value16() {
        return getLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value17() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value18() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value19() {
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value20() {
        return getUpdatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value1(String value) {
        setKey(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value3(String value) {
        setCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value4(String value) {
        setTitle(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value5(String value) {
        setLogo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value6(String value) {
        setDomain(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value7(Integer value) {
        setAppPort(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value8(String value) {
        setUrlEntry(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value9(String value) {
        setUrlMain(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value10(String value) {
        setPath(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value11(String value) {
        setRoute(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value12(String value) {
        setAppKey(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value13(Boolean value) {
        setActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value14(String value) {
        setSigma(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value15(String value) {
        setMetadata(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value16(String value) {
        setLanguage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value17(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value18(String value) {
        setCreatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value19(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord value20(String value) {
        setUpdatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XAppRecord values(String value1, String value2, String value3, String value4, String value5, String value6, Integer value7, String value8, String value9, String value10, String value11, String value12, Boolean value13, String value14, String value15, String value16, LocalDateTime value17, String value18, LocalDateTime value19, String value20) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        value18(value18);
        value19(value19);
        value20(value20);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IXApp from) {
        setKey(from.getKey());
        setName(from.getName());
        setCode(from.getCode());
        setTitle(from.getTitle());
        setLogo(from.getLogo());
        setDomain(from.getDomain());
        setAppPort(from.getAppPort());
        setUrlEntry(from.getUrlEntry());
        setUrlMain(from.getUrlMain());
        setPath(from.getPath());
        setRoute(from.getRoute());
        setAppKey(from.getAppKey());
        setActive(from.getActive());
        setSigma(from.getSigma());
        setMetadata(from.getMetadata());
        setLanguage(from.getLanguage());
        setCreatedAt(from.getCreatedAt());
        setCreatedBy(from.getCreatedBy());
        setUpdatedAt(from.getUpdatedAt());
        setUpdatedBy(from.getUpdatedBy());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IXApp> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached XAppRecord
     */
    public XAppRecord() {
        super(XApp.X_APP);
    }

    /**
     * Create a detached, initialised XAppRecord
     */
    public XAppRecord(String key, String name, String code, String title, String logo, String domain, Integer appPort, String urlEntry, String urlMain, String path, String route, String appKey, Boolean active, String sigma, String metadata, String language, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
        super(XApp.X_APP);

        set(0, key);
        set(1, name);
        set(2, code);
        set(3, title);
        set(4, logo);
        set(5, domain);
        set(6, appPort);
        set(7, urlEntry);
        set(8, urlMain);
        set(9, path);
        set(10, route);
        set(11, appKey);
        set(12, active);
        set(13, sigma);
        set(14, metadata);
        set(15, language);
        set(16, createdAt);
        set(17, createdBy);
        set(18, updatedAt);
        set(19, updatedBy);
    }
}
