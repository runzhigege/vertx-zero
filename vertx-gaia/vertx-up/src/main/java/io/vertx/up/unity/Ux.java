package io.vertx.up.unity;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.jwt.JWT;
import io.vertx.ext.jwt.JWTOptions;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.up.atom.query.Criteria;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.commune.Envelop;
import io.vertx.up.commune.Record;
import io.vertx.up.eon.Strings;
import io.vertx.up.exception.WebException;
import io.vertx.up.fn.Fn;
import io.vertx.up.fn.wait.Log;
import io.vertx.up.util.Ut;
import org.jooq.Condition;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;
import java.util.function.*;

/**
 * Here Ux is a util interface of uniform to call different tools.
 * It just like helper for business usage.
 */
@SuppressWarnings("all")
public final class Ux {

    // ---------------------- Fast Logging ----------------------------
    public static Log log(final Class<?> clazz) {
        return Log.create(null == clazz ? Ux.class : clazz);
    }

    // ---------------------- Debug/Timer --------------------------
    public static void debug(final Object... objects) {
        Debug.monitor(objects);
    }

    public static <T> Future<T> debug(final T item) {
        Fn.safeNull(() -> Debug.monitor(item), item);
        return Future.succeededFuture(item);
    }

    public static <T> T debug(final Throwable error, final Supplier<T> supplier) {
        if (Objects.nonNull(error)) {
            // TODO: Debug for JVM;
            error.printStackTrace();
        }
        return supplier.get();
    }

    // ---------------------- JsonObject Returned --------------------------
    // T -> JsonObject
    public static <T> JsonObject toJson(final T entity) {
        return To.toJson(entity, "");
    }

    // T -> JsonObject ( with Pojo )
    public static <T> JsonObject toJson(final T entity, final String pojo) {
        return To.toJson(entity, pojo);
    }

    public static JsonObject toFilters(final String[] columns, final Supplier<Object>... supplier) {
        return To.toFilters(columns, supplier);
    }

    // Toggle switch from interface style to worker here, the key should be "0", "1", "2", "3", ....
    public static JsonObject toToggle(final Object... args) {
        return To.toToggle(args);
    }

    public static <T> T fromJson(final JsonObject data, final Class<T> clazz) {
        return From.fromJson(data, clazz, "");
    }

    public static <T> T fromJson(final JsonObject data, final Class<T> clazz, final String pojo) {
        return From.fromJson(data, clazz, pojo);
    }

    public static JsonObject fromJson(final JsonObject data, final String pojo) {
        return From.fromJson(data, pojo);
    }

    // JsonArray -> JsonObject ( with field grouped )
    public static JsonObject toGroup(final JsonArray array, final String field) {
        return Calculator.groupBy(array, field);
    }

    // JsonArray -> Future<JsonObject>
    public static Future<JsonObject> thenGroup(final JsonArray array, final String field) {
        return Future.succeededFuture(Calculator.groupBy(array, field));
    }

    // Special Merge
    public static void appendJson(final JsonObject target, final JsonObject source) {
        Calculator.appendJson(target, source);
    }

    // ---------------------- JsonArray Returned --------------------------
    // -> List<T> -> JsonArray
    public static <T> JsonArray toArray(final List<T> list) {
        return To.toArray(list, "");
    }

    public static JsonArray toArray(final Record[] records) {
        return To.toArray(records);
    }

    // -> List<T> -> JsonArray ( with Pojo )
    public static <T> JsonArray toArray(final List<T> list, final String pojo) {
        return To.toArray(list, pojo);
    }

    // ---------------------- Envelop Returned --------------------------
    public static <T> Future<T> toFuture(final T entity) {
        return To.toFuture(entity);
    }

    // ---------------------- Function ------------------------
    public static <I, T> Function<I, Future<T>> applyNil(final Supplier<T> supplier, final Function<I, Future<T>> executor) {
        return Apply.applyNil(supplier, executor);
    }

    public static <I, T> Function<I, Future<T>> applyNil(final Supplier<T> supplier, final Supplier<Future<T>> executor) {
        return Apply.applyNil(supplier, executor);
    }

