package io.vertx.up.aiki;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.plugin.jooq.JooqInfix;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.eon.em.Format;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;
import org.jooq.Condition;
import org.jooq.Operator;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public class UxJooq {

    private static final Annal LOGGER = Annal.get(UxJooq.class);

    private transient final Class<?> clazz;
    private transient final VertxDAO vertxDAO;
    private transient final JooqAnalyzer analyzer;
    private transient Format format = Format.JSON;

    <T> UxJooq(final Class<T> clazz, final VertxDAO vertxDAO) {
        this.clazz = clazz;
        this.vertxDAO = vertxDAO;
        // Analyzing Column
        this.analyzer = JooqAnalyzer.create(vertxDAO);
    }

    <T> UxJooq(final Class<T> clazz) {
        this(clazz, (VertxDAO) JooqInfix.getDao(clazz));
    }

    // Search Operation --------------------------------------------------
    // Search ( Pager, Sort, Projection )
    // Because you want to do projection remove, it means that you could not pass List<T> in current method.
    public static Condition transform(final JsonObject filters, final Operator operator) {
        return JooqCond.transform(filters, operator, null);
    }

    public UxJooq on(final String pojo) {
        this.analyzer.bind(pojo, this.clazz);
        return this;
    }

    public UxJooq on(final Format format) {
        this.format = format;
        return this;
    }

    // Boolean
    // Find by existing
    public <T> Future<Boolean> findExists(final JsonObject filters) {
        return this.<T>findAsync(filters)
                .compose(item -> Future.succeededFuture(0 < item.size()));
    }

    // Find by missing
    public <T> Future<Boolean> findMissing(final JsonObject filters) {
        return this.<T>findAsync(filters)
                .compose(item -> Future.succeededFuture(0 == item.size()));
    }

    // CRUD - Read -----------------------------------------------------
    // Get by id
    public <T> Future<T> findByIdAsync(final Object id) {
        final CompletableFuture<T> future =
                this.vertxDAO.findByIdAsync(id);
        return Async.toFuture(future);
    }

    // Get all
    public <T> Future<List<T>> findAllAsync() {
        final CompletableFuture<List<T>> future =
                this.vertxDAO.findAllAsync();
        return Async.toFuture(future);
    }

    // Find by filters
    public <T> Future<List<T>> findAsync(final JsonObject filters) {
        return this.analyzer.searchAsync(filters);
    }

    // CRUD - Create ---------------------------------------------------
    // Create entity
    public <T> Future<T> insertAsync(final T entity) {
        final CompletableFuture<Void> future =
                this.vertxDAO.insertAsync(entity);
        return Async.toFuture(future)
                .compose(result -> Future.succeededFuture(entity));
    }

    // Create entity
    public <T> Future<T> insertReturningPrimaryAsync(final T entity, final Consumer<Long> consumer) {
        final CompletableFuture<Long> future =
                this.vertxDAO.insertReturningPrimaryAsync(entity);
        return Async.toFuture(future).compose(id -> {
            if (null != consumer) {
                consumer.accept(id);
            }
            return Future.succeededFuture(entity);
        });
    }

    // Create entities
    public <T> Future<List<T>> insertAsync(final List<T> entities) {
        final CompletableFuture<Void> future =
                this.vertxDAO.insertAsync(entities);
        return Async.toFuture(future)
                .compose(result -> Future.succeededFuture(entities));
    }

    // CRUD - Update ----------------------------------------------------
    // Update entity
    public <T> Future<T> updateAsync(final T entity) {
        final CompletableFuture<Void> future =
                this.vertxDAO.updateAsync(entity);
        return Async.toFuture(future)
                .compose(result -> Future.succeededFuture(entity));
    }

    public <T> Future<List<T>> updateAsync(final List<T> entities) {
        final CompletableFuture<Void> future =
                this.vertxDAO.updateAsync(entities);
        return Async.toFuture(future)
                .compose(result -> Future.succeededFuture(entities));
    }

    // CRUD - Existing/Missing ------------------------------------------

    public <T> Future<T> saveAsync(final Object id, final T updated) {
        return saveAsync(id, (target) -> this.analyzer.copyEntity(target, updated));
    }

    public <T> Future<T> saveAsync(final Object id, final Function<T, T> copyFun) {
        return this.<T>findByIdAsync(id).compose(old ->
                this.<T>updateAsync(copyFun.apply(old)));
    }

    public <T> Future<T> upsertReturningPrimaryAsync(final JsonObject andFilters, final T updated, final Consumer<Long> consumer) {
        return this.<T>fetchOneAndAsync(andFilters).compose(item -> Fn.match(
                // null != item, updated to existing item.
                Fn.fork(() -> this.<T>updateAsync(this.analyzer.copyEntity(item, updated))),
                // null == item, insert data
                Fn.branch(null == item, () -> this.insertReturningPrimaryAsync(updated, consumer))
        ));
    }

    public <T> Future<T> upsertAsync(final JsonObject andFilters, final T updated) {
        return this.<T>fetchOneAndAsync(andFilters).compose(item -> Fn.match(
                // null != item, updated to existing item.
                Fn.fork(() -> this.<T>updateAsync(this.analyzer.copyEntity(item, updated))),
                // null == item, insert data
                Fn.branch(null == item, () -> this.insertAsync(updated))
        ));
    }

    // CRUD - Delete ----------------------------------------------------
    public <T> Future<T> deleteAsync(final T entity) {
        final CompletableFuture<Void> future =
                this.vertxDAO.deleteAsync(Arrays.asList(entity));
        return Async.toFuture(future)
                .compose(result -> Future.succeededFuture(entity));
    }

    public <T> Future<Boolean> deleteAsync(final JsonObject filters) {
        return deleteAsync(filters, "");
    }

    public <T> Future<Boolean> deleteAsync(final JsonObject filters, final String pojo) {
        return findAsync(filters)
                .compose(Ux.fnJArray(this.analyzer.getPojoFile()))
                .compose(array -> Future.succeededFuture(array.stream()
                        .map(item -> (JsonObject) item)
                        .map(item -> item.getValue("key"))
                        .collect(Collectors.toList())))
                .compose(item -> Future.succeededFuture(item.toArray()))
                .compose(ids -> this.deleteByIdAsync(ids));
    }

    public <T> Future<Boolean> deleteByIdAsync(final Object id) {
        final CompletableFuture<Void> future =
                this.vertxDAO.deleteByIdAsync(id);
        return Async.toFuture(future)
                .compose(result -> Future.succeededFuture(Boolean.TRUE));
    }

    public <T> Future<Boolean> deleteByIdAsync(final Collection<Object> ids) {
        final CompletableFuture<Void> future =
                this.vertxDAO.deleteByIdAsync(ids);
        return Async.toFuture(future)
                .compose(result -> Future.succeededFuture(Boolean.TRUE));
    }

    public <T> Future<Boolean> deleteByIdAsync(final Object... ids) {
        final CompletableFuture<Void> future =
                this.vertxDAO.deleteByIdAsync(Arrays.asList(ids));
        return Async.toFuture(future)
                .compose(result -> Future.succeededFuture(Boolean.TRUE));
    }

    public <T> Future<Boolean> existsByIdAsync(final Object id) {
        final CompletableFuture<Boolean> future =
                this.vertxDAO.existsByIdAsync(id);
        return Async.toFuture(future);
    }

    public <T> Future<Boolean> existsOneAsync(final JsonObject andFilters) {
        return this.<T>fetchOneAndAsync(andFilters)
                .compose(item -> Future.succeededFuture(null != item));
    }

    // Filter column called
    public <T> Future<T> fetchOneAsync(final String field, final Object value) {
        final CompletableFuture<T> future =
                this.vertxDAO.fetchOneAsync(this.analyzer.getColumn(field), value);
        return Async.toFuture(future);
    }

    // Filter transform called
    public <T> Future<T> fetchOneAndAsync(final JsonObject andFilters) {
        final Condition condition = JooqCond.transform(andFilters, Operator.AND, this.analyzer::getColumn);
        final CompletableFuture<T> future =
                this.vertxDAO.fetchOneAsync(condition);
        return Async.toFuture(future);
    }

    // Filter column called
    // Fetch List
    public <T> Future<List<T>> fetchAsync(final String field, final Object value) {
        final CompletableFuture<List<T>> future =
                this.vertxDAO.fetchAsync(this.analyzer.getColumn(field), Arrays.asList(value));
        return Async.toFuture(future);
    }


    // Filter column called
    public <T> Future<List<T>> fetchInAsync(final String column, final Object... value) {
        final JsonArray values = Ut.toJArray(Arrays.asList(value));
        return fetchInAsync(column, values);
    }

    // Filter column called
    public <T> Future<List<T>> fetchInAsync(final String field, final JsonArray values) {
        final CompletableFuture<List<T>> future =
                this.vertxDAO.fetchAsync(this.analyzer.getColumn(field), values.getList());
        return Async.toFuture(future);
    }

    // Filter transform called
    public <T> Future<List<T>> fetchAndAsync(final JsonObject andFilters) {
        final Condition condition = JooqCond.transform(andFilters, Operator.AND, this.analyzer::getColumn);
        final CompletableFuture<List<T>> future =
                this.vertxDAO.fetchAsync(condition);
        return Async.toFuture(future);
    }

    // Filter transform called
    public <T> Future<List<T>> fetchOrAsync(final JsonObject orFilters) {
        final Condition condition = JooqCond.transform(orFilters, Operator.OR, this.analyzer::getColumn);
        final CompletableFuture<List<T>> future =
                this.vertxDAO.fetchAsync(condition);
        return Async.toFuture(future);
    }

    public Future<JsonObject> searchOrAsync(final Inquiry inquiry) {
        return this.searchOrAsync(inquiry, "");
    }

    public Future<JsonObject> searchOrAsync(final JsonObject params, final String pojo) {
        final Inquiry inquiry = Query.getInquiry(params, pojo);
        return searchOrAsync(inquiry, pojo);
    }

    public Future<JsonObject> searchOrAsync(final Inquiry inquiry, final String pojo) {
        // Pager, Sort, Criteria, Projection
        final JsonObject result = new JsonObject();
        return searchDirect(inquiry, Operator.OR)
                .compose(array -> Ux.thenJsonMore(array.getList(), pojo))
                .compose(array -> {
                    result.put("list", array);
                    return this.analyzer.countAsync(inquiry, Operator.OR);
                })
                .compose(count -> {
                    result.put("count", count);
                    return Future.succeededFuture(result);
                });
    }

    public <T> Future<JsonObject> searchAsync(final JsonObject params, final String pojo) {
        final Inquiry inquiry = Query.getInquiry(params, pojo);
        return searchAsync(inquiry, pojo);
    }

    public Future<Integer> countAsync(final JsonObject params, final String pojo) {
        final Inquiry inquiry = Query.getInquiry(params, pojo);
        return this.analyzer.countAsync(inquiry, null);
    }

    public <T> Future<JsonObject> searchAsync(final Inquiry inquiry, final String pojo) {
        final JsonObject result = new JsonObject();
        return this.analyzer.searchAsync(inquiry, null)
                .compose(list -> Ux.thenJsonMore(list, pojo))
                .compose(array -> {
                    result.put("list", array);
                    return this.analyzer.countAsync(inquiry, null);
                })
                .compose(count -> {
                    result.put("count", count);
                    return Future.succeededFuture(result);
                });
    }

    public Future<JsonObject> searchAndAsync(final Inquiry inquiry) {
        return this.searchAndAsync(inquiry, "");
    }

    public Future<JsonObject> searchAndAsync(final JsonObject params, final String pojo) {
        final Inquiry inquiry = Query.getInquiry(params, pojo);
        return searchAndAsync(inquiry, pojo);
    }

    public Future<JsonObject> searchAndAsync(final Inquiry inquiry, final String pojo) {
        // Pager, Sort, Criteria, Projection
        final JsonObject result = new JsonObject();
        return searchDirect(inquiry, Operator.AND)
                .compose(array -> Ux.thenJsonMore(array.getList(), pojo))
                .compose(array -> {
                    result.put("list", array);
                    return this.analyzer.countAsync(inquiry, Operator.AND);
                })
                .compose(count -> {
                    result.put("count", count);
                    return Future.succeededFuture(result);
                });
    }

    public <T> Future<List<T>> searchAndListAsync(final Inquiry inquiry) {
        // Fast mode is search AND operator
        return this.analyzer.searchAsync(inquiry, Operator.AND);
    }

    public <T> Future<List<T>> searchOrListAsync(final Inquiry inquiry) {
        // Fast mode is search AND operator
        return this.analyzer.searchAsync(inquiry, Operator.OR);
    }

    private Future<JsonArray> searchDirect(final Inquiry inquiry, final Operator operator) {
        // Pager, Sort, Criteria, Projection
        return this.analyzer.searchAsync(inquiry, operator).compose(list -> {
            if (null == inquiry.getProjection()) {
                return Ux.thenJsonMore(list);
            } else {
                return Ux.thenJsonMore(list).compose(array -> Uarr.create(array)
                        .remove(inquiry.getProjection().toArray(new String[]{}))
                        .toFuture());
            }
        });
    }
}
