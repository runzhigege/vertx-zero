package cn.vertxup.api;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.tp.crud.tool.Ix;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.log.Annal;

@Queue
public class CreateActor {
    private static final Annal LOGGER = Annal.get(CreateActor.class);

    @Address(Addr.Post.ADD)
    public Future<JsonObject> create(
            final String actor,
            final JsonObject body
    ) {
        LOGGER.info("[ Εκδήλωση ] Request : POST /api/{0}", actor);
        return Ix.toSingle(actor, (dao) -> dao.insertAsync(Ix.inJson(actor, body)));
    }
}
