package io.vertx.up.uca.rs.router;

import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.ResponseContentTypeHandler;
import io.vertx.up.eon.Orders;
import io.vertx.up.uca.rs.Axis;

import javax.ws.rs.core.HttpHeaders;
import java.util.HashSet;

public class RouterAxis implements Axis<Router> {

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
        // TODO: 2. Cors Template Solution
        router.route()
                .order(Orders.CORS)
                .handler(CorsHandler.create("*")
                        .allowCredentials(false)
                        .allowedHeaders(new HashSet<String>() {
                            {
                                this.add(HttpHeaders.AUTHORIZATION);
                                this.add(HttpHeaders.ACCEPT);
                                this.add(HttpHeaders.CONTENT_LENGTH);
                                this.add(HttpHeaders.CONTENT_TYPE);
                                this.add(HttpHeaders.CONTENT_DISPOSITION);
                                this.add(HttpHeaders.CONTENT_ENCODING);
                            }
                        })
                        .allowedMethods(new HashSet<HttpMethod>() {
                            {
                                this.add(HttpMethod.DELETE);
                                this.add(HttpMethod.GET);
                                this.add(HttpMethod.POST);
                                this.add(HttpMethod.PUT);
                                this.add(HttpMethod.OPTIONS);
                            }
                        }));

    }
}
