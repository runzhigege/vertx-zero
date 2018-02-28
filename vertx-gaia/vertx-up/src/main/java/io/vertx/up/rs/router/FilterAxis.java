package io.vertx.up.rs.router;

import io.vertx.ext.web.Router;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Axis;
import io.vertx.up.web.ZeroAnno;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;

public class FilterAxis implements Axis<Router> {
    private static final Annal LOGGER = Annal.get(FilterAxis.class);

    private static final ConcurrentMap<String, Set<Event>> FILTERS =
            ZeroAnno.getFilters();

    @Override
    public void mount(final Router router) {
        for (final String path : FILTERS.keySet()) {
            System.out.println(path);
            System.out.println(FILTERS.get(path));
        }
    }
}
