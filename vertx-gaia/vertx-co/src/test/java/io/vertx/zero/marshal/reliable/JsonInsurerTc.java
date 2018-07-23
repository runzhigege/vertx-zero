package io.vertx.zero.marshal.reliable;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.quiz.ZeroBase;
import io.vertx.up.epic.io.IO;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.exception.demon.DataTypeWrongException;
import io.vertx.zero.exception.demon.RequiredFieldException;
import org.junit.Test;

public class JsonInsurerTc extends ZeroBase {

    @Test(expected = RequiredFieldException.class)
    public void testRequiredObj() throws ZeroException {
        final JsonObject rule = IO.getYaml(this.getFile("rule-required.yml"));
        final JsonObject data = IO.getJObject(this.getFile("rule-required-data.json"));
        this.ensure(RequiredInsurer.class, data, rule);
    }

    @Test(expected = RequiredFieldException.class)
    public void testRequiredArray() throws ZeroException {
        final JsonObject rule = IO.getYaml(this.getFile("rule-required.yml"));
        final JsonArray data = IO.getJArray(this.getFile("rule-required-array.json"));
        this.ensure(RequiredInsurer.class, data, rule);
    }

    @Test(expected = DataTypeWrongException.class)
    public void testTypedObj() throws ZeroException {
        final JsonObject rule = IO.getYaml(this.getFile("rule-typed.yml"));
        final JsonObject data = IO.getJObject(this.getFile("rule-typed-jobject.json"));
        this.ensure(TypedInsurer.class, data, rule);
    }

    @Test(expected = DataTypeWrongException.class)
    public void testTypedNumber() throws ZeroException {
        final JsonObject rule = IO.getYaml(this.getFile("rule-typed.yml"));
        final JsonObject data = IO.getJObject(this.getFile("rule-typed-number.json"));
        this.ensure(TypedInsurer.class, data, rule);
    }

    @Test(expected = DataTypeWrongException.class)
    public void testTypedDecimal() throws ZeroException {
        final JsonObject rule = IO.getYaml(this.getFile("rule-typed.yml"));
        final JsonObject data = IO.getJObject(this.getFile("rule-typed-decimal.json"));
        this.ensure(TypedInsurer.class, data, rule);
    }

    @Test
    public void testTypedCorrect() throws ZeroException {
        final JsonObject rule = IO.getYaml(this.getFile("rule-typed.yml"));
        final JsonObject data = IO.getJObject(this.getFile("rule-typed-correct.json"));
        this.ensure(TypedInsurer.class, data, rule);
    }
}
