package io.vertx.up.eon.em;

public enum JobStatus {
    READY,      // The job could be started
    RUNNING,        // Job is running
    STOPPED,    // Job is stopped
    ERROR,      // Job met error when ran last time
}
