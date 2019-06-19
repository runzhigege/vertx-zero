package cn.vertxup.api;

import io.vertx.core.Future;
import io.vertx.tp.crud.actor.IxActor;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.up.aiki.Uson;
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
    public Future<Envelop> getFull(final Envelop request) {
        return Ix.create(this.getClass()).input(request).envelop((dao, config) -> {
            /* Get Stub */
            // final ColSub stub = IxStub.getStub().on(dao).on(config);
            return Ix.inColumns(request, config)
                    /* Header */
                    .compose(input -> IxActor.header().bind(request).procAsync(input, config))
                    /* Remove User Filters */
                    .compose(filters -> Uson.create(filters).remove("user").toFuture())
                    /* Fetch Full Columns */
                    .compose(Ux::debug)
                    // .compose(stub::fetchFullColumns)
                    /* Return Result */
                    .compose(Http::success200);
        });
    }

    /*
     * GET: /api/columns/my/{actor}
     */
    @Address(Addr.Get.COLUMN_MY)
    public Future<Envelop> getMy(final Envelop request) {
        return Ix.create(this.getClass()).input(request).envelop((dao, config) -> {

            return null;
        });
    }
}
