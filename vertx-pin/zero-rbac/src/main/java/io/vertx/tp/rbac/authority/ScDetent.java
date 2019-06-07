package io.vertx.tp.rbac.authority;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.Ux;
import io.zero.epic.fn.Fn;

import java.util.List;

/*
 * Detent for ( ProfileType = Detent )
 */
public interface ScDetent {

    static ScDetent user(final JsonObject input) {
        return Fn.pool(Pool.DETENT_POOL, UserDetent.class.getName(),
                () -> new UserDetent(input));
    }

    JsonObject proc(List<ScProfile> profiles);

    default Future<JsonObject> procAsync(final List<ScProfile> profiles) {
        return Ux.toFuture(this.proc(profiles));
    }
}
