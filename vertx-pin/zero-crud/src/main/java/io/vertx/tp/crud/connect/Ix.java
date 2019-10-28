package io.vertx.tp.crud.connect;

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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;

interface Pool {
    ConcurrentMap<String, IxLinker> LINKER_MAP =
            new ConcurrentHashMap<>();
}

interface OxSwitcher {

    static Future<Envelop> moveOn(final JsonObject data,
                                  final IxModule module,
                                  final BiFunction<UxJooq, IxModule, Future<Envelop>> function) {
        /*
         * Linker data preparing
         */
        final IxJoin connect = module.getConnect();
        final Annal LOGGER = Annal.get(OxSwitcher.class);
        if (Objects.isNull(connect)) {
            /*
             * IxJoin null, could not identify connect
             */
            Ix.infoDao(LOGGER, "IxJoin is null");
            return Ux.toFuture(Envelop.success(data));
        } else {
            final String moduleName = connect.getJoinedBy();
            if (Ut.isNil(moduleName)) {
                Ix.infoDao(LOGGER, "The `joinedBy` field is null");
                return Ux.toFuture(Envelop.success(data));
            } else {
                final String identifier = data.getString(moduleName);
                /*
                 * Get the data of module, data -> `moduleName` value
                 */
                final IxModule config = connect.getModule(identifier);
                if (Objects.isNull(config)) {
                    Ix.infoDao(LOGGER, "System could not find configuration for `{0}`, data = {1}",
                            identifier, connect.getJoined());
                    return Ux.toFuture(Envelop.success(data));
                } else {
                    final UxJooq dao = IxPin.getDao(config);
                    return function.apply(dao, config);
                }
            }
        }
    }

    static Future<Envelop> moveEnd(final JsonObject original, final Envelop response,
                                   final IxModule config) {
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
        createdJoined.mergeIn(original, true);
        return Ux.toFuture(Envelop.success(createdJoined));
    }
}
