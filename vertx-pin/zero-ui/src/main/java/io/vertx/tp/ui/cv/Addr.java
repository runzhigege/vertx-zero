package io.vertx.tp.ui.cv;

/**
 * Address for Event Bus
 */
interface Prefix {
    /*
     *
     */
    String _EVENT = "Ἀτλαντὶς νῆσος://Διασύνδεση χρήστη/";
}

public interface Addr {

    interface Page {

        String FETCH_AMP = Prefix._EVENT + "X-FETCH/AMP";
    }
}
