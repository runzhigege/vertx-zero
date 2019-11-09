package cn.vertxup.ambient.service;

import cn.vertxup.ambient.domain.tables.daos.XActivityChangeDao;
import cn.vertxup.ambient.domain.tables.daos.XActivityDao;
import cn.vertxup.ambient.domain.tables.pojos.XActivity;
import cn.vertxup.ambient.domain.tables.pojos.XActivityChange;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ambient.cv.em.ActivityStatus;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.ArrayList;
import java.util.List;

public class ActivityService implements ActivityStub {
    @Override
    public Future<JsonObject> fetchActivity(final String id) {
        return Ux.Jooq.on(XActivityDao.class)
                .<XActivity>findByIdAsync(id)
                .compose(Ut.applyNil(JsonObject::new, (activity) -> Ux.Jooq.on(XActivityChangeDao.class)
                        .<XActivityChange>fetchAsync(ACTIVITY_ID, activity.getKey())
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

    @Override
    public Future<JsonArray> saveChanges(final String id, final ActivityStatus status) {
        return Ux.Jooq.on(XActivityChangeDao.class)
                .<XActivityChange>fetchAsync(ACTIVITY_ID, id)
                .compose(changes -> {
                    /*
                     * Iterate `ActivityChanges`
                     */
                    final List<XActivityChange> original = new ArrayList<>(changes);
                    Ut.itList(original, (change, index) -> {
                        final String oldStatus = change.getStatus();
                        final XActivityChange itemRef = changes.get(index);
                        if (Ut.isNil(oldStatus)) {
                            itemRef.setStatus(status.name());
                        } else {
                            if (ActivityStatus.CONFIRMED == status) {
                                /*
                                 * -> CONFIRMED
                                 * Only `PENDING` allowed, system keeped
                                 */
                                final ActivityStatus old =
                                        Ut.toEnum(change::getStatus, ActivityStatus.class, ActivityStatus.SYSTEM);
                                if (ActivityStatus.PENDING == old) {
                                    itemRef.setStatus(ActivityStatus.CONFIRMED.name());
                                }
                            } else {
                                /*
                                 * -> PENDING or SYSTEM
                                 * It's allowed directly
                                 */
                                itemRef.setStatus(status.name());
                            }

                        }
                    });
                    return Ux.Jooq.on(XActivityChangeDao.class)
                            .updateAsync(changes)
                            .compose(Ux::fnJArray);
                });
    }
}
