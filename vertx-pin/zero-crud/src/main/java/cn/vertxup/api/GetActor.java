package cn.vertxup.api;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.tp.crud.actor.IxActor;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.tp.crud.init.IxPin;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.tp.ke.extension.ui.ColumnStub;
import io.vertx.tp.ke.extension.view.ColumnMyStub;
import io.vertx.up.aiki.Ux;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;

import java.util.Objects;
import java.util.function.Supplier;

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
            final ColumnStub stub = IxPin.getStub();
            return this.getUniform(stub, () -> Ix.inColumns(request, config)
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
            final ColumnMyStub stub = IxPin.getMyStub();
            return this.getUniform(stub, () -> Ix.inColumns(request, config)
                    /* Header */
                    .compose(input -> IxActor.header().bind(request).procAsync(input, config))
                    /* Fetch My Columns */
                    .compose(stub.on(dao)::fetchMy)
                    /* Return Result */
                    .compose(Http::success200));
        });
    }

    private <T> Future<Envelop> getUniform(final T stub, final Supplier<Future<Envelop>> executor) {
        /* If null */
        if (Objects.isNull(stub)) {
            /* No thing return from this interface */
            return Ux.toFuture(new JsonArray())
                    /* Return Result */
                    .compose(Http::success200);
        } else {
            return executor.get();
        }
    }
}
