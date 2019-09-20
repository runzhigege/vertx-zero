/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ui.domain.tables.daos;


import cn.vertxup.ui.domain.tables.UiColumn;
import cn.vertxup.ui.domain.tables.records.UiColumnRecord;

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
public class UiColumnDao extends DAOImpl<UiColumnRecord, cn.vertxup.ui.domain.tables.pojos.UiColumn, String> implements VertxDAO<cn.vertxup.ui.domain.tables.records.UiColumnRecord,cn.vertxup.ui.domain.tables.pojos.UiColumn,java.lang.String> {

    /**
     * Create a new UiColumnDao without any configuration
     */
    public UiColumnDao() {
        super(UiColumn.UI_COLUMN, cn.vertxup.ui.domain.tables.pojos.UiColumn.class);
    }

    /**
     * Create a new UiColumnDao with an attached configuration
     */
    public UiColumnDao(Configuration configuration) {
        super(UiColumn.UI_COLUMN, cn.vertxup.ui.domain.tables.pojos.UiColumn.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(cn.vertxup.ui.domain.tables.pojos.UiColumn object) {
        return object.getKey();
    }

    /**
     * Fetch records that have <code>KEY IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByKey(String... values) {
        return fetch(UiColumn.UI_COLUMN.KEY, values);
    }

    /**
     * Fetch a unique record that has <code>KEY = value</code>
     */
    public cn.vertxup.ui.domain.tables.pojos.UiColumn fetchOneByKey(String value) {
        return fetchOne(UiColumn.UI_COLUMN.KEY, value);
    }

    /**
     * Fetch records that have <code>TITLE IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByTitle(String... values) {
        return fetch(UiColumn.UI_COLUMN.TITLE, values);
    }

    /**
     * Fetch records that have <code>DATA_INDEX IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByDataIndex(String... values) {
        return fetch(UiColumn.UI_COLUMN.DATA_INDEX, values);
    }

    /**
     * Fetch records that have <code>RENDER IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByRender(String... values) {
        return fetch(UiColumn.UI_COLUMN.RENDER, values);
    }

    /**
     * Fetch records that have <code>WIDTH IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByWidth(Integer... values) {
        return fetch(UiColumn.UI_COLUMN.WIDTH, values);
    }

    /**
     * Fetch records that have <code>FIXED IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByFixed(Boolean... values) {
        return fetch(UiColumn.UI_COLUMN.FIXED, values);
    }

    /**
     * Fetch records that have <code>CLASS_NAME IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByClassName(String... values) {
        return fetch(UiColumn.UI_COLUMN.CLASS_NAME, values);
    }

    /**
     * Fetch records that have <code>SORTER IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchBySorter(Boolean... values) {
        return fetch(UiColumn.UI_COLUMN.SORTER, values);
    }

    /**
     * Fetch records that have <code>FILTER_TYPE IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByFilterType(String... values) {
        return fetch(UiColumn.UI_COLUMN.FILTER_TYPE, values);
    }

    /**
     * Fetch records that have <code>FILTER_CONFIG IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByFilterConfig(String... values) {
        return fetch(UiColumn.UI_COLUMN.FILTER_CONFIG, values);
    }

    /**
     * Fetch records that have <code>EMPTY IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByEmpty(String... values) {
        return fetch(UiColumn.UI_COLUMN.EMPTY, values);
    }

    /**
     * Fetch records that have <code>MAPPING IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByMapping(String... values) {
        return fetch(UiColumn.UI_COLUMN.MAPPING, values);
    }

    /**
     * Fetch records that have <code>CONFIG IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByConfig(String... values) {
        return fetch(UiColumn.UI_COLUMN.CONFIG, values);
    }

    /**
     * Fetch records that have <code>OPTION IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByOption(String... values) {
        return fetch(UiColumn.UI_COLUMN.OPTION, values);
    }

    /**
     * Fetch records that have <code>FORMAT IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByFormat(String... values) {
        return fetch(UiColumn.UI_COLUMN.FORMAT, values);
    }

    /**
     * Fetch records that have <code>CONTROL_ID IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByControlId(String... values) {
        return fetch(UiColumn.UI_COLUMN.CONTROL_ID, values);
    }

    /**
     * Fetch records that have <code>ACTIVE IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByActive(Boolean... values) {
        return fetch(UiColumn.UI_COLUMN.ACTIVE, values);
    }

    /**
     * Fetch records that have <code>SIGMA IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchBySigma(String... values) {
        return fetch(UiColumn.UI_COLUMN.SIGMA, values);
    }

    /**
     * Fetch records that have <code>METADATA IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByMetadata(String... values) {
        return fetch(UiColumn.UI_COLUMN.METADATA, values);
    }

    /**
     * Fetch records that have <code>LANGUAGE IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByLanguage(String... values) {
        return fetch(UiColumn.UI_COLUMN.LANGUAGE, values);
    }

    /**
     * Fetch records that have <code>CREATED_AT IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByCreatedAt(LocalDateTime... values) {
        return fetch(UiColumn.UI_COLUMN.CREATED_AT, values);
    }

    /**
     * Fetch records that have <code>CREATED_BY IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByCreatedBy(String... values) {
        return fetch(UiColumn.UI_COLUMN.CREATED_BY, values);
    }

    /**
     * Fetch records that have <code>UPDATED_AT IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByUpdatedAt(LocalDateTime... values) {
        return fetch(UiColumn.UI_COLUMN.UPDATED_AT, values);
    }

    /**
     * Fetch records that have <code>UPDATED_BY IN (values)</code>
     */
    public List<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchByUpdatedBy(String... values) {
        return fetch(UiColumn.UI_COLUMN.UPDATED_BY, values);
    }

    /**
     * Fetch records that have <code>KEY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByKeyAsync(List<String> values) {
        return fetchAsync(UiColumn.UI_COLUMN.KEY,values);
    }

    /**
     * Fetch a unique record that has <code>KEY = value</code> asynchronously
     */
    public CompletableFuture<cn.vertxup.ui.domain.tables.pojos.UiColumn> fetchOneByKeyAsync(String value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByKey(value)),vertx());
    }

