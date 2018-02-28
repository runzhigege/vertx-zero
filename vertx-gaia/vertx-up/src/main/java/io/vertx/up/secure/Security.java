package io.vertx.up.secure;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;

/**
 * Interface defined for component
 */
public interface Security {
    /**
     * 1. When you login into system successfully, you can store token in to:
     * 1) Redis
     * 2) Database
     * 3) Etcd
     * As you want.
     *
     * @param data
     */
    JsonObject store(JsonObject data);

    /**
     * 2. 401 Access, verify the token that you provided.
     * 1) Correct ?
     * 2) Expired ?
     * 3) Signature valid ?
     *
     * @param data
     * @return
     */
    Future<Boolean> verify(JsonObject data);

    /**
     * 3. Get auth provider for other usage
     *
     * @param <T>
     * @return
     */
    <T extends AuthProvider> T get();
}