    public static <T> Function<T, Future<T>> applyNil(final Function<T, Future<T>> executor) {
        return Apply.applyNil(executor);
    }

    public static Function<JsonObject, Future<JsonObject>> applyJNil(final Function<JsonObject, Future<JsonObject>> executor) {
        return Apply.applyNil(executor);
    }

    public static <T> Function<T, Future<JsonObject>> applyField(final JsonObject input, final String field) {
        return Apply.applyField(input, field);
    }

    public static <T> Function<T, Future<JsonObject>> applyMerge(final JsonObject input) {
        return Apply.applyField(input, null);
    }

    // ---------------------- Envelop Returned --------------------------
    // -> List<T> -> Envelop
    public static <T> Envelop to(final List<T> list) {
        return Envelop.success(Ux.toArray(list));
    }

    // -> Class<?> -> Envelop ( With failure )
    public static Envelop to(final Class<? extends WebException> clazz, final Object... args) {
        return To.toEnvelop(clazz, args);
    }

    // -> T -> Envelop
    public static <T> Envelop to(final T entity) {
        return To.toEnvelop(entity);
    }

    // -> T -> Envelop ( If entity is null, return Envelop.failure(error) )
    public static <T> Envelop to(final T entity, final WebException error) {
        return To.toEnvelop(entity, error);
    }
    // ---------------------- User Data -------------------------------------

    // -> JsonArray ( JsonObject ) + JsonArray ( String ) -> add 'serial'
    public static JsonArray serial(final JsonArray items, final JsonArray serials) {
        return In.assignValue(items, serials, "serial", true);
    }

    public static JsonArray field(final JsonArray items, final JsonArray targets, final String field) {
        return In.assignValue(items, targets, field, true);
    }

    // ---------------------- Web Flow --------------------------------------
    public static <T> Handler<AsyncResult<T>> handler(final Message<Envelop> message) {
        return Web.toHandler(message);
    }

    public static <T> Function<JsonObject, Future<JsonObject>> toAttach(final String field, final Function<T, Future<JsonObject>> function) {
        return Web.toAttach(field, function);
    }

    public static <T> Function<JsonObject, Future<JsonObject>> toAttachJson(final String field, final Function<T, Future<JsonObject>> function) {
        return Web.toAttachJson(field, function);
    }

    // ---------------------- Function ------------------------
    public static Future<JsonObject> fnRpc(final JsonArray array) {
        return UxRpc.fnRpc(array);
    }

    public static Future<JsonArray> fnRpc(final JsonObject data) {
        return UxRpc.fnRpc(data);
    }

    public static <T> Future<JsonObject> fnJObject(final T item) {
        return Future.succeededFuture(To.toJson(item, ""));
    }

    public static <T> Future<JsonArray> fnJArray(final List<T> item) {
        return Future.succeededFuture(To.toArray(item, ""));
    }

    public static <T> Future<ConcurrentMap<String, JsonArray>> fnJMap(final List<T> item, final String field) {
        return fnJMap(To.toArray(item, ""), field);
    }

    public static Future<ConcurrentMap<String, JsonArray>> fnJMap(final JsonArray item, final String field) {
        return Future.succeededFuture(Ut.elementGroup(item, field));
    }

    /*
     * Map by type here
     */
    public static <T> Future<ConcurrentMap<String, JsonArray>> fnJMapType(final List<T> item) {
        return fnJMap(To.toArray(item, ""), "type");
    }

    public static Future<ConcurrentMap<String, JsonArray>> fnJMapType(final JsonArray item) {
        return fnJMap(item, "type");
    }


    public static <T> Function<T, Future<JsonObject>> fnJObject(final String pojo) {
        return item -> Future.succeededFuture(To.toJson(item, pojo));
    }

    public static <T> Function<List<T>, Future<JsonArray>> fnJArray(final String pojo) {
        return list -> Future.succeededFuture(To.toArray(list, pojo));
    }

