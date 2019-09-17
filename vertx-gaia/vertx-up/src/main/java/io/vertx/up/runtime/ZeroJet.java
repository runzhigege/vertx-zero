package io.vertx.up.runtime;

import io.vertx.core.http.HttpMethod;

/*
 * Zero jet for dynamic deployment here.
 * This class is for zero-jet project as dynamic deployment
 * I_API / I_SERVICE here used.
 *
 * 1) The API / SERVICE definition should be captured by `ZeroUri` class
 * 2) The pattern should be captured and it will passed to security engine.
 */
public class ZeroJet {

    public static void resolve(final HttpMethod method,
                               final String uri) {
        ZeroUri.resolve(method, uri);
    }
}
