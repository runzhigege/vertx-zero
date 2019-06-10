package io.vertx.tp.rbac.authority.detent;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.authority.ProfileRole;
import io.vertx.tp.rbac.authority.ScDetent;

import java.util.List;

public class ScDetentParent implements ScDetent {

    private transient final JsonObject input;

    public ScDetentParent(final JsonObject input) {
        this.input = input;
    }

    @Override
    public JsonObject proc(final List<ProfileRole> profiles) {
        final JsonObject parent = new JsonObject();
        /* SeekGroup -> Parent Horizon */
        parent.mergeIn(ScDetent.Group.Parent.horizon().proc(profiles));
        /* SeekGroup -> Parent Critical */
        parent.mergeIn(ScDetent.Group.Parent.critical().proc(profiles));
        /* SeekGroup -> Parent Priority */
        parent.mergeIn(ScDetent.Group.Parent.overlook().proc(profiles));
        return this.input.mergeIn(parent);
    }
}
