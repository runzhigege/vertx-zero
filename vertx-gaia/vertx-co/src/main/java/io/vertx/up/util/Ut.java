package io.vertx.up.util;

import com.fasterxml.jackson.core.type.TypeReference;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Refer;
import io.vertx.up.exception.ZeroException;
import io.vertx.up.fn.Actuator;
import io.vertx.up.fn.ZeroBiConsumer;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.*;

/**
 * Uniform Tool
 */
@SuppressWarnings("all")
public final class Ut {
    private Ut() {
    }

    public static JsonArray sureJArray(final JsonArray array) {
        return Define.sureJArray(array);
    }

    public static JsonObject sureJObject(final JsonObject object) {
        return Define.sureJObject(object);
    }

    /*
     * Set Calculating
     * 1) intersect:    Set1 And Set2
     * 2) union:        Set1 Or  Set2
     * 3) diff:         Set1 -   Set2
     */
    public static <T> Set<T> intersect(final Set<T> left, final Set<T> right) {
        return Arithmetic.intersect(left, right);
    }

    public static <T> Set<T> union(final Set<T> left, final Set<T> right) {
        return Arithmetic.union(left, right);
    }

    public static <T> Set<T> diff(final Set<T> subtrahend, final Set<T> minuend) {
        return Arithmetic.diff(subtrahend, minuend);
    }

    /*
     * Array or List calculation
     * 1) elementAdd
     * 2) elementClimb
     * 3) elementFind
     * 4) elementZip
     * 5) elementGroup
     * 6) elementSubset
     * 7) elementChild
     */
    public static <T> T[] elementAdd(final T[] array, final T element) {
        return ArrayUtil.add(array, element);
    }

    public static JsonArray elementAdd(final JsonArray array, final JsonObject jsonObject, final String field) {
        return ArrayUtil.add(array, jsonObject, field);
    }

    public static JsonObject elementAdd(final JsonObject target, final JsonObject source, final String field) {
        Jackson.append(target, source, field);
        return target;
    }

    public static JsonObject elementAdd(final JsonObject target, final JsonObject source, final String... fields) {
        Arrays.stream(fields).forEach(field -> Jackson.append(target, source, field));
        return target;
    }

    public static JsonArray elementClimb(final JsonArray children, final JsonArray tree) {
        return ArrayUtil.climb(children, tree, null);
    }

    public static JsonArray elementClimb(final JsonObject child, final JsonArray tree) {
        return ArrayUtil.climb(child, tree, null);
    }

    public static JsonArray elementChild(final JsonArray children, final JsonArray tree) {
        return ArrayUtil.child(children, tree, null);
    }

    public static JsonArray elementChild(final JsonObject child, final JsonArray tree) {
        return ArrayUtil.child(child, tree, null);
    }

    public static JsonObject elementFind(final JsonArray input, final String field, final Object value) {
        return ArrayUtil.find(input, field, value);
    }

    public static <T> T elementFind(final List<T> list, final Predicate<T> fnFilter) {
        return Statute.find(list, fnFilter);
    }

    public static JsonArray elementZip(final JsonArray source, final JsonArray target, final String sourceKey, final String targetKey) {
        return Jackson.mergeZip(source, target, sourceKey, targetKey);
    }

    public static <F, S, T> List<T> elementZip(final List<F> first, final List<S> second, final BiFunction<F, S, T> function) {
        return Statute.zipper(first, second, function);
    }

    public static <F, T> ConcurrentMap<F, T> elementZip(final List<F> keys, final List<T> values) {
        return Statute.zipper(keys, values);
    }

    public static <K, V, E> ConcurrentMap<K, V> elementZip(final Collection<E> object, final Function<E, K> keyFn, final Function<E, V> valueFn) {
        return Statute.zipper(object, keyFn, valueFn);
    }

    public static <K, V, E> ConcurrentMap<K, V> elementZip(final E[] object, final Function<E, K> keyFn, final Function<E, V> valueFn) {
        return Statute.zipper(Arrays.asList(object), keyFn, valueFn);
    }

