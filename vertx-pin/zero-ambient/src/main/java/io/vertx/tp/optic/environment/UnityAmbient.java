package io.vertx.tp.optic.environment;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * Read ambient here for data initialization
 */
public class UnityAmbient implements UnityApp {
    private static final ConcurrentMap<String, UnityApp> UNITY_POOL =
            new ConcurrentHashMap<>();

    @Override
    public void initialize() {
        /*
         * Initialize Unity Pool
         */
        UnityAsker.init();
        /*
         * Critical initialization
         */
    }

    @Override
    public ConcurrentMap<String, UnityApp> getWhole() {
        return UNITY_POOL;
    }
}
