package io.vertx.tp.optic.environment;

import io.vertx.tp.error._500AmbientConnectException;
import io.vertx.tp.jet.init.JtPin;
import io.zero.epic.fn.Fn;

/*
 * The environment data, it's for multi-app deployment here
 * Here must be defined ambient for App / Source
 */
@SuppressWarnings("all")
public class Ambient {

    static {
        /*
         * 1. UnityApp fetching here.
         */
        final UnityApp unity = JtPin.getUnity();
        Fn.out(null == unity, _500AmbientConnectException.class, Ambient.class);
        /*
         * 2. UnityApp initializing, the whole environment will be initianlized
         */
        unity.initialize();
        /*
         * 3. Application environment initialization
         */
    }
}
