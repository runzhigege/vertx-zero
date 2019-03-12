package io.vertx.up.aiki;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.plugin.jooq.JooqInfix;
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
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

@SuppressWarnings("all")
class JooqAnalyzer {

    private static final Annal LOGGER = Annal.get(JooqAnalyzer.class);
    private static final ConcurrentMap<Integer, VertxDAO> DAO_POOL =
            new ConcurrentHashMap<>();
    private transient final VertxDAO vertxDAO;
    private transient final ConcurrentMap<String, String> mapping =
            new ConcurrentHashMap<>();
    private transient final ConcurrentMap<String, String> revert =
            new ConcurrentHashMap<>();
    private transient Mojo pojo;
    private transient String pojoFile;

    private JooqAnalyzer(final VertxDAO vertxDAO) {
        this.vertxDAO = Fn.pool(DAO_POOL, vertxDAO.hashCode(), () -> vertxDAO);
        // Mapping initializing
        this.initMapping();
    }

    static JooqAnalyzer create(final VertxDAO vertxDAO) {
        return new JooqAnalyzer(vertxDAO);
    }

    private void initMapping() {
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

    void bind(final String pojo, final Class<?> clazz) {
        if (Ut.isNil(pojo)) {
            this.pojoFile = null;
            this.pojo = null;
        } else {
            LOGGER.debug(Info.JOOQ_BIND, pojo, clazz);
            this.pojoFile = pojo;
            this.pojo = Mirror.create(UxJooq.class).mount(pojo)
                    .mojo().put(this.mapping);
            // When bind pojo, the system will analyze columns
            LOGGER.debug(Info.JOOQ_MOJO, this.pojo.getRevert(), this.pojo.getColumns());
        }
    }

    Field getColumn(final String field) {
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
        LOGGER.debug(Info.JOOQ_FIELD, field, targetField);

        return DSL.field(targetField);
    }

    private <T> T skipPrimaryKey(final T entity) {
        final Table<?> tableField = Ut.field(this.vertxDAO, "table");
        final UniqueKey key = tableField.getPrimaryKey();
        key.getFields().stream().map(item -> ((TableField) item).getName())
                .filter(this.revert::containsKey)
                .map(this.revert::get)
                .forEach(item -> Ut.field(entity, item.toString(), null));
        return entity;
    }

    <T> T copyEntity(final T target, final T updated) {
        Fn.outUp(null == updated, LOGGER, JooqMergeException.class,
                UxJooq.class, null == target ? null : target.getClass(), Ut.serialize(target));
        return Fn.getSemi(null == target && null == updated, LOGGER, () -> null, () -> {
            final JsonObject targetJson = null == target ? new JsonObject() : Ut.serializeJson(target);
            final JsonObject sourceJson = Ut.serializeJson(this.skipPrimaryKey(updated));
            targetJson.mergeIn(sourceJson, true);
            final Class<?> type = null == target ? updated.getClass() : target.getClass();
            return (T) Ut.deserialize(targetJson, type);
        });
    }

    String getPojoFile() {
        return this.pojoFile;
    }

    <T> Future<Integer> countAsync(final Inquiry inquiry, final Operator operator) {
        return countAsync(null == inquiry.getCriteria() ? new JsonObject() : inquiry.getCriteria().toJson(), operator);
    }

    <T> Future<Integer> countAsync(final JsonObject filters, final Operator operator) {
        final Function<DSLContext, Integer> function
                = dslContext -> null == filters ? dslContext.fetchCount(this.vertxDAO.getTable()) :
                dslContext.fetchCount(this.vertxDAO.getTable(), JooqCond.transform(filters, operator, this::getColumn));
        return Async.toFuture(this.vertxDAO.executeAsync(function));
    }

    <T> Integer count(final Inquiry inquiry, final Operator operator) {
        return count(null == inquiry.getCriteria() ? new JsonObject() : inquiry.getCriteria().toJson(), operator);
    }

    <T> Integer count(final JsonObject filters, final Operator operator) {
        final DSLContext context = JooqInfix.getDSL();
        return null == filters ? context.fetchCount(this.vertxDAO.getTable()) :
                context.fetchCount(this.vertxDAO.getTable(), JooqCond.transform(filters, operator, this::getColumn));
    }

    <T> Future<List<T>> searchAsync(final JsonObject criteria) {
        final Function<DSLContext, List<T>> function = context -> this.searchInternal(context, criteria);
        return Async.toFuture(this.vertxDAO.executeAsync(function));
    }

    <T> List<T> search(final JsonObject criteria) {
        final DSLContext context = JooqInfix.getDSL();
        return this.searchInternal(context, criteria);
    }

    <T> Future<List<T>> searchAsync(final Inquiry inquiry, final Operator operator) {
        final Function<DSLContext, List<T>> function = context -> this.searchInternal(context, inquiry, operator);
        return Async.toFuture(this.vertxDAO.executeAsync(function));
    }

    <T> List<T> search(final Inquiry inquiry, final Operator operator) {
        final DSLContext context = JooqInfix.getDSL();
        return this.searchInternal(context, inquiry, operator);
    }

    Future<JsonArray> searchJArrayAsync(final Inquiry inquiry, final Operator operator) {
        return this.searchAsync(inquiry, operator).compose(list -> {
            if (null == inquiry.getProjection()) {
                return Ux.thenJsonMore(list);
            } else {
                return Ux.thenJsonMore(list).compose(array -> Uarr.create(array)
                        .remove(inquiry.getProjection().toArray(new String[]{}))
                        .toFuture());
            }
        });
    }

    Future<JsonObject> searchJObjectAsync(final Inquiry inquiry, final String pojo, final Operator operator) {
        final JsonObject response = new JsonObject();
        return this.searchAsync(inquiry, operator)
                .compose(list -> Ux.thenJsonMore(list, pojo))
                .compose(array -> {
                    response.put("list", array);
                    return this.countAsync(inquiry, operator);
                })
                .compose(counter -> {
                    response.put("count", counter);
                    return Future.succeededFuture(response);
                });
    }

    Future<JsonObject> searchJObjectAsync(final Inquiry inquiry, final String pojo) {
        // No Root Operator
        return this.searchJObjectAsync(inquiry, pojo, null);
    }

    <T> JsonObject searchJObject(final Inquiry inquiry, final String pojo, final Operator operator) {
        final JsonObject response = new JsonObject();
        final List<T> list = this.search(inquiry, operator);
        response.put("list", Ux.thenJsonMore(list, pojo));
        final Integer counter = this.count(inquiry, operator);
        response.put("count", counter);
        return response;
    }

    <T> JsonObject searchJObject(final Inquiry inquiry, final String pojo) {
        return searchJObject(inquiry, pojo, null);
    }

    <T> JsonArray searchJArray(final Inquiry inquiry, final Operator operator) {
        final List<T> list = this.search(inquiry, operator);
        JsonArray result = Ux.toArray(list);
        if (null != inquiry.getProjection()) {
            result = Uarr.create(result)
                    .remove(inquiry.getProjection().toArray(new String[]{}))
                    .to();
        }
        return result;
    }

    private <T> List<T> searchInternal(final DSLContext dslContext, final Inquiry inquiry, final Operator operator) {
        // Started steps
        final SelectWhereStep started = dslContext.selectFrom(this.vertxDAO.getTable());
        // Condition set
        SelectConditionStep conditionStep = null;
        if (null != inquiry.getCriteria()) {
            final Condition condition = JooqCond.transform(inquiry.getCriteria().toJson(), operator, this::getColumn);
            conditionStep = started.where(condition);
        }
        // Sorted Enabled
        SelectSeekStepN selectStep = null;
        if (null != inquiry.getSorter()) {
            final JsonObject sorter = inquiry.getSorter().toJson();
            final List<OrderField> orders = new ArrayList<>();
            for (final String field : sorter.fieldNames()) {
                final boolean asc = sorter.getBoolean(field);
                final Field column = this.getColumn(field);
                orders.add(asc ? column.asc() : column.desc());
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
                pagerStep = started.offset(pager.getStart()).limit(pager.getSize());
            } else if (null == selectStep) {
                pagerStep = conditionStep.offset(pager.getStart()).limit(pager.getSize());
            } else {
                pagerStep = selectStep.offset(pager.getStart()).limit(pager.getSize());
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
    }

    private <T> List<T> searchInternal(final DSLContext dslContext, final JsonObject criteria) {
        // Started steps
        final SelectWhereStep started = dslContext.selectFrom(this.vertxDAO.getTable());
        // Condition injection
        SelectConditionStep conditionStep = null;
        if (null != criteria) {
            final Condition condition = JooqCond.transform(criteria, null, this::getColumn);
            conditionStep = started.where(condition);
        }
        return started.fetch(this.vertxDAO.mapper());
    }
}
