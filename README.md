# Vertx Zero Up Framework

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/cn.vertxup/vertx-zero/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/cn.vertxup/vertx-zero/)  [![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)  [![Build Status](https://travis-ci.org/silentbalanceyh/vertx-zero.svg?branch=master)](https://travis-ci.org/silentbalanceyh/vertx-zero)  [![Gitter](https://badges.gitter.im/JoinChat.svg)](https://gitter.im/vertx-up/Lobby)  [![Maintainability](https://api.codeclimate.com/v1/badges/d2d08e521276a496a94e/maintainability)](https://codeclimate.com/github/silentbalanceyh/vertx-zero/maintainability)  
[![Waffle.io - Columns and their card count](https://badge.waffle.io/silentbalanceyh/vertx-zero.svg?columns=all)](https://waffle.io/silentbalanceyh/vertx-zero)

This project is based on Vert.x, the idea came from Spring-Boot framework. It could help developers focus on business requirements instead of more details of Vert.x. The project contains two parts "Up" and "Zero". "Up" means running up, "Zero" means no configuration provided, you could run your project with default configuration only.

Micro Service architecture is a future focused method to design and build mature system and help more companies to implement project faster, based on this idea, Vertx Zero Up Framework came out. It's micro-service oriented framework and will be verified by real projects. Also it provide a tool set to help developers to do correct things. The last point is that because of Event Driven Model, it's high performance framework.

This framework contains four sub-projects, all these projects are put in `vertx-gaia` project

* `vertx-gaia/vertx-co`: Core Library of Zero Up Framework
* `vertx-gaia/vertx-tp`: Third part integration components in Zero Up Framework
* `vertx-gaia/vertx-up`: Zero Up Engine with nested vert.x web container
* `vertx-gaia/vertx-rx`: Zero Up Engine with nested rxjava web container instead of web container
* `vertx-import`: Zero Usage dependency project to perform development, provide uniform dependency for your projects.
* `vertx-zeus/*`: Zero Examples, it contains all the tutorials of this framework

![](doc/image/zero-up.png)

## 1. Envrionment

If you want to use Zero framework, you can add following dependency into you `pom.xml` to use Zero:

```xml
    <parent>
        <artifactId>vertx-import</artifactId>
        <groupId>cn.vertxup</groupId>
        <version>0.4.6.3</version>
    </parent>
```

## 2. Boot Up

In your project, you can provide main entry only as following to run Zero \( Annotated with `@Up` \) .

```java
import io.vertx.up.VertxApplication;
import io.vertx.up.annotations.Up;

@Up
public class Driver {

    public static void main(final String[] args) {
        VertxApplication.run(Driver.class);
    }
}
```

Once the Zero is up, you can see following logs in your console \( The default port is 6083 \):

```
[ ZERO ] ZeroHttpAgent Http Server has been started successfully. \
    Endpoint: http://0.0.0.0:6083/
```

## 3. Documentation

For all tutorial testing cases, you can import following resource with Postman Tool

[https://www.getpostman.com/collections/d64b2efe4d47599efe6e](https://www.getpostman.com/collections/d64b2efe4d47599efe6e)

1. Restful Web Service
   1. [D10001 - Getting Start](doc/vertx-zero-tutorial/0-start-up.md)
   2. [D10002 - Origin Story, Hi Zero](doc/vertx-zero-tutorial/d10002-origin-story-hi-zero.md)
   3. [D10003 - JSR311, @Path...EndPoint](doc/vertx-zero-tutorial/d10003-jsr311-path-usage.md)
   4. [D10004 - JSR311, @GET,@POST...Http Method](doc/vertx-zero-tutorial/d10004-httpmethod-usage.md)
   5. [D10005 - JSR311, @QueryParam...Parameters](doc/vertx-zero-tutorial/d10005-queryparam-usage.md)
   6. [D10006 - JSR311, @PathParam...Parameters](doc/vertx-zero-tutorial/d10006-jsr311-pathparamparameters.md)
   7. [D10007 - JSR311, @FormParam...Parameters](doc/vertx-zero-tutorial/d10007-jsr311-formparamparameters.md)
   8. [D10008 - JSR311, @HeaderParam...Parameters](doc/vertx-zero-tutorial/d10008-jsr311-headerparamparameters.md)
   9. [D10009 - JSR311, @CookieParam...Parameters](doc/vertx-zero-tutorial/d10009-jsr311-cookieparamparameters.md)
   10. [D10010 - Zero JSR311, @BodyParam...Parameters](doc/vertx-zero-tutorial/d10010-zero-jsr311-bodyparamparameters.md)
   11. [D10011 - Zero JSR311, @BodyParam...Pojo](doc/vertx-zero-tutorial/d10011-zero-jsr311-bodyparampojo.md)
   12. [D10012 - Zero JSR311, @StreamParam...Parameters](doc/vertx-zero-tutorial/d10012-zero-jsr311-streamparamparameters.md)
   13. [D10013 - Zero JSR311, @StreamParam...File/FileUpload](doc/vertx-zero-tutorial/d10013-zero-jsr311-streamparamfilefileupload.md)
   14. [D10014 - Zero JSR311, @SessionParam...Parameters](doc/vertx-zero-tutorial/d10014-zero-jsr311-sessionparamparameters.md)
   15. [D10015 - By Typed Parameters](doc/vertx-zero-tutorial/d10015-by-typed-parameters.md)
   16. [D10016 - JSR311, @Consumes...Media Type](doc/vertx-zero-tutorial/d10016-jsr311-consumesmedia-type.md)
   17. [D10017 - JSR311, @Produces...Media Type](doc/vertx-zero-tutorial/d10017-jsr311-producesmedia-type.md)
2. Async Non-Blocked
   1. [D10018 - Non-Blocking, Enable EventBus](doc/vertx-zero-tutorial/d10018-async-enable-eventbus.md)
   2. [D10019 - Non-Blocking, Mode 1 Sync Mode](doc/vertx-zero-tutorial/d10019-non-blocking-mode-1-sync-mode.md)
   3. [D10020 - Non-Blocking, Mode 2 Ping Mode](doc/vertx-zero-tutorial/d10020-non-blocking-mode-2-block-mode.md)
   4. [D10021 - Non-Blocking, Mode 3 One Way Mode](doc/vertx-zero-tutorial/d10021-non-blocking-mode-3-one-way-mode.md)
   5. [D10022 - Non-Blocking, Mode 4 Async Mode \( Java Style \)](doc/vertx-zero-tutorial/d10022-recommend-non-blocking-mode-4-async-mode.md)
   6. [D10023 - Non-Blocking, Mode 4 Experimental extension](doc/vertx-zero-tutorial/d10023-non-blocking-mode-4-experimental-extension.md)
   7. [D10024 - Non-Blocking, Mode 5 Async Mode \( vert.x style \)](doc/vertx-zero-tutorial/d10024-non-blocking-mode-5-async-mode-vertx-style.md)
   8. [D10025 - \( Recommend \) Non-Blocking, Mode 5 Experimental extension](doc/vertx-zero-tutorial/d10025-non-blocking-mode-5-experimental-extension.md)
3. JSR330 Dependenncy Injection
   1. [D10026 - JSR330, @Inject Simple Java Object](doc/vertx-zero-tutorial/d10026-jsr330-inject-simple-java-object.md)
   2. [D10027 - JSR330, @Inject One Implementation](doc/vertx-zero-tutorial/d10027-jsr330-inject-one-implementation.md)
   3. [D10028 - Zero JSR330, @Inject Multi Implementation](doc/vertx-zero-tutorial/d10028-zero-jsr330-inject-multi-implementation.md)
4. JSR303 Bean Validation
   1. [D10029 - JSR303, @NotNull](doc/vertx-zero-tutorial/d10029-jsr303-notnull.md)
   2. [D10030 - JSR303, @NotNull message](doc/vertx-zero-tutorial/d10030-jsr303-notnull-message.md)
   3. [D10031 - JSR303, @NotNull message template](doc/vertx-zero-tutorial/d10031-jsr303-notnull-message-template.md)
   4. [D10032 - JSR303, @DefaultValue for parameters](doc/vertx-zero-tutorial/d10032-jsr303-defaultvalue-for-parameters.md)
   5. [D10033 - JSR303, @Null for value](doc/vertx-zero-tutorial/d10033-jsr303-null-for-value.md)
   6. [D10034 - JSR303, @AssertTrue/@AssertFalse for boolean](doc/vertx-zero-tutorial/d10034-asserttrueassertfalse-for-boolean.md)
   7. [D10035 - JSR303, @Min/@Max for numeric](doc/vertx-zero-tutorial/d10035-jsr303-minmax-for-numeric.md)
   8. [D10036 - JSR303, @DecimalMin/@DecimalMax for decimal](doc/vertx-zero-tutorial/d10036-jsr303-decimalmindecimalmax-for-decimal.md)
   9. [D10037 - JSR303, @Size usage](doc/vertx-zero-tutorial/d10037-jsr303-size-usage.md)
   10. [D10038 - JSR303, @Digits for decimal](doc/vertx-zero-tutorial/d10038-jsr303-digits-for-decimal.md)
   11. [D10039 - JSR303, @Future, @Past for date](doc/vertx-zero-tutorial/d10039-jsr303-future-past-for-date.md)
   12. [D10040 - JSR303, @Pattern for regular expression](doc/vertx-zero-tutorial/d10040-jsr303-pattern-for-regular-expression.md)
   13. [D10041 - JSR303, 2.x version](doc/vertx-zero-tutorial/d10041-jsr303-in-future-usage.md)
   14. [D10042 - JSR303, Pojo First](doc/vertx-zero-tutorial/d10043-jsr303-pojo-first.md)
   15. [D10043 - Zero JSR303, Body Validation](doc/vertx-zero-tutorial/d10043-zero-jsr303-body-validation.md)
5. JSR340 Filter/Listener
   1. [D10098 - JSR340 Filter in Agent](doc/vertx-zero-tutorial/d10098-jsr340-filter-in-agent.md)
   2. [D10099 - JSR340 Filter in Worker](/doc/vertx-zero-tutorial/d10099-jsr340-filter-in-worker.md)
   3. [D10100 - JSR340 Multi Filters](/doc/vertx-zero-tutorial/d10100-jsr340-multi-filters.md)
6. Programming Style & User-defined
   1. [D10044 - Programming Styles](doc/vertx-zero-tutorial/d10044-programming-styles.md)
   2. [D10045 - Interface Style](doc/vertx-zero-tutorial/d10044-recommend-interface-mode-only.md)
   3. [D10094 - Exception, Defined your Errors](doc/vertx-zero-tutorial/d10094-exception-defined-your-errors.md)
   4. [D10096 - Exception, Readable message to help UI](doc/vertx-zero-tutorial/d10096-exception-readable-message-to-help-ui.md)
7. Utility X Tool Box
   1. [D10046 - Utility X Turn-On](doc/vertx-zero-tutorial/d10046-utilityx-turn-on.md)
   2. [D10047 - Utility X, JsonObject Processing](doc/vertx-zero-tutorial/d10047-utility-x-json-processinng.md)
   3. [D10048 - Utility X, Pager Processing](doc/vertx-zero-tutorial/d10048-utility-x-pager-processing.md)
   4. [D10049 - Utility X, Sorter Processing](doc/vertx-zero-tutorial/d10049-utility-x-sorter-processing.md)
   5. [D10050 - Utility X, Errors](doc/vertx-zero-tutorial/d10050-utility-x-errors.md)
   6. [D10051 - Utility X, JsonArray/Unique Extract](doc/vertx-zero-tutorial/d10051-utility-x-jsonarrayunique-extract.md)
   7. [D10052 - Utility X, User Data](doc/vertx-zero-tutorial/d10052-utility-x-user-data.md)
   8. [D10053 - Utility X, Normalized Response](doc/vertx-zero-tutorial/d10053-utility-x-normalized-response.md)
   9. [D10054 - Utility X Input, Agent Mode](doc/vertx-zero-tutorial/d10054-utility-x-input-agent-mode.md)
   10. [D10055 - Utility X Input, Interface Style](doc/vertx-zero-tutorial/d10055-utility-x-interface-style.md)
   11. [D10056 - Utility X Basic Future](doc/vertx-zero-tutorial/d10056-utility-x-rest.md)
   12. [D10057 - Utility X, thenParallel](doc/vertx-zero-tutorial/d10057-utility-x-advanced-futures.md)
   13. [D10058 - Utility X, thenParallelArray/Json](doc/vertx-zero-tutorial/d10058-utility-x-thenparallelarrayjson.md)
   14. [D10059 - Utility X, thenScatterJson](doc/vertx-zero-tutorial/d10059-utility-x-thenscatterjson.md)
   15. [D10060 - Utility X, thenError](doc/vertx-zero-tutorial/d10060-utility-x-thenerror.md)
8. Jooq for MySql
   1. [D10061 - Jooq/mysql, Configuration](doc/vertx-zero-tutorial/d10061-jooqmysql-configuration.md)
   2. [D10062 - Jooq/CRUD, Read Operation](doc/vertx-zero-tutorial/d10062-jooq-get.md)
   3. [D10063 - Jooq/CRUD, Write Operation](doc/vertx-zero-tutorial/d10063-jooqcreate-operations.md)
   4. [D10064 - Jooq/CRUD, Fetch One Operation](doc/vertx-zero-tutorial/d10064-jooqcrud-fetch-one-operation.md)
   5. [D10065 - Jooq/CRUD, Fetch List Operation](doc/vertx-zero-tutorial/d10065-jooqcrud-fetch-list-operation.md)
   6. [D10066 - Jooq/CRUD, Filter Syntax](doc/vertx-zero-tutorial/d10066-jooqcrud-filter-syntax.md)
   7. [D10067 - Jooq/CRUD, Paging/Sorting](doc/vertx-zero-tutorial/d10067-jooqcrud-advanced-search-operation.md)
   8. [D10097 - Jooq/CRUD, Save/Existing Operation](doc/vertx-zero-tutorial/d10097-jooqcrud-save-operation.md)
9. Yaml Connfiguration
   1. [D10068 - Configuration, vertx.yml](doc/vertx-zero-tutorial/d10068-fragment-yaml-configuration.md)
   2. [D10069 - Configuration, vertx-server.yml](doc/vertx-zero-tutorial/d10069-configuration-vertx-serveryml.md)
   3. [D10070 - Configuration, vertx-inject.yml](doc/vertx-zero-tutorial/d10070-configuration-vertx-injectyml.md)
   4. [D10071 - Configuration, vertx-error.yml](doc/vertx-zero-tutorial/d10071-configuration-vertx-erroryml.md)
   5. [D10072 - Configuration, vertx-mysql.yml](doc/vertx-zero-tutorial/d10072-configuration-vertx-mysqlyml.md)
   6. [D10073 - Configuration, vertx-mongo.yml](doc/vertx-zero-tutorial/d10073-configuration-vertx-mongoyml.md)
   7. [D10074 - Configuration, vertx-etcd3.yml](doc/vertx-zero-tutorial/d10074-configuration-vertx-etcd3yml.md)
   8. [D10075 - Configuration, vertx-rpc.yml](doc/vertx-zero-tutorial/d10075-configuration-vertx-rpcyml.md)
   9. [D10102 - Configuration, vertx-redis.yml](/doc/vertx-zero-tutorial/d10102-configuration-vertx-redisyml.md)
   10. [D10103 - Configuration, vertx-secure.yml](/doc/vertx-zero-tutorial/d10103-configuration-vertx-secureyml.md)
10. Native Support
    1. [D10076 - Vert.x Native, MySqlClient](doc/vertx-zero-tutorial/d10076-vertx-native-mysqlclient.md)
    2. [D10078 - Vert.x Native, MongoClient](doc/vertx-zero-tutorial/d10077-vertx-native-mongoclient.md)
    3. [D10101 - Vert.x Native, RedisClient](/doc/vertx-zero-tutorial/d10101-vertx-native-redisclient.md)
11. Micro Service Development
    1. [D10082 - Micro, Environment Preparing](doc/vertx-zero-tutorial/d10082-micro-environment-preparing.md)
    2. [D10083 - Micro, Rpc Mode](doc/vertx-zero-tutorial/d10083-micro-rpc-mode.md)
    3. [D10084 - Micro, Configuration](doc/vertx-zero-tutorial/d10084-micro-yaml-configuration.md)
    4. [D10085 - Micro, Simple Rpc](doc/vertx-zero-tutorial/d10085-micro-simple-rpc.md)
    5. [D10086 - Micro, Future with Utility X](doc/vertx-zero-tutorial/d10085-micro-future-with-utility-x.md)
    6. [D10087 - Micro, Multi Rpc Calls](doc/vertx-zero-tutorial/d10087-micro-multi-rpc-calls.md)
    7. [D10088 - Micro Stream,  Sender \( Agent \) to Terminator](doc/vertx-zero-tutorial/d10088-micro-stream-sender-agent-to-terminator.md)
    8. [D10089 - Micro Stream, Consumer \( Worker \) to Terminator](doc/vertx-zero-tutorial/d10089-micro-consumer-worker-to-terminator.md)
    9. [D10090 - Micro Stream, Consumer Sync Mode to Terminator](doc/vertx-zero-tutorial/d10090-micro-stream-consumer-sync.md)
    10. [D10091 - Micro Stream, Consumer Async Mode to Terminator](doc/vertx-zero-tutorial/d10091-micro-stream-consumer-async-to-terminator.md)
    11. [D10092 - Micro Stream, Consumer T Mode to Terminator](doc/vertx-zero-tutorial/d10092-micro-stream-consumer-t-mode-to-terminator.md)
    12. [D10093 - Micro Stream, Originator/Coordinator/Terminator](doc/vertx-zero-tutorial/d10093-micro-stream-originatorcoordinatorterminator.md)
12. Security Module
    1. [D10095 - Security, Basic Authorization](doc/vertx-zero-tutorial/d10095-security-basic-authorization.md)
    2. [D10104 - Security, Jwt Authorization](/doc/vertx-zero-tutorial/d10104-security-jwt-authorization.md)
13. Third Part Reference
    1. [D10077 - Reference, Mongo Setup](doc/vertx-zero-tutorial/d10077-third-part-mongo-setup.md)
    2. [D10079 - Reference, Mini k8s](doc/vertx-zero-tutorial/d10079-reference-mini-k8s.md)
    3. [D10080 - Reference, Istio](doc/vertx-zero-tutorial/d10080-reference-istio.md)
    4. [D10081 - Reference, Istio Addon](doc/vertx-zero-tutorial/d10081-reference-istio-addon.md)

## 4. Logging in Zero

You also could use following function in your coding to get Logger component.

```java
// Zero Logger initialized, connect to vert.x logging system directly but uniform managed by zero.
import io.vertx.up.log.Annal;

// Then in your class
public final class Statute {

    private static final Annal LOGGER = Annal.get(Statute.class);
    ......
}
```



