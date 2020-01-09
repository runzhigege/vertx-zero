package io.vertx.up.uca.adminicle;

import io.vertx.core.json.JsonObject;
import io.vertx.up.commune.config.DualMapping;

/*
 * Dual Processing for
 * ActIn / ActOut
 */
public interface Mapper {
    /*
     * Mapping
     * to -> from
     */
    JsonObject in(JsonObject in, DualMapping mapping);

    /*
     * Mapping
     * from -> to
     */
    JsonObject out(JsonObject out, DualMapping mapping);
}
