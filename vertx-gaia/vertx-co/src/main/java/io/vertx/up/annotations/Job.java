package io.vertx.up.annotations;

import io.vertx.up.eon.em.JobType;

import java.lang.annotation.*;

/**
 * Annotate the job tasks that will be scanned by zero and started by ZeroScheduler
 * Job category:
 * 1. FIXED：The tasks are made by zero and managed by timer, it execute at fixed timestamp.
 * 2. SCHEDULED: The tasks are made by zero and managed by scheduler, it execute in loop per time unit from started.
 * 3. ONCE: The task could be triggered by another job or manually and it's ran once but it's stored in JobStore.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Job {
    /**
     * Required for job to describe job type here.
     */
    JobType value();

    /**
     * Default job name will be generated by zero framework.
     */
    String name() default "";

    /**
     * Duration for job executing
     */
    long duration() default -1L;
}
