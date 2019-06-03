package cn.vertxup.api;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.actor.IxActor;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.up.aiki.Ux;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;
import io.vertx.up.log.Annal;

@Queue
public class PutActor {

    private static final Annal LOGGER = Annal.get(PutActor.class);

    @Address(Addr.Put.BY_ID)
    public <T> Future<Envelop> update(final Envelop request) {
        /* Module and Key Extract  */
        return Ix.create(this.getClass()).input(request).envelop((dao, config) -> {
            /* Data Get */
            final JsonObject body = Ux.getJson2(request);
            final String key = Ux.getString1(request);
            return dao.findByIdAsync(key).compose(queried -> null == queried ?
                    /* 204, No Content */
                    Http.success204(null) :
                    /* Save */
                    IxActor.key().bind(request).procAsync(body, config)
                            /* Verify */
                            .compose(input -> IxActor.verify().bind(request).procAsync(input, config))
                            /* T */
                            .compose(input -> Ix.entityAsync(input, config))
                            /* Save */
                            .compose(entity -> dao.saveAsync(key, entity))
                            /* 200, Envelop */
                            .compose(Http::success200)
            );
        });
    }

    @Address(Addr.Put.BATCH)
    public <T> Future<Envelop> updateBatch(final Envelop request) {
        /* Batch Extract */
        return Ix.create(this.getClass()).input(request).envelop((dao, config) -> {
            /* Data Get */
            final JsonArray array = Ux.getArray1(request);
            return Ix.inKeys(array, config)
                    /* Search List */
                    .compose(filters -> Ix.search(filters, config).apply(dao))
                    /* Extract List */
                    .compose(Ix::list)
                    /* JsonArray */
                    .compose(queried -> Ix.zipperAsync(queried, array, config))
                    /* JsonArray */
                    .compose(dataArr -> Ix.entityAsync(dataArr, config))
                    /* List<T> */
                    .compose(dao::updateAsync)
                    /* JsonArray */
                    .compose(Http::success200);
        });
    }
}
