package io.vertx.tp.optic.environment;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class UnityKey {
    /*
     * appId -> appKey
     */
    static ConcurrentMap<String, String> ID_KEY = new ConcurrentHashMap<>();
    /*
     * appId -> sigma
     */
    static ConcurrentMap<String, String> ID_SIGMA = new ConcurrentHashMap<>();
    /*
     * appKey -> sigma
     */
    static ConcurrentMap<String, String> KEY_SIGMA = new ConcurrentHashMap<>();
    /*
     * appKey -> id
     */
    static ConcurrentMap<String, String> KEY_ID = new ConcurrentHashMap<>();
    /*
     * sigma -> appKey
     */
    static ConcurrentMap<String, String> SIGMA_KEY = new ConcurrentHashMap<>();
    /*
     * sigma -> id
     */
    static ConcurrentMap<String, String> SIGMA_ID = new ConcurrentHashMap<>();
}
