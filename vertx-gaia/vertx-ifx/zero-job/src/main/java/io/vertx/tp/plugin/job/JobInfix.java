package io.vertx.tp.plugin.job;

import io.vertx.core.Vertx;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.plugin.Infix;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Plugin
@SuppressWarnings("all")
public class JobInfix implements Infix {
    private static final String NAME = "ZERO_JOB_POOL";

    private static final ConcurrentMap<String, JobClient> CLIENTS
            = new ConcurrentHashMap<>();

    private static void initInternal(final Vertx vertx,
                                     final String name) {

    }

    private static void init(final Vertx vertx) {
        initInternal(vertx, NAME);
    }

    public static JobClient getClient() {
        return CLIENTS.get(NAME);
    }

    @Override
    public JobClient get() {
        return getClient();
    }
}
