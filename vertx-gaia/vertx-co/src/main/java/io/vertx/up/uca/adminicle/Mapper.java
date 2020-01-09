package io.vertx.up.uca.adminicle;

import io.vertx.core.json.JsonObject;
import io.vertx.up.commune.config.DualItem;

/*
 * Dual Processing for
 * ActIn / ActOut
 */
public interface Mapper {
    /*
     * Mapping
     * to -> from
     */
    JsonObject in(JsonObject in, DualItem mapping);

    /*
     * Mapping
     * from -> to
     */
    JsonObject out(JsonObject out, DualItem mapping);
}
