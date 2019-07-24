package io.vertx.up.uca.rs.router;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.ResponseContentTypeHandler;
import io.vertx.tp.plugin.session.SessionClient;
import io.vertx.tp.plugin.session.SessionInfix;
import io.vertx.up.secure.config.CorsConfig;
import io.vertx.up.uca.rs.Axis;
import io.vertx.up.util.Ut;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RouterAxis implements Axis<Router> {

    private static final CorsConfig CONFIG = CorsConfig.get();

    private static final int KB = 1024;
    private static final int MB = KB * 1024;

    private transient final Vertx vertx;

    public RouterAxis(final Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void mount(final Router router) {
        // 1. Cookie, Body
        router.route().handler(CookieHandler.create());
        // 2. Session
        /*
         * Session Global for Authorization, replace old mode with
         * SessionClient, this client will get SessionStore
         * by configuration information instead of create it directly.
         */
        final SessionClient client = SessionInfix.getOrCreate(vertx);
        router.route().handler(client.getHandler());
        /*
         * CSRF Handler Setting ( Disabled in default )
         */
        // router.route().handler(CSRFHandler.create("lang"));
        /*
         * Body, Content
         */
        router.route().handler(BodyHandler.create().setBodyLimit(32 * MB));
        router.route().handler(ResponseContentTypeHandler.create());
        // 3. Cors data here
        mountCors(router);
    }

    private void mountCors(final Router router) {
        router.route().handler(CorsHandler.create(CONFIG.getOrigin())
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
