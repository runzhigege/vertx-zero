package io.vertx.up.uca.cosmic;

import io.vertx.core.MultiMap;
import io.vertx.up.commune.config.Integration;
import io.vertx.up.uca.cosmic.security.WebToken;
import org.apache.http.HttpHeaders;

public abstract class AbstractWebClient {
    protected final transient Emitter emitter;
    protected final transient Integration integration;

    public AbstractWebClient(final Integration integration) {
        this.emitter = Emitter.create(integration);
        this.integration = integration;
    }

    protected Emitter emitter() {
        return this.emitter;
    }

    protected MultiMap headers() {
        final MultiMap headers = MultiMap.caseInsensitiveMultiMap();
        headers.add(HttpHeaders.AUTHORIZATION, this.token().authorization());
        return headers;
    }

    public abstract WebToken token();
}
