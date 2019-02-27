package io.zero.quiz;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;

public class EpicBase {

    protected String getFile(final String filename) {
        final Class<?> clazz = this.getClass();
        final String file = "test/" + clazz.getPackage().getName() + "/" + filename;
        this.getLogger().info("[ ZERO Test ] Test input up.god.file: {0}", file);
        return file;
    }

    protected JsonObject getJson(final String filename) {
        return Ut.ioJObject(this.getFile(filename));
    }

    protected JsonArray getArray(final String filename) {
        return Ut.ioJArray(this.getFile(filename));
    }

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }
}