    public static <K, T, V> ConcurrentMap<K, V> elementZip(final ConcurrentMap<K, T> source, final ConcurrentMap<T, V> target) {
        return Statute.zipper(source, target);
    }

    public static JsonObject elementZip(final JsonArray array, final String field) {
        return Statute.zipper(array, field);
    }

    public static JsonObject elementSubset(final JsonObject input, final String... fields) {
        return Statute.subset(input, fields);
    }

    public static JsonArray elementSubset(final JsonArray input, final Function<JsonObject, Boolean> fnFilter) {
        return Statute.subset(input, fnFilter);
    }

    public static <K, V, E> ConcurrentMap<K, List<V>> elementGroup(final Collection<E> object, final Function<E, K> keyFn, final Function<E, V> valueFn) {
        return Statute.group(object, keyFn, valueFn);
    }

    public static <K, V> ConcurrentMap<K, V> elementGroup(final List<V> dataList, final Function<V, K> keyFunction) {
        return Statute.group(dataList, keyFunction);
    }

    public static ConcurrentMap<String, JsonArray> elementGroup(final JsonArray source, final String field) {
        return Statute.group(source, field);
    }

    /*
     * Encryption method for string
     * 1) encryptMD5
     * 2) encryptRSA
     * 3) encryptSHA256
     * 4) encryptSHA512
     */
    public static String encryptMD5(final String input) {
        return Codec.md5(input);
    }

    public static String encryptRSA(final String input, final String filePath) {
        return Rsa.encrypt(input, filePath);
    }

    public static String encryptRSA(final String input, final RSAPublicKey publicKey) {
        return Rsa.encrypt(input, publicKey);
    }

    public static String encryptSHA256(final String input) {
        return Codec.sha256(input);
    }

    public static String encryptSHA512(final String input) {
        return Codec.sha512(input);
    }

    /*
     * Comparing method of two
     * 1) compareTo: Generate comparing method to return int
     */
    public static int compareTo(final int left, final int right) {
        return Compare.compareTo(left, right);
    }

    public static int compareTo(final String left, final String right) {
        return Compare.compareTo(left, right);
    }

    public static <T> int compareTo(final T left, final T right, final BiFunction<T, T, Integer> fnCompare) {
        return Compare.compareTo(left, right, fnCompare);
    }

    /*
     * Interator method for different usage
     *  1) itMap
     *  2) itSet
     *  3) itDay
     *  4) itWeek
     *  5) itList
     *  6) itArray
     *  7) itMatrix
     *  8) itCollection
     *  9) itRepeat
     * 10) itJObject / etJObject
     * 11) itJArray / etJArray
     *
     * `it` means iterator method here
     * `et` means `Error Iterator` to be sure comsumer should throw some checked exception
     */
    public static <K, V> void itMap(final ConcurrentMap<K, V> map, final BiConsumer<K, V> fnEach) {
        Congregation.exec(map, fnEach);
    }

    public static <V> void itSet(final Set<V> set, final BiConsumer<V, Integer> fnEach) {
        final List<V> list = new ArrayList<>(set);
        Congregation.exec(list, fnEach);
    }

    public static void itDay(final String from, final String to, final Consumer<Date> consumer) {
        Period.itDay(from, to, consumer);
    }

    public static void itWeek(final String from, final String to, final Consumer<Date> consumer) {
        Period.itWeek(from, to, consumer);
    }

    public static <V> void itList(final List<V> list, final BiConsumer<V, Integer> fnEach) {
        Congregation.exec(list, fnEach);
    }

    public static <V> void itArray(final V[] array, final BiConsumer<V, Integer> fnEach) {
        Congregation.exec(Arrays.asList(array), fnEach);
    }

    public static <V> void itMatrix(final V[][] array, final Consumer<V> fnEach) {
        Congregation.exec(array, fnEach);
    }

    public static <F, S> void itCollection(final Collection<F> firsts, final Function<F, Collection<S>> seconds, final BiConsumer<F, S> consumer) {
        Congregation.exec(firsts, seconds, consumer);
    }

    public static <F, S> void itCollection(final Collection<F> firsts, final Function<F, Collection<S>> seconds, final BiConsumer<F, S> consumer, final BiPredicate<F, S> predicate) {
        Congregation.exec(firsts, seconds, consumer, predicate);
    }

