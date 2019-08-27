/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ambient.domain;


import cn.vertxup.ambient.domain.tables.XApp;
import cn.vertxup.ambient.domain.tables.XAttachment;
import cn.vertxup.ambient.domain.tables.XCategory;
import cn.vertxup.ambient.domain.tables.XMenu;
import cn.vertxup.ambient.domain.tables.XModule;
import cn.vertxup.ambient.domain.tables.XNumber;
import cn.vertxup.ambient.domain.tables.XSource;
import cn.vertxup.ambient.domain.tables.XTabular;
import cn.vertxup.ambient.domain.tables.XTodo;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in DB_ETERNAL
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>DB_ETERNAL.X_APP</code>.
     */
    public static final XApp X_APP = cn.vertxup.ambient.domain.tables.XApp.X_APP;

    /**
     * The table <code>DB_ETERNAL.X_ATTACHMENT</code>.
     */
    public static final XAttachment X_ATTACHMENT = cn.vertxup.ambient.domain.tables.XAttachment.X_ATTACHMENT;

    /**
     * The table <code>DB_ETERNAL.X_CATEGORY</code>.
     */
    public static final XCategory X_CATEGORY = cn.vertxup.ambient.domain.tables.XCategory.X_CATEGORY;

    /**
     * The table <code>DB_ETERNAL.X_MENU</code>.
     */
    public static final XMenu X_MENU = cn.vertxup.ambient.domain.tables.XMenu.X_MENU;

    /**
     * The table <code>DB_ETERNAL.X_MODULE</code>.
     */
    public static final XModule X_MODULE = cn.vertxup.ambient.domain.tables.XModule.X_MODULE;

    /**
     * The table <code>DB_ETERNAL.X_NUMBER</code>.
     */
    public static final XNumber X_NUMBER = cn.vertxup.ambient.domain.tables.XNumber.X_NUMBER;

    /**
     * The table <code>DB_ETERNAL.X_SOURCE</code>.
     */
    public static final XSource X_SOURCE = cn.vertxup.ambient.domain.tables.XSource.X_SOURCE;

    /**
     * The table <code>DB_ETERNAL.X_TABULAR</code>.
     */
    public static final XTabular X_TABULAR = cn.vertxup.ambient.domain.tables.XTabular.X_TABULAR;

    /**
     * The table <code>DB_ETERNAL.X_TODO</code>.
     */
    public static final XTodo X_TODO = cn.vertxup.ambient.domain.tables.XTodo.X_TODO;
}
