package io.vertx.zero.mirror;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/*
 * Get all package data in current environment
 * filter by `ke/config/package-filter.json` configuration inner
 */
@SuppressWarnings("unchecked")
class PackHunter {
    private static final Set<String> FILTERS = new TreeSet<>();
    private static final String FILTER_CONFIG = "ke/config/package-filter.json";
    private static final Annal LOGGER = Annal.get(PackHunter.class);

    static {
        /*
         * Read configuration to fill FILTERS;
         */
        final JsonObject filter = Ut.ioJObject(FILTER_CONFIG);
        if (filter.containsKey("skip")) {
            final JsonArray skiped = filter.getJsonArray("skip");
            if (Objects.nonNull(skiped)) {
                FILTERS.addAll(skiped.getList());
            }
        }
    }

    /*
     * Scanned target package here
     * Verified between
     * 1) development environment
     * 2) production environment
     */
    static Set<String> getPackages() {
        final Package[] packages = Package.getPackages();
        final Set<String> packageDirs = new TreeSet<>();
        for (final Package pkg : packages) {
            final String pending = pkg.getName();
            final boolean skip = FILTERS.stream().anyMatch(pending::startsWith);
            if (!skip) {
                packageDirs.add(pending);
            }
        }
        LOGGER.info(Info.PACKAGES, String.valueOf(packageDirs.size()), String.valueOf(packages.length));
        return packageDirs;
    }
}
