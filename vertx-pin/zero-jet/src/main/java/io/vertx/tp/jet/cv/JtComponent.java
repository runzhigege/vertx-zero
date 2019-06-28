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
     * - Worker
     * - Consumer
     */
    Class<?> COMPONENT_DEFAULT_WORKER = JtMinos.class;
    Class<?> COMPONENT_DEFAULT_CONSUMER = JtAiakos.class;
    /*
     * Parameter component extract key
     */
    Class<?> COMPONENT_INGEST_KEY = JtRadamanteis.class;
}
