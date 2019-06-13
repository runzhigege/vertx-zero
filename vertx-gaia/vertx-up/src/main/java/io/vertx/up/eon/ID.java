package io.vertx.up.eon;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public interface ID {

    String PARAMS_CONTENT = "$$PARAM_CONTENT$$";

    String PARAMS_LENGTH = "$$PARAM_LENGTH$$";

    String DIRECT = "$$DIRECT$$";

    String URI_KEY = "$$ALL$$";

    String IGNORE = "$$IGNORE$$";

    interface Addr {

        String REGISTRY_START = "ZERO://MICRO/REGISTRY/START";

        String IPC_START = "ZERO://MICRO/IPC/START";
    }

    interface Header {

        String X_USER = "X-User";

        String X_ROLE = "X-Role";

        /* appId */
        String X_APP_ID = "X-App-Id";

        /* appKey */
        String X_APP_KEY = "X-App-Key";

        /* sigma */
        String X_SIGMA = "X-Sigma";

        ConcurrentMap<String, String> PARAM_MAP = new ConcurrentHashMap<String, String>() {
            {
                this.put(X_APP_ID, "appId");
                this.put(X_APP_KEY, "appKey");
                this.put(X_SIGMA, "sigma");
            }
        };
    }
}
