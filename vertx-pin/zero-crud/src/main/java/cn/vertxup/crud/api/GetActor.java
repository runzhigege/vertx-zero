package cn.vertxup.crud.api;

import io.vertx.core.Future;
import io.vertx.tp.crud.actor.IxActor;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.tp.optic.ApeakMy;
import io.vertx.tp.optic.Pocket;
import io.vertx.up.unity.Ux;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;

@Queue
public class GetActor {
    /*
     * GET: /api/{actor}/{key}
     *     200: Query Data
     *     204: Query No Data
     */
    @Address(Addr.Get.BY_ID)
    public Future<Envelop> getById(final Envelop request) {
        return Ix.create(getClass()).input(request).envelop((dao, config) -> {
            /* Key */
            final String key = Ux.getString1(request);
            return dao.findByIdAsync(key)
                    .compose(queried -> null == queried ?
                            /* 204 */
                            Http.success204(queried) :
                            /* 200 */
                            Http.success200(queried));
        });
    }

    /*
     * GET: /api/columns/{actor}/full
     */
    @Address(Addr.Get.COLUMN_FULL)
    @SuppressWarnings("all")
    public Future<Envelop> getFull(final Envelop request) {
        return Ix.create(this.getClass()).input(request).envelop(
                /* Search full column and it will be used in another method */
                (dao, config) -> Unity.fetchFull(dao, request, config)
                        /* Result Wrapper to Envelop */
                        .compose(Http::success200));
    }

    /*
     * GET: /api/columns/{actor}/my
     */
    @Address(Addr.Get.COLUMN_MY)
    @SuppressWarnings("all")
    public Future<Envelop> getMy(final Envelop request) {
        return Ix.create(this.getClass()).input(request).envelop((dao, config) -> {
            /* Get Stub */
            final ApeakMy stub = Pocket.lookup(ApeakMy.class);
            return Unity.safeCall(stub, () -> Unity.fetchView(dao, request, config)
                    /* View parameters filling */
                    .compose(input -> IxActor.view().procAsync(input, config))
                    /* Uri filling, replace inited information: uri , method */
                    .compose(input -> IxActor.uri().bind(request).procAsync(input, config))
                    /* User filling */
                    .compose(input -> IxActor.user().bind(request).procAsync(input, config))
                    /* Fetch My Columns */
                    .compose(stub.on(dao)::fetchMy)
                    /* Return Result */
                    .compose(Http::success200)
            );
        });
    }
}
