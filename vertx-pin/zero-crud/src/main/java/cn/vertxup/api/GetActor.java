package cn.vertxup.api;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.tp.crud.tool.Ix;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.log.Annal;

@Queue
public class GetActor {
    private static final Annal LOGGER = Annal.get(GetActor.class);

    @Address(Addr.Get.BY_ID)
    public Future<JsonObject> getById(final String actor,
                                      final String key) {
        LOGGER.info("[ Εκδήλωση ] Uri Addr : GET /api/{0}/{1}", actor, key);
        return Ix.toSingle(actor, (dao) -> dao.findByIdAsync(key));
    }
}
