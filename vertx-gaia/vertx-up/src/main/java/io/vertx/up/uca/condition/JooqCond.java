package io.vertx.up.uca.condition;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.query.Criteria;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.atom.query.Sorter;
import io.vertx.up.eon.Strings;
import io.vertx.up.eon.Values;
import io.vertx.up.log.Annal;
import io.vertx.up.util.Ut;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Operator;
import org.jooq.OrderField;
import org.jooq.impl.DSL;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.Function;

@SuppressWarnings("rawtypes")
public class JooqCond {

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
                    this.put(Inquiry.Op.IN, (field, value) -> {
                        final Collection<?> values = Ut.toCollection(value);
                        return DSL.field(field).in(values);
                    });
                    this.put(Inquiry.Op.NOT_IN, (field, value) -> {
                        final Collection<?> values = Ut.toCollection(value);
                        return DSL.field(field).notIn(values);
                    });
                    this.put(Inquiry.Op.START, (field, value) -> DSL.field(field).startsWith(value));
                    this.put(Inquiry.Op.END, (field, value) -> DSL.field(field).endsWith(value));
                    this.put(Inquiry.Op.CONTAIN, (field, value) -> DSL.field(field).contains(value));
                }
            };
    private static final Annal LOGGER = Annal.get(JooqCond.class);

    private static String applyField(final String field,
                                     final Function<String, String> fnTable) {
        final Set<String> keywords = new HashSet<String>() {
            {
                this.add("KEY"); // MYSQL, KEY is keyword
            }
        };
        final StringBuilder normalized = new StringBuilder();
        if (Objects.nonNull(fnTable)) {
            normalized.append(fnTable.apply(field)).append(".");
        }
        normalized.append(keywords.contains(field) ? "`" + field + "`" : field);
        return normalized.toString();
    }

    // OrderField
    public static List<OrderField> orderBy(final Sorter sorter,
                                           final Function<String, Field> fnAnalyze,
                                           final Function<String, String> fnTable) {
        final JsonObject sorterJson = sorter.toJson();
        final List<OrderField> orders = new ArrayList<>();
        for (final String field : sorterJson.fieldNames()) {
            final boolean asc = sorterJson.getBoolean(field);
            if (Objects.isNull(fnTable)) {
                final Field column = fnAnalyze.apply(field);
                orders.add(asc ? column.asc() : column.desc());
            } else {
                final Field original = fnAnalyze.apply(field);
                final String prefix = fnTable.apply(original.getName());
                final Field normalized = DSL.field(prefix + "." + original.getName());
                orders.add(asc ? normalized.asc() : normalized.desc());
            }
        }
        return orders;
    }

    // Condition ---------------------------------------------------------
    public static Condition transform(final JsonObject filters,
                                      final Function<String, Field> fnAnalyze,
                                      final Function<String, String> fnTable) {
        return transform(filters, null, fnAnalyze, fnTable);
    }

    public static Condition transform(final JsonObject filters,
                                      final Function<String, Field> fnAnalyze) {
        return transform(filters, null, fnAnalyze);
    }

    public static Condition transform(final JsonObject filters,
                                      Operator operator,
                                      final Function<String, Field> fnAnalyze,
                                      final Function<String, String> fnTable) {
        final Condition condition;
        final Criteria criteria = Criteria.create(filters);
        /*
         * The mode has been selected by criteria, the condition is as following:
         * When filters contains the key = value ( value = JsonObject ), TREE
         * Otherwise it's LINEAR.
         */
        if (!Ut.isNil(filters)) {
            LOGGER.info("[ ZERO ] ( Query ) Mode selected {0}, filters raw = {1}",
                    criteria.getMode(), filters);
        }
        if (Inquiry.Mode.LINEAR == criteria.getMode()) {
            JsonObject inputFilters = filters;
            if (null == operator) {
                /*
                 * When the mode is linear, the system will be sure filters contains
                 * no value with JsonObject, remove all JsonObject value to switch
                 * LINEAR mode.
                 */
                inputFilters = transformLinear(filters);
                /*
                 * Re-calculate the operator AND / OR
                 * For complex normalize linear query tree.
                 */
                if (inputFilters.containsKey(Strings.EMPTY)) {
                    if (inputFilters.getBoolean(Strings.EMPTY)) {
                        operator = Operator.AND;
                    } else {
                        operator = Operator.OR;
                    }
                    inputFilters.remove(Strings.EMPTY);
                }
            } else {
                /*
                 * When LINEAR mode, operator is hight priority, the query engine will
                 * ignore the flag key = value. ( key = "", value = true )
                 * It's defined by zero.
                 */
                inputFilters.remove(Strings.EMPTY);
            }
            condition = transformLinear(inputFilters, operator, fnAnalyze, fnTable);
        } else {
            /*
             * When the mode is Tree, you mustn't set operator, because the operator will
             * be parsed by query tree engine, this operation is unsupported and it will
             * throw out exception JooqModeConflictException,
             * Ignore operator information here, because the next analyzing will ignore automatically.
             */
            /*Fn.outUp(null != operator, LOGGER, JooqModeConflictException.class,
                    class, Inquiry.Mode.LINEAR, filters);*/
            condition = transformTree(filters, fnAnalyze, fnTable);
        }
        if (null != condition) {
            LOGGER.info(Info.JOOQ_PARSE, condition);
        }
        return condition;
    }

    public static Condition transform(final JsonObject filters,
                                      final Operator operator,
                                      final Function<String, Field> fnAnalyze) {
        return transform(filters, operator, fnAnalyze, null);
    }

    private static Condition transformTree(final JsonObject filters,
                                           final Function<String, Field> fnAnalyze,
                                           final Function<String, String> fnTable) {
        Condition condition;
        // Calc operator in this level
        final Operator operator = calcOperator(filters);
        // Calc liner
        final JsonObject cloned = filters.copy();
        cloned.remove(Strings.EMPTY);
        // Operator has been calculated, remove "" to set linear of current tree.
        final Condition linear = transformLinear(transformLinear(cloned), operator, fnAnalyze, fnTable);
        // Calc All Tree
        final List<Condition> tree = transformTreeSet(filters, fnAnalyze, fnTable);
        // Merge the same level
        if (null != linear) {
            tree.add(linear);
        }
        if (1 == tree.size()) {
            condition = tree.get(Values.IDX);
        } else {
            condition = tree.get(Values.IDX);
            for (int idx = Values.ONE; idx < tree.size(); idx++) {
                final Condition right = tree.get(idx);
                condition = opCond(condition, right, operator);
            }
        }
        return condition;
    }

    private static List<Condition> transformTreeSet(
            final JsonObject filters,
            final Function<String, Field> fnAnalyze,
            final Function<String, String> fnTable) {
        final List<Condition> conditions = new ArrayList<>();
        final JsonObject tree = filters.copy();
        if (!tree.isEmpty()) {
            for (final String field : filters.fieldNames()) {
                if (Ut.isJObject(tree.getValue(field))) {
                    conditions.add(transformTree(tree.getJsonObject(field), fnAnalyze, fnTable));
                }
            }
        }
        return conditions;
    }

    private static JsonObject transformLinear(final JsonObject filters) {
        final JsonObject linear = filters.copy();
        for (final String field : filters.fieldNames()) {
            if (Ut.isJObject(linear.getValue(field))) {
                linear.remove(field);
            }
        }
        return linear;
    }

    @SuppressWarnings("all")
    private static Operator calcOperator(final JsonObject data) {
        final Operator operator;
        if (!data.containsKey(Strings.EMPTY)) {
            operator = Operator.OR;
        } else {
            final Boolean isAnd = Boolean.valueOf(data.getValue(Strings.EMPTY).toString());
            operator = isAnd ? Operator.AND : Operator.OR;
        }
        return operator;
    }

    private static Condition transformLinear(
            final JsonObject filters,
            final Operator operator,
            final Function<String, Field> fnAnalyze,
            final Function<String, String> fnTable) {
        final Condition condition = null;
        for (final String field : filters.fieldNames()) {
            /*
             * field analyzing first
             */
            final Object value = filters.getValue(field);

            /*
             * Code flow 1
             * - When `field` value is [] ( JsonArray ), the system must convert the result to
             *   field,i: []
             * - The statement is fixed structure for different query
             */
            final String[] fields;
            if (value instanceof JsonArray) {
                if (field.contains(",")) {
                    /*
                     * field,? = []
                     */
                    fields = field.split(",");
                } else {
                    /*
                     * field = []
                     */
                    fields = new String[2];
                    fields[Values.IDX] = field;
                }
                fields[Values.ONE] = Inquiry.Op.IN;
            } else {
                /*
                 * Common situation
                 * field,op = value
                 */
                fields = field.split(",");
            }

            /*
             * Code flow 2
             * - Get op string here to match future usage
             */
            final String op;
            if (value instanceof JsonArray) {
                op = Inquiry.Op.IN;
            } else if (!field.contains(",")) {
                op = Inquiry.Op.EQ;
            } else {
                final String extract = fields[Values.ONE];
                if (Objects.isNull(extract)) {
                    op = Inquiry.Op.EQ;
                } else {
                    op = extract.trim().toLowerCase();
                }
            }

            /*
             * Code flow 3
             * - Get `Field` definition for current field
             */
            final String targetField = fields[Values.IDX];
            final Field metaField = fnAnalyze.apply(targetField);

            /*
             * 1) fields = ( field,op )
             * 2) op
             * 3) Field object reference
             */
            final Class<?> type = metaField.getType();
            System.out.println(metaField.getType());
            System.out.println(metaField.getDataType());
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
}
