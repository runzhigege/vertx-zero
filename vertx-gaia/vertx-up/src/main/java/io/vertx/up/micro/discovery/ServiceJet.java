package io.vertx.up.micro.discovery;

import io.vertx.circuitbreaker.CircuitBreaker;
import io.vertx.circuitbreaker.CircuitBreakerOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.RequestOptions;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceReference;
import io.vertx.servicediscovery.types.HttpEndpoint;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.config.CircuitVisitor;
import io.vertx.up.micro.discovery.multipart.Pipe;
import io.vertx.up.micro.discovery.multipart.UploadPipe;
import io.vertx.up.micro.matcher.Arithmetic;
import io.vertx.up.micro.matcher.CommonArithmetic;
import io.vertx.zero.marshal.Visitor;
import io.vertx.zero.epic.Ut;
import io.vertx.zero.fn.Fn;

import java.util.List;
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
                    InOut.sync404Error(this.getClass(), context);
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

    private void doRequest(final RoutingContext context,
                           final ServiceReference reference,
                           final Record record,
                           final Consumer<Void> consumer) {
        {
            final HttpServerRequest rctRequest = context.request();

            final HttpMethod method = rctRequest.method();
            final String uri = InOut.normalizeUri(context);

            final WebClient client = reference.getAs(WebClient.class);
            final RequestOptions options = InOut.getOptions(record, uri);
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
            request.timeout(30000);
            /*
             * dispatching request
             * 1. Pure Request
             * 2. MultiPart
             */
            if (rctRequest.isExpectMultipart()) {
                /*
                 * The send method of multipart/form-data instead of others
                 * Use apache http client insead of vert.x web client
                 * because of issue of WebClient/HttpClient
                 */
                final Pipe<org.apache.http.HttpResponse> pump = UploadPipe.create(
                        context, reference, options);
                /*
                 * Http Request instead of Web Request here
                 */
                pump.doRequest(InOut.replyHttp(this.getClass(), context, consumer));
            } else {
                /*
                 * Pure request with buffer directly
                 */
                Buffer body = context.getBody();
                if (null == body) {
                    body = Buffer.buffer();
                }
                request.sendBuffer(body, InOut.replyWeb(this.getClass(), context, consumer));
            }

        }
    }
}
