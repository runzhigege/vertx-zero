package io.vertx.up.commune;

import io.vertx.up.commune.config.Dict;

/*
 * Service
 */
public interface ServiceDefinition extends Service {

    /*
     * `dictConfig`
     * `dictComponent` of I_SERVICE
     * `dictEpsilon` of I_SERVICE
     * Here `dictComponent` is required if configured.
     * Dictionary configuration for current Job / Component
     */
    Dict dict();
}
