package io.vertx.tp.ui.service.column;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.tp.ui.cv.UiMsg;
import io.vertx.tp.ui.init.UiPin;
import io.vertx.tp.ui.refine.Ui;
import io.vertx.up.aiki.Ux;
import io.vertx.up.log.Annal;

class FileValve implements UiValve {

    private static final Annal LOGGER = Annal.get(FileValve.class);

    @Override
    public Future<JsonArray> fetchColumn(final String view, final String identifier, final String sigma) {
        /* Default column JsonArray */
        final JsonArray columns = UiPin.getColumn(identifier);
        Ui.infoUi(LOGGER, UiMsg.COLUMN_STATIC, sigma, columns.size(), columns.encode());
        return Ux.toFuture(columns);
    }
}
