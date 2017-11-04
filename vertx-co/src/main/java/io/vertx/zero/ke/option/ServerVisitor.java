package io.vertx.zero.ke.option;

import com.vie.hors.ZeroException;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.zero.ke.Visitor;
import io.vertx.zero.util.Ensurer;

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
        // Must be the first line, fixed position.
        Ensurer.argLength(getClass(), 0, key);
        return null;
    }
}
