package io.vertx.up.micro.center;

import io.reactivex.Observable;
import io.vertx.core.ServidorOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.etcd.center.EtcdData;
import io.vertx.up.eon.em.Etat;
import io.vertx.up.eon.em.EtcdPath;
import io.vertx.up.epic.Ut;
import io.vertx.up.epic.fn.Fn;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Values;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;

/**
 * Zero registry center to write/read data with Etcd for zero micro service
 * This will be called by ZeroRpcAgent class to write service meta.
 * 1. Status RUNNING/STOPPED/FAILED
 * /zero/ipc/status/{name}/{host}:{port}
 * 2. Services for discovery
 * /zero/ipc/services/{name}/{host}/{port}
 */
public class ZeroRegistry {

    private static final String PATH_STATUS = "/zero/{0}/{1}/services/{2}:{3}:{4}";

    private static final String PATH_CATALOG = "/zero/{0}/{1}/services";

    private static final String ROUTE_TREE = "/zero/{0}/{1}/routes/{2}";

    private static final ConcurrentMap<String, ZeroRegistry>
            REGISTRY_MAP = new ConcurrentHashMap<>();

    private final transient Annal logger;
    private final transient EtcdData etcd;

    private ZeroRegistry(final Class<?> useCls) {
        this.etcd = EtcdData.create(useCls);
        this.logger = Annal.get(useCls);
    }

    public static ZeroRegistry create(final Class<?> useCls) {
        return Fn.poolThread(REGISTRY_MAP, () -> new ZeroRegistry(useCls));
    }

    /**
     * Get current etcd configuration information that initialized
     * in zero system.
     *
     * @return
     */
    public JsonArray getConfig() {
        return this.etcd.getConfig();
    }

    public Set<JsonObject> getData(
            final EtcdPath etcdPath,
            final String key,
            final BiFunction<String, JsonArray, Set<JsonObject>> convert
    ) {
        final String path = MessageFormat.format(ROUTE_TREE, this.etcd.getApplication(),
                etcdPath.toString().toLowerCase(), key);
        this.logger.debug(Info.ETCD_READ, path);
        final String node = this.etcd.readData(path);
        final Set<JsonObject> sets = new HashSet<>();
        if (!Ut.isNil(node)) {
            final JsonArray value = new JsonArray(node);
            sets.addAll(convert.apply(key, value));
        }
        return sets;
    }

    public Set<String> getServices(
            final EtcdPath etcdPath) {
        final String path = MessageFormat.format(PATH_CATALOG, this.etcd.getApplication(),
                etcdPath.toString().toLowerCase());
        this.logger.debug(Info.ETCD_READ, path);
        final ConcurrentMap<String, String> nodes = this.etcd.readDir(path, true);
        final Set<String> sets = new HashSet<>();

        Observable.fromIterable(nodes.entrySet())
                .filter(Objects::nonNull)
                .filter(item -> Objects.nonNull(item.getKey()) && Objects.nonNull(item.getValue()))
                .filter(item -> Etat.RUNNING == Ut.toEnum(Etat.class, item.getValue()))
                .map(Map.Entry::getKey)
                .subscribe(sets::add);
        return sets;
    }

    public void erasingStatus(final String name,
                              final String host,
                              final Integer port,
                              final EtcdPath path) {
        final String target = MessageFormat.format(PATH_STATUS, this.etcd.getApplication(),
                path.toString().toLowerCase(), name,
                host, String.valueOf(port));
        this.logger.info(Info.ETCD_CLEAN, name, path);
        this.etcd.write(target, Etat.STOPPED, Values.ZERO);
    }

    public void registryHttp(final String service,
                             final HttpServerOptions options, final Etat etat) {
        final String path = MessageFormat.format(PATH_STATUS, this.etcd.getApplication(),
                EtcdPath.ENDPOINT.toString().toLowerCase(), service,
                Ut.netIPv4(), String.valueOf(options.getPort()));
        this.logger.info(Info.ETCD_STATUS, service, etat, path);
        this.etcd.write(path, etat, Values.ZERO);
    }

    public void registryRpc(final ServidorOptions options, final Etat etat) {
        final String path = MessageFormat.format(PATH_STATUS, this.etcd.getApplication(),
                EtcdPath.IPC.toString().toLowerCase(), options.getName(),
                Ut.netIPv4(), String.valueOf(options.getPort()));
        this.logger.info(Info.ETCD_STATUS, options.getName(), etat, path);
        this.etcd.write(path, etat, Values.ZERO);
    }

    public void registryIpcs(final ServidorOptions options, final Set<String> ipcs) {
        final String path = MessageFormat.format(ROUTE_TREE, this.etcd.getApplication(),
                EtcdPath.IPC.toString().toLowerCase(),
                MessageFormat.format("{0}:{1}:{2}", options.getName(),
                        Ut.netIPv4(), String.valueOf(options.getPort())));
        final String host = Ut.netIPv4();
        final String endpoint = MessageFormat.format("grpc://{0}:{1}",
                host,
                String.valueOf(options.getPort()));
        // Screen Information
        final StringBuilder builder = new StringBuilder();
        for (final String ipc : ipcs) {
            builder.append("\n\t[ Up Rpc √ ] \t").append(ipc);
        }
        this.logger.info(Info.ETCD_IPCS, this.etcd.getApplication(),
                path, options.getName(), endpoint, builder.toString());
        // Build Data
        final JsonArray routeData = new JsonArray();
        Observable.fromIterable(ipcs)
                .subscribe(routeData::add);
        this.etcd.write(path, routeData, Values.ZERO);
    }

    public void registryRoute(final String name,
                              final HttpServerOptions options, final Set<String> routes) {
        final String path = MessageFormat.format(ROUTE_TREE, this.etcd.getApplication(),
                EtcdPath.ENDPOINT.toString().toLowerCase(),
                MessageFormat.format("{0}:{1}:{2}", name,
                        Ut.netIPv4(), String.valueOf(options.getPort())));
        final String host = Ut.netIPv4();
        final String endpoint = MessageFormat.format("http://{0}:{1}",
                host,
                String.valueOf(options.getPort()));
        // Screen Information
        final StringBuilder builder = new StringBuilder();
        for (final String route : routes) {
            builder.append("\n\t[ Up Micro ] \t").append(route);
        }
        this.logger.info(Info.ETCD_ROUTE, this.etcd.getApplication(),
                path, name, endpoint, builder.toString());
        // Build Data
        final JsonArray routeData = new JsonArray();
        Observable.fromIterable(routes)
                .subscribe(routeData::add);
        this.etcd.write(path, routeData, Values.ZERO);
    }
}