    public static void itRepeat(final Integer times, final Actuator actuator) {
        Congregation.exec(times, actuator);
    }

    public static <T> void itJObject(final JsonObject data, final BiConsumer<T, String> fnEach) {
        Congregation.exec(data, fnEach);
    }

    public static <T> void itJArray(final JsonArray array, final Class<T> clazz, final BiConsumer<T, Integer> fnEach) {
        Congregation.exec(array, clazz, fnEach);
    }

    public static void itJArray(final JsonArray array, final BiConsumer<JsonObject, Integer> fnEach) {
        Congregation.exec(array, JsonObject.class, fnEach);
    }

    public static java.util.stream.Stream<JsonObject> itJArray(final JsonArray array) {
        return It.itJArray(array);
    }

    public static <T> void etJObject(final JsonObject data, final ZeroBiConsumer<T, String> fnIt) throws ZeroException {
        Congregation.execZero(data, fnIt);
    }

    public static <T> void etJArray(final JsonArray dataArray, final Class<T> clazz, final ZeroBiConsumer<T, Integer> fnIt) throws ZeroException {
        Congregation.execZero(dataArray, clazz, fnIt);
    }

    public static <T> void etJArray(final JsonArray dataArray, final ZeroBiConsumer<T, String> fnIt) throws ZeroException {
        Congregation.execZero(dataArray, fnIt);
    }

    /*
     * Ensure method for some spec
     * 1) koLenEqual:
     * 2) koLenMin:
     * To be sure arguments here
     */
    public static void koLenEqual(final Class<?> clazz, final int expected, final Object... args) {
        Ensurer.eqLength(clazz, expected, args);
    }

    public static void koLenMin(final Class<?> clazz, final int min, final Object... args) {
        Ensurer.gtLength(clazz, min, args);
    }

    /*
     * Reflection method to create instance
     * 1) instance
     * 2) singleton ( Global singleton object reference )
     *
     * Reflection method to be as action
     * 1) invoke / invokeAsync
     * 2) clazz
     * 3) isImplement
     * 4) proxy
     * 5) withNoArgConstructor
     * 6) childUnique
     * 7) field / fields
     * 8) contract / contractAsync ( @Contract )
     * 9) plugin
     */
    public static <T> T plugin(final JsonObject options, final String pluginKey, final Class<T> interfaceCls) {
        return Instance.plugin(options, pluginKey, interfaceCls);
    }

    public static <T> T instance(final String name, final Object... params) {
        return Instance.instance(clazz(name), params);
    }

    public static <T> T instance(final Class<?> clazz, final Object... params) {
        return Instance.instance(clazz, params);
    }

    public static <T> T singleton(final String name, final Object... params) {
        return Instance.singleton(clazz(name), params);
    }

    public static <T> T singleton(final Class<?> clazz, final Object... params) {
        return Instance.singleton(clazz, params);
    }

    public static <T> T invoke(final Object instance, final String name, final Object... args) {
        return Invoker.invokeObject(instance, name, args);
    }

    public static <T> T invoke(final Class<?> interfaceCls, final String name, final Object... args) {
        return Invoker.invokeInterface(interfaceCls, name, args);
    }

    public static <T> Future<T> invokeAsync(final Object instance, final Method method, final Object... args) {
        return Invoker.invokeAsync(instance, method, args);
    }

    public static Class<?> clazz(final String name) {
        return Instance.clazz(name);
    }

    public static Class<?> clazz(final String name, final Class<?> defaultCls) {
        return Instance.clazz(name, defaultCls);
    }

    public static boolean isImplement(final Class<?> clazz, final Class<?> interfaceCls) {
        return Instance.isMatch(clazz, interfaceCls);
    }

    public static <T> T proxy(final Method method) {
        return Instance.getProxy(method);
    }

    public static boolean withNoArgConstructor(final Class<?> clazz) {
        return Instance.noarg(clazz);
    }

    public static Class<?> childUnique(final Class<?> clazz) {
        return Instance.uniqueChild(clazz);
    }

