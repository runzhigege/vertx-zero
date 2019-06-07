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

    // UNION ( HORIZON )
    /*
     * Inherit group role from children
     *
     * ->     G20, G31, G32 :: G30
     */
    /*
     * ( DOWN + LIMIT )
     * Calculation: Child - Current
     */
    DL_HORIZON,
    /*
     * ( DOWN + EXTEND )
     * Calculation: Child + Current
     */
    DE_HORIZON,

    /*
     * Inherit group role from parent
     *
     * ->     G20, G31, G32 :: G21, G10
     */
    /*
     * ( UP + LIMIT )
     * Calculation: Parent - Current
     */
    UL_HORIZON,
    /*
     * ( UP + EXTEND )
     * Calculation: Parent + Current
     */
    UE_HORIZON,

    // EAGER ( CRITICAL )
    /*
     * Priority selection, High
     *
     * ->     G20 :: G30, G31
     */
    /*
     * ( UP + LIMIT )
     * Calculation: Child - Current
     */
    DL_CRITICAL,
    /*
     * ( UP + EXTEND )
     * Calculation: Child + Current
     */
    DE_CRITICAL,
    /*
     * Priority selection, High
     *
     * ->     G20 :: G10
     */
    /*
     * ( UP + LIMIT )
     * Calculation: Parent - Current
     */
    UL_CRITICAL,
    /*
     * ( UP + EXTEND )
     * Calculation: Parent + Current
     */
    UE_CRITICAL,

    // LAZY ( CRITICAL )
    /*
     * Priority selection, Low
     *
     * ->     G32
     */
    /*
     * ( UP + LIMIT )
     * Calculation: Child - Current
     */
    DL_OVERLOOK,
    /*
     * ( UP + EXTEND )
     * Calculation: Child + Current
     */
    DE_OVERLOOK,
    /*
     * Priority selection, Low
     *
     * ->     G32 :: G21, G10
     */
    /*
     * ( UP + LIMIT )
     * Calculation: Parent - Current
     */
    UL_OVERLOOK,
    /*
     * ( UP + EXTEND )
     * Calculation: Parent + Current
     */
    UE_OVERLOOK,
}
