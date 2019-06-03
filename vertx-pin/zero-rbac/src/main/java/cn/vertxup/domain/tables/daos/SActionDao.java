/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.domain.tables.daos;


import cn.vertxup.domain.tables.SAction;
import cn.vertxup.domain.tables.records.SActionRecord;

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
public class SActionDao extends DAOImpl<SActionRecord, cn.vertxup.domain.tables.pojos.SAction, String> implements VertxDAO<cn.vertxup.domain.tables.records.SActionRecord,cn.vertxup.domain.tables.pojos.SAction,java.lang.String> {

    /**
     * Create a new SActionDao without any configuration
     */
    public SActionDao() {
        super(SAction.S_ACTION, cn.vertxup.domain.tables.pojos.SAction.class);
    }

    /**
     * Create a new SActionDao with an attached configuration
     */
    public SActionDao(Configuration configuration) {
        super(SAction.S_ACTION, cn.vertxup.domain.tables.pojos.SAction.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(cn.vertxup.domain.tables.pojos.SAction object) {
        return object.getKey();
    }

    /**
     * Fetch records that have <code>KEY IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SAction> fetchByKey(String... values) {
        return fetch(SAction.S_ACTION.KEY, values);
    }

    /**
     * Fetch a unique record that has <code>KEY = value</code>
     */
    public cn.vertxup.domain.tables.pojos.SAction fetchOneByKey(String value) {
        return fetchOne(SAction.S_ACTION.KEY, value);
    }

    /**
     * Fetch records that have <code>NAME IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SAction> fetchByName(String... values) {
        return fetch(SAction.S_ACTION.NAME, values);
    }

    /**
     * Fetch records that have <code>CODE IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SAction> fetchByCode(String... values) {
        return fetch(SAction.S_ACTION.CODE, values);
    }

    /**
     * Fetch records that have <code>LEVEL IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SAction> fetchByLevel(Integer... values) {
        return fetch(SAction.S_ACTION.LEVEL, values);
    }

    /**
     * Fetch records that have <code>PRIORITY IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SAction> fetchByPriority(String... values) {
        return fetch(SAction.S_ACTION.PRIORITY, values);
    }

    /**
     * Fetch records that have <code>RESOURCE_ID IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SAction> fetchByResourceId(String... values) {
        return fetch(SAction.S_ACTION.RESOURCE_ID, values);
    }

    /**
     * Fetch a unique record that has <code>RESOURCE_ID = value</code>
     */
    public cn.vertxup.domain.tables.pojos.SAction fetchOneByResourceId(String value) {
        return fetchOne(SAction.S_ACTION.RESOURCE_ID, value);
    }

    /**
     * Fetch records that have <code>CATEGORY IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SAction> fetchByCategory(String... values) {
        return fetch(SAction.S_ACTION.CATEGORY, values);
    }

    /**
     * Fetch records that have <code>SIGMA IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SAction> fetchBySigma(String... values) {
        return fetch(SAction.S_ACTION.SIGMA, values);
    }

    /**
     * Fetch records that have <code>LANGUAGE IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SAction> fetchByLanguage(String... values) {
        return fetch(SAction.S_ACTION.LANGUAGE, values);
    }

    /**
     * Fetch records that have <code>ACTIVE IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SAction> fetchByActive(Boolean... values) {
        return fetch(SAction.S_ACTION.ACTIVE, values);
    }

    /**
     * Fetch records that have <code>METADATA IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SAction> fetchByMetadata(String... values) {
        return fetch(SAction.S_ACTION.METADATA, values);
    }

    /**
     * Fetch records that have <code>CREATED_AT IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SAction> fetchByCreatedAt(LocalDateTime... values) {
        return fetch(SAction.S_ACTION.CREATED_AT, values);
    }

    /**
     * Fetch records that have <code>CREATED_BY IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SAction> fetchByCreatedBy(String... values) {
        return fetch(SAction.S_ACTION.CREATED_BY, values);
    }

    /**
     * Fetch records that have <code>UPDATED_AT IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SAction> fetchByUpdatedAt(LocalDateTime... values) {
        return fetch(SAction.S_ACTION.UPDATED_AT, values);
    }

    /**
     * Fetch records that have <code>UPDATED_BY IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SAction> fetchByUpdatedBy(String... values) {
        return fetch(SAction.S_ACTION.UPDATED_BY, values);
    }

    /**
     * Fetch records that have <code>KEY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SAction>> fetchByKeyAsync(List<String> values) {
        return fetchAsync(SAction.S_ACTION.KEY,values);
    }

    /**
     * Fetch a unique record that has <code>KEY = value</code> asynchronously
     */
    public CompletableFuture<cn.vertxup.domain.tables.pojos.SAction> fetchOneByKeyAsync(String value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByKey(value)),vertx());
    }

