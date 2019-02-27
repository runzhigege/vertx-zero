package io.vertx.up.aiki;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.plugin.jooq.JooqInfix;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.eon.em.Format;
import io.vertx.up.log.Annal;
import io.zero.epic.fn.Fn;
import org.jooq.Condition;
import org.jooq.Operator;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public class UxJooq {

    private static final Annal LOGGER = Annal.get(UxJooq.class);

    private transient final Class<?> clazz;
    /* Analyzer */
    private transient final JooqAnalyzer analyzer;
    /* Writer */
    private transient final JooqWriter writer;
    /* Reader */
    private transient final JooqReader reader;

    private transient Format format = Format.JSON;

    <T> UxJooq(final Class<T> clazz, final VertxDAO vertxDAO) {
        this.clazz = clazz;
        /* Analyzing column for Jooq */
        this.analyzer = JooqAnalyzer.create(vertxDAO);
        /* Reader connect Analayzer */
        this.reader = JooqReader.create(vertxDAO).on(this.analyzer);
        /* Writer connect Reader */
        this.writer = JooqWriter.create(vertxDAO).on(this.reader);
    }

    <T> UxJooq(final Class<T> clazz) {
        this(clazz, (VertxDAO) JooqInfix.getDao(clazz));
    }

    // -------------------- Condition Transform --------------------
    public static Condition transform(final JsonObject filters, final Operator operator) {
        return JooqCond.transform(filters, operator, null);
    }

    // -------------------- Bind Config --------------------
    public UxJooq on(final String pojo) {
        this.analyzer.bind(pojo, this.clazz);
        return this;
    }

    public UxJooq on(final Format format) {
        this.format = format;
        return this;
    }

    // -------------------- INSERT --------------------

    /* Async Only */
    public <T> Future<T> insertReturningPrimaryAsync(final T entity, final Consumer<Long> consumer) {
        return this.writer.insertReturningPrimaryAsync(entity, consumer);
    }

    public <T> Future<T> insertAsync(final T entity) {
        return this.writer.insertAsync(entity);
    }

    public <T> Future<List<T>> insertAsync(final List<T> entities) {
        return this.writer.insertAsync(entities);
    }

    public <T> T insert(final T entity) {
        return this.writer.insert(entity);
    }

    public <T> List<T> insert(final List<T> entities) {
        return this.writer.insert(entities);
    }

    // -------------------- UPDATE --------------------
    public <T> Future<T> updateAsync(final T entity) {
        return this.writer.updateAsync(entity);
    }

    public <T> Future<List<T>> updateAsync(final List<T> entities) {
        return this.writer.updateAsync(entities);
    }

    public <T> T update(final T entity) {
        return this.writer.update(entity);
    }

    public <T> List<T> update(final List<T> entities) {
        return this.writer.update(entities);
    }

    // -------------------- DELETE --------------------
    public <T> Future<T> deleteAsync(final T entity) {
        return this.writer.deleteAsync(entity);
    }

    public Future<Boolean> deleteByIdAsync(final Object id) {
        return this.writer.deleteByIdAsync(id);
    }

    public Future<Boolean> deleteByIdAsync(final Collection<Object> ids) {
        return this.writer.deleteByIdAsync(ids);
    }

    public Future<Boolean> deleteByIdAsync(final Object... ids) {
        return this.writer.deleteByIdAsync(Arrays.asList(ids));
    }

    public <T> T delete(final T entity) {
        return this.writer.delete(entity);
    }

    public Boolean deleteById(final Object id) {
        return this.writer.deleteById(id);
    }

    public Boolean deleteById(final Collection<Object> ids) {
        return this.writer.deleteById(ids);
    }

    public Boolean deleteById(final Object... ids) {
        return this.writer.deleteById(Arrays.asList(ids));
    }

    // -------------------- Fetch One --------------------
    // Fetch
    public <T> Future<T> fetchOneAsync(final String field, final Object value) {
        return this.reader.fetchOneAsync(field, value);
    }

    /* Async Only */
    public <T> Future<T> fetchOneAndAsync(final JsonObject andFilters) {
        return this.reader.fetchOneAndAsync(andFilters);
    }

    public <T> T fetchOne(final String field, final Object value) {
        return this.reader.fetchOne(field, value);
    }

    // Find
    public <T> Future<T> findByIdAsync(final Object id) {
        return this.reader.findByIdAsync(id);
    }

    public <T> T findById(final Object id) {
        return this.reader.findById(id);
    }

    public <T> Future<List<T>> findAllAsync() {
        return this.reader.findAllAsync();
    }

    public <T> List<T> findAll() {
        return this.reader.findAll();
    }

    // -------------------- Save Operation --------------------
    public <T> Future<T> saveAsync(final Object id, final T updated) {
        return this.writer.saveAsync(id, (target) -> this.analyzer.copyEntity(target, updated));
    }

    public <T> Future<T> saveAsync(final Object id, final Function<T, T> copyFun) {
        return this.writer.saveAsync(id, copyFun);
    }

    public <T> T save(final Object id, final T updated) {
        return this.writer.save(id, (target) -> this.analyzer.copyEntity(target, updated));
    }

    public <T> T save(final Object id, final Function<T, T> copyFun) {
        return this.writer.save(id, copyFun);
    }

    // -------------------- Exist Operation --------------------
    public Future<Boolean> existsByIdAsync(final Object id) {
        return this.reader.existsByIdAsync(id);
    }

    /* Async Only **/
    public <T> Future<Boolean> existsOneAsync(final JsonObject andFilters) {
        return this.<T>fetchOneAndAsync(andFilters)
                .compose(item -> Future.succeededFuture(null != item));
    }

    public Boolean existsById(final Object id) {
        return this.reader.existsById(id);
    }

    // -------------------- Fetch List Operation ---------------

    public <T> Future<List<T>> fetchAsync(final String field, final Object value) {
        return this.reader.fetchAsync(field, value);
    }

    public <T> Future<List<T>> fetchInAsync(final String field, final Object... value) {
        return this.reader.fetchInAsync(field, Arrays.asList(value));
    }

    public <T> Future<List<T>> fetchInAsync(final String field, final JsonArray values) {
        return this.reader.fetchInAsync(field, values.getList());
    }

    public <T> List<T> fetch(final String field, final Object value) {
        return this.reader.fetch(field, value);
    }

    public <T> List<T> fetchIn(final String field, final Object... values) {
        return this.reader.fetchIn(field, Arrays.asList(values));
    }

    public <T> List<T> fetchIn(final String field, final JsonArray values) {
        return this.reader.fetchIn(field, values.getList());
    }

    public <T> Future<List<T>> fetchAndAsync(final JsonObject andFilters) {
        return this.reader.fetchAsync(JooqCond.transform(andFilters, Operator.AND, this.analyzer::getColumn));
    }

    public <T> Future<List<T>> fetchOrAsync(final JsonObject orFilters) {
        return this.reader.fetchAsync(JooqCond.transform(orFilters, Operator.OR, this.analyzer::getColumn));
    }

    // -------------------- Old Code Below ---------------
    // Boolean
    // Find by existing
    public <T> Future<Boolean> findExistingAsync(final JsonObject filters) {
        return this.<T>findAsync(filters)
                .compose(item -> Future.succeededFuture(0 < item.size()));
    }

    // Find by missing
    public <T> Future<Boolean> findMissingAsync(final JsonObject filters) {
        return this.<T>findAsync(filters)
                .compose(item -> Future.succeededFuture(0 == item.size()));
    }


    // Find by filters
    public <T> Future<List<T>> findAsync(final JsonObject filters) {
        return this.analyzer.searchAsync(filters);
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

    // Filter column called


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

    public <T> Future<JsonObject> searchAsync(final JsonObject params) {
        final String pojo = this.analyzer.getPojoFile();
        return searchAsync(params, pojo);
    }

    public Future<Integer> countAsync(final JsonObject params, final String pojo) {
        final Inquiry inquiry = Query.getInquiry(params, pojo);
        return this.analyzer.countAsync(inquiry, null);
    }

    public Future<Integer> countAsync(final JsonObject params) {
        final String pojo = this.analyzer.getPojoFile();
        return countAsync(params, pojo);
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
