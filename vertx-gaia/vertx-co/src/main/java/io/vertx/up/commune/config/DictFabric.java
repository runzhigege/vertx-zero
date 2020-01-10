package io.vertx.up.commune.config;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.log.Annal;
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

    private static final Annal LOGGER = Annal.get(DictFabric.class);
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
        if (Objects.nonNull(epsilonMap) && !epsilonMap.isEmpty()) {
            epsilonMap.forEach((key, epsilon) -> {
                /*
                 * Only pick up valid configured `epsilon`
                 * Other invalid will be ignored.
                 */
                if (epsilon.isValid()) {
                    this.epsilonMap.put(key, epsilon);
                }
            });
        } else {
            LOGGER.warn("[ ZERO ] DictFabric got empty epsilonMap ( ConcurrentMap<String, DictEpsilon> ) !");
        }
        this.init();
        return this;
    }

    public DictFabric dict(final ConcurrentMap<String, JsonArray> dictData) {
        if (Objects.nonNull(dictData) && !dictData.isEmpty()) {
            this.dictData.putAll(dictData);
        } else {
            LOGGER.warn("[ ZERO ] DictFabric got empty dictData ( ConcurrentMap<String, JsonArray> ) !");
        }
        this.init();
        return this;
    }

    private void init() {
        if (this.ready()) {
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

    private boolean ready() {
        return !this.epsilonMap.isEmpty() && !this.dictData.isEmpty();
    }

    /*
     * DualItem ->
     *     in    ->   out
     *  ( name ) -> ( key )
     *
     * Api: to ( in -> out )
     * Api: from ( out -> in )
     */
    /*
     * inTo
     * 1) The field is Ox field
     * 2) uuid -> ( out -> in )
     */
    public JsonObject inTo(final JsonObject input) {
        return this.process(this.fromData, input, DualItem::from);
    }

    public JsonArray inTo(final JsonArray input) {
        return this.process(input, this::inTo);
    }

    /*
     * inFrom
     * 1) The field is Ox field
     * 2) display -> ( in -> out )
     */
    public JsonObject inFrom(final JsonObject input) {
        return this.process(this.fromData, input, DualItem::to);
    }

    public JsonArray inFrom(final JsonArray input) {
        return this.process(input, this::inFrom);
    }

    /*
     * outTo
     * 1) The field is Tp field
     * 2) uuid -> ( out -> in )
     */
    public JsonObject outTo(final JsonObject output) {
        return this.process(this.toData, output, DualItem::from);
    }

    public JsonArray outTo(final JsonArray output) {
        return this.process(output, this::outTo);
    }

    /*
     * outFrom
     * 1) The field is Tp field
     * 2) display -> ( in -> out )
     */
    public JsonObject outFrom(final JsonObject output) {
        return this.process(this.toData, output, DualItem::to);
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

    public void report(final StringBuilder builder) {
        builder.append("\n\t[ Epsilon ]: ");
        this.epsilonMap.forEach((key, epsilon) -> builder.append("\n\t\t").
                append(key).append(" = ").append(epsilon));
        builder.append("\n\t[ Dict Data ]: ");
        this.dictData.forEach((key, dictData) -> builder.append("\n\t\t").
                append(key).append(" = ").append(dictData.encode()));
        builder.append("\n\t[ Mapping ]: ").append(this.mapping.toString());
        builder.append("\n\t[ From Data ]: ");
        this.fromData.forEach((field, json) -> builder.append("\n\t\t")
                .append(field).append(" = ").append(json.toString()));
        builder.append("\n\t[ To Data ]: ");
        this.toData.forEach((field, json) -> builder.append("\n\t\t")
                .append(field).append(" = ").append(json.toString()));
    }
}
