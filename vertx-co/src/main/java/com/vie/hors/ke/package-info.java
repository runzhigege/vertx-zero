/**
 * Defined special package for internal exception definition.
 * 1. This kind of exceptions won't connect any Annal Logger
 * 2. This error messages use vertx logger directly.
 */
package com.vie.hors.ke;

interface Message {
    /** **/
    String NIL_MSG = "[ZERO] Empty stream exception found when {0}, caused = {1}.";
    /** **/
    String JSON_MSG = "[ZERO] The system met decoding/encoding file {0} exception, caused = {1}.";
}
