package io.vertx.up.aiki;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;
import io.vertx.core.Future;

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

    private static JooqWriter INSTANCE;
    private transient final VertxDAO vertxDAO;
    private transient JooqReader reader;

    private JooqWriter(final VertxDAO vertxDAO) {
        this.vertxDAO = vertxDAO;
    }

    static JooqWriter create(final VertxDAO vertxDAO) {
        synchronized (JooqWriter.class) {
            if (null == INSTANCE) {
                INSTANCE = new JooqWriter(vertxDAO);
            }
            return INSTANCE;
        }
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
        return Async.toFuture(future).compose(id -> {
            if (null != consumer) consumer.accept(id);
            return Future.succeededFuture(entity);
        });
    }

    /* Async insert operation: INSERT */
    <T> Future<T> insertAsync(final T entity) {
        final CompletableFuture<Void> future = this.vertxDAO.insertAsync(entity);
        return Async.toFuture(future).compose(nil -> Future.succeededFuture(entity));
    }

    <T> Future<List<T>> insertAsync(final List<T> entities) {
        final CompletableFuture<Void> future = this.vertxDAO.insertAsync(entities);
        return Async.toFuture(future).compose(nil -> Future.succeededFuture(entities));
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
        return Async.toFuture(future).compose(nil -> Future.succeededFuture(entity));
    }

    <T> Future<List<T>> updateAsync(final List<T> entities) {
        final CompletableFuture<Void> future = this.vertxDAO.updateAsync(entities);
        return Async.toFuture(future).compose(nil -> Future.succeededFuture(entities));
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
        return Async.toFuture(future).compose(nil -> Future.succeededFuture(entity));
    }

    Future<Boolean> deleteByIdAsync(final Object id) {
        final CompletableFuture<Void> future = this.vertxDAO.deleteByIdAsync(id);
        return Async.toFuture(future).compose(nil -> Future.succeededFuture(Boolean.TRUE));
    }

    Future<Boolean> deleteByIdAsync(final Collection<Object> ids) {
        final CompletableFuture<Void> future = this.vertxDAO.deleteByIdAsync(ids);
        return Async.toFuture(future).compose(nil -> Future.succeededFuture(Boolean.TRUE));
    }

    /* Sync delete operation: DELETE */
    <T> T delete(final T entity) {
        this.vertxDAO.delete(entity);
        return entity;
    }

    Boolean deleteById(final Object id) {
        this.vertxDAO.deleteById(id);
        return Boolean.TRUE;
    }

    Boolean deleteById(final Collection<Object> ids) {
        this.vertxDAO.deleteById(ids);
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
}
