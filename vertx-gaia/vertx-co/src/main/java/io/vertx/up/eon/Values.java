package io.vertx.up.eon;

import java.nio.charset.Charset;

/**
 * Value constants
 */
public interface Values {
    /* Buffer size */
    int BUFFER_SIZE = 16;

    String ENCODING = "UTF-8";

    String ENCODING_ISO_8859_1 = "ISO-8859-1";

    Charset CHARSET = Charset.forName(ENCODING);

    int UNSET = -1;

    int CODECS = UNSET;

    int ZERO = 0;

    int IDX = 0;

    int RANGE = -1;

    int ONE = 1;

    int SINGLE = ONE;

    int TWO = 2;

    String TRUE = "true";

    String FALSE = "false";

    String CONFIG_INTERNAL = "up";

    String CONFIG_INTERNAL_RULE = CONFIG_INTERNAL + "/rules/{0}.yml";
    String CONFIG_INTERNAL_FILE = CONFIG_INTERNAL + "/config/";
    String CONFIG_INTERNAL_PACKAGE = CONFIG_INTERNAL_FILE + "vertx-package-filter.json";
    char[] HEX_ARR = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
}
