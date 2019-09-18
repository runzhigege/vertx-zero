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
     * 构造子用于构造成功与异常
     */
    private ActOut(final Object data, final HttpStatusCode statusCode) {
        this.envelop = Envelop.success(data, statusCode);
    }

    private ActOut(final Throwable ex) {
        this.envelop = Envelop.failure(ex);
    }

    /*
     * 默认使用 204，没有内容
     */
    public static Future<ActOut> future() {
        return Ux.toFuture(noContent());
    }

    public static Future<ActOut> future(final JsonObject data) {
        if (Objects.isNull(data)) {
            return Ux.toFuture(noContent());
        } else {
            return Ux.toFuture(new ActOut(data, HttpStatusCode.OK));
        }
    }

    public static Future<ActOut> future(final JsonArray dataArray) {
        if (Objects.isNull(dataArray)) {
            return Ux.toFuture(noContent());
        } else {
            return Ux.toFuture(new ActOut(dataArray, HttpStatusCode.OK));
        }
    }

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

    private static ActOut noContent() {
        return new ActOut(new JsonObject(), HttpStatusCode.NO_CONTENT);
    }

    public Envelop sync() {
        return this.envelop;
    }

    public Future<Envelop> async() {
        return Ux.toFuture(this.envelop);
    }
}
