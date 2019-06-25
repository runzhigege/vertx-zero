package io.vertx.tp.optic;

import io.vertx.tp.crud.init.IxPin;

import java.util.Set;

/*
 * Calculate Resource Key;
 */
public class ActorOrbit implements Orbit {
    /*
     * Critical Pool For URI Here.
     */
    private static final Set<String> URIS = IxPin.getUris();

    @Override
    public String extract(final String uri, final String requestUri) {
        if (this.isMatch(requestUri)) {
            final String[] source = uri.split("/");
            final String[] request = requestUri.split("/");
            /*
             * Scan for actor parameters
             */
            final StringBuilder builder = new StringBuilder();
            for (int idx = 0; idx < source.length; idx++) {
                if (":actor".equals(source[idx])) {
                    builder.append(request[idx]).append('/');
                } else {
                    builder.append(source[idx]).append('/');
                }
            }
            return builder.delete(builder.length() - 1, builder.length()).toString();
        } else {
            return uri;
        }
    }

    private boolean isMatch(final String requestUri) {
        if (URIS.contains(requestUri)) {
            /* No :key mode */
            return true;
        } else {
            /* Length > 36, at least contains UUID */
            return 36 < requestUri.length();
        }
    }
}
