package io.vertx.up.secure.handler;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.http.*;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.AuthHandler;
import io.vertx.up.eon.ID;
import io.vertx.up.exception.*;
import io.vertx.up.log.Annal;
import io.vertx.up.web.ZeroAnno;
import io.vertx.zero.eon.Strings;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("all")
public abstract class AuthPhylum implements AuthHandler {

    static final String AUTH_PROVIDER_CONTEXT_KEY = "io.vertx.ext.web.handler.AuthHandler.provider";
    protected final String realm;
    protected final Set<String> authorities = new HashSet<>();
    final WebException FORBIDDEN = new _403ForbiddenException(this.getClass());
    final WebException UNAUTHORIZED = new _401UnauthorizedException(this.getClass());
    final WebException BAD_REQUEST = new _400BadRequestException(this.getClass());
    // Provider binding
    protected transient AuthProvider authProvider;

    public AuthPhylum(final AuthProvider authProvider) {
        this(authProvider, "");
    }

    public AuthPhylum(final AuthProvider authProvider, final String realm) {
        this.authProvider = authProvider;
        this.realm = realm;
    }

    @Override
    public AuthHandler addAuthority(final String authority) {
        this.authorities.add(authority);
        return this;
    }

    @Override
    public AuthHandler addAuthorities(final Set<String> authorities) {
        this.authorities.addAll(authorities);
        return this;
    }

    protected String authenticateHeader(final RoutingContext context) {
        return null;
    }

    @Override
    public void authorize(final User user, final Handler<AsyncResult<Void>> handler) {
        final int requiredcount = this.authorities.size();
        if (requiredcount > 0) {
            if (user == null) {
                handler.handle(Future.failedFuture(this.FORBIDDEN));
                return;
            }

            final AtomicInteger count = new AtomicInteger();
            final AtomicBoolean sentFailure = new AtomicBoolean();

            final Handler<AsyncResult<Boolean>> authHandler = res -> {
                if (res.succeeded()) {
                    if (res.result()) {
                        if (count.incrementAndGet() == requiredcount) {
                            // Has all required authorities
                            handler.handle(Future.succeededFuture());
                        }
                    } else {
                        if (sentFailure.compareAndSet(false, true)) {
                            handler.handle(Future.failedFuture(this.FORBIDDEN));
                        }
                    }
                } else {
                    handler.handle(Future.failedFuture(res.cause()));
                }
            };
            for (final String authority : this.authorities) {
                if (!sentFailure.get()) {
                    user.isAuthorized(authority, authHandler);
                }
            }
        } else {
            // No auth required
            handler.handle(Future.succeededFuture());
        }
    }

    @Override
    public void handle(final RoutingContext ctx) {
        /*
         * Because AuthPhylum component is Handler of vert.x, here it the first step
         * Call handlePreflight to verify http specification
         * Please refer: https://www.w3.org/TR/cors/#cross-origin-request-with-preflight-0
         * Cross domain
         */
        if (this.handlePreflight(ctx)) {
            return;
        }

        final User user = ctx.user();
        if (user != null) {
            // proceed to AuthZ
            this.authorizeUser(ctx, user);
            return;
        }
        // parse the request in order to extract the credentials object
        this.parseCredentials(ctx, res -> {
            if (res.failed()) {
                this.processException(ctx, res.cause());
                return;
            }
            // check if the user has been set
            final User updatedUser = ctx.user();

            if (updatedUser != null) {
                final Session session = ctx.session();
                if (session != null) {
                    // the user has upgraded from unauthenticated to authenticated
                    // session should be upgraded as recommended by owasp
                    session.regenerateId();
                }
                // proceed to AuthZ
                this.authorizeUser(ctx, updatedUser);
                return;
            }
            // proceed to authN
            this.getAuthProvider(ctx).authenticate(this.build(res.result(), ctx), authN -> {
                if (authN.succeeded()) {
                    final User authenticated = authN.result();
                    ctx.setUser(authenticated);
                    final Session session = ctx.session();
                    if (session != null) {
                        // the user has upgraded from unauthenticated to authenticated
                        // session should be upgraded as recommended by owasp
                        session.regenerateId();
                        // Store user's princple into session
                    }
                    // proceed to AuthZ
                    this.authorizeUser(ctx, authenticated);
                } else {
                    // The first time to get
                    final String header = this.authenticateHeader(ctx);
                    if (header != null) {
                        ctx.response().putHeader("WWW-Authenticate", header);
                    }
                    ctx.fail(null == authN.cause() ? this.UNAUTHORIZED : authN.cause());
                }
            });
        });
    }

