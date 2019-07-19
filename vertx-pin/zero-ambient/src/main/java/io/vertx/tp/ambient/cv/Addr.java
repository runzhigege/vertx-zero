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

    interface Menu {

        String BY_APP_ID = Prefix._EVENT + "X-MENU/APP-ID";
    }

    interface Init {

        String PREPARE = Prefix._EVENT + "X-PREPARE";

        String INIT = Prefix._EVENT + "X-INIT";

        String CONNECT = Prefix._EVENT + "X-CONNECT";
    }

    interface File {

        String UPLOAD = Prefix._EVENT + "X-UPLOAD";

        String DOWNLOAD = Prefix._EVENT + "X-DOWNLOAD";
    }

    interface Datum {

        String CATEGORY_TYPE = Prefix._EVENT + "X-CATEGORY/TYPE";

        String CATEGORY_TYPES = Prefix._EVENT + "X-CATEGORY/TYPES";

        String CATEGORY_CODE = Prefix._EVENT + "X-CATEGORY/CODE";

        String TABULAR_TYPE = Prefix._EVENT + "X-TABULAR/TYPE";

        String TABULAR_TYPES = Prefix._EVENT + "X-TABULAR/TYPES";

        String TABULAR_CODE = Prefix._EVENT + "X-TABULAR/CODE";
    }
}