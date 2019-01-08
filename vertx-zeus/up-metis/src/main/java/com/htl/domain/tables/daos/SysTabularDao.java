/*
 * This up.god.file is generated by jOOQ.
 */
package com.htl.domain.tables.daos;


import com.htl.domain.tables.SysTabular;
import com.htl.domain.tables.records.SysTabularRecord;

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
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class SysTabularDao extends DAOImpl<SysTabularRecord, com.htl.domain.tables.pojos.SysTabular, String> implements VertxDAO<com.htl.domain.tables.records.SysTabularRecord, com.htl.domain.tables.pojos.SysTabular, java.lang.String> {

    private io.vertx.core.Vertx vertx;

    /**
     * Create a new SysTabularDao without any configuration
     */
    public SysTabularDao() {
        super(SysTabular.SYS_TABULAR, com.htl.domain.tables.pojos.SysTabular.class);
    }

    /**
     * Create a new SysTabularDao with an attached configuration
     */
    public SysTabularDao(Configuration configuration) {
        super(SysTabular.SYS_TABULAR, com.htl.domain.tables.pojos.SysTabular.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(com.htl.domain.tables.pojos.SysTabular object) {
        return object.getPkId();
    }

    /**
     * Fetch records that have <code>PK_ID IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysTabular> fetchByPkId(String... values) {
        return fetch(SysTabular.SYS_TABULAR.PK_ID, values);
    }

    /**
     * Fetch a unique record that has <code>PK_ID = value</code>
     */
    public com.htl.domain.tables.pojos.SysTabular fetchOneByPkId(String value) {
        return fetchOne(SysTabular.SYS_TABULAR.PK_ID, value);
    }

    /**
     * Fetch records that have <code>T_COMMENT IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysTabular> fetchByTComment(String... values) {
        return fetch(SysTabular.SYS_TABULAR.T_COMMENT, values);
    }

    /**
     * Fetch records that have <code>S_NAME IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysTabular> fetchBySName(String... values) {
        return fetch(SysTabular.SYS_TABULAR.S_NAME, values);
    }

    /**
     * Fetch records that have <code>S_CODE IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysTabular> fetchBySCode(String... values) {
        return fetch(SysTabular.SYS_TABULAR.S_CODE, values);
    }

    /**
     * Fetch records that have <code>S_SERIAL IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysTabular> fetchBySSerial(String... values) {
        return fetch(SysTabular.SYS_TABULAR.S_SERIAL, values);
    }

    /**
     * Fetch records that have <code>S_TYPE IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysTabular> fetchBySType(String... values) {
        return fetch(SysTabular.SYS_TABULAR.S_TYPE, values);
    }

    /**
     * Fetch records that have <code>J_CONFIG IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysTabular> fetchByJConfig(String... values) {
        return fetch(SysTabular.SYS_TABULAR.J_CONFIG, values);
    }

    /**
     * Fetch records that have <code>I_ORDER IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysTabular> fetchByIOrder(Integer... values) {
        return fetch(SysTabular.SYS_TABULAR.I_ORDER, values);
    }

    /**
     * Fetch records that have <code>IS_ACTIVE IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysTabular> fetchByIsActive(Boolean... values) {
        return fetch(SysTabular.SYS_TABULAR.IS_ACTIVE, values);
    }

    /**
     * Fetch records that have <code>Z_SIGMA IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysTabular> fetchByZSigma(String... values) {
        return fetch(SysTabular.SYS_TABULAR.Z_SIGMA, values);
    }

    /**
     * Fetch records that have <code>Z_LANGUAGE IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysTabular> fetchByZLanguage(String... values) {
        return fetch(SysTabular.SYS_TABULAR.Z_LANGUAGE, values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_BY IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysTabular> fetchByZCreateBy(String... values) {
        return fetch(SysTabular.SYS_TABULAR.Z_CREATE_BY, values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_TIME IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysTabular> fetchByZCreateTime(LocalDateTime... values) {
        return fetch(SysTabular.SYS_TABULAR.Z_CREATE_TIME, values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_BY IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysTabular> fetchByZUpdateBy(String... values) {
        return fetch(SysTabular.SYS_TABULAR.Z_UPDATE_BY, values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_TIME IN (values)</code>
     */
    public List<com.htl.domain.tables.pojos.SysTabular> fetchByZUpdateTime(LocalDateTime... values) {
        return fetch(SysTabular.SYS_TABULAR.Z_UPDATE_TIME, values);
    }

    /**
     * Fetch records that have <code>PK_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysTabular>> fetchByPkIdAsync(List<String> values) {
        return fetchAsync(SysTabular.SYS_TABULAR.PK_ID, values);
    }

    /**
     * Fetch a unique record that has <code>PK_ID = value</code> asynchronously
     */
    public CompletableFuture<com.htl.domain.tables.pojos.SysTabular> fetchOneByPkIdAsync(String value) {
        return FutureTool.executeBlocking(h -> h.complete(fetchOneByPkId(value)), vertx());
    }

    /**
     * Fetch records that have <code>T_COMMENT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysTabular>> fetchByTCommentAsync(List<String> values) {
        return fetchAsync(SysTabular.SYS_TABULAR.T_COMMENT, values);
    }

    /**
     * Fetch records that have <code>S_NAME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysTabular>> fetchBySNameAsync(List<String> values) {
        return fetchAsync(SysTabular.SYS_TABULAR.S_NAME, values);
    }

    /**
     * Fetch records that have <code>S_CODE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysTabular>> fetchBySCodeAsync(List<String> values) {
        return fetchAsync(SysTabular.SYS_TABULAR.S_CODE, values);
    }

    /**
     * Fetch records that have <code>S_SERIAL IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysTabular>> fetchBySSerialAsync(List<String> values) {
        return fetchAsync(SysTabular.SYS_TABULAR.S_SERIAL, values);
    }

    /**
     * Fetch records that have <code>S_TYPE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysTabular>> fetchBySTypeAsync(List<String> values) {
        return fetchAsync(SysTabular.SYS_TABULAR.S_TYPE, values);
    }

    /**
     * Fetch records that have <code>J_CONFIG IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysTabular>> fetchByJConfigAsync(List<String> values) {
        return fetchAsync(SysTabular.SYS_TABULAR.J_CONFIG, values);
    }

    /**
     * Fetch records that have <code>I_ORDER IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysTabular>> fetchByIOrderAsync(List<Integer> values) {
        return fetchAsync(SysTabular.SYS_TABULAR.I_ORDER, values);
    }

    /**
     * Fetch records that have <code>IS_ACTIVE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysTabular>> fetchByIsActiveAsync(List<Boolean> values) {
        return fetchAsync(SysTabular.SYS_TABULAR.IS_ACTIVE, values);
    }

    /**
     * Fetch records that have <code>Z_SIGMA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysTabular>> fetchByZSigmaAsync(List<String> values) {
        return fetchAsync(SysTabular.SYS_TABULAR.Z_SIGMA, values);
    }

    /**
     * Fetch records that have <code>Z_LANGUAGE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysTabular>> fetchByZLanguageAsync(List<String> values) {
        return fetchAsync(SysTabular.SYS_TABULAR.Z_LANGUAGE, values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysTabular>> fetchByZCreateByAsync(List<String> values) {
        return fetchAsync(SysTabular.SYS_TABULAR.Z_CREATE_BY, values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_TIME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysTabular>> fetchByZCreateTimeAsync(List<LocalDateTime> values) {
        return fetchAsync(SysTabular.SYS_TABULAR.Z_CREATE_TIME, values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysTabular>> fetchByZUpdateByAsync(List<String> values) {
        return fetchAsync(SysTabular.SYS_TABULAR.Z_UPDATE_BY, values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_TIME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<com.htl.domain.tables.pojos.SysTabular>> fetchByZUpdateTimeAsync(List<LocalDateTime> values) {
        return fetchAsync(SysTabular.SYS_TABULAR.Z_UPDATE_TIME, values);
    }

    @Override
    public void setVertx(io.vertx.core.Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public io.vertx.core.Vertx vertx() {
        return this.vertx;
    }

}
