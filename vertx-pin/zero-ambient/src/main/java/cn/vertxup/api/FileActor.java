package cn.vertxup.api;

import cn.vertxup.ambient.tables.daos.XAttachmentDao;
import cn.vertxup.ambient.tables.pojos.XAttachment;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ambient.cv.Addr;
import io.vertx.tp.ambient.cv.AtMsg;
import io.vertx.tp.ambient.refine.At;
import io.vertx.up.aiki.Ux;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;

import java.util.Objects;

@Queue
public class FileActor {

    private static final Annal LOGGER = Annal.get(FileActor.class);

    @Address(Addr.File.UPLOAD)
    public Future<JsonObject> upload(final JsonObject content) {
        At.infoFile(LOGGER, AtMsg.FILE_UPLOAD, content.encodePrettily());
        final XAttachment attachment = Ut.deserialize(content, XAttachment.class);
        return Ux.Jooq.on(XAttachmentDao.class)
                .insertAsync(attachment)
                .compose(Ux::fnJObject);
    }

    @Address(Addr.File.DOWNLOAD)
    public Future<Buffer> download(final JsonObject filters) {
        At.infoFile(LOGGER, AtMsg.FILE_DOWNLOAD, filters.encodePrettily());
        return Ux.Jooq.on(XAttachmentDao.class)
                .<XAttachment>fetchOneAndAsync(filters)
                .compose(entity -> {
                    Buffer buffer = Buffer.buffer();
                    if (Objects.nonNull(entity)) {
                        buffer = Ut.ioBuffer(entity.getFilePath());
                    }
                    return Ux.toFuture(buffer);
                });
    }
}
