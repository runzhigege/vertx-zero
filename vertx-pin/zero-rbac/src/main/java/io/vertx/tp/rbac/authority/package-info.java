/*
 * Package for profile storage
 * After user logged into the system, RBAC module should initialize Profile Pool
 * 1) Based on User - Role ( Calculated User Profile )
 * 2) Based on Group - Role ( Calculated Group Profile )
 * 3) The data structure should be:
 * {
 *     user:"xxx",
 *     role:[{
 *          priority: xxx,
 *          roleId: "xxx"
 *     }],
 *     group:[{
 *          priority: xxx,
 *          groupId: "xxx",
 *          role:[{
 *              priority: xxx,
 *              roleId: "xxx"
 *          }]
 *     }],
 *     "profile":{
 *          "PROFILE1":["PERMISSION1","PERMISSION2"],
 *          "PROFILE2":["PERMISSION2","PERMISSION4"]
 *     }
 * }
 */
package io.vertx.tp.rbac.authority;