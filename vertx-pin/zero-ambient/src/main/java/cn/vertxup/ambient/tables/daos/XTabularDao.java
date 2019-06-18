/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ambient.tables.daos;


import cn.vertxup.ambient.tables.XTabular;
import cn.vertxup.ambient.tables.records.XTabularRecord;

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
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class XTabularDao extends DAOImpl<XTabularRecord, cn.vertxup.ambient.tables.pojos.XTabular, String> implements VertxDAO<cn.vertxup.ambient.tables.records.XTabularRecord,cn.vertxup.ambient.tables.pojos.XTabular,java.lang.String> {

    /**
     * Create a new XTabularDao without any configuration
     */
    public XTabularDao() {
        super(XTabular.X_TABULAR, cn.vertxup.ambient.tables.pojos.XTabular.class);
    }

    /**
     * Create a new XTabularDao with an attached configuration
     */
    public XTabularDao(Configuration configuration) {
        super(XTabular.X_TABULAR, cn.vertxup.ambient.tables.pojos.XTabular.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(cn.vertxup.ambient.tables.pojos.XTabular object) {
        return object.getKey();
    }

    /**
     * Fetch records that have <code>KEY IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XTabular> fetchByKey(String... values) {
        return fetch(XTabular.X_TABULAR.KEY, values);
    }

    /**
     * Fetch a unique record that has <code>KEY = value</code>
     */
    public cn.vertxup.ambient.tables.pojos.XTabular fetchOneByKey(String value) {
        return fetchOne(XTabular.X_TABULAR.KEY, value);
    }

    /**
     * Fetch records that have <code>NAME IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XTabular> fetchByName(String... values) {
        return fetch(XTabular.X_TABULAR.NAME, values);
    }

    /**
     * Fetch records that have <code>CODE IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XTabular> fetchByCode(String... values) {
        return fetch(XTabular.X_TABULAR.CODE, values);
    }

    /**
     * Fetch records that have <code>TYPE IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XTabular> fetchByType(String... values) {
        return fetch(XTabular.X_TABULAR.TYPE, values);
    }

    /**
     * Fetch records that have <code>ICON IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XTabular> fetchByIcon(String... values) {
        return fetch(XTabular.X_TABULAR.ICON, values);
    }

    /**
     * Fetch records that have <code>SORT IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XTabular> fetchBySort(Integer... values) {
        return fetch(XTabular.X_TABULAR.SORT, values);
    }

    /**
     * Fetch records that have <code>COMMENT IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XTabular> fetchByComment(String... values) {
        return fetch(XTabular.X_TABULAR.COMMENT, values);
    }

    /**
     * Fetch records that have <code>APP_ID IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XTabular> fetchByAppId(String... values) {
        return fetch(XTabular.X_TABULAR.APP_ID, values);
    }

    /**
     * Fetch records that have <code>ACTIVE IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XTabular> fetchByActive(Boolean... values) {
        return fetch(XTabular.X_TABULAR.ACTIVE, values);
    }

    /**
     * Fetch records that have <code>SIGMA IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XTabular> fetchBySigma(String... values) {
        return fetch(XTabular.X_TABULAR.SIGMA, values);
    }

    /**
     * Fetch records that have <code>METADATA IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XTabular> fetchByMetadata(String... values) {
        return fetch(XTabular.X_TABULAR.METADATA, values);
    }

    /**
     * Fetch records that have <code>LANGUAGE IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XTabular> fetchByLanguage(String... values) {
        return fetch(XTabular.X_TABULAR.LANGUAGE, values);
    }

    /**
     * Fetch records that have <code>CREATED_AT IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XTabular> fetchByCreatedAt(LocalDateTime... values) {
        return fetch(XTabular.X_TABULAR.CREATED_AT, values);
    }

    /**
     * Fetch records that have <code>CREATED_BY IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XTabular> fetchByCreatedBy(String... values) {
        return fetch(XTabular.X_TABULAR.CREATED_BY, values);
    }

    /**
     * Fetch records that have <code>UPDATED_AT IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XTabular> fetchByUpdatedAt(LocalDateTime... values) {
        return fetch(XTabular.X_TABULAR.UPDATED_AT, values);
    }

    /**
     * Fetch records that have <code>UPDATED_BY IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XTabular> fetchByUpdatedBy(String... values) {
        return fetch(XTabular.X_TABULAR.UPDATED_BY, values);
    }

    /**
     * Fetch records that have <code>KEY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XTabular>> fetchByKeyAsync(List<String> values) {
        return fetchAsync(XTabular.X_TABULAR.KEY,values);
    }

    /**
     * Fetch a unique record that has <code>KEY = value</code> asynchronously
     */
    public CompletableFuture<cn.vertxup.ambient.tables.pojos.XTabular> fetchOneByKeyAsync(String value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByKey(value)),vertx());
    }

    /**
     * Fetch records that have <code>NAME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XTabular>> fetchByNameAsync(List<String> values) {
        return fetchAsync(XTabular.X_TABULAR.NAME,values);
    }

    /**
     * Fetch records that have <code>CODE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XTabular>> fetchByCodeAsync(List<String> values) {
        return fetchAsync(XTabular.X_TABULAR.CODE,values);
    }

    /**
     * Fetch records that have <code>TYPE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XTabular>> fetchByTypeAsync(List<String> values) {
        return fetchAsync(XTabular.X_TABULAR.TYPE,values);
    }

    /**
     * Fetch records that have <code>ICON IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XTabular>> fetchByIconAsync(List<String> values) {
        return fetchAsync(XTabular.X_TABULAR.ICON,values);
    }

    /**
     * Fetch records that have <code>SORT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XTabular>> fetchBySortAsync(List<Integer> values) {
        return fetchAsync(XTabular.X_TABULAR.SORT,values);
    }

    /**
     * Fetch records that have <code>COMMENT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XTabular>> fetchByCommentAsync(List<String> values) {
        return fetchAsync(XTabular.X_TABULAR.COMMENT,values);
    }

    /**
     * Fetch records that have <code>APP_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XTabular>> fetchByAppIdAsync(List<String> values) {
        return fetchAsync(XTabular.X_TABULAR.APP_ID,values);
    }

    /**
     * Fetch records that have <code>ACTIVE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XTabular>> fetchByActiveAsync(List<Boolean> values) {
        return fetchAsync(XTabular.X_TABULAR.ACTIVE,values);
    }

    /**
     * Fetch records that have <code>SIGMA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XTabular>> fetchBySigmaAsync(List<String> values) {
        return fetchAsync(XTabular.X_TABULAR.SIGMA,values);
    }

    /**
     * Fetch records that have <code>METADATA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XTabular>> fetchByMetadataAsync(List<String> values) {
        return fetchAsync(XTabular.X_TABULAR.METADATA,values);
    }

    /**
     * Fetch records that have <code>LANGUAGE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XTabular>> fetchByLanguageAsync(List<String> values) {
        return fetchAsync(XTabular.X_TABULAR.LANGUAGE,values);
    }

    /**
     * Fetch records that have <code>CREATED_AT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XTabular>> fetchByCreatedAtAsync(List<LocalDateTime> values) {
        return fetchAsync(XTabular.X_TABULAR.CREATED_AT,values);
    }

    /**
     * Fetch records that have <code>CREATED_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XTabular>> fetchByCreatedByAsync(List<String> values) {
        return fetchAsync(XTabular.X_TABULAR.CREATED_BY,values);
    }

    /**
     * Fetch records that have <code>UPDATED_AT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XTabular>> fetchByUpdatedAtAsync(List<LocalDateTime> values) {
        return fetchAsync(XTabular.X_TABULAR.UPDATED_AT,values);
    }

    /**
     * Fetch records that have <code>UPDATED_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XTabular>> fetchByUpdatedByAsync(List<String> values) {
        return fetchAsync(XTabular.X_TABULAR.UPDATED_BY,values);
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
