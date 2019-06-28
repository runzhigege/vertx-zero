package io.vertx.tp.jet.cv;

import io.vertx.tp.jet.uca.micro.JtAiakos;
import io.vertx.tp.jet.uca.micro.JtMinos;
import io.vertx.tp.jet.uca.micro.JtRadamanteis;

public interface JtComponent {
    /* Default namespace build by JtApp */
    String NAMESPACE_PATTERN = "zero.jet.{0}";
    String EVENT_ADDRESS = "Πίδακας δρομολογητή://EVENT-JET/ZERO/UNIFORM";
    /*
     * Component Default
     * - Agent
     * - Worker
     * - Consumer
     */
    String COMPONENT_DEFAULT_AGENT = JtAiakos.class.getName();
    String COMPONENT_DEFAULT_WORKER = JtMinos.class.getName();
    String COMPONENT_DEFAULT_CONSUMER = JtRadamanteis.class.getName();
    /*
     * Parameter component extract key
     */
    String COMPONENT_PARAM_KEY = "io.vertx.tp.jet.uca.micro.JtValentane";
}
