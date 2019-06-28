package io.vertx.tp.jet.cv;

import io.vertx.up.eon.ID;

public interface JtKey {

    String PARAM_BODY = ID.PARAM_BODY;
    String PARAM_HEADER = ID.PARAM_HEADER;

    interface Delivery {
        String CONFIG = "config";
        String ORDER = "order";
        String SERVICE = "service";
        String API = "api";
        String KEY = "key";
        String APP_ID = "appId";
    }
}
