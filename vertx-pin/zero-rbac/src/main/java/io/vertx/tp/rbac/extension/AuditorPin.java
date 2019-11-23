package io.vertx.tp.rbac.extension;

import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.up.commune.Envelop;
import io.vertx.up.extension.PlugAuditor;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.time.Instant;
import java.util.Objects;

public class AuditorPin implements PlugAuditor {
    private final transient JsonObject config = new JsonObject();

    @Override
    public PlugAuditor bind(final JsonObject config) {
        if (Ut.notNil(config)) {
            this.config.mergeIn(config);
        }
        return this;
    }

    @Override
    public Future<Envelop> audit(final RoutingContext context,
                                 final Envelop envelop) {
        if (this.isValid(context)) {
            final HttpMethod method = context.request().method();
            /* Get user id */
            final String userId = Ke.keyUser(envelop);
            final Instant instant = Instant.now();
            /*
             * counter is not 0, it means match
             * Find the first JsonObject instead of provide index value here
             */
            if (HttpMethod.POST == method) {
                /*
                 * /api/xxx
                 * The method definition
                 * method(JsonObject data)
                 */
                envelop.setValue("createdBy", userId);
                envelop.setValue("createdAt", instant);
                envelop.setValue("updatedBy", userId);
                envelop.setValue("updatedAt", instant);
            } else {
                /*
                 * /api/xxx
                 * The method definition
                 * method(String, JsonObject)
                 */
                envelop.setValue("updatedBy", userId);
                envelop.setValue("updatedAt", instant);
            }
        }
        return Ux.future(envelop);
    }

    private boolean isValid(final RoutingContext context) {
        final JsonArray include = this.config.getJsonArray("include");
        if (Objects.isNull(include) || include.isEmpty()) {
            /*
             * Must set `include` and `exclude`
             */
            return false;
        }
        final HttpMethod method = context.request().method();
        if (HttpMethod.PUT != method && HttpMethod.POST != method) {
            /*
             * Must be impact on `PUT` or `POST`
             */
            return false;
        }
        final String path = context.request().path();
        final long counter = include.stream().filter(Objects::nonNull)
                .map(item -> (String) item)
                .filter(path::startsWith)
                .count();
        final JsonArray exclude = this.config.getJsonArray("exclude");
        if (Objects.isNull(exclude) || exclude.isEmpty()) {
            /*
             * Exclude counter = 0, only include valid
             */
            return 0 < counter;
        } else {
            final long except = exclude.stream().filter(Objects::nonNull)
                    .map(item -> (String) item)
                    .filter(path::startsWith)
                    .count();
            return 0 < counter && except <= 0;
        }
    }
}