    /**
     * Fetch records that have <code>NAME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SAction>> fetchByNameAsync(List<String> values) {
        return fetchAsync(SAction.S_ACTION.NAME,values);
    }

    /**
     * Fetch records that have <code>CODE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SAction>> fetchByCodeAsync(List<String> values) {
        return fetchAsync(SAction.S_ACTION.CODE,values);
    }

    /**
     * Fetch records that have <code>LEVEL IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SAction>> fetchByLevelAsync(List<Integer> values) {
        return fetchAsync(SAction.S_ACTION.LEVEL,values);
    }

    /**
     * Fetch records that have <code>PRIORITY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SAction>> fetchByPriorityAsync(List<String> values) {
        return fetchAsync(SAction.S_ACTION.PRIORITY,values);
    }

    /**
     * Fetch records that have <code>RESOURCE_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SAction>> fetchByResourceIdAsync(List<String> values) {
        return fetchAsync(SAction.S_ACTION.RESOURCE_ID,values);
    }

    /**
     * Fetch a unique record that has <code>RESOURCE_ID = value</code> asynchronously
     */
    public CompletableFuture<cn.vertxup.domain.tables.pojos.SAction> fetchOneByResourceIdAsync(String value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByResourceId(value)),vertx());
    }

    /**
     * Fetch records that have <code>CATEGORY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SAction>> fetchByCategoryAsync(List<String> values) {
        return fetchAsync(SAction.S_ACTION.CATEGORY,values);
    }

    /**
     * Fetch records that have <code>SIGMA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SAction>> fetchBySigmaAsync(List<String> values) {
        return fetchAsync(SAction.S_ACTION.SIGMA,values);
    }

    /**
     * Fetch records that have <code>LANGUAGE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SAction>> fetchByLanguageAsync(List<String> values) {
        return fetchAsync(SAction.S_ACTION.LANGUAGE,values);
    }

    /**
     * Fetch records that have <code>ACTIVE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SAction>> fetchByActiveAsync(List<Boolean> values) {
        return fetchAsync(SAction.S_ACTION.ACTIVE,values);
    }

    /**
     * Fetch records that have <code>METADATA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SAction>> fetchByMetadataAsync(List<String> values) {
        return fetchAsync(SAction.S_ACTION.METADATA,values);
    }

    /**
     * Fetch records that have <code>CREATED_AT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SAction>> fetchByCreatedAtAsync(List<LocalDateTime> values) {
        return fetchAsync(SAction.S_ACTION.CREATED_AT,values);
    }

    /**
     * Fetch records that have <code>CREATED_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SAction>> fetchByCreatedByAsync(List<String> values) {
        return fetchAsync(SAction.S_ACTION.CREATED_BY,values);
    }

    /**
     * Fetch records that have <code>UPDATED_AT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SAction>> fetchByUpdatedAtAsync(List<LocalDateTime> values) {
        return fetchAsync(SAction.S_ACTION.UPDATED_AT,values);
    }

    /**
     * Fetch records that have <code>UPDATED_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SAction>> fetchByUpdatedByAsync(List<String> values) {
        return fetchAsync(SAction.S_ACTION.UPDATED_BY,values);
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
