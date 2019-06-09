package io.vertx.tp.rbac.cv.em;

/*
 * Enable group feature to lookup roles.
 *
 * Group Structure
 *                 G10
 *                /   \
 *              G20   G21
 *             /   \     \
 *           G30   G31   G32
 *
 * > High Priority ( H )
 * > Middle Priority ( M )
 * > Low Priority ( L )
 *
 * 「 User - Group 」
 *       U - G20 - H
 *       U - G31 - M
 *       U - G32 - L
 */
public enum AimGroup {
    /*
     *  「 Group 」
     *   G20, G31, G32
     */
    HORIZON_UNION,

    /*
     *  「 Group 」
     *   G20
     */
    HORIZON_PRIORITY,

    /*
     *  「 Group 」
     *   G10, G20, G21, G31, G32
     */
    HORIZON_UNION_INHERIT,

    /*
     *  「 Group 」
     *   G20, G31, G32, G30
     */
    HORIZON_UNION_CHILD,

    /*
     *  「 Group 」
     *   G10, G20
     */
    HORIZON_PRIORITY_INHERIT,

    /*
     *  「 Group 」
     *   G20, G31, G30
     */
    HORIZON_PRIORITY_CHILD,
}