    public static <T> void field(final Object instance, final String name, final T value) {
        InstanceField.set(instance, name, value);
    }

    public static <T> void field(final Object instance, final Field field, final T value) {
        InstanceField.set(instance, field, value);
    }

    public static <T> T field(final Object instance, final String name) {
        return InstanceField.get(instance, name);
    }

    public static <T> T field(final Class<?> interfaceCls, final String name) {
        return InstanceField.getI(interfaceCls, name);
    }

    public static Field[] fields(final Class<?> clazz) {
        return InstanceField.fields(clazz);
    }

    public static <T> Field contract(final T instance, final Class<?> fieldType) {
        return InstanceField.contract(InstanceField.class, instance, fieldType);
    }

    public static <T, V> void contract(final T instance, final Class<?> fieldType, final V value) {
        InstanceField.contract(InstanceField.class, instance, fieldType, value);
    }

    public static <T, V> Future<Boolean> contractAsync(final T instance, final Class<?> fieldType, final V value) {
        contract(instance, fieldType, value);
        return Future.succeededFuture(Boolean.TRUE);
    }

    /*
     * IO method here to read file content & folder resource
     * 1) ioFiles / ioDirectories ( Folder Reading )
     * 2) ioYaml
     * 3) ioFile
     * 4) ioProperties
     * 5) ioJArray / ioJObject
     * 6) ioString
     * 7) ioBuffer / ioStream
     *
     * `input` operation
     */
    public static List<String> ioFiles(final String folder) {
        return Folder.listFiles(folder);
    }

    public static List<String> ioFiles(final String folder, final String extension) {
        return Folder.listFiles(folder, extension);
    }

    public static List<String> ioDirectories(final String folder) {
        return Folder.listDirectories(folder);
    }

    public static <T> T ioYaml(final String filename) {
        return IO.getYaml(filename);
    }

    public static File ioFile(final String filename) {
        return IO.getFile(filename);
    }

    public static Properties ioProperties(final String filename) {
        return IO.getProp(filename);
    }

    public static JsonArray ioJArray(final String filename) {
        return IO.getJArray(filename);
    }

    public static JsonObject ioJObject(final String filename) {
        return IO.getJObject(filename);
    }

    public static String ioString(final InputStream in) {
        return IO.getString(in);
    }

    public static String ioString(final String filename) {
        return IO.getString(filename);
    }

    public static Buffer ioBuffer(final String filename) {
        return IO.getBuffer(filename);
    }

    public static InputStream ioStream(final File file) {
        return Stream.in(file);
    }

    public static InputStream ioStream(final String filename, final Class<?> clazz) {
        return Stream.in(filename, clazz);
    }

    public static InputStream ioStream(final String filename) {
        return Stream.in(filename);
    }

    /*
     * `output` operation
     * 1) ioOut ( JsonObject / JsonArray / String ) writing method
     */
    public static void ioOut(final String file, final String data) {
        Out.write(file, data);
    }

    public static void ioOut(final String file, final JsonObject data) {
        Out.write(file, data);
    }

    public static void ioOut(final String file, final JsonArray data) {
        Out.write(file, data);
    }

    /*
     * Serialization method operation method here.
     * 1) serialize / serializeJson
     * 2) deserialize
     *
     * Object converting here of serialization
     */
    public static <T, R extends Iterable> R serializeJson(final T t) {
        return Jackson.serializeJson(t);
    }

    public static <T> String serialize(final T t) {
        return Jackson.serialize(t);
    }

    public static <T> T deserialize(final JsonObject value, final Class<T> type) {
        return Jackson.deserialize(value, type);
    }

    public static <T> T deserialize(final JsonArray value, final Class<T> type) {
        return Jackson.deserialize(value, type);
    }

    public static <T> List<T> deserialize(final JsonArray value, final TypeReference<List<T>> type) {
        return Jackson.deserialize(value, type);
    }

    public static <T> T deserialize(final String value, final Class<T> clazz) {
        return Jackson.deserialize(value, clazz);
    }

    public static <T> T deserialize(final String value, final TypeReference<T> type) {
        return Jackson.deserialize(value, type);
    }

