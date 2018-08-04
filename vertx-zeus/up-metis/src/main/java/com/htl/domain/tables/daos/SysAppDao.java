/*
 * This file is generated by jOOQ.
*/
package com.htl.domain.tables.daos;


import com.htl.domain.tables.SysApp;
import com.htl.domain.tables.records.SysAppRecord;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


import java.util.concurrent.CompletableFuture;
import io.github.jklingsporn.vertx.jooq.future.util.FutureTool;
/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SysAppDao extends DAOImpl<SysAppRecord, com.htl.domain.tables.pojos.SysApp, String> implements VertxDAO<com.htl.domain.tables.records.SysAppRecord,com.htl.domain.tables.pojos.SysApp,java.lang.String> {

    /**
     * Create a new SysAppDao without any configuration
     */
    public SysAppDao() {
        super(SysApp.SYS_APP, com.htl.domain.tables.pojos.SysApp.class);
    }

    /**
     * Create a new SysAppDao with an attached configuration
     */
    public SysAppDao(Configuration configuration) {
        super(SysApp.SYS_APP, com.htl.domain.tables.pojos.SysApp.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(com.htl.domain.tables.pojos.SysApp object) {
        return object.getPkId();
    }

    /**
     * Fetch records that have <code>PK_ID IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchByPkId(String... values) {
        return fetch(SysApp.SYS_APP.PK_ID, values);
    }

    /**
     * Fetch a unique record that has <code>PK_ID = value</code>
     */
    public com.htl.domain.tables.pojos.SysApp fetchOneByPkId(String value) {
        return fetchOne(SysApp.SYS_APP.PK_ID, value);
    }

    /**
     * Fetch records that have <code>T_COMMENTS IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchByTComments(String... values) {
        return fetch(SysApp.SYS_APP.T_COMMENTS, values);
    }

    /**
     * Fetch records that have <code>V_ESERVER_ID IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchByVEserverId(String... values) {
        return fetch(SysApp.SYS_APP.V_ESERVER_ID, values);
    }

    /**
     * Fetch records that have <code>S_NAME IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchBySName(String... values) {
        return fetch(SysApp.SYS_APP.S_NAME, values);
    }

    /**
     * Fetch records that have <code>S_CODE IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchBySCode(String... values) {
        return fetch(SysApp.SYS_APP.S_CODE, values);
    }

    /**
     * Fetch records that have <code>S_AUTH IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchBySAuth(String... values) {
        return fetch(SysApp.SYS_APP.S_AUTH, values);
    }

    /**
     * Fetch records that have <code>S_BACK_UP IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchBySBackUp(String... values) {
        return fetch(SysApp.SYS_APP.S_BACK_UP, values);
    }

    /**
     * Fetch records that have <code>S_CONTACT IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchBySContact(String... values) {
        return fetch(SysApp.SYS_APP.S_CONTACT, values);
    }

    /**
     * Fetch records that have <code>S_CURRENCY IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchBySCurrency(String... values) {
        return fetch(SysApp.SYS_APP.S_CURRENCY, values);
    }

    /**
     * Fetch records that have <code>S_DATE_FORMAT IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchBySDateFormat(String... values) {
        return fetch(SysApp.SYS_APP.S_DATE_FORMAT, values);
    }

    /**
     * Fetch records that have <code>S_ENCODING IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchBySEncoding(String... values) {
        return fetch(SysApp.SYS_APP.S_ENCODING, values);
    }

    /**
     * Fetch records that have <code>S_ICP IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchBySIcp(String... values) {
        return fetch(SysApp.SYS_APP.S_ICP, values);
    }

    /**
     * Fetch records that have <code>S_LOGO IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchBySLogo(String... values) {
        return fetch(SysApp.SYS_APP.S_LOGO, values);
    }

    /**
     * Fetch records that have <code>S_OWNER IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchBySOwner(String... values) {
        return fetch(SysApp.SYS_APP.S_OWNER, values);
    }

    /**
     * Fetch records that have <code>S_PATH IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchBySPath(String... values) {
        return fetch(SysApp.SYS_APP.S_PATH, values);
    }

    /**
     * Fetch records that have <code>S_RENEWAL IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchBySRenewal(String... values) {
        return fetch(SysApp.SYS_APP.S_RENEWAL, values);
    }

    /**
     * Fetch records that have <code>S_TITLE IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchBySTitle(String... values) {
        return fetch(SysApp.SYS_APP.S_TITLE, values);
    }

    /**
     * Fetch records that have <code>S_TYPE IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchBySType(String... values) {
        return fetch(SysApp.SYS_APP.S_TYPE, values);
    }

    /**
     * Fetch records that have <code>S_VERSION IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchBySVersion(String... values) {
        return fetch(SysApp.SYS_APP.S_VERSION, values);
    }

    /**
     * Fetch records that have <code>J_CONFIG IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchByJConfig(String... values) {
        return fetch(SysApp.SYS_APP.J_CONFIG, values);
    }

    /**
     * Fetch records that have <code>I_ATTACH_SIZE IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchByIAttachSize(Integer... values) {
        return fetch(SysApp.SYS_APP.I_ATTACH_SIZE, values);
    }

    /**
     * Fetch records that have <code>IS_ACTIVE IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchByIsActive(Boolean... values) {
        return fetch(SysApp.SYS_APP.IS_ACTIVE, values);
    }

    /**
     * Fetch records that have <code>Z_SIGMA IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchByZSigma(String... values) {
        return fetch(SysApp.SYS_APP.Z_SIGMA, values);
    }

    /**
     * Fetch records that have <code>Z_LANGUAGE IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchByZLanguage(String... values) {
        return fetch(SysApp.SYS_APP.Z_LANGUAGE, values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_BY IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchByZCreateBy(String... values) {
        return fetch(SysApp.SYS_APP.Z_CREATE_BY, values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_TIME IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchByZCreateTime(LocalDateTime... values) {
        return fetch(SysApp.SYS_APP.Z_CREATE_TIME, values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_BY IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchByZUpdateBy(String... values) {
        return fetch(SysApp.SYS_APP.Z_UPDATE_BY, values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_TIME IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysApp> fetchByZUpdateTime(LocalDateTime... values) {
        return fetch(SysApp.SYS_APP.Z_UPDATE_TIME, values);
    }

    /**
     * Fetch records that have <code>PK_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchByPkIdAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.PK_ID,values);
    }

    /**
     * Fetch a unique record that has <code>PK_ID = value</code> asynchronously
     */
    public CompletableFuture<com.htl.domain.tables.pojos.SysApp> fetchOneByPkIdAsync(String value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByPkId(value)),vertx());
    }

    /**
     * Fetch records that have <code>T_COMMENTS IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchByTCommentsAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.T_COMMENTS,values);
    }

    /**
     * Fetch records that have <code>V_ESERVER_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchByVEserverIdAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.V_ESERVER_ID,values);
    }

    /**
     * Fetch records that have <code>S_NAME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchBySNameAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.S_NAME,values);
    }

    /**
     * Fetch records that have <code>S_CODE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchBySCodeAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.S_CODE,values);
    }

    /**
     * Fetch records that have <code>S_AUTH IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchBySAuthAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.S_AUTH,values);
    }

    /**
     * Fetch records that have <code>S_BACK_UP IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchBySBackUpAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.S_BACK_UP,values);
    }

    /**
     * Fetch records that have <code>S_CONTACT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchBySContactAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.S_CONTACT,values);
    }

    /**
     * Fetch records that have <code>S_CURRENCY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchBySCurrencyAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.S_CURRENCY,values);
    }

    /**
     * Fetch records that have <code>S_DATE_FORMAT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchBySDateFormatAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.S_DATE_FORMAT,values);
    }

    /**
     * Fetch records that have <code>S_ENCODING IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchBySEncodingAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.S_ENCODING,values);
    }

    /**
     * Fetch records that have <code>S_ICP IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchBySIcpAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.S_ICP,values);
    }

    /**
     * Fetch records that have <code>S_LOGO IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchBySLogoAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.S_LOGO,values);
    }

    /**
     * Fetch records that have <code>S_OWNER IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchBySOwnerAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.S_OWNER,values);
    }

    /**
     * Fetch records that have <code>S_PATH IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchBySPathAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.S_PATH,values);
    }

    /**
     * Fetch records that have <code>S_RENEWAL IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchBySRenewalAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.S_RENEWAL,values);
    }

    /**
     * Fetch records that have <code>S_TITLE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchBySTitleAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.S_TITLE,values);
    }

    /**
     * Fetch records that have <code>S_TYPE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchBySTypeAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.S_TYPE,values);
    }

    /**
     * Fetch records that have <code>S_VERSION IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchBySVersionAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.S_VERSION,values);
    }

    /**
     * Fetch records that have <code>J_CONFIG IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchByJConfigAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.J_CONFIG,values);
    }

    /**
     * Fetch records that have <code>I_ATTACH_SIZE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchByIAttachSizeAsync(List<Integer> values) {
        return fetchAsync(SysApp.SYS_APP.I_ATTACH_SIZE,values);
    }

    /**
     * Fetch records that have <code>IS_ACTIVE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchByIsActiveAsync(List<Boolean> values) {
        return fetchAsync(SysApp.SYS_APP.IS_ACTIVE,values);
    }

    /**
     * Fetch records that have <code>Z_SIGMA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchByZSigmaAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.Z_SIGMA,values);
    }

    /**
     * Fetch records that have <code>Z_LANGUAGE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchByZLanguageAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.Z_LANGUAGE,values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchByZCreateByAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.Z_CREATE_BY,values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_TIME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchByZCreateTimeAsync(List<LocalDateTime> values) {
        return fetchAsync(SysApp.SYS_APP.Z_CREATE_TIME,values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchByZUpdateByAsync(List<String> values) {
        return fetchAsync(SysApp.SYS_APP.Z_UPDATE_BY,values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_TIME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysApp>> fetchByZUpdateTimeAsync(List<LocalDateTime> values) {
        return fetchAsync(SysApp.SYS_APP.Z_UPDATE_TIME,values);
    }

    private io.vertx.core.Vertx vertx;

    @Override
    public void setVertx(io.vertx.core.Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public io.vertx.core.Vertx vertx() {
        return this.vertx;
    }

}
