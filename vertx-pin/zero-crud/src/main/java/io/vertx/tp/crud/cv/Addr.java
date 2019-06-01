package io.vertx.tp.crud.cv;

/**
 * Address for Event Bus
 */
interface Prefix {
    /*
     * Distinguish from original definition or user defined event.
     */
    String _EVENT = "Ἀτλαντὶς νῆσος://μονάδα μέτρησης/";
}

public interface Addr {

    interface Get {

        String BY_ID = Prefix._EVENT + "X-GET/ID";
    }

    interface Delete {

        String BY_ID = Prefix._EVENT + "X-DELETE/ID";
    }

    interface Post {

        String ADD = Prefix._EVENT + "X-ADD";
    }

    interface Put {

        String BY_ID = Prefix._EVENT + "X-PUT/ID";
    }
}
