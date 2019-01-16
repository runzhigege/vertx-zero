package io.vertx.up.micro.discovery.multipart;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.http.*;
import io.vertx.ext.web.RoutingContext;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceReference;
import io.vertx.up.micro.discovery.InOut;

public class HttpPump implements Pump<HttpClientResponse> {

    private final transient RoutingContext context;
    private final transient ServiceReference reference;
    private final transient Record record;
    // Secondary reference
    private final transient HttpServerRequest request;
    private final transient HttpServerResponse response;

    private HttpPump(final RoutingContext context,
                     final ServiceReference reference,
                     final Record record) {
        this.context = context;
        /*
         * Extract request / response from context
         */
        this.request = context.request();
        this.response = context.response();
        this.reference = reference;
        this.record = record;
    }

    public static HttpPump create(final RoutingContext context,
                                  final ServiceReference reference,
                                  final Record record) {
        return new HttpPump(context, reference, record);
    }


    @Override
    public void doRequest(final Handler<AsyncResult<HttpClientResponse>> handler) {
        /*
         * Init Http Request Client, the reference came from service discovery
         * If the client came from service reference, the port, host have been set
         * and stored in ServiceReference instance.
         */
        final HttpClient client = this.reference.getAs(HttpClient.class);
        final String uri = InOut.normalizeUri(this.context);
        /*
         * To avoid absolute URI because of service reference discovery result
         * Build options with record that discoveried here.
         */
        final RequestOptions options = InOut.getOptions(this.record, uri);
        final HttpClientRequest request = client.request(this.request.method(), options);
        
    }
}