    /*
     * Flatting method for function executing
     * 1) item -> Ut.applyAdd(set, item)
     * 2) item -> Ut.applyAdd(refer, item)
     * 3) applyMerge
     * 4) applyNil / applyJNil
     * 5) applyJValue -> JsonObject field filling of value
     */
    public static <T> T applyAdd(final Set<T> sets, final T entity) {
        sets.add(entity);
        return entity;
    }

    public static <T> T applyAdd(final Refer refer, final T entity) {
        return refer.add(entity).get();
    }

    public static JsonObject applyMerge(final JsonObject target, final JsonObject source) {
        return Jackson.flatMerge(target, source);
    }

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

    public static Function<JsonArray, Future<JsonArray>> applyJEmpty(final Function<JsonArray, Future<JsonArray>> executor) {
        return Apply.applyJEmpty(executor);
    }

    public static <T> Function<T[], Future<T[]>> applyEmpty(final Function<T[], Future<T[]>> executor) {
        return Apply.applyEmpty(executor);
    }

    public static <T> Function<T, Future<JsonObject>> applyField(final JsonObject input, final String field) {
        return Apply.applyField(input, field);
    }

    public static <T, V> Consumer<JsonObject> applyField(final String field, final Function<V, T> function) {
        return Apply.applyField(field, function);
    }

    public static <T> Function<T, Future<JsonObject>> applyMerge(final JsonObject input) {
        return Apply.applyField(input, null);
    }

    public static JsonObject applyJValue(final JsonObject record, final String field, final Object value) {
        return Apply.applyJValue(record, field, value);
    }

    public static JsonObject applyJValue(final String field, final Object value) {
        return Apply.applyJValue(null, field, value);
    }

    /*
     * ConfigStoreOptions reading
     * 1) loadJson
     * 2) loadYaml
     * 3) loadProp
     */
    public static ConfigStoreOptions loadJson(final String filename) {
        return Store.getJson(filename);
    }

    public static ConfigStoreOptions loadYaml(final String filename) {
        return Store.getYaml(filename);
    }

    public static ConfigStoreOptions loadProp(final String filename) {
        return Store.getProp(filename);
    }

    /*
     * Network method
     * 1) netOk
     * 2) netIPv4 / netIPv6 / netIP
     * 3) netHostname
     * 4) netStatus
     * 5) netUri
     *
     */
    public static boolean netOk(final String host, final int port) {
        return Net.isReach(host, port);
    }

    public static boolean netOk(final String host, final int port, final int timeout) {
        return Net.isReach(host, port, timeout);
    }

    public static String netIPv4() {
        return Net.getIPv4();
    }

    public static String netHostname() {
        return Net.getHostName();
    }

    public static String netIPv6() {
        return Net.getIPv6();
    }

    public static String netIP() {
        return Net.getIP();
    }

    public static JsonObject netStatus(final String line) {
        return Net.netStatus(line);
    }

    public static String netUri(final String url) {
        return Net.netUri(url);
    }

    /*
     * Map reduce method here
     * 1) reduce
     */
    public static <K, T, V> ConcurrentMap<K, V> reduce(final ConcurrentMap<K, T> from, final ConcurrentMap<T, V> to) {
        return Statute.reduce(from, to);
    }

    public static <K, V> ConcurrentMap<K, V> reduce(final Set<K> from, final ConcurrentMap<K, V> to) {
        return Statute.reduce(from, to);
    }

    /*
     * Checking method for all
     * 1) isSame / isDate
     * 2) isBoolean
     * 3) isPositive / isNegative / isInteger
     * 4) isDecimal / isReal / isDecimalPositive / isDecimalNegative
     * 5) isSubset ( Compare 2 JsonObject )
     * 6) isJObject / isJArray
     * 7) isClass / isVoid / isPrimary
     * 8) isNil / notNil
     * 9) isRange
     */
    public static boolean isRange(final Integer value, final Integer min, final Integer max) {
        return Numeric.isRange(value, min, max);
    }

    public static boolean isSame(final Date left, final Date right) {
        return Period.equalDate(left, right);
    }

