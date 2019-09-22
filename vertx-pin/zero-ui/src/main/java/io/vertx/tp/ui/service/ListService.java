package io.vertx.tp.ui.service;

import cn.vertxup.ui.domain.tables.daos.UiListDao;
import cn.vertxup.ui.domain.tables.pojos.UiList;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import javax.inject.Inject;
import java.util.Objects;

public class ListService implements ListStub {
    @Inject
    private transient OptionStub optionStub;

    @Override
    public Future<JsonObject> fetchById(final String listId) {
        /*
         * Read list configuration for configuration
         */
        return Ux.Jooq.on(UiListDao.class)
                .<UiList>findByIdAsync(listId)
                .compose(list -> {
                    if (Objects.isNull(list)) {
                        return Ux.toFuture(new JsonObject());
                    } else {
                        /*
                         * It means here are some additional configuration that should be
                         * fetch then
                         */
                        final JsonObject listJson = Ut.serializeJson(list);
                        return this.attachConfig(listJson);
                    }
                });
    }

    private Future<JsonObject> attachConfig(final JsonObject listJson) {
        /*
         * Capture important configuration here
         */
        Ke.metadata(listJson, FIELD_OPTIONS);
        Ke.metadata(listJson, FIELD_OPTIONS_AJAX);
        Ke.metadata(listJson, FIELD_OPTIONS_SUBMIT);
        Ke.metadata(listJson, FIELD_V_SEGMENT);
        return Ux.toFuture(listJson)
                /* vQuery */
                .compose(Ux.toAttach(FIELD_V_QUERY, this.optionStub::fetchQuery))
                /* vSearch */
                .compose(Ux.toAttach(FIELD_V_SEARCH, this.optionStub::fetchSearch))
                /* vTable */
                .compose(Ux.toAttach(FIELD_V_TABLE, this.optionStub::fetchTable))
                /* vSegment */
                .compose(Ux.toAttachJson(FIELD_V_SEGMENT, this.optionStub::fetchFragment))
                /* Combiner for final processing */
                .compose(Ke.fabricAsync("classCombiner"));
    }
}
