package io.vertx.tp.optic;

import io.vertx.core.Future;

import java.util.List;

/*
 * EcSerial for serial generation in current environment
 * 1) it will impact crud module
 * 2) Also it could be used in different part
 */
public interface EcSerial {
    /*
     * Generate single serial
     */
    Future<String> serial(String sigma, String code);

    /*
     * Generate multi serials
     */
    Future<List<String>> serial(String sigma, String code, Integer counter);
}
