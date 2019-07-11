package io.vertx.up.uca.stable;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.quiz.ZeroBase;
import io.vertx.up.exception.ZeroException;
import io.vertx.up.exception.demon.DataTypeWrongException;
import io.vertx.up.exception.demon.RequiredFieldException;
import io.vertx.up.util.Ut;
import org.junit.Test;

public class JsonInsurerTc extends ZeroBase {

    @Test(expected = RequiredFieldException.class)
    public void testRequiredObj() throws ZeroException {
        final JsonObject rule = Ut.ioYaml(ioFile("rule-required.yml"));
        final JsonObject data = Ut.ioJObject(ioFile("rule-required-data.json"));
        ensure(RequiredInsurer.class, data, rule);
    }

    @Test(expected = RequiredFieldException.class)
    public void testRequiredArray() throws ZeroException {
        final JsonObject rule = Ut.ioYaml(ioFile("rule-required.yml"));
        final JsonArray data = Ut.ioJArray(ioFile("rule-required-array.json"));
        ensure(RequiredInsurer.class, data, rule);
    }

    @Test(expected = DataTypeWrongException.class)
    public void testTypedObj() throws ZeroException {
        final JsonObject rule = Ut.ioYaml(ioFile("rule-typed.yml"));
        final JsonObject data = Ut.ioJObject(ioFile("rule-typed-jobject.json"));
        ensure(TypedInsurer.class, data, rule);
    }

    @Test(expected = DataTypeWrongException.class)
    public void testTypedNumber() throws ZeroException {
        final JsonObject rule = Ut.ioYaml(ioFile("rule-typed.yml"));
        final JsonObject data = Ut.ioJObject(ioFile("rule-typed-number.json"));
        ensure(TypedInsurer.class, data, rule);
    }

    @Test(expected = DataTypeWrongException.class)
    public void testTypedDecimal() throws ZeroException {
        final JsonObject rule = Ut.ioYaml(ioFile("rule-typed.yml"));
        final JsonObject data = Ut.ioJObject(ioFile("rule-typed-decimal.json"));
        ensure(TypedInsurer.class, data, rule);
    }

    @Test
    public void testTypedCorrect() throws ZeroException {
        final JsonObject rule = Ut.ioYaml(ioFile("rule-typed.yml"));
        final JsonObject data = Ut.ioJObject(ioFile("rule-typed-correct.json"));
        ensure(TypedInsurer.class, data, rule);
    }
}
