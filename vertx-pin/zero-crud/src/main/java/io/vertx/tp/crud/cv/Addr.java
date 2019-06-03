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

        String COLUMN_FULL = Prefix._EVENT + "X-COLUMN/FULL";

        String COLUMN_MY = Prefix._EVENT + "X-COLUMN/MY";
    }

    interface Delete {

        String BY_ID = Prefix._EVENT + "X-DELETE/ID";

        String BATCH = Prefix._EVENT + "X-DELETE/BATCH";
    }

    interface Post {

        String ADD = Prefix._EVENT + "X-ADD";

        String SEARCH = Prefix._EVENT + "X-SEARCH";
    }

    interface Put {

        String BY_ID = Prefix._EVENT + "X-PUT/ID";

        String BATCH = Prefix._EVENT + "X-PUT/BATCH";
    }
}
