/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ambient.tables.daos;


import cn.vertxup.ambient.tables.XApp;
import cn.vertxup.ambient.tables.records.XAppRecord;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;

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
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class XAppDao extends DAOImpl<XAppRecord, cn.vertxup.ambient.tables.pojos.XApp, String> implements VertxDAO<cn.vertxup.ambient.tables.records.XAppRecord,cn.vertxup.ambient.tables.pojos.XApp,java.lang.String> {

    /**
     * Create a new XAppDao without any configuration
     */
    public XAppDao() {
        super(XApp.X_APP, cn.vertxup.ambient.tables.pojos.XApp.class);
    }

    /**
     * Create a new XAppDao with an attached configuration
     */
    public XAppDao(Configuration configuration) {
        super(XApp.X_APP, cn.vertxup.ambient.tables.pojos.XApp.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(cn.vertxup.ambient.tables.pojos.XApp object) {
        return object.getKey();
    }

    /**
     * Fetch records that have <code>KEY IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XApp> fetchByKey(String... values) {
        return fetch(XApp.X_APP.KEY, values);
    }

    /**
     * Fetch a unique record that has <code>KEY = value</code>
     */
    public cn.vertxup.ambient.tables.pojos.XApp fetchOneByKey(String value) {
        return fetchOne(XApp.X_APP.KEY, value);
    }

    /**
     * Fetch records that have <code>NAME IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XApp> fetchByName(String... values) {
        return fetch(XApp.X_APP.NAME, values);
    }

    /**
     * Fetch a unique record that has <code>NAME = value</code>
     */
    public cn.vertxup.ambient.tables.pojos.XApp fetchOneByName(String value) {
        return fetchOne(XApp.X_APP.NAME, value);
    }

    /**
     * Fetch records that have <code>CODE IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XApp> fetchByCode(String... values) {
        return fetch(XApp.X_APP.CODE, values);
    }

    /**
     * Fetch a unique record that has <code>CODE = value</code>
     */
    public cn.vertxup.ambient.tables.pojos.XApp fetchOneByCode(String value) {
        return fetchOne(XApp.X_APP.CODE, value);
    }

    /**
     * Fetch records that have <code>TITLE IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XApp> fetchByTitle(String... values) {
        return fetch(XApp.X_APP.TITLE, values);
    }

    /**
     * Fetch records that have <code>LOGO IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XApp> fetchByLogo(String... values) {
        return fetch(XApp.X_APP.LOGO, values);
    }

    /**
     * Fetch records that have <code>DOMAIN IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XApp> fetchByDomain(String... values) {
        return fetch(XApp.X_APP.DOMAIN, values);
    }

    /**
     * Fetch records that have <code>APP_PORT IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XApp> fetchByAppPort(Integer... values) {
        return fetch(XApp.X_APP.APP_PORT, values);
    }

    /**
     * Fetch records that have <code>URL_ENTRY IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XApp> fetchByUrlEntry(String... values) {
        return fetch(XApp.X_APP.URL_ENTRY, values);
    }

    /**
     * Fetch records that have <code>URL_MAIN IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XApp> fetchByUrlMain(String... values) {
        return fetch(XApp.X_APP.URL_MAIN, values);
    }

    /**
     * Fetch records that have <code>PATH IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XApp> fetchByPath(String... values) {
        return fetch(XApp.X_APP.PATH, values);
    }

    /**
     * Fetch records that have <code>ROUTE IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XApp> fetchByRoute(String... values) {
        return fetch(XApp.X_APP.ROUTE, values);
    }

    /**
     * Fetch records that have <code>APP_KEY IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XApp> fetchByAppKey(String... values) {
        return fetch(XApp.X_APP.APP_KEY, values);
    }

    /**
     * Fetch records that have <code>SIGMA IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XApp> fetchBySigma(String... values) {
        return fetch(XApp.X_APP.SIGMA, values);
    }

    /**
     * Fetch records that have <code>ACTIVE IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XApp> fetchByActive(Boolean... values) {
        return fetch(XApp.X_APP.ACTIVE, values);
    }

    /**
     * Fetch records that have <code>LANGUAGE IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XApp> fetchByLanguage(String... values) {
        return fetch(XApp.X_APP.LANGUAGE, values);
    }

    /**
     * Fetch records that have <code>METADATA IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XApp> fetchByMetadata(String... values) {
        return fetch(XApp.X_APP.METADATA, values);
    }

    /**
     * Fetch records that have <code>KEY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XApp>> fetchByKeyAsync(List<String> values) {
        return fetchAsync(XApp.X_APP.KEY,values);
    }

    /**
     * Fetch a unique record that has <code>KEY = value</code> asynchronously
     */
    public CompletableFuture<cn.vertxup.ambient.tables.pojos.XApp> fetchOneByKeyAsync(String value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByKey(value)),vertx());
    }

    /**
     * Fetch records that have <code>NAME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XApp>> fetchByNameAsync(List<String> values) {
        return fetchAsync(XApp.X_APP.NAME,values);
    }

    /**
     * Fetch a unique record that has <code>NAME = value</code> asynchronously
     */
    public CompletableFuture<cn.vertxup.ambient.tables.pojos.XApp> fetchOneByNameAsync(String value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByName(value)),vertx());
    }

    /**
     * Fetch records that have <code>CODE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XApp>> fetchByCodeAsync(List<String> values) {
        return fetchAsync(XApp.X_APP.CODE,values);
    }

    /**
     * Fetch a unique record that has <code>CODE = value</code> asynchronously
     */
    public CompletableFuture<cn.vertxup.ambient.tables.pojos.XApp> fetchOneByCodeAsync(String value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByCode(value)),vertx());
    }

    /**
     * Fetch records that have <code>TITLE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XApp>> fetchByTitleAsync(List<String> values) {
        return fetchAsync(XApp.X_APP.TITLE,values);
    }

    /**
     * Fetch records that have <code>LOGO IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XApp>> fetchByLogoAsync(List<String> values) {
        return fetchAsync(XApp.X_APP.LOGO,values);
    }

    /**
     * Fetch records that have <code>DOMAIN IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XApp>> fetchByDomainAsync(List<String> values) {
        return fetchAsync(XApp.X_APP.DOMAIN,values);
    }

    /**
     * Fetch records that have <code>APP_PORT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XApp>> fetchByAppPortAsync(List<Integer> values) {
        return fetchAsync(XApp.X_APP.APP_PORT,values);
    }

    /**
     * Fetch records that have <code>URL_ENTRY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XApp>> fetchByUrlEntryAsync(List<String> values) {
        return fetchAsync(XApp.X_APP.URL_ENTRY,values);
    }

    /**
     * Fetch records that have <code>URL_MAIN IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XApp>> fetchByUrlMainAsync(List<String> values) {
        return fetchAsync(XApp.X_APP.URL_MAIN,values);
    }

    /**
     * Fetch records that have <code>PATH IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XApp>> fetchByPathAsync(List<String> values) {
        return fetchAsync(XApp.X_APP.PATH,values);
    }

    /**
     * Fetch records that have <code>ROUTE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XApp>> fetchByRouteAsync(List<String> values) {
        return fetchAsync(XApp.X_APP.ROUTE,values);
    }

    /**
     * Fetch records that have <code>APP_KEY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XApp>> fetchByAppKeyAsync(List<String> values) {
        return fetchAsync(XApp.X_APP.APP_KEY,values);
    }

    /**
     * Fetch records that have <code>SIGMA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XApp>> fetchBySigmaAsync(List<String> values) {
        return fetchAsync(XApp.X_APP.SIGMA,values);
    }

    /**
     * Fetch records that have <code>ACTIVE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XApp>> fetchByActiveAsync(List<Boolean> values) {
        return fetchAsync(XApp.X_APP.ACTIVE,values);
    }

    /**
     * Fetch records that have <code>LANGUAGE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XApp>> fetchByLanguageAsync(List<String> values) {
        return fetchAsync(XApp.X_APP.LANGUAGE,values);
    }

    /**
     * Fetch records that have <code>METADATA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XApp>> fetchByMetadataAsync(List<String> values) {
        return fetchAsync(XApp.X_APP.METADATA,values);
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
