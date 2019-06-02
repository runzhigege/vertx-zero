package io.vertx.tp.crud.tool;

import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.tp.crud.atom.IxConfig;
import io.vertx.tp.crud.atom.IxField;
import io.vertx.up.aiki.Ux;
import io.vertx.up.atom.Envelop;
import io.zero.epic.Ut;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/*
 * Add Tool for normalized
 */
class IxDefault {
    /*
     * Get Add Normalized Json
     */
    static JsonObject inAdd(final Envelop envelop, final IxConfig config) {
        final IxField field = config.getField();
        final JsonObject body = Ux.getJson1(envelop);
        /* Primary Key Add */
        if (Ut.notNil(field.getKey())) {
            final String keyValue = body.getString(field.getKey());
            if (Ut.isNil(keyValue)) {
                body.put(field.getKey(), UUID.randomUUID().toString());
            }
        }
        return body;
    }

    /*
     * Get Edit Normalized Json
     */
    static JsonObject inEdit(final Envelop envelop, final IxConfig config) {
        final IxField field = config.getField();
        final JsonObject body = Ux.getJson2(envelop);
        /* Primary Key Add */
        if (Ut.notNil(field.getKey())) {
            final String keyValue = Ux.getString1(envelop);
            if (Ut.notNil(keyValue)) {
                body.put(field.getKey(), keyValue);
            }
        }
        return body;
    }

    /*
     * Get User information to set auditor
     */
    static JsonObject inAuditor(final Envelop envelop,
                                final IxConfig config,
                                final boolean isUpdate) {
        final User user = envelop.user();
        final JsonObject auditor = new JsonObject();
        if (null != user) {
            final JsonObject userData = user.principal();
            if (Objects.nonNull(userData)) {
                final String userId = userData.getString("user");
                final IxField field = config.getField();
                if (!isUpdate) {
                    /* created */
                    inAuditor(auditor, field.getCreated(), userId);
                }
                /* updated */
                inAuditor(auditor, field.getUpdated(), userId);
            }
        }
        return auditor;
    }

    static JsonObject inHeader(final Envelop envelop,
                               final IxConfig config) {
        final MultiMap headers = envelop.headers();
        final JsonObject header = new JsonObject();
        /* Header Data */
        final JsonObject headerConfig = config.getHeader();
        if (null != headerConfig) {
            Ut.itJObject(headerConfig, (to, from) -> {
                final String value = headers.get(to.toString());
                if (Ut.notNil(value)) {
                    header.put(from, value);
                }
            });
        }
        return header;
    }

    private static void inAuditor(final JsonObject auditor,
                                  final JsonObject config,
                                  final String userId) {
        if (Objects.nonNull(config) && Ut.notNil(userId)) {
            /* User By */
            final String by = config.getString("by");
            if (Ut.notNil(by)) {
                auditor.put(by, userId);
            }
            final String at = config.getString("at");
            if (Ut.notNil(at)) {
                auditor.put(at, Instant.now());
            }
        }
    }
}
