package io.vertx.up.unity;

import io.vertx.core.eventbus.DeliveryOptions;

/*
 * Unity configuration management
 * In future, all the configuration management will be here for
 * uniform calling
 */
public class UxOpt {
    /*
     * Default DeliveryOptions
     */
    public DeliveryOptions delivery() {
        final DeliveryOptions options = new DeliveryOptions();
        /* 2 min for timeout to avoid sync long works */
        options.setSendTimeout(120000);
        return options;
    }
}
