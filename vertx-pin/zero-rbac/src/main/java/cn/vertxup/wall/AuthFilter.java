package cn.vertxup.wall;

import io.vertx.core.VertxException;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.up.web.filter.HttpFilter;
import io.zero.epic.Ut;

import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {

    @Override
    public void doFilter(final HttpServerRequest request,
                         final HttpServerResponse response)
            throws VertxException {
        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Ut.notNil(token) && token.contains(" ")) {
            final String tokenString = token.substring(token.lastIndexOf(' '), token.length());
            this.put("token", tokenString);
        }
    }
}
