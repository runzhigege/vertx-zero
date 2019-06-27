package io.vertx.tp.jet.cv.em;

public enum ChannelType {
    ADAPTOR,        // Access dynamic database of each
    CONNECTOR,      // Integration for third part connector
    ACTOR,          // Worker schedules from background
    DIRECTOR,       // No access on database, script combine
}
