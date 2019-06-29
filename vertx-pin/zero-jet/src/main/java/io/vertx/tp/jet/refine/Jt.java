package io.vertx.tp.jet.refine;

import cn.vertxup.jet.tables.pojos.IApi;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.atom.JtConfig;
import io.vertx.tp.jet.atom.JtUri;
import io.vertx.tp.jet.atom.JtWorker;
import io.vertx.up.eon.em.ChannelType;
import io.vertx.up.log.Annal;
import io.vertx.zero.atom.Database;
import io.vertx.zero.atom.Integration;
import io.vertx.zero.eon.Strings;

import javax.ws.rs.core.MediaType;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Jt {

    public static void infoInit(final Annal logger, final String pattern, final Object... args) {
        JtLog.infoInit(logger, pattern, args);
    }

    public static void infoRoute(final Annal logger, final String pattern, final Object... args) {
        JtLog.infoRoute(logger, pattern, args);
    }

    public static void infoWorker(final Annal logger, final String pattern, final Object... args) {
        JtLog.infoWorker(logger, pattern, args);
    }

    public static void infoWeb(final Annal logger, final String pattern, final Object... args) {
        JtLog.infoWeb(logger, pattern, args);
    }

    /*
     * Extraction for some specification data
     */
    public static String toPath(final Supplier<String> routeSupplier, final Supplier<String> uriSupplier,
                                final boolean secure, final JtConfig external) {
        return JtRoute.toPath(routeSupplier, uriSupplier, secure, external);
    }

    public static Set<MediaType> toMime(final Supplier<String> supplier) {
        return JtRoute.toMime(supplier);
    }

    public static Set<String> toMimeString(final Supplier<String> supplier) {
        return toMime(supplier).stream()
                .map(type -> type.getType() + Strings.SLASH + type.getSubtype())
                .collect(Collectors.toSet());
    }

    public static Database toDatabase(final Supplier<String> supplier, final Database defaultDatabase) {
        return JtDataObject.toDatabase(supplier, defaultDatabase);
    }

    public static Integration toIntegration(final Supplier<String> supplier) {
        return JtDataObject.toIntegration(supplier);
    }

    public static Set<String> toSet(final Supplier<String> supplier) {
        return JtRoute.toSet(supplier);
    }

    /*
     * Type extraction
     */
    public static JtWorker toWorker(final IApi api) {
        return JtType.toWorker(api);
    }

    public static Class<?> toChannel(final Supplier<String> supplier, final ChannelType type) {
        return JtType.toChannel(supplier, type);
    }

    public static <T> Field toContract(final Class<?> executor, final T instance, final Class<?> fieldType) {
        return JtType.toContract(executor, instance, fieldType);
    }

    /*
     * Ask configuration, before deployVerticle here
     * 1. JtUri -> JsonObject
     * 2. Set -> Map ( key -> JtUri -> JsonObject )
     * 3. Before deployment of Verticle
     */
    public static ConcurrentMap<String, JsonObject> ask(final Set<JtUri> uriSet) {
        return JtDelivery.ask(uriSet);
    }

    /*
     * Answer configuration, after deployVerticle here
     * 1. JsonObject -> JtUri
     * 2. Map ( key -> apiKey -> JsonObject -> JtUri )
     * 3. After deployment of Verticle ( Consume )
     */
    public static ConcurrentMap<String, JtUri> answer(final JsonObject config) {
        return JtDelivery.answer(config);
    }
}
