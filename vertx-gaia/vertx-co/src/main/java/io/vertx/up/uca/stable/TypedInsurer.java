package io.vertx.up.uca.stable;

import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.em.DataType;
import io.vertx.up.exception.ZeroException;
import io.vertx.up.exception.demon.DataTypeWrongException;
import io.vertx.up.fn.Fn;
import io.vertx.up.util.Ut;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * Type Validation
 */
public class TypedInsurer extends AbstractInsurer {

    private static final ConcurrentMap<DataType, Function<Object, Boolean>>
            FUNS = new ConcurrentHashMap<DataType, Function<Object, Boolean>>() {
        {
            put(DataType.BOOLEAN, Ut::isBoolean);
            put(DataType.STRING, (input) -> Boolean.TRUE);
            put(DataType.INTEGER, Ut::isInteger);
            put(DataType.DECIMAL, Ut::isDecimal);
            put(DataType.DATE, Ut::isDate);
            put(DataType.JOBJECT, Ut::isJObject);
            put(DataType.JARRAY, Ut::isJArray);
            put(DataType.CLASS, Ut::isClass);
        }
    };

    /**
     * @param data input data that should be verified.
     * @param rule rule config data
     * @throws ZeroException Insure exception
     * @see "STRING | INTEGER | DECIMAL | BOOLEAN | JOBJECT | JARRAY | DATE"
     */
    @Override
    public void flumen(final JsonObject data, final JsonObject rule)
            throws ZeroException {
        // 1. If rule is null, skip
        Fn.shuntZero(() -> {
            // 2. extract rule from rule, only required accept
            if (rule.containsKey(Rules.TYPED)) {
                final JsonObject fields = rule.getJsonObject(Rules.TYPED);
                Ut.etJObject(fields, (item, name) -> {
                    // 3. extract key for field definition
                    final DataType key = Ut.toEnum(DataType.class,
                            item.toString());
                    final Function<Object, Boolean> fnTest
                            = FUNS.getOrDefault(key, (input) -> Boolean.TRUE);
                    // 4. checking failure, the pre-condition is that data contains checked key.
                    if (data.containsKey(name)) {
                        final Object value = data.getValue(name);

                        Fn.outZero(!fnTest.apply(data.getValue(name)), getLogger(),
                                DataTypeWrongException.class,
                                getClass(), name, value, key);
                    }
                });
            }
        }, rule, data);
    }
}
