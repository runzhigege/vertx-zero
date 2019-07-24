package io.vertx.tp.rbac.atom;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.rbac.cv.AuthKey;
import io.vertx.tp.rbac.refine.Sc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
 * Single middle fetchProfile for group
 */
public class ProfileGroup implements Serializable {

    private transient final String groupId;
    private transient final Integer priority;
    private transient final JsonArray role;
    private transient final List<ProfileRole> roles = new ArrayList<>();
    private transient String reference;

    public ProfileGroup(final JsonObject data) {
        /* Group Id */
        groupId = data.getString(AuthKey.F_GROUP_ID);
        /* Priority */
        priority = data.getInteger(AuthKey.PRIORITY);
        /* Role */
        role = null == data.getJsonArray("role")
                ? new JsonArray() : data.getJsonArray("role");
    }

    Future<ProfileGroup> initAsync() {
        /* No determine */
        return fetchProfilesAsync().compose(profiles -> {
            /* Clear and add */
            setRoles(profiles);
            return Future.succeededFuture(this);
        });
    }

    public ProfileGroup init() {
        setRoles(fetchProfiles());
        return this;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getKey() {
        return groupId;
    }

    public List<ProfileRole> getRoles() {
        return roles;
    }

    private void setRoles(final List<ProfileRole> profiles) {
        roles.clear();
        roles.addAll(profiles);
    }

    /*
     * Parent Reference for current fetchProfile group
     * */
    public String getReference() {
        return reference;
    }

    public ProfileGroup setReference(final String reference) {
        this.reference = reference;
        return this;
    }

    /*
     * Extract the latest relations: initAsync role for each group fetchProfile
     */
    @SuppressWarnings("all")
    private Future<List<ProfileRole>> fetchProfilesAsync() {
        final List futures = new ArrayList();
        this.role.stream().filter(Objects::nonNull)
                .map(item -> (JsonObject) item)
                .map(ProfileRole::new)
                .map(ProfileRole::initAsync)
                .forEach(futures::add);
        return CompositeFuture.all(futures)
                /* Composite Result */
                .compose(Sc::<ProfileRole>composite)
                .compose(profiles -> {
                    /* Bind each fetchProfile to group Id */
                    profiles.forEach(profile -> profile.setGroup(this));
                    return Future.succeededFuture(profiles);
                });
    }

    private List<ProfileRole> fetchProfiles() {
        return role.stream().filter(Objects::nonNull)
                .map(item -> (JsonObject) item)
                .map(ProfileRole::new)
                .map(ProfileRole::init)
                .map(role -> role.setGroup(this))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfileGroup)) {
            return false;
        }
        final ProfileGroup that = (ProfileGroup) o;
        return groupId.equals(that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId);
    }

    @Override
    public String toString() {
        return "ProfileGroup{" +
                "groupId='" + groupId + '\'' +
                ", priority=" + priority +
                ", role=" + role +
                ", reference='" + reference + '\'' +
                '}';
    }
}
