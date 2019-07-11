package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.up.log.Annal;
import io.vertx.up.eon.FileSuffix;
import io.vertx.up.eon.Strings;
import io.vertx.zero.exception.heart.EmptyStreamException;
import io.vertx.zero.epic.Ut;

public class ZeroTool {
    private static final Annal LOGGER = Annal.get(ZeroTool.class);

    public static String produce(final String key) {
        if (null == key) {
            return "vertx" + Strings.DOT + FileSuffix.YML;
        } else {
            return "vertx" + Strings.DASH + key +
                    Strings.DOT + FileSuffix.YML;
        }
    }

    static JsonObject read(final String key, final boolean extension) {
        // Read the original configuration
        final JsonObject original = readDirect(produce(key));
        final JsonObject merged = new JsonObject();
        if (extension) {
            // Read the internal configuration instead
            final JsonObject internal = readDirect("ke/config/" + produce(key));
            if (null != internal) {
                merged.mergeIn(internal, true);
            }
        }
        if (null != original) {
            merged.mergeIn(original, true);
        }
        return merged;
    }

    private static JsonObject readDirect(final String filename) {
        // Fix Docker issue
        if (Storage.CONFIG.containsKey(filename)) {
            return Storage.CONFIG.get(filename);
        } else {
            // Fix issue of deployment
            /*
             * For direct read configuration file, it must be successful or empty
             * Maybe the file is missing here
             */
            final JsonObject data = new JsonObject();
            try{
                final JsonObject yamlData = Ut.ioYaml(filename);
                if(Ut.notNil(yamlData)) {
                    data.mergeIn(yamlData);
                }
            }catch (EmptyStreamException ex){
                // LOGGER.warn(ex.getMessage());
                // Here do nothing to avoid useless log out
            }
            if (!data.isEmpty()) {
                Storage.CONFIG.put(filename, data);
            }
            return data;
        }
    }
}
