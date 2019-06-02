/*
 * This file is generated by jOOQ.
*/
package up.zero.domain;


import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;

import up.zero.domain.tables.SRole;
import up.zero.domain.tables.SUser;


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
    public static final Index S_USER_EMAIL = Indexes0.S_USER_EMAIL;
    public static final Index S_USER_MOBILE = Indexes0.S_USER_MOBILE;
    public static final Index S_USER_PRIMARY = Indexes0.S_USER_PRIMARY;
    public static final Index S_USER_USERNAME = Indexes0.S_USER_USERNAME;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index S_ROLE_CODE = Internal.createIndex("CODE", SRole.S_ROLE, new OrderField[] { SRole.S_ROLE.CODE, SRole.S_ROLE.SIGMA }, true);
        public static Index S_ROLE_PRIMARY = Internal.createIndex("PRIMARY", SRole.S_ROLE, new OrderField[] { SRole.S_ROLE.KEY }, true);
        public static Index S_USER_EMAIL = Internal.createIndex("EMAIL", SUser.S_USER, new OrderField[] { SUser.S_USER.EMAIL, SUser.S_USER.SIGMA }, true);
        public static Index S_USER_MOBILE = Internal.createIndex("MOBILE", SUser.S_USER, new OrderField[] { SUser.S_USER.MOBILE, SUser.S_USER.SIGMA }, true);
        public static Index S_USER_PRIMARY = Internal.createIndex("PRIMARY", SUser.S_USER, new OrderField[] { SUser.S_USER.KEY }, true);
        public static Index S_USER_USERNAME = Internal.createIndex("USERNAME", SUser.S_USER, new OrderField[] { SUser.S_USER.USERNAME, SUser.S_USER.SIGMA }, true);
    }
}
