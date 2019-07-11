package io.vertx.up;

import io.vertx.core.Vertx;
import io.vertx.tp.error.RpcPreparingException;
import io.vertx.tp.etcd.center.EtcdData;
import io.vertx.up.annotations.Up;
import io.vertx.up.fn.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.runtime.Anno;
import io.vertx.up.runtime.Runner;
import io.vertx.up.util.Ut;
import io.vertx.up.web.ZeroLauncher;
import io.vertx.up.web.anima.DetectScatter;
import io.vertx.up.web.anima.InfixScatter;
import io.vertx.up.web.anima.PointScatter;
import io.vertx.up.web.anima.Scatter;
import io.vertx.zero.exception.UpClassArgsException;
import io.vertx.zero.exception.UpClassInvalidException;

import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Vertx Application begin launcher for api gateway.
 * It's only used in Micro Service mode.
 */
public class MicroApplication {

    private static final Annal LOGGER = Annal.get(MicroApplication.class);

    private transient final Class<?> clazz;

    private transient ConcurrentMap<String, Annotation> annotationMap = new ConcurrentHashMap<>();

    private MicroApplication(final Class<?> clazz) {
        // Must not null
        Fn.outUp(
                null == clazz,
                LOGGER,
                UpClassArgsException.class, getClass());
        this.clazz = clazz;
        annotationMap = Anno.get(clazz);
        // Must be invalid
        Fn.outUp(
                !annotationMap.containsKey(Up.class.getName()),
                LOGGER,
                UpClassInvalidException.class, getClass(), clazz.getName());
    }

    public static void run(final Class<?> clazz, final Object... args) {
        Fn.shuntRun(() -> {
            // Run vertx application.
            new MicroApplication(clazz).run(args);
        }, LOGGER);
    }

    private void run(final Object... args) {
        // Check etcd server status, IPC Only
        Fn.outUp(!EtcdData.enabled(),
                LOGGER, RpcPreparingException.class, getClass());

        final Launcher<Vertx> launcher = Ut.singleton(ZeroLauncher.class);

        launcher.start(vertx -> {
            /* 1.Find Agent for deploy **/
            Runner.run(() -> {
                final Scatter<Vertx> scatter = Ut.singleton(PointScatter.class);
                scatter.connect(vertx);
            }, "gateway-runner");
            /* 2.Find Worker for deploy **/
            Runner.run(() -> {
                final Scatter<Vertx> scatter = Ut.singleton(DetectScatter.class);
                scatter.connect(vertx);
            }, "detect-runner");
            /* 3.Initialize Infix **/
            Runner.run(() -> {
                // Infix For Api Gateway
                final Scatter<Vertx> scatter = Ut.singleton(InfixScatter.class);
                scatter.connect(vertx);
            }, "infix-afflux-runner");
        });
    }
}
