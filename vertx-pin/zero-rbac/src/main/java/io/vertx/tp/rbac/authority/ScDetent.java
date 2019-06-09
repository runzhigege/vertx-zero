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
        return Fn.pool(Pool.DETENT_POOL, ScDetentRole.class.getName(),
                () -> new ScDetentRole(input));
    }

    static ScDetent group(final JsonObject input) {
        return Fn.pool(Pool.DETENT_POOL, ScDetentGroup.class.getName(),
                () -> new ScDetentGroup(input));
    }

    JsonObject proc(List<ProfileRole> profiles);

    default Future<JsonObject> procAsync(final List<ProfileRole> profiles) {
        return Ux.toFuture(this.proc(profiles));
    }

    /*
     * Internal default group
     */
    interface Group {

        static ScDetent horizon() {
            return Fn.pool(Pool.DETENT_POOL, SgHorizon.class.getName(), SgHorizon::new);
        }

        static ScDetent critical() {
            return Fn.pool(Pool.DETENT_POOL, SgCritical.class.getName(), SgCritical::new);
        }
    }
}
