package io.vertx.tp.rbac.cv;

/**
 * Address for Event Bus
 */
interface Prefix {

    String _EVENT = "Ἀτλαντὶς νῆσος://Ασφάλεια/";
}

public interface Addr {

    interface Auth {

        String LOGIN = Prefix._EVENT + "O-LOGIN";

        String LOGOUT = Prefix._EVENT + "O-LOGOUT";

        String AUTHORIZE = Prefix._EVENT + "O-AUTHORIZE";

        String TOKEN = Prefix._EVENT + "O-TOKEN";
    }

    interface User {
        String INFORMATION = Prefix._EVENT + "X-INFORMATION";

        String PASSWORD = Prefix._EVENT + "X-PASSWORD";

        String PROFILE = Prefix._EVENT + "X-PROFILE";
    }
}
