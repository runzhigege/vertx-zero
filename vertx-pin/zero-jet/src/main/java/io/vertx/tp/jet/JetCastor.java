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
import io.vertx.tp.jet.refine.Jt;

import java.util.Set;
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
            final ConcurrentMap<String, JsonObject> config = Jt.ask(uriSet);

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
}
