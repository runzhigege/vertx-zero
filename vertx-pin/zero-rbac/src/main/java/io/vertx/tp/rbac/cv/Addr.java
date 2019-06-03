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

        String AUTHORIZE = Prefix._EVENT + "O-AUTHORIZE";

        String TOKEN = Prefix._EVENT + "O-TOKEN";
    }
}
