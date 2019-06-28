package io.vertx.tp.jet.cv;

public interface JtMsg {
    String AGENT_CONFIG = "Jet agent will start `Routing System` with additional config = {0}";

    String PARAM_INGEST = "Param mode: {0}, select `JtIngest` component: name = {1}, hashCode = {2}";
    String PARAM_FINAL = "Finally normalized data: `{0}`";

    String WEB_ENGINE = "Web request: `{0} {1}`, params: {2}";
    String WEB_SEND = "Send data `{0}` to address = `{1}`";

    String WORKER_DEPLOY = "Workers will be deployed in background async ......";
    String WORKER_FAILURE = "Ambient Environment booting error, initialized failure";
    String WORKER_DEPLOYING = "Worker instance = {0}, class = {1}";
    String WORKER_DEPLOYED = "Worker {0} = {1} has been deployed successfully";
}
