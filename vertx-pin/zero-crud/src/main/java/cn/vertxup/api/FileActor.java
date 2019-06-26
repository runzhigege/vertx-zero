package cn.vertxup.api;

import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;

@Queue
public class FileActor {

    @Address(Addr.File.IMPORT)
    public Future<JsonObject> importFile(final Envelop envelop) {

        return Future.succeededFuture();
    }

    @Address(Addr.File.EXPORT)
    public Future<Buffer> exportFile(final Envelop envelop) {

        return Future.succeededFuture();
    }
}
