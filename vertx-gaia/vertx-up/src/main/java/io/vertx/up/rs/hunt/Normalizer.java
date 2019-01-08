package io.vertx.up.rs.hunt;

import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.HttpStatusCode;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.up.annotations.SessionData;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.agent.Event;
import io.vertx.zero.eon.Strings;
import io.zero.epic.Ut;

import javax.ws.rs.core.MediaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Content-Type
 * Accept
 */
public final class Normalizer {
    /**
     * Response initializing
     */
    static HttpServerResponse initialize(final RoutingContext context,
                                         final Envelop envelop) {
        // 1. Get response reference
        final HttpServerResponse response
                = context.response();
        // 2. Set status code
        final HttpStatusCode code = envelop.status();
        response.setStatusCode(code.code());
        response.setStatusMessage(code.message());
        return response;
    }

    static <T> void storeSession(final RoutingContext context,
                                 final T data,
                                 final Method method) {
        final Session session = context.session();
        if (null != session && null != data && method.isAnnotationPresent(SessionData.class)) {
            final Annotation annotation = method.getAnnotation(SessionData.class);
            final String key = Ut.invoke(annotation, "value");
            final String field = Ut.invoke(annotation, "field");
            // Data Storage
            Object reference = data;
            if (Ut.isJObject(data) && Ut.notNil(field)) {
                final JsonObject target = (JsonObject) data;
                reference = target.getValue(field);
            }
            // Session Put / Include Session ID
            session.put(key, reference);
        }
    }

    /**
     * Wrapper response with media type
     */
    static void media(final HttpServerResponse response,
                      final Event event) {
        // 1. Whether input event
        if (null == event) {
            // Default Situation
            response.putHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        } else {
            final Set<MediaType> produces = event.getProduces();
            // @Produces means that the server generate response to client
            if (produces.contains(MediaType.WILDCARD_TYPE)) {
                response.putHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            } else {
                final MediaType type = produces.iterator().next();
                if (null != type) {
                    final String content = type.getType() + Strings.SLASH + type.getSubtype();
                    response.putHeader(HttpHeaders.CONTENT_TYPE, content);
                } else {
                    // Other situation for default again
                    response.putHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
                }
            }
        }
    }

    static void out(
            final HttpServerResponse response,
            final Envelop envelop,
            final Event event
    ) {
        // Set response for event
        if (!response.ended()) {
            // response header
            final String headerStr = response.headers().get(HttpHeaders.CONTENT_TYPE);
            if (null == headerStr) {
                // Default mode
                response.end(envelop.responseString());
            } else {
                final MediaType type = MediaType.valueOf(headerStr);
                if (MediaType.WILDCARD_TYPE.equals(type)) {
                    // Default mode
                    response.end(envelop.responseString());
                } else {
                    // Dispatch
                    if (MediaType.APPLICATION_OCTET_STREAM_TYPE.equals(type)) {
                        // Buffer only
                        response.end(envelop.responseBuffer());
                    } else {
                        // Other uniform convert to string mode
                        response.end(envelop.responseString());
                    }
                }
            }
        }
        response.close();
    }
}
