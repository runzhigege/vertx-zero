package io.vertx.tp.plugin.job;

interface Info {

    String IS_RUNNING = "[ ZERO ] ( Job ) The job {0} has already been running !!!";

    String IS_STARTING = "[ ZERO ] ( Job ) The job {0} is booting, please preparing for READY";

    String IS_ERROR = "[ ZERO ] ( Job ) The job {0} met error last time, please contact administrator and try to resume.";

    String IS_STOPPED = "[ ZERO ] ( Job ) The timeId {0} does not exist in RUNNING pool of jobs.";

    String NOT_RUNNING = "[ ZERO ] ( Job ) The job {0} is not running, the status is = {1}";
}
