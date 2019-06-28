package io.vertx.tp.jet.cv;

public interface JtMsg {
    String AGENT_CONFIG = "Jet agent will start `Routing System` with additional config = {0}";

    String PARAM_INGEST = "Param mode: {0}, select `JtIngest` component: name = {1}, hashCode = {2}";

    String PARAM_FINAL = "Finally normalized data: `{0}`";
}
