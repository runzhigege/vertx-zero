package io.vertx.zero.runtime;

import io.vertx.core.json.JsonObject;
import io.vertx.up.log.Annal;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.exception.heart.LimeFileException;
import io.vertx.zero.log.internal.Log4JAnnal;
import io.vertx.zero.marshal.options.Opts;
import io.vertx.zero.epic.Ut;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Critical Environment
 *
 * @author lang
 */
public final class ZeroAmbient {

    private static final String KEY = "inject";
    /**
     * Avoid dead lock, use internal Log only.
     **/
    private static final Annal LOGGER = new Log4JAnnal(ZeroAmbient.class);

    private static final ConcurrentMap<String, Class<?>> INJECTIONS;

    private static final Opts<JsonObject> OPTS = Opts.get();

    static {
        INJECTIONS = new ConcurrentHashMap<>();
        /*
         * Apply default value for
         * vertx-inject.yml
         */
        final JsonObject injectOpt = new JsonObject();
        try {
            final JsonObject opt = OPTS.ingest(KEY);
            injectOpt.mergeIn(opt);
        }catch (ZeroException | LimeFileException ex){
            LOGGER.warn(ex.getMessage());
        }
        /*
         * Scanned for injectOpt here
         */
        Ut.itJObject(injectOpt, (item, field) -> {
            final String plugin = item.toString();
            if (!plugin.equals(Log4JAnnal.class.getName())) {
                // Skip class "io.vertx.zero.log.internal.Log4JAnnal"
                LOGGER.info(Info.PLUGIN_LOAD, KEY, field, plugin);
            }
            INJECTIONS.put(field, Ut.clazz(plugin));
        });
    }

    private ZeroAmbient() {
    }

    public static Class<?> getPlugin(final String key) {
        return INJECTIONS.get(key);
    }

    public static ConcurrentMap<String, Class<?>> getInjections() {
        return INJECTIONS;
    }

    /**
     * Inited by ZeroGrid static
     */
    public static void init() {
    }
}
