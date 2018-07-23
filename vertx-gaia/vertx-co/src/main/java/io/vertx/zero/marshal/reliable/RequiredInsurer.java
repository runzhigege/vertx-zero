package io.vertx.zero.marshal.reliable;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.epic.Ut;
import io.vertx.up.epic.fn.Fn;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.exception.demon.RequiredFieldException;

/**
 * Required validation
 */
public class RequiredInsurer extends AbstractInsurer {
    /**
     * @param data input data that should be verified.
     * @param rule rule config data
     * @throws ZeroException Insure exception
     */
    @Override
    public void flumen(final JsonObject data, final JsonObject rule) throws ZeroException {
        // 1. If rule is null, skip
        Fn.shuntZero(() -> {
            // 2. extract rule from rule, only required accept
            if (rule.containsKey(Rules.REQUIRED)) {
                final JsonArray fields = Ut.toJArray(rule.getValue(Rules.REQUIRED));
                Ut.etJArray(fields, String.class, (field, index) -> {
                    // 3.Check if data contains field.
                    // Fast throw out
                    Fn.outZero(!data.containsKey(field), this.getLogger(),
                            RequiredFieldException.class,
                            this.getClass(), data, field);
                });
            }
        }, rule, data);
    }
}
