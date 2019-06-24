package io.vertx.tp.ke.extension;

/*
 * Interface for authorization resource key processing
 * 1) Request Uri: /api/group/search
 * 2) Pattern Uri:  /api/:actor/search
 * 3) Resolution for request key:
 */
public interface Orbit {

    /*
     * Calculation method here.
     */
    String extract(String uri, String requestUri);
}
