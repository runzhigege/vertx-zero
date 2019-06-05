package io.vertx.tp.rbac.cv.em;

/*
 * Range calculation for each role ( Multi Roles )
 * Support 3 types
 *
 * Xor could not be support because it often limit user to access resource
 * and it's not common used.
 *
 * > High Priority ( H )
 * > Low Priority ( L )
 *
 * 「 Role - User 」
 *       U - R1 - H
 *       U - R2 - L
 *
 */
public enum AimRole {
    /*
     * Default ( R1, R2 ) union calculation
     * 「 Role 」
     *  R1, R2
     */
    UNION,
    /*
     * Priority Select ( R1, R2 ), select high priority role （ Only 1 available )
     * 「 Role 」
     *  R1
     */
    PRIORITY,
    /*
     * Intersect Select, ( High Limitation ), select shared part of ( R1, R2 )
     * 「 Role 」
     *  R1 x R2
     * *: Result should be ( < R1, < R2 )
     */
    INTERSECT
}
