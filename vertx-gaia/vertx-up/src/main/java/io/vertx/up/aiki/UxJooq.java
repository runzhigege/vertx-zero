package io.vertx.up.aiki;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.plugin.jooq.JooqInfix;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.StringUtil;
import io.vertx.up.tool.mirror.Types;
import io.vertx.zero.eon.Values;
import net.sf.cglib.beans.BeanCopier;
import org.jooq.Condition;
import org.jooq.Operator;
import org.jooq.impl.DSL;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("all")
public class UxJooq {

    private static final Annal LOGGER = Annal.get(UxJooq.class);

    private transient final Class<?> clazz;
    private transient final VertxDAO vertxDAO;

    <T> UxJooq(final Class<T> clazz) {
        this.clazz = clazz;
        this.vertxDAO = (VertxDAO) JooqInfix.getDao(clazz);
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

    // CRUD - Create ---------------------------------------------------
    // Create entity
    public <T> Future<T> insertAsync(final T entity) {
        final CompletableFuture<Void> future =
                this.vertxDAO.insertAsync(entity);
        return Future.succeededFuture(entity);
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
        return Future.succeededFuture(entities);
    }

    // CRUD - Update ----------------------------------------------------
    // Update entity
    public <T> Future<T> updateAsync(final T entity) {
        final CompletableFuture<Void> future =
                this.vertxDAO.updateAsync(entity);
        return Future.succeededFuture(entity);
    }

    public <T> Future<List<T>> updateAsync(final List<T> entities) {
        final CompletableFuture<Void> future =
                this.vertxDAO.updateAsync(entities);
        return Future.succeededFuture(entities);
    }

    // CRUD - Upsert ----------------------------------------------------
    public <T> Future<T> saveAsync(final Object id, final T updated) {
        final Function<T, T> copyFun = (old) -> {
            final BeanCopier copier = BeanCopier.create(old.getClass(), updated.getClass(), false);
            copier.copy(updated, old, null);
            return old;
        };
        return saveAsync(id, copyFun);
    }

    public <T> Future<T> saveAsync(final Object id, final Function<T, T> copyFun) {
        return this.<T>findByIdAsync(id).compose(old ->
                this.<T>updateAsync(copyFun.apply(old)));
    }

    // CRUD - Delete ----------------------------------------------------
    public <T> Future<T> deleteAsync(final T entity) {
        final CompletableFuture<Void> future =
                this.vertxDAO.deleteAsync(Arrays.asList(entity));
        return Future.succeededFuture(entity);
    }

    public <T> Future<Boolean> deleteByIdAsync(final Object id) {
        final CompletableFuture<Void> future =
                this.vertxDAO.deleteByIdAsync(id);
        return Future.succeededFuture(Boolean.TRUE);
    }

    public <T> Future<Boolean> deleteByIdAsync(final Collection<Object> ids) {
        final CompletableFuture<Void> future =
                this.vertxDAO.deleteByIdAsync(ids);
        return Future.succeededFuture(Boolean.TRUE);
    }

    public <T> Future<Boolean> deleteByIdAsync(final Object... ids) {
        final CompletableFuture<Void> future =
                this.vertxDAO.deleteByIdAsync(Arrays.asList(ids));
        return Future.succeededFuture(Boolean.TRUE);
    }

    // Fetch Operation --------------------------------------------------
    // Fetch One
    public <T> Future<T> fetchOneAsync(final String column, final Object value) {
        final CompletableFuture<T> future =
                this.vertxDAO.fetchOneAsync(DSL.field(column), value);
        return Async.toFuture(future);
    }

    public <T> Future<T> fetchOneAndAsync(final JsonObject andFilters) {
        final Condition condition = transform(andFilters, Operator.AND);
        final CompletableFuture<T> future =
                this.vertxDAO.fetchOneAsync(condition);
        return Async.toFuture(future);
    }

    public <T> Future<T> fetchOneOrAsync(final JsonObject orFilters) {
        final Condition condition = transform(orFilters, Operator.OR);
        final CompletableFuture<T> future =
                this.vertxDAO.fetchOneAsync(condition);
        return Async.toFuture(future);
    }

    // Fetch List
    public <T> Future<List<T>> fetchAsync(final String column, final Object value) {
        final CompletableFuture<List<T>> future =
                this.vertxDAO.fetchAsync(DSL.field(column), Arrays.asList(value));
        return Async.toFuture(future);
    }

    public <T> Future<List<T>> fetchInAsync(final String column, final Object... value) {
        final CompletableFuture<List<T>> future =
                this.vertxDAO.fetchAsync(DSL.field(column), Arrays.asList(value));
        return Async.toFuture(future);
    }

    public <T> Future<List<T>> fetchAndAsync(final JsonObject andFilters) {
        final Condition condition = transform(andFilters, Operator.AND);
        final CompletableFuture<List<T>> future =
                this.vertxDAO.fetchAsync(condition);
        return Async.toFuture(future);
    }

    public <T> Future<List<T>> fetchOrAsync(final JsonObject orFilters) {
        final Condition condition = transform(orFilters, Operator.OR);
        final CompletableFuture<List<T>> future =
                this.vertxDAO.fetchAsync(condition);
        return Async.toFuture(future);
    }

    // Search Operation --------------------------------------------------
    // Search ( Pager, Sort, Projection )
    public <T> Future<List<T>> searchAsync(final Inquiry inquiry) {

        return null;
    }

    // Condition ---------------------------------------------------------
    public static Condition transform(final JsonObject filters, final Operator operator) {
        Condition condition = null;
        for (final String field : filters.fieldNames()) {
            final String key = getKey(field);
            final String targetField = field.split(",")[Values.IDX];
            Object value = filters.getValue(field);
            // Function
            final BiFunction<String, Object, Condition> fun = OPS.get(key);
            // JsonArray to List, fix vert.x and jooq connect issue.
            if (Types.isJArray(value)) {
                value = ((JsonArray) value).getList().toArray();
            }
            final Condition item = fun.apply(targetField, value);
            condition = opCond(condition, item, Operator.AND);
            // Function condition inject
        }
        LOGGER.info(Info.JOOQ_PARSE, condition);
        return condition;
    }

    private static String getKey(final String field) {
        if (!field.contains(",")) {
            return "=";
        } else {
            final String opStr = field.split(",")[Values.ONE];
            return StringUtil.isNil(opStr) ? "=" : opStr.trim().toLowerCase();
        }
    }

    private static Condition opIn(final String field, final Object value) {
        return opCond(value, Operator.OR, DSL.field(field)::eq);
    }

    private static Condition opNotIn(final String field,
                                     final Object value) {
        return opCond(value, Operator.OR, DSL.field(field)::ne);
    }

    private static Condition opCond(final Object value,
                                    final Operator operator,
                                    final Function<Object, Condition> condFun) {
        // Using or instead of in
        Condition condition = null;
        // Params
        final Collection values = Types.toCollection(value);
        if (null != values) {
            for (final Object item : values) {
                final Condition itemCond = condFun.apply(item);
                condition = opCond(condition, itemCond, operator);
            }
        }
        return condition;
    }

    private static Condition opCond(final Condition left,
                                    final Condition right,
                                    final Operator operator) {
        if (null == left || null == right) {
            if (null == left && null != right) {
                return right;
            } else {
                return null;
            }
        } else {
            if (Operator.AND == operator) {
                return left.and(right);
            } else {
                return left.or(right);
            }
        }
    }

    private static final ConcurrentMap<String, BiFunction<String, Object, Condition>> OPS =
            new ConcurrentHashMap<String, BiFunction<String, Object, Condition>>() {
                {
                    this.put(Inquiry.Op.LT, (field, value) -> DSL.field(field).lt(value));
                    this.put(Inquiry.Op.GT, (field, value) -> DSL.field(field).gt(value));
                    this.put(Inquiry.Op.LE, (field, value) -> DSL.field(field).le(value));
                    this.put(Inquiry.Op.GE, (field, value) -> DSL.field(field).ge(value));
                    this.put(Inquiry.Op.EQ, (field, value) -> DSL.field(field).eq(value));
                    this.put(Inquiry.Op.NEQ, (field, value) -> DSL.field(field).ne(value));
                    this.put(Inquiry.Op.NOT_NULL, (field, value) -> DSL.field(field).isNotNull());
                    this.put(Inquiry.Op.NULL, (field, value) -> DSL.field(field).isNull());
                    this.put(Inquiry.Op.TRUE, (field, value) -> DSL.field(field).isTrue());
                    this.put(Inquiry.Op.FALSE, (field, value) -> DSL.field(field).isFalse());
                    this.put(Inquiry.Op.IN, UxJooq::opIn);
                    this.put(Inquiry.Op.NOT_IN, UxJooq::opNotIn);
                    this.put(Inquiry.Op.START, (field, value) -> DSL.field(field).startsWith(value));
                    this.put(Inquiry.Op.END, (field, value) -> DSL.field(field).endsWith(value));
                    this.put(Inquiry.Op.CONTAIN, (field, value) -> DSL.field(field).contains(value));
                }
            };
}
