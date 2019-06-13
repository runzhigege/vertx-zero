package io.vertx.up.web;

import io.vertx.core.http.HttpMethod;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.matcher.RegexPath;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

class ZeroUri {

    private static final Annal LOGGER = Annal.get(ZeroUri.class);

    private final static ConcurrentMap<HttpMethod, Set<String>>
            URIS = new ConcurrentHashMap<HttpMethod, Set<String>>() {
        {
            this.put(HttpMethod.GET, new HashSet<>());
            this.put(HttpMethod.POST, new HashSet<>());
            this.put(HttpMethod.DELETE, new HashSet<>());
            this.put(HttpMethod.PUT, new HashSet<>());
        }
    };

    static void resolve(final Event event) {
        add(event.getMethod(), event.getPath());
    }

    static void report() {
        final long size = URIS.values().stream()
                .mapToLong(Set::size).sum();
        LOGGER.info("[ ZERO ] ( Uri ) Pattern Uri Size: {0}", String.valueOf(size));
    }

    static String recovery(final String uri, final HttpMethod method) {
        final Set<String> definition = URIS.get(method);
        if (Objects.isNull(definition)) {
            return uri;
        } else {
            return definition.stream()
                    .filter(path -> isMatch(uri, path))
                    .findFirst().orElse(uri);
        }
    }

    private static boolean isMatch(final String uri, final String path) {
        final Pattern pattern = RegexPath.createRegex(path);
        return pattern.matcher(uri).matches();
    }

    private static void add(final HttpMethod method, final String uri) {
        if (Objects.isNull(method)) {
            URIS.keySet().forEach(each -> addSingle(each, uri));
        } else {
            addSingle(method, uri);
        }
    }

    private static void addSingle(final HttpMethod method, final String uri) {
        URIS.get(method).add(uri);
    }
}
