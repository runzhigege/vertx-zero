package io.vertx.up.commune.config;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.util.Ut;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.Function;

/*
 * Combiner of Dict
 * 1) ConcurrentMap<String, Epsilon> map
 * -- stored field -> dict
 * 2) ConcurrentMap<String, JsonArray>
 * -- dictionary data in current channel
 * 3) DualMapping
 * -- Stored mapping ( from -> to )
 */
public class DictFabric {
    /*
     * From field = DictEpsilon
     */
    private final transient ConcurrentMap<String, DictEpsilon> epsilonMap
            = new ConcurrentHashMap<>();
    private final transient ConcurrentMap<String, JsonArray> dictData
            = new ConcurrentHashMap<>();
    private final transient DualItem mapping;
    /*
     * Data here for dictionary
     */
    private final transient ConcurrentMap<String, DualItem> fromData
            = new ConcurrentHashMap<>();
    private final transient ConcurrentMap<String, DualItem> toData
            = new ConcurrentHashMap<>();

    private DictFabric(final DualItem mapping) {
        this.mapping = mapping;
    }

    public static DictFabric create(final DualItem mapping) {
        return new DictFabric(mapping);
    }

    public static DictFabric create() {
        return new DictFabric(null);
    }

    public DictFabric epsilon(final ConcurrentMap<String, DictEpsilon> epsilonMap) {
        if (Objects.nonNull(epsilonMap)) {
            epsilonMap.forEach((key, epsilon) -> {
                /*
                 * Only pick up valid configured `epsilon`
                 * Other invalid will be ignored.
                 */
                if (epsilon.isValid()) {
                    this.epsilonMap.put(key, epsilon);
                }
            });

        }
        this.init();
        return this;
    }

    public DictFabric dict(final ConcurrentMap<String, JsonArray> dictData) {
        if (Objects.nonNull(dictData)) {
            this.dictData.putAll(dictData);
        }
        this.init();
        return this;
    }

    private void init() {
        if (this.initialized()) {
            /*
             * Iterate the epsilonMap
             */
            this.epsilonMap.forEach((fromField, epsilon) -> {
                /*
                 * Get JsonArray from dict
                 */
                JsonArray dataArray = this.dictData.get(epsilon.getSource());
                if (Objects.isNull(dataArray)) {
                    dataArray = new JsonArray();
                }
                /*
                 * Data Source is dataArray
                 * Build current `DualItem`
                 */
                final JsonObject dataItem = new JsonObject();
                Ut.itJArray(dataArray).forEach(item -> {
                    /*
                     * Data in ( From ) - out ( To )
                     */
                    final String inValue = item.getString(epsilon.getIn());
                    final String outValue = item.getString(epsilon.getOut());
                    if (Ut.notNil(inValue) && Ut.notNil(outValue)) {
                        dataItem.put(inValue, outValue);
                    }
                });
                /*
                 * Fill data in our data structure
                 */
                if (Ut.notNil(dataItem)) {

                    /*
                     * From Data Map processing
                     */
                    final DualItem item = new DualItem(dataItem);
                    this.fromData.put(fromField, item);

                    /*
                     * To Data Map processing
                     */
                    if (Objects.nonNull(this.mapping)) {
                        final String hitField = this.mapping.to(fromField);
                        if (Ut.notNil(hitField)) {
                            this.toData.put(hitField, item);
                        }
                    }
                }
            });
        }
    }

    public boolean initialized() {
        return !this.epsilonMap.isEmpty() && !this.dictData.isEmpty();
    }

    public JsonObject inTo(final JsonObject input) {
        return this.process(this.fromData, input, DualItem::to);
    }

    public JsonArray inTo(final JsonArray input) {
        return this.process(input, this::inTo);
    }

    public JsonObject inFrom(final JsonObject input) {
        return this.process(this.fromData, input, DualItem::from);
    }

    public JsonArray inFrom(final JsonArray input) {
        return this.process(input, this::inFrom);
    }

    public JsonObject outTo(final JsonObject output) {
        return this.process(this.toData, output, DualItem::to);
    }

    public JsonArray outTo(final JsonArray output) {
        return this.process(output, this::outTo);
    }

    public JsonObject outFrom(final JsonObject output) {
        return this.process(this.toData, output, DualItem::from);
    }

    public JsonArray outFrom(final JsonArray output) {
        return this.process(output, this::outFrom);
    }

    public DualItem mapping() {
        return this.mapping;
    }

    private JsonArray process(final JsonArray process,
                              final Function<JsonObject, JsonObject> function) {
        final JsonArray normalized = new JsonArray();
        Ut.itJArray(process).map(function).forEach(normalized::add);
        return normalized;
    }

    private JsonObject process(final ConcurrentMap<String, DualItem> dataMap,
                               final JsonObject input,
                               final BiFunction<DualItem, String, String> applier) {
        final JsonObject normalized = Objects.isNull(input) ? new JsonObject() : input.copy();
        dataMap.forEach((field, item) -> {
            final Object fromValue = input.getValue(field);
            if (Objects.nonNull(fromValue) && fromValue instanceof String) {
                final String toValue = applier.apply(item, fromValue.toString());
                normalized.put(field, toValue);
            }
        });
        return normalized;
    }
}
