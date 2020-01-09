package io.vertx.up.commune.config;

import io.vertx.core.json.JsonArray;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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
    private final transient DualMapping mapping;
    /*
     * Data here for dictionary
     */
    private final transient ConcurrentMap<String, DualItem> fromData
            = new ConcurrentHashMap<>();
    private final transient ConcurrentMap<String, DualItem> toData
            = new ConcurrentHashMap<>();

    private DictFabric(final DualMapping mapping) {
        this.mapping = mapping;
    }

    public static DictFabric create(final DualMapping mapping) {
        return new DictFabric(mapping);
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
                final JsonArray dataArray = this.dictData.get(epsilon.getSource());

            });
        }
    }

    public boolean initialized() {
        return !this.epsilonMap.isEmpty() && !this.dictData.isEmpty() && Objects.nonNull(this.mapping);
    }
}
