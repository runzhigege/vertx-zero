package cn.vertxup;

import io.vertx.up.VertxApplication;
import io.vertx.up.annotations.Up;

@Up
public class AresAgent {

    public static void main(final String[] args) {
        VertxApplication.run(AresAgent.class);
    }
}
