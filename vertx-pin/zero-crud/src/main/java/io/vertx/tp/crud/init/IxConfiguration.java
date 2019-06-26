package io.vertx.tp.crud.init;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.tp.crud.cv.IxFolder;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

/*
 * Configuration class initialization
 * plugin/crud/configuration.json
 *
 */
class IxConfiguration {

    private static final Annal LOGGER = Annal.get(IxConfiguration.class);
    /* Module Registry */
    private static final Set<String> MODULE_REG =
            new HashSet<>();
    private static IxConfig CONFIG = null;

    static void init() {
        /*
         * Read definition of security configuration from RBAC default folder
         */
        if (null == CONFIG) {
            final JsonObject configData = Ut.ioJObject(IxFolder.CONFIG_FILE);
            Ix.infoInit(LOGGER, "Ix Json Data: {0}", configData.encode());
            CONFIG = Ut.deserialize(configData, IxConfig.class);
            Ix.infoInit(LOGGER, "Ix Configuration: {0}", CONFIG.toString());
        }
    }

    static void addUrs(final String key) {
        final JsonArray patterns = CONFIG.getPatterns();
        patterns.stream()
                .map(item -> (String) item)
                .map(pattern -> MessageFormat.format(pattern, key))
                .forEach(MODULE_REG::add);
    }

    static Set<String> getUris() {
        return MODULE_REG;
    }
}
