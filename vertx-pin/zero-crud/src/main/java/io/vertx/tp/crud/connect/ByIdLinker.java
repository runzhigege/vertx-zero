package io.vertx.tp.crud.connect;

import cn.vertxup.crud.api.IxHttp;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxJoin;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.commune.Envelop;
import io.vertx.up.util.Ut;

/*
 * Get by id linker for Get uri
 */
class ByIdLinker implements IxLinker {
    @Override
    public Future<Envelop> procAsync(final Envelop data, final JsonObject original, final IxModule module) {
        return OxSwitcher.moveOn(original, module, (dao, config) -> {
            /*
             * Safe call because of MoveOn
             */
            final JsonObject filters = new JsonObject();
            final IxJoin connect = module.getConnect();
            final String mapped = connect.getJoined(original);
            if (Ut.notNil(mapped)) {
                /*
                 * joinedValue
                 */
                final String joinedValue = original.getString(connect.getMappedBy());
                filters.put(mapped, joinedValue);
            }
            /*
             * Append `Sigma` Here
             */
            if (original.containsKey(KeField.SIGMA)) {
                filters.put("", Boolean.TRUE);
                filters.put(KeField.SIGMA, original.getString(KeField.SIGMA));
            }
            return dao.fetchOneAsync(filters)
                    .compose(queried -> null == queried ?
                            /* 204 */
                            IxHttp.success204(queried) :
                            /* 200 */
                            IxHttp.success200(queried, config))
                    .compose(response -> OxSwitcher.moveEnd(original, response, config));
        });
    }
}
