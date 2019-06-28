package io.vertx.tp.jet.cv;

public interface JtKey {

    String PARAM_BODY = "__BODY__";
    String PARAM_HEADER = "__HEADER__";

    interface Delivery {
        String CONFIG = "config";
        String ORDER = "order";
        String SERVICE = "service";
        String API = "api";
        String KEY = "key";
        String APP_ID = "appId";
    }
}
