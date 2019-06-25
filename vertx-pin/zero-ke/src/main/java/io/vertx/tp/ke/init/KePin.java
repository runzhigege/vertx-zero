package io.vertx.tp.ke.init;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Strings;
import io.zero.epic.Ut;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * Cross Infix management here as bus
 * It's defined interface / abstract only and management uniform
 */
public class KePin {

    private static final Annal LOGGER = Annal.get(KePin.class);

    private static final String TUNNEL_CONFIG = "plugin/channel.json";

    private static final ConcurrentMap<Class<?>, Class<?>> TUNNELS =
            new ConcurrentHashMap<>();

    /*
     * Static initialization for loading configuration
     */
    static {
        final JsonObject config = Ut.ioJObject(TUNNEL_CONFIG);
        if (!Ut.isNil(config)) {
            /*
             * Load information here.
             */
            final String pkgName = config.getString("package");
            final JsonArray channels = config.getJsonArray("channels");
            channels.stream().filter(Objects::nonNull)
                    .map(item -> (JsonObject) item)
                    .forEach(each -> init(
                            refine(pkgName, each.getString("key")),     // Interface
                            refine(pkgName, each.getString("value"))    // Implementation
                    ));
        }
    }

    private static String refine(final String pkgName, final String clsName) {
        if (clsName.contains(Strings.DOT)) {
            return clsName;
        } else {
            return pkgName + Strings.DOT + clsName;
        }
    }

    private static void init(final String key, final String value) {
        final Class<?> interfaceCls = Ut.clazz(key);
        final Class<?> implCls = Ut.clazz(value);
        if (Objects.nonNull(interfaceCls) && Objects.nonNull(implCls)) {
            /* Check and throw error */
            if (Ut.isImplement(implCls, interfaceCls)) {
                /* Valid channel building */
                LOGGER.info("[ πυρήνας ] Channel connect to: {0} -> {1}",
                        interfaceCls.getName(), implCls.getName());
                TUNNELS.put(interfaceCls, implCls);
            }
        }
    }

    public static Class<?> get(final Class<?> interfaceCls) {
        return TUNNELS.getOrDefault(interfaceCls, null);
    }
}
