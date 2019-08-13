package io.vertx.tp.rbac.refine;

import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.optic.Orbit;
import io.vertx.tp.optic.Pocket;
import io.vertx.tp.optic.atom.Income;
import io.vertx.tp.rbac.permission.ScHabitus;
import io.vertx.up.commune.Envelop;
import io.vertx.up.log.Annal;
import io.vertx.up.runtime.ZeroAnno;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

/*
 * Uri Processing to calculate resource key here.
 */
class ScPhase {

    private static final Annal LOGGER = Annal.get(ScPhase.class);

    static String uri(final String uri, final String requestUri) {
        final Orbit orbit = Pocket.lookup(Orbit.class);
        if (null == orbit) {
            return uri;
        } else {
            /* Pocket processing */
            final Income income = Pocket.income(Orbit.class, uri, requestUri);
            return orbit.analyze(income.arguments());
        }
    }

    static String uri(final RoutingContext context) {
        final HttpServerRequest request = context.request();
        final HttpMethod method = request.method();
        final String requestUri = ZeroAnno.recoveryUri(request.uri(), method);
        return uri(requestUri, request.uri());
    }

    static Future<JsonObject> cacheBound(final Envelop envelop) {
        final String token = envelop.jwt();
        final JsonObject tokenJson = Ux.Jwt.extract(token);
        final String habit = tokenJson.getString("habitus");
        if (Ut.isNil(habit)) {
            /*
             * Empty bound in current interface instead of other
             */
            return Ux.toFuture(new JsonObject());
        } else {
            /*
             * ScHabitus instead of Session
             */
            final ScHabitus habitus = ScHabitus.initialize(habit);
            /*
             * Method name / uri
             */
            final HttpMethod method = envelop.getMethod();
            final String uri = envelop.getUri();
            String requestUri = ZeroAnno.recoveryUri(uri, method);
            requestUri = uri(requestUri, uri);
            /*
             * Cache key here.
             */
            Sc.infoAuth(LOGGER, "Processed Uri: {0}", requestUri);
            /*
             * Cache key
             */
            final String cacheKey = Ke.keySession(method.name(), requestUri);
            Sc.infoAuth(LOGGER, "Try cacheKey: {0}", cacheKey);
            return habitus.get(cacheKey);
        }
    }
}
