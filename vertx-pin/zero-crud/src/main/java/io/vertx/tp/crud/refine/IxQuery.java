package io.vertx.tp.crud.refine;

import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.tp.crud.atom.IxColumn;
import io.vertx.tp.crud.atom.IxField;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.Envelop;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;

import java.util.Objects;

class IxQuery {

    static JsonObject inKeys(final JsonArray array, final IxModule config) {
        final IxField field = config.getField();
        final String keyField = field.getKey();
        /* Filters */
        final JsonObject filters = new JsonObject();
        final JsonArray keys = new JsonArray();
        array.stream().filter(Objects::nonNull).forEach((item) -> {
            if (JsonObject.class == item.getClass()) {
                /* keyValue */
                final String keyValue = ((JsonObject) item).getString(keyField);
                if (Ut.notNil(keyValue)) {
                    keys.add(keyValue);
                }
            } else {
                keys.add(item);
            }
        });
        /* Filters */
        return filters.put(keyField + ",i", keys);
    }

    static JsonObject inColumns(final Envelop envelop, final IxModule module) {
        final String actor = Ux.getString(envelop);
        /* Filters */
        final JsonObject params = new JsonObject();
        /* Extract column information to params */
        {
            final IxColumn column = module.getColumn();
            /*
             * In static mode, identifier could found ui file
             * In dynamic mode, identifier & sigma could let system fetch columns
             * from database directly.
             * Here add new parameter `view` for future usage to support multi views
             */
            params.put(KeField.IDENTIFIER, column.getIdentifier());
            params.put(KeField.DYNAMIC, column.getDynamic());
            params.put(KeField.VIEW, column.getView());
            /*
             * User principal data will be passed into params
             * Extract data here for data passing.
             */
            final User user = envelop.user();
            Fn.safeNull(() -> params.put(KeField.PRINCIPLE, user.principal()), user);
            /*
             * Actor params
             */
            params.put(KeField.ACTOR, actor);
        }
        return params;
    }

    /*
     * TODO: Reserved for future use
     */
    static JsonObject input(final Envelop envelop) {
        final JsonObject data = new JsonObject();
        /* Data */
        final User user = envelop.user();
        if (null != user) {
            final JsonObject userData = user.principal();
            if (Objects.nonNull(userData)) {
                data.mergeIn(userData);
            }
        }
        /* Header */
        final MultiMap header = envelop.headers();
        header.forEach(entry -> data.put(entry.getKey(), entry.getValue()));
        return data;
    }
}
