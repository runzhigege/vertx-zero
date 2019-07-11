package cn.vertxup.crud.api;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
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
        return Ix.create(getClass()).input(request).envelop((dao, config) -> {
            /* Key */
            final String key = Ux.getString1(request);
            return dao.findByIdAsync(key).compose(result -> null == result ?
                    /* 204 */
                    Http.success204(Boolean.TRUE) :
                    /* 200 */
                    dao.deleteByIdAsync(key).compose(Http::success200));
        });
    }

    /*
     * DELETE: /api/batch/{actor}/delete
     *     200: Delete existing records
     *     204: Second deleting, The records have been gone
     */
    @Address(Addr.Delete.BATCH)
    public Future<Envelop> deleteBatch(final Envelop request) {
        return Ix.create(getClass()).input(request).envelop((dao, config) -> {
            /* Keys */
            final JsonArray keys = Ux.getArray1(request);
            /* ID */
            return Ix.inKeys(keys, config)
                    /* List */
                    .compose(dao::deleteAsync)
                    /* Boolean */
                    .compose(Http::success200);
        });
    }
}