    public static boolean isBoolean(final Class<?> clazz) {
        return Types.isBoolean(clazz);
    }

    public static boolean isBoolean(final Object value) {
        return Types.isBoolean(value);
    }

    public static boolean isPositive(final String original) {
        return Numeric.isPositive(original);
    }

    public static boolean isPositive(final int number) {
        return Numeric.isPositive(number);
    }

    public static boolean isPositive(final int[] numbers) {
        return Numeric.isPositive(numbers);
    }

    public static boolean isNegative(final String original) {
        return Numeric.isNegative(original);
    }

    public static boolean isInteger(final String original) {
        return Numeric.isInteger(original);
    }

    public static boolean isInteger(final Object value) {
        return Types.isInteger(value);
    }

    public static boolean isInteger(final Class<?> clazz) {
        return Types.isInteger(clazz);
    }

    public static boolean isDecimal(final Object value) {
        return Types.isDecimal(value);
    }

    public static boolean isDecimal(final Class<?> clazz) {
        return Types.isDecimal(clazz);
    }

    public static boolean isDecimal(final String original) {
        return Numeric.isDecimal(original);
    }

    public static boolean isReal(final String original) {
        return Numeric.isReal(original);
    }

    public static boolean isDecimalPositive(final String original) {
        return Numeric.Decimal.isPositive(original);
    }

    public static boolean isDecimalNegative(final String original) {
        return Numeric.Decimal.isNegative(original);
    }

    public static boolean isDate(final Object value) {
        return Types.isDate(value);
    }

    public static boolean isSubset(final JsonObject cond, final JsonObject record) {
        return Is.isSubset(cond, record);
    }

    public static boolean isChanged(final JsonObject oldRecord, final JsonObject newRecord,
                                    final Set<String> ignores, final ConcurrentMap<String, Class<?>> dateFields) {
        return Is.isChanged(oldRecord, newRecord, ignores, dateFields, null);
    }

    public static boolean isChanged(final JsonObject oldRecord, final JsonObject newRecord,
                                    final Set<String> ignores, final ConcurrentMap<String, Class<?>> dateFields,
                                    final BiFunction<String, Class<?>, BiPredicate<Object, Object>> fnPredicate) {
        return Is.isChanged(oldRecord, newRecord, ignores, dateFields, fnPredicate);
    }

    public static TemporalUnit toUnit(final Class<?> clazz) {
        return Is.getUnit(clazz);
    }

    public static boolean isSame(final Object oldValue, final Object newValue, final boolean isDate,
                                 final TemporalUnit unit) {
        return Is.isSame(oldValue, newValue, isDate, unit);
    }

    public static boolean isSame(final Object oldValue, final Object newValue, final boolean isDate) {
        return Is.isSame(oldValue, newValue, isDate, null);
    }

    public static boolean isJArray(final String literal) {
        return Types.isJArray(literal);
    }

    public static boolean isJArray(final Object value) {
        return Types.isJArray(value);
    }

    public static boolean isJArray(final Class<?> clazz) {
        return Types.isJArray(clazz);
    }

    public static boolean isJObject(final String literal) {
        return Types.isJObject(literal);
    }

    public static boolean isJObject(final Object value) {
        return Types.isJObject(value);
    }

    public static boolean isJObject(final Class<?> clazz) {
        return Types.isJObject(clazz);
    }

    public static boolean isVoid(final Class<?> clazz) {
        return Types.isVoid(clazz);
    }

    public static boolean isClass(final Object value) {
        return Types.isClass(value);
    }

    public static boolean isPrimary(final Class<?> clazz) {
        return Types.isPrimary(clazz);
    }

    public static boolean isNil(final String input) {
        return StringUtil.isNil(input);
    }

    public static boolean isNil(final JsonObject json) {
        return Types.isEmpty(json);
    }

    public static boolean isNil(final JsonArray jsonArray) {
        return Types.isEmpty(jsonArray);
    }

    public static boolean notNil(final String input) {
        return StringUtil.notNil(input);
    }

    public static boolean notNil(final JsonObject json) {
        return !isNil(json);
    }

