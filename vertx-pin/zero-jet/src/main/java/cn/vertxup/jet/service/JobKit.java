package cn.vertxup.jet.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.ke.refine.Ke;
import io.vertx.tp.plugin.job.JobPool;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/*
 *  Job kit here for configuration
 */
class JobKit {
    private static final List<Mission> MISSION_LIST = JobPool.get();

    static Future<JsonArray> fetchMission(final Set<String> codes) {
        if (Objects.isNull(codes) || codes.isEmpty()) {
            return Ux.future(new JsonArray());
        } else {
            final JsonArray response = new JsonArray();
            MISSION_LIST.stream()
                    .filter(mission -> codes.contains(mission.getCode()))
                    .map(JobKit::normalize)
                    .forEach(response::add);
            return Ux.future(response);
        }
    }

    static Future<JsonObject> fetchMission(final String code) {
        final Mission found = MISSION_LIST.stream()
                .filter(mission -> code.equals(mission.getCode()))
                .findFirst().orElse(null);
        if (Objects.isNull(found)) {
            return Ux.future(new JsonObject());
        } else {
            return Ux.future(normalize(found));
        }
    }

    private static JsonObject normalize(final Mission mission) {
        final JsonObject serialized = Ut.serializeJson(mission);
        final JsonObject metadata = serialized.getJsonObject(KeField.METADATA);
        if (Ut.notNil(metadata)) {
            final JsonObject service = metadata.getJsonObject(KeField.SERVICE);
            if (Ut.notNil(service)) {
                Ke.metadata(service, KeField.METADATA);

                /*
                 * Zero standard configuration
                 * 1) Integration
                 * 2) Database
                 * Here should be configuration for `Database` & `Integration`
                 */
                Ke.metadata(service, KeField.Api.CONFIG_INTEGRATION);
                Ke.metadata(service, KeField.Api.CONFIG_DATABASE);

                /*
                 * 1) channelConfig - Channel Component configuration
                 * 2) serviceConfig - Service Component configuration
                 * 3) dictConfig = Dict Component configuration
                 * 4) mappingConfig = Mapping Component configuration
                 */
                Ke.metadata(service, KeField.Api.CHANNEL_CONFIG);
                Ke.metadata(service, KeField.Api.SERVICE_CONFIG);
                Ke.metadata(service, KeField.Api.DICT_CONFIG);
                Ke.metadata(service, KeField.Api.MAPPING_CONFIG);
            }
        }
        return serialized;
    }
}
