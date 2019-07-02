package io.vertx.tp.jet.uca.tunnel;

import io.vertx.core.Future;
import io.vertx.tp.optic.jet.JtComponent;
import io.vertx.up.commune.ActIn;

public class DirectorChannel extends AbstractChannel {
    @Override
    public Future<Boolean> initAsync(final JtComponent component, final ActIn request) {
        // TODO:
        return Future.succeededFuture(Boolean.TRUE);
    }
}
