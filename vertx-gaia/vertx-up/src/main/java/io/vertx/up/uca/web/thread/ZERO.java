package io.vertx.up.uca.web.thread;

interface Info {
    String SCANNED_EVENTS = "[ ZERO ] ( {1} Event ) The endpoint {0} scanned {1} events of Event, " +
            "will be mounted to routing system.";

    String SCANNED_RECEIPTS = "[ ZERO ] ( {1} Receipt ) The queue {0} scanned {1} records of Receipt, " +
            "will be mounted to event bus.";

    String SCANNED_FIELD = "[ ZERO ] ( Field ) Class \"{0}\" scanned field = \"{1}\" " +
            "of {2} annotated with {3}. will be initialized with DI container.";

    String SCANNED_INSTANCES = "[ ZERO ] The instance classes ({0}) will be scanned.";
}
