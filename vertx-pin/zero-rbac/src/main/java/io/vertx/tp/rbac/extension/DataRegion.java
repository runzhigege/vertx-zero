package io.vertx.tp.rbac.extension;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.plugin.extension.region.AbstractRegion;

import java.util.Objects;

/*
 * Extension in RBAC module
 */
public class DataRegion extends AbstractRegion {

    @Override
    public void before(final RoutingContext context, final Envelop envelop) {
        if (this.isEnabled(context)) {
            /* Get Critical parameters */
            final JsonObject matrix = DataTool.fetchMatrix(context);
            Sc.infoAuth(this.getLogger(), AuthMsg.REGION_BEFORE, matrix.encode());
            /* Projection Modification */
            final JsonArray projection = matrix.getJsonArray(Inquiry.KEY_PROJECTION);
            if (Objects.nonNull(projection) && !projection.isEmpty()) {
                envelop.onProjection(projection);
            }
            /* Criteria Modification */
            final JsonObject criteria = matrix.getJsonObject(Inquiry.KEY_CRITERIA);
            if (Objects.nonNull(criteria) && !criteria.isEmpty()) {
                envelop.onCriteria(criteria);
            }
        }
    }

    @Override
    public void after(final RoutingContext context, final Envelop response) {
        if (this.isEnabled(context)) {
            /* Get Critical parameters */
            final JsonObject matrix = DataTool.fetchMatrix(context);
            Sc.infoAuth(this.getLogger(), AuthMsg.REGION_AFTER, matrix.encode());
            /* Projection */
            DataTool.dwarfRecord(response, matrix);
            /* Rows */
            DataTool.dwarfCollection(response, matrix);
        }
    }
}
