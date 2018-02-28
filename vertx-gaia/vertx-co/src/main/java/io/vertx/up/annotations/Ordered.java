package io.vertx.up.annotations;

import java.lang.annotation.*;

/**
 * Annotated on @WebFilter for manage sequence of class.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Ordered {
    /**
     * The order will be the sequence of filters
     *
     * @return
     */
    int value() default 0;
}
