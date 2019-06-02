package cn.vertxup.api;

import io.vertx.core.Future;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.up.aiki.Ux;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;

@Queue
public class DeleteActor {
    /*
     * DELETE: /api/{actor}/{key}
     *     200: Delete existing record
     *     204: Second deleting, The record has been gone
     */
    @Address(Addr.Delete.BY_ID)
    public Future<Envelop> delete(final Envelop request) {
        return Ix.create(this.getClass()).input(request).envelop((dao, config) -> {
            /* Key */
            final String key = Ux.getString1(request);
            return dao.findByIdAsync(key).compose(result -> null == result ?
                    /* 204 */
                    Http.success204(Boolean.TRUE) :
                    /* 200 */
                    dao.deleteByIdAsync(key).compose(Http::success200));
        });
    }
}
