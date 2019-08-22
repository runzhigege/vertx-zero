package io.vertx.tp.lbs.cv;

interface Prefix {

    String _EVENT = "Ἀτλαντὶς νῆσος://Διεύθυνση/";
}

public interface Addr {
    /*
     * Interface for list selector only
     */
    interface PickUp {
        String REGION_META = Prefix._EVENT + "L-REGION/META";

        String REGION_BY_CITY = Prefix._EVENT + "L-REGION/BY-CITY";

        String CITY_BY_STATE = Prefix._EVENT + "L-CITY/BY-STATE";

        String STATE_BY_COUNTRY = Prefix._EVENT + "L-STATE/BY-COUNTRY";

        String COUNTRIES = Prefix._EVENT + "L-COUNTRIES";
    }
}
