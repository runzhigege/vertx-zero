/*
 * This file is generated by jOOQ.
 */
package cn.vertxup.ambient.domain;


import cn.vertxup.ambient.domain.tables.*;
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
    private static final long serialVersionUID = 1371895717;
    /**
     * The table <code>DB_ETERNAL.X_APP</code>.
     */
    public final XApp X_APP = cn.vertxup.ambient.domain.tables.XApp.X_APP;

    /**
     * The table <code>DB_ETERNAL.X_ATTACHMENT</code>.
     */
    public final XAttachment X_ATTACHMENT = cn.vertxup.ambient.domain.tables.XAttachment.X_ATTACHMENT;

    /**
     * The table <code>DB_ETERNAL.X_CATEGORY</code>.
     */
    public final XCategory X_CATEGORY = cn.vertxup.ambient.domain.tables.XCategory.X_CATEGORY;

    /**
     * The table <code>DB_ETERNAL.X_MENU</code>.
     */
    public final XMenu X_MENU = cn.vertxup.ambient.domain.tables.XMenu.X_MENU;

    /**
     * The table <code>DB_ETERNAL.X_MODULE</code>.
     */
    public final XModule X_MODULE = cn.vertxup.ambient.domain.tables.XModule.X_MODULE;

    /**
     * The table <code>DB_ETERNAL.X_NUMBER</code>.
     */
    public final XNumber X_NUMBER = cn.vertxup.ambient.domain.tables.XNumber.X_NUMBER;

    /**
     * The table <code>DB_ETERNAL.X_SOURCE</code>.
     */
    public final XSource X_SOURCE = cn.vertxup.ambient.domain.tables.XSource.X_SOURCE;

    /**
     * The table <code>DB_ETERNAL.X_TABULAR</code>.
     */
    public final XTabular X_TABULAR = cn.vertxup.ambient.domain.tables.XTabular.X_TABULAR;

    /**
     * The table <code>DB_ETERNAL.X_TODO</code>.
     */
    public final XTodo X_TODO = cn.vertxup.ambient.domain.tables.XTodo.X_TODO;

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
                XApp.X_APP,
                XAttachment.X_ATTACHMENT,
                XCategory.X_CATEGORY,
                XMenu.X_MENU,
                XModule.X_MODULE,
                XNumber.X_NUMBER,
                XSource.X_SOURCE,
                XTabular.X_TABULAR,
                XTodo.X_TODO);
    }
}
