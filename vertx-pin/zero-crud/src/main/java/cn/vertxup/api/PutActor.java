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
public class PutActor {

    private static final Annal LOGGER = Annal.get(PutActor.class);

    @Address(Addr.Put.BY_ID)
    public <T> Future<JsonObject> update(final Envelop request) {
        /* Module and Key Extract  */
        final String actor = Ux.getString(request);
        final String key = Ux.getString1(request);
        LOGGER.info("[ Εκδήλωση ] ---> Uri Addr : PUT /api/{0}/{1}", actor, key);
        return Ix.toSingle(actor, (dao) -> {
            /*
             * Here must split into single line
             */
            final T updated = Ix.inEdit(actor, request);
            /*
             * saveAsync method
             */
            return dao.saveAsync(key, updated);
        });
    }
}
