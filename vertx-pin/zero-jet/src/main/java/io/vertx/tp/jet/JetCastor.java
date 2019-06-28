package io.vertx.tp.jet;

import io.vertx.core.Vertx;
import io.vertx.tp.jet.atom.JtUri;

import java.util.Set;

/*
 * Worker entry of dynamic deployment,
 * This class will deploy the workers by JetPollux component when booting.
 */
public class JetCastor {
    private transient final Vertx vertx;

    private JetCastor(final Vertx vertx) {
        this.vertx = vertx;
    }

    public static JetCastor create(final Vertx vertx) {
        return new JetCastor(vertx);
    }

    /*
     * Package scope to start workers
     */
    void startWorkers(final Set<JtUri> uriSet) {
        {

        }
    }
}
