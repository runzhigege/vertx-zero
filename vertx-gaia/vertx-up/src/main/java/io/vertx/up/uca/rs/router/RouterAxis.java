package io.vertx.up.uca.rs.router;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.ResponseContentTypeHandler;
import io.vertx.up.eon.Orders;
import io.vertx.up.secure.config.CorsConfig;
import io.vertx.up.uca.rs.Axis;
import io.vertx.up.util.Ut;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RouterAxis implements Axis<Router> {

    private static final CorsConfig CONFIG = CorsConfig.get();

    @Override
    public void mount(final Router router) {
        // 1. Cookie, Body
        router.route()
                .order(Orders.COOKIE)
                .handler(CookieHandler.create());
        router.route()
                .order(Orders.BODY)
                .handler(BodyHandler.create());
        router.route()
                .order(Orders.CONTENT)
                .handler(ResponseContentTypeHandler.create());
        // 2. Cors data here
        mountCors(router);
    }

    private void mountCors(final Router router) {
        router.route().order(Orders.CORS)
                .handler(CorsHandler.create(CONFIG.getOrigin())
                        .allowCredentials(CONFIG.getCredentials())
                        .allowedHeaders(getAllowedHeaders(CONFIG.getHeaders()))
                        .allowedMethods(getAllowedMethods(CONFIG.getMethods())));
    }

    private Set<String> getAllowedHeaders(final JsonArray array) {
        final Set<String> headerSet = new HashSet<>();
        array.stream()
                .filter(Objects::nonNull)
                .map(item -> (String) item)
                .forEach(headerSet::add);
        return headerSet;
    }

    private Set<HttpMethod> getAllowedMethods(final JsonArray array) {
        final Set<HttpMethod> methodSet = new HashSet<>();
        array.stream()
                .filter(Objects::nonNull)
                .map(item -> (String) item)
                .map(item -> Ut.toEnum(() -> item, HttpMethod.class, HttpMethod.GET))
                .forEach(methodSet::add);
        return methodSet;
    }
}
