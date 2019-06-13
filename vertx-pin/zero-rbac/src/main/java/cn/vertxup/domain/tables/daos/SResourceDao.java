/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.domain.tables.daos;


import cn.vertxup.domain.tables.SResource;
import cn.vertxup.domain.tables.records.SResourceRecord;

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
public class SResourceDao extends DAOImpl<SResourceRecord, cn.vertxup.domain.tables.pojos.SResource, String> implements VertxDAO<cn.vertxup.domain.tables.records.SResourceRecord,cn.vertxup.domain.tables.pojos.SResource,java.lang.String> {

    /**
     * Create a new SResourceDao without any configuration
     */
    public SResourceDao() {
        super(SResource.S_RESOURCE, cn.vertxup.domain.tables.pojos.SResource.class);
    }

    /**
     * Create a new SResourceDao with an attached configuration
     */
    public SResourceDao(Configuration configuration) {
        super(SResource.S_RESOURCE, cn.vertxup.domain.tables.pojos.SResource.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(cn.vertxup.domain.tables.pojos.SResource object) {
        return object.getKey();
    }

    /**
     * Fetch records that have <code>KEY IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchByKey(String... values) {
        return fetch(SResource.S_RESOURCE.KEY, values);
    }

    /**
     * Fetch a unique record that has <code>KEY = value</code>
     */
    public cn.vertxup.domain.tables.pojos.SResource fetchOneByKey(String value) {
        return fetchOne(SResource.S_RESOURCE.KEY, value);
    }

    /**
     * Fetch records that have <code>CODE IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchByCode(String... values) {
        return fetch(SResource.S_RESOURCE.CODE, values);
    }

    /**
     * Fetch records that have <code>NAME IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchByName(String... values) {
        return fetch(SResource.S_RESOURCE.NAME, values);
    }

    /**
     * Fetch records that have <code>COMMENT IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchByComment(String... values) {
        return fetch(SResource.S_RESOURCE.COMMENT, values);
    }

    /**
     * Fetch records that have <code>LEVEL IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchByLevel(Integer... values) {
        return fetch(SResource.S_RESOURCE.LEVEL, values);
    }

    /**
     * Fetch records that have <code>MODE_ROLE IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchByModeRole(String... values) {
        return fetch(SResource.S_RESOURCE.MODE_ROLE, values);
    }

    /**
     * Fetch records that have <code>MODE_GROUP IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchByModeGroup(String... values) {
        return fetch(SResource.S_RESOURCE.MODE_GROUP, values);
    }

    /**
     * Fetch records that have <code>MODE_TREE IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchByModeTree(String... values) {
        return fetch(SResource.S_RESOURCE.MODE_TREE, values);
    }

    /**
     * Fetch records that have <code>SIGMA IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchBySigma(String... values) {
        return fetch(SResource.S_RESOURCE.SIGMA, values);
    }

    /**
     * Fetch records that have <code>MODEL_ID IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchByModelId(String... values) {
        return fetch(SResource.S_RESOURCE.MODEL_ID, values);
    }

    /**
     * Fetch records that have <code>MODEL_KEY IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchByModelKey(String... values) {
        return fetch(SResource.S_RESOURCE.MODEL_KEY, values);
    }

    /**
     * Fetch records that have <code>CATEGORY IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchByCategory(String... values) {
        return fetch(SResource.S_RESOURCE.CATEGORY, values);
    }

    /**
     * Fetch records that have <code>LANGUAGE IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchByLanguage(String... values) {
        return fetch(SResource.S_RESOURCE.LANGUAGE, values);
    }

    /**
     * Fetch records that have <code>ACTIVE IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchByActive(Boolean... values) {
        return fetch(SResource.S_RESOURCE.ACTIVE, values);
    }

    /**
     * Fetch records that have <code>METADATA IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchByMetadata(String... values) {
        return fetch(SResource.S_RESOURCE.METADATA, values);
    }

    /**
     * Fetch records that have <code>CREATED_AT IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchByCreatedAt(LocalDateTime... values) {
        return fetch(SResource.S_RESOURCE.CREATED_AT, values);
    }

    /**
     * Fetch records that have <code>CREATED_BY IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchByCreatedBy(String... values) {
        return fetch(SResource.S_RESOURCE.CREATED_BY, values);
    }

    /**
     * Fetch records that have <code>UPDATED_AT IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchByUpdatedAt(LocalDateTime... values) {
        return fetch(SResource.S_RESOURCE.UPDATED_AT, values);
    }

    /**
     * Fetch records that have <code>UPDATED_BY IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.SResource> fetchByUpdatedBy(String... values) {
        return fetch(SResource.S_RESOURCE.UPDATED_BY, values);
    }

    /**
     * Fetch records that have <code>KEY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchByKeyAsync(List<String> values) {
        return fetchAsync(SResource.S_RESOURCE.KEY,values);
    }

    /**
     * Fetch a unique record that has <code>KEY = value</code> asynchronously
     */
    public CompletableFuture<cn.vertxup.domain.tables.pojos.SResource> fetchOneByKeyAsync(String value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByKey(value)),vertx());
    }

