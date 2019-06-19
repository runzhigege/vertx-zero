package io.vertx.tp.rbac.extension;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.tp.rbac.cv.AuthMsg;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.plugin.extension.region.AbstractRegion;

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
            envelop.onProjection(matrix.getJsonArray(Inquiry.KEY_PROJECTION));
            /* Projection Modification */
            envelop.onCriteria(matrix.getJsonObject(Inquiry.KEY_CRITERIA));
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
