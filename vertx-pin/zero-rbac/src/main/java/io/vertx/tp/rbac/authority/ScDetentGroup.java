package io.vertx.tp.rbac.authority;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.AuthKey;

import java.util.List;

/*
 * Horizon Group
 */
public class ScDetentGroup implements ScDetent {

    private transient final JsonObject input;

    ScDetentGroup(final JsonObject input) {
        this.input = input;
    }

    @Override
    public JsonObject proc(final List<ProfileRole> profiles) {
        final JsonObject group = new JsonObject();
        /* SeekGroup -> Horizon */
        group.mergeIn(ScDetent.Group.horizon(group).proc(profiles));
        
        return this.input.put(AuthKey.GROUP_AUTHORITIES, group);
    }
}
