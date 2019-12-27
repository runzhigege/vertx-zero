package io.vertx.up.util;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.fn.Fn;
import io.vertx.up.log.Annal;

import java.io.File;
import java.io.FileWriter;

/*
 *
 */
final class Out {
    private static final Annal LOGGER = Annal.get(Out.class);

    private Out() {
    }

    @SuppressWarnings("all")
    static void write(final String path, final String data) {
        Fn.safeNull(() -> Fn.safeJvm(() -> {
            final File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            final FileWriter writer = new FileWriter(file);
            writer.write(data);
            writer.close();
        }), path, data);
    }

    static void write(final String path, final JsonObject data) {
        final String target = null == data ? "{}" : data.encodePrettily();
        write(path, target);
    }

    static void write(final String path, final JsonArray data) {
        final String target = null == data ? "[]" : data.encodePrettily();
        write(path, target);
    }
}
