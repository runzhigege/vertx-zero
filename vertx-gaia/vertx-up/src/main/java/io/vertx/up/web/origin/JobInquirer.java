package io.vertx.up.web.origin;

import io.vertx.up.annotations.Job;
import io.vertx.up.atom.worker.Mission;

import java.util.Set;
import java.util.stream.Collectors;

public class JobInquirer implements Inquirer<Set<Mission>> {

    @Override
    public Set<Mission> scan(final Set<Class<?>> clazzes) {
        final Set<Class<?>> jobs = clazzes.stream()
                .filter(item -> item.isAnnotationPresent(Job.class))
                .collect(Collectors.toSet());
        /* All classes of jobs here */
        return null;
    }
}
