package io.vertx.up.uca.condition;

import org.jooq.Condition;
import org.jooq.Field;

@SuppressWarnings("all")
public class ClauseInstant extends ClauseString {
    @Override
    public Condition where(final Field columnName, final String fieldName, final String op, final Object value) {
        final Class<?> type = value.getClass();
        System.out.println(value);
        return super.where(columnName, fieldName, op, value);
    }
}
