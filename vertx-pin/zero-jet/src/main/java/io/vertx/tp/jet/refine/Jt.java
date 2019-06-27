package io.vertx.tp.jet.refine;

import io.vertx.tp.jet.atom.JtConfig;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Strings;

import javax.ws.rs.core.MediaType;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Jt {

    public static void infoInit(final Annal logger, final String pattern, final Object... args) {
        JtLog.infoInit(logger, pattern, args);
    }

    public static void infoRoute(final Annal logger, final String pattern, final Object... args) {
        JtLog.infoRoute(logger, pattern, args);
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

    public static Set<String> toSet(final Supplier<String> supplier) {
        return JtRoute.toSet(supplier);
    }
}
