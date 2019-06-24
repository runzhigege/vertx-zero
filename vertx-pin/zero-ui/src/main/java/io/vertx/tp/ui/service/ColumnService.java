package io.vertx.tp.ui.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.extension.ui.AbstractColumn;
import io.vertx.tp.ui.cv.UiMsg;
import io.vertx.tp.ui.refine.Ui;
import io.vertx.tp.ui.service.column.UiValve;

/*
 * Bridge design for call internal actual column service
 * 1. Dynamic Column
 * 2. Static Column
 */
public class ColumnService extends AbstractColumn {

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
