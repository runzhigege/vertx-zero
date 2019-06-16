package io.vertx.tp.rbac.extension;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.cv.em.RegionType;
import io.vertx.tp.rbac.extension.dwarf.DataDwarf;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.log.Annal;
import io.vertx.up.web.ZeroAnno;
import io.vertx.zero.eon.Strings;
import io.vertx.zero.eon.Values;
import io.zero.epic.Ut;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class DataTool {

    private static final Annal LOGGER = Annal.get(DataTool.class);

    static JsonObject fetchMatrix(final RoutingContext context) {
        /* Session Extract */
        final Session session = context.session();
        final HttpServerRequest request = context.request();
        final HttpMethod method = request.method();
        final String recoveryUri = ZeroAnno.recoveryUri(request.uri(), method);
        /* Cache Key */
        final String cacheKey = "session-" + request.method().name() + ":" + recoveryUri;
        /* CacheData */
        final Buffer buffer = session.get(cacheKey);
        if (Objects.nonNull(buffer)) {
            return buffer.toJsonObject();
        } else {
            return new JsonObject();
        }
    }

    static void updateProjection(final Envelop envelop, final JsonArray projection) {
        if (Objects.nonNull(projection) && !projection.isEmpty()) {
            final JsonObject referenceForParameters = envelop.regionInput();
            if (Objects.nonNull(referenceForParameters)) {
                /*
                 * If no parameters
                 */
                final Set<String> projectionSet = new HashSet<>();
                if (referenceForParameters.containsKey(Inquiry.KEY_PROJECTION)) {
                    referenceForParameters.getJsonArray(Inquiry.KEY_PROJECTION).stream()
                            .map(item -> (String) item).forEach(projectionSet::add);
                }
                projection.stream()
                        .map(item -> (String) item).forEach(projectionSet::add);
                /*
                 * Replace
                 */
                referenceForParameters.put(Inquiry.KEY_PROJECTION, Ut.toJArray(projectionSet));
            }
        }
    }

    static void updateCriteria(final Envelop envelop, final JsonObject criteria) {
        if (Objects.nonNull(criteria) && !criteria.isEmpty()) {
            final JsonObject referenceForParameters = envelop.regionInput();
            if (Objects.nonNull(referenceForParameters)) {
                /*
                 * If no parameters
                 */
                final JsonObject criteriaResult = new JsonObject();
                if (referenceForParameters.containsKey(Inquiry.KEY_CRITERIA)) {
                    criteriaResult.put(Strings.EMPTY, Boolean.TRUE);
                    criteriaResult.put(AuthKey.TREE_ORIGINAL, referenceForParameters.getJsonObject(Inquiry.KEY_CRITERIA).copy());
                    criteriaResult.put(AuthKey.TREE_MATRIX, criteria);
                } else {
                    criteriaResult.mergeIn(referenceForParameters.getJsonObject(Inquiry.KEY_CRITERIA));
                }
                /*
                 * Replace
                 */
                referenceForParameters.put(Inquiry.KEY_CRITERIA, criteriaResult);
            }
        }
    }

    /*
     * projection on result
     * RegionType.RECORD
     */
    @SuppressWarnings("all")
    static void dwarfRecord(final Envelop envelop, final JsonObject matrix) {
        final JsonArray projection = matrix.getJsonArray(Inquiry.KEY_PROJECTION);
        if (Objects.nonNull(projection) && !projection.isEmpty()) {
            final JsonObject responseJson = envelop.responseJson();
            if (Objects.nonNull(responseJson)) {
                final RegionType type = analyzeRegion(responseJson);
                Sc.infoAuth(LOGGER, AuthMsg.REGION_TYPE, type, responseJson.encode());
                if (RegionType.RECORD == type) {
                    DataDwarf.create(type).minimize(responseJson, matrix);
                }
            }
        }
    }

    /*
     * rows on result
     * RegionType.PAGINATION
     * RegionType.ARRAY
     */
    @SuppressWarnings("all")
    static void dwarfCollection(final Envelop envelop, final JsonObject matrix) {
        final JsonObject rows = matrix.getJsonObject("rows");
        if (Objects.nonNull(rows) && !rows.isEmpty()) {
            final JsonObject responseJson = envelop.responseJson();
            if (Objects.nonNull(responseJson)) {
                final RegionType type = analyzeRegion(responseJson);
                Sc.infoAuth(LOGGER, AuthMsg.REGION_TYPE, type, responseJson.encode());
                if (RegionType.ARRAY == type || RegionType.PAGINATION == type) {
                    DataDwarf.create(type).minimize(responseJson, matrix);
                }
            }
        }
    }

    /*
     * There are three data format that could be enabled for region.
     * 1. Json Object:
     * {
     *     "data": {}
     * }
     * 2. Pagination
     * {
     *     "data": {
     *         "list": [],
     *         "count": xxx
     *     }
     * }
     * 3. Json Array:
     * {
     *     "data": []
     * }
     */
    private static RegionType analyzeRegion(final JsonObject reference) {
        /* Response Data Extract */
        /* Extract Data Object */
        final Object value = reference.getValue("data");
        if (Objects.nonNull(value)) {
            if (Ut.isJArray(value)) {
                /* value = JsonArray */
                return RegionType.ARRAY;
            } else if (Ut.isJObject(value)) {
                /* Distinguish between Pagination / Object */
                final JsonObject json = (JsonObject) value;
                if (json.containsKey("list") && json.containsKey("count")
                        && Values.TWO == json.size()) {
                    return RegionType.PAGINATION;
                } else {
                    return RegionType.RECORD;
                }
            } else {
                /* value = Other, Region Disabled */
                return RegionType.FORBIDDEN;
            }
        } else {
            /* value = null , Region Disabled */
            return RegionType.FORBIDDEN;
        }
    }
}
