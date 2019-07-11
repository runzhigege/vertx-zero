package io.vertx.up.micro.config;

import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.up.log.Annal;
import io.vertx.up.uca.marshal.Transformer;
import io.vertx.up.fn.Fn;

public class VertxStrada implements Transformer<VertxOptions> {

    private static final Annal LOGGER = Annal.get(VertxStrada.class);

    @Override
    public VertxOptions transform(final JsonObject input) {
        final JsonObject config = input.getJsonObject(NodeVisitor.YKEY_OPTIONS, null);
        return Fn.getSemi(null == config, LOGGER,
                VertxOptions::new,
                () -> new VertxOptions(config));
    }
}
