package io.vertx.tp.jet;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.jet.atom.JtConfig;
import io.vertx.tp.jet.atom.JtUri;
import io.vertx.tp.jet.atom.JtWorker;
import io.vertx.tp.jet.cv.em.WorkerType;
import io.vertx.tp.jet.init.JtPin;
import io.vertx.tp.jet.monitor.JtMonitor;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * Worker entry of dynamic deployment,
 * This class will deploy the workers by JetPollux component when booting.
 */
public class JetCastor {
    private transient final Vertx vertx;
    private transient final JtMonitor monitor = JtMonitor.create(this.getClass());

    private JetCastor(final Vertx vertx) {
        this.vertx = vertx;
    }

    public static JetCastor create(final Vertx vertx) {
        return new JetCastor(vertx);
    }

    /*
     * Package scope to start workers
     */
    void startWorkers(final Set<JtUri> uriSet) {
        /*
         * Non Js worker class here
         */
        {
            /*
             * Preparing for Java workers
             */
            uriSet.stream().map(JtUri::worker)
                    .filter(worker -> WorkerType.JS != worker.getWorkerType())
                    .map(JtWorker::getWorkerClass)
                    .forEach(Pool.WORKER_SET::add);
            /*
             * Configuration preparing
             */
            final ConcurrentMap<String, JsonObject> config = this.configuration(uriSet);

            /*
             * Deployment of workers
             */
            final JtConfig configData = JtPin.getConfig();
            Pool.WORKER_SET.forEach(workerCls -> {
                /*
                 * Generate DeploymentOptions from JtConfig
                 */
                final String name = workerCls.getName();
                final DeploymentOptions options = configData.getWorkerOptions();
                final JsonObject deliveryConfig = config.get(name);
                options.setConfig(deliveryConfig);
                /*
                 * Logging information of current worker here
                 */
                this.monitor.workerDeploying(options.getInstances(), name);
                this.vertx.deployVerticle(name, options,
                        /*
                         * Worker deployed here
                         */
                        handler -> this.monitor.workerDeployed(handler, name));
            });
        }
        /*
         * Js worker class here
         */
        {

        }
    }

    /*
     * Data Structure
     * {
     *      "workerClass":{
     *          "apiKey":{
     *              {
     *                  "key": "API Primary Key",
     *                  "order": "Vert.x order",
     *                  "api": {
     *                  },
     *                  "service":{
     *                  },
     *                  "config":{
     *                  },
     *                  "appId": "Application Key"
     *              }
     *          }
     *      }
     * }
     */
    private ConcurrentMap<String, JsonObject> configuration(final Set<JtUri> uriSet) {
        final ConcurrentMap<String, JsonObject> configMap = new ConcurrentHashMap<>();
        /*
         * Build each worker config as structure here
         */
        uriSet.forEach(uri -> {
            /*
             * Worker here
             */
            final JtWorker worker = uri.worker();
            /*
             * Consider worker name as `key`
             */
            final String key = worker.getWorkerClass().getName();
            /*
             * JsonObject configuration of JsonObject
             */
            final JsonObject config = configMap.getOrDefault(key, new JsonObject());
            /*
             * Api Key = config
             */
            config.put(uri.key(), uri.toJson());
            configMap.put(key, config);
        });
        return configMap;
    }
}
