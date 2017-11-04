/**
 * Defined special package for internal exception definition.
 * 1. This kind of exceptions won't connect any Annal Logger
 * 2. This error messages use vertx logger directly.
 */
package com.vie.hors.ke;

interface Message {
    /** **/
    String NIL_MSG = "[Zero] Empty stream exception found when {0}, caused = {1}";
    /** **/
    String JVM_MSG = "[Zero] Met jvm checked exception, caused = {0}";
}
