package io.vertx.up.rs.router;

import io.vertx.ext.web.Route;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.rs.PlugRouter;
import io.vertx.up.rs.Sentry;
import io.vertx.up.rs.dispatch.ModeSplitter;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Info {

    String NULL_EVENT = "[ ZERO ] ( {0} ) The system found \"null\" event in the queue. ";

    String DY_DETECT = "[ ZERO ] ( {0} ) The system is detecting dynamic routing component...";

    String DY_SKIP = "[ ZERO ] ( {0} ) Skip dynamic routing because clazz is null or class {1} is not assignable from \"io.vertx.up.rs.PlugRouter\".";

    String DY_FOUND = "[ ZERO ] ( {0} ) Zero system detect class {1} ( io.vertx.up.rs.PlugRouter ) with config {2}.";
}

interface Pool {
    ConcurrentMap<String, ModeSplitter> THREADS
            = new ConcurrentHashMap<>();
    ConcurrentMap<String, Sentry<RoutingContext>> VERIFIERS
            = new ConcurrentHashMap<>();

    ConcurrentMap<String, Hub<Route>> URIHUBS
            = new ConcurrentHashMap<>();

    ConcurrentMap<String, Hub<Route>> MEDIAHUBS
            = new ConcurrentHashMap<>();

    ConcurrentMap<String, Set<Cliff>> WALL_MAP =
            new ConcurrentHashMap<>();

    ConcurrentMap<String, PlugRouter> PLUGS = new ConcurrentHashMap<>();
}
