package io.vertx.tp.rbac.cv.em;

/*
 * 【 Group 】Enable group feature to do deeply lookup roles.
 * For example:
 * Group Structure
 *                 G10
 *                /   \
 *              G20   G21
 *             /   \     \
 *           G30   G31   G32
 *
 * 「 User - Group 」
 *       x - G10 - x ( Not belong to )
 *       U - G20 - H
 *       x - G21 - x ( Not belong to )
 *       x - G30 - x ( Not belong to )
 *       U - G31 - M
 *       U - G32 - L
 *
 * All groups must contains current group
 */
public enum SeekGroup {
    /*
     * Default situation ( G20, G31, G32 ) union calculation
     *
     * ->     G20, G31, G32
     *
     * UNION , NO TREE
     */
    HORIZON,
    /*
     * Priority selection, select high priority group ( Only 1 available )
     *
     * ->     G20
     *
     * EAGER , NO TREE
     */
    CRITICAL,
    /*
     * Priority selection, select low priority group ( Only 1 available )
     *
     * ->     G32
     *
     * LAZY,   NO TREE
     */
    OVERLOOK,
    // ---------------- Parent Group Only
    /*
     * Parent Mode ( Pickup Parent Group )
     *
     * ->     G10, G20, G21
     */
    PARENT_HORIZON,
    /*
     * Parent Mode ( Priority Selection High )
     *
     * ->     G10
     */
    PARENT_CRITICAL,
    /*
     * Parent Mode ( Priority Selection Low )
     *
     * ->     G21
     */
    PARENT_OVERLOOK,
    // ---------------- Child Group Only
    /*
     * Child Mode ( Pickup Child Groups )
     *
     * ->     G30, G31
     */
    CHILD_HORIZON,
    /*
     * Child Mode ( Priority Selection High )
     *
     * ->     G30, G31
     */
    CHILD_CRITICAL,
    /*
     * Child Mode ( Priority Selection Low )
     *
     * ->     None
     */
    CHILD_OVERLOOK,
}
