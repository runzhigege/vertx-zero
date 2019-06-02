package io.vertx.zero.eon;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * Zero specification http header
 * This constants values contains business fields
 */
public interface Headers {
    /* appId */
    String X_APP_ID = "Ox-App-Id";

    /* appKey */
    String X_APP_KEY = "Ox-App-Key";

    /* sigma */
    String X_SIGMA = "Ox-Sigma";

    ConcurrentMap<String, String> PARAM_MAP = new ConcurrentHashMap<String, String>() {
        {
            this.put(X_APP_ID, "appId");
            this.put(X_APP_KEY, "appKey");
            this.put(X_SIGMA, "sigma");
        }
    };
}
