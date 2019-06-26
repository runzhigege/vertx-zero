package io.vertx.tp.rbac.refine;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.tp.optic.Orbit;
import io.vertx.tp.optic.Pocket;
import io.vertx.tp.optic.atom.Income;
import io.vertx.up.log.Annal;
import io.vertx.up.web.ZeroAnno;

import java.util.Objects;

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

    static String cacheKey(final RoutingContext context) {
        final HttpServerRequest request = context.request();
        final String uri = Sc.uri(context);
        /* Cache Key */
        final String cacheKey = "session-" + request.method().name() + ":" + uri;
        /* Cache Data */
        Sc.infoAuth(LOGGER, "Try cacheKey: {0}", cacheKey);
        return cacheKey;
    }

    static JsonObject cacheData(final RoutingContext context) {
        /* Session Extract */
        final Session session = context.session();
        /* Cache Key */
        final String cacheKey = cacheKey(context);
        final Buffer buffer = session.get(cacheKey);
        if (Objects.nonNull(buffer)) {
            return buffer.toJsonObject();
        } else {
            return new JsonObject();
        }
    }
}
