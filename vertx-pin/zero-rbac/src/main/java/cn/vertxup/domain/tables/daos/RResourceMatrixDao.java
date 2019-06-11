/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.domain.tables.daos;


import cn.vertxup.domain.tables.RResourceMatrix;
import cn.vertxup.domain.tables.records.RResourceMatrixRecord;

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
public class RResourceMatrixDao extends DAOImpl<RResourceMatrixRecord, cn.vertxup.domain.tables.pojos.RResourceMatrix, String> implements VertxDAO<cn.vertxup.domain.tables.records.RResourceMatrixRecord,cn.vertxup.domain.tables.pojos.RResourceMatrix,java.lang.String> {

    /**
     * Create a new RResourceMatrixDao without any configuration
     */
    public RResourceMatrixDao() {
        super(RResourceMatrix.R_RESOURCE_MATRIX, cn.vertxup.domain.tables.pojos.RResourceMatrix.class);
    }

    /**
     * Create a new RResourceMatrixDao with an attached configuration
     */
    public RResourceMatrixDao(Configuration configuration) {
        super(RResourceMatrix.R_RESOURCE_MATRIX, cn.vertxup.domain.tables.pojos.RResourceMatrix.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(cn.vertxup.domain.tables.pojos.RResourceMatrix object) {
        return object.getKey();
    }

    /**
     * Fetch records that have <code>KEY IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.RResourceMatrix> fetchByKey(String... values) {
        return fetch(RResourceMatrix.R_RESOURCE_MATRIX.KEY, values);
    }

    /**
     * Fetch a unique record that has <code>KEY = value</code>
     */
    public cn.vertxup.domain.tables.pojos.RResourceMatrix fetchOneByKey(String value) {
        return fetchOne(RResourceMatrix.R_RESOURCE_MATRIX.KEY, value);
    }

    /**
     * Fetch records that have <code>USER_ID IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.RResourceMatrix> fetchByUserId(String... values) {
        return fetch(RResourceMatrix.R_RESOURCE_MATRIX.USER_ID, values);
    }

    /**
     * Fetch records that have <code>RESOURCE_ID IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.RResourceMatrix> fetchByResourceId(String... values) {
        return fetch(RResourceMatrix.R_RESOURCE_MATRIX.RESOURCE_ID, values);
    }

    /**
     * Fetch records that have <code>PROJECTION IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.RResourceMatrix> fetchByProjection(String... values) {
        return fetch(RResourceMatrix.R_RESOURCE_MATRIX.PROJECTION, values);
    }

    /**
     * Fetch records that have <code>QUERY IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.RResourceMatrix> fetchByQuery(String... values) {
        return fetch(RResourceMatrix.R_RESOURCE_MATRIX.QUERY, values);
    }

    /**
     * Fetch records that have <code>MODE_PROJECTION IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.RResourceMatrix> fetchByModeProjection(String... values) {
        return fetch(RResourceMatrix.R_RESOURCE_MATRIX.MODE_PROJECTION, values);
    }

    /**
     * Fetch records that have <code>MODE_QUERY IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.RResourceMatrix> fetchByModeQuery(String... values) {
        return fetch(RResourceMatrix.R_RESOURCE_MATRIX.MODE_QUERY, values);
    }

    /**
     * Fetch records that have <code>SIGMA IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.RResourceMatrix> fetchBySigma(String... values) {
        return fetch(RResourceMatrix.R_RESOURCE_MATRIX.SIGMA, values);
    }

    /**
     * Fetch records that have <code>KEY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.RResourceMatrix>> fetchByKeyAsync(List<String> values) {
        return fetchAsync(RResourceMatrix.R_RESOURCE_MATRIX.KEY,values);
    }

    /**
     * Fetch a unique record that has <code>KEY = value</code> asynchronously
     */
    public CompletableFuture<cn.vertxup.domain.tables.pojos.RResourceMatrix> fetchOneByKeyAsync(String value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByKey(value)),vertx());
    }

    /**
     * Fetch records that have <code>USER_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.RResourceMatrix>> fetchByUserIdAsync(List<String> values) {
        return fetchAsync(RResourceMatrix.R_RESOURCE_MATRIX.USER_ID,values);
    }

    /**
     * Fetch records that have <code>RESOURCE_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.RResourceMatrix>> fetchByResourceIdAsync(List<String> values) {
        return fetchAsync(RResourceMatrix.R_RESOURCE_MATRIX.RESOURCE_ID,values);
    }

    /**
     * Fetch records that have <code>PROJECTION IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.RResourceMatrix>> fetchByProjectionAsync(List<String> values) {
        return fetchAsync(RResourceMatrix.R_RESOURCE_MATRIX.PROJECTION,values);
    }

    /**
     * Fetch records that have <code>QUERY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.RResourceMatrix>> fetchByQueryAsync(List<String> values) {
        return fetchAsync(RResourceMatrix.R_RESOURCE_MATRIX.QUERY,values);
    }

    /**
     * Fetch records that have <code>MODE_PROJECTION IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.RResourceMatrix>> fetchByModeProjectionAsync(List<String> values) {
        return fetchAsync(RResourceMatrix.R_RESOURCE_MATRIX.MODE_PROJECTION,values);
    }

    /**
     * Fetch records that have <code>MODE_QUERY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.RResourceMatrix>> fetchByModeQueryAsync(List<String> values) {
        return fetchAsync(RResourceMatrix.R_RESOURCE_MATRIX.MODE_QUERY,values);
    }

    /**
     * Fetch records that have <code>SIGMA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.RResourceMatrix>> fetchBySigmaAsync(List<String> values) {
        return fetchAsync(RResourceMatrix.R_RESOURCE_MATRIX.SIGMA,values);
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
