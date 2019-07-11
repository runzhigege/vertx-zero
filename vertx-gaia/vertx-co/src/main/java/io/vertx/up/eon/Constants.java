package io.vertx.up.eon;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public interface Constants {

    String DEFAULT_GROUP = "__VERTX_ZERO__";

    int DEFAULT_INSTANCES = 32;

    boolean DEFAULT_HA = true;

    String DEFAULT_JOB = "jobs";

    /**
     * Scanned data to distinguish mode
     * 1) Only Interface Style could have the indexes key such as 0,1,2 consider as data key.
     * 2) The mode impact different flow of Envelop
     */
    ConcurrentMap<Integer, String> INDEXES = new ConcurrentHashMap<Integer, String>() {
        {
            put(0, "0");
            put(1, "1");
            put(2, "2");
            put(3, "3");
            put(4, "4");
            put(5, "5");
            put(6, "6");
            put(7, "7");
        }
    };
}