    // ---------------------- Future --------------------------
    public static <T> Future<JsonObject> thenRpc(final String name, final String address, final JsonObject params) {
        return UxRpc.thenRpc(name, address, params);
    }

    public static <T> Future<JsonObject> thenRpc(final String name, final String address, final String field, final Object value) {
        return UxRpc.thenRpc(name, address, new JsonObject().put(field, value));
    }

    // ---------------------- New future ----------------------
    public static Future<JsonObject> thenBool(final Boolean result) {
        return Future.succeededFuture(new JsonObject().put(Strings.J_RESULT, result));
    }

    // -> Consumer<Future<T>> -> Future<T>
    public static <T> Future<T> thenGeneric(final Consumer<Future<T>> consumer) {
        return Fn.thenGeneric(consumer);
    }

    public static <T> Future<T> thenGeneric(final Object result, final Future<T> future, final Throwable ex) {
        return Fn.thenGeneric(result, future, ex);
    }

    // ---------------------- Future --------------------------

    /**
     * @param source      The first query result of list
     * @param generateFun (json) -> future(out) ( each record )
     * @param operatorFun (json, out) -> merged
     * @return It often used in secondary select in database here
     * The workflow
     * --------> generateFun ( Supplier )     operatorFun ( BiConsumer )
     * --------> json1 -> ? future<out1>  ->  operatorFun[0] -> (json1, out1) -> merged1
     * jarray -> json2 -> ? future<out2>  ->  operatorFun[1] -> (json2, out2) -> merged2  -> merged
     * --------> json3 -> ? future<out3>  ->  operatorFun[2] -> (json3, out3) -> merged3
     */
    public static Future<JsonArray> thenCombine(final Future<JsonArray> source, final Function<JsonObject, Future<JsonObject>> generateFun, final BinaryOperator<JsonObject> operatorFun) {
        return Fluctuate.thenCombine(source, generateFun, operatorFun);
    }

    /**
     * @param source      The input json object
     * @param generateFun The json object should generate list<future>, each future should be json object
     * @param operatorFun merged the result to json object instead of other
     * @return The final result of future
     * The workflow
     * ------>  generateFun ( Supplier )                operatorFun ( BiConsumer )
     * ------>  future1 ( json -> ? future<out1> )  ->  operatorFun[0] -> (json, out1) -> merged1  ->
     * json ->  future2 ( json -> ? future<out2> )  ->  operatorFun[1] -> (json, out2) -> merged2  -> merged
     * ------>  future3 ( json -> ? future<out3> )  ->  operatorFun[2] -> (json, out3) -> merged3  ->
     */
    public static Future<JsonObject> thenCombine(final JsonObject source, final Function<JsonObject, List<Future>> generateFun, final BiConsumer<JsonObject, JsonObject>... operatorFun) {
        return Fluctuate.thenCombine(Future.succeededFuture(source), generateFun, operatorFun);
    }

    /**
     * @param futures The list of futures
     * @return The final result of futures
     * input:
     * - List: [future1, future2, future3]
     * output:
     * - Future: JsonArray ( future1 -> json, future2 -> json, future3 -> json )
     * The workflow
     * future1 -> (in1 -> out1)
     * future2 -> (in2 -> out2) --> future ( [out1, out2, out3] )
     * future3 -> (in3 -> out3)
     */
    public static Future<JsonArray> thenCombine(final List<Future<JsonObject>> futures) {
        return Fluctuate.thenCombine(futures);
    }

    public static <T> Future<ConcurrentMap<String, T>> thenCombine(final ConcurrentMap<String, Future<T>> futureMap) {
        return Fluctuate.thenCombine(futureMap);
    }

    public static Future<JsonArray> thenCombineArray(final List<Future<JsonArray>> futures) {
        return Fluctuate.thenCombineArray(futures);
    }

    public static Future<ConcurrentMap<String, JsonArray>> thenCompress(final List<Future<ConcurrentMap<String, JsonArray>>> futures) {
        return Fluctuate.thenCompress(futures, (original, latest) -> original.addAll(latest));
    }

