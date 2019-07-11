package io.vertx.up.web.limit;

import io.vertx.tp.error.RpcPreparingException;
import io.vertx.tp.etcd.center.EtcdData;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.fn.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.ZeroHttpAgent;
import io.vertx.up.micro.ZeroRpcAgent;
import io.vertx.up.micro.ZeroSockAgent;
import io.vertx.up.runtime.ZeroMotor;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * This factor could begin following:
 * 1. Http Server,
 * 2. Rpc Server.
 */
public class HttpFactor implements Factor {

    private static final Annal LOGGER = Annal.get(HttpFactor.class);

    private static final Class<?>[] DEFAULT_AGENTS = new Class<?>[]{
            ZeroHttpAgent.class, ZeroRpcAgent.class, ZeroSockAgent.class
    };

    private static final ConcurrentMap<ServerType, Class<?>> INTERNALS
            = new ConcurrentHashMap<ServerType, Class<?>>() {
        {
            put(ServerType.HTTP, ZeroHttpAgent.class);
            put(ServerType.IPC, ZeroRpcAgent.class);
            put(ServerType.SOCK, ZeroSockAgent.class);
        }
    };

    @Override
    public ConcurrentMap<ServerType, Class<?>> agents() {
        /* 1.Find Agent for deploy **/
        final ConcurrentMap<ServerType, Class<?>> agents
                = ZeroMotor.agents(ServerType.HTTP, DEFAULT_AGENTS, INTERNALS);
        if (agents.containsKey(ServerType.IPC)) {
            // 2. Check etcd server status, IPC Only
            Fn.outUp(!EtcdData.enabled(),
                    LOGGER, RpcPreparingException.class, getClass());
        }
        // 3. Filter invalid agents
        final Set<ServerType> scanned = new HashSet<>(agents.keySet());
        final Set<ServerType> keeped = INTERNALS.keySet();
        scanned.removeAll(keeped);
        scanned.forEach(agents::remove);
        return agents;
    }
}
