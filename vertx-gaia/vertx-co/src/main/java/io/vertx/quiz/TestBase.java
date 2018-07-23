package io.vertx.quiz;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.up.epic.io.IO;
import io.vertx.zero.log.Log;

public class TestBase {

    protected String getFile(final String filename) {
        final Class<?> clazz = this.getClass();
        final String file = "test/" + clazz.getPackage().getName() + "/" + filename;
        Log.info(this.getLogger(), "[TEST] Test input file: {0}", file);
        return file;
    }

    protected JsonObject getJson(final String filename) {
        return IO.getJObject(this.getFile(filename));
    }

    protected JsonArray getArray(final String filename) {
        return IO.getJArray(this.getFile(filename));
    }

    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }
}
