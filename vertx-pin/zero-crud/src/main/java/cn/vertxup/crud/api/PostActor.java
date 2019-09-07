package cn.vertxup.crud.api;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.actor.IxActor;
import io.vertx.tp.crud.cv.Addr;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.commune.Envelop;
import io.vertx.up.unity.Ux;

/*
 * Create new Record that defined in zero system.
 * The definition file are stored under
 *      `plugin/crud/module/`
 * The validation rule file are stored under
 *      `plugin/crud/validator/`
 */
@Queue
public class PostActor {

    /*
     * POST: /api/{actor}
     *     200: Created new record
     *     201: The record existing in database ( Could not do any things )
     */
    @Address(Addr.Post.ADD)
    public Future<Envelop> create(final Envelop request) {
        /* Actor Extraction */
        return Ix.create(this.getClass()).input(request).envelop((dao, config) -> {
            /* Data Get */
            final JsonObject body = Ux.getJson1(request);
            return Ux.toFuture(body)
                    /* Header */
                    .compose(input -> IxActor.header().bind(request).procAsync(input, config))
                    /* Verify */
                    .compose(input -> IxActor.verify().bind(request).procAsync(input, config))
                    /* Unique Filters */
                    .compose(input -> IxActor.unique().procAsync(input, config))
                    /* Filters */
                    .compose(filters -> Ix.search(filters, config).apply(dao))
                    /* Unique Extract from { list, count } */
                    .compose(result -> Ix.isExist(result) ?
                            /* Unique */
                            Ix.unique(result)
                                    /* Deserialize */
                                    .compose(json -> Ix.entityAsync(json, config))
                                    /* 201, Envelop */
                                    .compose(entity -> Http.success201(entity, config)) :
                            /* Primary Key Add */
                            IxActor.uuid().procAsync(body, config)
                                    /* Create */
                                    .compose(input -> IxActor.create().bind(request).procAsync(input, config))
                                    /* Update */
                                    .compose(input -> IxActor.update().bind(request).procAsync(input, config))
                                    /* Build Data */
                                    .compose(input -> Ix.entityAsync(input, config))
                                    /* T */
                                    .compose(dao::insertAsync)
                                    /* 200, Envelop */
                                    .compose(entity -> Http.success200(entity, config))
                    );
        });
    }
}
