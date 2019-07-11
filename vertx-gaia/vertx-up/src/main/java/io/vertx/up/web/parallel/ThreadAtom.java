package io.vertx.up.web.parallel;

import io.vertx.zero.epic.fn.Actuator;

import java.util.concurrent.CountDownLatch;

public class ThreadAtom extends Thread {

    private final transient CountDownLatch counter;
    private final transient Actuator consumer;

    ThreadAtom(final CountDownLatch counter,
               final Actuator consumer) {
        this.counter = counter;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        this.consumer.execute();
        this.counter.countDown();
    }
}
