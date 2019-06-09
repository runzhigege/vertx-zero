package io.vertx.tp.rbac.cv.em;

/*
 * Resource required.
 */
public enum AimResource {
    /*
     * User + ( Group Result )
     */
    UNION,
    /*
     * User High
     */
    PRIORITY_USER,
    /*
     * Group High
     */
    PRIORITY_GROUP,
    /*
     * User x Group ( < User, < Group )
     */
    INTERSECT
}
