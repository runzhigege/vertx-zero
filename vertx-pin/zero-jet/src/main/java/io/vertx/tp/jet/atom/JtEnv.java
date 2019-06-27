package io.vertx.tp.jet.atom;

import java.io.Serializable;

/*
 * Environment for current jet here.
 */
public class JtEnv implements Serializable {
    /* Application Name */
    private transient String name;
    private transient String sigma;
    private transient String appId;
    private transient String appKey;
}
