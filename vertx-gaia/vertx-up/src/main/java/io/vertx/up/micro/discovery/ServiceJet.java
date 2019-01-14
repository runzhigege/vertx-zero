package io.vertx.up.micro.discovery;

import io.vertx.circuitbreaker.CircuitBreaker;
import io.vertx.circuitbreaker.CircuitBreakerOptions;
import io.vertx.core.*;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.*;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceReference;
import io.vertx.servicediscovery.types.HttpEndpoint;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.matcher.Arithmetic;
import io.vertx.up.micro.matcher.CommonArithmetic;
import io.vertx.zero.marshal.Visitor;
import io.vertx.zero.micro.config.CircuitVisitor;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class ServiceJet {
    private static final Annal LOGGER = Annal.get(ServiceJet.class);
    private static final Visitor<CircuitBreakerOptions> VISITOR =
            Ut.singleton(CircuitVisitor.class);
    private static CircuitBreakerOptions OPTIONS;

    static {
        Fn.outUp(() -> {
            if (null == OPTIONS) {
                OPTIONS = VISITOR.visit();
            }
        }, LOGGER);
    }

    private final transient Arithmetic arithmetic = Ut.singleton(CommonArithmetic.class);
    private final transient HttpServerOptions options;
    private transient ServiceDiscovery discovery;
    private transient CircuitBreaker breaker;

    private ServiceJet(final HttpServerOptions options) {
        this.options = options;
    }

    public static ServiceJet create(final HttpServerOptions options) {
        return new ServiceJet(options);
    }

    public ServiceJet connect(final Vertx vertx) {
        this.discovery = ServiceDiscovery.create(vertx);
        final String name = this.options.getHost() + this.options.getPort();
        this.breaker = CircuitBreaker.create(name, vertx, OPTIONS);
        return this;
    }

    private Future<List<Record>> getEndPoints() {
        final Future<List<Record>> future = Future.future();
        this.discovery.getRecords(record -> record.getType().equals(HttpEndpoint.TYPE),
                future.completer());
        return future;
    }

    public Handler<RoutingContext> handle() {
        // Run with circuit breaker
        return context -> this.breaker.execute(future -> this.getEndPoints().setHandler(res -> {
            if (res.succeeded()) {
                final List<Record> records = res.result();
                // Find the record hitted. ( Include Path variable such as /xx/yy/:zz/:xy )
                final Record hitted = this.arithmetic.search(records, context);
                // Complete actions.
                if (null == hitted) {
                    /**
                     * Service Not Found ( 404 )
                     * Situation 1:
                     * Zero engine could not find the uri that client provided.
                     * After sync operations, you can call future.complete directly.
                     **/
                    Output.sync404Error(this.getClass(), context);
                    future.complete();
                } else {
                    // Get service reference
                    final ServiceReference reference = this.discovery.getReference(hitted);
                    // Set callback completer
                    final Consumer<Void> consumer = (nil) -> {
                        reference.release();    // release service reference
                        future.complete();      // execute future complete operation
                    };
                    /**
                     * Service Found
                     * Situation 1:
                     * Here matching successfully when gateway get request.
                     **/
                    this.doRequest(context, reference, hitted, consumer);
                }
            } else {
                // Future failed
                future.fail(res.cause());
            }
        }));
    }

    private RequestOptions getOptions(final Record record, final String normalizedUri) {
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

    private void doRequest(final RoutingContext context,
                           final ServiceReference reference,
                           final Record record,
                           final Consumer<Void> consumer) {
        {
            final HttpServerRequest rctRequest = context.request();
            if (rctRequest.isExpectMultipart()) {
                /*
                 * Multi Part
                 */
                final Set<FileUpload> fileUploads = context.fileUploads();
                if (!fileUploads.isEmpty()) {
                    /*
                     * File Upload request sending.
                     */
                    this.sendUploadRequest(context, reference, record,
                            this.replyStreamContent(context, consumer));
                } else {
                    Output.sync500Error(this.getClass(), context, null);
                    consumer.accept(null);
                }
            } else {
                final HttpMethod method = rctRequest.method();
                final String uri = Output.normalizeUri(context);

                final WebClient client = reference.getAs(WebClient.class);
                final RequestOptions options = this.getOptions(record, uri);
                /*
                 * Here client got from service reference, it means that it's not needed to use requestAbs
                 * request instead.
                 * requestAbs: it means used absolute URI instead of uri address
                 */
                final HttpRequest<Buffer> request = client.request(method, options);
                /*
                 * Headers processing ( copy all the headers from request, perfect redirect );
                 */
                final MultiMap headers = rctRequest.headers();
                headers.forEach((item) -> request.putHeader(item.getKey(), item.getValue()));
                /*
                 * Default timeout parameters set to 5s
                 */
                request.timeout(5000);
                /*
                 * dispatching request
                 * 1. Pure Request
                 * 2. MultiPart
                 */
                /*
                 * Pure request with buffer directly
                 */
                Buffer body = context.getBody();
                if (null == body) {
                    body = Buffer.buffer();
                }
                request.sendBuffer(body, this.replyContent(context, consumer));
            }
        }
    }

    private void sendUploadRequest(final RoutingContext context,
                                   final ServiceReference reference,
                                   final Record record,
                                   final Handler<HttpClientResponse> handler) {
        /*
         * Extract file information here.
         */
        final FileUpload fileUpload = context.fileUploads().iterator().next();
        final HttpServerRequest serverRequest = context.request();
        final String contentType = serverRequest.getHeader("Content-Type");
        final Buffer buffer = Buffer.buffer();
        /*
         * HttpClient
         */
        final HttpClient client = reference.getAs(HttpClient.class);
        final String uri = Output.normalizeUri(context);
        final HttpClientRequest request =
                client.request(serverRequest.method(), this.getOptions(record, uri));
        request.headers().setAll(serverRequest.headers());
        {
            /*
             * boundary extracting
             */
            final String[] splitted = contentType.split(";");
            String boundary = null;
            if (2 == splitted.length && null != splitted[1]) {
                final String[] boundaries = splitted[1].split("=");
                if (2 == boundaries.length && null != boundaries[1]) {
                    boundary = boundaries[1];
                }
            }
            /*
             * Absolute file name
             */
            final File file = new File(fileUpload.uploadedFileName());
            if (file.exists()) {
                /*
                 * Content-Disposition: name =, filename =
                 * Content-Type
                 * Content-Transfer-Encoding
                 */
                final String disposition = "form-data; name=\"" + fileUpload.name() +
                        "\"; filename=\"" + file.getAbsolutePath() + "\"";
                request.putHeader("Content-Disposition", disposition);
                /*
                 * file data reading from file system
                 */
                final Buffer fileData = Ut.ioBuffer(fileUpload.uploadedFileName());
                buffer.appendString("\r\n\r\n" + boundary + "\r\n");
                buffer.appendBuffer(fileData);
                buffer.appendString("\r\n" + boundary + "\r\n");
                // length setting
                LOGGER.info("[ ZERO-DEBUG ] Length: " + buffer.length());
                request.putHeader("content-length", String.valueOf(buffer.length()));
            }
        }
        /*
         * User-Agent virtual
         */
        request.setTimeout(5000);
        /*
         * required for next request continue
         */
        request.handler(handler);
        request.setChunked(true);
        request.end(buffer);
    }

    private Handler<HttpClientResponse> replyStreamContent(
            final RoutingContext context,
            final Consumer<Void> consumer
    ) {
        return response -> response.bodyHandler(body -> {
            if (404 == response.statusCode()) {
                /*
                 * 404 -> 405 Error
                 */
                Output.sync405Error(this.getClass(), context);
            } else {
                /*
                 * 200 -> Success
                 */
                Output.syncSuccess(context.response(), response, body);
            }
            consumer.accept(null);
        });
    }

    private Handler<AsyncResult<HttpResponse<Buffer>>> replyContent(
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
                    Output.sync405Error(this.getClass(), context);
                } else {
                    /*
                     * 200 -> Success
                     */
                    Output.syncSuccess(context.response(), remoteResp);
                }
            } else {
                /*
                 * 500
                 */
                Output.sync500Error(this.getClass(), context, response.cause());
            }
            consumer.accept(null);
        };
    }
}
