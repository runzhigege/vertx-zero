package cn.vertxup.api;

import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.actor.IxActor;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.optic.Pocket;
import io.vertx.tp.optic.Seeker;
import io.vertx.up.aiki.Ux;
import io.vertx.up.aiki.UxJooq;
import io.vertx.up.atom.Envelop;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.function.Supplier;

class Unity {

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

    /*
     * Uri, Method instead
     */
    private static JsonObject initMy(final Envelop envelop) {
        final String pattern = "/api/{0}/search";
        final String actor = Ux.getString(envelop);
        return new JsonObject()
                .put(KeField.URI, MessageFormat.format(pattern, actor))
                .put(KeField.METHOD, HttpMethod.POST.name());
    }

    static Future<JsonObject> seeker(final UxJooq dao, final Envelop request, final IxModule config) {
        /* Get Seeker */
        final Seeker seeker = Pocket.lookup(Seeker.class);
        /* init parameters */
        final JsonObject params = Unity.initMy(request);
        return Ux.toFuture(params)
                /* Header */
                .compose(input -> IxActor.header().bind(request).procAsync(input, config))
                /* Fetch Impact */
                .compose(seeker.on(dao)::fetchImpact);
    }
}