    /**
     * Common usage: To error directly
     *
     * @param clazz The type of WebException, class type, it will be created by reflection.
     * @param args  The rule
     *              - arg0: this.getClass(), Because all the first arg of WebException must be clazz here.
     *              - argX: the arguments of WebException constructor here, instead of fixed arguments.
     */
    public static <T> Future<T> thenError(final Class<? extends WebException> clazz, final Object... args) {
        return Fluctuate.thenError(clazz, args);
    }

    // ---------------------- Request Data Extract --------------------------
    // -> Message<Envelop> -> T ( Interface mode )

    public static JsonArray getArray(final Envelop envelop, final int index) {
        return In.request(envelop, index, JsonArray.class);
    }

    // -> Message<Envelop> -> T ( Interface mode )
    public static JsonArray getArray(final Envelop envelop) {
        return In.request(envelop, 0, JsonArray.class);
    }

    // -> Message<Envelop> -> T ( Interface mode )

    public static JsonArray getArray1(final Envelop envelop) {
        return In.request(envelop, 1, JsonArray.class);
    }

    // -> Message<Envelop> -> T ( Interface mode )

    public static JsonArray getArray2(final Envelop envelop) {
        return In.request(envelop, 2, JsonArray.class);
    }

    // -> Message<Envelop> -> T ( Interface mode )

    public static JsonArray getArray3(final Envelop envelop) {
        return In.request(envelop, 3, JsonArray.class);
    }

    // -> Message<Envelop> -> String ( Interface mode )

    public static String getString(final Envelop envelop, final int index) {
        return In.request(envelop, index, String.class);
    }

    // -> Message<Envelop> -> String ( Interface mode )
    public static String getString(final Envelop envelop) {
        return In.request(envelop, 0, String.class);
    }

    // -> Message<Envelop> -> String ( Interface mode )
    public static String getString1(final Envelop envelop) {
        return In.request(envelop, 1, String.class);
    }

    // -> Message<Envelop> -> String ( Interface mode )
    public static String getString2(final Envelop envelop) {
        return In.request(envelop, 2, String.class);
    }

    // -> Message<Envelop> -> JsonObject ( Interface mode )
    public static JsonObject getJson(final Envelop envelop, final int index) {
        return In.request(envelop, index, JsonObject.class);
    }
    // -> Message<Envelop> -> JsonObject ( Interface mode )

    public static <T> T fromEnvelop(final Envelop envelop, final Class<T> clazz, final String pojo) {
        return From.fromJson(getJson(envelop), clazz, pojo);
    }

    public static JsonObject fromEnvelop(final Envelop envelop, final String pojo) {
        return From.fromJson(Ux.getJson(envelop), pojo);
    }

    public static JsonObject getJson(final Envelop envelop) {
        return In.request(envelop, 0, JsonObject.class);
    }

    public static Inquiry getInquiry(final JsonObject envelop) {
        return Query.getInquiry(envelop, "");
    }

    public static Inquiry getInquiry(final JsonObject envelop, final String pojo) {
        return Query.getInquiry(envelop, pojo);
    }

    // -> Message<Envelop> -> JsonObject ( Interface mode )

    public static <T> T fromEnvelop1(final Envelop envelop, final Class<T> clazz, final String pojo) {
        return From.fromJson(getJson1(envelop), clazz, pojo);
    }

    public static JsonObject fromEnvelop1(final Envelop envelop, final String pojo) {
        return From.fromJson(Ux.getJson1(envelop), pojo);
    }

    public static JsonObject getJson1(final Envelop envelop) {
        return In.request(envelop, 1, JsonObject.class);
    }

    // -> Message<Envelop> -> JsonObject ( Interface mode )

    public static JsonObject getJson2(final Envelop envelop) {
        return In.request(envelop, 2, JsonObject.class);
    }

    public static JsonObject fromEnvelop2(final Envelop envelop, final String pojo) {
        return From.fromJson(Ux.getJson2(envelop), pojo);
    }

    public static <T> T fromEnvelop2(final Envelop envelop, final Class<T> clazz, final String pojo) {
        return From.fromJson(getJson2(envelop), clazz, pojo);
    }

