package cn.vertxup.ui.service;

import cn.vertxup.ui.domain.tables.daos.UiFieldDao;
import cn.vertxup.ui.domain.tables.pojos.UiField;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.ui.refine.Ui;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FieldService implements FieldStub {
    private static final Annal LOGGER = Annal.get(FieldService.class);

    @Override
    public Future<JsonArray> fetchUi(final String formId) {
        return Ux.Jooq.on(UiFieldDao.class)
                .<UiField>fetchAsync("controlId", formId)
                .compose(ui -> {
                    if (Objects.isNull(ui) || ui.isEmpty()) {
                        Ui.infoWarn(FieldService.LOGGER, " Field not configured.");
                        return Ux.toFuture(new JsonArray());
                    } else {
                        final JsonArray uiJson = Ut.serializeJson(ui);
                        return this.attachConfig(uiJson);
                    }
                });
    }

    private Future<JsonArray> attachConfig(final JsonArray fieldJson) {
        /*
         * metadata mode for parsing processor
         */
        final JsonArray ui = new JsonArray();
        /*
         * Calculate row
         */
        final int rowIndex = Ut.itJArray(fieldJson)
                .map(each -> each.getInteger("yPoint"))
                .max(Comparator.naturalOrder())
                .orElse(0);
        for (int idx = 0; idx <= rowIndex; idx++) {
            final Integer current = idx;
            final List<JsonObject> row = Ut.itJArray(fieldJson)
                    .filter(item -> current.equals(item.getInteger("yPoint")))
                    .sorted(Comparator.comparing(item -> item.getInteger("xPoint")))
                    .collect(Collectors.toList());
            /*
             * Calculate columns
             */
            final JsonArray rowArr = new JsonArray();
            row.forEach(cell -> {
                Ke.metadata(cell, FieldStub.OPTION_JSX);
                Ke.metadata(cell, FieldStub.OPTION_CONFIG);
                Ke.metadata(cell, FieldStub.OPTION_ITEM);
                Ke.metadataArray(cell, "rules");
                final String render = Objects.isNull(cell.getString("render")) ? "" :
                        cell.getString("render");
                final String label = Objects.isNull(cell.getString("label")) ? "" :
                        cell.getString("label");
                final String metadata = cell.getString("name")
                        + "," + label + "," + cell.getInteger("span")
                        + ",," + render;

                final JsonObject dataCell = new JsonObject();
                dataCell.put("metadata", metadata);
                /*
                 * hidden
                 */
                final Boolean hidden = cell.getBoolean("hidden");
                if (hidden) {
                    dataCell.put("hidden", Boolean.TRUE);
                }
                /*
                 * Rules
                 */
                final JsonArray rules = cell.getJsonArray("rules");
                if (Objects.nonNull(rules) && !rules.isEmpty()) {
                    dataCell.put("optionConfig.rules", rules);
                }
                /*
                 * Three core configuration
                 */
                if (Objects.nonNull(cell.getValue(FieldStub.OPTION_JSX))) {
                    dataCell.put(FieldStub.OPTION_JSX, cell.getValue(FieldStub.OPTION_JSX));
                }
                if (Objects.nonNull(cell.getValue(FieldStub.OPTION_CONFIG))) {
                    dataCell.put(FieldStub.OPTION_CONFIG, cell.getValue(FieldStub.OPTION_CONFIG));
                }
                if (Objects.nonNull(cell.getValue(FieldStub.OPTION_ITEM))) {
                    dataCell.put(FieldStub.OPTION_ITEM, cell.getValue(FieldStub.OPTION_ITEM));
                }
                rowArr.add(dataCell);
            });
            ui.add(rowArr);
        }
        return Ux.toFuture(ui);
    }
}
