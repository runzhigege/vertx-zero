/*
 * Standard Crud Api in zero system, if you want to use it, you must import as following:
 *
        <dependency>
            <groupId>cn.vertxup</groupId>
            <artifactId>zero-crud</artifactId>
            <version>${zero.version}</version>
        </dependency>
 *
 * Here are some limitation to use this module
 * 1) Your booter ( main ) must be under `cn.vertxup` package, it could trigger the class scan
 * 2) You couldn't design Restful API that has been used in current module such as /api/{module}
 * Here are API position that has been taken:
 *
 *
 */
package cn.vertxup;