package io.vertx.up.commune;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.em.ChannelType;
import io.vertx.zero.atom.Database;
import io.vertx.zero.atom.Integration;

/*
 * API definition here, there are some complex definitions
 * 1）Critical attributes: method, uri
 */
public interface Api extends Json, Commercial, Application {
    /*
     * Http Method
     */
    HttpMethod method();

    /*
     * Api Uri:
     * 1）Secure: `/api/*`
     * 2）Public: `/*`
     * */
    String path();
}

interface Application {
    /*
     * Application Id
     */
    String app();
}

/*
 * Channel Definition
 */
interface Commercial {
    /*
     * Get channel type of definition ( 1 of 4 )
     * The channel class is fixed in current version, mapped to channel type.
     */
    ChannelType channelType();

    /*
     * Get channel class here, it will be initialized by other positions
     */
    Class<?> channelComponent();

    /*
     * Get business component class, it will be initialized by other positions
     */
    Class<?> businessComponent();

    /*
     * Get record class, it will be initialized by other positions
     */
    Class<?> recordComponent();

    /*
     * Get database reference
     */
    Database database();

    /*
     * Get integration reference
     */
    Integration integration();

    /*
     * Get business component config
     */
    JsonObject options();
}
