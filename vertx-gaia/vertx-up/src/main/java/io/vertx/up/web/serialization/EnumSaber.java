package io.vertx.up.web.serialization;

import io.vertx.up.epic.fn.Fn;
import io.vertx.up.epic.mirror.Instance;

/**
 * Enum
 */
public class EnumSaber extends BaseSaber {
    @Override
    public <T> Object from(final T input) {
        return Fn.getNull(() -> {
            Object reference = null;
            if (input instanceof Enum) {
                reference = Instance.invoke(input, "name");
            }
            return reference;
        }, input);
    }
}
