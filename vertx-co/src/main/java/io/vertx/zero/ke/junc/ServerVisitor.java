package io.vertx.zero.ke.junc;

import com.vie.hors.ZeroException;
import com.vie.util.Ensurer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.zero.ke.Visitor;

/**
 * @author lang
 */
public class ServerVisitor implements Visitor<HttpServerOptions> {
    /**
     * @return
     * @throws ZeroException
     */
    @Override
    public HttpServerOptions visit(final String... key) throws ZeroException {
        // 1. Must be the first line, fixed position.
        Ensurer.eqLength(getClass(), 0, key);
        // 2. Visit the node for server, http
        return null;
    }
}
