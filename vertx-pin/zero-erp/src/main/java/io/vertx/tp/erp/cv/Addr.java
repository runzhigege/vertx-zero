package io.vertx.tp.erp.cv;

interface Prefix {

    String _EVENT = "Ἀτλαντὶς νῆσος://Επιχείρηση/";
}

public interface Addr {

    interface Company {
        String INFORMATION = Prefix._EVENT + "X-COMPANY";
        String COMPANY_KEY = Prefix._EVENT + "E-COMPANY/KEY";
    }
    interface Dept {
        String DEPT_SIGMA = Prefix._EVENT + "E-DEPT/SIGMA";
    }
}
