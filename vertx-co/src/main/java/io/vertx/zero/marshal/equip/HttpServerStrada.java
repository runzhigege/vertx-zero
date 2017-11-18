package io.vertx.zero.marshal.equip;

import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.func.HBool;
import io.vertx.zero.marshal.Transformer;

public class HttpServerStrada implements Transformer<HttpServerOptions> {

    @Override
    public HttpServerOptions transform(final JsonObject input) {
        final JsonObject config = input.getJsonObject(ServerVisitor.YKEY_CONFIG, null);
        return HBool.exec(null == config,
                HttpServerOptions::new,
                () -> new HttpServerOptions(config));
    }
}