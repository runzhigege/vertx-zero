package io.vertx.up.aiki;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.plugin.jooq.JooqInfix;
import io.vertx.up.atom.query.Criteria;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.atom.query.Pager;
import io.vertx.up.log.Annal;
import io.vertx.zero.atom.Mirror;
import io.vertx.zero.atom.Mojo;
import io.vertx.zero.eon.Values;
import io.vertx.zero.exception.JooqFieldMissingException;
import io.vertx.zero.exception.JooqMergeException;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("all")
public class UxJooq {

    private static final Annal LOGGER = Annal.get(UxJooq.class);

    private transient final Class<?> clazz;
    private transient final VertxDAO vertxDAO;
    private transient final ConcurrentMap<String, String> mapping =
            new ConcurrentHashMap<>();
    private transient final ConcurrentMap<String, String> revert =
            new ConcurrentHashMap<>();
    private transient Mojo pojo;
    private transient String pojoFile;

    <T> UxJooq(final Class<T> clazz, final VertxDAO vertxDAO) {
        this.clazz = clazz;
        this.vertxDAO = vertxDAO;
        // Analyzing Column
        analyzeColumns();
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

    // Bind current jooq to pojo configuration file.
    public UxJooq on(final String pojo) {
        if (Ut.isNil(pojo)) {
            this.pojoFile = null;
            this.pojo = null;
        } else {
            LOGGER.debug(Info.JOOQ_BIND, pojo, this.clazz);
            this.pojoFile = pojo;
            this.pojo = Mirror.create(UxJooq.class).mount(pojo)
                    .mojo().put(this.mapping);
            // When bind pojo, the system will analyze columns
            LOGGER.debug(Info.JOOQ_MOJO, this.pojo.getRevert(), this.pojo.getColumns());
        }
        return this;
    }

    private void analyzeColumns() {
        final Table<?> tableField = Ut.field(this.vertxDAO, "table");
        final Class<?> typeCls = Ut.field(this.vertxDAO, "type");
        final java.lang.reflect.Field[] fields = Ut.fields(typeCls);
        // Analyze Type and definition sequence, columns hitted.
        final Field[] columns = tableField.fields();
        // Mapping building
        for (int idx = Values.IDX; idx < columns.length; idx++) {
            final Field column = columns[idx];
            final java.lang.reflect.Field field = fields[idx];
            this.mapping.put(field.getName(), column.getName());
            this.revert.put(column.getName(), field.getName());
        }
    }

    /**
     * Mojo
     * param came from request instead of Pojo declared.
     * Mapping: field = param
     * Revert: param = field
     * Column: field = column
     *
     * @param field
     * @return
     */
    private Field column(final String field) {
        String targetField;
        if (null == this.pojo) {
            if (this.mapping.values().contains(field)) {
                // 1.1 No Mojo bind, find column first
                targetField = field;
            } else {
                // 1.2 field does not exist in table columns
                // consider field as field
                targetField = this.mapping.get(field);
            }
        } else {
            if (this.pojo.getColumns().containsValue(field)) {
                // 2.1. Mojo bind, find column first
                targetField = field;
            } else {
                // 2.2. Mojo bind, consider field as param field first
                targetField = this.pojo.getRevert().get(field);
                if (null == targetField) {
                    // 2.2.1. If target field is null, consider field as pojo field
                    targetField = field;
                }
                // 2.3. Find column
                targetField = this.mapping.get(targetField);
            }
        }
        Fn.outUp(null == targetField, LOGGER,
                JooqFieldMissingException.class, UxJooq.class, field, Ut.field(this.vertxDAO, "type"));
        LOGGER.debug(Info.JOOQ_FIELD, targetField);

        return DSL.field(targetField);
    }

    private <T> T skipPk(final T entity) {
        final Table<?> tableField = Ut.field(this.vertxDAO, "table");
        final UniqueKey key = tableField.getPrimaryKey();
        key.getFields().stream().map(item -> ((TableField) item).getName())
                .filter(this.revert::containsKey)
                .map(this.revert::get)
                .forEach(item -> Ut.field(entity, item.toString(), null));
        return entity;
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

    // CRUD - Upsert ----------------------------------------------------
    private <T> T copyEntity(final T target, final T updated) {
        Fn.outUp(null == updated, LOGGER, JooqMergeException.class,
                UxJooq.class, null == target ? null : target.getClass(), Ut.serialize(target));
        final JsonObject targetJson = null == target ? new JsonObject() : Ut.serializeJson(target);
        final JsonObject sourceJson = Ut.serializeJson(skipPk(updated));
        targetJson.mergeIn(sourceJson, true);
        return (T) Ut.deserialize(targetJson, target.getClass());
    }

    // CRUD - Existing/Missing ------------------------------------------

    public <T> Future<T> saveAsync(final Object id, final T updated) {
        return saveAsync(id, (target) -> copyEntity(target, updated));
    }

    public <T> Future<T> saveAsync(final Object id, final Function<T, T> copyFun) {
        return this.<T>findByIdAsync(id).compose(old ->
                this.<T>updateAsync(copyFun.apply(old)));
    }

    public <T> Future<T> upsertReturningPrimaryAsync(final JsonObject andFilters, final T updated, final Consumer<Long> consumer) {
        return this.<T>fetchOneAndAsync(andFilters)
                .compose(item -> Fn.match(
                        // null != item, updated to existing item.
                        Fn.fork(() -> this.<T>updateAsync(copyEntity(item, updated))),
                        // null == item, insert data
                        Fn.branch(null == item, () -> this.insertReturningPrimaryAsync(updated, consumer))
                ));
    }

    public <T> Future<T> upsertAsync(final JsonObject andFilters, final T updated) {
        return this.<T>fetchOneAndAsync(andFilters)
                .compose(item -> Fn.match(
                        // null != item, updated to existing item.
                        Fn.fork(() -> this.<T>updateAsync(copyEntity(item, updated))),
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
                this.vertxDAO.fetchOneAsync(column(field), value);
        return Async.toFuture(future);
    }

    // Filter transform called
    public <T> Future<T> fetchOneAndAsync(final JsonObject andFilters) {
        final Condition condition = JooqCond.transform(andFilters, Operator.AND, this::column);
        final CompletableFuture<T> future =
                this.vertxDAO.fetchOneAsync(condition);
        return Async.toFuture(future);
    }

    // Filter column called
    // Fetch List
    public <T> Future<List<T>> fetchAsync(final String field, final Object value) {
        final CompletableFuture<List<T>> future =
                this.vertxDAO.fetchAsync(column(field), Arrays.asList(value));
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
                this.vertxDAO.fetchAsync(column(field), values.getList());
        return Async.toFuture(future);
    }

    // Filter transform called
    public <T> Future<List<T>> fetchAndAsync(final JsonObject andFilters) {
        final Condition condition = JooqCond.transform(andFilters, Operator.AND, this::column);
        final CompletableFuture<List<T>> future =
                this.vertxDAO.fetchAsync(condition);
        return Async.toFuture(future);
    }

    // Filter transform called
    public <T> Future<List<T>> fetchOrAsync(final JsonObject orFilters) {
        final Condition condition = JooqCond.transform(orFilters, Operator.OR, this::column);
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
                    return countSearchAsync(inquiry, Operator.OR);
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

    public <T> Future<JsonObject> searchAsync(final Inquiry inquiry, final String pojo) {
        final JsonObject result = new JsonObject();
        return searchAsync(inquiry, Operator.AND)
                .compose(list -> Ux.thenJsonMore(list, pojo))
                .compose(array -> {
                    result.put("list", array);
                    return countSearchAsync(inquiry, Operator.OR);
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
                    return countSearchAsync(inquiry, Operator.AND);
                })
                .compose(count -> {
                    result.put("count", count);
                    return Future.succeededFuture(result);
                });
    }

    public <T> Future<List<T>> searchAndListAsync(final Inquiry inquiry) {
        // Fast mode is search AND operator
        return searchAsync(inquiry, Operator.AND);
    }

    public <T> Future<List<T>> searchOrListAsync(final Inquiry inquiry) {
        // Fast mode is search AND operator
        return searchAsync(inquiry, Operator.OR);
    }

    private Future<JsonArray> searchDirect(final Inquiry inquiry, final Operator operator) {
        // Pager, Sort, Criteria, Projection
        return searchAsync(inquiry, operator)
                .compose(list -> {
                    if (null == inquiry.getProjection()) {
                        return Ux.thenJsonMore(list);
                    } else {
                        return Ux.thenJsonMore(list)
                                .compose(array -> Uarr.create(array)
                                        .remove(inquiry.getProjection().toArray(new String[]{}))
                                        .toFuture());
                    }
                });
    }

    // Filter transform called
    private <T> Future<Integer> countSearchAsync(final Inquiry inquiry, final Operator operator) {
        final Criteria criteria = inquiry.getCriteria();
        final Function<DSLContext, Integer> function
                = dslContext -> null == criteria ? dslContext.fetchCount(this.vertxDAO.getTable()) :
                dslContext.fetchCount(this.vertxDAO.getTable(), JooqCond.transform(criteria.toJson(), operator, this::column));
        return Async.toFuture(this.vertxDAO.executeAsync(function));
    }

    // Filter transform called
    public <T> Future<List<T>> searchAsync(final Inquiry inquiry, final Operator operator) {
        // Pager, Sort, Criteria Only, this mode do not support projection
        final Function<DSLContext, List<T>> function
                = (dslContext) -> {
            // Started steps
            final SelectWhereStep started = dslContext.selectFrom(this.vertxDAO.getTable());
            // Condition set
            SelectConditionStep conditionStep = null;
            if (null != inquiry.getCriteria()) {
                final Condition condition = JooqCond.transform(inquiry.getCriteria().toJson(), operator, this::column);
                conditionStep = started.where(condition);
            }
            // Sorted Enabled
            SelectSeekStepN selectStep = null;
            if (null != inquiry.getSorter()) {
                final JsonObject sorter = inquiry.getSorter().toJson();
                final List<OrderField> orders = new ArrayList<>();
                for (final String field : sorter.fieldNames()) {
                    final boolean asc = sorter.getBoolean(field);
                    orders.add(asc ? DSL.field(field).asc() : DSL.field(field).desc());
                }
                if (null == conditionStep) {
                    selectStep = started.orderBy(orders);
                } else {
                    selectStep = conditionStep.orderBy(orders);
                }
            }
            // Pager Enabled
            SelectWithTiesAfterOffsetStep pagerStep = null;
            if (null != inquiry.getPager()) {
                final Pager pager = inquiry.getPager();
                if (null == selectStep && null == conditionStep) {
                    pagerStep = started.offset(pager.getStart()).limit(pager.getEnd());
                } else if (null == selectStep) {
                    pagerStep = conditionStep.offset(pager.getStart()).limit(pager.getEnd());
                } else {
                    pagerStep = selectStep.offset(pager.getStart()).limit(pager.getEnd());
                }
            }
            // Returned one by one
            if (null != pagerStep) {
                return pagerStep.fetch(this.vertxDAO.mapper());
            }
            if (null != selectStep) {
                return selectStep.fetch(this.vertxDAO.mapper());
            }
            if (null != conditionStep) {
                return conditionStep.fetch(this.vertxDAO.mapper());
            }
            return started.fetch(this.vertxDAO.mapper());
        };
        return Async.toFuture(this.vertxDAO.executeAsync(function));
    }
}
