/*
 * This file is generated by jOOQ.
 */
package cn.vertxup.domain;


import cn.vertxup.domain.tables.*;
import io.vertx.tp.ke.refine.Ke;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.10.8"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Db extends SchemaImpl {

    /**
     * The reference instance of <code>DB_ETERNAL</code>
     */
    public static final Db DB_ETERNAL = new Db();
    private static final long serialVersionUID = 587967189;
    /**
     * The table <code>DB_ETERNAL.O_ACCESS_TOKEN</code>.
     */
    public final OAccessToken O_ACCESS_TOKEN = cn.vertxup.domain.tables.OAccessToken.O_ACCESS_TOKEN;

    /**
     * The table <code>DB_ETERNAL.O_USER</code>.
     */
    public final OUser O_USER = cn.vertxup.domain.tables.OUser.O_USER;

    /**
     * The table <code>DB_ETERNAL.R_GROUP_ROLE</code>.
     */
    public final RGroupRole R_GROUP_ROLE = cn.vertxup.domain.tables.RGroupRole.R_GROUP_ROLE;

    /**
     * The table <code>DB_ETERNAL.R_ROLE_PERM</code>.
     */
    public final RRolePerm R_ROLE_PERM = cn.vertxup.domain.tables.RRolePerm.R_ROLE_PERM;

    /**
     * The table <code>DB_ETERNAL.R_USER_GROUP</code>.
     */
    public final RUserGroup R_USER_GROUP = cn.vertxup.domain.tables.RUserGroup.R_USER_GROUP;

    /**
     * The table <code>DB_ETERNAL.R_USER_ROLE</code>.
     */
    public final RUserRole R_USER_ROLE = cn.vertxup.domain.tables.RUserRole.R_USER_ROLE;

    /**
     * The table <code>DB_ETERNAL.S_ACTION</code>.
     */
    public final SAction S_ACTION = cn.vertxup.domain.tables.SAction.S_ACTION;

    /**
     * The table <code>DB_ETERNAL.S_GROUP</code>.
     */
    public final SGroup S_GROUP = cn.vertxup.domain.tables.SGroup.S_GROUP;

    /**
     * The table <code>DB_ETERNAL.S_PERMISSION</code>.
     */
    public final SPermission S_PERMISSION = cn.vertxup.domain.tables.SPermission.S_PERMISSION;

    /**
     * The table <code>DB_ETERNAL.S_RESOURCE</code>.
     */
    public final SResource S_RESOURCE = cn.vertxup.domain.tables.SResource.S_RESOURCE;

    /**
     * The table <code>DB_ETERNAL.S_ROLE</code>.
     */
    public final SRole S_ROLE = cn.vertxup.domain.tables.SRole.S_ROLE;

    /**
     * The table <code>DB_ETERNAL.S_USER</code>.
     */
    public final SUser S_USER = cn.vertxup.domain.tables.SUser.S_USER;

    /**
     * The table <code>DB_ETERNAL.S_VIEW</code>.
     */
    public final SView S_VIEW = cn.vertxup.domain.tables.SView.S_VIEW;

    /**
     * No further instances allowed
     */
    private Db() {
        super(Ke.getDatabase(), null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
                OAccessToken.O_ACCESS_TOKEN,
                OUser.O_USER,
                RGroupRole.R_GROUP_ROLE,
                RRolePerm.R_ROLE_PERM,
                RUserGroup.R_USER_GROUP,
                RUserRole.R_USER_ROLE,
                SAction.S_ACTION,
                SGroup.S_GROUP,
                SPermission.S_PERMISSION,
                SResource.S_RESOURCE,
                SRole.S_ROLE,
                SUser.S_USER,
                SView.S_VIEW);
    }
}
