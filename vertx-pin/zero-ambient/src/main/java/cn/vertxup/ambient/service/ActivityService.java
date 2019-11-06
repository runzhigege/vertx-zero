package cn.vertxup.ambient.service;

import cn.vertxup.ambient.domain.tables.daos.XActivityChangeDao;
import cn.vertxup.ambient.domain.tables.daos.XActivityDao;
import cn.vertxup.ambient.domain.tables.pojos.XActivity;
import cn.vertxup.ambient.domain.tables.pojos.XActivityChange;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

public class ActivityService implements ActivityStub {
    @Override
    public Future<JsonObject> fetchActivity(final String id) {
        return Ux.Jooq.on(XActivityDao.class)
                .<XActivity>findByIdAsync(id)
                .compose(Ut.applyNil(JsonObject::new, (activity) -> Ux.Jooq.on(XActivityChangeDao.class)
                        .<XActivityChange>fetchAsync("activityId", activity.getKey())
                        .compose(Ux::fnJArray)
                        .compose(changes -> {
                            final JsonObject activityJson = Ux.toJson(activity);
                            /*
                             * Json Serialization for recordNew / recordOld
                             */
                            Ke.metadata(activityJson, "recordNew");
                            Ke.metadata(activityJson, "recordOld");
                            activityJson.put(KeField.CHANGES, changes);
                            return Ux.future(activityJson);
                        })));
    }
}
