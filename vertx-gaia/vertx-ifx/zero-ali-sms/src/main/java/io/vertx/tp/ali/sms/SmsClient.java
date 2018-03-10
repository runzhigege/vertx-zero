package io.vertx.tp.ali.sms;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.json.JsonObject;

/**
 * AliSmsClient for platform of https://dysms.console.aliyun.com/dysms.htm
 * Message open sdk
 */
public interface SmsClient {

    @Fluent
    SmsClient init(JsonObject params);
}
