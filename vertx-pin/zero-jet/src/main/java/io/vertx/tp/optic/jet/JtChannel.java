package io.vertx.tp.optic.jet;

import io.vertx.core.Future;
import io.vertx.up.atom.Envelop;
import io.vertx.up.commune.ZApi;
import io.vertx.up.commune.ZRecord;

/*
 * The channel, it will be selected by ChannelSelector,
 * Here are four modes:
 * 1)
 */
public interface JtChannel {
    /**
     * This method will created `ZRecord` that contains all data records
     * 1) Different channel should have different creation method here
     *
     * @param api `ZApi` definition to describe api information here ( api + service )
     * @return Return `ZRecord` type of data record
     */
    ZRecord getRecord(ZApi api);

    /*
     * Bind current to ZApi object here
     */
    JtChannel bind(ZApi api);

    /*
     * Async implementation
     */
    Future<Envelop> transferAsync(Envelop envelop);
}
