package io.vertx.tp.rbac.service.view;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.extension.jooq.AbstractJewel;
import io.vertx.tp.ke.extension.jooq.EpidemiaMy;
import io.zero.epic.Ut;

public class ColumnMyService extends AbstractJewel<EpidemiaMy> implements EpidemiaMy {

    private final transient ViewStub stub = Ut.singleton(ViewService.class);


    @Override
    public Future<JsonArray> fetchMy(final JsonObject config) {
        final JsonObject principle = config.getJsonObject(KeField.PRINCIPLE);
        // final String user = this.getUser(principle);
        System.out.println(config.encodePrettily());
        return null;
    }

    @Override
    public Future<JsonArray> saveMy(final String resourceId, final JsonArray projection) {
        return null;
    }
}
