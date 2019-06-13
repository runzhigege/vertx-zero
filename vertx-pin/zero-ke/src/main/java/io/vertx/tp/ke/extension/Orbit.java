package io.vertx.tp.ke.extension;

import io.zero.epic.Ut;

/*
 * Interface for authorization resource key processing
 * 1) Request Uri: /api/group/search
 * 2) Pattern Uri:  /api/:actor/search
 * 3) Resolution for request key:
 */
public interface Orbit {
    /*
     * Could support only one
     */
    static Orbit generate(final Class<?> clazz) {
        return Ut.singleton(clazz);
    }

    /*
     * Calculation method here.
     */
    String extract(String uri, String requestUri);
}
