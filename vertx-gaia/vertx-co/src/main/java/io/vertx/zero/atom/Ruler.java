package io.vertx.zero.atom;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.epic.Ut;
import io.vertx.up.epic.fn.Fn;
import io.vertx.up.epic.io.IO;
import io.vertx.up.epic.mirror.Instance;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Strings;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.marshal.reliable.ForbiddenInsurer;
import io.vertx.zero.marshal.reliable.Insurer;
import io.vertx.zero.marshal.reliable.RequiredInsurer;
import io.vertx.zero.marshal.reliable.TypedInsurer;

import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Ruler checking for json object / json array
 */
public class Ruler {

    private static final Annal LOGGER = Annal.get(Ruler.class);

    private static final String RULE_PATH = "ke/rules/{0}.yml";

    private static final ConcurrentMap<String, JsonObject> RULE_MAP =
            new ConcurrentHashMap<>();

    /**
     * Verify data for each file
     *
     * @param file
     * @param data
     * @throws ZeroException
     */
    public static void verify(
            final String file,
            final JsonObject data)
            throws ZeroException {
        Fn.shuntZero(() -> {
            // 1. Rule for json object
            final JsonObject rule = getRule(file);
            verifyItem(data, rule);
            // 2. For json item
            for (final String field : data.fieldNames()) {
                final Object value = data.getValue(field);
                Fn.shuntZero(() -> {
                    if (Ut.isJObject(value) || Ut.isJArray(value)) {
                        final String filename = file + Strings.DOT + field;
                        if (Ut.isJObject(value)) {
                            // 3.1.2 Json array child
                            verify(filename, (JsonObject) value);
                        } else if (Ut.isJArray(value)) {
                            // 3.1.3 Json array child
                            verify(filename, (JsonArray) value);
                        }
                    }
                }, value);
            }
        }, file, data);
    }

    /**
     * Verify data for each file
     *
     * @param file
     * @param data
     * @throws ZeroException
     */
    public static void verify(
            final String file,
            final JsonArray data)
            throws ZeroException {
        Fn.shuntZero(() -> {
            // 1. Rule for json array
            final JsonObject rule = getRule(file);
            verifyItem(data, rule);
            // 2. For json item
            Ut.etJArray(data, (value, field) -> {
                // 3. Value = JsonObject, identify if extension.
                final String filename = file + Strings.DOT + field;
                if (Ut.isJObject(value)) {
                    // JsonObject
                    verify(filename, (JsonObject) value);
                } else if (Ut.isJArray(value)) {
                    // JsonArray
                    verify(filename, (JsonArray) value);
                }
            });
        }, file, data);
    }

    /**
     * JsonObject
     *
     * @param input
     * @param rule
     * @throws ZeroException
     */
    private static <T> void verifyItem(final T input, final JsonObject rule)
            throws ZeroException {
        Fn.shuntZero(() -> {
            if (Ut.isJArray(input)) {
                final JsonArray data = (JsonArray) input;
                // Required
                Insurer reference = Instance.singleton(RequiredInsurer.class);
                reference.flumen(data, rule);
                // Typed
                reference = Instance.singleton(TypedInsurer.class);
                reference.flumen(data, rule);
                // Forbidden
                reference = Instance.singleton(ForbiddenInsurer.class);
                reference.flumen(data, rule);
            } else {
                final JsonObject data = (JsonObject) input;
                // Required
                Insurer reference = Instance.singleton(RequiredInsurer.class);
                reference.flumen(data, rule);
                // Typed
                reference = Instance.singleton(TypedInsurer.class);
                reference.flumen(data, rule);
                // Forbidden
                reference = Instance.singleton(ForbiddenInsurer.class);
                reference.flumen(data, rule);
            }
        }, input, rule);
    }

    private static JsonObject getRule(final String file) {
        // Cached rule into memory pool
        final String filename = MessageFormat.format(RULE_PATH, file);
        if (RULE_MAP.containsKey(filename)) {
            LOGGER.debug(Info.RULE_CACHED_FILE, filename);
        } else {
            LOGGER.debug(Info.RULE_FILE, filename);
        }
        return Fn.pool(RULE_MAP, filename, () -> IO.getYaml(filename));
    }
}
