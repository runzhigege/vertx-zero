package io.vertx.tp.ambient.cv;

/*
 * Address for Event Bus of Ambient
 */
interface Prefix {

    String _EVENT = "Ἀτλαντὶς νῆσος://περιβάλλων/";
}

public interface Addr {

    interface App {

        String BY_NAME = Prefix._EVENT + "X-APP";
    }
}
