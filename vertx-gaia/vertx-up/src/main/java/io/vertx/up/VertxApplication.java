package io.vertx.up;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.tp.etcd.center.EtcdData;
import io.vertx.up.annotations.Up;
import io.vertx.up.boot.DansApplication;
import io.vertx.up.concurrent.Runner;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.epic.fn.Fn;
import io.vertx.up.epic.mirror.Anno;
import io.vertx.up.epic.mirror.Instance;
import io.vertx.up.log.Annal;
import io.vertx.up.web.ZeroLauncher;
import io.vertx.up.web.anima.*;
import io.vertx.zero.config.ServerVisitor;
import io.vertx.zero.exception.EtcdNetworkException;
import io.vertx.zero.exception.MicroModeUpException;
import io.vertx.zero.exception.UpClassArgsException;
import io.vertx.zero.exception.UpClassInvalidException;
import io.vertx.zero.micro.config.DynamicVisitor;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Vertx Application start information
 */
public class VertxApplication {

    private static final Annal LOGGER = Annal.get(VertxApplication.class);

    private transient final Class<?> clazz;

    private transient ConcurrentMap<String, Annotation> annotationMap = new ConcurrentHashMap<>();

    private VertxApplication(final Class<?> clazz) {
        // Must not null
        Fn.outUp(
                null == clazz,
                LOGGER,
                UpClassArgsException.class, this.getClass());
        this.clazz = clazz;
        this.annotationMap = Anno.get(clazz);
        // Must be invalid
        Fn.outUp(
                !this.annotationMap.containsKey(Up.class.getName()),
                LOGGER,
                UpClassInvalidException.class, this.getClass(), clazz.getName());
    }

    public static void run(final Class<?> clazz, final Object... args) {
        Fn.shuntRun(() -> {
            // Precheck mode
            ensureEtcd(clazz);
            // Run vertx application.
            if (isGateway()) {
                // Api Gateway
                DansApplication.run(clazz);
            } else {
                // Service Node
                new VertxApplication(clazz).run(args);
            }
        }, LOGGER);
    }

    private static boolean isGateway() {
        // Secondary Scanned for Api Gateway
        final Set<Integer> apiScanned = new HashSet<>();
        Fn.outUp(() -> {
            final ServerVisitor<HttpServerOptions> visitor =
                    Instance.singleton(DynamicVisitor.class);
            apiScanned.addAll(visitor.visit(ServerType.API.toString()).keySet());
        }, LOGGER);
        return !apiScanned.isEmpty();
    }

    private static void ensureEtcd(final Class<?> clazz) {
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

        final Launcher<Vertx> launcher = Instance.singleton(ZeroLauncher.class);
        launcher.start(vertx -> {
            /** 1.Find Agent for deploy **/
            Runner.run(() -> {
                final Scatter<Vertx> scatter = Instance.singleton(AgentScatter.class);
                scatter.connect(vertx);
            }, "agent-runner");

            /** 2.Find Worker for deploy **/
            Runner.run(() -> {
                final Scatter<Vertx> scatter = Instance.singleton(WorkerScatter.class);
                scatter.connect(vertx);
            }, "worker-runner");

            /** 3.Initialize Infix **/
            Runner.run(() -> {
                // Infix
                Scatter<Vertx> scatter = Instance.singleton(InfixScatter.class);
                scatter.connect(vertx);
                // Injection
                scatter = Instance.singleton(AffluxScatter.class);
                scatter.connect(vertx);
            }, "infix-afflux-runner");

            /** 4.Rule started **/
            Runner.run(() -> {
                final Scatter<Vertx> scatter = Instance.singleton(CodexScatter.class);
                scatter.connect(vertx);
            }, "codex-engine-runner");
        });
    }
}
