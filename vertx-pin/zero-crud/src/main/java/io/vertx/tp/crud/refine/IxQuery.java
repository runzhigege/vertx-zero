package io.vertx.tp.crud.refine;

import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.tp.crud.atom.IxColumn;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.tp.crud.atom.IxField;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.Envelop;
import io.zero.epic.Ut;

import java.util.Objects;

class IxQuery {

    static JsonObject inKeys(final JsonArray array, final IxConfig config) {
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

    static JsonObject inColumns(final Envelop envelop, final IxConfig config) {
        final String actor = Ux.getString(envelop);
        /* IxColumn */
        final IxColumn column = config.getColumn();
        /* Filters */
        final JsonObject filters = new JsonObject();
        {
            /* module */
            final String actorField = column.getActor();
            if (Ut.notNil(actorField)) {
                filters.put(actorField, actor);
            }
        }
        /* Merge ( Header, User ) */
        final JsonObject data = input(envelop);
        {
            /* JsonArray */
            final JsonArray condition = column.getCondition();
            Ut.itJArray(condition, String.class, (columnField, index) -> {
                final Object value = data.getValue(columnField);
                if (Objects.nonNull(value)) {
                    filters.put(columnField, value);
                }
            });
        }
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