    public static boolean notNil(final JsonArray jsonArray) {
        return !isNil(jsonArray);
    }

    /*
     * Ai analyzing for type based on
     * some different business requirement
     */
    public static Object aiJValue(final Object input, final Class<?> type) {
        return Value.aiJValue(input, type);
    }

    public static Object aiJValue(final Object input) {
        return Value.aiJValue(input, null);
    }

    public static Object aiValue(final Object input, final Class<?> type) {
        return Value.aiValue(input, type);
    }

    public static Object aiValue(final Object input) {
        return Value.aiValue(input, null);
    }

    /*
     * To conversation here
     * 1) toJArray
     * 2) toJObject
     * 3) toJValue
     * 4) toMonth / toYear
     * 5) toEnum
     * 6) toCollection / toPrimary
     * 7) toString
     * 8) toDateTime / toDate / toTime
     * 9) toBytes
     * 10) toSet
     */
    public static Set<String> toSet(final String input, final String separator) {
        return StringUtil.split(input, separator);
    }

    public static Set<String> toSet(final JsonArray keys) {
        return To.toSet(keys);
    }

    public static JsonArray toJArray(final Object value) {
        return Jackson.toJArray(value);
    }

    public static JsonArray toJArray(final String literal) {
        return To.toJArray(literal);
    }

    public static <T> JsonArray toJArray(final T value, final int repeat) {
        return To.toJArray(value, repeat);
    }

    public static <T> JsonArray toJArray(final Set<T> set) {
        return To.toJArray(set);
    }

    public static <T> JsonArray toJArray(final List<T> list) {
        return To.toJArray(list);
    }

    public static JsonObject toJObject(final String literal) {
        return To.toJObject(literal);
    }

    public static JsonObject toJObject(final Map<String, Object> map) {
        return To.toJObject(map);
    }

    public static int toMonth(final String literal) {
        return Period.toMonth(literal);
    }

    public static int toMonth(final Date date) {
        return Period.toMonth(date);
    }

    public static int toYear(final String literal) {
        return Period.toYear(literal);
    }

    public static int toYear(final Date date) {
        return Period.toYear(date);
    }

    public static <T extends Enum<T>> T toEnum(final Class<T> clazz, final String input) {
        return To.toEnum(clazz, input);
    }

    public static <T extends Enum<T>> T toEnum(final Supplier<String> supplier, final Class<T> type, final T defaultEnum) {
        return To.toEnum(supplier, type, defaultEnum);
    }

    public static String toString(final Object reference) {
        return To.toString(reference);
    }

    public static Collection toCollection(final Object value) {
        return To.toCollection(value);
    }

    public static Class<?> toPrimary(final Class<?> source) {
        return To.toPrimary(source);
    }

    public static LocalDateTime toDateTime(final String literal) {
        return Period.toDateTime(literal);
    }

    public static LocalDate toDate(final String literal) {
        return Period.toDate(literal);
    }

    public static LocalTime toTime(final String literal) {
        return Period.toTime(literal);
    }

    public static <T> byte[] toBytes(final T message) {
        return Stream.to(message);
    }

    public static LocalDate toDate(final Date date) {
        return Period.toDate(date);
    }

    public static LocalTime toTime(final Date date) {
        return Period.toTime(date);
    }

    public static LocalDateTime toDateTime(final Date date) {
        return Period.toDateTime(date);
    }

    public static LocalDate toDate(final Instant date) {
        return Period.toDate(date);
    }

    public static LocalTime toTime(final Instant date) {
        return Period.toTime(date);
    }

    public static LocalDateTime toDateTime(final Instant date) {
        return Period.toDateTime(date);
    }

    /*
     * JsonObject tree visiting
     * 1) visitJObject
     * 2) visitJArray
     * 3) visitInt
     * 4) visitString
     */
    public static JsonObject visitJObject(final JsonObject item, final String... keys) {
        return Jackson.visitJObject(item, keys);
    }

    public static JsonArray visitJArray(final JsonObject item, final String... keys) {
        return Jackson.visitJArray(item, keys);
    }

