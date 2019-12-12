package io.vertx.up.uca.condition;

import org.jooq.Condition;
import org.jooq.Field;

/*
 * Channel for Type
 * 1) Object -> UnifiedClause
 * 2) String -> StringClause
 * 3) Boolean -> BooleanClause
 */
@SuppressWarnings("all")
public interface Clause {

    Condition where(final Field columnName, final String fieldName, final String op, final Object value);
}
