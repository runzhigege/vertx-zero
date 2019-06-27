package io.vertx.tp.optic.environment;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.error._500AmbientConnectException;
import io.vertx.tp.jet.atom.JtEnv;
import io.vertx.tp.jet.init.JtPin;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * The environment data, it's for multi-app deployment here
 * Here must be defined ambient for App / Source
 */
@SuppressWarnings("all")
public class Ambient {

    private static final ConcurrentMap<String, JtEnv> ENVRIONMENT =
            new ConcurrentHashMap<>();

    static {
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
        unityData.forEach((key, json) -> ENVRIONMENT.put(key, Ut.deserialize(json, JtEnv.class)));
    }
}
