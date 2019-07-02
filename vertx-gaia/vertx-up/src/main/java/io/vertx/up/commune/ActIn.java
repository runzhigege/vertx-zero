package io.vertx.up.commune;

import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.eon.ID;
import io.vertx.up.eon.ZeroValue;
import io.zero.epic.Ut;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/*
 * Business Request
 * 「ActIn」 means Action Request and offer business component parameters here.
 * 1) This DTO contains: JsonObject ( Pure Data ), ZRecord ( Record Structure )
 * 2) Bind record data structure for serialization/deserialization.
 *
 * 「Workflow」
 *  In ——
 *      Consumer -> ZApi/Envelop ->
 *                      Channel -> ActIn ->
 *                                          Component
 *  Out ——
 *      Component -> ActOut ->
 *                      Envelop ->
 *                             Consumer ->
 *                                    SendAim ( Callback )
 */
public class ActIn implements Serializable {

    /* Raw data of `Envelop` object/reference */
    private final transient Envelop envelop;
    private final transient JsonObject data = new JsonObject();
    private final transient JsonObject query = new JsonObject();
    private transient Record record;

    public ActIn(final Envelop envelop) {
        /* Envelop reference here */
        this.envelop = envelop;

        /* Data Init */
        this.partData(envelop);

        /* Header Init */
        this.partHeader(envelop);
    }

    private void partHeader(final Envelop envelop) {
        final JsonObject headerData = new JsonObject();
        envelop.headers().names().stream()
                .filter(field -> field.startsWith(ID.Header.PREFIX))
                /*
                 * Data for header
                 * X-App-Id -> appId
                 * X-App-Key -> appKey
                 * X-Sigma -> sigma
                 */
                .forEach(field -> headerData.put(ID.Header.PARAM_MAP.get(field), envelop.headers().get(field)));
        /*
         * Data Merge
         */
        this.data.mergeIn(headerData, true);
    }

    private void partData(final Envelop envelop) {
        final JsonObject rawJson = envelop.data();
        if (!Ut.isNil(rawJson)) {
            final long counter = rawJson.fieldNames().stream()
                    .filter(ZeroValue.INDEXES::containsValue)
                    .count();
            final JsonObject body;
            if (0 < counter) {
                /*
                 * Interface style
                 * {
                 *      "0": "xxx",
                 *      "1": {
                 *          "name": "x",
                 *          "name1": "y"
                 *      }
                 * }
                 */
                final JsonObject found = rawJson.fieldNames().stream()
                        .filter(Objects::nonNull)
                        .map(rawJson::getValue)
                        /*
                         * Predicate to test whether value is JsonObject
                         * If JsonObject, then find the first JsonObject as body
                         */
                        .filter(value -> value instanceof JsonObject)
                        .map(item -> (JsonObject) item)
                        .findFirst().orElse(null);

                /* Copy new data structure */
                body = null == found ? new JsonObject() : found.copy();
            } else {

                body = rawJson.copy();
                /*
                 * Cross reference
                 */
                JsonObject cross = new JsonObject();
                if (body.containsKey(ID.PARAM_BODY)) {
                    /*
                     * Common style
                     * {
                     *      "field": "value",
                     *      "$$__BODY__$$": "body"
                     * }
                     */
                    final JsonObject inputData = body.copy();
                    body.fieldNames().stream()
                            .filter(field -> !ID.PARAM_BODY.equals(field))
                            /*
                             * NON, $$__BODY__$$
                             */
                            .forEach(field -> this.data.put(field, inputData.getValue(field)));

                    cross = body.getJsonObject(ID.PARAM_BODY);
                }
                /*
                 * $$__BODY__$$ is null
                 * */
                if (!Ut.isNil(cross)) {
                    body.clear();
                    /*
                     * Modify to latest body
                     */
                    body.mergeIn(cross, true);
                }
            }
            /*
             * isQuery ? criteria
             * Until now the system has calculated the body data here
             */
            if (body.containsKey(Inquiry.KEY_CRITERIA)) {
                /*
                 * Query part
                 */
                Arrays.stream(Inquiry.KEY_QUERY).filter(field -> Objects.nonNull(body.getValue(field)))
                        .forEach(field -> this.query.put(field, body.getValue(field)));
            } else {
                /*
                 * Common data
                 */
                this.data.mergeIn(body.copy(), true);
            }
        }
    }

    public Envelop getEnvelop() {
        return this.envelop;
    }

    public JsonObject getQuery() {
        return this.query;
    }

    public Record getRecord() {
        return this.record;
    }

    /*
     * Set input data to Record object ( reference here )
     */
    public void connect(final Record record) {
        if (!Ut.isNil(this.data)) {
            /*
             * Set current data to `Record`
             */
            this.data.fieldNames()
                    .forEach(field -> record.set(field, this.data.getValue(field)));
        }
        /*
         * Revert reference binding
         */
        this.record = record;
    }
}
