package io.vertx.up.plugin.session;

import io.vertx.core.Vertx;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.eon.Plugins;
import io.vertx.up.plugin.Infix;
import io.vertx.zero.fn.Fn;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Plugin
@SuppressWarnings("unchecked")
public class SessionInfix implements Infix {

    private static final String NAME = "ZERO_SESSION_POOL";
    private static final ConcurrentMap<String, SessionClient> CLIENTS
            = new ConcurrentHashMap<>();

    private static void initInternal(final Vertx vertx,
                                     final String name) {
        Fn.pool(CLIENTS, name,
                () -> Infix.initTp(Plugins.Infix.SESSION,
                        (config) -> SessionClient.createShared(vertx, config),
                        SessionInfix.class));
    }

    public static void init(final Vertx vertx) {
        initInternal(vertx, NAME);
    }

    public static SessionClient getClient() {
        return CLIENTS.get(NAME);
    }

    public static SessionClient getOrCreate(final Vertx vertx) {
        return Fn.pool(CLIENTS, NAME, () -> SessionClient.createShared(vertx));
    }

    @Override
    public SessionClient get() {
        return getClient();
    }
}
