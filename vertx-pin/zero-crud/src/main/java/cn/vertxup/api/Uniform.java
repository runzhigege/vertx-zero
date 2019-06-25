package cn.vertxup.api;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.actor.IxActor;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.tp.optic.Pocket;
import io.vertx.tp.optic.Seeker;
import io.vertx.up.aiki.Ux;
import io.vertx.up.aiki.UxJooq;
import io.vertx.up.atom.Envelop;

import java.util.Objects;
import java.util.function.Supplier;

class Uniform {
    /*
     * Uniform call stub
     */
    static <T> Future<Envelop> call(final T stub, final Supplier<Future<Envelop>> executor) {
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

    static Future<JsonObject> seeker(final UxJooq dao, final Envelop request, final IxModule config) {
        /* Seeker Stub */
        final Seeker seeker = Pocket.lookup(Seeker.class);
        return Ix.inSeekers(request)
                /* Header */
                .compose(input -> IxActor.header().bind(request).procAsync(input, config))
                /* Fetch Impact Resource */
                .compose(seeker.on(dao)::fetchImpact)
                /* Column Fetch */
                .compose(result -> Ix.inColumns(request, config, result));
    }
}
