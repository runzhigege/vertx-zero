package io.vertx.up.web;

import io.vertx.up.atom.agent.Event;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.atom.worker.Receipt;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.epic.fn.Fn;
import io.vertx.up.epic.mirror.Instance;
import io.vertx.up.epic.mirror.Pack;
import io.vertx.up.log.Annal;
import io.vertx.up.web.origin.*;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Transfer Class<?> set to difference mapping.
 */
public class ZeroAnno {

    private static final Annal LOGGER = Annal.get(ZeroAnno.class);

    private final static Set<Class<?>>
            ENDPOINTS = new HashSet<>();
    private final static ConcurrentMap<Class<?>, ConcurrentMap<String, Class<?>>>
            PLUGINS = new ConcurrentHashMap<>();
    private final static Set<Receipt>
            RECEIPTS = new HashSet<>();
    private final static Set<Event>
            EVENTS = new HashSet<>();
    private final static ConcurrentMap<String, Set<Event>>
            FILTERS = new ConcurrentHashMap<>();
    private final static ConcurrentMap<ServerType, List<Class<?>>>
            AGENTS = new ConcurrentHashMap<>();
    private final static Set<Class<?>>
            WORKERS = new HashSet<>();
    private final static Set<Cliff>
            WALLS = new TreeSet<>();
    private final static ConcurrentMap<String, Method>
            IPCS = new ConcurrentHashMap<>();
    private final static Set<Class<?>>
            POINTER = new HashSet<>();
    private final static Set<Class<?>>
            TPS = new HashSet<>();

    static {
        /** 1.Scan the packages **/
        final Set<Class<?>> clazzes = Pack.getClasses(null);
        /** EndPoint **/
        Inquirer<Set<Class<?>>> inquirer =
                Instance.singleton(EndPointInquirer.class);
        ENDPOINTS.addAll(inquirer.scan(clazzes));

        /** EndPoint -> Event **/
        Fn.safeSemi(!ENDPOINTS.isEmpty(),
                LOGGER,
                () -> {
                    final Inquirer<Set<Event>> event =
                            Instance.singleton(EventInquirer.class);
                    EVENTS.addAll(event.scan(ENDPOINTS));
                });

        /** Wall -> Authenticate, Authorize **/
        final Inquirer<Set<Cliff>> walls =
                Instance.singleton(WallInquirer.class);
        WALLS.addAll(walls.scan(clazzes));

        /** Filter -> WebFilter **/
        final Inquirer<ConcurrentMap<String, Set<Event>>> filters =
                Instance.singleton(FilterInquirer.class);
        FILTERS.putAll(filters.scan(clazzes));

        /** Queue **/
        inquirer = Instance.singleton(QueueInquirer.class);
        final Set<Class<?>> queues = inquirer.scan(clazzes);

        /** Queue -> Receipt **/
        Fn.safeSemi(!queues.isEmpty(),
                LOGGER,
                () -> {
                    final Inquirer<Set<Receipt>> receipt =
                            Instance.singleton(ReceiptInquirer.class);
                    RECEIPTS.addAll(receipt.scan(queues));
                });

        /** Ipc Only **/
        Fn.safeSemi(IPCS.isEmpty(),
                LOGGER,
                () -> {
                    final Inquirer<ConcurrentMap<String, Method>> ipc =
                            Instance.singleton(IpcInquirer.class);
                    IPCS.putAll(ipc.scan(clazzes));
                });

        /** Agent **/
        final Inquirer<ConcurrentMap<ServerType, List<Class<?>>>> agent =
                Instance.singleton(AgentInquirer.class);
        AGENTS.putAll(agent.scan(clazzes));

        /** JSR330 Fix **/
        final Inquirer<Set<Class<?>>> pointer =
                Instance.singleton(PointerInquirer.class);
        POINTER.addAll(pointer.scan(clazzes));

        /** Tp Clients **/
        final Inquirer<Set<Class<?>>> tps =
                Instance.singleton(PluginInquirer.class);
        TPS.addAll(tps.scan(clazzes));

        /** Worker **/
        final Inquirer<Set<Class<?>>> worker =
                Instance.singleton(WorkerInquirer.class);
        WORKERS.addAll(worker.scan(clazzes));

        /** Walls **/

        /** Injections **/
        final Inquirer<ConcurrentMap<Class<?>, ConcurrentMap<String, Class<?>>>> afflux =
                Instance.singleton(AffluxInquirer.class);
        PLUGINS.putAll(afflux.scan(clazzes));
    }

    /**
     * Get all plugins
     *
     * @return
     */
    public static ConcurrentMap<Class<?>, ConcurrentMap<String, Class<?>>>
    getPlugins() {
        return PLUGINS;
    }

    /**
     * Get all agents.
     *
     * @return
     */
    public static ConcurrentMap<ServerType, List<Class<?>>>
    getAgents() {
        return AGENTS;
    }

    /**
     * Injects
     *
     * @return
     */
    public static Set<Class<?>>
    getInjects() {
        return POINTER;
    }

    /**
     * Tp Clients
     *
     * @return
     */
    public static Set<Class<?>>
    getTps() {
        return TPS;
    }

    /**
     * Get all workers
     *
     * @return
     */
    public static Set<Class<?>>
    getWorkers() {
        return WORKERS;
    }

    /**
     * Get all receipts
     *
     * @return
     */
    public static Set<Receipt>
    getReceipts() {
        return RECEIPTS;
    }

    /**
     * Get all endpoints
     *
     * @return
     */
    public static Set<Class<?>>
    getEndpoints() {
        return ENDPOINTS;
    }

    public static ConcurrentMap<String, Method>
    getIpcs() {
        return IPCS;
    }

    /**
     * Get all envents
     *
     * @return
     */
    public static Set<Event>
    getEvents() {
        return EVENTS;
    }

    /**
     * Get all filters
     *
     * @return
     */
    public static ConcurrentMap<String, Set<Event>>
    getFilters() {
        return FILTERS;
    }

    /**
     * Get all guards
     *
     * @return
     */
    public static Set<Cliff>
    getWalls() {
        return WALLS;
    }
}
