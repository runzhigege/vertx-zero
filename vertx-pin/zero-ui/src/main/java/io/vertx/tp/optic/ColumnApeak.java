package io.vertx.tp.optic;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.optic.fantom.Anchoret;
import io.vertx.tp.ui.cv.UiMsg;
import io.vertx.tp.ui.refine.Ui;
import io.vertx.tp.ui.service.column.UiValve;

/*
 * Bridge design for call internal actual column service
 * 1. Dynamic Apeak
 * 2. Static Apeak
 */
public class ColumnApeak extends Anchoret<Apeak> implements Apeak {

    @Override
    public Future<JsonArray> fetchFull(final JsonObject config) {
        Ui.infoUi(this.getLogger(), UiMsg.COLUMN_FULL, config.encodePrettily());
        final Boolean dynamic = config.getBoolean(KeField.DYNAMIC);
        /* Ui valve initialization */
        final UiValve valve;
        if (dynamic) {
            valve = UiValve.dynamic();
        } else {
            valve = UiValve.fixed();
        }
        /* Whether this module used dynamic column here */
        final String identifier = config.getString(KeField.IDENTIFIER);
        final String sigma = config.getString(KeField.SIGMA);
        final String view = config.getString(KeField.VIEW);
        return valve.fetchColumn(view, identifier, sigma);
    }
}
