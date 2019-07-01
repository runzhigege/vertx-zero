/*
 * Function package for some code execution defined here.
 * It means that you can use `Fn.xx` to be sure some specific code workflow instead of
 * duplicated checking such as
 * 1) Null Pointer checking
 * 2) Jvm unexpected exception
 * 3) Vertx unexpected exception
 * 4) Zero runtime exception / Zero exception
 * This package also defined checked exception code flow such as follow
 * 1) io.zero.epic.fn.JvmActuator
 * 2) io.zero.epic.fn.JvmSupplier
 * 3) io.zero.epic.fn.ZeroActuator
 * 4) io.zero.epic.fn.ZeroBiConsumer
 * 5) io.zero.epic.fn.ZeroSupplier
 */
package io.zero.epic.fn;