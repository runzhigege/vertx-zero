package io.vertx.tp.jet.uca.tunnel;

import io.vertx.core.Future;
import io.vertx.tp.optic.jet.JtComponent;
import io.vertx.up.aiki.Ux;
import io.vertx.up.commune.ActIn;
import io.vertx.zero.atom.Database;
import io.zero.epic.Ut;

/*
 * Default Adaptor channel for database accessing
 */
public class AdaptorChannel extends AbstractChannel {
    /*
     * Adaptor is pure to access dynamic database here, the specification is as:
     * Step 1:
     * - The component defined Database reference, it could be initialized
     */
    @Override
    public Future<Boolean> initAsync(final JtComponent component, final ActIn request) {
        return Ux.toFuture(this.getCommercial())
                /*
                 * Database initialized
                 */
                .compose(Anagogic::databaseAsync)
                .compose(database -> Ut.contractAsync(component, Database.class, database));
    }
}
