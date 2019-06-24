package cn.vertxup.api;

import io.vertx.core.Future;
import io.vertx.tp.crud.actor.IxActor;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.tp.crud.init.IxPin;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.tp.ke.extension.jooq.Apeak;
import io.vertx.tp.ke.extension.jooq.ApeakMy;
import io.vertx.up.aiki.Ux;
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
        return Ix.create(this.getClass()).input(request).envelop((dao, config) -> {
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
     * GET: /api/columns/full/{actor}
     */
    @Address(Addr.Get.COLUMN_FULL)
    @SuppressWarnings("all")
    public Future<Envelop> getFull(final Envelop request) {
        return Ix.create(this.getClass()).input(request).envelop((dao, config) -> {
            /* Get Stub */
            final Apeak stub = IxPin.getStub();
            return Uniform.call(stub, () -> Ix.inColumns(request, config)
                    /* Header */
                    .compose(input -> IxActor.header().bind(request).procAsync(input, config))
                    /* Fetch Full Columns */
                    .compose(stub.on(dao)::fetchFull)
                    /* Return Result */
                    .compose(Http::success200));
        });
    }

    /*
     * GET: /api/columns/my/{actor}
     */
    @Address(Addr.Get.COLUMN_MY)
    @SuppressWarnings("all")
    public Future<Envelop> getMy(final Envelop request) {
        return Ix.create(this.getClass()).input(request).envelop((dao, config) -> {
            /* Get Stub */
            final ApeakMy stub = IxPin.getMyStub();
            return Uniform.call(stub, () -> Uniform.seeker(dao, request, config)
                    /* Fetch My Columns */
                    .compose(stub.on(dao)::fetchMy)
                    /* Return Result */
                    .compose(Http::success200));
        });
    }
}
