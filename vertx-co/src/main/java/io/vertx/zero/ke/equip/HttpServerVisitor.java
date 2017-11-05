package io.vertx.zero.ke.equip;

import com.vie.hors.ZeroException;
import com.vie.util.Ensurer;
import com.vie.util.Instance;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.ke.config.JObjectBase;
import io.vertx.zero.ke.config.ZeroServer;

import java.util.concurrent.ConcurrentMap;

/**
 * @author lang
 */
public class HttpServerVisitor implements ServerVisitor<HttpServerOptions> {

    private transient final JObjectBase NODE
            = Instance.singleton(ZeroServer.class);

    /**
     * @return
     * @throws ZeroException
     */
    @Override
    public ConcurrentMap<Integer, HttpServerOptions> visit(final String... key)
            throws ZeroException {
        // 1. Must be the first line, fixed position.
        Ensurer.eqLength(getClass(), 0, key);
        // 2. Visit the node for server, http
        final JsonObject data = this.NODE.read();
        System.out.println(data);
        return null;
    }
}
