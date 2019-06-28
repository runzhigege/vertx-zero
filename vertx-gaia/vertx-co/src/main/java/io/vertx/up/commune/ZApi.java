package io.vertx.up.commune;

import io.vertx.core.http.HttpMethod;
import io.vertx.up.eon.em.ChannelType;

/*
 * API definition here, there are some complex definitions
 * 1）Critical attributes: method, uri
 */
public interface ZApi extends ZJson, ZChannel, ZComponent {
    /*
     * Http Method
     *
     */
    HttpMethod method();

    /*
     * Api Uri:
     * 1）Secure: `/api/*`
     * 2）Public: `/*`
     * */
    String path();
}

/*
 * Channel Definition
 */
interface ZChannel {
    /*
     * Get channel type of definition ( 1 of 4 )
     * The channel class is fixed in current version, mapped to channel type.
     */
    ChannelType channelType();

    /*
     * Get channel class here, it will be initialized by other positions
     */
    Class<?> channelComponent();
}

interface ZComponent {
    /*
     * Get business component class, it will be initialized by other positions
     */
    Class<?> businessComponent();
}