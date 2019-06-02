package io.vertx.tp.crud.tool;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.Envelop;
import io.vertx.up.log.Annal;

class IxQuery {
    private static final Annal LOGGER = Annal.get(IxPojo.class);

    /*
     * key = <value>
     */
    static JsonObject inKey(final String module, final Envelop envelop) {
        final IxConfig config = IxDao.get(module);
        /* Key extract */
        final String keyField = config.getField().getKey();
        final String keyValue = Ux.getString1(envelop);
        final JsonObject filters = new JsonObject();
        filters.put(keyField, keyValue);
        LOGGER.info("[ Εκδήλωση ] Key Filters : {0}", filters);
        return filters;
    }
}
