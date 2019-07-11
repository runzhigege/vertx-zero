package io.vertx.up.aiki;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.plugin.jooq.JooqInfix;
import io.vertx.up.atom.Mirror;
import io.vertx.up.atom.Mojo;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.atom.query.Pager;
import io.vertx.up.eon.Values;
import io.vertx.up.log.Annal;
import io.vertx.up.epic.Ut;
import io.vertx.up.exception.zero.JooqFieldMissingException;
import io.vertx.up.exception.zero.JooqMergeException;
import io.vertx.up.fn.Fn;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

@SuppressWarnings("all")
class JooqAnalyzer {

    private static final Annal LOGGER = Annal.get(JooqAnalyzer.class);
    private static final ConcurrentMap<Integer, VertxDAO> DAO_POOL =
            new ConcurrentHashMap<>();
    private transient final VertxDAO vertxDAO;
    /* Field to Column */
    private transient final ConcurrentMap<String, String> mapping =
            new ConcurrentHashMap<>();
    /* Column to Field */
    private transient final ConcurrentMap<String, String> revert =
            new ConcurrentHashMap<>();
    private transient Mojo pojo;
    private transient String pojoFile;

    private JooqAnalyzer(final VertxDAO vertxDAO) {
        this.vertxDAO = Fn.pool(DAO_POOL, vertxDAO.hashCode(), () -> vertxDAO);
        // Mapping initializing
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

    static JooqAnalyzer create(final VertxDAO vertxDAO) {
        return new JooqAnalyzer(vertxDAO);
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

    <T> T copyEntity(final T target, final T updated) {
        Fn.outUp(null == updated, LOGGER, JooqMergeException.class,
                UxJooq.class, null == target ? null : target.getClass(), Ut.serialize(target));
        return Fn.getSemi(null == target && null == updated, LOGGER, () -> null, () -> {
            final JsonObject targetJson = null == target ? new JsonObject() : Ut.serializeJson(target);
            /*
             * Skip Primary Key
             */
            final Table<?> tableField = Ut.field(this.vertxDAO, "table");
            final UniqueKey key = tableField.getPrimaryKey();
            key.getFields().stream().map(item -> ((TableField) item).getName())
                    .filter(this.revert::containsKey)
                    .map(this.revert::get)
                    .forEach(item -> Ut.field(updated, item.toString(), null));
            /*
             * Deserialization
             */
            final JsonObject sourceJson = Ut.serializeJson(updated);
            targetJson.mergeIn(sourceJson, true);
            final Class<?> type = null == target ? updated.getClass() : target.getClass();
            return (T) Ut.deserialize(targetJson, type);
        });
    }

    String getPojoFile() {
        return this.pojoFile;
    }

    /*
     * Pagination Searching
     */
    Future<JsonObject> searchPaginationAsync(final Inquiry inquiry, final String pojo) {
        final JsonObject response = new JsonObject();
        return this.searchAsync(inquiry)
                .compose(list -> Ux.thenJsonMore(list, pojo))
                .compose(array -> {
                    response.put("list", array);
                    return this.countAsync(inquiry);
                })
                .compose(counter -> {
                    response.put("count", counter);
                    return Future.succeededFuture(response);
                });
    }

    <T> JsonObject searchPagination(final Inquiry inquiry, final String pojo) {
        final JsonObject response = new JsonObject();
        final List<T> list = this.search(inquiry);
        response.put("list", Ux.thenJsonMore(list, pojo));
        final Integer counter = this.count(inquiry);
        response.put("count", counter);
        return response;
    }

    /*
     * Basic Method for low tier search/count pair
     */
    <T> Future<Integer> countAsync(final Inquiry inquiry) {
        return countAsync(null == inquiry.getCriteria() ? new JsonObject() : inquiry.getCriteria().toJson());
    }

    <T> Future<Integer> countAsync(final JsonObject filters) {
        final Function<DSLContext, Integer> function
                = dslContext -> null == filters ? dslContext.fetchCount(this.vertxDAO.getTable()) :
                dslContext.fetchCount(this.vertxDAO.getTable(), JooqCond.transform(filters, this::getColumn));
        return Async.toFuture(this.vertxDAO.executeAsync(function));
    }

    <T> Future<List<T>> searchAsync(final Inquiry inquiry) {
        final Function<DSLContext, List<T>> function = context -> this.searchInternal(context, inquiry);
        return Async.toFuture(this.vertxDAO.executeAsync(function));
    }

    <T> Future<List<T>> searchAsync(final JsonObject criteria) {
        final Function<DSLContext, List<T>> function = context -> this.searchInternal(context, criteria);
        return Async.toFuture(this.vertxDAO.executeAsync(function));
    }

    <T> List<T> search(final Inquiry inquiry) {
        final DSLContext context = JooqInfix.getDSL();
        return this.searchInternal(context, inquiry);
    }

    <T> List<T> search(final JsonObject criteria) {
        final DSLContext context = JooqInfix.getDSL();
        return this.searchInternal(context, criteria);
    }

    <T> Integer count(final Inquiry inquiry) {
        return count(null == inquiry.getCriteria() ? new JsonObject() : inquiry.getCriteria().toJson());
    }

    <T> Integer count(final JsonObject filters) {
        final DSLContext context = JooqInfix.getDSL();
        return null == filters ? context.fetchCount(this.vertxDAO.getTable()) :
                context.fetchCount(this.vertxDAO.getTable(), JooqCond.transform(filters, this::getColumn));
    }

    /*
     * Search Internal
     */
    /*
     * Major search code logical inner.
     * Jooq Engine supported
     * 1) sorter
     * 2) pager
     * 3) projection
     * 4) criteria
     */
    private <T> List<T> searchInternal(final DSLContext dslContext, final Inquiry inquiry) {
        // Started steps
        final SelectWhereStep started = dslContext.selectFrom(this.vertxDAO.getTable());
        // Condition set
        SelectConditionStep conditionStep = null;
        if (null != inquiry.getCriteria()) {
            final Condition condition = JooqCond.transform(inquiry.getCriteria().toJson(), this::getColumn);
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
        // Projection
        final Set<String> projectionSet = inquiry.getProjection();
        final JsonArray projection = Objects.isNull(projectionSet) ? new JsonArray() : Ut.toJArray(projectionSet);
        // Returned one by one
        if (null != pagerStep) {
            return JooqResult.toResult(pagerStep.fetch(this.vertxDAO.mapper()), projection, this.pojo);
        }
        if (null != selectStep) {
            return JooqResult.toResult(selectStep.fetch(this.vertxDAO.mapper()), projection, this.pojo);
        }
        if (null != conditionStep) {
            return JooqResult.toResult(conditionStep.fetch(this.vertxDAO.mapper()), projection, this.pojo);
        }
        return JooqResult.toResult(started.fetch(this.vertxDAO.mapper()), projection, this.pojo);
    }

    private <T> List<T> searchInternal(final DSLContext dslContext, final JsonObject criteria) {
        // Started steps
        final SelectWhereStep started = dslContext.selectFrom(this.vertxDAO.getTable());
        // Condition injection
        SelectConditionStep conditionStep = null;
        if (null != criteria) {
            final Condition condition = JooqCond.transform(criteria, this::getColumn);
            conditionStep = started.where(condition);
        }
        // Projection
        return started.fetch(this.vertxDAO.mapper());
    }
}
