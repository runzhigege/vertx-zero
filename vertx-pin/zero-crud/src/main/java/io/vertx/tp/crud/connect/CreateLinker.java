package io.vertx.tp.crud.connect;

import cn.vertxup.crud.api.IxHub;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxJoin;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.tp.crud.init.IxPin;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.commune.Envelop;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;
import io.vertx.up.unity.UxJooq;
import io.vertx.up.util.Ut;

import java.util.Objects;

/*
 * Linker creation
 */
class CreateLinker implements IxLinker {
    private static final Annal LOGGER = Annal.get(CreateLinker.class);

    @Override
    public Future<Envelop> procAsync(final Envelop request, final JsonObject data, final IxModule module) {
        /*
         * Linker data preparing
         */
        final IxJoin connect = module.getConnect();
        if (Objects.isNull(connect)) {
            /*
             * IxJoin null, could not identify connect
             */
            Ix.infoDao(LOGGER, "IxJoin is null");
            return Ux.toFuture(request);
        } else {
            final String moduleName = connect.getJoinedBy();
            if (Ut.isNil(moduleName)) {
                Ix.infoDao(LOGGER, "The `joinedBy` field is null");
                return Ux.toFuture(request);
            } else {
                final String identifier = data.getString(moduleName);
                /*
                 * Get the data of module, data -> `moduleName` value
                 */
                final IxModule config = connect.getModule(identifier);
                if (Objects.isNull(config)) {
                    Ix.infoDao(LOGGER, "System could not find configuration for `{0}`, data = {1}",
                            identifier, connect.getJoined());
                    return Ux.toFuture(request);
                } else {
                    final UxJooq dao = IxPin.getDao(config);
                    /*
                     * data is response data, here the code will generate final response
                     */
                    final String joinedKey = data.getString(connect.getMappedBy());
                    final JsonObject inputData = data.copy();
                    /*
                     * Remove primary key, it will generate new.
                     */
                    inputData.remove(module.getField().getKey());
                    final String mapped = connect.getMapped(identifier);
                    if (Ut.notNil(mapped)) {
                        inputData.put(mapped, joinedKey);
                    }
                    return IxHub.createAsync(request, inputData, dao, config)
                            .compose(response -> {
                                final JsonObject createdJoined = response.data();
                                /*
                                 * Merged two data here,
                                 * Be careful is that we must overwrite createdJoined
                                 * instead of data because the original data must be keep
                                 * Here are some modification of `key` here.
                                 * Here provide `joinedKey` field for target object.
                                 */
                                final String joinedField = config.getField().getKey();
                                createdJoined.put(KeField.JOINED_KEY, createdJoined.getString(joinedField));
                                createdJoined.mergeIn(data, true);
                                return Ux.toFuture(Envelop.success(createdJoined));
                            });
                }
            }
        }
    }
}
