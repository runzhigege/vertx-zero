package io.vertx.tp.ui.service;

import cn.vertxup.ui.domain.tables.daos.UiPageDao;
import cn.vertxup.ui.domain.tables.pojos.UiPage;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import javax.inject.Inject;
import java.util.Objects;

public class PageService implements PageStub {
    @Inject
    private transient LayoutStub layoutStub;

    @Override
    public Future<JsonObject> fetchAmp(final String sigma,
                                       final JsonObject params) {
        final JsonObject filters = params.copy();
        filters.put(KeField.SIGMA, sigma);
        filters.put("", Boolean.TRUE);
        return Ux.Jooq.on(UiPageDao.class)
                .<UiPage>fetchOneAsync(filters)
                .compose(page -> {
                    if (Objects.nonNull(page)) {
                        /*
                         * Page Existing in current system
                         */
                        if (Ut.notNil(page.getLayoutId())) {
                            /*
                             * Continue to extract layout Data here
                             */
                            return this.layoutStub.fetchLayout(page.getLayoutId())
                                    .compose(layout -> {
                                        final JsonObject pageJson = Ux.toJson(page);
                                        pageJson.put("layout", layout);
                                        return Ux.toFuture(pageJson)
                                                /*
                                                 * Configuration converted to Json
                                                 */
                                                .compose(Ke.metadata(KeField.Ui.CONTAINER_CONFIG));
                                    });
                        } else {
                            return Ux.fnJObject(page);
                        }
                    } else {
                        /*
                         * No configuration related to current page
                         */
                        return Ux.toFuture(new JsonObject());
                    }
                });
    }
}
