package io.vertx.tp.crud.init;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.tp.crud.atom.IxSeeker;
import io.vertx.tp.crud.cv.IxFolder;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;

import java.util.Locale;
import java.util.Objects;

/*
 * Configuration class initialization
 * plugin/crud/configuration.json
 *
 */
class IxConfiguration {

    private static final Annal LOGGER = Annal.get(IxConfiguration.class);
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
            initSeekers(CONFIG);
        }
    }

    static IxConfig getConfig() {
        return CONFIG;
    }

    static IxSeeker getSeeker(final String requestUri, final HttpMethod method) {
        final String key = method.toString().toUpperCase(Locale.getDefault()) + " " + requestUri;
        return IxSeeker.get(key);
    }

    private static void initSeekers(final IxConfig config) {
        if (Objects.nonNull(config)) {
            final JsonObject seekerConfig = config.getSeekerConfig();
            if (Objects.nonNull(seekerConfig) && !Ut.isNil(seekerConfig)) {
                /*
                 * Seeker Config
                 */
                seekerConfig.fieldNames().stream()
                        .filter(key -> seekerConfig.getValue(key) instanceof String)
                        .forEach(key -> IxSeeker.create(key, seekerConfig.getString(key)));
                Ix.infoInit(LOGGER, "Ix Seekers: {0}", IxSeeker.size());
            }
        }
    }
}
