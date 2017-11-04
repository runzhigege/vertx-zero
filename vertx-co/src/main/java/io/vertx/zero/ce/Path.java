package io.vertx.zero.ce;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.shareddata.impl.ClusterSerializable;

/**
 * Path object for different usage.
 * 1. This object could be transferred in event bus
 */
public class Path implements ClusterSerializable{

    public void writeToBuffer(final Buffer buffer) {

    }
    public int readFromBuffer(final int i, final Buffer buffer) {
        return 0;
    }
}
