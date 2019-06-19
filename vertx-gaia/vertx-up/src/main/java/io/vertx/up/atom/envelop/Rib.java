package io.vertx.up.atom.envelop;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.exception.WebException;

public class Rib {

    public static <T> JsonObject input(final T data) {
        return RibTool.input(data);
    }

    public static WebException normalize(final WebException error) {
        return RibTool.normalize(error);
    }

    public static <T> T deserialize(final Object value, final Class<?> clazz) {
        return RibTool.deserialize(value, clazz);
    }

    public static JsonObject outJson(final JsonObject data, final WebException error) {
        return RibTool.outJson(data, error);
    }

    public static Buffer outBuffer(final JsonObject data, final WebException error) {
        return RibTool.outBuffer(data, error);
    }

    public static JsonObject getBody(final JsonObject data) {
        return RibData.getBody(data);
    }

    public static <T> T get(final JsonObject data) {
        return RibData.get(data);
    }

    public static <T> T get(final JsonObject data, final Class<?> clazz) {
        return RibData.get(data, clazz);
    }

    public static <T> T get(final JsonObject data, final Class<?> clazz, final Integer index) {
        return RibData.get(data, clazz, index);
    }

    public static <T> void set(final JsonObject data, final String field, final T value, final Integer argIndex) {
        RibData.set(data, field, value, argIndex);
    }

    public static boolean isIndex(final Integer argIndex) {
        return Arg.INDEXES.keySet().contains(argIndex);
    }

    public static void projection(final JsonObject reference, final JsonArray projection, final boolean clear) {
        RibIr.irProjection(reference, projection, clear);
    }

    public static void criteria(final JsonObject reference, final JsonObject criteria, final boolean clear) {
        RibIr.irCriteria(reference, criteria, clear);
    }
}
