package io.vertx.up.web.serialization;

import io.vertx.up.epic.Ut;
import io.vertx.up.fn.Fn;

/**
 * Enum
 */
public class EnumSaber extends BaseSaber {
    @Override
    public <T> Object from(final T input) {
        return Fn.getNull(() -> {
            Object reference = null;
            if (input instanceof Enum) {
                reference = Ut.invoke(input, "name");
            }
            return reference;
        }, input);
    }
}
