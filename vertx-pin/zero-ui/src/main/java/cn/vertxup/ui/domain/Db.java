/*
 * This file is generated by jOOQ.
 */
package cn.vertxup.ui.domain;


import cn.vertxup.ui.domain.tables.*;
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
    private static final long serialVersionUID = 2063536755;
    /**
     * The table <code>DB_ETERNAL.UI_COLUMN</code>.
     */
    public final UiColumn UI_COLUMN = cn.vertxup.ui.domain.tables.UiColumn.UI_COLUMN;

    /**
     * The table <code>DB_ETERNAL.UI_CONTROL</code>.
     */
    public final UiControl UI_CONTROL = cn.vertxup.ui.domain.tables.UiControl.UI_CONTROL;

    /**
     * The table <code>DB_ETERNAL.UI_EVENT</code>.
     */
    public final UiEvent UI_EVENT = cn.vertxup.ui.domain.tables.UiEvent.UI_EVENT;

    /**
     * The table <code>DB_ETERNAL.UI_FIELD</code>.
     */
    public final UiField UI_FIELD = cn.vertxup.ui.domain.tables.UiField.UI_FIELD;

    /**
     * The table <code>DB_ETERNAL.UI_FORM</code>.
     */
    public final UiForm UI_FORM = cn.vertxup.ui.domain.tables.UiForm.UI_FORM;

    /**
     * The table <code>DB_ETERNAL.UI_LAYOUT</code>.
     */
    public final UiLayout UI_LAYOUT = cn.vertxup.ui.domain.tables.UiLayout.UI_LAYOUT;

    /**
     * The table <code>DB_ETERNAL.UI_PAGE</code>.
     */
    public final UiPage UI_PAGE = cn.vertxup.ui.domain.tables.UiPage.UI_PAGE;

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
                UiColumn.UI_COLUMN,
                UiControl.UI_CONTROL,
                UiEvent.UI_EVENT,
                UiField.UI_FIELD,
                UiForm.UI_FORM,
                UiLayout.UI_LAYOUT,
                UiPage.UI_PAGE);
    }
}
