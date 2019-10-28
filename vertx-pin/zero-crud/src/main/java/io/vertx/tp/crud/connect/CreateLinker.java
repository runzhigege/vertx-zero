package io.vertx.tp.crud.connect;

import cn.vertxup.crud.api.IxHub;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxJoin;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.up.commune.Envelop;
import io.vertx.up.log.Annal;
import io.vertx.up.util.Ut;

/*
 * Linker creation
 */
class CreateLinker implements IxLinker {
    private static final Annal LOGGER = Annal.get(CreateLinker.class);

    @Override
    public Future<Envelop> procAsync(final Envelop request, final JsonObject data, final IxModule module) {

        return OxSwitcher.moveOn(data, module, (dao, config) -> {
            /*
             * Safe call because of MoveOn
             */
            final IxJoin connect = module.getConnect();
            /*
             * Remove primary key, it will generate new.
             */
            final JsonObject inputData = data.copy();
            inputData.remove(module.getField().getKey());
            final String mapped = connect.getJoined(data);
            if (Ut.notNil(mapped)) {
                /*
                 * data is response data, here the code will generate final response
                 */
                final String joinedValue = data.getString(connect.getMappedBy());
                inputData.put(mapped, joinedValue);
            }
            return IxHub.createAsync(request, inputData, dao, config)
                    .compose(response -> OxSwitcher.moveEnd(data, response, config));
        });
    }
}
