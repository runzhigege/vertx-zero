package io.vertx.tp.ui.init;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ui.atom.UiConfig;
import io.vertx.tp.ui.cv.UiFolder;
import io.vertx.tp.ui.refine.Ui;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * Configuration class initialization
 * plugin/ui/configuration.json
 *
 */
class UiConfiguration {
    private static final Annal LOGGER = Annal.get(UiConfiguration.class);
    /*
     * Static configuration mapping for column, it could not be modified
     * once configured into
     */
    private static final ConcurrentMap<Class<?>, JsonArray> COLUMN_MAP =
            new ConcurrentHashMap<>();
    private static UiConfig CONFIG = null;

    static void init() {
        if (null == CONFIG) {
            final JsonObject uiData = Ut.ioJObject(UiFolder.CONFIG_FILE);
            Ui.infoInit(LOGGER, "Ui Json Data: {0}", uiData.encode());
            CONFIG = Ut.deserialize(uiData, UiConfig.class);
            Ui.infoInit(LOGGER, "Ui Configuration: {0}", CONFIG.toString());
            /* Static Loading */
            initColumn(CONFIG);
            Ui.infoInit(LOGGER, "Ui Columns: Size = {0}", COLUMN_MAP.size());
        }
    }

    static UiConfig getConfig() {
        return CONFIG;
    }

    private static void initColumn(final UiConfig config) {
        final JsonObject staticConfig = config.getConfigStatic();
        if (!Ut.isNil(staticConfig)) {
            final String configPath = config.getConfigPath();
            staticConfig.fieldNames().forEach(clazz -> {
                final String file = staticConfig.getString(clazz);
                final String filePath = configPath + '/' + file;
                initColumn(clazz, filePath);
            });
        }
    }

    private static void initColumn(final String className, final String file) {
        /* 1. Class Not Found */
        final Class<?> keyCls = Ut.clazz(className);
        if (Objects.nonNull(keyCls)) {
            /* 2. Read columns */
            final JsonArray columns = Ut.ioJArray(file);
            if (Objects.nonNull(columns) && !columns.isEmpty()) {
                COLUMN_MAP.put(keyCls, columns);
            }
        } else {
            Ui.infoWarn(LOGGER, "Ui Class Null: {0}", className);
        }
    }
}
