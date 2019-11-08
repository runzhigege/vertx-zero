package io.vertx.up.commune.config;

import io.vertx.core.json.JsonArray;
import io.vertx.up.util.Ut;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * 1) Tabular related data
 * 2）Assist related data
 * 3）Category related data
 * Configuration for three critical data to define rule here.
 * Rules definition here ( Multi rule definition )
 * [
 *     {
 *         "source":"CATEGORY / TABULAR / ASSIST",
 *         "types": [
 *              "xxx": "xxx"
 *         ],
 *         "key": "assistKey",
 *         "sourceComponent": "class",
 *         "filters": {
 *         }
 *     }
 * ]
 */
public class Dict implements Serializable {

    /*
     * Source definition here for directory configuration
     */
    private final transient List<DictSource> source = new ArrayList<>();
    private transient Class<?> component;
    private transient String sigma;

    /*
     * Build object of Dict
     */
    public Dict(final String literal) {
        if (Ut.isJArray(literal)) {
            final JsonArray parameters = new JsonArray(literal);
            /* Initialize */
            this.init(parameters);
        }
    }

    public Dict(final JsonArray input) {
        if (Objects.nonNull(input)) {
            /* Initialize */
            this.init(input);
        }
    }

    private void init(final JsonArray input) {
        /* Normalize `DictSource` List */
        Ut.itJArray(input)
                .map(DictSource::new)
                .forEach(this.source::add);
    }

    public Dict bind(final String sigma) {
        this.sigma = sigma;
        return this;
    }

    public Dict bind(final Class<?> component) {
        if (Objects.isNull(component)) {
            /*
             * When component not found,
             * clear source data cache to empty list.
             */
            this.source.clear();
        } else {
            this.component = component;
        }
        return this;
    }

    public Class<?> getComponent() {
        return this.component;
    }

    public boolean valid() {
        /*
         * When current source is not empty,
         * The `dictComponent` is required here.
         */
        return !this.source.isEmpty();
    }

    public List<DictSource> getSource() {
        return this.source;
    }

    public String getSigma() {
        return this.sigma;
    }
}
