package io.vertx.up.atom.envelop;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.error.Readible;
import io.vertx.up.exception.WebException;
import io.vertx.zero.fn.Fn;
import io.vertx.zero.runtime.ZeroSerializer;

import java.util.Objects;

class RibTool {

    static <T> JsonObject input(final T data) {
        final Object serialized = ZeroSerializer.toSupport(data);
        final JsonObject bodyData = new JsonObject();
        bodyData.put(Key.DATA, serialized);
        return bodyData;
    }

    static WebException normalize(final WebException error) {
        final Readible readible = Readible.get();
        readible.interpret(error);
        return error;
    }

    @SuppressWarnings("all")
    static <T> T deserialize(final Object value, final Class<?> clazz) {
        T reference = null;
        if (Objects.nonNull(value)) {
            final Object result = ZeroSerializer.getValue(clazz, value.toString());
            reference = Fn.getNull(() -> (T) result, result);
        }
        return reference;
    }

    static JsonObject outJson(final JsonObject data, final WebException error) {
        if (Objects.isNull(error)) {
            return data;
        } else {
            return error.toJson();
        }
    }

    static Buffer outBuffer(final JsonObject data, final WebException error) {
        if (Objects.isNull(error)) {
            final JsonObject response = data.getJsonObject(Key.DATA);
            return Buffer.buffer(response.getBinary("bytes"));
        } else {
            final JsonObject errorJson = error.toJson();
            return Buffer.buffer(errorJson.encode());
        }
    }
}