    /**
     * Fetch records that have <code>CODE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchByCodeAsync(List<String> values) {
        return fetchAsync(SResource.S_RESOURCE.CODE,values);
    }

    /**
     * Fetch records that have <code>NAME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchByNameAsync(List<String> values) {
        return fetchAsync(SResource.S_RESOURCE.NAME,values);
    }

    /**
     * Fetch records that have <code>COMMENT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchByCommentAsync(List<String> values) {
        return fetchAsync(SResource.S_RESOURCE.COMMENT,values);
    }

    /**
     * Fetch records that have <code>LEVEL IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchByLevelAsync(List<Integer> values) {
        return fetchAsync(SResource.S_RESOURCE.LEVEL,values);
    }

    /**
     * Fetch records that have <code>MODE_ROLE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchByModeRoleAsync(List<String> values) {
        return fetchAsync(SResource.S_RESOURCE.MODE_ROLE,values);
    }

    /**
     * Fetch records that have <code>MODE_GROUP IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchByModeGroupAsync(List<String> values) {
        return fetchAsync(SResource.S_RESOURCE.MODE_GROUP,values);
    }

    /**
     * Fetch records that have <code>MODE_TREE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchByModeTreeAsync(List<String> values) {
        return fetchAsync(SResource.S_RESOURCE.MODE_TREE,values);
    }

    /**
     * Fetch records that have <code>SIGMA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchBySigmaAsync(List<String> values) {
        return fetchAsync(SResource.S_RESOURCE.SIGMA,values);
    }

    /**
     * Fetch records that have <code>MODEL_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchByModelIdAsync(List<String> values) {
        return fetchAsync(SResource.S_RESOURCE.MODEL_ID,values);
    }

    /**
     * Fetch records that have <code>MODEL_KEY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchByModelKeyAsync(List<String> values) {
        return fetchAsync(SResource.S_RESOURCE.MODEL_KEY,values);
    }

    /**
     * Fetch records that have <code>CATEGORY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchByCategoryAsync(List<String> values) {
        return fetchAsync(SResource.S_RESOURCE.CATEGORY,values);
    }

    /**
     * Fetch records that have <code>LANGUAGE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchByLanguageAsync(List<String> values) {
        return fetchAsync(SResource.S_RESOURCE.LANGUAGE,values);
    }

    /**
     * Fetch records that have <code>ACTIVE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchByActiveAsync(List<Boolean> values) {
        return fetchAsync(SResource.S_RESOURCE.ACTIVE,values);
    }

    /**
     * Fetch records that have <code>METADATA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchByMetadataAsync(List<String> values) {
        return fetchAsync(SResource.S_RESOURCE.METADATA,values);
    }

    /**
     * Fetch records that have <code>CREATED_AT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchByCreatedAtAsync(List<LocalDateTime> values) {
        return fetchAsync(SResource.S_RESOURCE.CREATED_AT,values);
    }

    /**
     * Fetch records that have <code>CREATED_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchByCreatedByAsync(List<String> values) {
        return fetchAsync(SResource.S_RESOURCE.CREATED_BY,values);
    }

    /**
     * Fetch records that have <code>UPDATED_AT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchByUpdatedAtAsync(List<LocalDateTime> values) {
        return fetchAsync(SResource.S_RESOURCE.UPDATED_AT,values);
    }

    /**
     * Fetch records that have <code>UPDATED_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.SResource>> fetchByUpdatedByAsync(List<String> values) {
        return fetchAsync(SResource.S_RESOURCE.UPDATED_BY,values);
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
