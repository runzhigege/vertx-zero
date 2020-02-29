package io.vertx.up.unity.jq;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.uca.condition.JooqCond;
import org.jooq.Condition;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Jooq Splitted Writter
 * Create
 * Update
 * Delete
 */
@SuppressWarnings("all")
class JooqWriter {

    private transient final VertxDAO vertxDAO;
    private transient JooqReader reader;
    private transient JooqAnalyzer analyzer;

    private JooqWriter(final VertxDAO vertxDAO) {
        this.vertxDAO = vertxDAO;
    }

    static JooqWriter create(final VertxDAO vertxDAO) {
        return new JooqWriter(vertxDAO);
    }

    JooqWriter on(JooqAnalyzer analyzer) {
        this.analyzer = analyzer;
        return this;
    }

    JooqWriter on(JooqReader reader) {
        this.reader = reader;
        return this;
    }

    // ============ INSERT Operation =============

    /* Async insert operation with key returned: INSERT ( AUTO INCREAMENT ) */
    <T> Future<T> insertReturningPrimaryAsync(final T entity,
                                              final Consumer<Long> consumer) {
        final CompletableFuture<Long> future = this.vertxDAO.insertReturningPrimaryAsync(entity);
        return QTool.future(future).compose(id -> {
            if (null != consumer) consumer.accept(id);
            return Future.succeededFuture(entity);
        });
    }

    /* Async insert operation: INSERT */
    <T> Future<T> insertAsync(final T entity) {
        final CompletableFuture<Void> future = this.vertxDAO.insertAsync(entity);
        return QTool.future(future).compose(nil -> Future.succeededFuture(entity));
    }

    <T> Future<List<T>> insertAsync(final List<T> entities) {
        final CompletableFuture<Void> future = this.vertxDAO.insertAsync(entities);
        return QTool.future(future).compose(nil -> Future.succeededFuture(entities));
    }

    /* Sync insert operation: INSERT */
    <T> T insert(final T entity) {
        this.vertxDAO.insert(entity);
        return entity;
    }

    <T> List<T> insert(final List<T> entities) {
        this.vertxDAO.insert(entities);
        return entities;
    }

    // ============ UPDATE Operation =============

    /* Async insert operation: UPDATE */
    <T> Future<T> updateAsync(final T entity) {
        final CompletableFuture<Void> future = this.vertxDAO.updateAsync(entity);
        return QTool.future(future).compose(nil -> Future.succeededFuture(entity));
    }

    <T> Future<List<T>> updateAsync(final List<T> entities) {
        final CompletableFuture<Void> future = this.vertxDAO.updateAsync(entities);
        return QTool.future(future).compose(nil -> Future.succeededFuture(entities));
    }

    /* Sync insert operation: UPDATE */
    <T> T update(final T entity) {
        this.vertxDAO.update(entity);
        return entity;
    }

    <T> List<T> update(final List<T> entities) {
        this.vertxDAO.update(entities);
        return entities;
    }

    // ============ DELETE Operation =============

    /* Async delete operation: DELETE */
    <T> Future<T> deleteAsync(final T entity) {
        final CompletableFuture<Void> future = this.vertxDAO.deleteAsync(Arrays.asList(entity));
        return QTool.future(future).compose(nil -> Future.succeededFuture(entity));
    }

    <ID> Future<Boolean> deleteByIdAsync(final ID id) {
        final CompletableFuture<Void> future = this.vertxDAO.deleteByIdAsync(id);
        return QTool.future(future).compose(nil -> Future.succeededFuture(Boolean.TRUE));
    }

    <ID> Future<Boolean> deleteByIdAsync(final Collection<ID> ids) {
        final CompletableFuture<Void> future = this.vertxDAO.deleteByIdAsync(ids);
        return QTool.future(future).compose(nil -> Future.succeededFuture(Boolean.TRUE));
    }

    <T, ID> Future<Boolean> deleteAsync(final JsonObject filters, final String pojo) {
        final Condition condition = JooqCond.transform(filters, null, this.analyzer::getColumn);
        final CompletableFuture<Integer> deleted = this.vertxDAO.deleteExecAsync(condition);
        return QTool.future(deleted).compose(nil -> Future.succeededFuture(Boolean.TRUE));
    }

    /* Sync delete operation: DELETE */
    <T> T delete(final T entity) {
        this.vertxDAO.delete(entity);
        return entity;
    }

    <ID> Boolean deleteById(final ID id) {
        this.vertxDAO.deleteById(id);
        return Boolean.TRUE;
    }

    <ID> Boolean deleteById(final Collection<ID> ids) {
        this.vertxDAO.deleteById(ids);
        return Boolean.TRUE;
    }

    <T, ID> Boolean delete(final JsonObject filters, final String pojo) {
        final Condition condition = JooqCond.transform(filters, null, this.analyzer::getColumn);
        final List<T> result = this.reader.fetch(condition);
        result.stream().map(item -> this.delete(item));
        return Boolean.TRUE;
    }

    // ============ UPDATE Operation (Save) =============
    <T> Future<T> saveAsync(final Object id, final Function<T, T> copyFun) {
        return this.reader.<T>findByIdAsync(id).compose(old -> this.<T>updateAsync(copyFun.apply(old)));
    }

    <T> T save(final Object id, final Function<T, T> copyFun) {
        final T old = this.reader.<T>findById(id);
        return copyFun.apply(old);
    }
    /*
     * Old Code
    private <ID> List<ID> extractIds(final JsonArray array) {
        return array.stream()
                .map(item -> (JsonObject) item)
                .map(item -> item.getValue("key"))
                .filter(Objects::nonNull)
                .map(item -> (ID) item)
                .collect(Collectors.toList());
    } */
}
