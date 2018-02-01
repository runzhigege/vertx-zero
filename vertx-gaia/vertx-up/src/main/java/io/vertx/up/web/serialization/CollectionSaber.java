package io.vertx.up.web.serialization;

import com.fasterxml.jackson.core.type.TypeReference;
import io.vertx.core.json.JsonArray;
import io.vertx.up.func.Fn;
import io.vertx.up.tool.Jackson;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Enum
 */
public class CollectionSaber extends BaseSaber {
    @Override
    public <T> Object from(final T input) {
        return Fn.get(() -> {
            Object reference = null;
            if (input instanceof Collection) {
                if (input.getClass().isArray()) {
                    final String literal = Jackson.serialize(input);
                    reference = new JsonArray(literal);
                } else if (input instanceof List) {
                    // List
                    reference = Jackson.deserialize(input.toString(), new TypeReference<List>() {
                    });
                } else if (input instanceof Set) {
                    // Set
                    reference = Jackson.deserialize(input.toString(), new TypeReference<Set>() {
                    });
                }
            }
            return reference;
        }, input);
    }
}
