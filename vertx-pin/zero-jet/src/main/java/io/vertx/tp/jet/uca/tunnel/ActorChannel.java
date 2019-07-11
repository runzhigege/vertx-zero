package io.vertx.tp.jet.uca.tunnel;

import io.vertx.core.Future;
import io.vertx.tp.optic.jet.JtComponent;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.commune.ActIn;
import io.vertx.zero.atom.Database;
import io.vertx.zero.atom.Integration;
import io.vertx.zero.epic.Ut;

public class ActorChannel extends AbstractChannel {
    /*
     * Adaptor is pure to access dynamic database here, the specification is as:
     * Step 1:
     * - The component defined Database reference, it could be initialized
     * Step 2:
     * - The component defined Integration reference, it could be initialized
     * Step 3:
     * - The component defined Mission reference, it could be initialized
     */
    @Override
    public Future<Boolean> initAsync(final JtComponent component, final ActIn request) {
        return Ux.toFuture(this.getCommercial())
                /*
                 * Database initialized, Mount database to `JtComponent`
                 */
                .compose(Anagogic::databaseAsync)
                .compose(database -> Ut.contractAsync(component, Database.class, database))
                /*
                 * Integration inited, mount to `JtComponent`
                 */
                .compose(dbed -> Ux.toFuture(this.getCommercial().integration()))
                .compose(integration -> Ut.contractAsync(component, Integration.class, integration))
                /*
                 * Mission inited, mount to `JtComponent`
                 */
                .compose(dbed -> Ux.toFuture(this.getMission()))
                .compose(mission -> Ut.contractAsync(component, Mission.class, mission));
    }
}
