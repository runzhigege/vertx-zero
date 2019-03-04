package io.vertx.up.rs;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.up.eon.Orders;

/**
 * Basic dynamic spec extension, every dynamic router must
 * implements this interface for building dynamic routing system.
 */
public interface PlugRouter {

    void mount(Router router, JsonObject config);

    /**
     * We suggest do not overwrite this value once you haven't known the internal
     * architecture of Zero Framework.
     */
    default int getOrder() {
        return Orders.DYNAMIC;
    }
}
