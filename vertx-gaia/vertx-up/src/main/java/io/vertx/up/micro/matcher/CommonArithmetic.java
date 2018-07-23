package io.vertx.up.micro.matcher;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.servicediscovery.Record;
import io.vertx.up.epic.Ut;
import io.vertx.up.micro.discovery.Origin;
import io.vertx.zero.eon.Strings;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Simple load balancer arithmetic
 */
public class CommonArithmetic implements Arithmetic {
    @Override
    public Record search(final List<Record> records,
                         final RoutingContext context) {
        final HttpServerRequest request = context.request();
        // Input source
        final String uri = request.path();
        final Optional<Record> hitted =
                records.stream()
                        .filter(record -> this.isMatch(uri, record))
                        .findAny();
        // Find valid;
        return hitted.orElse(null);
    }

    /**
     * Match calculation.
     *
     * @param uri
     * @param record
     * @return
     */
    private boolean isMatch(final String uri, final Record record) {
        final JsonObject data = record.getMetadata();
        boolean match = false;
        if (data.containsKey(Origin.PATH)) {
            final String path = data.getString(Origin.PATH);
            if (!Ut.isNil(path) && path.contains(Strings.COLON)) {
                final Pattern pattern = RegexPath.createRegex(path);
                match = pattern.matcher(uri).matches();
            } else {
                match = path.equalsIgnoreCase(uri);
            }
        }
        return match;
    }
}
