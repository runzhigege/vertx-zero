package io.vertx.tp.rbac.extension;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.tp.rbac.atom.ScUri;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.cv.em.RegionType;
import io.vertx.tp.rbac.extension.dwarf.DataDwarf;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Values;
import io.zero.epic.Ut;

import java.util.Objects;

class DataTool {

    private static final Annal LOGGER = Annal.get(DataTool.class);

    private static String fetchKey(final RoutingContext context) {
        final HttpServerRequest request = context.request();
        final String uri = ScUri.getUriId(context);
        /* Cache Key */
        final String cacheKey = "session-" + request.method().name() + ":" + uri;
        /* Cache Data */
        Sc.infoAuth(LOGGER, "Try cacheKey: {0}", cacheKey);

        return cacheKey;
    }

    static JsonObject fetchMatrix(final RoutingContext context) {
        /* Session Extract */
        final Session session = context.session();
        /* Cache Key */
        final String cacheKey = fetchKey(context);
        final Buffer buffer = session.get(cacheKey);
        if (Objects.nonNull(buffer)) {
            return buffer.toJsonObject();
        } else {
            return new JsonObject();
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
            final JsonObject responseJson = envelop.outJson();
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
            final JsonObject responseJson = envelop.outJson();
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
