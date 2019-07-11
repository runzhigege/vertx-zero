package io.vertx.tp.optic.environment;

import cn.vertxup.ambient.domain.tables.pojos.XApp;
import cn.vertxup.ambient.domain.tables.pojos.XSource;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.epic.Ut;
import io.vertx.up.fn.Fn;

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
                .map(appId -> connect(apps.get(appId), sources.get(appId)))
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
             * language - language of this application
             * active - Whether enabled ( it's for future )
             */
            normalized.put(KeField.NAME, app.getName());
            normalized.put(KeField.CODE, app.getCode());
            normalized.put(KeField.LANGUAGE, app.getLanguage());
            normalized.put(KeField.ACTIVE, app.getActive());
        }
        /* Business information */
        {
            /* Major: Logo and Title */
            normalized.put(KeField.LOGO, app.getLogo());
            normalized.put(KeField.App.TITLE, app.getTitle());
            /*
             * Business information
             * title - display the information on front app
             * logo - display the logo on front app here.
             * icp - icp number of this application.
             * copyRight - copy right of this application.
             * email - administrator email that could be contacted
             */
            final JsonObject business = new JsonObject();
            business.put(KeField.App.ICP, app.getIcp());
            business.put(KeField.App.EMAIL, app.getEmail());
            business.put(KeField.App.COPY_RIGHT, app.getCopyRight());
            normalized.put("business", business);
        }
        /* Deployment information */
        {
            /*
             * Deployment information
             * Back-End
             * domain - application domain information that will be deployed
             * port - application port information that will be exposed.
             * route - application sub routing information
             */
            final JsonObject backend = new JsonObject();
            backend.put(KeField.App.DOMAIN, app.getDomain());
            backend.put(KeField.App.APP_PORT, app.getAppPort());
            backend.put(KeField.App.ROUTE, app.getRoute());
            normalized.put("backend", backend);
            /*
             * Front-End
             * path - front end application information
             * urlEntry - Url Entry of Login Home
             * urlMain - Url Entry of Admin Home
             *
             */
            final JsonObject frontend = new JsonObject();
            frontend.put(KeField.App.PATH, app.getPath());
            frontend.put(KeField.App.URL_ENTRY, app.getUrlEntry());
            frontend.put(KeField.App.URL_MAIN, app.getUrlMain());
            normalized.put("frontend", frontend);
        }
        /* Auditor information */
        {
            /*
             * Auditor information of current application.
             * createdAt, createdBy
             * updatedAt, updatedBy
             */
            final JsonObject auditor = new JsonObject();
            auditor.put("createdBy", app.getCreatedBy());
            Fn.safeNull(() -> auditor.put("createdAt", Ut.parse(app.getCreatedAt()).toInstant()), app.getCreatedAt());
            auditor.put("updatedBy", app.getUpdatedBy());
            Fn.safeNull(() -> auditor.put("updatedAt", Ut.parse(app.getUpdatedAt()).toInstant()), app.getUpdatedAt());
            normalized.put("auditor", auditor);
        }
        /* Database information */
        {
            /*
             * Database information for JDBC
             * hostname - database server host
             * instance - database name
             * port - database server port
             * category - database type here
             * jdbcUrl - JDBC connection string
             * username - JDBC username
             * password - JDBC password
             */
            final JsonObject sourceJson = new JsonObject();
            sourceJson.put("hostname", source.getHostname());
            sourceJson.put("instance", source.getInstance());
            sourceJson.put("port", source.getPort());
            sourceJson.put("category", source.getCategory());
            sourceJson.put("jdbcUrl", source.getJdbcUrl());
            sourceJson.put("username", source.getUsername());
            sourceJson.put("password", source.getPassword());
            normalized.put("source", sourceJson);
        }
        return normalized;
    }
}
