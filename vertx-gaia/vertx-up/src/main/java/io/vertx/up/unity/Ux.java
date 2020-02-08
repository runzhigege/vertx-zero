package io.vertx.up.unity;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.jwt.JWT;
import io.vertx.ext.jwt.JWTOptions;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.tp.plugin.database.DataPool;
import io.vertx.tp.plugin.jooq.JooqInfix;
import io.vertx.up.atom.query.Criteria;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.commune.Envelop;
import io.vertx.up.commune.Record;
import io.vertx.up.commune.config.Dict;
import io.vertx.up.commune.config.DictEpsilon;
import io.vertx.up.eon.Constants;
import io.vertx.up.eon.Strings;
import io.vertx.up.eon.em.ChangeFlag;
import io.vertx.up.exception.WebException;
import io.vertx.up.fn.Fn;
import io.vertx.up.fn.wait.Log;
import io.vertx.up.util.Ut;
import org.jooq.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Here Ux is a util interface of uniform to call different tools.
 * It just like helper for business usage.
 */
@SuppressWarnings("all")
public final class Ux {

    /*
     * Debug method for help us to do development
     * 1) log:  for branch log creation
     * 2) debug:
     * 3) otherwise:
     * ( Business Part: Debugging )
     */
    public static Log log(final Class<?> clazz) {
        return Log.create(null == clazz ? Ux.class : clazz);
    }

    public static void debug(final Object... objects) {
        Debug.monitor(objects);
    }

    public static <T> Future<T> debug(final T item) {
        return Debug.debug(item);
    }

    public static <T> T debug(final Throwable error, final Supplier<T> supplier) {
        return Debug.debug(error, supplier);
    }

    public static Function<Throwable, Envelop> otherwise() {
        return Debug.otherwise();
    }

    public static <T> Function<Throwable, T> otherwise(Supplier<T> supplier) {
        return Debug.otherwise(supplier);
    }

    /*
     * Entity ( Pojo ) to JsonObject, support pojo file here
     * 1) toJson / fromJson
     * 2) toToggle:  Toggle switch from interface style to worker here, the key should be "0", "1", "2", "3", ....
     * 3) toArray
     * ( Business Part, support `pojoFile` conversation )
     */
    public static <T> JsonObject toJson(final T entity) {
        return To.toJson(entity, "");
    }

    public static <T> JsonObject toJson(final T entity, final String pojo) {
        return To.toJson(entity, pojo);
    }

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

    public static <T> JsonArray toArray(final List<T> list) {
        return To.toArray(list, "");
    }

    public static JsonArray toArray(final Record[] records) {
        return To.toArray(records);
    }

    public static <T> JsonArray toArray(final List<T> list, final String pojo) {
        return To.toArray(list, pojo);
    }


    /*
     * Envelop building here
     * 1) envelop: ( Get different Envelop )
     * 2) future: ( Wrapper Future.successedFuture / Future.failureFuture ) at same time
     * 3) handler: ( Handler<AsyncResult<T>> )
     * 4) compare: ( Compare two object )
     */
    public static Envelop envelop(final Class<? extends WebException> clazz, final Object... args) {
        return To.toEnvelop(clazz, args);
    }

    public static <T> Envelop envelop(final T entity) {
        return To.toEnvelop(entity);
    }

    public static <T> Envelop envelop(final T entity, final WebException error) {
        return To.toEnvelop(entity, error);
    }

    public static <T> Future<T> future(final T entity) {
        return To.toFuture(entity);
    }

    public static <T> Future<T> future() {
        return To.toFuture(null);
    }

    public static <T> Handler<AsyncResult<T>> handler(final Message<Envelop> message) {
        return Web.toHandler(message);
    }

    public static <T, R> ConcurrentMap<ChangeFlag, List<T>> compare(final List<T> original, final List<T> current, final Function<T, R> fnValue, final String mergedPojo) {
        return Comparer.compare(original, current, fnValue, mergedPojo);
    }

    public static <T, R> ConcurrentMap<ChangeFlag, List<T>> compare(final List<T> original, final List<T> current, final Function<T, R> fnValue) {
        return Comparer.compare(original, current, fnValue, Strings.EMPTY);
    }

    /*
     * Flatting method for `Function Reference`
     * 1) fnRpc
     * 2) fnJObject / fnJArray
     * 3) fnJMap
     * 4) fnJMapType
     */
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

    public static <T> Function<T, Future<JsonObject>> fnJObject(final String pojo) {
        return item -> Future.succeededFuture(To.toJson(item, pojo));
    }

    public static <T> Function<List<T>, Future<JsonArray>> fnJArray(final String pojo) {
        return list -> Future.succeededFuture(To.toArray(list, pojo));
    }

    public static <T> Future<ConcurrentMap<String, JsonArray>> fnJMap(final List<T> item, final String field) {
        return fnJMap(To.toArray(item, ""), field);
    }

    public static Future<ConcurrentMap<String, JsonArray>> fnJMap(final JsonArray item, final String field) {
        return Future.succeededFuture(Ut.elementGroup(item, field));
    }

