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
public class Adminicle implements Serializable {

    /*
     * Source definition here for directory configuration
     */
    private final transient List<AdminicleSource> source = new ArrayList<>();

    /*
     * Build object of Adminicle
     */
    public Adminicle(final String literal) {
        if (Ut.isJArray(literal)) {
            final JsonArray parameters = new JsonArray(literal);
            /* Initialize */
            this.init(parameters);
        }
    }

    public Adminicle(final JsonArray input) {
        if (Objects.nonNull(input)) {
            /* Initialize */
            this.init(input);
        }
    }

    private void init(final JsonArray input) {
        /* Normalize `AdminicleSource` List */
        Ut.itJArray(input)
                .map(AdminicleSource::new)
                .forEach(this.source::add);
    }
}
