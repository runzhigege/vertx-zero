package io.vertx.tp.init;

import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.zero.atom.Ruler;
import io.vertx.zero.exception.DynamicConfigTypeException;
import io.vertx.zero.exception.DynamicKeyMissingException;
import io.vertx.zero.marshal.node.Node;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Third part configuration data.
 * endpoint: Major endpoint
 * config: Configuration Data of third part.
 */
public class TpConfig implements Serializable {

    private static final Annal LOGGER = Annal.get(TpConfig.class);

    private static final ConcurrentMap<String, TpConfig> CACHE = new ConcurrentHashMap<>();

    private static final Node<JsonObject> TP = Node.infix("tp");

    private static final String KEY_ENDPOINT = "endpoint";
    private static final String KEY_CONFIG = "config";

    private final transient JsonObject config;
    private final transient String endpoint;

    public static TpConfig create(final String key) {
        return Fn.pool(CACHE, key, () -> new TpConfig(key, null));
    }

    public static TpConfig create(final String key, final String rule) {
        return Fn.pool(CACHE, key, () -> new TpConfig(key, rule));
    }

    public TpConfig(final String key, final String rule) {
        final JsonObject config = TP.read();
        // Check up exception for key
        Fn.flingUp(null == config || !config.containsKey(key),
                LOGGER, DynamicKeyMissingException.class,
                this.getClass(), key, config);

        // Check up exception for JsonObject
        final Class<?> type = config.getValue(key).getClass();
        Fn.flingUp(JsonObject.class != type,
                LOGGER, DynamicConfigTypeException.class,
                this.getClass(), key, type);

        // Extract config information.
        final JsonObject raw = config.getJsonObject(key);
        this.endpoint = Fn.get(null, () -> raw.getString(KEY_ENDPOINT), raw.getValue(KEY_ENDPOINT));
        this.config = Fn.get(new JsonObject(), () -> raw.getJsonObject(KEY_CONFIG), raw.getValue(KEY_CONFIG));
        // Verify the config data.
        if (null != rule) {
            Fn.flingUp(() -> Fn.shuntZero(() -> Ruler.verify(rule, this.config), this.config), LOGGER);
        }
    }

    public JsonObject getConfig() {
        return this.config;
    }

    public String getEndPoint() {
        return this.endpoint;
    }
}
