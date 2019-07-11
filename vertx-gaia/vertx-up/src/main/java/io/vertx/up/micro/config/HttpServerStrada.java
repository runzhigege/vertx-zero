package io.vertx.up.micro.config;

import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.up.log.Annal;
import io.vertx.zero.config.ServerVisitor;
import io.vertx.zero.marshal.Transformer;
import io.zero.epic.fn.Fn;

public class HttpServerStrada implements Transformer<HttpServerOptions> {

    private static final Annal LOGGER = Annal.get(HttpServerStrada.class);

    @Override
    public HttpServerOptions transform(final JsonObject input) {
        final JsonObject config = input.getJsonObject(ServerVisitor.YKEY_CONFIG, null);
        return Fn.getSemi(null == config, LOGGER,
                HttpServerOptions::new,
                () -> new HttpServerOptions(config));
    }
}
