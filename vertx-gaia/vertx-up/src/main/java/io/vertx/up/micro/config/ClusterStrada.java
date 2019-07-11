package io.vertx.up.micro.config;

import io.vertx.core.ClusterOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.up.log.Annal;
import io.vertx.up.uca.marshal.Transformer;
import io.vertx.up.fn.Fn;

public class ClusterStrada implements Transformer<ClusterOptions> {

    private static final Annal LOGGER = Annal.get(ClusterStrada.class);

    @Override
    public ClusterOptions transform(final JsonObject config) {
        return Fn.getSemi(null == config, LOGGER,
                ClusterOptions::new,
                () -> new ClusterOptions(config));
    }
}
