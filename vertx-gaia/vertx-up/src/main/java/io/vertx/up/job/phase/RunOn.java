package io.vertx.up.job.phase;

import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.Session;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.worker.Mission;
import io.vertx.up.commune.Commercial;
import io.vertx.up.eon.ID;
import io.vertx.up.eon.Info;
import io.vertx.up.exception._417JobMethodException;
import io.vertx.up.log.Annal;
import io.vertx.zero.epic.Ut;

import javax.ws.rs.BodyParam;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class RunOn {
    private static final Annal LOGGER = Annal.get(RunOn.class);
    private transient final Vertx vertx;

    RunOn(final Vertx vertx) {
        this.vertx = vertx;
    }

    Future<Envelop> invoke(final Envelop envelop, final Mission mission) {
        final Method method = mission.getOn();
        Element.onceLog(mission,
                () -> LOGGER.info(Info.PHASE_3RD_JOB_RUN, mission.getName(), method.getName()));
        return execute(envelop, method, mission);
    }

    Future<Envelop> callback(final Envelop envelop, final Mission mission) {
        final Method method = mission.getOff();
        Element.onceLog(mission,
                () -> LOGGER.info(Info.PHASE_6TH_JOB_CALLBACK, mission.getName(), method.getName()));
        return execute(envelop, method, mission);
    }

    private Future<Envelop> execute(final Envelop envelop, final Method method, final Mission mission) {
        if (envelop.valid()) {
            final Object proxy = mission.getProxy();
            try {
                final Object[] arguments = buildArgs(envelop, method, mission);
                return Ut.invokeAsync(proxy, method, arguments)
                        /* Normalizing data */
                        .compose(this::normalize);
            } catch (final Throwable ex) {
                ex.printStackTrace();
                return Future.failedFuture(ex);
            }
        } else {
            Element.onceLog(mission,
                    () -> LOGGER.info(Info.PHASE_ERROR, mission.getName(),
                            envelop.error().getClass().getName()));
            return envelop.toFuture();
        }
    }

    private <T> Future<Envelop> normalize(final T returnValue) {
        if (Objects.isNull(returnValue)) {
            return Envelop.okJson().toFuture();
        } else {
            if (Envelop.class == returnValue.getClass()) {
                return Future.succeededFuture((Envelop) returnValue);
            } else {
                return Envelop.success(returnValue).toFuture();
            }
        }
    }

    private Object[] buildArgs(final Envelop envelop, final Method method, final Mission mission) {
        /*
         * Available arguments:
         * -- Envelop
         * -- Mission
         * -- JsonObject -> Mission ( config )
         * */
        final Class<?>[] parameters = method.getParameterTypes();
        final List<Object> argsList = new ArrayList<>();
        if (0 < parameters.length) {
            for (final Class<?> parameterType : parameters) {
                argsList.add(buildArgs(parameterType, envelop, mission));
            }
        } else {
            throw new _417JobMethodException(getClass(), mission.getName());
        }
        return argsList.toArray();
    }

    private Object buildArgs(final Class<?> clazz, final Envelop envelop, final Mission mission) {
        if (Envelop.class == clazz) {
            /*
             * Envelop
             */
            return envelop;
        } else if (Ut.isImplement(clazz, Session.class)) {
            /*
             * 「ONCE Only」Session
             */
            return envelop.getSession();
        } else if (Ut.isImplement(clazz, User.class)) {
            /*
             * 「ONCE Only」User
             */
            return envelop.user();
        } else if (MultiMap.class == clazz) {
            /*
             * 「ONCE Only」Headers
             */
            return envelop.headers();
        } else if (Commercial.class == clazz) {
            /*
             * Commercial specification
             */
            final JsonObject metadata = mission.getMetadata();
            final String className = metadata.getString(ID.CLASS);
            if (Ut.notNil(className)) {
                final Commercial commercial = Ut.instance(className);
                commercial.fromJson(metadata);
                return commercial;
            } else {
                return null;
            }
        } else if (JsonObject.class == clazz) {
            if (clazz.isAnnotationPresent(BodyParam.class)) {
                /*
                 * @BodyParam, it's for data passing
                 */
                return envelop.data();
            } else {
                /*
                 * Non @BodyParam, it's for configuration of current job here.
                 * Return to additional data of JsonObject
                 * This method will be used in future.
                 */
                return mission.getAdditional().copy();
            }
        } else if (Mission.class == clazz) {
            /*
             * Actor/Director must
             */
            return mission;
        } else {

            throw new _417JobMethodException(getClass(), mission.getName());
        }
    }
}
