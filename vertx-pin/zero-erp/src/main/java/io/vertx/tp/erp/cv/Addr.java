package io.vertx.tp.erp.cv;

interface Prefix {

    String _EVENT = "Ἀτλαντὶς νῆσος://Επιχείρηση/";
}

public interface Addr {

    interface Company {
        String INFORMATION = Prefix._EVENT + "X-COMPANY";
    }
}
