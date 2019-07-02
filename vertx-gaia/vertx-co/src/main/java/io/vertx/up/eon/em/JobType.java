package io.vertx.up.eon.em;

public enum JobType {
    FIXED,      // Run at timestamp
    SCHEDULED,  // Scheduled
    ONCE        // Run once
}
