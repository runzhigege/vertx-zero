/**
 * Defined special package for internal exception definition.
 * 1. This kind of exceptions won't connect any Annal Logger
 * 2. This error messages use vertx logger directly.
 */
package io.vertx.up.exception.heart;

interface Info {

    String NIL_MSG =
            "[ ZERO ] Empty stream exception found when {0}, caused = {1}.";

    String JSON_MSG =
            "[ ZERO ] The system met decoding/encoding up.god.file {0} exception, caused = {1}.";

    String ECODE_MSG =
            "[ ZERO ] The code = {0} of error is missing in your up.god.file: " +
                    "vertx-error.yml, callee = {1}.";

    String ARG_MSG =
            "[ ZERO ] The method \"{0}\" of class \"{1}\" accept " +
                    "({3} {2}) arguments only, the length is conflict";
    String LIME_FILE = "[ ZERO ] Lime node configured up.god.file = \"{0}\"" +
            " is missing, please check the missed up.god.file";

    String OP_MSG = "[ ZERO ] This operation is not supported! " +
            "( method = {0}, class = {1} )";

    String JEXL_MSG =
            "[ ZERO ] The expression \"{0}\" could not be parsed, details = {1}";

    String DATE_MSG =
            "[ ZERO ] The input `{0}` could not be parsed to valid date";
}
