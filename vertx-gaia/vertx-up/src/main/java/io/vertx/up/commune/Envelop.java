package io.vertx.up.commune;

import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpStatusCode;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.up.commune.envelop.Rib;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception.web._500InternalServerException;
import io.vertx.up.fn.Fn;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;
import io.vertx.zero.exception.IndexExceedException;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class Envelop implements Serializable {

    /* Basic Data for Envelop such as: Data, Error, Status */
    private final HttpStatusCode status;
    private final WebException error;
    private final JsonObject data;

    /* Additional Data for Envelop, Assist Data here. */
    private final Assist assist = new Assist();
    private final JsonObject cachedJwt = new JsonObject();
    /* Communicate Key in Event Bus, to identify the Envelop */
    private String key;
    /*
     * Constructor for Envelop creation, two constructor for
     * 1) Success Envelop
     * 2) Failure Envelop
     * All Envelop are private mode with non-single because it's
     * Data Object instead of tool or other reference
     */

    /**
     * @param data   input data that stored into Envelop
     * @param status http status of this Envelop
     * @param <T>    The type of stored data
     */
    private <T> Envelop(final T data, final HttpStatusCode status) {
        this.data = Rib.input(data);
        error = null;
        this.status = status;
    }

    /**
     * @param error input error that stored into Envelop
     */
    private Envelop(final WebException error) {
        status = error.getStatus();
        this.error = error;
        data = error.toJson();
    }

    /*
     * Static method to create new Envelop with different fast mode here.
     * 1) Success: 204 Empty Envelop ( data = null )
     * 2) Success: 200 With input data ( data = T )
     * 3) Success: XXX with input data ( data = T ) XXX means that you can have any HttpStatus
     * 4) Error: 500 Default error with description
     * 5) Error: XXX with input WebException
     * 6) Error: 500 Default with Throwable ( JVM Error )
     */
    // 204, null
    public static Envelop ok() {
        return new Envelop(null, HttpStatusCode.NO_CONTENT);
    }

    public static Envelop okJson() {
        return new Envelop(new JsonObject(), HttpStatusCode.OK);
    }

    // 200, T
    public static <T> Envelop success(final T entity) {
        return new Envelop(entity, HttpStatusCode.OK);
    }

    // xxx, T
    public static <T> Envelop success(final T entity, final HttpStatusCode status) {
        return new Envelop(entity, status);
    }

    // default error 500
    public static Envelop failure(final String message) {
        return new Envelop(new _500InternalServerException(Envelop.class, message));
    }

    // default error 500 ( JVM Error )
    public static Envelop failure(final Throwable ex) {
        return new Envelop(new _500InternalServerException(Envelop.class, ex.getMessage()));
    }

    // other error with WebException
    public static Envelop failure(final WebException error) {
        return new Envelop(Rib.normalize(error));
    }

    // ------------------ Above are initialization method -------------------
    /*
     * Predicate to check envelop to see whether is't valid
     * Error = null means valid
     */
    public boolean valid() {
        return null == error;
    }

    public WebException error() {
        return error;
    }

    // ------------------ Below are data part -------------------
    /* Get `data` part */
    public <T> T data() {
        return Rib.get(data);
    }

    /* Get `data` part by type */
    public <T> T data(final Class<T> clazz) {
        return Rib.get(data, clazz);
    }

    /* Get `data` part by argIndex here */
    public <T> T data(final Integer argIndex, final Class<T> clazz) {
        Fn.outUp(!Rib.isIndex(argIndex), IndexExceedException.class, getClass(), argIndex);
        return Rib.get(data, clazz, argIndex);
    }

    /* Set value in `data` part */
    public void setValue(final String field, final Object value) {
        Rib.set(data, field, value, null);
    }

    /* Set value in `data` part ( with Index ) */
    public void setValue(final Integer argIndex, final String field, final Object value) {
        Rib.set(data, field, value, argIndex);
    }

    // ------------------ Below are response Part -------------------
    /* String */
    public String outString() {
        return outJson().encode();
    }

    /* Json */
    public JsonObject outJson() {
        return Rib.outJson(data, error);
    }

    /* Buffer */
    public Buffer outBuffer() {
        return Rib.outBuffer(data, error);
    }

    /* Future */
    public Future<Envelop> toFuture() {
        return Future.succeededFuture(this);
    }

    // ------------------ Below are Bean Get -------------------
    /* HttpStatusCode */
    public HttpStatusCode status() {
        return status;
    }

    /* Communicate Id */
    public Envelop key(final String key) {
        this.key = key;
        return this;
    }

    public String key() {
        return key;
    }

    // ------------------ Below are Query part ----------------
    private void reference(final Consumer<JsonObject> consumer) {
        final JsonObject reference = Rib.getBody(data);
        if (Objects.nonNull(reference)) {
            consumer.accept(reference);
        }
    }

    /* Query Part for projection */
    public void onProjection(final JsonArray projection) {
        reference(reference -> Rib.projection(reference, projection, false));
    }

    public void inProjection(final JsonArray projection) {
        reference(reference -> Rib.projection(reference, projection, true));
    }

    /* Query Part for criteria */
    public void onCriteria(final JsonObject criteria) {
        reference(reference -> Rib.criteria(reference, criteria, false));
    }

    public void inCriteria(final JsonObject criteria) {
        reference(reference -> Rib.criteria(reference, criteria, true));
    }

    // ------------------ Below are assist method -------------------
    /*
     * Assist Data for current Envelop, all these methods will resolve the issue
     * of EventBus splitted. Because all the request data could not be got from Worker class,
     * then the system will store some reference/data into Envelop and then after
     * this envelop passed from EventBus address, it also could keep state here.
     */
    /* Extract data from Context Map */
    public <T> T context(final String key, final Class<T> clazz) {
        return assist.getContextData(key, clazz);
    }

    /* Get user data from User of Context */
    public String identifier(final String field) {
        return assist.principal(field);
    }

    /*
     * Get jwt information here
     */
    public String jwt(final String field) {
        if (Ut.isNil(cachedJwt)) {
            final String jwt = assist.principal("jwt");
            final JsonObject user = Ux.Jwt.extract(jwt);
            cachedJwt.mergeIn(user, true);
        }
        return cachedJwt.getString(field);
    }

    public User user() {
        return assist.user();
    }

    public void setUser(final User user) {
        assist.user(user);
    }

    /* Get Headers */
    public MultiMap headers() {
        return assist.headers();
    }

    public void setHeaders(final MultiMap headers) {
        assist.headers(headers);
    }

    /* Session */
    public Session getSession() {
        return assist.session();
    }

    public void setSession(final Session session) {
        assist.session(session);
    }

    /* Uri */
    public String getUri() {
        return assist.uri();
    }

    public void setUri(final String uri) {
        assist.uri(uri);
    }

    /* Method of Http */
    public HttpMethod getMethod() {
        return assist.method();
    }

    public void setMethod(final HttpMethod method) {
        assist.method(method);
    }

    /* Context Set */
    public void setContext(final Map<String, Object> data) {
        assist.context(data);
    }

    /*
     * Bind Routing Context to process Assist structure
     */
    public Envelop bind(final RoutingContext context) {
        final HttpServerRequest request = context.request();

        /* Http Request Part */
        assist.headers(request.headers());
        assist.uri(request.uri());
        assist.method(request.method());

        /* Session, User, Data */
        assist.session(context.session());
        assist.user(context.user());
        assist.context(context.data());
        return this;
    }

    @Override
    public String toString() {
        return "Envelop{" +
                "status=" + status +
                ", error=" + error +
                ", data=" + data +
                ", assist=" + assist +
                ", key='" + key + '\'' +
                '}';
    }
}
