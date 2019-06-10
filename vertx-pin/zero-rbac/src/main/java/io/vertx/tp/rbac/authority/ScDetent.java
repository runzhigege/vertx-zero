package io.vertx.tp.rbac.authority;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.authority.child.GcCritical;
import io.vertx.tp.rbac.authority.child.GcHorizon;
import io.vertx.tp.rbac.authority.child.GcOverlook;
import io.vertx.tp.rbac.authority.detent.ScDetentChild;
import io.vertx.tp.rbac.authority.detent.ScDetentGroup;
import io.vertx.tp.rbac.authority.detent.ScDetentParent;
import io.vertx.tp.rbac.authority.detent.ScDetentRole;
import io.vertx.tp.rbac.authority.direct.GdCritical;
import io.vertx.tp.rbac.authority.direct.GdHorizon;
import io.vertx.tp.rbac.authority.direct.GdOverlook;
import io.vertx.tp.rbac.authority.parent.GpCritical;
import io.vertx.tp.rbac.authority.parent.GpHorizon;
import io.vertx.tp.rbac.authority.parent.GpOverlook;
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

    static ScDetent parent(final JsonObject input) {
        return Fn.pool(Pool.DETENT_POOL, ScDetentParent.class.getName(),
                () -> new ScDetentParent(input));
    }

    static ScDetent children(final JsonObject input) {
        return Fn.pool(Pool.DETENT_POOL, ScDetentChild.class.getName(),
                () -> new ScDetentChild(input));
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
            return Fn.pool(Pool.DETENT_POOL, GdHorizon.class.getName(), GdHorizon::new);
        }

        static ScDetent critical() {
            return Fn.pool(Pool.DETENT_POOL, GdCritical.class.getName(), GdCritical::new);
        }

        static ScDetent overlook() {
            return Fn.pool(Pool.DETENT_POOL, GdOverlook.class.getName(), GdOverlook::new);
        }

        /*
         * Group : Parent ( Exclude Current )
         */
        interface Parent {

            static ScDetent horizon() {
                return Fn.pool(Pool.DETENT_POOL, GpHorizon.class.getName(), GpHorizon::new);
            }

            static ScDetent critical() {
                return Fn.pool(Pool.DETENT_POOL, GpCritical.class.getName(), GpCritical::new);
            }

            static ScDetent overlook() {
                return Fn.pool(Pool.DETENT_POOL, GpOverlook.class.getName(), GpOverlook::new);
            }
        }

        /*
         * Group : Child ( Exclude Current )
         */
        interface Child {
            static ScDetent horizon() {
                return Fn.pool(Pool.DETENT_POOL, GcHorizon.class.getName(), GcHorizon::new);
            }

            static ScDetent critical() {
                return Fn.pool(Pool.DETENT_POOL, GcCritical.class.getName(), GcCritical::new);
            }

            static ScDetent overlook() {
                return Fn.pool(Pool.DETENT_POOL, GcOverlook.class.getName(), GcOverlook::new);
            }
        }
    }
}
