package io.vertx.up.job.refine;

import io.vertx.up.atom.config.ComponentOpts;

import java.io.Serializable;
import java.util.Optional;

/*
 * Job configuration in `vertx-job.yml`, the job node
 * job:
 * - store:
 *   - component:
 *   - config:
 * - interval:
 *   - component:
 *   - config:
 * - client:
 *   - config:
 */
public class JobConfig implements Serializable {

    private transient ComponentOpts store;
    private transient ComponentOpts interval;
    private transient ComponentOpts client;

    public ComponentOpts getStore() {
        return Optional.ofNullable(this.store).orElse(new ComponentOpts());
    }

    public void setStore(final ComponentOpts store) {
        this.store = store;
    }

    public ComponentOpts getInterval() {
        return Optional.ofNullable(this.interval).orElse(new ComponentOpts());
    }

    public void setInterval(final ComponentOpts interval) {
        this.interval = interval;
    }

    public ComponentOpts getClient() {
        return Optional.ofNullable(this.client).orElse(new ComponentOpts());
    }

    public void setClient(final ComponentOpts client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "JobConfig{" +
                "store=" + this.store +
                ", interval=" + this.interval +
                ", client=" + this.client +
                '}';
    }
}
