package javax.ws.rs;

import io.vertx.up.uca.rs.media.resolver.JsonResolver;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BodyParam {
    /**
     * Default resolver to process the regionInput
     */
    Class<?> resolver() default JsonResolver.class;
}
