package cn.vertxup.ui.service;

import cn.vertxup.ui.domain.tables.daos.UiFormDao;
import cn.vertxup.ui.domain.tables.pojos.UiForm;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ui.refine.Ui;
import io.vertx.up.log.Annal;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.Objects;

public class FormService implements FormStub {
    private static final Annal LOGGER = Annal.get(FormService.class);

    @Override
    public Future<JsonObject> fetchById(final String formId) {
        return Ux.Jooq.on(UiFormDao.class)
                .<UiForm>findByIdAsync(formId)
                .compose(form -> {
                    if (Objects.isNull(form)) {
                        Ui.infoWarn(FormService.LOGGER, " Form not found, id = {0}", formId);
                        return Ux.toFuture(new JsonObject());
                    } else {
                        /*
                         * form / fields combine here
                         */
                        final JsonObject formJson = Ut.serializeJson(form);
                        return this.attachConfig(formJson);
                    }
                });
    }

    private Future<JsonObject> attachConfig(final JsonObject formJson) {
        System.err.println(formJson.encodePrettily());
        return Ux.toFuture(formJson);
    }
}