    // -> Message<Envelop> -> Integer ( Interface mode )

    public static Integer getInteger(final Envelop envelop, final int index) {
        return In.request(envelop, index, Integer.class);
    }

    public static Integer getInteger(final Envelop envelop) {
        return In.request(envelop, 0, Integer.class);
    }

    // -> Message<Envelop> -> Integer ( Interface mode )
    public static Integer getInteger1(final Envelop envelop) {
        return In.request(envelop, 1, Integer.class);
    }

    // -> Message<Envelop> -> Integer ( Interface mode )
    public static Integer getInteger2(final Envelop envelop) {
        return In.request(envelop, 2, Integer.class);
    }

    // -> Message<Envelop> -> Long ( Interface mode )

    public static Long getLong(final Envelop envelop, final int index) {
        return In.request(envelop, index, Long.class);
    }

    // -> Message<Envelop> -> Long ( Interface mode )
    public static Long getLong(final Envelop envelop) {
        return In.request(envelop, 0, Long.class);
    }

    // -> Message<Envelop> -> Long ( Interface mode )
    public static Long getLong1(final Envelop envelop) {
        return In.request(envelop, 1, Long.class);
    }

    // -> Message<Envelop> -> Long ( Interface mode )
    public static Long getLong2(final Envelop envelop) {
        return In.request(envelop, 2, Long.class);
    }

    // -> Message<Envelop> -> T ( Interface mode )

    public static <T> T getT(final Envelop envelop, final int index, final Class<T> clazz) {
        return In.request(envelop, index, clazz);
    }

    // -> Message<Envelop> -> T ( Interface mode )

    public static <T> T getT(final Envelop envelop, final Class<T> clazz) {
        return In.request(envelop, 0, clazz);
    }

    // -> Message<Envelop> -> T ( Interface mode )

    public static <T> T getT1(final Envelop envelop, final Class<T> clazz) {
        return In.request(envelop, 1, clazz);
    }

    // -> Message<Envelop> -> T ( Interface mode )

    public static <T> T getT2(final Envelop envelop, final Class<T> clazz) {
        return In.request(envelop, 2, clazz);
    }

    // ---------------------- Agent mode usage --------------------------
    // -> Message<Envelop> -> JsonObject ( Agent mode )
    // -> Envelop -> JsonObject ( Agent mode )
    public static JsonObject getBody(final Envelop envelop) {
        return In.request(envelop, JsonObject.class);
    }

    // -> Envelop -> T ( Agent mode )
    public static <T> T getBodyT(final Envelop envelop, final Class<T> clazz) {
        return In.request(envelop, clazz);
    }

    // -- Atom
    public static Function<JsonObject, Future<JsonObject>> atomJoin(final String field, final JsonObject to) {
        return Atomic.joinTo(to, field);
    }

    public static Function<JsonObject, Future<JsonObject>> atomJoin(final JsonObject from, final String field) {
        return Atomic.joinFrom(from, field);
    }

    // -> Jooq Condition
    public static Condition condition(final JsonObject filters) {
        return UxJooq.transform(filters);
    }

    public static Condition condition(final Criteria criteria) {
        return UxJooq.transform(criteria.toJson());
    }

    public static void initComponent(final JsonObject init) {
        Atomic.initComponent(init);
    }

    // -> Jooq
    public static class Jooq {

        public static UxJooq on(final Class<?> clazz) {
            return Fn.pool(io.vertx.up.unity.Pool.JOOQ, clazz, () -> new UxJooq(clazz));
        }

        public static UxJooq on(final Class<?> clazz, final VertxDAO vertxDAO) {
            return Fn.pool(io.vertx.up.unity.Pool.JOOQ, clazz, () -> new UxJooq(clazz, vertxDAO));
        }
    }

    // -> Jooq -> Multi
    public static class Join {

        public static UxJoin on(final String configFile) {
            return new UxJoin(configFile);
        }
    }

    public static class Pool {

        public static UxPool on(final String name) {
            return Fn.pool(io.vertx.up.unity.Pool.POOL, name, () -> new UxPool(name));
        }

