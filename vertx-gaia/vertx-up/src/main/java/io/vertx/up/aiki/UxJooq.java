package io.vertx.up.aiki;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.plugin.jooq.JooqInfix;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.eon.em.Format;
import io.vertx.up.log.Annal;
import io.vertx.zero.epic.fn.Fn;
import org.jooq.Condition;
import org.jooq.Operator;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

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
        this.reader = JooqReader.create(vertxDAO)
                .on(this.analyzer);
        /* Writer connect Reader */
        this.writer = JooqWriter.create(vertxDAO)
                .on(this.analyzer).on(this.reader);
    }

    <T> UxJooq(final Class<T> clazz) {
        this(clazz, (VertxDAO) JooqInfix.getDao(clazz));
    }

    // -------------------- Condition Transform --------------------

    public static Condition transform(final JsonObject filters, final Operator operator) {
        return JooqCond.transform(filters, operator, null);
    }

    /**
     * Direct analyzing the result to condition
     */
    public static Condition transform(final JsonObject filters) {
        return JooqCond.transform(filters, null, null);
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


    /* (Async / Sync) Entity Insert */
    public <T> Future<T> insertAsync(final T entity) {
        return this.writer.insertAsync(entity);
    }

    public <T> T insert(final T entity) {
        return this.writer.insert(entity);
    }


    /* (Async / Sync) Collection Insert */
    public <T> Future<List<T>> insertAsync(final List<T> entities) {
        return this.writer.insertAsync(entities);
    }

    public <T> List<T> insert(final List<T> entities) {
        return this.writer.insert(entities);
    }

    // -------------------- UPDATE --------------------
    /* Async Only */
    public <T> Future<T> upsertReturningPrimaryAsync(final JsonObject filters, final T updated, final Consumer<Long> consumer) {
        return this.<T>fetchOneAsync(filters).compose(item -> Fn.match(
                Fn.fork(() -> this.<T>updateAsync(this.analyzer.copyEntity(item, updated))),
                Fn.branch(null == item, () -> this.insertReturningPrimaryAsync(updated, consumer))
        ));
    }


    /* (Async / Sync) Entity Update */
    public <T> Future<T> updateAsync(final T entity) {
        return this.writer.updateAsync(entity);
    }

    public <T> T update(final T entity) {
        return this.writer.update(entity);
    }


    /* (Async / Sync) Collection Update */
    public <T> Future<List<T>> updateAsync(final List<T> entities) {
        return this.writer.updateAsync(entities);
    }

    public <T> List<T> update(final List<T> entities) {
        return this.writer.update(entities);
    }

    // -------------------- DELETE --------------------
    /* (Async / Sync) Delete By ( ID / IDs ) */
    public <ID> Future<Boolean> deleteByIdAsync(final ID id) {
        return this.writer.<ID>deleteByIdAsync(id);
    }

    public <ID> Future<Boolean> deleteByIdAsync(final ID... ids) {
        return this.writer.<ID>deleteByIdAsync(Arrays.asList(ids));
    }

    public <ID> Boolean deleteById(final ID id) {
        return this.writer.<ID>deleteById(id);
    }

    public <ID> Boolean deleteById(final ID... ids) {
        return this.writer.<ID>deleteById(Arrays.asList(ids));
    }


    /* (Async / Sync) Delete Entity */
    public <T> Future<T> deleteAsync(final T entity) {
        return this.writer.<T>deleteAsync(entity);
    }

    public <T> T delete(final T entity) {
        return this.writer.<T>delete(entity);
    }


    /* (Async / Sync) Delete by Filters */
    public <T, ID> Future<Boolean> deleteAsync(final JsonObject filters) {
        return this.writer.<T, ID>deleteAsync(filters, "");
    }

    public <T, ID> Boolean delete(final JsonObject filters, final String pojo) {
        return this.writer.<T, ID>delete(filters, pojo);
    }

    public <T, ID> Future<Boolean> deleteAsync(final JsonObject filters, final String pojo) {
        return this.writer.<T, ID>deleteAsync(filters, pojo);
    }

    public <T, ID> Boolean delete(final JsonObject filters) {

        return this.writer.<T, ID>delete(filters, "");
    }

    // -------------------- Fetch One/All --------------------

    /* (Async / Sync) Fetch One */
    public <T> Future<T> fetchOneAsync(final String field, final Object value) {
        return this.reader.fetchOneAsync(field, value);
    }

    public <T> T fetchOne(final String field, final Object value) {
        return this.reader.fetchOne(field, value);
    }

    public <T> Future<T> fetchOneAsync(final JsonObject filters) {
        return this.reader.fetchOneAndAsync(filters);
    }

    public <T> T fetchOne(final JsonObject filters) {
        return this.reader.fetchOneAnd(filters);
    }

    /* (Async / Sync) Find By ID */
    public <T> Future<T> findByIdAsync(final Object id) {
        return this.reader.findByIdAsync(id);
    }

    public <T> T findById(final Object id) {
        return this.reader.findById(id);
    }


    /* (Async / Sync) Find All */
    public <T> Future<List<T>> findAllAsync() {
        return this.reader.findAllAsync();
    }

    public <T> List<T> findAll() {
        return this.reader.findAll();
    }

    // -------------------- Save Operation --------------------
    /* (Async / Sync) Save Operations */
    public <T> Future<T> saveAsync(final Object id, final T updated) {
        return this.writer.saveAsync(id, (target) -> this.analyzer.copyEntity(target, updated));
    }

    public <T> T save(final Object id, final T updated) {
        return this.writer.save(id, (target) -> this.analyzer.copyEntity(target, updated));
    }

    public <T> Future<T> saveAsync(final Object id, final Function<T, T> copyFun) {
        return this.writer.saveAsync(id, copyFun);
    }

    public <T> T save(final Object id, final Function<T, T> copyFun) {
        return this.writer.save(id, copyFun);
    }

    // -------------------- Exist Operation --------------------
    /* (Async / Sync) Exist By ID Operation */
    public Future<Boolean> existsByIdAsync(final Object id) {
        return this.reader.existsByIdAsync(id);
    }

    public Boolean existsById(final Object id) {
        return this.reader.existsById(id);
    }


    /* (Async / Sync) Exist By Filters Operation */
    public <T> Future<Boolean> existsOneAsync(final JsonObject filters) {
        return this.<T>fetchOneAsync(filters)
                .compose(item -> Future.succeededFuture(null != item));
    }

    public <T> Boolean existsOne(final JsonObject filters) {
        final T result = this.<T>fetchOne(filters);
        return null != result;
    }

    // -------------------- Fetch List Operation ---------------
    /* (Async / Sync) Fetch to List<T> Operation */
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

    // -------------------- Search Operation -----------------
    /* (Async / Sync) Find / Exist / Missing By Filters Operation */
    public <T> Future<List<T>> findAsync(final JsonObject filters) {
        return this.analyzer.searchAsync(filters);
    }

    public <T> Future<Boolean> findExistingAsync(final JsonObject filters) {
        return this.<T>findAsync(filters)
                .compose(item -> Future.succeededFuture(0 < item.size()));
    }

    public <T> Future<Boolean> findMissingAsync(final JsonObject filters) {
        return this.<T>findAsync(filters)
                .compose(item -> Future.succeededFuture(0 == item.size()));
    }

    public <T> List<T> find(final JsonObject filters) {
        return this.analyzer.search(filters);
    }

    public <T> Boolean findExisting(final JsonObject filters) {
        final List<T> list = find(filters);
        return 0 < list.size();
    }

    public <T> Boolean findMissing(final JsonObject filters) {
        final List<T> list = find(filters);
        return 0 == list.size();
    }

    // -------------------- Count Operation ------------
    /* (Async / Sync) Count Operation */
    public Future<Integer> countAsync(final JsonObject params, final String pojo) {
        final Inquiry inquiry = Query.getInquiry(params, pojo);
        return this.analyzer.countAsync(inquiry);
    }

    public Future<Integer> countAsync(final JsonObject params) {
        return countAsync(params, this.analyzer.getPojoFile());
    }

    public Integer count(final JsonObject params, final String pojo) {
        final Inquiry inquiry = Query.getInquiry(params, pojo);
        return this.analyzer.count(inquiry);
    }

    public Integer count(final JsonObject params) {
        return count(params, this.analyzer.getPojoFile());
    }

    // -------------------- Search Operation -----------
    /* (Async / Sync) Sort, Projection, Criteria, Pager Search Operations */
    public Future<JsonObject> searchAsync(final JsonObject params, final String pojo) {
        final Inquiry inquiry = Query.getInquiry(params, pojo);
        return searchAsync(inquiry, pojo);
    }

    public Future<JsonObject> searchAsync(final JsonObject params) {
        return searchAsync(params, this.analyzer.getPojoFile());
    }

    public Future<JsonObject> searchAsync(final Inquiry inquiry, final String pojo) {
        return this.analyzer.searchPaginationAsync(inquiry, pojo);
    }

    public JsonObject search(final JsonObject params, final String pojo) {
        final Inquiry inquiry = Query.getInquiry(params, pojo);
        return search(inquiry, pojo);
    }

    public JsonObject search(final JsonObject params) {
        return search(params, this.analyzer.getPojoFile());
    }

    public JsonObject search(final Inquiry inquiry, final String pojo) {
        return this.analyzer.searchPagination(inquiry, pojo);
    }

    // -------------------- Fetch List -------------------
    public <T> Future<List<T>> fetchAndAsync(final JsonObject filters) {
        return this.reader.fetchAsync(JooqCond.transform(filters, Operator.AND, this.analyzer::getColumn));
    }

    public <T> List<T> fetchAnd(final JsonObject filters) {
        return this.reader.fetch(JooqCond.transform(filters, Operator.AND, this.analyzer::getColumn));
    }

    public <T> Future<List<T>> fetchOrAsync(final JsonObject orFilters) {
        return this.reader.fetchAsync(JooqCond.transform(orFilters, Operator.OR, this.analyzer::getColumn));
    }

    public <T> List<T> fetchOr(final JsonObject orFilters) {
        return this.reader.fetch(JooqCond.transform(orFilters, Operator.OR, this.analyzer::getColumn));
    }

    // -------------------- Upsert ---------
    public <T> Future<T> upsertAsync(final JsonObject filters, final T updated) {
        return this.<T>fetchOneAsync(filters).compose(item -> Fn.match(
                // null != item, updated to existing item.
                Fn.fork(() -> this.<T>updateAsync(this.analyzer.copyEntity(item, updated))),
                // null == item, insert data
                Fn.branch(null == item, () -> this.insertAsync(updated))
        ));
    }

    public <T> T upsert(final JsonObject filters, final T updated) {
        final T entity = this.fetchOne(filters);
        if (null == entity) {
            return this.insert(updated);
        } else {
            return this.update(this.analyzer.copyEntity(entity, updated));
        }
    }
}
