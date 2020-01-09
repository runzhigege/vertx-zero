package io.vertx.up.commune;

import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpStatusCode;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.commune.config.DualMapping;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.io.Serializable;
import java.util.Objects;

public class ActOut extends ActMapping implements Serializable {

    private transient final Envelop envelop;

    /*
     * Success or Failure response building
     */
    private ActOut(final Object data, final HttpStatusCode statusCode) {
        this.envelop = Envelop.success(data, statusCode);
    }

    private ActOut(final Throwable ex) {
        this.envelop = Envelop.failure(ex);
    }

    private static ActOut noContent() {
        return new ActOut(new JsonObject(), HttpStatusCode.NO_CONTENT);
    }

    /*
     * The default response is 204 no content
     */
    public static Future<ActOut> future() {
        return Ux.future(noContent());
    }

    /*
     * True / False result
     */
    public static Future<ActOut> future(final Boolean result) {
        return Ux.future(new ActOut(result, HttpStatusCode.OK));
    }

    public static Future<ActOut> future(final Buffer buffer) {
        return Ux.future(new ActOut(buffer, HttpStatusCode.OK));
    }

    public static Future<ActOut> future(final Throwable ex) {
        return Ux.future(new ActOut(ex));
    }

    /*
     * JsonObject result
     */
    public static Future<ActOut> future(final JsonObject data) {
        if (Objects.isNull(data)) {
            return Ux.future(noContent());
        } else {
            return Ux.future(new ActOut(data, HttpStatusCode.OK));
        }
    }

    /*
     * JsonArray result
     */
    public static Future<ActOut> future(final JsonArray dataArray) {
        if (Objects.isNull(dataArray)) {
            return Ux.future(noContent());
        } else {
            return Ux.future(new ActOut(dataArray, HttpStatusCode.OK));
        }
    }

    public static Future<ActOut> future(final Record[] records) {
        /*
         * Record[] to JsonArray
         */
        final JsonArray result = Ux.toArray(records);
        return Ux.future(new ActOut(result, HttpStatusCode.OK));
    }

    /*
     * Record result
     */
    public static Future<ActOut> future(final Record record) {
        final ActOut response;
        if (Objects.isNull(record)) {
            response = noContent();
        } else {
            final JsonObject data = record.toJson();
            response = new ActOut(data, HttpStatusCode.OK);
        }
        return Ux.future(response);
    }

    public Envelop envelop() {
        return this.envelop;
    }

    public Envelop envelop(final DualMapping mapping) {
        final Object response = this.envelop.data();
        if (response instanceof JsonObject || response instanceof JsonArray) {
            if (this.isAfter(mapping)) {
                final HttpStatusCode status = this.envelop.status();
                if (response instanceof JsonObject) {
                    /*
                     * JsonObject here for mapping
                     */
                    final JsonObject normalized = this.mapper().out(((JsonObject) response), mapping);
                    return Envelop.success(normalized, status).from(this.envelop);
                } else {
                    /*
                     * JsonArray here for mapping
                     */
                    final JsonArray normalized = new JsonArray();
                    Ut.itJArray((JsonArray) response).map(item -> this.mapper().out(item, mapping))
                            .forEach(normalized::add);
                    return Envelop.success(normalized, status).from(this.envelop);
                }
            } else return this.envelop;
        } else return this.envelop;
    }
}
