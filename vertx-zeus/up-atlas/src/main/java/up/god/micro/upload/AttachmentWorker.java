package up.god.micro.upload;

import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.log.Annal;

@Queue
public class AttachmentWorker {
    private static final Annal LOGGER = Annal.get(AttachmentWorker.class);

    @Address("ZERO://FILE/UPLOAD")
    public Future<JsonObject> upload(final JsonObject content) {
        LOGGER.info("传入上传参数：{0}", content.encodePrettily());
        return Future.succeededFuture(new JsonObject());
    }

    @Address("ZERO://FILE/DOWNLOAD")
    public Future<Buffer> download(final JsonObject fileObj) {
        LOGGER.info("下载参数：key = {0}", fileObj.encodePrettily());
        return Future.succeededFuture(Buffer.buffer());
    }
}
