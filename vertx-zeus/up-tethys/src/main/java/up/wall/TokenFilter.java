package up.wall;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.up.annotations.Ordered;
import io.vertx.up.web.filter.HttpFilter;

import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
@Ordered(1)
public class TokenFilter extends HttpFilter {

    @Override
    public void doFilter(final HttpServerRequest request,
                         final HttpServerResponse response) {
        this.put("key", "Hello Lang");
    }
}
