package io.vertx.tp.jet.uca.tunnel;

import io.vertx.core.Future;
import io.vertx.tp.optic.jet.JtComponent;
import io.vertx.up.aiki.Ux;
import io.vertx.up.commune.ActRequest;

public class ConnectorChannel extends AbstractChannel {
    /*
     * Adaptor is pure to access dynamic database here, the specification is as:
     * Step 1:
     * - The component defined Database reference, it could be initialized
     * Step 2:
     * - The component defined Integration reference, it could be initialized
     */
    @Override
    public Future<Boolean> initAsync(final JtComponent component, final ActRequest request) {
        return Ux.toFuture(this.getApi())
                /*
                 * Database initialized, Mount database to `JtComponent`
                 */
                .compose(Anagogic::databaseAsync)
                .compose(database -> this.onDatabase(component, database))
                /*
                 * Integration inited, mount to `JtComponent`
                 */
                .compose(dbed -> Ux.toFuture(this.getApi().integration()))
                .compose(integration -> this.onIntegration(component, integration));
    }
}
