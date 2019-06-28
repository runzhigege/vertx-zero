package io.vertx.up.commune;

import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Envelop;
import io.vertx.up.eon.ID;

import java.io.Serializable;

/*
 * Business Request
 * 「ActRequest」 means Action Request and offer business component parameters here.
 * 1) This DTO contains: JsonObject ( Pure Data ), ZRecord ( Record Structure )
 * 2) Bind record data structure for serialization/deserialization.
 *
 * 「Workflow」
 *  In ——
 *      Consumer -> ZApi/Envelop ->
 *                      Channel -> ActRequest ->
 *                                          Component
 *  Out ——
 *      Component -> ActResponse ->
 *                      Envelop ->
 *                             Consumer ->
 *                                    SendAim ( Callback )
 */
public class ActRequest implements Serializable {

    /* Inner reference of Act Request */
    private final transient Envelop envelop;
    private final transient JsonObject data;
    private transient ZRecord record;

    public ActRequest(final Envelop envelop) {
        /* Envelop reference here */
        this.envelop = envelop;
        /* Json Body */
        final JsonObject body = envelop.data();
        if (body.containsKey(ID.PARAM_BODY)) {
            this.data = body.getJsonObject(ID.PARAM_BODY);
        } else {
            /* Copy the body data here */
            this.data = body.copy();
        }
    }

    /*
     * User input record data structure
     */
    public ActRequest input(final ZRecord record) {
        /*
         * First the reference of current record should be set here.
         */
        this.record = record;
        if (null != record) {
            /*
             * Copy data from: Envelop to Record
             */
            this.data.fieldNames()
                    .forEach(field -> record.set(field, this.data.getValue(field)));
        }
        return this;
    }

    public ZRecord getRecord() {
        return this.record;
    }
}
