package io.vertx.up.uca.condition;

import io.vertx.core.json.JsonArray;
import io.vertx.up.log.Annal;
import org.jooq.Condition;
import org.jooq.Field;

import java.util.Objects;
import java.util.function.Function;

@SuppressWarnings("all")
public class ClauseString implements Clause {
    @Override
    public Condition where(final Field columnName, final String fieldName, final String op, final Object value) {
        final Term term = Pool.TERM_OBJECT_MAP.get(op);
        if (Objects.isNull(term)) {
            logger().warn(Info.JOOQ_TERM_ERR, op);
        } else {
            logger().info(Info.JOOQ_TERM, term, op);
        }
        return term.where(columnName, fieldName, value);
    }

    protected Annal logger() {
        return Annal.get(getClass());
    }

    protected Object normalized(final Object value, final Function<Object, Object> convert) {
        if (value instanceof JsonArray) {
            final JsonArray result = new JsonArray();
            ((JsonArray) value).stream().map(convert)
                    .filter(Objects::nonNull).forEach(result::add);
            return result;
        } else {
            return convert.apply(value);
        }
    }
}
