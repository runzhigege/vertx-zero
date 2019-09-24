package cn.vertxup.ui.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public class FormService implements FormStub{
    @Override
    public Future<JsonObject> fetchById(String formId) {
        return null;
    }
}
