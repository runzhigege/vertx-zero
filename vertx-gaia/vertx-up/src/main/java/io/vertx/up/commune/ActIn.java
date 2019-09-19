package io.vertx.up.commune;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.ID;
import io.vertx.up.fn.Fn;
import io.vertx.zero.exception.ActSpecificationException;

import java.io.Serializable;

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
    private final boolean isBatch;
    private transient ActJObject json;
    private transient ActJArray jarray;
    private transient Record definition;

    public ActIn(final Envelop envelop) {
        /* Envelop reference here */
        this.envelop = envelop;
        /*
         * Whether
         * 1) JsonObject
         * 2) JsonArray
         * */
        final JsonObject data = envelop.data();
        if (data.containsKey(ID.PARAM_BODY)) {
            final Object value = data.getValue(ID.PARAM_BODY);
            if (value instanceof JsonArray) {
                this.jarray = new ActJArray(envelop);
                this.isBatch = true;        // Batch
            } else {
                this.json = new ActJObject(envelop);
                this.isBatch = false;       // Single
            }
        } else {
            this.json = new ActJObject(envelop);
            this.isBatch = false;           // Single
        }
    }

    public Envelop getEnvelop() {
        return this.envelop;
    }

    public JsonObject getQuery() {
        Fn.outUp(this.isBatch, ActSpecificationException.class, this.getClass(), this.isBatch);
        return this.json.getQuery();
    }

    public Record getRecord() {
        Fn.outUp(this.isBatch, ActSpecificationException.class, this.getClass(), this.isBatch);
        return this.json.getRecord(this.definition);
    }

    public Record[] getRecords() {
        Fn.outUp(!this.isBatch, ActSpecificationException.class, this.getClass(), this.isBatch);
        return this.jarray.getRecords(this.definition);
    }

    public Record getDefinition() {
        return this.definition;
    }

    /*
     * 1) Set input data to Record object ( reference here )
     */
    public void connect(final Record definition) {
        this.definition = definition;
    }
}
