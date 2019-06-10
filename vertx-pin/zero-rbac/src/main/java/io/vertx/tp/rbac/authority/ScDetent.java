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
 *
 * Be careful:
 * If there existing constructor data parameter such as JsonObject or List<ProfileGroup>, it means
 * each time this object will stored single data here, in this kind of situation, we could not use
 * Pool.DETENT_POOL, if you used Pool.DETENT_POOL, the system will ignore
 * input parameter and used cached instead, it means that different user authorization may
 * shared the first time input parameters. It's wrong.
 *
 * There are some points:
 * 1. For tool object ( No constructor parameters ), we could cached ScDetent.
 * 2. For non tool object ( Input constructor parameters ), we mustn't cached ScDetent.
 */
public interface ScDetent {

    static ScDetent user(final JsonObject input) {
        return new ScDetentRole(input);
    }

    static ScDetent group(final JsonObject input) {
        return new ScDetentGroup(input);
    }

    static ScDetent parent(final JsonObject input,
                           final List<ProfileGroup> profiles) {
        return new ScDetentParent(input, profiles);
    }

    static ScDetent children(final JsonObject input,
                             final List<ProfileGroup> profiles) {
        return new ScDetentChild(input, profiles);
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

            static ScDetent critical(final List<ProfileGroup> original) {
                return new GpCritical(original);
            }

            static ScDetent overlook(final List<ProfileGroup> original) {
                return new GpOverlook(original);
            }
        }

        /*
         * Group : Child ( Exclude Current )
         */
        interface Child {
            static ScDetent horizon() {
                return Fn.pool(Pool.DETENT_POOL, GcHorizon.class.getName(), GcHorizon::new);
            }

            static ScDetent critical(final List<ProfileGroup> original) {
                return new GcCritical(original);
            }

            static ScDetent overlook(final List<ProfileGroup> original) {
                return new GcOverlook(original);
            }
        }
    }
}
