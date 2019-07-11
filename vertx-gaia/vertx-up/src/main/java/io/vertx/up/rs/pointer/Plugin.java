package io.vertx.up.rs.pointer;

import io.vertx.core.json.JsonObject;
import io.vertx.up.uca.marshal.node.Node;
import io.vertx.up.uca.marshal.node.ZeroUniform;
import io.vertx.up.util.Ut;

import java.util.Objects;
import java.util.function.BiConsumer;

/*
 * Default package scope tool for extension.
 */
class Plugin {

    private static transient final Node<JsonObject> UNIFORM = Ut.singleton(ZeroUniform.class);
    private static transient final JsonObject PLUGIN_CONFIG = new JsonObject();

    /*
     * I/O read for config loading.
     */
    static {
        final JsonObject uniform = UNIFORM.read();
        if (uniform.containsKey("extension")) {
            final JsonObject pluginConfig = uniform.getJsonObject("extension");
            if (Objects.nonNull(pluginConfig)) {
                PLUGIN_CONFIG.mergeIn(pluginConfig);
            }
        }
    }

    static void mountPlugin(final String key, final BiConsumer<Class<?>, JsonObject> consumer) {
        if (PLUGIN_CONFIG.containsKey(key)) {
            final JsonObject metadata = PLUGIN_CONFIG.getJsonObject(key);
            final Class<?> pluginCls = Ut.clazz(metadata.getString("component"));
            if (Objects.nonNull(pluginCls)) {
                final JsonObject config = metadata.getJsonObject("config");
                consumer.accept(pluginCls, config);
            }
        }
    }
}
