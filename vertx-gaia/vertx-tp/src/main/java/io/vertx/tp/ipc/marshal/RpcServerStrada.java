package io.vertx.tp.ipc.marshal;

import io.vertx.core.ServidorOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.up.epic.fn.Fn;
import io.vertx.up.log.Annal;
import io.vertx.zero.marshal.Transformer;

public class RpcServerStrada implements Transformer<ServidorOptions> {

    private static final Annal LOGGER = Annal.get(RpcServerStrada.class);

    @Override
    public ServidorOptions transform(final JsonObject input) {
        return Fn.getSemi(null == input, LOGGER,
                ServidorOptions::new,
                () -> new ServidorOptions(input));
    }
}
