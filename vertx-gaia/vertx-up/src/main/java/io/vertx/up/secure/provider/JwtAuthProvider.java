package io.vertx.up.secure.provider;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystemException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.KeyStoreOptions;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.SecretOptions;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.auth.jwt.impl.JWTAuthProviderImpl;
import io.vertx.ext.auth.jwt.impl.JWTUser;
import io.vertx.ext.jwt.JWK;
import io.vertx.ext.jwt.JWT;
import io.vertx.ext.jwt.JWTOptions;
import io.vertx.up.exception.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class JwtAuthProvider implements JwtAuth {
    private static final JsonArray EMPTY_ARRAY = new JsonArray();
    private final JWT jwt;
    private final String permissionsClaimKey;
    private final JWTOptions jwtOptions;
    private final Function<JsonObject, Future<Boolean>> executor;

    public JwtAuthProvider(final Vertx vertx, final JWTAuthOptions config, final Function<JsonObject, Future<Boolean>> executor) {
        this.executor = executor;
        this.permissionsClaimKey = config.getPermissionsClaimKey();
        this.jwtOptions = config.getJWTOptions();
        final KeyStoreOptions keyStore = config.getKeyStore();

        try {
            if (keyStore != null) {
                final KeyStore ks = KeyStore.getInstance(keyStore.getType());
                final Class var5 = JWTAuthProviderImpl.class;
                synchronized (JWTAuthProviderImpl.class) {
                    final Buffer keystore = vertx.fileSystem().readFileBlocking(keyStore.getPath());
                    final InputStream in = new ByteArrayInputStream(keystore.getBytes());
                    Throwable var8 = null;

                    try {
                        ks.load(in, keyStore.getPassword().toCharArray());
                    } catch (final Throwable var20) {
                        var8 = var20;
                        throw var20;
                    } finally {
                        if (in != null) {
                            if (var8 != null) {
                                try {
                                    in.close();
                                } catch (final Throwable var19) {
                                    var8.addSuppressed(var19);
                                }
                            } else {
                                in.close();
                            }
                        }

                    }
                }

                this.jwt = new JWT(ks, keyStore.getPassword().toCharArray());
            } else {
                this.jwt = new JWT();
                final List<PubSecKeyOptions> keys = config.getPubSecKeys();
                if (keys != null) {
                    final Iterator var25 = config.getPubSecKeys().iterator();

                    while (var25.hasNext()) {
                        final PubSecKeyOptions pubSecKey = (PubSecKeyOptions) var25.next();
                        if (pubSecKey.isSymmetric()) {
                            this.jwt.addJWK(new JWK(pubSecKey.getAlgorithm(), pubSecKey.getPublicKey()));
                        } else {
                            this.jwt.addJWK(new JWK(pubSecKey.getAlgorithm(), pubSecKey.isCertificate(), pubSecKey.getPublicKey(), pubSecKey.getSecretKey()));
                        }
                    }
                }

                final List<SecretOptions> secrets = config.getSecrets();
                if (secrets != null) {
                    final Iterator var28 = secrets.iterator();

                    while (var28.hasNext()) {
                        final SecretOptions secret = (SecretOptions) var28.next();
                        this.jwt.addSecret(secret.getType(), secret.getSecret());
                    }
                }
            }

        } catch (IOException | FileSystemException | CertificateException | NoSuchAlgorithmException | KeyStoreException var23) {
            throw new _500JwtRuntimeException(this.getClass(), var23);
        }
    }

    @Override
    public void authenticate(final JsonObject authInfo, final Handler<AsyncResult<User>> resultHandler) {
        final Future future;
        if (null == this.executor) {
            future = this.authorize(authInfo);
        } else {
            future = this.executor.apply(authInfo).compose(result -> {
                if (result) {
                    return this.authorize(authInfo);
                } else {
                    return Future.failedFuture(new _401JwtExecutorException(this.getClass(), authInfo));
                }
            });
        }
        resultHandler.handle(future);
    }

    private Future<User> authorize(final JsonObject authInfo) {
        try {
            final JsonObject payload = this.jwt.decode(authInfo.getString("jwt"));
            if (this.jwt.isExpired(payload, this.jwtOptions)) {
                return Future.failedFuture(new _401JwtExpiredException(this.getClass(), payload));
            }
            if (this.jwtOptions.getAudience() != null) {
                final JsonArray target;
                if (payload.getValue("aud") instanceof String) {
                    target = (new JsonArray()).add(payload.getValue("aud", ""));
                } else {
                    target = payload.getJsonArray("aud", EMPTY_ARRAY);
                }

                if (Collections.disjoint(this.jwtOptions.getAudience(), target.getList())) {
                    return Future.failedFuture(new _401JwtAudientException(this.getClass(), Json.encode(this.jwtOptions.getAudience())));
                }
            }

            if (this.jwtOptions.getIssuer() != null && !this.jwtOptions.getIssuer().equals(payload.getString("iss"))) {
                return Future.failedFuture(new _401JwtIssuerException(this.getClass(), payload.getString("iss")));
            }

            return Future.succeededFuture(new JWTUser(payload, this.permissionsClaimKey));
        } catch (final RuntimeException var5) {
            return Future.failedFuture(new _500JwtRuntimeException(this.getClass(), var5));
        }
    }

    @Override
    public String generateToken(final JsonObject claims, final JWTOptions options) {
        final JsonObject _claims = claims.copy();
        if (options.getPermissions() != null && !_claims.containsKey(this.permissionsClaimKey)) {
            _claims.put(this.permissionsClaimKey, new JsonArray(options.getPermissions()));
        }
        return this.jwt.sign(_claims, options);
    }
}