    public static <T> Future<ConcurrentMap<String, JsonArray>> fnJMapType(final List<T> item) {
        return fnJMap(To.toArray(item, ""), "type");
    }

    public static Future<ConcurrentMap<String, JsonArray>> fnJMapType(final JsonArray item) {
        return fnJMap(item, "type");
    }

    /*
     * Flatting method for function executing
     * 1) applyMount -> JsonObject ( field )
     * 2) applyMount -> Advanced JsonObject ( field )
     * 3) applyRpc
     * 4) applyBool
     */
    public static <T> Function<JsonObject, Future<JsonObject>> applyMount(final String field, final Function<T, Future<JsonObject>> function) {
        return Web.toAttach(field, function);
    }

    public static <T> Function<JsonObject, Future<JsonObject>> applyMountJson(final String field, final Function<T, Future<JsonObject>> function) {
        return Web.toAttachJson(field, function);
    }

    public static <T> Future<JsonObject> applyRpc(final String name, final String address, final JsonObject params) {
        return UxRpc.thenRpc(name, address, params);
    }

    public static <T> Future<JsonObject> applyRpc(final String name, final String address, final String field, final Object value) {
        return UxRpc.thenRpc(name, address, new JsonObject().put(field, value));
    }

    public static Future<JsonObject> applyBool(final Boolean result) {
        return Future.succeededFuture(new JsonObject().put(Strings.J_RESULT, result));
    }

    /*
     * Complex calculation
     * 1) thenCombine
     * 2) thenCombineArray
     * 3) thenCompress
     * 4) thenError
     *
     * Additional methods for generic T here
     * 1) thenCombineT
     * 2) thenCombineArrayT
     */

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
        return Combine.thenCombine(source, generateFun, operatorFun);
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
        return Combine.thenCombine(Future.succeededFuture(source), generateFun, operatorFun);
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
        return Combine.thenCombine(futures);
    }

    public static <T> Future<List<T>> thenCombineT(final List<Future<T>> futures) {
        return CombineT.thenCombine(futures);
    }

    public static Future<JsonArray> thenCombine(final JsonArray input, final Function<JsonObject, Future<JsonObject>> function) {
        final List<Future<JsonObject>> futures = new ArrayList<>();
        Ut.itJArray(input).map(function).forEach(futures::add);
        return Combine.thenCombine(futures);
    }

    public static <T> Future<ConcurrentMap<String, T>> thenCombine(final ConcurrentMap<String, Future<T>> futureMap) {
        return Combine.thenCombine(futureMap);
    }

    /*
     * Specific combine method here.
     */
    public static Future<JsonArray> thenCombineArray(final List<Future<JsonArray>> futures) {
        return Combine.thenCombineArray(futures);
    }

    public static <T> Future<List<T>> thenCombineArrayT(final List<Future<List<T>>> futures) {
        return CombineT.thenCombineArray(futures);
    }

    public static Future<ConcurrentMap<String, JsonArray>> thenCompress(final List<Future<ConcurrentMap<String, JsonArray>>> futures) {
        return Combine.thenCompress(futures, (original, latest) -> original.addAll(latest));
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
        return Combine.thenError(clazz, args);
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

    // -> Dict for caculation
    /*
     * Keep following dict method
     */
    public static ConcurrentMap<String, DictEpsilon> dictEpsilon(final JsonObject epsilon) {
        return DictTool.mapEpsilon(epsilon);
    }

    public static Future<ConcurrentMap<String, JsonArray>> dictCalc(final Dict dict, final MultiMap paramsMap) {
        return DictTool.dictCalc(dict, paramsMap);
    }

    // -> Jooq
    public static class Jooq {
        // Only history database will use new POOL to store data
        public static UxJooq ons(final Class<?> clazz) {
            final VertxDAO vertxDAO = (VertxDAO) JooqInfix.getDao(clazz, Constants.DEFAULT_JOOQ_HISTORY);
            return Fn.pool(io.vertx.up.unity.Pool.JOOQ_POOL_HIS, clazz, () -> new UxJooq(clazz, vertxDAO));
        }

        // Three method for overwrite
        public static UxJooq on(final Class<?> clazz, final DataPool pool) {
            final VertxDAO vertxDAO = (VertxDAO) JooqInfix.getDao(clazz, pool);
            return Fn.pool(io.vertx.up.unity.Pool.JOOQ_POOL, clazz, () -> new UxJooq(clazz, vertxDAO));
        }

        public static UxJooq on(final Class<?> clazz, final String key) {
            final VertxDAO vertxDAO = (VertxDAO) JooqInfix.getDao(clazz, key);
            return Fn.pool(io.vertx.up.unity.Pool.JOOQ_POOL, clazz, () -> new UxJooq(clazz, vertxDAO));
        }

        public static UxJooq on(final Class<?> clazz) {
            final VertxDAO vertxDAO = (VertxDAO) JooqInfix.getDao(clazz);
            return Fn.pool(io.vertx.up.unity.Pool.JOOQ_POOL, clazz, () -> new UxJooq(clazz, vertxDAO));
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

    /*
     * The only one uniform configuration of tp here
     */
    public static class Opt {
        public static UxOpt on() {
            return new UxOpt();
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
