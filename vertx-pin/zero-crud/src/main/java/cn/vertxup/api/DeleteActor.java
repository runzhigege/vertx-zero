package cn.vertxup.api;

import io.vertx.core.Future;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.tp.crud.tool.Ix;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.log.Annal;

@Queue
public class DeleteActor {
    private static final Annal LOGGER = Annal.get(DeleteActor.class);

    @Address(Addr.Delete.BY_ID)
    public Future<Boolean> delete(final String actor,
                                  final String key) {
        LOGGER.info("[ Εκδήλωση ] ---> Uri Addr : DELETE /api/{0}/{1}", actor, key);
        return Ix.toBoolean(actor, (dao) -> dao.deleteByIdAsync(key));
    }
}
