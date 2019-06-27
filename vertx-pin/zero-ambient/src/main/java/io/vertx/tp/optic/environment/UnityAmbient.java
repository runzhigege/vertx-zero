package io.vertx.tp.optic.environment;

import cn.vertxup.ambient.tables.pojos.XApp;
import cn.vertxup.ambient.tables.pojos.XSource;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * Read ambient here for data initialization
 */
public class UnityAmbient implements UnityApp {

    private static final ConcurrentMap<String, JsonObject> UNITY_POOL =
            new ConcurrentHashMap<>();

    @Override
    public void initialize() {
        /*
         * Initialize Unity Pool
         */
        UnityAsker.init();
        /*
         * JsonObject initialization for configuration here
         */
        final ConcurrentMap<String, XApp> apps = UnityAsker.getApps();
        final ConcurrentMap<String, XSource> sources = UnityAsker.getSources();
        apps.keySet().stream()
                .filter(appId -> Objects.nonNull(apps.get(appId)))
                .filter(appId -> Objects.nonNull(sources.get(appId)))
                /* JsonObject converted here for app & source data */
                .map(appId -> this.connect(apps.get(appId), sources.get(appId)))
                .forEach(item -> UNITY_POOL.put(item.getString(KeField.APP_ID), item));
    }

    @Override
    public ConcurrentMap<String, JsonObject> connect() {
        return UNITY_POOL;
    }

    private JsonObject connect(final XApp app, final XSource source) {
        final JsonObject normalized = new JsonObject();
        /* ID */
        {
            /*
             * 3 Fields of identifiers
             * sigma - Cross module to identify application / container here.
             * appId - Inner ambient environment to identify application.
             * appKey - Ox engine used as dynamic identifier here.
             */
            normalized.put(KeField.APP_ID, app.getKey());
            normalized.put(KeField.APP_KEY, app.getAppKey());
            normalized.put(KeField.SIGMA, app.getSigma());
        }
        /* Unique */
        {
            /*
             * System information of application
             * 「Front App」
             * name - the unique name that will be used in front environment variable.  Z_APP
             * code - the application system code here that could be parsed by system.
             */
            normalized.put(KeField.NAME, app.getName());
            normalized.put(KeField.CODE, app.getCode());
        }
        /* Business information */
        {
            /*
             * Business information
             * title - display the information on front app
             * logo - display the logo on front app here.
             * icp - icp number of this application.
             * copyRight - copy right of this application.
             * email - administrator email that could be contacted
             */
            final JsonObject business = new JsonObject();
            business.put(KeField.TITLE, app.getTitle());
            business.put(KeField.LOGO, app.getLogo());
            business.put(KeField.ICP, app.getIcp());
            business.put(KeField.EMAIL, app.getEmail());
            business.put(KeField.COPY_RIGHT, app.getCopyRight());
            normalized.put("business", business);
        }
        /* Deployment information */
        {
            final JsonObject deployment = new JsonObject();
        }
        return normalized;
    }
}
