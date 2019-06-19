/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ambient.tables.daos;


import cn.vertxup.ambient.tables.XAttachment;
import cn.vertxup.ambient.tables.records.XAttachmentRecord;

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
public class XAttachmentDao extends DAOImpl<XAttachmentRecord, cn.vertxup.ambient.tables.pojos.XAttachment, String> implements VertxDAO<cn.vertxup.ambient.tables.records.XAttachmentRecord,cn.vertxup.ambient.tables.pojos.XAttachment,java.lang.String> {

    /**
     * Create a new XAttachmentDao without any configuration
     */
    public XAttachmentDao() {
        super(XAttachment.X_ATTACHMENT, cn.vertxup.ambient.tables.pojos.XAttachment.class);
    }

    /**
     * Create a new XAttachmentDao with an attached configuration
     */
    public XAttachmentDao(Configuration configuration) {
        super(XAttachment.X_ATTACHMENT, cn.vertxup.ambient.tables.pojos.XAttachment.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(cn.vertxup.ambient.tables.pojos.XAttachment object) {
        return object.getKey();
    }

    /**
     * Fetch records that have <code>KEY IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchByKey(String... values) {
        return fetch(XAttachment.X_ATTACHMENT.KEY, values);
    }

    /**
     * Fetch a unique record that has <code>KEY = value</code>
     */
    public cn.vertxup.ambient.tables.pojos.XAttachment fetchOneByKey(String value) {
        return fetchOne(XAttachment.X_ATTACHMENT.KEY, value);
    }

    /**
     * Fetch records that have <code>STORE_WAY IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchByStoreWay(String... values) {
        return fetch(XAttachment.X_ATTACHMENT.STORE_WAY, values);
    }

    /**
     * Fetch records that have <code>STATUS IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchByStatus(String... values) {
        return fetch(XAttachment.X_ATTACHMENT.STATUS, values);
    }

    /**
     * Fetch records that have <code>NAME IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchByName(String... values) {
        return fetch(XAttachment.X_ATTACHMENT.NAME, values);
    }

    /**
     * Fetch records that have <code>FILE_NAME IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchByFileName(String... values) {
        return fetch(XAttachment.X_ATTACHMENT.FILE_NAME, values);
    }

    /**
     * Fetch records that have <code>FILE_KEY IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchByFileKey(String... values) {
        return fetch(XAttachment.X_ATTACHMENT.FILE_KEY, values);
    }

    /**
     * Fetch a unique record that has <code>FILE_KEY = value</code>
     */
    public cn.vertxup.ambient.tables.pojos.XAttachment fetchOneByFileKey(String value) {
        return fetchOne(XAttachment.X_ATTACHMENT.FILE_KEY, value);
    }

    /**
     * Fetch records that have <code>FILE_URL IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchByFileUrl(String... values) {
        return fetch(XAttachment.X_ATTACHMENT.FILE_URL, values);
    }

    /**
     * Fetch a unique record that has <code>FILE_URL = value</code>
     */
    public cn.vertxup.ambient.tables.pojos.XAttachment fetchOneByFileUrl(String value) {
        return fetchOne(XAttachment.X_ATTACHMENT.FILE_URL, value);
    }

    /**
     * Fetch records that have <code>FILE_PATH IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchByFilePath(String... values) {
        return fetch(XAttachment.X_ATTACHMENT.FILE_PATH, values);
    }

    /**
     * Fetch a unique record that has <code>FILE_PATH = value</code>
     */
    public cn.vertxup.ambient.tables.pojos.XAttachment fetchOneByFilePath(String value) {
        return fetchOne(XAttachment.X_ATTACHMENT.FILE_PATH, value);
    }

    /**
     * Fetch records that have <code>EXTENSION IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchByExtension(String... values) {
        return fetch(XAttachment.X_ATTACHMENT.EXTENSION, values);
    }

    /**
     * Fetch records that have <code>MODULE IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchByModule(String... values) {
        return fetch(XAttachment.X_ATTACHMENT.MODULE, values);
    }

    /**
     * Fetch records that have <code>MIME IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchByMime(String... values) {
        return fetch(XAttachment.X_ATTACHMENT.MIME, values);
    }

    /**
     * Fetch records that have <code>SIZE IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchBySize(Integer... values) {
        return fetch(XAttachment.X_ATTACHMENT.SIZE, values);
    }

    /**
     * Fetch records that have <code>ACTIVE IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchByActive(Boolean... values) {
        return fetch(XAttachment.X_ATTACHMENT.ACTIVE, values);
    }

    /**
     * Fetch records that have <code>SIGMA IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchBySigma(String... values) {
        return fetch(XAttachment.X_ATTACHMENT.SIGMA, values);
    }

    /**
     * Fetch records that have <code>METADATA IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchByMetadata(String... values) {
        return fetch(XAttachment.X_ATTACHMENT.METADATA, values);
    }

    /**
     * Fetch records that have <code>LANGUAGE IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchByLanguage(String... values) {
        return fetch(XAttachment.X_ATTACHMENT.LANGUAGE, values);
    }

    /**
     * Fetch records that have <code>CREATED_AT IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchByCreatedAt(LocalDateTime... values) {
        return fetch(XAttachment.X_ATTACHMENT.CREATED_AT, values);
    }

    /**
     * Fetch records that have <code>CREATED_BY IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchByCreatedBy(String... values) {
        return fetch(XAttachment.X_ATTACHMENT.CREATED_BY, values);
    }

    /**
     * Fetch records that have <code>UPDATED_AT IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchByUpdatedAt(LocalDateTime... values) {
        return fetch(XAttachment.X_ATTACHMENT.UPDATED_AT, values);
    }

    /**
     * Fetch records that have <code>UPDATED_BY IN (values)</code>
     */
    public List<cn.vertxup.ambient.tables.pojos.XAttachment> fetchByUpdatedBy(String... values) {
        return fetch(XAttachment.X_ATTACHMENT.UPDATED_BY, values);
    }

    /**
     * Fetch records that have <code>KEY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchByKeyAsync(List<String> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.KEY,values);
    }

    /**
     * Fetch a unique record that has <code>KEY = value</code> asynchronously
     */
    public CompletableFuture<cn.vertxup.ambient.tables.pojos.XAttachment> fetchOneByKeyAsync(String value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByKey(value)),vertx());
    }

