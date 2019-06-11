package io.vertx.tp.ambient.atom;

import java.io.Serializable;

/*
 * Application configuration data
 *
 */
public class AtConfig implements Serializable {
    /*
     * Whether enable XSource to stored multi data source in current XApp.
     */
    private Boolean supportSource = Boolean.FALSE;

    public Boolean getSupportSource() {
        return this.supportSource;
    }

    public void setSupportSource(final Boolean supportSource) {
        this.supportSource = supportSource;
    }

    @Override
    public String toString() {
        return "AtConfig{" +
                "supportSource=" + this.supportSource +
                '}';
    }
}