        public static UxPool on() {
            return new UxPool();
        }
    }

    public static class Job {
        public static UxJob on() {
            return new UxJob();
        }
    }

    // -> Jwt
    public static class Jwt {

        public static String token(final JsonObject claims) {
            return UxJwt.generate(claims, new JWTOptions());
        }

        public static String token(final JsonObject claims, final Function<String, Buffer> funcBuffer) {
            return UxJwt.generate(claims, new JWTOptions(), funcBuffer);
        }

        public static JsonObject extract(final JsonObject vertxToken) {
            return UxJwt.extract(vertxToken.getString("jwt"));
        }

        public static JsonObject extract(final String token) {
            return UxJwt.extract(token);
        }

        public static JsonObject extract(final String token, final JsonObject config) {
            return UxJwt.extract(token, config);
        }

        public static JWT create(final JWTAuthOptions config) {
            return UxJwt.create(new JWTAuthOptions(config), Ut::ioBuffer);
        }

        public static JWT create(final JsonObject config) {
            return UxJwt.create(new JWTAuthOptions(config), Ut::ioBuffer);
        }

        public static JWT create(final JWTAuthOptions config, final Function<String, Buffer> funcBuffer) {
            return UxJwt.create(config, funcBuffer);
        }

        public static JWT create(final JsonObject config, final Function<String, Buffer> funcBuffer) {
            return UxJwt.create(new JWTAuthOptions(config), funcBuffer);
        }
    }

    // -> Mongo
    public static class Mongo {

        public static JsonObject termIn(final JsonObject filter, final String field, final JsonArray values) {
            return UxMongo.termIn(filter, field, values);
        }

        public static JsonObject termLike(final JsonObject filter, final String field, final String value) {
            return UxMongo.termLike(filter, field, value);
        }

        public static Future<Boolean> missing(final String collection, final JsonObject filter) {
            return UxMongo.missing(collection, filter);
        }

        public static Future<Boolean> existing(final String collection, final JsonObject filter) {
            return UxMongo.existing(collection, filter);
        }

        public static Future<JsonObject> insert(final String collection, final JsonObject data) {
            return UxMongo.insert(collection, data);
        }

        public static Future<JsonObject> findOne(final String collection, final JsonObject filter) {
            return UxMongo.findOne(collection, filter);
        }

        public static Future<JsonObject> findOne(final String collection, final JsonObject filter,
                                                 final String joinedCollection, final String joinedKey, final JsonObject additional,
                                                 final BinaryOperator<JsonObject> operatorFun) {
            return UxMongo.findOne(collection, filter, joinedCollection, joinedKey, additional, operatorFun);
        }

        public static Future<JsonObject> findOneAndReplace(final String collection, final JsonObject filter,
                                                           final String field, final Object value) {
            return UxMongo.findOneAndReplace(collection, filter, new JsonObject().put(field, value));
        }

        public static Future<JsonObject> findOneAndReplace(final String collection, final JsonObject filter,
                                                           final JsonObject data) {
            return UxMongo.findOneAndReplace(collection, filter, data);
        }

        public static Future<Long> removeDocument(final String collection, final JsonObject filter) {
            return UxMongo.removeDocument(collection, filter);
        }

        public static Future<JsonArray> findWithOptions(final String collection, final JsonObject filter,
                                                        final FindOptions options) {
            return UxMongo.findWithOptions(collection, filter, options);
        }

        public static Future<JsonArray> findWithOptions(final String collection, final JsonObject filter, final FindOptions options,
                                                        // Secondary Query
                                                        final String joinedCollection, final String joinedKey, final JsonObject additional,
                                                        final BinaryOperator<JsonObject> operatorFun) {
            return UxMongo.findWithOptions(collection, filter, options,
                    joinedCollection, joinedKey, additional, operatorFun);
        }

        public static Future<JsonArray> find(final String collection, final JsonObject filter) {
            return UxMongo.findWithOptions(collection, filter, new FindOptions());
        }
    }
}
