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
    public static ProfileType UNION = new ProfileType(SeekRole.UNION);
    public static ProfileType EAGER = new ProfileType(SeekRole.EAGER);
    public static ProfileType LAZY = new ProfileType(SeekRole.LAZY);
    public static ProfileType INTERSECT = new ProfileType(SeekRole.INTERSECT);
    /* Group Profile */

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