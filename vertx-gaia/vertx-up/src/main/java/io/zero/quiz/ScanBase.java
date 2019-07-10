package io.zero.quiz;

import io.vertx.zero.mirror.Pack;

import java.util.HashSet;
import java.util.Set;

public class ScanBase {

    private final transient Set<Class<?>> classes;

    public ScanBase() {
        this.classes = Pack.getClasses();
    }

    protected Set<Class<?>> getClasses() {
        return new HashSet<>(this.classes);
    }
}
