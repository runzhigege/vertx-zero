package io.vertx.tp.rbac.authority;

import io.vertx.tp.rbac.cv.em.SeekGroup;
import io.vertx.tp.rbac.cv.em.SeekRole;

import java.io.Serializable;
import java.util.Objects;

/*
 * Profile Type for role/group seeking
 * 1) SeekRole:  role seeking
 * 2) SeekGroup: group seeking
 */
public class ProfileType implements Serializable {
    /* Role Profile */
    static ProfileType UNION = new ProfileType(SeekRole.UNION);          // U
    static ProfileType EAGER = new ProfileType(SeekRole.EAGER);          // E
    static ProfileType LAZY = new ProfileType(SeekRole.LAZY);            // L
    static ProfileType INTERSECT = new ProfileType(SeekRole.INTERSECT);  // I

    /* Group : HORIZON ->  Role ( U, E, L, I ) */
    static ProfileType HORIZON_UNION = new ProfileType(SeekRole.UNION, SeekGroup.HORIZON);
    static ProfileType HORIZON_EAGER = new ProfileType(SeekRole.EAGER, SeekGroup.HORIZON);
    static ProfileType HORIZON_LAZY = new ProfileType(SeekRole.LAZY, SeekGroup.HORIZON);
    static ProfileType HORIZON_INTERSECT = new ProfileType(SeekRole.INTERSECT, SeekGroup.HORIZON);
    /* Group : CRITICAL -> Role ( U, E, L, I ) */
    static ProfileType CRITICAL_UNION = new ProfileType(SeekRole.UNION, SeekGroup.CRITICAL);
    static ProfileType CRITICAL_EAGER = new ProfileType(SeekRole.EAGER, SeekGroup.CRITICAL);
    static ProfileType CRITICAL_LAZY = new ProfileType(SeekRole.LAZY, SeekGroup.CRITICAL);
    static ProfileType CRITICAL_INTERSECT = new ProfileType(SeekRole.INTERSECT, SeekGroup.CRITICAL);
    /* Group : OVERLOOK -> Role ( U, E, L, I ) */
    static ProfileType OVERLOOK_UNION = new ProfileType(SeekRole.UNION, SeekGroup.OVERLOOK);
    static ProfileType OVERLOOK_EAGER = new ProfileType(SeekRole.EAGER, SeekGroup.OVERLOOK);
    static ProfileType OVERLOOK_LAZY = new ProfileType(SeekRole.LAZY, SeekGroup.OVERLOOK);
    static ProfileType OVERLOOK_INTERSECT = new ProfileType(SeekRole.INTERSECT, SeekGroup.OVERLOOK);

    /* Private Variable */
    private final SeekRole role;
    private final SeekGroup group;

    private ProfileType(final SeekRole role) {
        this(role, null);
    }

    private ProfileType(final SeekRole role, final SeekGroup group) {
        this.role = role;
        this.group = group;
    }

    public String getKey() {
        /* Group,User - Role */
        if (null == this.group) {
            return "USER-" + this.role.name();
        } else {
            return this.group.name() + "-" + this.role.name();
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfileType)) {
            return false;
        }
        final ProfileType that = (ProfileType) o;
        return this.role == that.role &&
                this.group == that.group;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.role, this.group);
    }
}