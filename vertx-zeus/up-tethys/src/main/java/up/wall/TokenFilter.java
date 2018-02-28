package up.wall;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.up.annotations.Ordered;
import io.vertx.up.web.filter.RestFilter;

import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
@Ordered(1)
public class TokenFilter extends RestFilter {

    @Override
    public void doFilter(final HttpServerRequest request,
                         final HttpServerResponse response) {

    }
}
