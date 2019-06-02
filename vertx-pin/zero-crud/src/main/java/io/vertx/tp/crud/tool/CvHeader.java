package io.vertx.tp.crud.tool;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * Zero specification http header
 */
public interface CvHeader {
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

interface Folder {

    String MODULE = "plugin/crud/module/";

    String VALIDATOR = "plugin/crud/validator/";
}