    private void processException(final RoutingContext ctx, final Throwable exception) {
        if (null != exception) {
            if (exception instanceof WebException) {
                final WebException error = (WebException) exception;
                final HttpStatusCode statusCode = error.getStatus();
                final String payload = error.getMessage();

                final HttpServerResponse response = ctx.response();

                switch (statusCode) {
                    case MOVE_TEMPORARILY: {
                        response.putHeader(HttpHeaders.LOCATION, payload)
                                .setStatusCode(statusCode.code())
                                .end("Redirecting to " + payload + ".");
                    }
                    return;
                    case UNAUTHORIZED: {
                        final String header = this.authenticateHeader(ctx);
                        if (null != header) {
                            response.putHeader("WWW-Authenticate", header);
                        }
                        ctx.fail(this.UNAUTHORIZED);
                    }
                    return;
                    default:
                        ctx.fail(error);
                        return;
                }
            }
        }
        // Fallback 500
        ctx.fail(new _500InternalServerException(this.getClass(),
                null == exception ? null : exception.getMessage()));
    }

    private void authorizeUser(final RoutingContext ctx, final User user) {
        this.authorize(user, authZ -> {
            if (authZ.failed()) {
                this.processException(ctx, authZ.cause());
                return;
            }
            // success, allowed to continue
            ctx.next();
        });
    }

    private boolean handlePreflight(final RoutingContext ctx) {
        final HttpServerRequest request = ctx.request();
        // See: https://www.w3.org/TR/cors/#cross-origin-request-with-preflight-0
        // Preflight requests should not be subject to security due to the reason UAs will remove the Authorization header
        if (request.method() == HttpMethod.OPTIONS) {
            // check if there is a access control request header
            final String accessControlRequestHeader = ctx.request().getHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS);
            if (accessControlRequestHeader != null) {
                // lookup for the Authorization header
                for (final String ctrlReq : accessControlRequestHeader.split(Strings.COMMA)) {
                    if (ctrlReq.equalsIgnoreCase(HttpHeaders.AUTHORIZATION.toString())) {
                        // this request has auth in access control, so we can allow preflighs without authentication
                        ctx.next();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private AuthProvider getAuthProvider(final RoutingContext ctx) {
        try {
            final AuthProvider provider = ctx.get(AUTH_PROVIDER_CONTEXT_KEY);
            if (provider != null) {
                // we're overruling the configured one for this request
                this.authProvider = provider;
                return provider;
            }
        } catch (final RuntimeException e) {
            // bad type, ignore and return default
            e.printStackTrace();
        }
        return this.authProvider;
    }

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }

    /*
     * This method is for dynamic using of 403 authorization.
     * Sometimes when the URI has been stored as resource in zero system,
     * You can extract the metadata in @Wall classes by
     *      data.getJsonObject("metadata");
     * It means that you can find unique resource identifier by
     * 1) Http Method: GET, DELETE, POST, PUT
     * 2) Uri Original
     * Here are some calculation results that has been provided by zero container such as following situation:
     * When the registry uri is as : /api/test/:name
     * In this situation the real path should be : /api/test/lang
     * In this method the metadata -> uri will be provided by : /api/test/:name
     *                    metadata -> requestUri will be provided by : /api/test/lang
     * It's specific situation when you used path variable.
     *
     * 「Objective」
     * The metadata stored for real project when you want to do some limitation in RBAC mode.
     * Because the application system will scanned our storage to do resource authorization, the application
     * often need the metadata information to do locating and checking here.
     */
    private JsonObject build(final JsonObject data, final RoutingContext context) {
        final HttpServerRequest request = context.request();
        /*
         * Build metadata
         */
        final JsonObject metadata = new JsonObject();
        metadata.put("uri", recoveryUri(context));
        metadata.put("requestUri", request.uri());
        metadata.put("method", request.method().name());
        data.put("metadata", metadata);
        /*
         * Build Custom Headers
         */
        final MultiMap inputHeaders = request.headers();
        final JsonObject headers = new JsonObject();
        inputHeaders.forEach(entry -> {
            if (ID.Header.PARAM_MAP.containsKey(entry.getKey())) {
                headers.put(entry.getKey(), entry.getValue());
            }
        });
        data.put("headers", headers);
        this.getLogger().info("[ ZERO ] Auth Information: {0}", data.encode());
        return data;
    }

    /*
     * Recovery Uri Pattern to definition
     * /api/test/lang -> /api/test/:name
     */
    private String recoveryUri(final RoutingContext context) {
        /* Get Path Parameters */
        final Map<String, String> params = context.pathParams();
        final String uri = context.request().uri();
        /* Whether it's path */
        if (params.isEmpty()) {
            return uri;
        } else {
            return ZeroAnno.recoveryUri(uri);
        }
    }
}