    public static Integer visitInt(final JsonObject item, final String... keys) {
        return Jackson.visitInt(item, keys);
    }

    public static String visitString(final JsonObject item, final String... keys) {
        return Jackson.visitString(item, keys);
    }

    /*
     * Random method
     * 1) randomNumber
     * 2) randomString
     * 3) randomLetter
     */
    public static Integer randomNumber(final int length) {
        return Numeric.randomNumber(length);
    }

    public static String randomString(final int length) {
        return StringUtil.random(length);
    }

    public static String randomLetter(final int length) {
        return StringUtil.randomNoDigit(length);
    }

    /*
     * Date literal parse here for time calculation
     * 1) parse / parseFull
     * 2) now()
     */
    public static Date parse(final String literal) {
        return Period.parse(literal);
    }

    public static Date parse(final LocalTime time) {
        return Period.parse(time);
    }

    public static Date parse(final LocalDateTime datetime) {
        return Period.parse(datetime);
    }

    public static Date parse(final LocalDate date) {
        return Period.parse(date);
    }

    public static Date now() {
        return Period.parse(LocalDateTime.now());
    }

    public static Date parseFull(final String literal) {
        return Period.parseFull(literal);
    }

    /*
     * String conversation
     * 1) fromBuffer
     * 2) fromObject
     * 3) fromJObject
     * 4) fromJoin
     * 5) fromAequilatus
     * 6) fromExpression
     */
    public static <T> T fromBuffer(final int pos, final Buffer buffer) {
        return Stream.from(pos, buffer);
    }

    public static String fromObject(final Object value) {
        return StringUtil.from(value);
    }

    public static String fromJObject(final JsonObject value) {
        return StringUtil.from(value);
    }

    public static String fromJoin(final Set<String> input) {
        return StringUtil.join(input, null);
    }

    public static String fromJoin(final List<String> input, final String separator) {
        return StringUtil.join(input, separator);
    }

    public static String fromJoin(final List<String> input) {
        return StringUtil.join(input, null);
    }

    public static String fromJoin(final Set<String> input, final String separator) {
        return StringUtil.join(input, separator);
    }

    public static String fromJoin(final Object[] input) {
        return fromJoin(input, ",");
    }

    public static String fromJoin(final Object[] input, final String separator) {
        return StringUtil.join(input, separator);
    }

    public static String fromAequilatus(final Integer seed, final Integer width, final char fill) {
        return StringUtil.aequilatus(seed, width, fill);
    }

    public static String fromAequilatus(final Integer seed, final Integer width) {
        return StringUtil.aequilatus(seed, width, '0');
    }

    public static String fromExpression(final String expr, final JsonObject data) {
        return StringUtil.expression(expr, data);
    }

    /*
     * Math method
     * 1) mathMultiply
     * 2) mathSumDecimal
     * 3) mathSumInteger
     * 4) mathSumLong
     */
    public static Integer mathMultiply(final Integer left, final Integer right) {
        return Numeric.mathMultiply(left, right);
    }

    public static BigDecimal mathSumDecimal(final JsonArray source, final String field) {
        return Numeric.mathJSum(source, field, BigDecimal.class);
    }

    public static Integer mathSumInteger(final JsonArray source, final String field) {
        return Numeric.mathJSum(source, field, Integer.class);
    }

    public static Long mathSumLong(final JsonArray source, final String field) {
        return Numeric.mathJSum(source, field, Long.class);
    }

    /*
     * Range getting
     * 1) inRange:  range data extraction
     */
    public static List<String> inRange(final String from, final String to) {
        return Period.inRange(from, to);
    }

    /*
     * Mapping operation
     */
    public static Set<String> mapString(final JsonArray array, final String field) {
        return Epsilon.mapString(array, field);
    }

    public static String mapStringOne(final JsonArray array, final String field) {
        return Epsilon.mapStringOne(array, field);
    }

    public static Set<JsonArray> mapArray(final JsonArray array, final String field) {
        return Epsilon.mapArray(array, field);
    }

    public static <T> T mapValue(final JsonObject item, final String field, final Class<T> clazz) {
        return Epsilon.mapValue(item, field, clazz);
    }
}
