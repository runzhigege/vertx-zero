package cn.vertxup.api;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.tp.crud.tool.Ix;
import io.vertx.up.aiki.Ux;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;
import io.vertx.up.log.Annal;

@Queue
public class CreateActor {
    private static final Annal LOGGER = Annal.get(CreateActor.class);

    @Address(Addr.Post.ADD)
    public <T> Future<JsonObject> create(final Envelop request) {
        /* Module的提取 */
        final String actor = Ux.getString(request);
        LOGGER.info("[ Εκδήλωση ] Uri Addr : POST /api/{0}", actor);
        final T inserted = Ix.inAdd(actor, request);
        return Ix.toSingle(actor, (dao) -> dao.insertAsync(inserted));
    }
}
