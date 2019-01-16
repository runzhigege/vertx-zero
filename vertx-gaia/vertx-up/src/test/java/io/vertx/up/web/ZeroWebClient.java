package io.vertx.up.web;

import io.vertx.core.buffer.Buffer;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.client.methods.ZeroCopyConsumer;
import org.apache.http.nio.client.methods.ZeroCopyPost;

import java.io.File;
import java.util.concurrent.Future;

public class ZeroWebClient {

    public void testClient() throws Exception {
        final CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();
        final File file = new File("data-test");
        if (file.exists()) {
            client.start();
            final Buffer buffer = Buffer.buffer();
            final ZeroCopyPost httpost = new ZeroCopyPost(
                    "http://localhost:6201/api/attachment/upload/hotel", file,
                    ContentType.create("application/octet-stream"));
            final ZeroCopyConsumer<Buffer> consumer = new ZeroCopyConsumer<Buffer>(file) {
                @Override
                protected Buffer process(final HttpResponse httpResponse,
                                         final File file, final ContentType contentType) throws Exception {
                    return null;
                }
            };
            final Future<Buffer> future = client.execute(httpost, consumer, null);
            future.get();
        }
    }
}
