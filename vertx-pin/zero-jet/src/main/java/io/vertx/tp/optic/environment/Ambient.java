package io.vertx.tp.optic.environment;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.database.DataPool;
import io.vertx.tp.error._500AmbientConnectException;
import io.vertx.tp.jet.atom.JtApp;
import io.vertx.tp.jet.init.JtPin;
import io.vertx.zero.epic.Ut;
import io.vertx.zero.epic.fn.Fn;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * The environment data, it's for multi-app deployment here
 * Here must be defined ambient for App / Source
 */
@SuppressWarnings("all")
public class Ambient {
    /* Each application has one environment */
    private static final ConcurrentMap<String, JtApp> APPS =
            new ConcurrentHashMap<>();

    /* Database Pool of each application */
    private static final ConcurrentMap<String, DataPool> POOL =
            new ConcurrentHashMap<>();

    /* Environment information of Ambient */
    private static final ConcurrentMap<String, AmbientEnvironment> ENVIRONMENTS =
            new ConcurrentHashMap<>();

    static {
        try {
            /*
             * 1. UnityApp fetching here.
             */
            final UnityApp unity = JtPin.getUnity();
            Fn.out(null == unity, _500AmbientConnectException.class, Ambient.class);
            /*
             * 2. UnityApp initializing, the whole environment will be initianlized
             */
            unity.initialize();
            /*
             * 3. Application environment initialization
             */
            final ConcurrentMap<String, JsonObject> unityData = unity.connect();
            unityData.forEach((key, json) -> APPS.put(key, Ut.deserialize(json, JtApp.class)));
            /*
             * 4. Binding configuration of this environment
             * - DSLContext ( reference )
             * - Service/Api -> Uri
             * - Router -> Route of Vert.x
             */
            APPS.forEach((appId, app) -> ENVIRONMENTS.put(appId, new AmbientEnvironment(app).init()));
        } catch (Throwable ex) {
            // TODO: Start up exception
            ex.printStackTrace();
        }
    }

    public static ConcurrentMap<String, AmbientEnvironment> getEnvironments() {
        return ENVIRONMENTS;
    }

    public static JtApp getApp(final String key) {
        return APPS.get(key);
    }
}
