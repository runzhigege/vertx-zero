package io.vertx.tp.jet.uca.tunnel;

import io.vertx.core.Future;
import io.vertx.tp.optic.jet.JtComponent;
import io.vertx.up.aiki.Ux;
import io.vertx.up.commune.ActRequest;

public class ActorChannel extends AbstractChannel {
    /*
     * Adaptor is pure to access dynamic database here, the specification is as:
     * Step 1:
     * - The component defined Database reference, it could be initialized
     */
    @Override
    public Future<Boolean> initAsync(final JtComponent component, final ActRequest request) {
        return Ux.toFuture(this.getApi())
                /*
                 * Database initialized
                 */
                .compose(Anagogic::databaseAsync)
                /*
                 * Mount database to `JtComponent`
                 */
                .compose(database -> this.onDatabase(component, database));
    }
}
