package io.vertx.up.micro.discovery.multipart;

import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.http.*;
import io.vertx.core.streams.Pump;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceReference;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.discovery.InOut;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

public class HttpPipe implements Pipe<HttpClientResponse> {

    private static final Annal LOGGER = Annal.get(HttpPipe.class);

    private final transient RoutingContext context;
    private final transient ServiceReference reference;
    private final transient Record record;
    private final transient RequestOptions options;
    // Secondary reference
    private final transient HttpServerRequest request;
    private final transient HttpServerResponse response;

    private HttpPipe(final RoutingContext context,
                     final ServiceReference reference,
                     final Record record,
                     final RequestOptions options) {
        this.context = context;
        /*
         * Extract request / response from context
         */
        this.request = context.request();
        this.response = context.response();
        this.reference = reference;
        this.record = record;
        this.options = options;
    }

    public static HttpPipe create(final RoutingContext context,
                                  final ServiceReference reference,
                                  final Record record,
                                  final RequestOptions options) {
        return new HttpPipe(context, reference, record, options);
    }


    @Override
    public void doRequest(final Handler<HttpClientResponse> handler) {
        /*
         * Only support post method in uploading HttpPipe
         */
        if (HttpMethod.POST == this.request.method()) {
            /*
             * Init Http Request Client, the reference came from service discovery
             * If the client came from service reference, the port, host have been set
             * and stored in ServiceReference instance.
             */
            final HttpClient client = this.reference.getAs(HttpClient.class);
            /*
             * To avoid absolute URI because of service reference discovery result
             * Build options with record that discoveried here.
             */
            final HttpClientRequest request = client.request(this.request.method(), this.options);
            request.handler(handler);
            /*
             * Header processing
             */
            this.processHeaders(request);
            /*
             * Callback handler for request
             */

            /*
             * https://github.com/vert-x/vertx-examples/blob/master/src/raw/java/upload/UploadClient.java
             * Preparing for uploading
             */
            final FileUpload fileUpload = this.getFile();
            if (null == fileUpload) {
                /*
                 * It means that the system met error when multipart request happened.
                 */
                InOut.sync500Error(this.getClass(), this.context, new RuntimeException("Upload file missing..."));
            } else {
                /*
                 * Execute uploading processing
                 */
                this.executeUpload(request, fileUpload);
            }
        } else {
            InOut.sync405Error(this.getClass(), this.context);
        }
    }

    private void executeUpload(final HttpClientRequest request, final FileUpload fileUpload) {
        final String filename = fileUpload.uploadedFileName();
        try {
            /*
             * Set Content-Length in http request
             */
            final String length = String.valueOf(Files.size(Paths.get(filename)));
            request.putHeader(HttpHeaders.CONTENT_LENGTH, length);

            final Vertx vertx = this.context.vertx();
            vertx.fileSystem().open(filename, new OpenOptions(), handler -> {
                if (handler.succeeded()) {
                    /*
                     * Get async file that uploaded to api gateway temp path
                     */
                    final AsyncFile file = handler.result();
                    final Pump pump = Pump.pump(file, request);
                    pump.start();

                    file.endHandler(end -> file.close(closed -> {
                        if (closed.succeeded()) {
                            request.end();
                        } else {
                            InOut.sync500Error(this.getClass(), this.context, closed.cause());
                        }
                    }));
                } else {
                    InOut.sync500Error(this.getClass(), this.context, handler.cause());
                }
            });
        } catch (final Exception ex) {

            InOut.sync500Error(this.getClass(), this.context, ex);
        }
    }

    private void processHeaders(final HttpClientRequest request) {
        /*
         * Extract Server Request headers
         */
        final MultiMap headers = MultiMap.caseInsensitiveMultiMap();
        this.request.headers().forEach(entry -> headers.set(entry.getKey(), entry.getValue()));
        /*
         * User Agent Switch, Fix value
         */
        headers.set(HttpHeaders.USER_AGENT, "Zero Internal Client Agent");
        headers.forEach(entry -> LOGGER.info("[ ZERO ] Normalized headers: key = {0}, value = {1}",
                entry.getKey(), entry.getValue()));
        request.headers().setAll(headers);
    }

    private FileUpload getFile() {
        final Set<FileUpload> fileUploads = this.context.fileUploads();
        if (fileUploads.isEmpty()) {
            return null;
        } else {
            return fileUploads.iterator().next();
        }
    }
}
