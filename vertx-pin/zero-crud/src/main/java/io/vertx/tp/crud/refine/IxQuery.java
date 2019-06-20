package io.vertx.tp.crud.refine;

import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.tp.crud.atom.IxField;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.Envelop;
import io.zero.epic.Ut;

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

    static JsonObject inColumns(final Envelop envelop, final IxModule config) {
        final String actor = Ux.getString(envelop);
        /* Filters */
        final JsonObject filters = new JsonObject();
        /* ColumnStub */
        System.err.println(config);
        return filters;
    }

    private static JsonObject input(final Envelop envelop) {
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
