/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ui;


import cn.vertxup.ui.tables.UiAjax;
import cn.vertxup.ui.tables.UiColumn;
import cn.vertxup.ui.tables.UiControl;
import cn.vertxup.ui.tables.UiEvent;
import cn.vertxup.ui.tables.UiField;
import cn.vertxup.ui.tables.UiForm;
import cn.vertxup.ui.tables.UiLayout;
import cn.vertxup.ui.tables.UiPage;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>DB_ETERNAL</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index UI_AJAX_NAME = Indexes0.UI_AJAX_NAME;
    public static final Index UI_AJAX_PRIMARY = Indexes0.UI_AJAX_PRIMARY;
    public static final Index UI_COLUMN_CONTROL_ID = Indexes0.UI_COLUMN_CONTROL_ID;
    public static final Index UI_COLUMN_PRIMARY = Indexes0.UI_COLUMN_PRIMARY;
    public static final Index UI_CONTROL_PRIMARY = Indexes0.UI_CONTROL_PRIMARY;
    public static final Index UI_CONTROL_SIGN = Indexes0.UI_CONTROL_SIGN;
    public static final Index UI_EVENT_PRIMARY = Indexes0.UI_EVENT_PRIMARY;
    public static final Index UI_FIELD_FORM_ID = Indexes0.UI_FIELD_FORM_ID;
    public static final Index UI_FIELD_PRIMARY = Indexes0.UI_FIELD_PRIMARY;
    public static final Index UI_FORM_CODE = Indexes0.UI_FORM_CODE;
    public static final Index UI_FORM_PRIMARY = Indexes0.UI_FORM_PRIMARY;
    public static final Index UI_LAYOUT_PATH = Indexes0.UI_LAYOUT_PATH;
    public static final Index UI_LAYOUT_PRIMARY = Indexes0.UI_LAYOUT_PRIMARY;
    public static final Index UI_PAGE_PRIMARY = Indexes0.UI_PAGE_PRIMARY;
    public static final Index UI_PAGE_URL = Indexes0.UI_PAGE_URL;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index UI_AJAX_NAME = Internal.createIndex("NAME", UiAjax.UI_AJAX, new OrderField[] { UiAjax.UI_AJAX.NAME, UiAjax.UI_AJAX.RELATED_ID }, true);
        public static Index UI_AJAX_PRIMARY = Internal.createIndex("PRIMARY", UiAjax.UI_AJAX, new OrderField[] { UiAjax.UI_AJAX.KEY }, true);
        public static Index UI_COLUMN_CONTROL_ID = Internal.createIndex("CONTROL_ID", UiColumn.UI_COLUMN, new OrderField[] { UiColumn.UI_COLUMN.CONTROL_ID, UiColumn.UI_COLUMN.DATA_INDEX }, true);
        public static Index UI_COLUMN_PRIMARY = Internal.createIndex("PRIMARY", UiColumn.UI_COLUMN, new OrderField[] { UiColumn.UI_COLUMN.KEY }, true);
        public static Index UI_CONTROL_PRIMARY = Internal.createIndex("PRIMARY", UiControl.UI_CONTROL, new OrderField[] { UiControl.UI_CONTROL.KEY }, true);
        public static Index UI_CONTROL_SIGN = Internal.createIndex("SIGN", UiControl.UI_CONTROL, new OrderField[] { UiControl.UI_CONTROL.SIGN }, true);
        public static Index UI_EVENT_PRIMARY = Internal.createIndex("PRIMARY", UiEvent.UI_EVENT, new OrderField[] { UiEvent.UI_EVENT.KEY }, true);
        public static Index UI_FIELD_FORM_ID = Internal.createIndex("FORM_ID", UiField.UI_FIELD, new OrderField[] { UiField.UI_FIELD.FORM_ID, UiField.UI_FIELD.NAME }, true);
        public static Index UI_FIELD_PRIMARY = Internal.createIndex("PRIMARY", UiField.UI_FIELD, new OrderField[] { UiField.UI_FIELD.KEY }, true);
        public static Index UI_FORM_CODE = Internal.createIndex("CODE", UiForm.UI_FORM, new OrderField[] { UiForm.UI_FORM.CODE, UiForm.UI_FORM.SIGMA }, true);
        public static Index UI_FORM_PRIMARY = Internal.createIndex("PRIMARY", UiForm.UI_FORM, new OrderField[] { UiForm.UI_FORM.KEY }, true);
        public static Index UI_LAYOUT_PATH = Internal.createIndex("PATH", UiLayout.UI_LAYOUT, new OrderField[] { UiLayout.UI_LAYOUT.PATH, UiLayout.UI_LAYOUT.SIGMA }, true);
        public static Index UI_LAYOUT_PRIMARY = Internal.createIndex("PRIMARY", UiLayout.UI_LAYOUT, new OrderField[] { UiLayout.UI_LAYOUT.KEY }, true);
        public static Index UI_PAGE_PRIMARY = Internal.createIndex("PRIMARY", UiPage.UI_PAGE, new OrderField[] { UiPage.UI_PAGE.KEY }, true);
        public static Index UI_PAGE_URL = Internal.createIndex("URL", UiPage.UI_PAGE, new OrderField[] { UiPage.UI_PAGE.URL, UiPage.UI_PAGE.SIGMA }, true);
    }
}