    /**
     * Fetch records that have <code>TITLE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByTitleAsync(List<String> values) {
        return fetchAsync(UiColumn.UI_COLUMN.TITLE,values);
    }

    /**
     * Fetch records that have <code>DATA_INDEX IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByDataIndexAsync(List<String> values) {
        return fetchAsync(UiColumn.UI_COLUMN.DATA_INDEX,values);
    }

    /**
     * Fetch records that have <code>RENDER IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByRenderAsync(List<String> values) {
        return fetchAsync(UiColumn.UI_COLUMN.RENDER,values);
    }

    /**
     * Fetch records that have <code>WIDTH IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByWidthAsync(List<Integer> values) {
        return fetchAsync(UiColumn.UI_COLUMN.WIDTH,values);
    }

    /**
     * Fetch records that have <code>FIXED IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByFixedAsync(List<Boolean> values) {
        return fetchAsync(UiColumn.UI_COLUMN.FIXED,values);
    }

    /**
     * Fetch records that have <code>CLASS_NAME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByClassNameAsync(List<String> values) {
        return fetchAsync(UiColumn.UI_COLUMN.CLASS_NAME,values);
    }

    /**
     * Fetch records that have <code>SORTER IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchBySorterAsync(List<Boolean> values) {
        return fetchAsync(UiColumn.UI_COLUMN.SORTER,values);
    }

    /**
     * Fetch records that have <code>FILTER_TYPE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByFilterTypeAsync(List<String> values) {
        return fetchAsync(UiColumn.UI_COLUMN.FILTER_TYPE,values);
    }

    /**
     * Fetch records that have <code>FILTER_CONFIG IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByFilterConfigAsync(List<String> values) {
        return fetchAsync(UiColumn.UI_COLUMN.FILTER_CONFIG,values);
    }

    /**
     * Fetch records that have <code>EMPTY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByEmptyAsync(List<String> values) {
        return fetchAsync(UiColumn.UI_COLUMN.EMPTY,values);
    }

    /**
     * Fetch records that have <code>MAPPING IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByMappingAsync(List<String> values) {
        return fetchAsync(UiColumn.UI_COLUMN.MAPPING,values);
    }

    /**
     * Fetch records that have <code>CONFIG IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByConfigAsync(List<String> values) {
        return fetchAsync(UiColumn.UI_COLUMN.CONFIG,values);
    }

    /**
     * Fetch records that have <code>OPTION IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByOptionAsync(List<String> values) {
        return fetchAsync(UiColumn.UI_COLUMN.OPTION,values);
    }

    /**
     * Fetch records that have <code>FORMAT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByFormatAsync(List<String> values) {
        return fetchAsync(UiColumn.UI_COLUMN.FORMAT,values);
    }

    /**
     * Fetch records that have <code>CONTROL_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByControlIdAsync(List<String> values) {
        return fetchAsync(UiColumn.UI_COLUMN.CONTROL_ID,values);
    }

    /**
     * Fetch records that have <code>ACTIVE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByActiveAsync(List<Boolean> values) {
        return fetchAsync(UiColumn.UI_COLUMN.ACTIVE,values);
    }

    /**
     * Fetch records that have <code>SIGMA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchBySigmaAsync(List<String> values) {
        return fetchAsync(UiColumn.UI_COLUMN.SIGMA,values);
    }

    /**
     * Fetch records that have <code>METADATA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByMetadataAsync(List<String> values) {
        return fetchAsync(UiColumn.UI_COLUMN.METADATA,values);
    }

    /**
     * Fetch records that have <code>LANGUAGE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByLanguageAsync(List<String> values) {
        return fetchAsync(UiColumn.UI_COLUMN.LANGUAGE,values);
    }

    /**
     * Fetch records that have <code>CREATED_AT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByCreatedAtAsync(List<LocalDateTime> values) {
        return fetchAsync(UiColumn.UI_COLUMN.CREATED_AT,values);
    }

    /**
     * Fetch records that have <code>CREATED_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByCreatedByAsync(List<String> values) {
        return fetchAsync(UiColumn.UI_COLUMN.CREATED_BY,values);
    }

    /**
     * Fetch records that have <code>UPDATED_AT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByUpdatedAtAsync(List<LocalDateTime> values) {
        return fetchAsync(UiColumn.UI_COLUMN.UPDATED_AT,values);
    }

    /**
     * Fetch records that have <code>UPDATED_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ui.domain.tables.pojos.UiColumn>> fetchByUpdatedByAsync(List<String> values) {
        return fetchAsync(UiColumn.UI_COLUMN.UPDATED_BY,values);
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
