package cn.vertxup.crud.api;

import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.actor.IxActor;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.optic.Apeak;
import io.vertx.tp.optic.Seeker;
import io.vertx.up.commune.Envelop;
import io.vertx.up.unity.Ux;
import io.vertx.up.unity.UxJooq;

import java.text.MessageFormat;

class Unity {

    /*
     * Seeker for lookup target resource
     * 1. Provide uri, method to find target resource of personal
     * 2. Sigma from header
     * 3. Find impact resourcedId that will be related to view.
     */
    static Future<JsonObject> fetchView(final UxJooq dao, final Envelop request, final IxModule config) {
        /* init parameters */
        final JsonObject params = Unity.initMy(request);
        return Ke.channel(Seeker.class, JsonObject::new, seeker -> Ux.future(params)
                /* Header */
                .compose(input -> IxActor.header().bind(request).procAsync(input, config))
                /* Fetch Impact */
                .compose(seeker.on(dao)::fetchImpact));
    }

    static Future<JsonArray> fetchFull(final UxJooq dao, final Envelop request, final IxModule config) {
        /* Get Stub */
        return Ke.channel(Apeak.class, JsonArray::new, stub -> IxActor.start()
                /* Apeak column definition here */
                .compose(input -> IxActor.apeak().bind(request).procAsync(input, config))
                /* Header */
                .compose(input -> IxActor.header().bind(request).procAsync(input, config))
                /* Fetch Full Columns */
                .compose(stub.on(dao)::fetchFull));
    }

    /*
     * This method is for uniform safeCall for Future<JsonArray> returned
     * It's shared by
     * /api/columns/{actor}/full
     * /api/columns/{actor}/my
     * Because all of above api returned JsonArray of columns on model
     */
/*    static <T> Future<Envelop> safeCall(final T stub, final Supplier<Future<Envelop>> executor) {
        if (Objects.isNull(stub)) {
            return Ux.future(new JsonArray()).compose(IxHttp::success200);
        } else {
            return executor.get();
        }
    }*/

    /*
     * Uri, Method instead
     * This method is only for save my columns, it provided fixed impact uri for clean cache
     * 1) Save my columns
     * 2) Clean up impact uri about cache flush
     */
    static JsonObject initMy(final Envelop envelop) {
        final String pattern = "/api/{0}/search";
        final String actor = Ux.getString(envelop);
        return new JsonObject()
                .put(KeField.URI, MessageFormat.format(pattern, actor))
                .put(KeField.METHOD, HttpMethod.POST.name());
    }
}
