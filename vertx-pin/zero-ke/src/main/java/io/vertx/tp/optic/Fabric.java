package io.vertx.tp.optic;

import io.vertx.core.Future;

public interface Fabric<T> {

    Future<T> combine(T input);
}
