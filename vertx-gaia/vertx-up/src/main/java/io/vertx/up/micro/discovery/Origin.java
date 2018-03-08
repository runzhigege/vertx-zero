package io.vertx.up.micro.discovery;

import io.vertx.servicediscovery.Record;

import java.util.concurrent.ConcurrentMap;

/**
 * Service discovery metadata discovery
 * 1. Backend : The worker will publish service out.
 * 2. Frontend : The api gateway will do discovery
 */
public interface Origin {
    /**
     * Get backend
     *
     * @return
     */
    ConcurrentMap<String, Record> getRegistryData();

    /**
     * Erase record
     *
     * @param record
     * @return
     */
    boolean erasing(Record record);

    String HOST = "host";
    String NAME = "name";
    String PORT = "port";
    String META = "metadata";
    String ID = "id";
    String PATH = "path";
}
