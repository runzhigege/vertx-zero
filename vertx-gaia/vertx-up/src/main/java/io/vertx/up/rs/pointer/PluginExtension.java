package io.vertx.up.rs.pointer;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.plugin.extension.PlugAuditor;
import io.vertx.up.plugin.extension.PlugRegion;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;
import io.zero.epic.Ut;

import java.util.Objects;
import java.util.function.BiConsumer;

/*
 * Plugin Instance Pool for different usage.
 */
public class PluginExtension {

    private static transient final Node<JsonObject> UNIFORM = Ut.singleton(ZeroUniform.class);
    /*
     * Plugin for Auditor
     */
    private static final String AUDITOR = "auditor";
    /*
     * Plugin for Region
     */
    private static final String REGION = "region";

    private static void mountPlugin(final String key, final BiConsumer<Class<?>, JsonObject> consumer) {
        final JsonObject uniform = UNIFORM.read();
        if (uniform.containsKey("extension")) {
            final JsonObject pluginConfig = uniform.getJsonObject("extension");
            if (pluginConfig.containsKey(key)) {
                final JsonObject metadata = pluginConfig.getJsonObject(key);
                final Class<?> pluginCls = Ut.clazz(metadata.getString("component"));
                if (Objects.nonNull(pluginCls)) {
                    final JsonObject config = metadata.getJsonObject("config");
                    consumer.accept(pluginCls, config);
                }
            }
        }
    }

    public static void audit(final RoutingContext context,
                             final Envelop envelop) {
        mountPlugin(AUDITOR, (auditCls, config) -> {
            /*
             * Extend PlugAuditor for auditing system setting for some spec business.
             */
            final PlugAuditor auditor = Ut.singleton(auditCls);
            auditor.audit(context, envelop);
        });
    }

    public static void regionBefore(final RoutingContext context,
                                    final Envelop envelop) {
        mountPlugin(REGION, (auditCls, config) -> {
            /*
             * Data Region Before: Request
             */
            final PlugRegion region = Ut.singleton(auditCls);
            region.before(context, envelop);
        });
    }

    public static void regionAfter(final RoutingContext context,
                                   final Envelop envelop) {
        mountPlugin(REGION, (auditCls, config) -> {
            /*
             * Data Region After: Response
             */
            final PlugRegion region = Ut.singleton(auditCls);
            region.after(context, envelop);
        });
    }
}