/*
 * This file is generated by jOOQ.
*/
package up.zero.domain;


import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;

import up.zero.domain.tables.SRole;


/**
 * A class modelling indexes of tables of the <code>UP_ARES</code> schema.
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

    public static final Index S_ROLE_CODE = Indexes0.S_ROLE_CODE;
    public static final Index S_ROLE_PRIMARY = Indexes0.S_ROLE_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index S_ROLE_CODE = Internal.createIndex("CODE", SRole.S_ROLE, new OrderField[] { SRole.S_ROLE.CODE, SRole.S_ROLE.SIGMA }, true);
        public static Index S_ROLE_PRIMARY = Internal.createIndex("PRIMARY", SRole.S_ROLE, new OrderField[] { SRole.S_ROLE.KEY }, true);
    }
}
