package up.god.file;

import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;
import up.god.cv.Addr;

@Queue
public class UploadWorker {

    private static final Annal LOGGER = Annal.get(UploadWorker.class);

    @Address(Addr.App.ATTACH_DOWNLOAD)
    public Future<Buffer> download(final JsonObject fileObj) {
        LOGGER.info("下载参数：key = {0}", fileObj.encodePrettily());

        return Future.succeededFuture(Ut.ioBuffer("file-uploads/937c8fbd-2049-44bf-93f4-7e53237fdc3c"));
    }
}
