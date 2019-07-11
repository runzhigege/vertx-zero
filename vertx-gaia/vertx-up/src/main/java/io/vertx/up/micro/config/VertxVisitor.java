package io.vertx.up.micro.config;

import io.vertx.core.ClusterOptions;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Ruler;
import io.vertx.up.eon.Info;
import io.vertx.up.log.Annal;
import io.vertx.up.util.Ut;
import io.vertx.up.exception.ZeroException;
import io.vertx.up.exception.demon.ClusterConflictException;
import io.vertx.up.fn.Fn;
import io.vertx.up.uca.marshal.Transformer;
import io.vertx.up.uca.marshal.node.Node;
import io.vertx.up.uca.marshal.node.ZeroVertx;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class VertxVisitor implements NodeVisitor {
    private static final Annal LOGGER = Annal.get(VertxVisitor.class);

    private static final String KEY = "vertx";

    private transient final Node<JsonObject> NODE
            = Ut.singleton(ZeroVertx.class);
    private transient final Transformer<VertxOptions>
            transformer = Ut.singleton(VertxStrada.class);
    private transient final Transformer<ClusterOptions>
            clusterTransformer = Ut.singleton(ClusterStrada.class);

    private transient ClusterOptions clusterOptions;

    @Override
    public ConcurrentMap<String, VertxOptions> visit(final String... keys)
            throws ZeroException {
        // 1. Must be the first line, fixed position.
        Ut.ensureEqualLength(getClass(), 0, (Object[]) keys);
        // 2. Visit the node for vertx
        final JsonObject data = NODE.read();
        // 3. Vertx node validation.
        final JsonObject vertxData = data.getJsonObject(KEY);
        LOGGER.info(Info.INF_B_VERIFY, KEY, getClass().getSimpleName(), vertxData);
        Fn.shuntZero(() -> Ruler.verify(KEY, vertxData), vertxData);
        // 4. Set cluster options
        clusterOptions = clusterTransformer.transform(data.getJsonObject(YKEY_CLUSTERED));
        // 5. Transfer Data
        return visit(vertxData.getJsonArray(YKEY_INSTANCE));
    }

    @Override
    public ClusterOptions getCluster() {
        return clusterOptions;
    }

    private ConcurrentMap<String, VertxOptions> visit(
            final JsonArray vertxData)
            throws ZeroException {
        final ConcurrentMap<String, VertxOptions> map =
                new ConcurrentHashMap<>();
        final boolean clustered = clusterOptions.isEnabled();
        Ut.etJArray(vertxData, JsonObject.class, (item, index) -> {
            // 1. Extract single
            final String name = item.getString(YKEY_NAME);
            // 2. Extract VertxOptions
            final VertxOptions options = transformer.transform(item);
            // 3. Check the configuration for cluster sync
            Fn.outZero(clustered != options.isClustered(), LOGGER,
                    ClusterConflictException.class,
                    getClass(), name, options.toString());
            // 4. Put the options into map
            map.put(name, options);
        });
        return map;
    }
}
