package io.vertx.tp.optic.environment;

import java.util.concurrent.ConcurrentMap;

/*
 * Application configuration for X_APP but the implementation should be provided
 * in another project instead of current one.
 */
public interface UnityApp {

    void initialize();

    ConcurrentMap<String, UnityApp> getWhole();


}
