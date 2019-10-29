package io.vertx.tp.crud.actor;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.atom.IxField;
import io.vertx.tp.crud.atom.IxModule;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.tp.optic.business.ExSerial;
import io.vertx.tp.optic.Pocket;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class SerialActor extends AbstractActor {
    @Override
    public JsonObject proc(final JsonObject data, final IxModule config) {
        throw new RuntimeException("Do not support this method here.");
    }

    @Override
    public Future<JsonObject> procAsync(final JsonObject data, final IxModule config) {
        final IxField field = config.getField();
        final JsonObject numbers = field.getNumbers();
        if (Ut.isNil(numbers)) {
            /*
             * Do not generate numbers
             */
            return Ux.toFuture(data);
        } else {
            /*
             * Generate numbers here
             */
            final ExSerial serial = Pocket.lookup(ExSerial.class);
            final String sigma = data.getString(KeField.SIGMA);
            if (Objects.nonNull(serial) && Ut.notNil(sigma)) {
                final ConcurrentMap<String, Future<String>> numberMap =
                        new ConcurrentHashMap<>();
                numbers.fieldNames().stream()
                        .filter(numberField -> !data.containsKey(numberField))
                        .filter(numberField -> Objects.nonNull(numbers.getString(numberField)))
                        .forEach(numberField -> {
                            final String code = numbers.getString(numberField);
                            numberMap.put(numberField, serial.serial(sigma, code));
                        });
                /*
                 * Future combine
                 */
                return Ux.thenCombine(numberMap)
                        /*
                         * Combine number map here for generation
                         * 1) Current should be `account-item` instead of others
                         */
                        .compose(generated -> {
                            final Set<String> generatedFields = generated.keySet();
                            generatedFields.forEach(generatedField -> data.put(generatedField, generated.get(generatedField)));
                            return Ux.toFuture(data);
                        });
            } else {
                /*
                 * EcSerial is null, could not generate
                 */
                return Ux.toFuture(data);
            }
        }
    }

}
