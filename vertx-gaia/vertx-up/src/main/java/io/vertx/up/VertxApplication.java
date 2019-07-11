package io.vertx.up;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.tp.etcd.center.EtcdData;
import io.vertx.up.annotations.Up;
import io.vertx.up.boot.DansApplication;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.config.DynamicVisitor;
import io.vertx.up.micro.config.ServerVisitor;
import io.vertx.up.web.ZeroLauncher;
import io.vertx.up.web.anima.*;
import io.vertx.zero.exception.EtcdNetworkException;
import io.vertx.zero.exception.MicroModeUpException;
import io.vertx.zero.exception.UpClassArgsException;
import io.vertx.zero.exception.UpClassInvalidException;
import io.zero.epic.Ut;
import io.zero.epic.fn.Fn;
import io.zero.runtime.Anno;
import io.zero.runtime.Runner;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * Vertx Application entry
 * 1) VertxApplication: start up zero framework in `application` mode. ( Service | Application )
 * 2) DansApplication: start up zero framework in `micro` mode. ( Service & Gateway )
 * 3) Five scanners for critical components starting up
 */
public class VertxApplication {

    private static final Annal LOGGER = Annal.get(VertxApplication.class);

    private transient final Class<?> clazz;

    private final transient ConcurrentMap<String, Annotation> annotationMap;

    private VertxApplication(final Class<?> clazz) {
        /*
         * Although the input `clazz` is not important, but zero container require the input
         * clazz mustn't be null, for future usage such as plugin extension for it.
         */
        Fn.out(null == clazz, UpClassArgsException.class, getClass());

        /*
         * Stored clazz information
         * 1. clazz stored
         * 2. annotation extraction from Annotation[] -> Annotation Map
         */
        this.clazz = clazz;
        annotationMap = Anno.get(clazz);

        /*
         * Zero specification definition for @Up here.
         * The input class must annotated with @Up instead of other description
         */

        Fn.out(!annotationMap.containsKey(Up.class.getName()), UpClassInvalidException.class, getClass(),
                null == this.clazz ? null : this.clazz.getName());
    }

    public static void run(final Class<?> clazz, final Object... args) {
        Fn.shuntRun(() -> {
            /*
             * Class definition predicate
             */
            ensureEtcd(clazz);
            /*
             * To avoid getPackages issue here
             * Move to InitScatter here
             */
            // ZeroHeart.init();

            /*
             * Before launcher, start package scanning for preparing metadata
             * This step is critical because it's environment core preparing steps.
             * 1) Before vert.x started, the system must be scanned all to capture some metadata classes instead.
             * 2) PackScan will scan all classes to capture Annotation information
             * 3) For zero extension module, although it's not in ClassLoader, we also need to scan dependency library
             *    to capture zero extension module annotation
             *
             * Because static {} initializing will be triggered when `ZeroAnno` is called first time, to avoid
             * some preparing failure, here we replaced `static {}` with `prepare()` calling before any instance
             * of VertxApplication/DansApplication.
             */
            // ZeroAnno.prepare();

            /*
             * Then the container could start
             */
            if (isGateway()) {
                /*
                 * Api Gateway:
                 * `Micro` mode only
                 * Current zero node will run as api gateway
                 */
                DansApplication.run(clazz);
            } else {
                /*
                 * Standard application
                 * 1. In `Micro` mode, it will run as service node.
                 * 2. In `Standalone` mode, it will run as application.
                 */
                new VertxApplication(clazz).run(args);
            }
        }, LOGGER);
    }

    private static boolean isGateway() {
        /*
         * This method is only ok when `micro` mode
         * Secondary Scanned for Api Gateway
         */
        final Set<Integer> apiScanned = new HashSet<>();
        Fn.outUp(() -> {
            final ServerVisitor<HttpServerOptions> visitor =
                    Ut.singleton(DynamicVisitor.class);
            apiScanned.addAll(visitor.visit(ServerType.API.toString()).keySet());
        }, LOGGER);
        return !apiScanned.isEmpty();
    }

    private static void ensureEtcd(final Class<?> clazz) {
        /*
         * Whether startup etcd environment
         * 1) Etcd environment depend on `vertx-micro.yml`
         * 2) If it's start up, zero container must check etcd configuration and try to connect
         * 3) Zero will initialize etcd nodes information for current micro environment.
         */
        if (EtcdData.enabled()) {
            try {
                EtcdData.create(clazz);
            } catch (final EtcdNetworkException ex) {
                Fn.outUp(true, LOGGER,
                        MicroModeUpException.class, clazz, ex.getMessage());
            }
        }
    }

    private void run(final Object... args) {

        final Launcher<Vertx> launcher = Ut.singleton(ZeroLauncher.class);
        launcher.start(vertx -> {
            /* 1.Find Agent for deploy **/
            Runner.run(() -> {
                final Scatter<Vertx> scatter = Ut.singleton(AgentScatter.class);
                scatter.connect(vertx);
            }, "agent-runner");

            /* 2.Find Worker for deploy **/
            Runner.run(() -> {
                final Scatter<Vertx> scatter = Ut.singleton(WorkerScatter.class);
                scatter.connect(vertx);
            }, "worker-runner");

            /* 3.Initialize Infix **/
            Runner.run(() -> {
                // Infix
                Scatter<Vertx> scatter = Ut.singleton(InfixScatter.class);
                scatter.connect(vertx);
                // Injection
                scatter = Ut.singleton(AffluxScatter.class);
                scatter.connect(vertx);
            }, "infix-afflux-runner");

            /* 4.Rule started **/
            Runner.run(() -> {
                final Scatter<Vertx> scatter = Ut.singleton(CodexScatter.class);
                scatter.connect(vertx);
            }, "codex-engine-runner");

            /* 5.Plugin init */

            Runner.run(() -> {
                final Scatter<Vertx> scatter = Ut.singleton(InitScatter.class);
                scatter.connect(vertx);
            }, "initializer-runner");
        });
    }
}
