package io.vertx.up.micro.discovery;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.up.atom.Envelop;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception._404ServiceNotFoundException;
import io.vertx.up.exception._405MethodForbiddenException;
import io.vertx.up.exception._500InternalServerException;
import io.vertx.up.rs.hunt.Answer;
import io.vertx.zero.eon.Strings;

/**
 * Reply response to client for special situation
 * Include some important http status code
 */
final class Output {
    /**
     * Service Not Found ( 404 )
     *
     * @param clazz
     * @param context
     */
    static void sync404Error(final Class<?> clazz,
                             final RoutingContext context) {
        final HttpServerRequest request = context.request();
        final WebException exception = new _404ServiceNotFoundException(clazz, request.uri(),
                request.method());
        Answer.reply(context, Envelop.failure(exception));
    }

    /**
     * Method not allowed ( 405 )
     */
    static void sync405Error(final Class<?> clazz,
                             final RoutingContext context) {
        final HttpServerRequest request = context.request();
        final WebException exception = new _405MethodForbiddenException(clazz, request.method(),
                request.uri());
        Answer.reply(context, Envelop.failure(exception));
    }

    static void sync500Error(final Class<?> clazz,
                             final RoutingContext context,
                             final Throwable ex) {
        final WebException exception = new _500InternalServerException(clazz,
                null == ex ? "Server Error" : ex.getMessage());
        Answer.reply(context, Envelop.failure(exception));
    }

    /**
     * Success ( 200 )
     */
    static void syncSuccess(
            final HttpServerResponse response,
            final HttpResponse<Buffer> clientResponse) {
        final Buffer data = clientResponse.bodyAsBuffer();
        // Copy header
        response.headers().setAll(clientResponse.headers());
        response.setStatusCode(clientResponse.statusCode());
        response.setStatusMessage(clientResponse.statusMessage());
        response.write(data);
        response.end();
    }

    /**
     * Build redirect uri
     */
    static String normalizeUri(final RoutingContext event) {
        final StringBuilder uri = new StringBuilder();
        uri.append(event.request().path());
        if (null != event.request().query()) {
            uri.append(Strings.QUESTION).append(event.request().query());
        }
        return uri.toString();
    }
}
