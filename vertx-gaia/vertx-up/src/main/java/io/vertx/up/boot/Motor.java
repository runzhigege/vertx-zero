package io.vertx.up.boot;

import io.vertx.core.ClusterOptions;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EnvelopCodec;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.up.atom.Envelop;
import io.vertx.up.eon.Info;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.log.Annal;
import io.vertx.zero.exception.VertxCallbackException;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;
import io.zero.runtime.ZeroAnno;
import io.zero.runtime.ZeroGrid;
import io.zero.runtime.ZeroHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Start up tools shared in
 * Web Application & Rx Application
 */
public final class Motor {

    private static final Annal LOGGER = Annal.get(Motor.class);

    public static <T> void start(
            final Class<?> clazz,
            final Consumer<T> consumer,
            final Consumer<Consumer<T>> fnSingle,
            final BiConsumer<ClusterManager, Consumer<T>> fnCluster,
            final Annal logger) {
        if (null == consumer) {
            throw new VertxCallbackException(clazz);
        }
        // 1. Check if clustered mode
        final ClusterOptions cluster = ZeroGrid.getClusterOption();
        if (cluster.isEnabled()) {
            // 2.1. Clustered
            final ClusterManager manager = cluster.getManager();
            logger.info(Info.APP_CLUSTERD, manager.getClass().getName(),
                    manager.getNodeID(), manager.isActive());
            fnCluster.accept(manager, consumer);
        } else {
            // 2.2. Standalone
            fnSingle.accept(consumer);
        }
    }

    public static void each(
            final BiConsumer<String, VertxOptions> consumer) {
        final ConcurrentMap<String, VertxOptions> vertxOptions
                = ZeroGrid.getVertxOptions();
        vertxOptions.forEach(consumer);
    }

    public static void codec(final EventBus eventBus) {
        eventBus.registerDefaultCodec(Envelop.class,
                Ut.singleton(EnvelopCodec.class));
    }

    /**
     * Agent calculation
     *
     * @param defaultAgents
     * @param internals
     * @return
     */
    public static ConcurrentMap<ServerType, Class<?>> agents(
            final ServerType category,
            final Class<?>[] defaultAgents,
            final ConcurrentMap<ServerType, Class<?>> internals
    ) {
        final ConcurrentMap<ServerType, List<Class<?>>> agents =
                getMergedAgents(category, internals);
        final ConcurrentMap<ServerType, Boolean> defines =
                ZeroHelper.isAgentDefined(agents, defaultAgents);
        final ConcurrentMap<ServerType, Class<?>> ret =
                new ConcurrentHashMap<>();
        // Fix Boot
        // 1. If defined, use default
        Ut.itMap(agents, (type, list) -> {
            // 2. Defined -> You have defined
            Fn.safeSemi(defines.containsKey(type) && defines.get(type), LOGGER,
                    () -> {
                        // Use user-defined Agent instead.
                        final Class<?> found = Ut.elementFind(list,
                                (item) -> internals.get(type) != item);
                        if (null != found) {
                            ret.put(type, found);
                        }
                    }, () -> {
                        // Use internal defined ( system defaults )
                        final Class<?> found = Ut.elementFind(list,
                                (item) -> internals.get(type) == item);
                        if (null != found) {
                            LOGGER.info(Info.AGENT_DEFINED, found, type);
                            ret.put(type, found);
                        }
                    });
        });
        // 2.Filter
        return filterAgents(ret);
    }

    private static ConcurrentMap<ServerType, Class<?>> filterAgents(
            final ConcurrentMap<ServerType, Class<?>> agents) {
        // Check Rpc Enabled
        if (ZeroGrid.getRpcOptions().isEmpty()) {
            agents.remove(ServerType.IPC);
        } else {
            LOGGER.info(Info.RPC_ENABLED);
        }
        // Check Socket Enabled
        if (ZeroGrid.getSockOptions().isEmpty()) {
            agents.remove(ServerType.SOCK);
        } else {
            LOGGER.info(Info.SOCK_ENABLED);
        }
        return agents;
    }

    private static ConcurrentMap<ServerType, List<Class<?>>> getMergedAgents(
            final ServerType category,
            final ConcurrentMap<ServerType, Class<?>> internals
    ) {
        final ConcurrentMap<ServerType, List<Class<?>>> agents = ZeroAnno.getAgents();
        if (agents.isEmpty()) {
            // Inject ServerType by category input.
            agents.put(category, new ArrayList<>(internals.values()));
        }
        return agents;
    }
}
