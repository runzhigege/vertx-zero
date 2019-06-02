package io.vertx.tp.crud.tool;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.Rule;
import io.vertx.up.exception.WebException;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.announce.Rigor;
import io.vertx.zero.eon.FileSuffix;
import io.vertx.zero.eon.Strings;
import io.zero.epic.Ut;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * Post validation in worker here
 * Because this module is dynamic inject rules for validation, here could not be
 * implemented with Zero @Codex, instead it should be implemented with extension.
 * But the configuration file format is the same as default @Codex.
 */
class IxValidator {
    private static final Annal LOGGER = Annal.get(IxValidator.class);
    /*
     * uri -> List<Rule>
     */
    private static final ConcurrentMap<String, ConcurrentMap<String, List<Rule>>> RULE_MAP =
            new ConcurrentHashMap<>();

    static {
        final List<String> files = Ut.ioFiles(Folder.VALIDATOR, FileSuffix.YML);
        files.forEach(file -> {
            /* 1. Validator file under classpath */
            final String path = Folder.VALIDATOR + file;
            LOGGER.info("[ Εκδήλωση ] (Init) Validator File = {0}", path);
            /* 2. JsonArray process */
            final JsonObject rules = Ut.ioYaml(path);
            final ConcurrentMap<String, List<Rule>> ruleMap = new ConcurrentHashMap<>();
            rules.fieldNames().forEach(field -> {
                /* 3. Rule List */
                final JsonArray ruleArray = rules.getJsonArray(field);
                ruleMap.put(field, getRules(ruleArray));
            });
            /* 4. Append rules */
            final String key = file.replace(Strings.DOT + FileSuffix.YML, Strings.EMPTY);
            LOGGER.info("[ Εκδήλωση ] (Init) Validator Key = {0}", key);
            RULE_MAP.put(key, ruleMap);
        });
    }

    private static List<Rule> getRules(final JsonArray ruleArray) {
        final List<Rule> ruleList = new ArrayList<>();
        Ut.itJArray(ruleArray, (item, index) -> {
            final Rule rule = Rule.create(item);
            ruleList.add(rule);
        });
        return ruleList;
    }

    private static ConcurrentMap<String, List<Rule>> getRules(final String key) {
        ConcurrentMap<String, List<Rule>> rules = RULE_MAP.get(key);
        if (null == rules) {
            rules = new ConcurrentHashMap<>();
        }
        return rules;
    }

    private static String getKey(final Envelop envelop) {
        /* 1.method, uri */
        final String uri = envelop.getUri();
        final String method = envelop.getMethod().name();
        return uri.toLowerCase(Locale.getDefault()).replace('/', '.')
                .substring(1) + Strings.DOT
                + method.toLowerCase(Locale.getDefault());
    }

    static void verifyBody(final Envelop envelop, final JsonObject normalized) {
        /* 1.method, uri */
        final String key = getKey(envelop);
        LOGGER.info("[ Εκδήλωση ] ---> Rule: {0}", key);
        final ConcurrentMap<String, List<Rule>> rules = getRules(key);
        if (!rules.isEmpty()) {
            /*
             * 2. Validate JsonObject
             */
            final Rigor rigor = Rigor.get(JsonObject.class);
            final WebException error = rigor.verify(rules, normalized);
            /*
             * 3. WebException
             */
            if (null != error) {
                throw error;
            }
        }
    }
}
