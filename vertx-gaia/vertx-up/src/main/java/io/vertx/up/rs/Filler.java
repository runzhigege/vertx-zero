package io.vertx.up.rs;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.rs.argument.*;
import io.vertx.up.epic.mirror.Instance;

import javax.ws.rs.*;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Fill the arguments into reference list
 * as arguments
 */
public interface Filler {
    ConcurrentMap<Class<? extends Annotation>, Filler> PARAMS =
            new ConcurrentHashMap<Class<? extends Annotation>, Filler>() {
                {
                    // JSR311 Provided
                    this.put(QueryParam.class, Instance.singleton(QueryFiller.class));
                    this.put(FormParam.class, Instance.singleton(FormFiller.class));
                    this.put(MatrixParam.class, Instance.singleton(QueryFiller.class));
                    this.put(PathParam.class, Instance.singleton(PathFiller.class));
                    this.put(HeaderParam.class, Instance.singleton(HeaderFiller.class));
                    this.put(CookieParam.class, Instance.singleton(CookieFiller.class));
                    // Extension
                    this.put(BodyParam.class, Instance.singleton(EmptyFiller.class));
                    this.put(StreamParam.class, Instance.singleton(EmptyFiller.class));
                    this.put(SessionParam.class, Instance.singleton(SessionFiller.class));
                    this.put(ContextParam.class, Instance.singleton(ContextFiller.class));
                }
            };
    Set<Class<? extends Annotation>> NO_VALUE =
            new HashSet<Class<? extends Annotation>>() {
                {
                    this.add(BodyParam.class);
                    this.add(StreamParam.class);
                }
            };

    /**
     * @param name
     * @param paramType
     * @param datum
     * @return
     */
    Object apply(String name,
                 Class<?> paramType,
                 RoutingContext datum);
}
