package io.vertx.up.job.center;

import io.vertx.core.Vertx;
import io.vertx.up.annotations.Contract;

public abstract class AbstractAgha implements Agha {

    @Contract
    private transient Vertx vertx;

    protected Vertx vertx() {
        return this.vertx;
    }
}
