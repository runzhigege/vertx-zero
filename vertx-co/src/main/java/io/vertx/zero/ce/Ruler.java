package io.vertx.zero.ce;

import com.vie.hoc.HPool;
import com.vie.hors.ZeroException;
import com.vie.ke.reliable.RequiredInsurer;
import com.vie.ke.reliable.TypedInsurer;
import com.vie.util.Instance;
import com.vie.util.io.IO;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.ke.reliable.Insurer;

import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Ruler checking for json object / json array
 */
public class Ruler {

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
        
    }

    /**
     * Verify data for each file
     *
     * @param file
     * @param dataArray
     * @throws ZeroException
     */
    public static void verify(
            final String file,
            final JsonArray dataArray)
            throws ZeroException {

    }

    /**
     * JsonObject
     *
     * @param data
     * @param rule
     * @throws ZeroException
     */
    private void verifyItem(final JsonObject data, final JsonObject rule)
            throws ZeroException {
        // Required
        Insurer reference = Instance.singleton(RequiredInsurer.class);
        reference.flumen(data, rule);
        // Typed
        reference = Instance.singleton(TypedInsurer.class);
        reference.flumen(data, rule);
    }

    /**
     * JsonArray
     *
     * @param data
     * @param rule
     * @throws ZeroException
     */
    private void verifyItem(final JsonArray data, final JsonObject rule)
            throws ZeroException {
        // Required
        Insurer reference = Instance.singleton(RequiredInsurer.class);
        reference.flumen(data, rule);
        // Typed
        reference = Instance.singleton(TypedInsurer.class);
        reference.flumen(data, rule);
    }

    private static JsonObject getRule(final String file) {
        // Cached rule into memory pool
        final String filename = MessageFormat.format(RULE_PATH, file);
        return HPool.exec(RULE_MAP, filename, () -> IO.getYaml(filename));
    }
}
