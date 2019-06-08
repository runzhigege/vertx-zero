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
    /* User Profile */
    static ProfileType UNION = new ProfileType(SeekRole.UNION);          // U
    static ProfileType EAGER = new ProfileType(SeekRole.EAGER);          // E
    static ProfileType LAZY = new ProfileType(SeekRole.LAZY);            // L
    static ProfileType INTERSECT = new ProfileType(SeekRole.INTERSECT);  // I

    /* Group : HORIZON ->  User ( U, E, L, I ) */
    static ProfileType HORIZON_U = new ProfileType(SeekRole.UNION, SeekGroup.HORIZON);
    static ProfileType HORIZON_E = new ProfileType(SeekRole.EAGER, SeekGroup.HORIZON);
    static ProfileType HORIZON_L = new ProfileType(SeekRole.LAZY, SeekGroup.HORIZON);
    static ProfileType HORIZON_I = new ProfileType(SeekRole.INTERSECT, SeekGroup.HORIZON);

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
        /* Role / Group */
        if (null == this.group) {
            return this.role.name();
        } else {
            return this.role.name() + "-" + this.group.name();
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