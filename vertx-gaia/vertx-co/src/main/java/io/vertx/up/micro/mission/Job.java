package io.vertx.up.micro.mission;

/*
 * Job task to start new job in background here
 * 1) This job does not support web-socket.
 * 2) Start new job to deploy new verticle of single instance = 1.
 * 3) The job should work in mode
 *    - Once
 *    - Scheduled
 *    - Triggered
 */
public interface Job {
    
}
