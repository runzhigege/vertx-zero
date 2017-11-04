package com.vie.util;

import com.vie.hoc.HFail;
import com.vie.hoc.HPool;
import com.vie.util.io.IO;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.json.JsonObject;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Connect to vertx config to get options
 * From filename to ConfigStoreOptions
 */
public final class Store {
    /**
     * Memory pool for each file of ConfigStoreOptions
     */
    private static final ConcurrentMap<String, ConfigStoreOptions> STORAGE
            = new ConcurrentHashMap<>();

    /**
     * Return json
     *
     * @param filename
     * @return
     */
    public static ConfigStoreOptions getJson(final String filename) {
        return HFail.exec(() -> {
            final JsonObject data = IO.getJObject(filename);
            return HFail.exec(() ->
                            HPool.exec(STORAGE, filename,
                                    () -> new ConfigStoreOptions().setType("json").setConfig(data))
                    , data);
        }, filename);
    }

    /**
     * Return yaml
     */

    private Store() {
    }
}
