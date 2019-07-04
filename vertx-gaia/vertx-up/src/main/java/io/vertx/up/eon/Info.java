package io.vertx.up.eon;

public interface Info {
    String VTC_END = "[ ZERO ] ( {3} ) The verticle {0} has been deployed " +
            "{1} instances successfully. id = {2}.";
    String VTC_FAIL = "[ ZERO ] ( {3} ) The verticle {0} has been deployed " +
            "{1} instances failed. id = {2}, cause = {3}.";

    String VTC_STOPPED = "[ ZERO ] ( {2} ) The verticle {0} has been undeployed " +
            " successfully, id = {1}.";

    String AGENT_DEFINED = "[ ZERO ] User defined agent {0} of type = {1}, " +
            "the default will be overwritten.";

    String SCANED_ENDPOINT = "[ ZERO ] ( {0} EndPoint ) The Zero system has found " +
            "{0} components of @EndPoint.";

    String SCANED_JOB = "[ ZERO ] ( {0} Job ) The Zero system has found " +
            "{0} components of @Job.";

    String SCANED_QUEUE = "[ ZERO ] ( {0} Queue ) The Zero system has found " +
            "{0} components of @Queue.";

    String SCANED_INJECTION = "[ ZERO ] ( {1} Inject ) The Zero system has found \"{0}\" object contains " +
            "{1} components of @Inject or ( javax.inject.infix.* ).";

    String APP_CLUSTERD = "[ ZERO ] Current app is running in cluster mode, " +
            "manager = {0} on node {1} with status = {2}.";

    String SOCK_ENABLED = "[ ZERO ] ( Micro -> Sock ) Zero system detected the socket server is Enabled.";

    String RPC_ENABLED = "[ ZERO ] ( Micro -> Rpc ) Zero system detected the rpc server is Enabled. ";

    // ----------- Job related information

    String JOB_IGNORE = "[ ZERO ] ( Job ) The class {0} annotated with @Job will be ignored because there is no @On method defined.";

    String JOB_EMPTY = "[ ZERO ] ( Job ) Zero system detect no jobs, the scheduler will be stopped.";

    String JOB_CONFIG_NULL = "[ ZERO ] ( Ignore ) Because there is no definition in `vertx-job.yml`, Job container is stop....";

    String JOB_MONITOR = "[ ZERO ] ( Job ) Zero system detect {0} jobs, the scheduler will begin....";

    String JOB_CONFIG = "[ ZERO ] ( Job ) Job configuration read : {0}";

    String JOB_AGHA_SELECTED = "[ ZERO ] ( Job: {1} ) Agha = {0} has been selected for job {1} of type {2}";

    String JOB_READY = "[ ZERO ] ( Job: {0} ) The status has been ready: STARTING -> READY";

    String JOB_COMPONENT_SELECTED = "[ ZERO ] ( Job ) {0} selected: {1}";

    String JOB_ADDRESS_EVENT_BUS = "[ ZERO ] ( Job ) {0} event bus enabled: {1}";

    // ------------- Job monitor for ONCE
    String PHASE_1ST_JOB = "[ ZERO ] ( Job: {0} ) 1. Input new data of JsonObject";

    String PHASE_1ST_JOB_ASYNC = "[ ZERO ] ( Job: {0} ) 1. Input from address {1}";

    String PHASE_2ND_JOB = "[ ZERO ] ( Job: {0} ) 2. Input without `JobIncome`";

    String PHASE_2ND_JOB_ASYNC = "[ ZERO ] ( Job: {0} ) 2. Input with `JobIncome` = {1}";

    String PHASE_3RD_JOB_RUN = "[ ZERO ] ( Job: {0} ) 3. --> @On Method call {1}";

    String PHASE_6TH_JOB_CALLBACK = "[ ZERO ] ( Job: {0} ) 6. --> @Off Method call {1}";

    String PHASE_4TH_JOB_ASYNC = "[ ZERO ] ( Job: {0} ) 4. Output with `JobOutcome` = {1}";

    String PHASE_4TH_JOB = "[ ZERO ] ( Job: {0} ) 4. Output without `JobOutcome`";

    String PHASE_5TH_JOB = "[ ZERO ] ( Job: {0} ) 5. Output directly, ignore next EventBus steps";

    String PHASE_5TH_JOB_ASYNC = "[ ZERO ] ( Job: {0} ) 5. Output send to address {1}";

    String PHASE_ERROR = "[ ZERO ] ( Job: {0} ) Terminal with error: {1}";
}
