package io.vertx.tp.rbac.service.view;

import cn.vertxup.domain.tables.pojos.SView;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;

import java.util.List;

public interface ViewStub {

    Future<SView> fetchMatrix(String user, String resourceId, String view);

    Future<SView> saveMatrix(String user, String resourceId, String view,
                             String sigma, JsonArray projection);

    Future<List<SView>> fetchMatrix(JsonArray role, String resourceId, String view);
}