    /**
     * Fetch records that have <code>STORE_WAY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchByStoreWayAsync(List<String> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.STORE_WAY,values);
    }

    /**
     * Fetch records that have <code>STATUS IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchByStatusAsync(List<String> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.STATUS,values);
    }

    /**
     * Fetch records that have <code>NAME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchByNameAsync(List<String> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.NAME,values);
    }

    /**
     * Fetch records that have <code>FILE_NAME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchByFileNameAsync(List<String> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.FILE_NAME,values);
    }

    /**
     * Fetch records that have <code>FILE_KEY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchByFileKeyAsync(List<String> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.FILE_KEY,values);
    }

    /**
     * Fetch a unique record that has <code>FILE_KEY = value</code> asynchronously
     */
    public CompletableFuture<cn.vertxup.ambient.tables.pojos.XAttachment> fetchOneByFileKeyAsync(String value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByFileKey(value)),vertx());
    }

    /**
     * Fetch records that have <code>FILE_URL IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchByFileUrlAsync(List<String> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.FILE_URL,values);
    }

    /**
     * Fetch a unique record that has <code>FILE_URL = value</code> asynchronously
     */
    public CompletableFuture<cn.vertxup.ambient.tables.pojos.XAttachment> fetchOneByFileUrlAsync(String value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByFileUrl(value)),vertx());
    }

    /**
     * Fetch records that have <code>FILE_PATH IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchByFilePathAsync(List<String> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.FILE_PATH,values);
    }

    /**
     * Fetch a unique record that has <code>FILE_PATH = value</code> asynchronously
     */
    public CompletableFuture<cn.vertxup.ambient.tables.pojos.XAttachment> fetchOneByFilePathAsync(String value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByFilePath(value)),vertx());
    }

    /**
     * Fetch records that have <code>EXTENSION IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchByExtensionAsync(List<String> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.EXTENSION,values);
    }

    /**
     * Fetch records that have <code>MODULE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchByModuleAsync(List<String> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.MODULE,values);
    }

    /**
     * Fetch records that have <code>MIME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchByMimeAsync(List<String> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.MIME,values);
    }

    /**
     * Fetch records that have <code>SIZE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchBySizeAsync(List<Integer> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.SIZE,values);
    }

    /**
     * Fetch records that have <code>ACTIVE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchByActiveAsync(List<Boolean> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.ACTIVE,values);
    }

    /**
     * Fetch records that have <code>SIGMA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchBySigmaAsync(List<String> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.SIGMA,values);
    }

    /**
     * Fetch records that have <code>METADATA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchByMetadataAsync(List<String> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.METADATA,values);
    }

    /**
     * Fetch records that have <code>LANGUAGE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchByLanguageAsync(List<String> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.LANGUAGE,values);
    }

    /**
     * Fetch records that have <code>CREATED_AT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchByCreatedAtAsync(List<LocalDateTime> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.CREATED_AT,values);
    }

    /**
     * Fetch records that have <code>CREATED_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchByCreatedByAsync(List<String> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.CREATED_BY,values);
    }

    /**
     * Fetch records that have <code>UPDATED_AT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchByUpdatedAtAsync(List<LocalDateTime> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.UPDATED_AT,values);
    }

    /**
     * Fetch records that have <code>UPDATED_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.ambient.tables.pojos.XAttachment>> fetchByUpdatedByAsync(List<String> values) {
        return fetchAsync(XAttachment.X_ATTACHMENT.UPDATED_BY,values);
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
