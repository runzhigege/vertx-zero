package io.vertx.tp.rbac.authority;

import io.vertx.core.json.JsonArray;
import io.zero.epic.fn.Fn;

import java.util.List;

/*
 * Detent for ( ProfileType = Detent )
 */
public interface ScDetent {

    static ScDetent user() {
        return Fn.pool(Pool.DETENT_POOL, UserDetent.class.getName(),
                UserDetent::new);
    }

    JsonArray proc(List<ScProfile> profile);
}
