package io.vertx.up.micro.discovery;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.servicediscovery.Record;
import io.vertx.up.atom.Envelop;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception._404ServiceNotFoundException;
import io.vertx.up.exception._405MethodForbiddenException;
import io.vertx.up.exception._500InternalServerException;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.hunt.Answer;
import io.vertx.zero.eon.Strings;

import java.util.function.Consumer;

/**
 * Reply response to client for special situation
 * Include some important http status code
 */
public final class InOut {

    private static final Annal LOGGER = Annal.get(InOut.class);

    /**
     * Service Not Found ( 404 )
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
    public static void sync405Error(final Class<?> clazz,
                                    final RoutingContext context) {
        final HttpServerRequest request = context.request();
        final WebException exception = new _405MethodForbiddenException(clazz, request.method(),
                request.uri());
        Answer.reply(context, Envelop.failure(exception));
    }

    public static void sync500Error(final Class<?> clazz,
                                    final RoutingContext context,
                                    final Throwable ex) {
        final WebException exception = new _500InternalServerException(clazz,
                null == ex ? "Server Error" : ex.getMessage());
        Answer.reply(context, Envelop.failure(exception));
    }

    /**
     * Success ( 200 )
     */
    private static void syncSuccess(
            final HttpServerResponse response,
            final HttpResponse<Buffer> clientResponse) {
        final Buffer data = clientResponse.bodyAsBuffer();
        // Copy header
        syncSuccess(response,
                clientResponse.headers(),
                clientResponse.statusCode(),
                clientResponse.statusMessage(),
                data);
    }

    static Handler<AsyncResult<HttpResponse<Buffer>>> replyWeb(
            final Class<?> clazz,
            final RoutingContext context,
            final Consumer<Void> consumer
    ) {
        return response -> {
            if (response.succeeded()) {
                final HttpResponse<Buffer> remoteResp = response.result();
                if (404 == remoteResp.statusCode()) {
                    /*
                     * 404 -> 405 Error
                     */
                    InOut.sync405Error(clazz, context);
                } else {
                    /*
                     * 200 -> Success
                     */
                    InOut.syncSuccess(context.response(), remoteResp);
                }
            } else {
                /*
                 * 500
                 */
                InOut.sync500Error(clazz, context, response.cause());
            }
            consumer.accept(null);
        };
    }

    static Handler<HttpClientResponse> replyHttp(
            final Class<?> clazz,
            final RoutingContext context,
            final Consumer<Void> consumer
    ) {
        return response -> {
            response.bodyHandler(buffer -> {
                if (404 == response.statusCode()) {
                    /*
                     * 404 -> 405 Error
                     */
                    InOut.sync405Error(clazz, context);
                } else {
                    /*
                     * 200 -> Success
                     */
                    InOut.syncSuccess(context.response(), response, buffer);
                }
            });
            consumer.accept(null);
        };
    }

    private static void syncSuccess(
            final HttpServerResponse response,
            final HttpClientResponse clientResponse,
            final Buffer buffer) {
        // Copy header
        syncSuccess(response,
                clientResponse.headers(),
                clientResponse.statusCode(),
                clientResponse.statusMessage(),
                buffer);
    }

    private static void syncSuccess(
            final HttpServerResponse response,
            final MultiMap headers,
            final int statusCode,
            final String statusMessage,
            final Buffer data
    ) {
        response.headers().setAll(headers);
        response.setStatusCode(statusCode);
        response.setStatusMessage(statusMessage);
        response.write(data);
        response.end();
    }

    // ----------------------- In Methods for request
    public static String normalizeUri(final RoutingContext event) {
        final StringBuilder uri = new StringBuilder();
        uri.append(event.request().path());
        if (null != event.request().query()) {
            uri.append(Strings.QUESTION).append(event.request().query());
        }
        return uri.toString();
    }

    public static RequestOptions getOptions(final Record record, final String normalizedUri) {
        final RequestOptions options = new RequestOptions();
        options.setURI(normalizedUri);
        // Extract host / port
        final JsonObject location = record.getLocation();
        options.setHost(location.getString("host"));
        options.setPort(location.getInteger("port"));
        LOGGER.info("[ ZERO ] Found remote host: {0}, port: {1}, uri: {2}",
                options.getHost(), String.valueOf(options.getPort()), options.getURI());
        return options;
    }
}
