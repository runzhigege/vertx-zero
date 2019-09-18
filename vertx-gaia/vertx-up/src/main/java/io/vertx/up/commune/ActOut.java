package io.vertx.up.commune;

import io.vertx.core.Future;
import io.vertx.core.http.HttpStatusCode;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.unity.Ux;

import java.io.Serializable;
import java.util.Objects;

public class ActOut implements Serializable {

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
        return Ux.toFuture(noContent());
    }

    /*
     * True / False result
     */
    public static Future<ActOut> future(final Boolean result) {
        return Ux.toFuture(new ActOut(result, HttpStatusCode.OK));
    }

    /*
     * JsonObject result
     */
    public static Future<ActOut> future(final JsonObject data) {
        if (Objects.isNull(data)) {
            return Ux.toFuture(noContent());
        } else {
            return Ux.toFuture(new ActOut(data, HttpStatusCode.OK));
        }
    }

    /*
     * JsonArray result
     */
    public static Future<ActOut> future(final JsonArray dataArray) {
        if (Objects.isNull(dataArray)) {
            return Ux.toFuture(noContent());
        } else {
            return Ux.toFuture(new ActOut(dataArray, HttpStatusCode.OK));
        }
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
        return Ux.toFuture(response);
    }

    public Future<Envelop> async() {
        return Ux.toFuture(this.envelop);
    }
}
